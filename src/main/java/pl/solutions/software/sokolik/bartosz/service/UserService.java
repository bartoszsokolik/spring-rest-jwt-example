package pl.solutions.software.sokolik.bartosz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.solutions.software.sokolik.bartosz.dto.LoginDto;
import pl.solutions.software.sokolik.bartosz.dto.RegisterDto;
import pl.solutions.software.sokolik.bartosz.dto.TokenDto;
import pl.solutions.software.sokolik.bartosz.model.User;
import pl.solutions.software.sokolik.bartosz.repository.UserRepository;
import pl.solutions.software.sokolik.bartosz.security.JwtTokenService;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, JwtTokenService jwtTokenService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(RegisterDto registerDto) {
        User user = new User(registerDto.getUsername(), passwordEncoder.encode(registerDto.getPassword()), registerDto.getEmail(), registerDto.getRole());
        userRepository.save(user);
    }

    public TokenDto login(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsername())
                .filter(u -> passwordEncoder.matches(loginDto.getPassword(), u.getPassword()))
                .orElseThrow(() -> new RuntimeException("Wrong password"));

        return new TokenDto(jwtTokenService.generateToken(user.getUsername()));
    }
}
