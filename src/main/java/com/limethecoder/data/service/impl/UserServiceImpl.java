package com.limethecoder.data.service.impl;


import com.limethecoder.data.domain.User;
import com.limethecoder.data.repository.UserRepository;
import com.limethecoder.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Service
public class UserServiceImpl extends AbstractJPAService<User, String>
        implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected JpaRepository<User, String> getRepository() {
        return userRepository;
    }
}
