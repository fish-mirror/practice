package com.zjicm.cooperation.beans;

import com.zjicm.common.lang.consts.RegxConsts;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 创建企业账号参数
 * <p>
 * Created by yujing on 2017/4/23.
 */
public class CompanyRegisterParams {
    @NotBlank(message = "账号不能为空")
    @Length(min = 6, max = 9, message = "输入长度在 6 - 9 位")
    private String number;
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 12, message = "输入长度在 6 - 12 位")
    @Pattern(regexp = RegxConsts.EXPRESSION_PWD, message = "密码字符不符合规则，请输入 数字/字母/符号 的组合")
    private String password;
    @NotBlank
    @Length(min = 1, max = 20, message = "输入长度在 1 - 20 位")
    private String name;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
