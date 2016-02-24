package com.skymiracle.gameUnion.models;

import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.Mdo.Title;

@Title("用户信息表")
public abstract class UserInfo<T extends UserInfo<T>> extends AbsMdo<T>{
	
	public UserInfo(Mdo_X<T> mdoX) {
		super(mdoX);
	}
	
	@Title("用户名")
	private String username;
	
	@Title("QQ号")
	private String qq;
	
	@Title("真实姓名")
	private String realname;
	
	@Title("姓别")
	public static enum GENDER {unknown, male, female};
	private GENDER gender = GENDER.unknown;
	
	@Title("生日年份")
	private int birthYear;
	
	@Title("生日月份")
	private int birthMonth;
	
	@Title("生日日期")
	private int birthDay;
	
	@Title("省份")
	private String province;
	
	@Title("邮箱")
	private String email;
	
	@Title("手机｜电话")
	private String mobile;
	
	@Title("职业")
	private String vocation;
	
	@Title("学校")
	private String college;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public GENDER getGender() {
		return gender;
	}

	public void setGender(GENDER gender) {
		this.gender = gender;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public int getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}

	public int getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVocation() {
		return vocation;
	}

	public void setVocation(String vocation) {
		this.vocation = vocation;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

}
