package pl.solutions.software.sokolik.bartosz.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_TESTER;

    @Override
    public String getAuthority() {
        return name();
    }
}
