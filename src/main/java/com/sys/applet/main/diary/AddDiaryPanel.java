package com.sys.applet.main.diary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;



import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sys.applet.ConstService;
import com.sys.applet.main.CommonPanel;
import com.sys.spring.account.domain.Diary;

/** 
 * by dyong 2010-9-2
 */
public class AddDiaryPanel extends CommonPanel{
    
	private static final long serialVersionUID = 1217463325990042093L;
	//    text
    JTextField titleText = new JTextField();
    JTextArea noteText = new JTextArea();
    
//    button
    JButton submitBut = new JButton();
    JButton cancleBut = new JButton();
    
    public AddDiaryPanel() {
       labList.add(new JLabel("标题")) ;
       labList.add(new JLabel("内容")) ;
       
//       noteText.setWrapStyleWord(true) ;
//       noteText.setAutoscrolls(true) ;
       noteText.setLineWrap(true) ;
       fieldList.add(titleText) ;
       fieldList.add(noteText) ;
       
//        按钮
        submitBut.setText("提交");
        submitBut.addActionListener(new SubmitButtonActionAdapter());
        buttonList.add(submitBut) ;
        
        super.printFormModel() ;
    }

    private void cancelAction(ActionEvent e) {
    	clear() ;
    }
    private void submitAction(ActionEvent e) {
    	String message = "" ;
    	String title = titleText.getText() ;
    	String note	= noteText.getText() ;
    	
    	if(title==null || note==null ){
    		message = "信息不完整" ;
    	} else {
    		Diary d = new Diary() ;
    		d.setTitle(title) ;
    		d.setContent(note) ;
    		d.setDatetime(new Date().toLocaleString()) ;
			int re = ConstService.diaryService.insertDiary(d, ConstService.user) ;
			
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
