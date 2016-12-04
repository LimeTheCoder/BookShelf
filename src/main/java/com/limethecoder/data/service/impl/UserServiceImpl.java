package com.limethecoder.data.service.impl;


import com.limethecoder.data.domain.User;
import com.limethecoder.data.dto.UserDto;
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
    public User add(User obj) {
        if(userRepository.exists(obj.getLogin())) {
            return null;
        }

        obj.setPassword(passwordEncoder.encode(obj.getPassword()));

        return userRepository.saveAndFlush(obj);
    }

    @Override
    protected JpaRepository<User, String> getRepository() {
        return userRepository;
    }

    @Override
    public User registerNewUser(UserDto userDto) {
        if(userRepository.exists(userDto.getLogin())) {
            return null;
        }

        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setCity(userDto.getCity());
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        if(userDto.getRoles() == null || userDto.getRoles().isEmpty()) {
            user.setRoles(Arrays.asList(roleRepository.findOne("USER")));
        } else {
            user.setRoles(userDto.getRoles());
        }

        if(userDto.getPhotoUrl() != null) {
            user.setPhotoUrl(userDto.getPhotoUrl());
        }

        return userRepository.saveAndFlush(user);
    }
}
