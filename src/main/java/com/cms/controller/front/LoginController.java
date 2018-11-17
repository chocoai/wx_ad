package com.cms.controller.front;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.cms.Feedback;
import com.cms.entity.Agency;
import com.cms.routes.RouteMapping;

/**
 * Controller - 登录
 * 
 * 
 * 
 */
@RouteMapping(url = "/login")
public class LoginController extends BaseController{

	/**
	 * 登录页面
	 */
	public void index(){
	    String mobile = getPara("mobile");
        String password = getPara("password");
        if(StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(password)){
            Agency agency = new Agency().dao().findByMobile(mobile);
            if(agency == null){
                setAttr("feedback", Feedback.error("用户不存在"));
            }else if(!DigestUtils.md5Hex(password).equals(agency.getPassword())){
                setAttr("feedback", Feedback.error("用户名密码错误"));
            }else{
                getSession().setAttribute(Agency.SESSION_AGENCY, agency);
                redirect("/");
                return;
            }
        }
		render("/templates/"+getTheme()+"/"+getDevice()+"/login.html");
	}
}
