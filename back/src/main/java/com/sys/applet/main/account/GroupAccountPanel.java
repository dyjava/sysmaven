package com.sys.applet.main.account;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import com.sys.applet.ConstService;
import com.sys.applet.main.CommonPanel;
import com.sys.applet.util.DateChooserJTextField;
import com.sys.applet.util.DoubleUtil;
import com.sys.applet.util.TableFactory;
import com.sys.spring.account.domain.Account;
import com.sys.spring.account.domain.AccountTable;
import com.sys.spring.account.domain.Kind;

/** 
 * by dyong 2010-9-1
 */
public class GroupAccountPanel extends CommonPanel{
	private static final long serialVersionUID = 8094222460871915784L;
	
	JTextField titleText = new JTextField() ;
	JTextField moneyText = new JTextField() ;
	JComboBox kindBox = new JComboBox();
	
    JTextField beginTimeText = new DateChooserJTextField();
    JTextField endTimeText = new DateChooserJTextField();
    JComboBox typeBox = new JComboBox();
    private List<Kind> kindList ;
    
//	table
    List<List<Object>> data = new ArrayList<List<Object>>() ;
    
    public GroupAccountPanel() {
        kindList = ConstService.kindService.findOutKindList() ;
        kindBox.addItem("-请选择-") ;
        for(Kind k:kindList){
        	kindBox.addItem(k.getTitle()) ;
        }
        
        String beginDate = new SimpleDateFormat("yyyy-").format(new Date()) +"01-01" ;
        beginTimeText = new DateChooserJTextField(beginDate);
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) ;
        endTimeText= new DateChooserJTextField(endDate);
        typeBox.addItem("kind") ;
        typeBox.addItem("year") ;
        typeBox.addItem("month") ;
        typeBox.addItem("day") ;

//    	button
        JButton submitButton = new JButton();
        JButton imgButton = new JButton() ;
        
        submitButton.setText("确定");
        submitButton.addActionListener(new SubmitActionAdapter());
        
		imgButton.setText("图表显示");
        imgButton.addActionListener(new ImageActionAdapter());
        
    	fieldList.add(new JLabel("名称")) ;
    	fieldList.add(titleText) ;
    	fieldList.add(new JLabel("价格")) ;
    	fieldList.add(moneyText) ;
    	fieldList.add(new JLabel("类别")) ;
    	fieldList.add(kindBox) ;
    	fieldList.add(null) ;
    	fieldList.add(new JLabel("时间")) ;
    	fieldList.add(beginTimeText) ;
    	fieldList.add(endTimeText) ;
    	fieldList.add(new JLabel("统计类别")) ;
    	fieldList.add(typeBox) ;
    	fieldList.add(submitButton) ;
    	fieldList.add(imgButton) ;
    	
//        table
    	List<String> title = new ArrayList<String>() ;
    	title.add("次数") ;
    	title.add("价格") ;
    	title.add("类别") ;
    	title.add("说明") ;
        table = TableFactory.createtable(title, data);
        
        super.printSearchTableModel() ;
        
//      初始化表数据
        data = getData(beginDate,endDate, new Account(),"kind") ;
        TableFactory.freshTableData(table, data) ;
    }

    private void submitAction(ActionEvent e) {//查询 显示
    	String title = titleText.getText() ;
    	String money = moneyText.getText() ;
    	int index = kindBox.getSelectedIndex() ;
    	
    	String begin = beginTimeText.getText() ;
    	String end = endTimeText.getText() ;
    	String type = typeBox.getSelectedItem().toString() ;
    	
    	Account acc = new Account() ;
    	if(title!=null && title.length()>0){
    		acc.setTitle(title) ;
    	}
    	if(money!=null && money.trim().length()>0){
    		acc.setMoney(Double.parseDouble(money)) ;
    	}
    	if(index>0){
        	Kind kind = kindList.get(index-1) ;
    		acc.setKindid(kind.getUid()) ;
//    		acc.setKid(kind.getId());
    	}
    	
    	data = getData(begin, end, acc,type) ;
        TableFactory.freshTableData(table, data) ;
    }
    
//    查询结果输出到表格
    private List<List<Object>> getData(String begin,String end,Account account,String type){
    	List<List<Object>> data = new ArrayList<List<Object>>() ;
    	
    	List<AccountTable> accList = ConstService.accService.findAccountTableList(begin, end, account,type) ;
        int count=0;
        double allMoney=0.0 ;
    	if(accList!=null){
	        for(int i=0;i<accList.size();i++){
	        	List<Object> l = new ArrayList<Object>() ;
	        	AccountTable acc = accList.get(i) ;
	        	l.add(acc.getCountNum()) ;
	        	l.add(DoubleUtil.money(acc.getMoney())) ;
	        	l.add(acc.getType()) ;
	        	l.add(acc.getOther()) ;
	        	data.add(l) ;
	        	count++;
	        	allMoney+=acc.getMoney() ;
	        }
        }
        List<Object> l = new ArrayList<Object>() ;
        l.add("总计") ;
        l.add(count+"笔") ;
        l.add(DoubleUtil.money(allMoney)) ;
        l.add("") ;
        data.add(l) ;
        return data ;
    }

//  跳转到图片显示页面
    private void showImageAction(ActionEvent e){
    	String begin = beginTimeText.getText() ;
    	String end = endTimeText.getText() ;
    	Account acc = getAccount() ;
    	
    	String type = (String) typeBox.getSelectedItem() ;
    	List<AccountTable> list = ConstService.accService.findAccountTableList(begin, end, acc, type);
    	this.removeAll();
    	this.repaint() ;
    	this.add(new ListAccountImagePanel(list,type,begin,end));
    	this.validate();
    }
    private Account getAccount(){
//    	String title = titleText.getText() ;
//    	String money = moneyText.getText() ;
//    	int index = kindBox.getSelectedIndex() ;
    	
    	Account acc = new Account() ;
//    	if(title!=null && title.length()>0){
//    		acc.setTitle(title) ;
//    	}
//    	if(money!=null && money.trim().length()>0){
//    		acc.setMoney(Double.parseDouble(money)) ;
//    	}
//    	if(index>0){
//        	Kind kind = kindList.get(index-1) ;
//    		acc.setKindId(kind.getUid()) ;
//    	}
        return acc ;
    }
    
//  生成图片的监听
    class ImageActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            showImageAction(e);
        }
    }
    
    class SubmitActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submitAction(e);
        }
    }
    class ACellRenderer extends JLabel implements ListCellRenderer{
    	/**
		 * 
		 */
		private static final long serialVersionUID = -4919338275462886925L;
		ACellRenderer(){
    		setOpaque(true);
    	}
    	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
    		if (value!=null){
    	      setText(value.toString());
//    	      setIcon(new ImageIcon(".\\icons\\fruit"+(index+1)+".jpg"));
    	    }
    	    if (isSelected){
    	       setBackground(list.getSelectionBackground());
    	       setForeground(list.getSelectionForeground());
    	    }else{
    	       setBackground(list.getBackground());
    	       setForeground(list.getForeground());
    	    }
    	    return this;
    	}
    }
}
