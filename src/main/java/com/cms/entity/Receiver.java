package com.cms.entity;

import java.util.List;

import com.cms.entity.base.BaseReceiver;
import com.cms.util.DBUtils;

/**
 * Entity - 收货地址
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class Receiver extends BaseReceiver<Receiver> {
    
    /**
     * 地区
     */
    private Area area;
    
   /**
     * 查找收货地址
     * 
     * @param agencyId
     *            会员ID
     * @return 收货地址
     */
    public List<Receiver> findList(Long agencyId){
        String orderBySql = DBUtils.getOrderBySql("createDate desc");
        return find("select * from mzlt_receiver where agencyId=? "+orderBySql, agencyId);
    }
    
    /**
     * 查找默认收货地址
     * 
     * @param agencyId
     *            会员ID
     * @return 默认收货地址，若不存在则返回最新收货地址
     */
    public Receiver findDefault(Long agencyId) {
        Receiver defaultReceiver=findFirst("select * from mzlt_receiver where agencyId=? and isDefault=? ", agencyId,true);
        if(defaultReceiver==null){
            String orderBySql = DBUtils.getOrderBySql("createDate desc");
            defaultReceiver=findFirst("select * from mzlt_receiver where agencyId=? "+orderBySql, agencyId);
        }
        return defaultReceiver;
    }
    
    /**
     * 获取地区
     * 
     * @return 地区
     */
    public Area getArea(){
        if(area == null){
            area = new Area().dao().findById(getAreaId());
        }
        return area;
    }
}
