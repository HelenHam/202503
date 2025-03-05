package com.java.client;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final OAuthClientRepository oAuthClientRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public boolean save(OAuthClient oAuthClient) {
        oAuthClient.setClientSecret( passwordEncoder.encode(oAuthClient.getClientSecret()) ); // 암호화 처리
        return oAuthClientRepository.save(oAuthClient) == null ? false : true;
    }

}
