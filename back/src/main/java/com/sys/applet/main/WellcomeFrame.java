package com.sys.applet.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sys.applet.ConstService;
import com.sys.applet.util.XYConstraints;

/** 
 * 登陆后的欢迎页面
 * by dyong 2010-9-1
 */
public class WellcomeFrame extends JPanel {

	private static final long serialVersionUID = -6186179840612899135L;
	JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    
    
    public WellcomeFrame() {
        this.setLayout(null);
        jLabel1.setBackground(Color.lightGray);
        jLabel1.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 20));
        jLabel1.setForeground(Color.red);
        jLabel1.setText("欢迎使用!");
        jLabel1.setBounds(new Rectangle(20, 100, 154, 52));
        this.setBackground(SystemColor.info);
//        lbtu.setBounds(new Rectangle(0, 0, 521, 391));
        jLabel2.setText("设计者：邸永");
        jLabel2.setBounds(new Rectangle(20, 150, 270, 43));
        jLabel3.setText("设计语言：java");
        jLabel3.setBounds(new Rectangle(20, 200, 252, 45));
        jLabel4.setText("设计目的：家庭记账和记事用。");
        jLabel4.setBounds(new Rectangle(20, 250, 252, 45));
        
        this.add(jLabel1);
        this.add(jLabel2);
        this.add(jLabel3);
        this.add(jLabel4);
        
      //添加背景图片
        Icon t=new ImageIcon(ConstService.ROOT+"/image/wellcome.JPG");
        JLabel ep = new JLabel(t,JLabel.CENTER);
//        ep.setBounds(new Rectangle(0, 0, 800, 600));
        ep.setBounds(new Rectangle(0, 0, t.getIconWidth(), t.getIconHeight()));
//        ep.setDisabledIcon(null);
        this.add(ep, new XYConstraints(0, 0, 0, 0));
    }

}
