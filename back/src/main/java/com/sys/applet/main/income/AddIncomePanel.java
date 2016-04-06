package com.sys.applet.main.income;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.sys.applet.ConstService;
import com.sys.applet.main.CommonPanel;
import com.sys.applet.util.DateChooserJTextField;
import com.sys.spring.account.domain.Income;
import com.sys.spring.account.domain.Kind;
import com.sys.spring.user.domain.User;

/** 
 * by dyong 2010-9-30
 */
public class AddIncomePanel extends CommonPanel {

	private static final long serialVersionUID = -553537348345683336L;

//  text
  JTextField titleText = new JTextField();
  JTextField moneyText = new JTextField();
  JComboBox kindBox = new JComboBox();
  JTextField timeText = new DateChooserJTextField();
  private List<Kind> kindList ;
  
  public AddIncomePanel() {
  	labList.add(new JLabel("说明")) ;
  	labList.add(new JLabel("收入")) ;
  	labList.add(new JLabel("类别")) ;
  	labList.add(new JLabel("时间")) ;
  	labList.add(new JLabel("记录人：\t"+ConstService.user.getName())) ;
  	
//      数据
      kindList = ConstService.kindService.findKindList(1) ;
      for(Kind k:kindList){
      	kindBox.addItem(k.getTitle()) ;
      }
      timeText.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date())) ;
      fieldList.add(titleText) ;
      fieldList.add(moneyText) ;
      fieldList.add(kindBox) ;
      fieldList.add(timeText) ;
      fieldList.add(null) ;
      
//      按钮  button
      JButton submitBut = new JButton();
      JButton cancleBut = new JButton();
      
      submitBut.setText("提交");
      submitBut.addActionListener(new SubmitButtonActionAdapter());
      cancleBut.setText("取消");
      cancleBut.addActionListener(new CancelButtonActionAdapter());
      buttonList.add(submitBut) ;
      buttonList.add(cancleBut) ;
      super.printFormModel() ;
  }

  private void cancelAction(ActionEvent e) {
  	clear() ;
  }
  private void submitAction(ActionEvent e) {
  	String message = "" ;
  	String title	= titleText.getText() ;
  	String money	= moneyText.getText() ;
  	String kind		= kindBox.getSelectedItem().toString() ;
  	String time		= timeText.getText() ;
  	
  	if(title==null || money==null || time==null || 
  			title.length()==0 || money.length()==0 || time.length()==0){
  		message = "信息不完整" ;
  	} else {
  		User user = ConstService.user ;
      	Income in = new Income() ;
      	in.setTitle(title) ;
      	in.setMoney(Double.parseDouble(money)) ;
      	for(Kind k:kindList){
      		if(k.getTitle().equals(kind)){
      			in.setKindid(k.getUid()) ;
      			in.setKindtitle(k.getTitle()) ;
      			break ;
      		}
      	}
      	in.setDatetime(time) ;
      	in.setUserId(user.getUid()) ;
      	in.setUsername(user.getUsername()) ;
      	
		ConstService.incomeService.insertIncome(in) ;
  		message = "添加成功" ;
  		clear() ;
  	}
  	this.showMessageDialog(message) ;
  }
  private void clear(){
  	titleText.setText("") ;
  	moneyText.setText("") ;
  }

  class SubmitButtonActionAdapter implements ActionListener {
      public void actionPerformed(ActionEvent e) {
          submitAction(e) ;
      }
  }

  class CancelButtonActionAdapter implements ActionListener {
      public void actionPerformed(ActionEvent e) {
          cancelAction(e);
      }
  }
  
}
