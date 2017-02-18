package cn.thinkbear.gc.gui.panel;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cn.thinkbear.gc.gui.BaseView;
import cn.thinkbear.gc.gui.GuiFactory;
import cn.thinkbear.gc.utils.MyFindException;
/**
 * 布局文件XML数据输入面板
 * 
 * @author ThinkBear
 * @date 2017年2月18日 下午10:31:07
 */
public class XmlSourcePanel extends BaseView {

	private JPanel mainPanel = null;

	private JTextArea dataArea = null;
	private JScrollPane dataAreaScroll = null;

	public XmlSourcePanel() {
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

		this.dataAreaScroll.setBorder(GuiFactory.getTitleBorder("布局文件XML数据输入面板", 5));
		this.dataArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.dataArea.setFont(GuiFactory.FONT_AMONG_PLAIN_OBJECT);
		this.dataArea.setLineWrap(false);
		this.dataArea.setWrapStyleWord(true);
	}
	
	/**
	 * 取得文本框中的数据内容
	 * @return 加工后的文本数据
	 * @throws MyFindException
	 */
	public String getXmlSource() throws MyFindException{
		String data = this.dataArea.getText().trim();
		if("".equals(data)){
			throw new MyFindException("XML源数据为空，请输入");
		}
		int index = data.indexOf("android:id=\"@+id/");
		if(index == -1){
			throw new MyFindException("未发现相关id值，无法生成代码");
		}
		
		//把换行符替换成空格
		data = data.replace("\n", " ");
		
		return data;
	}
	
	public JPanel getMainPanel(){
		return this.mainPanel;
	}

}
