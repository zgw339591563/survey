package com.atguigu.survey.entities.manager;

import java.util.List;

public class Auth {
    private Integer authId;

    private String authName;

    private List<Res> resList;
    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName == null ? null : authName.trim();
    }

	public List<Res> getResList() {
		return resList;
	}

	public void setResList(List<Res> resList) {
		this.resList = resList;
	}

	@Override
	public String toString() {
		return "Auth [authId=" + authId + ", authName=" + authName + ", resList=" + resList + "]";
	}
}