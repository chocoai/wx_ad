package com.cms.entity.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseAgency<M extends BaseAgency<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Long id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public M setCreateDate(java.util.Date createDate) {
		set("createDate", createDate);
		return (M)this;
	}
	
	public java.util.Date getCreateDate() {
		return get("createDate");
	}

	public M setModifyDate(java.util.Date modifyDate) {
		set("modifyDate", modifyDate);
		return (M)this;
	}
	
	public java.util.Date getModifyDate() {
		return get("modifyDate");
	}

	public M setAddress(java.lang.String address) {
		set("address", address);
		return (M)this;
	}
	
	public java.lang.String getAddress() {
		return getStr("address");
	}

	public M setPassword(java.lang.String password) {
		set("password", password);
		return (M)this;
	}
	
	public java.lang.String getPassword() {
		return getStr("password");
	}

	public M setLoginDate(java.util.Date loginDate) {
		set("loginDate", loginDate);
		return (M)this;
	}
	
	public java.util.Date getLoginDate() {
		return get("loginDate");
	}

	public M setLoginIp(java.lang.String loginIp) {
		set("loginIp", loginIp);
		return (M)this;
	}
	
	public java.lang.String getLoginIp() {
		return getStr("loginIp");
	}

	public M setUsername(java.lang.String username) {
		set("username", username);
		return (M)this;
	}
	
	public java.lang.String getUsername() {
		return getStr("username");
	}

	public M setBirth(java.util.Date birth) {
		set("birth", birth);
		return (M)this;
	}
	
	public java.util.Date getBirth() {
		return get("birth");
	}

	public M setMobile(java.lang.String mobile) {
		set("mobile", mobile);
		return (M)this;
	}
	
	public java.lang.String getMobile() {
		return getStr("mobile");
	}

	public M setWeixinOpenId(java.lang.String weixinOpenId) {
		set("weixinOpenId", weixinOpenId);
		return (M)this;
	}
	
	public java.lang.String getWeixinOpenId() {
		return getStr("weixinOpenId");
	}

	public M setAreaId(java.lang.Long areaId) {
		set("areaId", areaId);
		return (M)this;
	}
	
	public java.lang.Long getAreaId() {
		return getLong("areaId");
	}
	
}
