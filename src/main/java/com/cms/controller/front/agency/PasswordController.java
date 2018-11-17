package com.cms.controller.front.agency;

import org.apache.commons.codec.digest.DigestUtils;

import com.cms.Feedback;
import com.cms.controller.front.BaseController;
import com.cms.entity.Agency;
import com.cms.routes.RouteMapping;

@RouteMapping(url = "/agency/password")
public class PasswordController extends BaseController{

    /**
     * 编辑
     */
    public void edit(){
        render("/templates/"+getTheme()+"/"+getDevice()+"/agencyPasswordEdit.html");
    }
    
    /**
     * 修改
     */
    public void update(){
        String currentPassword = getPara("currentPassword");
        String password = getPara("password");
        Agency currentAgency = getCurrentAgency();
        if(!currentAgency.getPassword().equals(DigestUtils.md5Hex(currentPassword))){
            renderJson(Feedback.error("密码错误!"));
            return;
        }
        currentAgency.setPassword(DigestUtils.md5Hex(password));
        currentAgency.update();
        renderJson(Feedback.success(""));
    }
}
