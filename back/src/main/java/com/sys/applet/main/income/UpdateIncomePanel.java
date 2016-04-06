package com.sys.applet.main.income;

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
import com.sys.spring.account.domain.Income;
import com.sys.spring.account.domain.Kind;

/** 
 * by dyong 2010-9-30
 */
public class UpdateIncomePanel extends CommonPanel {
	private static final long serialVersionUID = -3273560975846853063L;
	//  text
	JTextField titleText = new JTextField();
    JTextField moneyText = new JTextField();
    JComboBox kindBox = new JComboBox();
    JTextField timeText = new DateChooserJTextField();
    private List<Kind> kindList ;
    
    int id ;
    public UpdateIncomePanel(int id) {
    	this.id = id ;
    	Income income = ConstService.incomeService.findIncomeById(id) ;
    	
    	labList.add(new JLabel("名称")) ;
    	labList.add(new JLabel("价格")) ;
    	labList.add(new JLabel("类别")) ;
    	labList.add(new JLabel("时间")) ;
    	labList.add(new JLabel("记录人：\t"+income.getUsername())) ;
    	
//        数据
        kindList = ConstService.kindService.findKindList(1) ;
        for(Kind k:kindList){
        	kindBox.addItem(k.getTitle()) ;
        	if(k.getUid().equals(income.getKindid())){
        		kindBox.setSelectedItem(k.getTitle()) ;
        	}
        }
        titleText.setText(income.getTitle()) ;
        moneyText.setText(String.valueOf(income.getMoney())) ;
        timeText.setText(income.getDatetime()) ;
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
    	
    	Income income = ConstService.incomeService.findIncomeById(id) ;
    	income.setTitle(title) ;
    	income.setMoney(Double.parseDouble(money)) ;
    	income.setKindid(kind.getUid()) ;
    	income.setKindtitle(kind.getTitle()) ;
    	income.setDatetime(time) ;
    	ConstService.incomeService.updateIncome(income) ;
    	
//    	list
    	this.removeAll();
    	this.repaint() ;
    	this.add(new ListIncomePanel());
    	this.validate();
    }
    
    class SubmitButtonActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submitAction(e);
        }
    }
    
}
