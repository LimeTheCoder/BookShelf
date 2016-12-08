package com.limethecoder.data.service.impl;


import com.limethecoder.data.domain.User;
import com.limethecoder.data.repository.RoleRepository;
import com.limethecoder.data.repository.UserRepository;
import com.limethecoder.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@org.springframework.stereotype.Service
public class UserServiceImpl extends AbstractJPAService<User, String>
        implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User add(User user) {
        if(userRepository.exists(user.getLogin())) {
            return null;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Arrays.asList(roleRepository.findOne("USER")));
        }

        return userRepository.saveAndFlush(user);
    }

    @Override
    protected JpaRepository<User, String> getRepository() {
        return userRepository;
    }
}
