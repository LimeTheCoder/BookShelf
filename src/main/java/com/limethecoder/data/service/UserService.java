package com.limethecoder.data.service;


import com.limethecoder.data.domain.User;

public interface UserService extends Service<User, String> {
    byte[] loadImage(User user);
}
