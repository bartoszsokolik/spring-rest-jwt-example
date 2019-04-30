package pl.solutions.software.sokolik.bartosz.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @GetMapping("/all")
    public String allUsersController() {
        return "Hello Spring";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminController() {
        return "Hello admin zone";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String userController() {
        return "Hello user zone";
    }

    @GetMapping("/tester")
    @PreAuthorize("hasRole('ROLE_TESTER')")
    public String testerController() {
        return "Hello tester zone";
    }
}
