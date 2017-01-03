package com.zjicm.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zjicm.entity.User;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.entity.ShortTermProject;
import com.zjicm.service.IShortTermService;

public class ShortTermProjectUploadAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private IShortTermService sts;
	private String docUrl;
	private String result;
	private Integer pid;
	//上传文件的参数格式
	private File doc;
	private String docFileName;
	private String docContentType;
	
	@Override
	public String execute() throws Exception {
		try{
			
			ShortTermProject stp = null;
			User u = (User)(request.getSession().getAttribute("user"));
			String tmpPid = request.getParameter("pid");
			String institute = (String) request.getSession().getAttribute("institute");
			
			if(u == null || doc == null){
				result = "error";
				return ERROR;
			}
			String id = u.getId();
			//创建输入流
	        InputStream is = new FileInputStream(doc);
	        //创建父目录
	        String root = ServletActionContext.getServletContext().getRealPath("/college/short_term_info/"+institute);
	        if (!new File(root).exists()) { // 如果路径不存在，创建  
	        	new File(root).mkdirs();
	        }  
			//如果不是第一次上传文件则把之前的文件删掉
			if(tmpPid!=null && tmpPid!=""&& !tmpPid.equals("1")){
				pid = Integer.parseInt(tmpPid);
				stp = sts.getProject(pid);
				docUrl = stp.getFileUrl();
				if(docUrl!=null){
					String tmpUrl = docUrl.substring(docUrl.lastIndexOf('/'));
					File tmp = new File(root+tmpUrl);
					if(tmp.exists()){
						tmp.delete();
					}
				}
				
			}
			
	        
	        //创建输出流以及目的文件
	        String suffix = docFileName.substring(docFileName.lastIndexOf('.'));
	        String saveFileName = new Date().getTime()+suffix;
	        OutputStream os = new FileOutputStream(new File(root,saveFileName));
	        byte[] buffer = new byte[1024];
	        int length = 0;
	        
	        while(-1 != (length = is.read(buffer)))
	        {
	            os.write(buffer,0,length);
	        }
	        
	        os.close();
	        is.close();
	        docUrl = "/practice/college/short_term_info/"+institute+"/"+saveFileName;
	        if(stp!=null){
	        	stp.setFileUrl(docUrl);
	        	sts.updateProject(stp);
	        }else{
	        	stp = new ShortTermProject();
	        	stp.setFileUrl(docUrl);
	        	pid = sts.addProject(stp);
	        }
	        
	        return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			result = "error";
			return ERROR;
		}

		
	}
	
	
	
	public Integer getPid() {
		return pid;
	}




	public String getDocUrl() {
		return docUrl;
	}



	@JSON(serialize=false)
	public IShortTermService getSts() {
		return sts;
	}
	@Resource(name="shortTermService")
	public void setSts(IShortTermService sts) {
		this.sts = sts;
	}
	
	@JSON(serialize=false)
	public File getDoc() {
		return doc;
	}

	public void setDoc(File doc) {
		this.doc = doc;
	}
	@JSON(serialize=false)
	public String getDocFileName() {
		return docFileName;
	}

	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}
	@JSON(serialize=false)
	public String getDocContentType() {
		return docContentType;
	}

	public void setDocContentType(String docContentType) {
		this.docContentType = docContentType;
	}

	public void setResult(String result) {
		this.result = result;
	}
	public String getResult() {
		return result;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
