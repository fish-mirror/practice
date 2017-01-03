package com.zjicm.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zjicm.service.IShortTermService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.entity.ShortTermProject;

public class ShortTermProjectListAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	private IShortTermService sts;
	private List<ShortTermProject> stps;
	
	@Override
	public String execute() throws Exception {
		Short status = null;
		String institute = (String) request.getSession().getAttribute("institute");
		String statusStr = request.getParameter("status");
		
		if(statusStr!=null && !statusStr.equals("")){
			 status = Short.parseShort(statusStr);
		}
		stps = sts.getProjectListByCol(institute, status);
		return SUCCESS;
	}
	
	
	public List<ShortTermProject> getStps() {
		return stps;
	}


	public void setStps(List<ShortTermProject> stps) {
		this.stps = stps;
	}


	@JSON(serialize=false)
	public IShortTermService getSts() {
		return sts;
	}
	@Resource(name="shortTermService")
	public void setSts(IShortTermService sts) {
		this.sts = sts;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
}
