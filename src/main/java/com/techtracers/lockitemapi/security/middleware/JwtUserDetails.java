package com.techtracers.lockitemapi.security.middleware;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techtracers.lockitemapi.users.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    public static JwtUserDetails fromUser(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String name = user.getRole()
                .getName()
                .name();
        authorities.add(new SimpleGrantedAuthority(name));

        return new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword(), authorities);
    }
}
