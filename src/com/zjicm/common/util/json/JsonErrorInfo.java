package com.zjicm.common.util.json;

/**
 * Created by yujing on 16-8-30.
 */
public class JsonErrorInfo {
    private String domain;          //唯一标识ID，可以表示域模型中出错的表单字段
    private String reason;          //系统原因
    private String message;         //可被人识别的原因，即提供给用户的错误信息提示
    private String location;        //位置
    private String locationType;    //位置类型
    private String extendedHelp;    //附件帮助链接
    private String sendReport;      //报告错误链接

    public JsonErrorInfo(String domain,
                         String reason,
                         String message,
                         String location,
                         String locationType,
                         String extendedHelp, String sendReport) {
        this.domain = domain;
        this.reason = reason;
        this.message = message;
        this.location = location;
        this.locationType = locationType;
        this.extendedHelp = extendedHelp;
        this.sendReport = sendReport;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getExtendedHelp() {
        return extendedHelp;
    }

    public void setExtendedHelp(String extendedHelp) {
        this.extendedHelp = extendedHelp;
    }

    public String getSendReport() {
        return sendReport;
    }

    public void setSendReport(String sendReport) {
        this.sendReport = sendReport;
    }

}
