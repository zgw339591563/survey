package com.atguigu.survey.component.service.i;

import com.atguigu.survey.entities.guest.User;

public interface UserService {

	void regist(User user);

	User login(User user);

}
