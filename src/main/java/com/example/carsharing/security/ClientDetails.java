package com.example.carsharing.security;

import com.example.carsharing.models.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class ClientDetails implements UserDetails {
    private Client client;

    public ClientDetails(Client client) {
        this.client = client;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(client.getRole()));
    }

    @Override
    public String getPassword() {
        return client.getPassword();
    }

    @Override
    public String getUsername() {
        return client.getName();
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

    public Client getClient(){
        return client;
    }
}
