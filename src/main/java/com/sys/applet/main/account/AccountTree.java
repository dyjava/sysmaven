package com.sys.applet.main.account;

import javax.swing.JButton;

import com.sys.applet.main.TreePanel;

/** 
 * by dyong 2010-9-1
 */
public class AccountTree extends TreePanel {
	private static final long serialVersionUID = 5306146858818019665L;
	
	JButton registerButton = new JButton();
	JButton listButton = new JButton();
	JButton groupButton = new JButton();
	
	public AccountTree() {
	    registerButton.setText("新增记录");
	    registerButton.addActionListener(new ButtonActionAdapter(new AddAccountPanel()));

	    listButton.setText("查询修改");
	    listButton.addActionListener(new ButtonActionAdapter(new ListAccountPanel()));
	    
	    groupButton.setText("查询统计");
	    groupButton.addActionListener(new ButtonActionAdapter(new GroupAccountPanel()));

	    buttonList.add(registerButton);
	    buttonList.add(listButton);
	    buttonList.add(groupButton);
	    
	    
	    super.printButton() ;
	    new ButtonActionAdapter(new AddAccountPanel()) ;
	}
	
}
