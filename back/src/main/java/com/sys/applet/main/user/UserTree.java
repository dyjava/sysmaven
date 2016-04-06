package com.sys.applet.main.user;

import javax.swing.JButton;

import com.sys.applet.ConstService;
import com.sys.applet.main.TreePanel;

/** 
 * by dyong 2010-9-1
 */
public class UserTree extends TreePanel {
	private static final long serialVersionUID = 2738010164904409693L;
	
	public UserTree() {
		JButton registerButton = new JButton() ;
		JButton listButton = new JButton() ;
//		JButton updateButton = new JButton() ;
		JButton updatepwdButton = new JButton() ;
		
    	registerButton.setText("用户注册");
	    registerButton.addActionListener(new ButtonActionAdapter(new RegisterUserPanel()));
	    
	    listButton.setText("信息修改");
	    listButton.addActionListener(new ButtonActionAdapter(new ListUserPanel()));
		    
//		updateButton.setText("信息修改");
//		updateButton.addActionListener(new ButtonActionAdapter(new UpdateUserPanel(ConstService.user.getId(),"update")));
		
		updatepwdButton.setText("密码修改");
		updatepwdButton.addActionListener(new ButtonActionAdapter(new UpdateUserPanel(ConstService.user.getId(),"pwd")));
		    
		new ButtonActionAdapter(new RegisterUserPanel());

	    buttonList.add(registerButton);
	    buttonList.add(listButton);
//	    buttonList.add(updateButton);
	    buttonList.add(updatepwdButton);
	    
	    super.printButton() ;
	}
	
}
