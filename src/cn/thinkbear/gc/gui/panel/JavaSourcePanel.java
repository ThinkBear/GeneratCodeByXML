package cn.thinkbear.gc.gui.panel;

import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cn.thinkbear.gc.gui.BaseView;
import cn.thinkbear.gc.gui.GuiFactory;
import cn.thinkbear.gc.utils.TemplateUtils;
import cn.thinkbear.gc.vo.GCClass;
/**
 * Java源码输出面板
 * 
 * @author ThinkBear
 * @date 2017年2月18日 下午10:29:51
 */
public class JavaSourcePanel extends BaseView {

	private JPanel mainPanel = null;
	private JTextArea dataArea = null;
	private JScrollPane dataAreaScroll = null;

	public JavaSourcePanel() {
		this.doCreateGui();
	}

	@Override
	public void doCase() {
		this.mainPanel = new JPanel(new BorderLayout());
		this.dataArea = new JTextArea();
		
		this.dataAreaScroll = new JScrollPane(this.dataArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	}

	@Override
	public void doAppend() {
		this.mainPanel.add(this.dataAreaScroll);
	}

	@Override
	public void doSet() {

		this.dataAreaScroll.setBorder(GuiFactory.getTitleBorder("Java源码输出面板", 5));

		this.dataArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.dataArea.setFont(GuiFactory.FONT_AMONG_PLAIN_OBJECT);
		this.dataArea.setLineWrap(false);
		this.dataArea.setWrapStyleWord(true);
	}
	
	public void outputJavaSource(List<GCClass> all){
		Iterator<GCClass> ite = all.iterator();
		StringBuffer importBuf = new StringBuffer();
		StringBuffer defBuf = new StringBuffer();
		StringBuffer initBuf = new StringBuffer();
		Set<String> importSet = new LinkedHashSet<String>();
		while(ite.hasNext()){
			GCClass gc = ite.next();
			importSet.add(gc.getPackageClassName());
			
			defBuf.append("private");
			defBuf.append(" ");
			defBuf.append(gc.getClassName());
			defBuf.append(" ");
			defBuf.append(gc.getId());
			defBuf.append(";");
			defBuf.append("\r\n    ");
			
			
			initBuf.append("this.");
			initBuf.append(gc.getId());
			initBuf.append(" ");
			initBuf.append("=");
			initBuf.append(" ");
			initBuf.append("(");
			initBuf.append(gc.getClassName());
			initBuf.append(")");
			initBuf.append(" ");
			initBuf.append("super.findViewById(R.id.");
			initBuf.append(gc.getId());
			initBuf.append(");");
			initBuf.append("\r\n        ");
		}
		
		
		Iterator<String> importIter = importSet.iterator();
		while(importIter.hasNext()){
			importBuf.append("import");
			importBuf.append(" ");
			importBuf.append(importIter.next());
			importBuf.append(";");
			importBuf.append("\r\n");
		}
		
		
		String templateData = TemplateUtils.getInstance().getTemplateData();
		if(templateData == null){
			JOptionPane.showConfirmDialog(this.mainPanel, "模板数据为空，无法生成java代码");
		}else{
			templateData = templateData.replace(TemplateUtils.IMPORT_FLAG, importBuf.toString());
			templateData = templateData.replace(TemplateUtils.DEF_FLAG, defBuf.toString());
			templateData = templateData.replace(TemplateUtils.INIT_FLAG, initBuf.toString());
			this.dataArea.setText(templateData);
		}
	}
	
	public JPanel getMainPanel(){
		return this.mainPanel;
	}

}
