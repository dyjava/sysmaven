package com.sys.applet.main.diary;

import javax.swing.JButton;

import com.sys.applet.main.TreePanel;

/** 
 * by dyong 2010-9-1
 */
public class DiaryTree extends TreePanel {

	private static final long serialVersionUID = -4682160841650877776L;
	
	JButton addButton = new JButton();
	JButton listButton = new JButton();
	
	public DiaryTree() {
	    addButton.setText("新增记录");
	    addButton.addActionListener(new ButtonActionAdapter(new AddDiaryPanel()));
	    listButton.setText("查询修改");
	    listButton.addActionListener(new ButtonActionAdapter(new ListDiaryPanel()));

	    buttonList.add(addButton);
	    buttonList.add(listButton);
	    
	    super.printButton() ;
	    new ButtonActionAdapter(new AddDiaryPanel()) ;
//	    addAction.print() ;
	}
	
}


