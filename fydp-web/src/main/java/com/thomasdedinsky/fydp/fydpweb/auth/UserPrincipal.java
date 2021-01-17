package com.thomasdedinsky.fydp.fydpweb.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UserPrincipal implements UserDetails {
    private User user;
    public static final SimpleGrantedAuthority authorityUser = new SimpleGrantedAuthority("USER");
    public static final SimpleGrantedAuthority authorityManager = new SimpleGrantedAuthority("MANAGER");
    public static final SimpleGrantedAuthority authorityAdmin = new SimpleGrantedAuthority("ADMIN");
    public UserPrincipal(User user) {
        super();
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        if (this.user.isEnabled() && !this.user.isLocked()) {
            grantedAuthorities.add(authorityUser);
        }
        if (this.user.isManager()) {
            grantedAuthorities.add(authorityManager);
        }
        if (this.user.isAdmin()) {
            grantedAuthorities.add(authorityAdmin);
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.user.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user.isEnabled();
    }
}
