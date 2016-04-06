package com.sys.spring.account.domain;

/** 
 * by dyong 2010-6-16
 */
public class Account {

	private int id ;
	private String uid ;
	private String title ;
	private double money =0 ;
	
	private int kid ;
	private String kindid ;
	private String kindtitle ;
	private String datetime ;
	private String userId ;
	private String username ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getKindid() {
		return kindid;
	}
	public void setKindid(String kindid) {
		this.kindid = kindid;
	}
	public String getKindtitle() {
		return kindtitle;
	}
	public void setKindtitle(String kindTitle) {
		this.kindtitle = kindTitle;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String userName) {
		this.username = userName;
	}
	public int getKid() {
		return kid;
	}
	public void setKid(int kid) {
		this.kid = kid;
	}
	
	
	
}
