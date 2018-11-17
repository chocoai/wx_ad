package com.cms.controller.front.agency;

import com.cms.controller.front.BaseController;
import com.cms.routes.RouteMapping;

/**
 * Controller - 首页
 * 
 * 
 * 
 */
@RouteMapping(url = "/agency")
public class IndexController extends BaseController{
	
	
	public void index(){
	    render("/templates/"+getTheme()+"/"+getDevice()+"/agency.html");
	}

}
