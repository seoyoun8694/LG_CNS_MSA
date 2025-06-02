package com.welab.api_gateway.security.jwt.authentication;


import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class JwtAuthentication extends AbstractAuthenticationToken {
    private final String token;
    private final UserPrincipal principal;
    public JwtAuthentication(UserPrincipal principal, String token, Collection<? extends GrantedAuthority> authorities) {super(authorities);
        this.principal = principal;
        this.token = token;
        this.setDetails(principal);
        setAuthenticated(true);
    }
    @Override
    public boolean isAuthenticated() {
        return true;
    }
    @Override
    public String getCredentials() {
        return token;
    }
    @Override
    public UserPrincipal getPrincipal() {
        return principal;
    }
}