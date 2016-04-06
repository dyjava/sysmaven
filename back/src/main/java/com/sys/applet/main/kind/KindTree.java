package com.sys.applet.main.kind;

import javax.swing.JButton;

import com.sys.applet.main.TreePanel;

/** 
 * by dyong 2010-9-1
 */
public class KindTree extends TreePanel {
	
	private static final long serialVersionUID = -883445581798502670L;
	
	JButton addButton = new JButton();
	JButton listButton = new JButton();
	
	public KindTree() {
	    addButton.setText("新增类别");
	    addButton.addActionListener(new ButtonActionAdapter(new AddKindPanel()));
	    listButton.setText("查询修改");
	    listButton.addActionListener(new ButtonActionAdapter(new ListKindPanel()));
	    
	    buttonList.add(addButton) ;
	    buttonList.add(listButton) ;
	    
	    super.printButton() ;
	    new ButtonActionAdapter(new AddKindPanel()) ;
	}
	
}


