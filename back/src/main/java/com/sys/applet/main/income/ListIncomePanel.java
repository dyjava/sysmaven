package com.sys.applet.main.income;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.sys.applet.ConstService;
import com.sys.applet.main.CommonPanel;
import com.sys.applet.util.DateChooserJTextField;
import com.sys.applet.util.DoubleUtil;
import com.sys.applet.util.TableFactory;
import com.sys.spring.account.domain.Income;
import com.sys.spring.account.domain.Kind;

/** 
 * by dyong 2010-9-30
 */
public class ListIncomePanel extends CommonPanel {

	private static final long serialVersionUID = -4154503914028510006L;


	JTextField titleText = new JTextField() ;
	JTextField moneyText = new JTextField() ;
	JComboBox kindBox = new JComboBox();
    JTextField beginTimeText = new DateChooserJTextField();
    JTextField endTimeText = new DateChooserJTextField();
    private List<Kind> kindList ;
    
//	table
    List<List<Object>> data = new ArrayList<List<Object>>() ;
    
    public ListIncomePanel() {
        kindList = ConstService.kindService.findKindList(1) ;
        kindBox.addItem("-请选择-") ;
        for(Kind k:kindList){
        	kindBox.addItem(k.getTitle()) ;
        }
        String beginDate = new SimpleDateFormat("yyyy-").format(new Date()) +"01-01" ;
        beginTimeText.setText(beginDate) ;
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) ;
        endTimeText.setText(endDate) ;

//    	button
        JButton submitButton = new JButton();
        submitButton.setText("确定");
        submitButton.addActionListener(new SubmitActionAdapter());
        
    	fieldList.add(new JLabel("说明")) ;
    	fieldList.add(titleText) ;
    	fieldList.add(new JLabel("收入")) ;
    	fieldList.add(moneyText) ;
    	fieldList.add(new JLabel("类别")) ;
    	fieldList.add(kindBox) ;
    	fieldList.add(null) ;
    	fieldList.add(new JLabel("时间")) ;
    	fieldList.add(beginTimeText) ;
    	fieldList.add(endTimeText) ;
    	fieldList.add(submitButton) ;
    	
//        table
    	List<String> title = new ArrayList<String>() ;
    	title.add("UUID") ;
    	title.add("说明") ;
    	title.add("收入") ;
    	title.add("类别") ;
    	title.add("日期") ;
        table = TableFactory.createtable(title, data);
        table.addMouseListener(new TableMouseListener()) ;
        
        super.printSearchTableModel() ;
        
//        初始化表数据
        data = getData(beginDate,endDate, new Income()) ;
        TableFactory.freshTableData(table, data) ;
    }

    private void submitAction(ActionEvent e) {//查询 显示
    	String title = titleText.getText() ;
    	String money = moneyText.getText() ;
    	int index = kindBox.getSelectedIndex() ;
    	
    	String begin = beginTimeText.getText() ;
    	String end = endTimeText.getText() ;
    	
    	Income income = new Income() ;
    	if(title!=null && title.length()>0){
    		income.setTitle(title) ;
    	}
    	if(money!=null && money.trim().length()>0){
    		income.setMoney(Double.parseDouble(money)) ;
    	}
    	if(index>0){
        	Kind kind = kindList.get(index-1) ;
    		income.setKindid(kind.getUid()) ;
    	}

    	data = getData(begin, end, income) ;
        TableFactory.freshTableData(table, data) ;
    }
    
//    查询结果输出到表格
    private List<List<Object>> getData(String begin,String end,Income income){
    	List<List<Object>> data = new ArrayList<List<Object>>() ;
    	
    	List<Income> dataList = ConstService.incomeService.findIncomeList(begin, end, income) ;
    	double money =0 ;
    	int count =0 ;
        if(dataList!=null)
        for(int i=0;i<dataList.size();i++){
        	List<Object> l = new ArrayList<Object>() ;
        	Income in = dataList.get(i) ;
        	l.add(in.getId()) ;
        	l.add(in.getTitle()) ;
        	l.add(DoubleUtil.money(in.getMoney())) ;
        	l.add(in.getKindtitle()) ;
        	l.add(in.getDatetime()) ;
        	data.add(l) ;
        	count ++ ;
        	money +=in.getMoney() ;
        }
        List<Object> l = new ArrayList<Object>() ;
        l.add("合计：") ;
        l.add(count+"笔") ;
        l.add(DoubleUtil.money(money)) ;
        l.add("") ;
        l.add("") ;
        data.add(l) ;
        return data ;
    }

    private void update(int id){
    	this.removeAll();
    	this.repaint() ;
    	this.add(new UpdateIncomePanel(id));
    	this.validate();
    }
    class SubmitActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submitAction(e);
        }
    }
    class TableMouseListener extends MouseAdapter  {
    	public void mouseClicked(MouseEvent e){
    		int clickTimes = e.getClickCount();
    		if (clickTimes >= 1){
    			JTable table = (JTable)e.getSource() ;
    			int allRow = table.getRowCount() ;
    			int row = table.getSelectedRow() ;
//    			int col = table.getSelectedColumn() ;
    			String id = data.get(row).get(0).toString() ;
//    			System.out.println("Doublc Clicked!"+row+"/"+uid);
    			
//    			进入详细页面
    			if(row<allRow-1){
    				update(Integer.parseInt(id)) ;
//    			} else {
//    				showAcc(uid) ;
    			}
    		}
    	}
	}
}
