package com.sys.applet.main.diary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.sys.applet.ConstService;
import com.sys.applet.main.CommonPanel;
import com.sys.spring.account.domain.Diary;

/** 
 * by dyong 2010-9-1
 */
public class ShowDiaryPanel extends CommonPanel{
	
	//  Lab
	JButton submitBut = new JButton();
	
	int uid ;
	private int pageNo ;
	public ShowDiaryPanel(int id) {
		this.uid = uid ;

//		 按钮
		submitBut.setText("修改");
		submitBut.addActionListener(new SubmitActionAdapter());
		buttonList.add(submitBut) ;
		
		Diary diary = ConstService.diaryService.findDiaryById(id) ;
		labList.add(new JLabel("标题："+diary.getTitle())) ;
		labList.add(new JLabel("作者："+diary.getUsername()+" "+diary.getDatetime())) ;
		JLabel title = new JLabel("内容："+diary.getContent()) ;
		title.setAutoscrolls(true) ;
		labList.add(title) ;
		
		super.printFormModel() ;
	}
	
	private void submitAction(ActionEvent e) {//查询 显示
    	this.removeAll();
    	this.repaint() ;
    	this.add(new UpdateDiaryPanel(uid));
    	this.validate();
    }
    
    class SubmitActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submitAction(e);
        }
    }
}
