package cn.thinkbear.gc.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import cn.thinkbear.gc.gui.BaseView;
import cn.thinkbear.gc.gui.GuiFactory;
import cn.thinkbear.gc.utils.TemplateUtils;
/**
 * 模板更新窗口
 * 
 * @author ThinkBear
 * @date 2017年2月18日 下午10:25:12
 */
public class TemplateDialog extends BaseView implements ActionListener {

	public static final int OK = 0;
	private JDialog mainDialog = null;
	private JComponent parent = null;
	private JPanel mainPanel = null;
	private JSplitPane centerSplit = null;
	private JTextArea dataArea = null;
	private JScrollPane dataAreaScroll = null;
	private JTextArea toastArea = null;

	private JPanel buttonPanel = null;
	private JButton saveBut = null;
	

	private int result = -1;

	public int getResult() {
		return this.result;
	}

	public TemplateDialog(JComponent parent) {
		this.parent = parent;
		super.doCreateGui();
	}

	@Override
	public void doCase() {
		this.mainDialog = new JDialog();
		this.mainPanel = new JPanel(new BorderLayout(5, 5));
		
		
		this.dataArea = new JTextArea();
		this.dataAreaScroll = new JScrollPane(this.dataArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		this.toastArea = new JTextArea();

		this.centerSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.toastArea,
				this.dataAreaScroll);
		
		this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.saveBut = GuiFactory.getButton("保存模板", this);
	}

	@Override
	public void doAppend() {
		

		this.buttonPanel.add(this.saveBut);

		this.mainPanel.add(this.centerSplit, BorderLayout.CENTER);
		this.mainPanel.add(this.buttonPanel, BorderLayout.SOUTH);
		
		this.mainDialog.add(this.mainPanel);
	}

	@Override
	public void doSet() {
		
		

		this.centerSplit.setDividerLocation(250);
		this.centerSplit.setBorder(BorderFactory.createEmptyBorder());
		this.centerSplit.setOpaque(true);
		this.centerSplit.setDividerSize(0);
		
		this.toastArea.setBorder(GuiFactory.getTitleBorder("模板说明", 5));
		this.dataAreaScroll.setBorder(GuiFactory.getTitleBorder("模板数据", 5));
		
		this.toastArea.setText(TemplateUtils.IMPORT_FLAG);
		this.toastArea.append("\n");
		this.toastArea.append("此标记将会替换成 \n“import xx.xx.xxx”");
		this.toastArea.append("\n");
		this.toastArea.append("\n");
		this.toastArea.append(TemplateUtils.DEF_FLAG);
		this.toastArea.append("\n");
		this.toastArea.append("此标记将会替换成\n“private 对象 id值”");
		this.toastArea.append("\n");
		this.toastArea.append("\n");
		this.toastArea.append(TemplateUtils.INIT_FLAG);
		this.toastArea.append("\n");
		this.toastArea.append("此标记将会替换成\n“this.id值  = super.findVi..”");
		this.toastArea.append("\n");
		this.toastArea.append("\n");
		
		this.toastArea.setEditable(false);
		this.toastArea.setLineWrap(true);
		this.toastArea.setFont(GuiFactory.FONT_AMONG_BOLD_OBJECT);
		this.toastArea.setWrapStyleWord(true);
		
		this.dataArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		this.dataArea.setFont(GuiFactory.FONT_AMONG_PLAIN_OBJECT);
		
		this.dataArea.setText(TemplateUtils.getInstance().getTemplateData());
		
		this.mainDialog.setTitle("模板");
		// 设置对话框大小
		this.mainDialog.setSize(800, 600);
		// 设置对话框默认关闭处理操作
		this.mainDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// 设置对话框显示于相对于主窗口的正中央位置
		this.mainDialog.setLocationRelativeTo(this.parent);
		// 设置此 dialog 的模式类型,对话框阻塞同一 Java 应用程序中的所有顶层窗口
		this.mainDialog.setModalityType(ModalityType.APPLICATION_MODAL);
		// 显示对话框
		this.mainDialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.saveBut) {
			String data = this.dataArea.getText().trim();
			if(TemplateUtils.getInstance().saveTemplateData(data)){
				this.mainDialog.dispose();
			}else{
				JOptionPane.showMessageDialog(this.mainPanel, "模板保存失败，请重试");
			}
			
		}
	}

}
