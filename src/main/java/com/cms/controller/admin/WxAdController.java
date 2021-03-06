package com.cms.controller.admin;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.cms.Feedback;
import com.cms.entity.AdIndustry;
import com.cms.entity.AdMaster;
import com.cms.entity.Agency;
import com.cms.entity.WxAd;
import com.cms.routes.RouteMapping;


/**
 * Controller - 会员
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/wx_ad")

public class WxAdController extends BaseController{

	/**
	 * 添加
	 */
	public void add(){
		setAttr("masterList",AdMaster.dao.findList());
		setAttr("adIndustryList",AdIndustry.dao.findList());
		setAttr("agencyList",Agency.dao.findList());
	    render(getView("wx_ad/add"));
	}
	
	/**
	 * 保存
	 */
	public void save(){
		WxAd wxAd = getModel(WxAd.class);
		
		Date startDate = wxAd.getStartTime();
		int days = wxAd.getDays();
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(startDate); 
		calendar.add(Calendar.DATE, days);
		wxAd.setOverTime(calendar.getTime());
		wxAd.save();
		redirect(getListQuery("/admin/wx_ad/list"));
	}
	
	
	/**
	 * 编辑
	 */
	public void edit(){
		Long id = getParaToLong("id");
		setAttr("wxAd", new WxAd().dao().findById(id));
		setAttr("masterList",AdMaster.dao.findList());
		setAttr("adIndustryList",AdIndustry.dao.findList());
		setAttr("agencyList",Agency.dao.findList());
		render(getView("wx_ad/edit"));
	}
	
	/**
	 * 更新
	 */
	public void update(){
		WxAd wxAd = getModel(WxAd.class);
		Date startDate = wxAd.getStartTime();
		int days = wxAd.getDays();
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(startDate); 
		calendar.add(Calendar.DATE, days);
		wxAd.setOverTime(calendar.getTime());
		wxAd.update();
		redirect(getListQuery("/admin/wx_ad/list"));
	}
	
	/**
	 * 列表
	 */
	public void list(){
	    String adName = getPara("adName");
	    String masterId = getPara("masterId");
	    String industryId = getPara("industryId");
	    String agencyId = getPara("agencyId");
	    String mobile = getPara("mobile");
	    Integer pageNumber = getParaToInt("pageNumber");
        if(pageNumber==null){
            pageNumber = 1;
        }
		setAttr("page", new WxAd().dao().findPage(adName, industryId, masterId, agencyId, mobile, pageNumber,PAGE_SIZE));
		setAttr("adName", adName);
		setAttr("masterId", masterId);
		setAttr("industryId", industryId);
		setAttr("agencyId", agencyId);
		setAttr("mobile", mobile);
		setAttr("masterList",AdMaster.dao.findList());
		setAttr("adIndustryList",AdIndustry.dao.findList());
		setAttr("agencyList",Agency.dao.findList());
		render(getView("wx_ad/list"));
	}
	
	/**
	 * 删除
	 */
	public void delete(){
		Long ids[] = getParaValuesToLong("ids");
		for(Long id:ids){
			new WxAd().dao().deleteById(id);
		}
		renderJson(Feedback.success(new HashMap<>()));
	}
}
