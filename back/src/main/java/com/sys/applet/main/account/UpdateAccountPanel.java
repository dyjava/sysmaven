package com.sys.applet.main.account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.sys.applet.ConstService;
import com.sys.applet.main.CommonPanel;
import com.sys.applet.util.DateChooserJTextField;
import com.sys.spring.account.domain.Account;
import com.sys.spring.account.domain.Kind;

/** 
 * by dyong 2010-9-1
 */
public class UpdateAccountPanel extends CommonPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8682544940082314455L;
	//  text
	JTextField titleText = new JTextField();
    JTextField moneyText = new JTextField();
    JComboBox kindBox = new JComboBox();
    JTextField timeText = new DateChooserJTextField();
    private List<Kind> kindList ;
    
    int id ;
    public UpdateAccountPanel(int id) {
    	this.id = id ;
    	Account acc = ConstService.accService.findAccountById(id) ;
    	
    	labList.add(new JLabel("名称")) ;
    	labList.add(new JLabel("价格")) ;
    	labList.add(new JLabel("类别")) ;
    	labList.add(new JLabel("时间")) ;
    	labList.add(new JLabel("记录人：\t"+acc.getUsername())) ;
    	
//        数据
        kindList = ConstService.kindService.findOutKindList() ;
        for(Kind k:kindList){
        	kindBox.addItem(k.getTitle()) ;
        	if(k.getId()==acc.getKid()){
        		kindBox.setSelectedItem(k.getTitle()) ;
        	}
        }
        titleText.setText(acc.getTitle()) ;
        moneyText.setText(String.valueOf(acc.getMoney())) ;
        timeText.setText(acc.getDatetime()) ;
        fieldList.add(titleText) ;
        fieldList.add(moneyText) ;
        fieldList.add(kindBox) ;
        fieldList.add(timeText) ;
        fieldList.add(null) ;
        
//        按钮      button
        JButton submitBut = new JButton();
//        JButton cancleBut = new JButton();
        submitBut.setText("提交");
        submitBut.addActionListener(new SubmitButtonActionAdapter());
        buttonList.add(submitBut) ;
        super.printFormModel() ;
    }

	private void submitAction(ActionEvent e) {//查询 显示
    	String title = titleText.getText() ;
    	String money = moneyText.getText() ;
    	int kindex = kindBox.getSelectedIndex() ;
    	Kind kind = kindList.get(kindex) ;
    	String time = timeText.getText() ;
    	
    	Account acc = ConstService.accService.findAccountById(id) ;
    	acc.setTitle(title) ;
    	acc.setMoney(Double.parseDouble(money)) ;
    	acc.setKid(kind.getId()) ;
    	acc.setKindid(kind.getUid()) ;
    	acc.setKindtitle(kind.getTitle()) ;
    	acc.setDatetime(time) ;
    	ConstService.accService.updateAccount(acc) ;
    	
//    	list
    	this.removeAll();
    	this.repaint() ;
    	this.add(new ListAccountPanel());
    	this.validate();
    }
    
    class SubmitButtonActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submitAction(e);
        }
    }
    
}
