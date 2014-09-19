package com.sys.applet.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sys.applet.ConstService;
import com.sys.applet.util.XYConstraints;
import com.sys.applet.util.XYLayout;

/** 
 * 列表数输出，控制父类
 * by dyong 2010-9-29
 */
public abstract class TreePanel extends JPanel {
	private static final long serialVersionUID = 5822207107303529098L;

	private BorderLayout borderLayout1 = new BorderLayout();
	private JPanel listF = new JPanel();
	private JPanel mainF = new JPanel();
	
	public ArrayList<JButton> buttonList = new ArrayList<JButton>();
	
	public TreePanel(){
		this.setLayout(borderLayout1);
	    listF.setBackground(Color.pink);
	    listF.setPreferredSize(new Dimension(80, 10));
	    listF.setLayout(new XYLayout());
	    
	    this.add(listF, java.awt.BorderLayout.WEST);
	    this.add(mainF, java.awt.BorderLayout.CENTER);
	    
	}
	/**
	 * 初始化按钮树
	 */
	public void printButton(){
		int x = 15 ;	//初始值X
		int y = 20 ;	//初始值Y
		int rank = 50 ;	//Y增长值
		
		int i = 0 ;		//计数器
		for(JButton button:buttonList){
		    listF.add(button, new XYConstraints(x, y+rank*i++, -1, -1));
		}
		

//	    列表树添加背景图片
	    Icon t=new ImageIcon(ConstService.ROOT+"/image/leftTree.JPG");
        JLabel ep = new JLabel(t,JLabel.CENTER);
        ep.setBounds(new Rectangle(0, 0, 80, 600));
        listF.add(ep, new XYConstraints(-100, 0, 0, 0));

	}
	
	/**
	 * 初始化默认显示页面
	 * @author dyong
	 *
	 */
	public class ButtonActionAdapter implements ActionListener {
		private JPanel panel ;
		public ButtonActionAdapter(JPanel panel){
			this.panel = panel ;
			print() ;
		}
		/**
		 * 刷新显示默认页面
		 */
		public void print(){
			mainF.removeAll();
			mainF.repaint() ;
			mainF.add(panel);
			mainF.validate();
		}
		public void actionPerformed(ActionEvent e) {
			mainF.removeAll();
			mainF.repaint() ;
			
			mainF.add(panel);
			mainF.validate();
			mainF.requestFocusInWindow() ;
//			panel.requestFocusInWindow() ;
//			panel.requestFocus() ;
//			panel.grabFocus() ;
			try {
				panel = panel.getClass().newInstance() ;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
//			CommonPanel cp = ((CommonPanel)panel) ;
//			cp.fieldList.get(0).grabFocus() ;
//			mainF.requestFocusInWindow() ;
//			panel.requestFocus() ;
//			panel.grabFocus() ;
		}
	}
}
