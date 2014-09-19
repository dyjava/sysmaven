package com.sys.applet.main.diary;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import com.sys.applet.ConstService;
import com.sys.applet.main.CommonPanel;
import com.sys.applet.util.TableFactory;
import com.sys.spring.account.domain.Diary;

/** 
 * by dyong 2010-9-1
 */
public class ListDiaryPanel extends CommonPanel{

    List<List<Object>> data = new ArrayList<List<Object>>() ;
    
    private int pageNo ;
    public ListDiaryPanel() {
    	
    	List<String> title = new ArrayList<String>() ;
    	title.add("UUID") ;
    	title.add("标题") ;
    	title.add("内容") ;
    	title.add("日期") ;
    	title.add("用户") ;
    	title.add("修改") ;
        table = TableFactory.createtable(title, data) ;
        
		table.addMouseListener(new TableMouseListener()) ;
        
		super.printSearchTableModel() ;
//        
        TableFactory.freshTableData(table, getData(0)) ;
    }
    
//    查询数据
    private List<List<Object>> getData(int pageNo){
//    	查询
    	List<Diary> ulist = ConstService.diaryService.findDiaryListByUser(ConstService.user) ;
    	
//    	封装
    	int psize = 10 ;
        if(ulist!=null)
        for(int i=psize*pageNo;i<ulist.size()&&i<psize*(pageNo+1);i++){
        	List<Object> l = new ArrayList<Object>() ;
        	Diary d = ulist.get(i) ;
        	l.add(d.getId()) ;
        	l.add(d.getTitle()) ;
        	l.add(d.getContent()) ;
        	l.add(d.getDatetime().split(" ")[0]) ;
        	l.add(d.getUsername()) ;
        	l.add("修改") ;
        	data.add(l) ;
        }
        return data ;
    }
    
//    详细页面
    private void showDiary(int uid){
    	this.removeAll();
    	this.repaint() ;
    	this.add(new ShowDiaryPanel(uid));
    	this.validate();
    }
    private void updateDiary(int uid){
    	this.removeAll();
    	this.repaint() ;
    	this.add(new UpdateDiaryPanel(uid));
    	this.validate();
    }
    
    class TableMouseListener extends MouseAdapter  {
    	public void mouseClicked(MouseEvent e){
    		int clickTimes = e.getClickCount();
    		if (clickTimes >= 1){
    			JTable table = (JTable)e.getSource() ;
    			int row = table.getSelectedRow() ;
    			int col = table.getSelectedColumn() ;
    			int uid = Integer.parseInt(data.get(row).get(0).toString()) ;
//    			System.out.println("Doublc Clicked!"+row+"/"+uid);
    			
//    			进入详细页面
    			if(col==4){
    				updateDiary(uid) ;
    			} else {
    				showDiary(uid) ;
    			}
    		}
    	}
	}
    
}
