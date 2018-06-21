package com.UserTask.mvc.services.serivceInterfaces;

import com.UserTask.mvc.Entities.User;

public interface UserService {
      User postData(User user);
      User GetData(String email,String password);
      User getUserById(int id);
}
