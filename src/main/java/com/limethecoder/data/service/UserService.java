package com.limethecoder.data.service;


import com.limethecoder.data.domain.User;
import com.limethecoder.data.dto.UserDto;

public interface UserService extends Service<User, String> {
    User registerNewUser(UserDto userDto);
}
