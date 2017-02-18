package cn.thinkbear.gc.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * Gui工厂类
 * 取得颜色值对象，字体对象，等常用的组件对象
 * 
 * @author ThinkBear
 * @date 2017年2月18日 下午10:22:42
 */

	
public class GuiFactory {

	public static final Color BLACK_A = Color.decode("#aaaaaa");
	public static final Color BLACK_B = Color.decode("#bbbbbb");
	public static final Color BLACK_C = Color.decode("#cccccc");
	public static final Color BLACK_D = Color.decode("#dddddd");
	public static final Color BLACK_E = Color.decode("#eeeeee");

	public static final Font FONT_SMALL_BOLD_OBJECT = new Font(
			GuiPara.FONT_NAME, Font.BOLD, GuiPara.FONT_SMALL);
	public static final Font FONT_SMALL_PLAIN_OBJECT = new Font(
			GuiPara.FONT_NAME, Font.PLAIN, GuiPara.FONT_SMALL);

	public static final Font FONT_BIG_BOLD_OBJECT = new Font(GuiPara.FONT_NAME,
			Font.BOLD, GuiPara.FONT_BIG);
	public static final Font FONT_BIG_PLAIN_OBJECT = new Font(
			GuiPara.FONT_NAME, Font.PLAIN, GuiPara.FONT_BIG);

	public static final Font FONT_AMONG_BOLD_OBJECT = new Font(
			GuiPara.FONT_NAME, Font.BOLD, GuiPara.FONT_AMONG);
	public static final Font FONT_AMONG_PLAIN_OBJECT = new Font(
			GuiPara.FONT_NAME, Font.PLAIN, GuiPara.FONT_AMONG);

	
	public static JLabel getAmongPlainLabel(String text) {
		JLabel label = new JLabel(text);
		label.setFont(FONT_AMONG_PLAIN_OBJECT);
		return label;
	}

	public static JLabel getBigBoldLabel(String text) {
		JLabel label = new JLabel(text);
		label.setFont(FONT_BIG_BOLD_OBJECT);
		return label;
	}

	public static JLabel getBigPlainLabel(String text) {
		JLabel label = new JLabel(text);
		label.setFont(FONT_BIG_PLAIN_OBJECT);
		return label;
	}

	public static JLabel getAmongBoldLabel(String text) {
		JLabel label = new JLabel(text);
		label.setFont(FONT_AMONG_BOLD_OBJECT);
		return label;
	}

	public static JButton getButton(String text, Object listener) {
		JButton newButton = new JButton(text);
		newButton.setFont(FONT_AMONG_PLAIN_OBJECT);
		newButton.addActionListener((ActionListener) listener);
		newButton.setOpaque(false);
		return newButton;
	}


	public static JButton getBigButton(String text, Object listener) {
		JButton newButton = new JButton(text);
		newButton.setFont(new Font(GuiPara.FONT_NAME, Font.BOLD,
				GuiPara.FONT_BIG));
		newButton.addActionListener((ActionListener) listener);
		newButton.setOpaque(false);
		return newButton;
	}
	
	public static Border getTitleBorder(String title) {
		TitledBorder titleBorder = new TitledBorder(BorderFactory.createEtchedBorder());
		titleBorder.setTitle(title);
		titleBorder.setTitleFont(GuiFactory.FONT_AMONG_PLAIN_OBJECT);
		titleBorder.setTitleColor(Color.BLACK);
		titleBorder.setTitleJustification(TitledBorder.LEFT);
		return titleBorder;
	}
	
	public static Border getTitleBorder(String title,int inPadd){
		Border titleBorder = getTitleBorder(title);
		Border insideBorder = BorderFactory.createEmptyBorder(inPadd,inPadd, inPadd, inPadd);
		return BorderFactory.createCompoundBorder(titleBorder, insideBorder);
	}

	public static JTextField getTextField() {
		return getTextField(null, 0, 0);
	}

	public static JTextField getTextField(int columns, int padd) {
		return getTextField(null, columns, padd);
	}

	public static JTextField getTextField(String text) {
		return getTextField(text, 0, 0);
	}

	public static JTextField getTextField(String text, int columns, int padd) {
		JTextField field = new JTextField();

		field.setFont(FONT_AMONG_PLAIN_OBJECT);
		if (text != null) {
			field.setText(text);
		}
		if (columns != 0) {
			field.setColumns(columns);
		}

		if (padd != 0) {
			field.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createEtchedBorder(),
					BorderFactory.createEmptyBorder(padd, padd, padd, padd)));
		}
		return field;
	}

}
