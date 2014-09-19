package com.sys.applet.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.sys.applet.util.XYConstraints;
import com.sys.applet.util.XYLayout;

/** 
 * by dyong 2010-9-21
 */
public class CommonPanel extends JPanel {

	private static final long serialVersionUID = 7110139687990358872L;
	
	XYLayout xYLayout1 = new XYLayout();
	 
	 public ArrayList<JLabel> labList = new ArrayList<JLabel>();
	 public ArrayList<JComponent> fieldList = new ArrayList<JComponent>();
	 
	 public ArrayList<JButton> buttonList = new ArrayList<JButton>();
	 
	 private JScrollPane jScrollPane1 = new JScrollPane();
	 public JTable table = new JTable();
	 public CommonPanel(){
	     xYLayout1.setWidth(800);
	     xYLayout1.setHeight(700);
		 this.setLayout(xYLayout1);
//	     this.setBackground(SystemColor.LIGHT_GRAY);
	     this.setBackground(SystemColor.controlLtHighlight);
	     
	 }
	 
	 /**
	  * 表单提交模板
	  * 单列，标签、文本框对应
	  * 最后一行以提交按钮结束
	  */
	 public void printFormModel(){
		 Font font = new Font("宋体", Font.PLAIN, 12) ;	//标签字体
		 int x_lab = 80 ;	//标签的X坐标
		 int x_text = 120 ;	//输入框的X坐标
		 int height_dif = 30 ;	//行间距
		 int y_start = 20 ;	//起始行Y坐标
		 
		 int y = 20 ;	//当前行Y坐标
		 for(int i=0;i<labList.size();i++){
			 y = y_start+height_dif*i ;
			 labList.get(i).setFont(font) ;
			 this.add(labList.get(i), new XYConstraints(x_lab, y, -1, -1));
		 }
		 for(int i=0;i<fieldList.size();i++){
			 int y_line = y_start+height_dif*i ;
//			 跳过空行
			 if(fieldList.get(i)!=null){
				 JComponent item = fieldList.get(i) ;
				 if(item instanceof JTextArea){
//					 item.setLineWrap(true) ;
					 item.setBackground(new Color(40,200,58)) ;
					 this.add(item, new XYConstraints(x_text, y_line, 500, 300));
					 y_line +=300 ;
				 } else {
					 this.add(item, new XYConstraints(x_text, y_line, 100, 20));
				 }
			 }
			 if(y < y_line){
				 y = y_line ;
			 }
		 }
		 
		 for(int i=0;i<buttonList.size();i++){
			 this.add(buttonList.get(i), new XYConstraints(100+i*100, height_dif+y, -1, -1));
		 }
	 }
	 
	 /**
	  * 表格模板
	  * 查询表单 表格
	  */
	 public void printSearchTableModel(){
		 int y = 20 ;		//y坐标
		 int x_start = 80 ;	//X起始坐标
		 int y_hight = 30 ;	//行距间隔
		 
		 jScrollPane1.getViewport().setBackground(SystemColor.info);
		 jScrollPane1.getViewport().add(table);
		 
		 if(fieldList!=null && fieldList.size()>0){
			 int x_dif = 0 ;	//x坐标差值
			 for(int i=0;i<fieldList.size();i++){
				 JComponent item = fieldList.get(i) ;
				 if(item==null){
					 y=y+y_hight ;
					 x_dif = 0;
					 continue ;
				 }
				 if(item instanceof JLabel){
					 this.add(item, new XYConstraints(x_start+x_dif, y, -1, -1));
					 x_dif+=50 ;
				 } else if(item instanceof JButton){
					 this.add(item, new XYConstraints(x_start+x_dif, y, -1, -1));
					 x_dif+=80 ;
				 } else {
					 this.add(item, new XYConstraints(x_start+x_dif, y, 100, 20));
					 x_dif+=120 ;
				 }
			}
			y=y+y_hight ;
		}
		
		this.add(jScrollPane1, new XYConstraints(x_start, y, -1, -1));
	 }
	 
	 protected void showMessageDialog(String message){
		 JOptionPane.showMessageDialog(this, message) ;
	 }
}
