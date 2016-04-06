package com.sys.applet.main.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sys.applet.ConstService;
import com.sys.applet.main.CommonPanel;
import com.sys.spring.user.domain.User;

/** 
 * by dyong 2010-9-1
 */
public class RegisterUserPanel extends CommonPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7526622175059738878L;
//    text
    JTextField nameText = new JTextField();
    JTextField userNameText = new JTextField();
    JPasswordField passwordText = new JPasswordField();
    
    public RegisterUserPanel() {

//       标签
        labList.add(new JLabel("用户名:")) ;
        labList.add(new JLabel("姓名:")) ;
        labList.add(new JLabel("密 码:")) ;

        fieldList.add(userNameText) ;
        fieldList.add(nameText) ;
        fieldList.add(passwordText) ;
        
//        按钮      button
        JButton runBut = new JButton();
        JButton reBackBut = new JButton();
        runBut.setText("提交");
        runBut.addActionListener(new RunButtonActionAdapter());
        buttonList.add(runBut) ;
        reBackBut.setText("取消");
        reBackBut.addActionListener(new ReBackButtonActionAdapter());
        buttonList.add(reBackBut) ;
        
        super.printFormModel() ;
    }

    private void rebackAction(ActionEvent e) {
    	clear() ;
    }
    @SuppressWarnings("deprecation")
	private void submitAction(ActionEvent e) {
    	String message = "" ;
    	String name = nameText.getText().trim() ;
    	String username = userNameText.getText().trim() ;
		String pwd = passwordText.getText().trim() ;
    	
    	if(name==null || name.length()==0 ||
    			username==null || username.length()==0
    			|| pwd==null || pwd.length()==0){
    		message = "请完整输入注册信息。" ;
    	} else {
	    	User user = new User() ;
	    	user.setName(name) ;
	    	user.setUsername(username) ;
	    	user.setPassword(pwd) ;
			ConstService.userService.userRegister(user) ;
			
    		message = "注册成功" ;
    		clear() ;
    	}
    	this.showMessageDialog(message) ;
    }
    private void clear(){
    	nameText.setText("") ;
    	userNameText.setText("") ;
    	passwordText.setText("") ;
    }

    class RunButtonActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submitAction(e) ;
        }
    }

    class ReBackButtonActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            rebackAction(e);
        }
    }
    
}
