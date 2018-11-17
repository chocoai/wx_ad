/*
 * 
 * 
 * 
 */
package com.cms.controller.front;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.cms.CommonAttribute;
import com.cms.entity.Agency;
import com.cms.util.DeviceUtils;
import com.cms.util.SystemUtils;
import com.jfinal.core.Controller;

/**
 * Controller - 基类
 * 
 * 
 * 
 */
public class BaseController extends Controller{
	
   /**
     * 获取当前会员
     * 
     * @return 当前会员
     */
    protected Agency getCurrentAgency() {
        Agency currentAgency = (Agency) getSession().getAttribute(Agency.SESSION_AGENCY);
        return currentAgency;
    }
    
	
	/**
	 * 获取主题
	 * 
	 * @return 主题
	 */
	public String getTheme(){
		return SystemUtils.getConfig().getTheme();
	}
	
	/**
	 * 获取设备
	 * @return
	 */
	public String getDevice(){
	    if(DeviceUtils.isMobile(getRequest())){
	        return "mobile";
	    }
	    return "front";
	}
	
	/**
	 * 获取BigDecimal数据
	 * 
	 * @param name
	 * 			名称
	 * @return BigDecimal数据
	 */
	public BigDecimal getParaToBigDecimal(String name){
		String value = getPara(name);
		if(StringUtils.isNotBlank(value)){
			return new BigDecimal(value);
		}
		return null;
	}

   /**
     * 判断是否是GET请求
     * 
     * @return 是否是GET请求
     */
    public Boolean isGet(){
        if(CommonAttribute.GET.equalsIgnoreCase(getRequest().getMethod())){
            return true;
        }
        return false;
    }
}