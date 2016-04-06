package com.sys.applet.main.income;

import javax.swing.JButton;

import com.sys.applet.main.TreePanel;

/** 
 * by dyong 2010-9-30
 */
public class IncomeTree extends TreePanel {

	private static final long serialVersionUID = -2316497301818785011L;

	JButton addButton = new JButton();
	JButton listButton = new JButton();
	
	public IncomeTree() {
	    addButton.setText("新增记录");
	    addButton.addActionListener(new ButtonActionAdapter(new AddIncomePanel()));
	    listButton.setText("查询修改");
	    listButton.addActionListener(new ButtonActionAdapter(new ListIncomePanel()));
	    
	    buttonList.add(addButton) ;
	    buttonList.add(listButton) ;
	    
	    super.printButton() ;
	    new ButtonActionAdapter(new AddIncomePanel()) ;
	}
}
