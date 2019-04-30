package pl.solutions.software.sokolik.bartosz.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.solutions.software.sokolik.bartosz.dto.LoginDto;
import pl.solutions.software.sokolik.bartosz.dto.RegisterDto;
import pl.solutions.software.sokolik.bartosz.dto.TokenDto;
import pl.solutions.software.sokolik.bartosz.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterDto registerDto) {
        userService.saveUser(registerDto);
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }
}
