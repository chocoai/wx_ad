package com.cms.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import com.cms.entity.Admin;
import com.cms.entity.Agency;

public class PermissionFilter implements Filter{
    
    /** 不包含 */
    private List<String> adminExcludes = new ArrayList<String>(){{
        add("/admin/login");
        add("/admin/error");
    }};
    
    /** 不包含 */
    private List<String> permissionExcludes = new ArrayList<String>(){{
        add("/admin/logout");
        add("/admin/common");
        add("/admin/file");
    }};
    
    /** 不包含 */
    private List<String> agencyExcludes = new ArrayList<String>(){{
        add("/register");
        add("/login");
        add("/password");
        add("/payment/alipayNotify");
        add("/payment/weixinNotify");
    }};
    
    /** 包含 */
    private List<String> agencyIncludes = new ArrayList<String>(){{
        add("/agency");
        add("/agency/");
        add("/order/");
        add("/receiver/");
        add("/payment/");
    }};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String url = request.getRequestURI().toString();
        String contextPath = request.getContextPath();
        url = url.substring(contextPath.length());
        if(StringUtils.isBlank(url) 
                || url.equals("/") 
                || url.startsWith("/upload") 
                || url.startsWith("/static") 
                || url.startsWith("/common")
                || StringUtils.isNotBlank(FilenameUtils.getExtension(url))
            ){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //匹配agency
        for(String key : agencyExcludes){
            if(url.startsWith(key)){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        for(String key : agencyIncludes){
            if(url.startsWith(key)){
                Agency currentAgency = (Agency) request.getSession().getAttribute(Agency.SESSION_AGENCY);
            	if(currentAgency!=null){
        	        filterChain.doFilter(servletRequest, servletResponse);
                    return;
            	}
        		response.sendRedirect(contextPath+"/login");
        		return;
               
            }
        }
        if(!url.startsWith("/admin")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //匹配admin
        for(String key : adminExcludes){
            if(url.startsWith(key)){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        Admin currentAdmin = (Admin) request.getSession().getAttribute(Admin.SESSION_ADMIN);
        if(currentAdmin!=null){
            for(String key : permissionExcludes){
                if(url.startsWith(key)){
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
            List<String> permissions = currentAdmin.getPermissions();
            for(String key : permissions){
                if(url.startsWith(key)){
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
            response.sendRedirect(contextPath+"/admin/error/unauthorized");
            return;
        }
        response.sendRedirect(contextPath+"/admin/login");
        return;
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        
    }

}
