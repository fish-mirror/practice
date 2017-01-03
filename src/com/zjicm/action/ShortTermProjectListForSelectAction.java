package com.zjicm.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zjicm.entity.ShortTermReport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.entity.ShortTermProject;
import com.zjicm.service.IShortTermService;

public class ShortTermProjectListForSelectAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	private IShortTermService sts;
	private List<ShortTermProject> stps;
	private ShortTermReport selected;
	
	@Override
	public String execute() throws Exception {
		HttpSession session = request.getSession();
		String institute = (String) session.getAttribute("institute");
		String stuId = (String) session.getAttribute("ID");
		int year = new Date().getYear()-100;
		String term = "20"+(year-2)+"-20"+(year-1)+"学年第二学期";
		
		stps = sts.getProjectListByCol(institute, (short)1);

		selected = sts.getProjectSelected(stuId, term);
		return SUCCESS;
	}
	
	
	public List<ShortTermProject> getStps() {
		return stps;
	}


	public void setStps(List<ShortTermProject> stps) {
		this.stps = stps;
	}
	
	


	public ShortTermReport getSelected() {
		return selected;
	}


	public void setSelected(ShortTermReport selected) {
		this.selected = selected;
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
