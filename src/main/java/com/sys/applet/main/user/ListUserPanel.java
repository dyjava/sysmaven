package com.sys.applet.main.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.sys.applet.ConstService;
import com.sys.applet.main.CommonPanel;
import com.sys.applet.util.TableFactory;
import com.sys.spring.user.domain.User;

/** 
 * by dyong 2010-9-1
 */
public class ListUserPanel extends CommonPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8508052268988065914L;
	JTextField usernameText = new JTextField() ;
	JTextField rankText = new JTextField() ;
	
//	table
    List<List<Object>> data = new ArrayList<List<Object>>() ;
    
    public ListUserPanel() {
    	List<String> title = new ArrayList<String>() ;
    	title.add("ID") ;
    	title.add("姓名") ;
    	title.add("用户名") ;
    	title.add("权限") ;
//    	title.add("角色") ;
    	title.add("修改密码") ;
        table = TableFactory.createtable(title, data) ;
        table.addMouseListener(new TableMouseListener()) ;

//    	button
        JButton submitButton = new JButton();
        JButton reSetButton = new JButton();
        
        submitButton.setText("确定");
        submitButton.addActionListener(new SubmitActionAdapter());
        reSetButton.setText("取消");
        reSetButton.addActionListener(new ResetActionAdapter());

        fieldList.add(new JLabel("用户名")) ;
        fieldList.add(usernameText) ;
        fieldList.add(new JLabel("权限")) ;
        fieldList.add(rankText) ;
        fieldList.add(submitButton) ;
        fieldList.add(reSetButton) ;
        
        super.printSearchTableModel() ;
        
        data = getData(new User()) ;
        TableFactory.freshTableData(table, data) ;
    }

    private void submitAction(ActionEvent e) {//查询 显示
    	String name = usernameText.getText() ;
    	String rank = rankText.getText() ;
        User u = new User() ;
        u.setName(name) ;
        u.setRank(rank) ;
        
        data = getData(u) ;
        TableFactory.freshTableData(table, data) ;
    }
    
//    查询数据
    private List<List<Object>> getData(User u){
    	List<List<Object>> data = new ArrayList<List<Object>>() ;
//    	查询
    	List<User> ulist = ConstService.userService.getUsersByUser(u) ;
    	
//    	封装
        if(ulist!=null)
        for(int i=0;i<ulist.size();i++){
        	List<Object> l = new ArrayList<Object>() ;
        	User user = ulist.get(i) ;
        	l.add(user.getId()) ;
        	l.add(user.getName()) ;
        	l.add(user.getUsername()) ;
        	l.add(user.getRank()) ;
//        	l.add(user.getRole()) ;
        	l.add("修改密码");
        	data.add(l) ;
        }
        return data ;
    }

    private void resetAction(ActionEvent e) {
//        jop.showMessageDialog(this,"");
    }
    
    private void updatePanel(int uid,String type){
    	this.removeAll();
    	this.repaint() ;
    	this.add(new UpdateUserPanel(uid,type));
    	this.validate();
    }
    class SubmitActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submitAction(e);
        }
    }
    class ResetActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            resetAction(e);
        }
    }
    class TableMouseListener extends MouseAdapter  {
    	public void mouseClicked(MouseEvent e){
    		int clickTimes = e.getClickCount();
    		if (clickTimes >= 1){
    			JTable table = (JTable)e.getSource() ;
    			int row = table.getSelectedRow() ;
    			int col = table.getSelectedColumn() ;
    			int id = Integer.parseInt(data.get(row).get(0).toString()) ;
//    			System.out.println("Doublc Clicked!"+row+"/"+uid);
    			
//    			进入详细页面
    			String type = "update" ;
    			if(col==5){
    				type = "pwd" ;
    			}
    			updatePanel(id,type) ;
    		}
    	}
	}
    
}
