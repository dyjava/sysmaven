package com.sys.applet.main.kind;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.sys.applet.ConstService;
import com.sys.applet.main.CommonPanel;
import com.sys.spring.account.domain.Kind;

/** 
 * by dyong 2010-9-1
 */
public class UpdateKindPanel extends CommonPanel{
	private static final long serialVersionUID = -4133121811861357959L;
	//  text
	JTextField titleText = new JTextField();
	JTextField noteText = new JTextField();
	
//  button
	JButton submitBut = new JButton();

    int id ;
    public UpdateKindPanel(int id) {
    	this.id = id ;
    	Kind kind = ConstService.kindService.findKindById(id) ;
    	
    	titleText.setText(kind.getTitle()) ;
    	noteText.setText(kind.getNote()) ;
    	
    	labList.add(new JLabel("ID："+kind.getId())) ;
    	labList.add(new JLabel("标题：")) ;
    	labList.add(new JLabel("简介：")) ;
    	
    	fieldList.add(null) ;
    	fieldList.add(titleText) ;
    	fieldList.add(noteText) ;
    	
    	//       按钮
    	submitBut.setText("提交");
    	submitBut.addActionListener(new SubmitActionAdapter());
    	buttonList.add(submitBut) ;
    	super.printFormModel() ;
    }
    
	private void submitAction(ActionEvent e) {//查询 显示
    	String title = titleText.getText() ;
    	String note	= noteText.getText() ;
    	Kind kind = ConstService.kindService.findKindById(id) ;
    	kind.setTitle(title) ;
    	kind.setNote(note) ;
    	ConstService.kindService.updateKind(kind) ;
    	
//    	list
    	this.removeAll();
    	this.repaint() ;
    	this.add(new ListKindPanel());
    	this.validate();
    }
    
    class SubmitActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submitAction(e);
        }
    }
    
}
