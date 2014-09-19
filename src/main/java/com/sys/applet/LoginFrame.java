package com.sys.applet;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sys.applet.util.XYConstraints;
import com.sys.applet.util.XYLayout;
import com.sys.spring.user.domain.User;
import com.sys.util.Logs;

/**
 * 登陆页面
 * @author dyong
 *
 */
public class LoginFrame extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = -115593344199240572L;
	JPanel contentPane;	//
    XYLayout xYLayout1 = new XYLayout();
    
    private String proof ;
//    Lab
    JLabel userLab = new JLabel();
    JLabel passwordLab = new JLabel();
    JLabel proofLab = new JLabel();
    JLabel proofTextLab = new JLabel();
    
//    text
    JTextField userText = new JTextField();
    JPasswordField passwordText = new JPasswordField();
    JTextField proofText = new JTextField();
    
//    button
    JButton runBut = new JButton();
    JButton cancelBut = new JButton();
    
    JOptionPane jop = new JOptionPane();
    
    public LoginFrame() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

//  初始化登陆页面
    private void jbInit() throws Exception {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(xYLayout1);
        contentPane.setBackground(SystemColor.controlLtHighlight);
        contentPane.setToolTipText("");
        this.setIconImage(null);
        this.setResizable(false);
        setSize(new Dimension(300, 200));
        setTitle("记账系统登陆");
        
//       标签
        userLab.setText("用户名");
        userLab.setFont(new Font("宋体", Font.PLAIN, 12)) ;
        passwordLab.setText("密码");
        passwordLab.setFont(new Font("宋体", Font.PLAIN, 12)) ;
        proofLab.setText("验证码");
        proofLab.setFont(new Font("宋体", Font.PLAIN, 12)) ;
        changeProofText() ;
//        proofTextLab.setText(proof) ;
		proofTextLab.addMouseListener(new ProofMouseListener()) ;
		
//      按钮，添加监控
        runBut.setText("提交");
        runBut.addActionListener(new RunButtonActionAdapter());
        cancelBut.setText("退出");
        cancelBut.addActionListener(new CancelButtonActionAdapter());
        
//        contentPane.add(jop1, new XYConstraints(800, 600, 800, 600));
        contentPane.add(userLab, new XYConstraints(40, 20, -1, -1));
        contentPane.add(userText, new XYConstraints(100, 20, 100, 20));
        contentPane.add(passwordLab, new XYConstraints(40, 50, -1, -1));
        contentPane.add(passwordText, new XYConstraints(100, 50, 100, 20));
        contentPane.add(proofLab, new XYConstraints(40, 80, -1, -1));
        contentPane.add(proofText, new XYConstraints(100, 80, 60, 20));
        contentPane.add(proofTextLab, new XYConstraints(180, 80,-1,-1));
        
        contentPane.add(runBut, new XYConstraints(80, 120, -1, -1));
        contentPane.add(cancelBut, new XYConstraints(180, 120, -1, -1));
        
        //按键监控
        userText.addKeyListener(new EnterKeyListener()) ;
        passwordText.addKeyListener(new EnterKeyListener()) ;
        proofText.addKeyListener(new EnterKeyListener()) ;
        
        //添加背景图片
        Icon t=new ImageIcon(ConstService.ROOT+"/image/login.JPG");
        JLabel ep = new JLabel(t,JLabel.LEADING);
        ep.setDisabledIcon(null);
        contentPane.add(ep, new XYConstraints(-30, -50, 0, 0));
    }

//    退出
    public void cancelBut_actionPerformed(ActionEvent e) {
        System.exit(0) ;
    }
//    登陆方法
    public void runBut_actionPerformed(ActionEvent e) {
    	StringBuffer buf = new StringBuffer("LoginFrame|runBut_actionPerformed") ;
    	long start = System.currentTimeMillis() ;
    	
    	String message = "" ;
    	String name = userText.getText() ;
    	String pwd = passwordText.getText() ;//getToolTipText() ;
    	String proof = proofText.getText() ;
    	buf.append("|").append(name).append(",").append(pwd).append(",").append(proof) ;
    	
    	if(proof.equals(this.proof) || true){
//    		登录查询
    		User u = new User() ;
    		u.setUsername(name) ;
    		u.setPassword(pwd) ;
			User user = ConstService.userService.userLogin(u) ;
    		if(user!=null){
    			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    			ConstService.setUser(user) ;
    			MainFrame main = new MainFrame();	//创建下一个界面的对象
                dispose();							//关闭当前界面
                main.setSize(800, 600); 			//设置界面大小
//                main.setSize(screenSize.width, screenSize.height-20) ;
                main.setLocation(250, 150); 		//设置界面位置
                main.setLocation(0,0);
                main.setVisible(true); 				//显示界面
//                main.setAlwaysOnTop(true) ;		//总是前端显示
                
                buf.append("|true|").append(System.currentTimeMillis()-start) ;
                Logs.info(buf);
                return ;
    		} else {
    			message="用户名或密码错误。" ;
    		}
    	} else {
    		message = "验证码输入错误。" ;
    	}
    	changeProofText() ;
    	jop.showMessageDialog(this, message) ;
    	
    	buf.append("|false>").append(message).append("|").append(System.currentTimeMillis()-start) ;
    	Logs.info(buf);
    }

    class RunButtonActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            runBut_actionPerformed(e) ;
        }
    }

    class CancelButtonActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cancelBut_actionPerformed(e);
        }
    }
    
//    验证码更新
    public void changeProofText(){
    	proof = String.valueOf((int)(Math.random()*9000 +1000)) ;
    	proofTextLab.setText(proof) ;
    }
//    监控鼠标，更新验证码
    class ProofMouseListener implements MouseListener{
    	public void mouseClicked(MouseEvent e) {
    		changeProofText();
    	}
    	public void mouseEntered(MouseEvent e) {}

    	public void mouseExited(MouseEvent e) {}

    	public void mousePressed(MouseEvent e) {}

    	public void mouseReleased(MouseEvent e) {}
    	
    }

//    按键监控，取回车键提交
    class EnterKeyListener implements KeyListener{
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode() ;
			if(code==10){
				runBut_actionPerformed(null) ;
			}
		}
	
		public void keyReleased(KeyEvent e) {
			
		}
	
		public void keyTyped(KeyEvent e) {
			
		}
    }
}



