package com.java.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuthClientController {

    private final OAuthService oAuthService;

    @PostMapping("/addClient")
    public boolean save(@ModelAttribute OAuthClient oAuthClient) {
        return oAuthService.save(oAuthClient);
    }

}
