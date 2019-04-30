package pl.solutions.software.sokolik.bartosz.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.solutions.software.sokolik.bartosz.model.User;
import pl.solutions.software.sokolik.bartosz.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(this::userOf)
                .orElseThrow(() -> new RuntimeException("User with username " + " not found"));
    }

    private UserDetails userOf(User user) {
        List<GrantedAuthority> roles = Collections.singletonList(user.getRole());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }
}
