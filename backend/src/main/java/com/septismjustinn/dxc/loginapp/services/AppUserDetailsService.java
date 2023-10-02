package com.septismjustinn.dxc.loginapp.services;

import com.septismjustinn.dxc.loginapp.data.UserRepository;
import com.septismjustinn.dxc.loginapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> target = userRepo.findByUsername(username);
        if (target.isEmpty()) {
            throw new UsernameNotFoundException("No such user");
        } else {
            return target.get();
        }
    }
}
