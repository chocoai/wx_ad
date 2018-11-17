package com.cms.controller.admin;

import java.util.Date;
import java.util.HashMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.cms.Feedback;
import com.cms.entity.AdIndustry;
import com.cms.entity.AdMaster;
import com.cms.routes.RouteMapping;


/**
 * Controller - 广告主管理
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/adMaster")

public class AdMasterController extends BaseController{

	/**
	 * 添加
	 */
	public void add(){
		setAttr("adIndustryList", new AdIndustry().dao().findList());
	    render(getView("adMaster/add"));
	}
	
	/**
	 * 保存
	 */
	public void save(){
		AdMaster agency = getModel(AdMaster.class,"",true);
		agency.setPassword(DigestUtils.md5Hex(agency.getPassword()));
		agency.setCreateDate(new Date());
		agency.save();
		redirect(getListQuery("/admin/adMaster/list"));
	}
	
	
	/**
	 * 编辑
	 */
	public void edit(){
		Long id = getParaToLong("id");
		setAttr("adIndustryList", new AdIndustry().dao().findList());
		setAttr("adMaster", new AdMaster().dao().findById(id));
		render(getView("adMaster/edit"));
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
	    Long industryId = getParaToLong("industryId");
	    AdMaster adMaster = new AdMaster().dao().findById(id);
	    if(StringUtils.isNotBlank(password)){
	        adMaster.setPassword(DigestUtils.md5Hex(password));
	    }
	    
	    if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(areaId.toString())
	    		&& StringUtils.isNotBlank(address)) {
	    	adMaster.setUsername(username);
	    	adMaster.setMobile(mobile);
	    	adMaster.setAreaId(areaId);
	    	adMaster.setAddress(address);
	    	adMaster.setIndustryId(industryId);
	    }
	    adMaster.update();
		redirect(getListQuery("/admin/adMaster/list"));
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
		setAttr("page", new AdMaster().dao().findPage(mobile,pageNumber,PAGE_SIZE));
		setAttr("mobile", mobile);
		render(getView("adMaster/list"));
	}
	
	/**
	 * 删除
	 */
	public void delete(){
		Long ids[] = getParaValuesToLong("ids");
		for(Long id:ids){
			new AdMaster().dao().deleteById(id);
		}
		renderJson(Feedback.success(new HashMap<>()));
	}
}
