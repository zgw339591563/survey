package com.atguigu.survey.e;

public class UserNameAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNameAlreadyExistException(String message) {
		super(message);
	}

}
