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
import com.sys.domain.account.Diary;

/** 
 * by dyong 2010-9-1
 */
public class UpdateDiaryPanel extends CommonPanel{

//  text
	JTextField titleText = new JTextField();
	JTextArea contentText = new JTextArea();
	
//  button
	JButton submitBut = new JButton();

	private int pageNo ;
	int uid ;
    public UpdateDiaryPanel(int uid) {
    	this.uid = uid ;
    	Diary diary = ConstService.diaryService.findDiaryById(uid) ;
    	titleText.setText(diary.getTitle()) ;

    	contentText.setLineWrap(true) ;
    	contentText.setText(diary.getContent()) ;
    	
    	labList.add(new JLabel("标题："+diary.getTitle())) ;
    	labList.add(new JLabel("作者："+diary.getUsername())) ;
    	labList.add(new JLabel("内容：")) ;
    	
    	fieldList.add(null) ;
    	fieldList.add(null) ;
    	fieldList.add(contentText) ;
    	
    	//       按钮
    	submitBut.setText("提交");
    	submitBut.addActionListener(new SubmitActionAdapter());
    	buttonList.add(submitBut) ;
    	super.printFormModel() ;
    }

    @SuppressWarnings("deprecation")
	private void submitAction(ActionEvent e) {//查询 显示
    	String title = titleText.getText() ;
    	String content	= contentText.getText() ;
    	Diary diary = ConstService.diaryService.findDiaryById(uid) ;
    	diary.setTitle(title) ;
    	diary.setContent(content) ;
    	diary.setDatetime(new Date().toLocaleString()) ;
    	ConstService.diaryService.updateDiary(diary,ConstService.user) ;
    	
//    	list
    	this.removeAll();
    	this.repaint() ;
    	this.add(new ListDiaryPanel());
    	this.validate();
    }
    
    class SubmitActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submitAction(e);
        }
    }
    
}
