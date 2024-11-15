package com.techtracers.lockitemapi.security.middleware;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techtracers.lockitemapi.security.domain.enums.Roles;
import com.techtracers.lockitemapi.shared.exception.UnauthorizedException;
import com.techtracers.lockitemapi.users.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class JwtUserDetails implements UserDetails {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private List<? extends GrantedAuthority> authorities;

    public boolean hasRole(Roles role) {
        return authorities.stream()
                .anyMatch(authority -> authority.getAuthority().equals(role.name()));
    }

    public static JwtUserDetails fromUser(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String name = user.getRole()
                .getName()
                .name();
        authorities.add(new SimpleGrantedAuthority(name));

        return new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword(), authorities);
    }

    public static JwtUserDetails getFromSecurityContext(boolean strict) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null && strict)
            throw new UnauthorizedException("No authorized request.");

        return authentication != null ? (JwtUserDetails) authentication.getPrincipal() : null;
    }

    public static JwtUserDetails getFromSecurityContext() {
        return getFromSecurityContext(true);
    }

}
