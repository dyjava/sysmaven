package com.sys.applet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sys.applet.main.WellcomeFrame;
import com.sys.applet.main.account.AccountTree;
import com.sys.applet.main.diary.DiaryTree;
import com.sys.applet.main.income.IncomeTree;
import com.sys.applet.main.kind.KindTree;
import com.sys.applet.main.user.UserTree;

/** 
 * 登陆后主页面
 * @author dyong
 *
 */
public class MainFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 112547L;

	BorderLayout borderLayout1 = new BorderLayout();
    
	JLabel userOut = new JLabel("",JLabel.CENTER);
	
    JButton acc_button = new JButton();
    JButton in_button = new JButton();
    JButton kind_button = new JButton();
    JButton diary_button = new JButton();
//    JButton photo_button = new JButton();
    JButton user_button = new JButton();
    JButton out_button = new JButton();

    JPanel topF = new JPanel();
    JPanel mainF = new JPanel();
    GridLayout gridLayout1 = new GridLayout();
    JOptionPane jop = new JOptionPane();
//    Icon icon=new ImageIcon(".\\src\\image\\1.gif");
//    Icon i=new ImageIcon(".\\src\\image\\user.ico");
//    JLabel lb = new JLabel(icon,JLabel.CENTER);
    
    public MainFrame(){
    	getContentPane().setLayout(borderLayout1);
    	topF.setBackground(new Color(212, 154, 227));

        mainF.setLayout(gridLayout1);
        this.setResizable(false);
        this.setTitle("个人记账系统--"+ConstService.user.getName());
        
//        按钮设置
    	acc_button.setBackground(Color.white);
    	acc_button.setForeground(Color.blue);
    	acc_button.setText("花销");
    	acc_button.addMouseListener(new AccountMouseAdapter());
    	
    	in_button.setBackground(Color.white);
    	in_button.setForeground(Color.blue);
    	in_button.setText("收入");
    	in_button.addMouseListener(new IncomeMouseAdapter());
    	
    	kind_button.setBackground(Color.white);
    	kind_button.setForeground(Color.blue);
    	kind_button.setText("分类管理");
    	kind_button.addMouseListener(new KindMouseAdapter());
    	
    	diary_button.setBackground(Color.white);
    	diary_button.setForeground(Color.blue);
    	diary_button.setText("个人日记");
    	diary_button.addMouseListener(new DiaryMouseAdapter());
    	
    	user_button.setBackground(Color.white);
    	user_button.setForeground(Color.blue);
    	user_button.setText("个人信息");
    	user_button.addMouseListener(new UserMouseAdapter());
    	
    	out_button.setBackground(Color.white);
    	out_button.setForeground(Color.blue);
    	out_button.setText("退出系统");
    	out_button.addMouseListener(new OutMouseAdapter());
    	
    	userOut.setText(" --"+ConstService.user.getName()) ;
    	
//    	分隔符
    	String slip = "--" ;
    	
//    	部署按钮
//    	UserRank rank = ConstService.rank ;
//    	if(rank.accunt=='1'){
    		topF.add(acc_button);
    		topF.add(new JLabel(slip)) ;
//    	}
//    	if(rank.income=='1'){
    		topF.add(in_button) ;
    		topF.add(new JLabel(slip)) ;
//    	}
//    	if(rank.diary=='1'){
        	topF.add(diary_button);
    		topF.add(new JLabel(slip)) ;
//    	}
//    	if(rank.diary=='1'){
//        	topF.add(photo_button);
//    		topF.add(new JLabel(slip)) ;
//    	}
//    	if(rank.kind=='1'){
    		topF.add(kind_button);
    		topF.add(new JLabel(slip)) ;
//    	}
    	topF.add(user_button);
		topF.add(new JLabel(slip)) ;
//    	topF.add(userOut) ;
    	topF.add(out_button);
    	
    	this.getContentPane().add(mainF, java.awt.BorderLayout.CENTER);
    	this.getContentPane().add(topF, java.awt.BorderLayout.NORTH);
        
    	mainF.add(new WellcomeFrame());	//初次进入显示的欢迎信息	
    }
    private void accountMouseReleased(MouseEvent e){
    	mainF.removeAll() ;
    	mainF.add(new AccountTree()) ;
    	mainF.validate() ;
    }
    private void incomeMouseReleased(MouseEvent e){
    	mainF.removeAll() ;
    	mainF.add(new IncomeTree()) ;
    	mainF.validate() ;
    }
    private void kindMouseReleased(MouseEvent e){
    	mainF.removeAll() ;
    	mainF.add(new KindTree()) ;
    	mainF.validate() ;
    }
    private void diaryMouseReleased(MouseEvent e){
    	mainF.removeAll() ;
    	mainF.add(new DiaryTree()) ;
    	mainF.validate() ;
    }
    private void userMouseReleased(MouseEvent e){
    	mainF.removeAll() ;
    	mainF.add(new UserTree()) ;
    	mainF.validate() ;
    }
    @SuppressWarnings("static-access")
	private void outMouseReleased(MouseEvent e){
    	int rel=jop.showConfirmDialog(this,"你要退出系统吗？","退出",jop.YES_NO_OPTION ) ;
        if(rel==jop.YES_OPTION ){
            System.exit(0);
        }
    }

    class AccountMouseAdapter extends MouseAdapter{
    	public void mouseReleased(MouseEvent e) {
    		accountMouseReleased(e);
        }
    }
    class IncomeMouseAdapter extends MouseAdapter{
    	public void mouseReleased(MouseEvent e) {
    		incomeMouseReleased(e);
        }
    }
    class KindMouseAdapter extends MouseAdapter{
    	public void mouseReleased(MouseEvent e) {
    		kindMouseReleased(e);
        }
    }
    class DiaryMouseAdapter extends MouseAdapter{
    	public void mouseReleased(MouseEvent e) {
    		diaryMouseReleased(e);
        }
    }
    class UserMouseAdapter extends MouseAdapter{
    	public void mouseReleased(MouseEvent e) {
    		userMouseReleased(e);
        }
    }
    class OutMouseAdapter extends MouseAdapter{
    	public void mouseReleased(MouseEvent e) {
    		outMouseReleased(e);
        }
    }
}
