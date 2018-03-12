package com.followlikecomment_sc.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;


@Transactional
@Service
public class SSUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public SSUserDetailsService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername (String username)
            throws UsernameNotFoundException {
        try{
            User user = userRepository.findByUsername(username);
            if(user==null) {
                return null;
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    getAuthorities(user));
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }


    private Set<GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> authorities
                = new HashSet<>();
        for(RoleClass roleClass : user.getRoles()) {
            GrantedAuthority grantedAuthority
                    = new SimpleGrantedAuthority(roleClass.getRole());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }

}