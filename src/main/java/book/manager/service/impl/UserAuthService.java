package book.manager.service.impl;

import book.manager.entity.AuthUser;
import book.manager.mapper.UserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserAuthService implements UserDetailsService {

    @Resource
    UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthUser user = mapper.getPasswordByUsername(s);
        if (user == null){
            throw new UsernameNotFoundException("登陆失败，用户名或密码错误！");
        }
        return User
                .withUsername(user.getName())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
