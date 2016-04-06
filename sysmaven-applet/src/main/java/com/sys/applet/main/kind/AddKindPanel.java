package com.sys.applet.main.kind;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.sys.applet.ConstService;
import com.sys.applet.main.CommonPanel;
import com.sys.domain.account.Kind;

/** 
 * by dyong 2010-9-2
 */
public class AddKindPanel extends CommonPanel{

	private static final long serialVersionUID = 6232238597212926261L;
	//    text
    JTextField titleText = new JTextField();
    JTextField noteText = new JTextField();
    JComboBox parentBox = new JComboBox();
    private List<Kind> kindList ;
    
    public AddKindPanel() {
//       标签
        labList.add(new JLabel("名称")) ;
        labList.add(new JLabel("简介")) ;
        labList.add(new JLabel("类别")) ;
       
        fieldList.add(titleText) ;
        fieldList.add(noteText) ;
//        数据
        kindList = ConstService.kindService.findKindList(0) ;
        for(Kind k:kindList){
        	parentBox.addItem(k.getTitle()) ;
        }
        fieldList.add(parentBox) ;
//        按钮      button
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
    	String note	= noteText.getText() ;
    	String parentId	= parentBox.getSelectedItem().toString() ;
    	for(Kind k:kindList){
        	if(k.getTitle().equals(parentId)){
        		parentId = k.getUid() ;
        		break ;
        	}
        }
    	
    	if(title==null || note==null ){
    		message = "信息不完整" ;
    	} else {
    		Kind kind = new Kind() ;
    		kind.setTitle(title) ;
    		kind.setNote(note) ;
    		kind.setParentId(parentId) ;
    		
			ConstService.kindService.insertKind(kind) ;
    		message = "添加成功" ;
    		clear() ;
    	}
    	this.showMessageDialog(message) ;
    }
    private void clear(){
    	titleText.setText("") ;
    	noteText.setText("") ;
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
