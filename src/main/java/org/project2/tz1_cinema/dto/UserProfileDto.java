package org.project2.tz1_cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.project2.tz1_cinema.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

/**
 * Data Transfer Object for user profile implementing UserDetails for Spring Security.
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserProfileDto implements UserDetails {

    /**
     * Username (email) of the user.
     */
    private final String name;

    /**
     * Password of the user.
     */
    private final String password;

    /**
     * Authorities granted to the user.
     */
    private final List<GrantedAuthority> authorities;

    /**
     * Constructs a UserProfileDto from a Users entity.
     * @param user Users entity
     */
    public UserProfileDto(Users user) {
        this.name = user.getEmail();
        this.password = user.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}