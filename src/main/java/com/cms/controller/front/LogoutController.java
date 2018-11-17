package com.cms.controller.front;

import com.cms.entity.Agency;
import com.cms.routes.RouteMapping;

/**
 * Controller - 注销
 * 
 * 
 * 
 */
@RouteMapping(url = "/logout")
public class LogoutController extends BaseController{

	/**
	 * 注销
	 */
	public void index(){
		getSession().removeAttribute(Agency.SESSION_AGENCY);
		redirect("/");
	}
}
