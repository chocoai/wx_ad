package com.cms.controller.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cms.Feedback;
import com.cms.entity.AdIndustry;
import com.cms.entity.Area;
import com.cms.routes.RouteMapping;


/**
 * Controller - 行业
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/adIndustry")

public class AdIndustryController extends BaseController{

	/**
	 * 添加
	 */
	public void add(){
	    render(getView("adIndustry/add"));
	}
	
	/**
	 * 保存
	 */
	public void save(){
		AdIndustry adIndustry = getModel(AdIndustry.class,"",true);
		adIndustry.setCreateDate(new Date());
		adIndustry.save();
		redirect(getListQuery("/admin/adIndustry/list"));
	}
	
	
	/**
	 * 编辑
	 */
	public void edit(){
		Long id = getParaToLong("id");
		setAttr("adIndustry", new AdIndustry().dao().findById(id));
		render(getView("adIndustry/edit"));
	}
	
	/**
	 * 更新
	 */
	public void update(){
	    Long id = getParaToLong("id");
	    String name = getPara("name");
	    AdIndustry adIndustry = new AdIndustry().dao().findById(id);
	    if(StringUtils.isNotBlank(name)){
	        adIndustry.setName(name);
	        adIndustry.update();
	    }
		redirect(getListQuery("/admin/adIndustry/list"));
	}
	
	/**
	 * 列表
	 */
	public void list(){
	    String name = getPara("name");
	    Integer pageNumber = getParaToInt("pageNumber");
        if(pageNumber==null){
            pageNumber = 1;
        }
		setAttr("page", new AdIndustry().dao().findPage(name,pageNumber,PAGE_SIZE));
		setAttr("name", name);
		render(getView("adIndustry/list"));
	}
	
	/**
	 * 删除
	 */
	public void delete(){
		Long ids[] = getParaValuesToLong("ids");
		for(Long id:ids){
			new AdIndustry().dao().deleteById(id);
		}
		renderJson(Feedback.success(new HashMap<>()));
	}
	
	/**
     * 行业下拉框
     */
    public void index() {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        List<AdIndustry> adIndustryList = new AdIndustry().dao().findList();
        for (AdIndustry adIndustry : adIndustryList) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("name", adIndustry.getName());
            item.put("value", adIndustry.getId());
            data.add(item);
        }
        renderJson(data);
    }
}
