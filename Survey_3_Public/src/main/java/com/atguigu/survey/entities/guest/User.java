package com.atguigu.survey.entities.guest;

import java.util.List;

import com.atguigu.survey.entities.manager.Role;

public class User {
    private Integer userId;

    private String userName;

    private String userPwd;

    private Boolean company;

    private String codeArr;

    private List<Role> roleList;
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public Boolean getCompany() {
        return company;
    }

    public void setCompany(Boolean company) {
        this.company = company;
    }

    public String getCodeArr() {
        return codeArr;
    }

    public void setCodeArr(String codeArr) {
        this.codeArr = codeArr == null ? null : codeArr.trim();
    }

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
}