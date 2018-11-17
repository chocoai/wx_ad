package com.cms.controller.admin;

import java.util.Date;
import java.util.HashMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.cms.Feedback;
import com.cms.entity.Agency;
import com.cms.routes.RouteMapping;


/**
 * Controller - 会员
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/agency")

public class AgencyController extends BaseController{

	/**
	 * 添加
	 */
	public void add(){
	    render(getView("agency/add"));
	}
	
	/**
	 * 保存
	 */
	public void save(){
		Agency agency = getModel(Agency.class,"",true);
		agency.setPassword(DigestUtils.md5Hex(agency.getPassword()));
		agency.setCreateDate(new Date());
		agency.setModifyDate(new Date());
		agency.save();
		redirect(getListQuery("/admin/agency/list"));
	}
	
	
	/**
	 * 编辑
	 */
	public void edit(){
		Long id = getParaToLong("id");
		setAttr("agency", new Agency().dao().findById(id));
		render(getView("agency/edit"));
	}
	
	/**
	 * 更新
	 */
	public void update(){
	    Long id = getParaToLong("id");
	    String password = getPara("password");
	    String username = getPara("username");
	    String mobile = getPara("mobile");
	    Long areaId = getParaToLong("areaId");
	    String address = getPara("address");
	    Agency agency = new Agency().dao().findById(id);
	    if(StringUtils.isNotBlank(password)){
	        agency.setPassword(DigestUtils.md5Hex(password));
	    }
	    
	    if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(areaId.toString()) && StringUtils.isNotBlank(address)) {
	    	agency.setUsername(username);
	    	agency.setMobile(mobile);
	    	agency.setAreaId(areaId);
	    	agency.setAddress(address);
	    }
	    agency.update();
		redirect(getListQuery("/admin/agency/list"));
	}
	
	/**
	 * 列表
	 */
	public void list(){
	    String mobile = getPara("mobile");
	    Integer pageNumber = getParaToInt("pageNumber");
        if(pageNumber==null){
            pageNumber = 1;
        }
		setAttr("page", new Agency().dao().findPage(mobile,pageNumber,PAGE_SIZE));
		setAttr("mobile", mobile);
		render(getView("agency/list"));
	}
	
	/**
	 * 删除
	 */
	public void delete(){
		Long ids[] = getParaValuesToLong("ids");
		for(Long id:ids){
			new Agency().dao().deleteById(id);
		}
		renderJson(Feedback.success(new HashMap<>()));
	}
}
