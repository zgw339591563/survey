package com.atguigu.survey.entities.manager;

import java.util.List;

public class Admin {
    private Integer adminId;

    private String adminName;

    private String adminPwd;

    private String codeArr;
    
    private List<Role> roles;
    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd == null ? null : adminPwd.trim();
    }

    public String getCodeArr() {
        return codeArr;
    }

    public void setCodeArr(String codeArr) {
        this.codeArr = codeArr == null ? null : codeArr.trim();
    }

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminPwd=" + adminPwd + ", codeArr="
				+ codeArr + ", roles=" + roles + "]";
	}
}