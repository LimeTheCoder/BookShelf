package com.limethecoder.data.service.impl;


import com.limethecoder.data.domain.User;
import com.limethecoder.data.repository.RoleRepository;
import com.limethecoder.data.repository.UserRepository;
import com.limethecoder.data.service.UserService;
import com.limethecoder.util.FileUtil;
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

        savePhoto(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Arrays.asList(roleRepository.findOne("USER")));
        }

        return userRepository.saveAndFlush(user);
    }

    @Override
    public User update(User user) {
        savePhoto(user);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(String login) {
        User user = userRepository.findOne(login);
        FileUtil.removeFileIfExists(user.getPhotoUrl());
        userRepository.delete(login);
    }

    @Override
    protected JpaRepository<User, String> getRepository() {
        return userRepository;
    }

    private void savePhoto(User user) {
        final String ICON_PREFIX = "_icon";
        if(user.getPhoto() != null && !user.getPhoto().isEmpty()) {
            String[] parts = user.getPhoto().getOriginalFilename()
                    .split("\\.");

            String fileExtension = parts[parts.length - 1];
            String filename = user.getLogin() + ICON_PREFIX +
                    "." + fileExtension;

            FileUtil.saveFile(user.getPhoto(), filename);
            user.setPhotoUrl(filename);
        }
    }
}
