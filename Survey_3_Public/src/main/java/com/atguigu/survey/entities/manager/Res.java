package com.atguigu.survey.entities.manager;

public class Res {
    private Integer resId;

    private String servletPath;

    private Integer resCode;

    private Integer resPos;

    private Boolean publicStatus;

    
    public Res(Integer resId, String servletPath, Integer resCode, Integer resPos, Boolean publicStatus) {
		super();
		this.resId = resId;
		this.servletPath = servletPath;
		this.resCode = resCode;
		this.resPos = resPos;
		this.publicStatus = publicStatus;
	}

	public Res() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getServletPath() {
        return servletPath;
    }

    public void setServletPath(String servletPath) {
        this.servletPath = servletPath == null ? null : servletPath.trim();
    }

    public Integer getResCode() {
        return resCode;
    }

    public void setResCode(Integer resCode) {
        this.resCode = resCode;
    }

    public Integer getResPos() {
        return resPos;
    }

    public void setResPos(Integer resPos) {
        this.resPos = resPos;
    }

    public Boolean getPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(Boolean publicStatus) {
        this.publicStatus = publicStatus;
    }

	@Override
	public String toString() {
		return "Res [resId=" + resId + ", servletPath=" + servletPath + ", resCode=" + resCode + ", resPos=" + resPos
				+ ", publicStatus=" + publicStatus + "]";
	}
}