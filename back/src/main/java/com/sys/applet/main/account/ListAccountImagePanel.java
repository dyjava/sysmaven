package com.sys.applet.main.account;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.Year;

import com.sys.applet.ConstService;
import com.sys.applet.main.CommonPanel;
import com.sys.applet.util.XYConstraints;
import com.sys.applet.util.jfreechart.Demo;
import com.sys.applet.util.jfreechart.bar.BarChartData;
import com.sys.applet.util.jfreechart.bar.BarChartDemo;
import com.sys.applet.util.jfreechart.date.DateChartData;
import com.sys.applet.util.jfreechart.date.DateChartDemo;
import com.sys.applet.util.jfreechart.date.TimeData;
import com.sys.applet.util.jfreechart.pie.PieChartData;
import com.sys.applet.util.jfreechart.pie.PieChartDemo;
import com.sys.spring.account.domain.AccountTable;

/** 
 * by dyong 2010-9-1
 */
public class ListAccountImagePanel extends CommonPanel{
	private static final long serialVersionUID = 8094222460871915784L;
	
	private JButton but = new JButton();
	private boolean isBar = false ;
	private List<AccountTable> list ;
	private String type ;
	private String begin ;
	private String end ;
	
	
    public ListAccountImagePanel(List<AccountTable> list,String type,String begin,String end) {
    	this.list = list ;
    	this.type = type ;
    	this.begin = begin ;
    	this.end = end ;
    	
    	but.setText("显示柱状图");
    	but.addActionListener(new ButActionAdapter());
    	this.add(but, new XYConstraints(200,0,0,0)) ;
    	
    	printImage() ;
    }
//    柱状图和其他形式图像的切换
    public ListAccountImagePanel(List<AccountTable> list,String type,String begin,String end,boolean isBar){
    	this.list = list ;
    	this.type = type ;
    	this.begin = begin ;
    	this.end = end ;
    	this.isBar = isBar ;
    	
    	if(isBar){
    		but.setText("返回");
    	} else {
    		but.setText("显示柱状图");
    	}
    	but.addActionListener(new ButActionAdapter());
    	this.add(but, new XYConstraints(200,0,0,0)) ;
    	
    	printImage() ;
    }
//    生产图片
    public void printImage() {

    	//输出图片
    	Demo demo = new DateChartDemo("按日期统计","日期","花销") ;
		String path = ConstService.ROOT+"/img/"+type+"_"+begin+"_"+end+"_"+isBar+"_rp.jpg" ;
		
		try{
			List<?> data = new ArrayList<DateChartData>() ;
			if(!isBar){
				if(type.equals("day")){
					demo = new DateChartDemo("按日期统计("+begin+" "+end+")","日期","花销(元)") ;
					data = getDataListDate(list);
				} else if(type.equals("month")){
					demo = new DateChartDemo("按月统计("+begin+" "+end+")","月份","花销(元)") ;
					data = getDataListMonth(list);
				} else if(type.equals("year")){
					demo = new DateChartDemo("按年统计("+begin+" "+end+")","月份","花销(元)") ;
					data = getDataListYear(list);
				} else if(type.equals("kind")){
					demo = new PieChartDemo("按类别统计("+begin+" "+end+")","元") ;
					data = getPieList(list);
				}
			} else {
				demo = new BarChartDemo("按"+type+"统计("+begin+" "+end+")","类别","花销(元)") ;
				data = getDataListBar(list);
			}
			JFreeChart chart = demo.createChart(data) ;
			demo.writeToJpg(chart, path,700,500) ;
		} catch(ParseException e){
			e.printStackTrace() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	//显示图片
    	Icon p=new ImageIcon(path);
        JLabel ep = new JLabel(p,JLabel.CENTER);
        ep.setBounds(new Rectangle(0, 0, 80, 600));
        this.add(ep, new XYConstraints(50, 20, 0, 0));

    }

    /**
     * 按日期查
     * @param data
     * @return
     * @throws ParseException
     */
	private List<DateChartData> getDataListDate(List<AccountTable> data) throws ParseException {
		ArrayList<DateChartData> list = new ArrayList<DateChartData>() ;
		
		DateChartData dateData = new DateChartData() ;
		dateData.date_title = "日期" ;
		dateData.TimeClass = new Day() ;
		for(AccountTable acc:data){
			dateData.data.add(new TimeData(DateFormat.getDateInstance().parse(acc.getType()),acc.getMoney())) ;
		}
		list.add(dateData) ;
		return list ;
	}

	/**
	 * 按月查
	 * @param data
	 * @return
	 * @throws ParseException
	 */
	private List<DateChartData> getDataListMonth(List<AccountTable> data) throws ParseException {
		ArrayList<DateChartData> list = new ArrayList<DateChartData>() ;
		
		DateChartData dateData = new DateChartData() ;
		dateData.date_title = "月份" ;
		dateData.TimeClass = new Month() ;
		for(AccountTable acc:data){
			dateData.data.add(new TimeData(DateFormat.getDateInstance().parse(acc.getType()+"-15"),acc.getMoney())) ;
		}
		list.add(dateData) ;
		return list ;
	}
	
	/**
	 * 按年查
	 * @param data
	 * @return
	 * @throws ParseException
	 */
	private List<DateChartData> getDataListYear(List<AccountTable> data) throws ParseException {
		ArrayList<DateChartData> list = new ArrayList<DateChartData>() ;
		
		DateChartData dateData = new DateChartData() ;
		dateData.date_title = "年份" ;
		dateData.TimeClass = new Year() ;
		for(AccountTable acc:data){
			dateData.data.add(new TimeData(DateFormat.getDateInstance().parse(acc.getType()+"-06-01"),acc.getMoney())) ;
		}
		list.add(dateData) ;
		return list ;
	}
	
	/**
	 * 按类别查
	 * @param data
	 * @return
	 * @throws ParseException
	 */
	private List<PieChartData> getPieList(List<AccountTable> data) throws ParseException {
		List<PieChartData> list = new ArrayList<PieChartData>() ;
		for(AccountTable acc:data){
			list.add(new PieChartData(acc.getOther(),acc.getMoney())) ;
		}
		return list ;
	}
	
	/**
	 * 柱状图
	 * @return
	 */
	private List<BarChartData> getDataListBar(List<AccountTable> data){
		List<BarChartData> list = new ArrayList<BarChartData>() ;
		
		for(AccountTable acc:data){
//			BarChartData bar = new BarChartData(100, "北京", "苹果") ;
			if(type.equals("kind")){
				list.add(new BarChartData(acc.getMoney(), acc.getOther(), acc.getOther())) ;
			} else {
				list.add(new BarChartData(acc.getMoney(), acc.getType(), acc.getType())) ;
			}
		}
		
		return list ;
	}

	private void butAction(ActionEvent e) {
		ListAccountImagePanel panel = new ListAccountImagePanel(list,type,begin,end,!isBar);
		
		this.removeAll();
    	this.repaint() ;
    	this.add(panel);
    	this.validate();
	}
    class ButActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            butAction(e);
        }
    }
}
