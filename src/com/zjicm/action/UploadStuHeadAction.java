package com.zjicm.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.dto.Page;
import com.zjicm.entity.User;
import com.zjicm.service.impl.StudentInfoService;

public class UploadStuHeadAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private StudentInfoService sis;
	private String img_url;
	private String result;
	//上传文件的参数格式
	private File img;
	private String imgFileName;
	private String imgContentType;
	
	@Override
	public String execute() throws Exception {
		User u = (User)(request.getSession().getAttribute("user"));
		if(u==null||img==null){
			result = "error";
			return ERROR;
		}
		String id = u.getId();
		//创建输入流
        InputStream is = new FileInputStream(img);
        //创建父目录
        String root = ServletActionContext.getServletContext().getRealPath("/college/stu_info/head_img");
        if (!new File(root).exists()) { // 如果路径不存在，创建  
        	new File(root).mkdirs();
        }  
        
        //创建输出流以及目的文件
        String suffix = imgFileName.substring(imgFileName.lastIndexOf('.'));
        String saveFileName = id+suffix;
        OutputStream os = new FileOutputStream(new File(root,saveFileName));
        byte[] buffer = new byte[1024];
        int length = 0;
        
        while(-1 != (length = is.read(buffer)))
        {
            os.write(buffer,0,length);
        }
        
        os.close();
        is.close();
        img_url = "/practice/college/stu_info/head_img/"+saveFileName;
        if(sis.updateStuImg(id, img_url)){
        	result = img_url;
        	return SUCCESS;
        }else{
        	result = "error";
        	return ERROR;
        }
        
		
		
	}
	
	@JSON(serialize=false)
	public StudentInfoService getSis() {
		return sis;
	}
	@Resource(name="studentInfoService")
	public void setSis(StudentInfoService sis) {
		this.sis = sis;
	}
	@JSON(serialize=false)
	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}
	@JSON(serialize=false)
	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
	@JSON(serialize=false)
	public String getImgContentType() {
		return imgContentType;
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}
	
	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
