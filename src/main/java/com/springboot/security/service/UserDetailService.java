package com.springboot.security.service;

import com.springboot.project.user.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userDetailsService")
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.springboot.project.user.model.bo.User> user = this.userDao.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("用戶不存在");
        }
        com.springboot.project.user.model.bo.User userData = user.get();
        List<GrantedAuthority> auths =
                AuthorityUtils.commaSeparatedStringToAuthorityList("manager, ROLE_sale, ROLE_secure1");
        return new User(userData.getUsername(),
                new BCryptPasswordEncoder().encode(userData.getPassword()), auths);
    }
}
