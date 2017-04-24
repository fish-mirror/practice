package com.zjicm.auth.beans;

import com.zjicm.common.lang.consts.RegxConsts;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by yujing on 2017/3/8.
 */
public class LoginParam {

    @NotBlank(message = "学号或工号不能为空")
    @Length(max = 9, min = 8, message = "学号或教工号长度不符合")
    private String account;

    @NotBlank(message = "密码不能为空")
    @Length(max = 12, min = 6, message = "密码长度必须在 6 到 12 之间")
    @Pattern(regexp = RegxConsts.EXPRESSION_PWD, message = "密码字符不符合规则，请输入 数字/字母/符号 的组合")
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
