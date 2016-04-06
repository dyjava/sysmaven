package com.sys.spring.account.domain;

/** 
 * by dyong 2010-6-16
 */
public class AccountTable {

	private int countNum ;	//统计数目
	private double money ;	//总和
	
	private String type ;	//统计类型
	private String other ;	//其他信息
	
	public double getMoney() {
		return money;
	}
	public int getCountNum() {
		return countNum;
	}
	public void setCountNum(int countNum) {
		this.countNum = countNum;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
}
