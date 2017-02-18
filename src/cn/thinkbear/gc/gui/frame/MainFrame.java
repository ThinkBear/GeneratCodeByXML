package cn.thinkbear.gc.gui.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import cn.thinkbear.gc.gui.BaseView;
import cn.thinkbear.gc.gui.GuiFactory;
import cn.thinkbear.gc.gui.dialog.TemplateDialog;
import cn.thinkbear.gc.gui.panel.JavaSourcePanel;
import cn.thinkbear.gc.gui.panel.XmlSourcePanel;
import cn.thinkbear.gc.utils.MyFindException;
import cn.thinkbear.gc.vo.GCClass;
/**
 * 主界面
 * 
 * @author ThinkBear
 * @date 2017年2月18日 下午10:25:48
 */
public class MainFrame extends BaseView implements ActionListener {

	private JFrame mainFrame = null;

	private final int SCREEN_WIDTH = 1000;
	private final int SCREEN_HEIGHT = 600;
	private JPanel mainPanel = null;
	private XmlSourcePanel xmlSourcePanel = null;
	private JavaSourcePanel javaSourcePanel = null;
	private JSplitPane centerSplit = null;

	private JPanel buttonPanel = null;

	private JButton generatBut = null;
	private JButton templateBut = null;

	public MainFrame() {
		super.doCreateGui();
	}

	@Override
	public void doCase() {
		this.mainFrame = new JFrame("代码生成器");
		this.mainPanel = new JPanel(new BorderLayout());
		this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		this.templateBut = GuiFactory.getBigButton("更新模板", this);
		this.generatBut = GuiFactory.getBigButton("开始生成", this);
		this.xmlSourcePanel = new XmlSourcePanel();
		this.javaSourcePanel = new JavaSourcePanel();

		this.centerSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.xmlSourcePanel.getMainPanel(),
				this.javaSourcePanel.getMainPanel());
	}

	@Override
	public void doAppend() {
		this.mainPanel.add(this.centerSplit, BorderLayout.CENTER);
		this.mainFrame.add(this.mainPanel);

		this.buttonPanel.add(this.templateBut);
		this.buttonPanel.add(this.generatBut);

		this.mainPanel.add(this.buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public void doSet() {
		this.centerSplit.setDividerLocation(400);
		this.centerSplit.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		this.centerSplit.setOpaque(true);
		this.centerSplit.setDividerSize(10);
		this.centerSplit.setOneTouchExpandable(true);

		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		this.mainFrame.setLocationRelativeTo(null);
		this.mainFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.generatBut) {//点击了开始生成按钮
			/**
			 * 核心
			 */
			try {
				String data = this.xmlSourcePanel.getXmlSource();
				String regex = "<(*)android:id=\"(*)\"";
				Pattern p = Pattern.compile(regex.replace("(*)", ".*?"));
				Matcher matcher = p.matcher(data);
				List<GCClass> all = new ArrayList<GCClass>();
				while (matcher.find()) {
					String group = matcher.group();//取得每一组数据
					GCClass gc = GCClass.getInstance(group);//把数据交给getInstance方法进行处理
					if (gc != null) {
						all.add(gc);
					}
				}
				
				if(all.size() == 0){
					JOptionPane.showMessageDialog(this.mainFrame, "GCClass个数为空，无法生成java代码");
				}else{
					//解析出来的对象集合交给outputJavaSource方法进行显示操作
					this.javaSourcePanel.outputJavaSource(all);
				}
				
			} catch (MyFindException e1) {
				JOptionPane.showMessageDialog(this.mainFrame, e1.getMessage());
			}

		}else if(e.getSource() == this.templateBut){
			new TemplateDialog(this.mainPanel);
		}
	}

}
