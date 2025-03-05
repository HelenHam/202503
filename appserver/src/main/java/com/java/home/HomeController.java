package com.java.home;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.Map;

@CrossOrigin(origins = "*")
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/signIn")
    public String signIn1(@ModelAttribute HomeReqDTO homeReqDTO, HttpServletResponse response, HttpSession session) {
        String status = "ok";
        try {
            Map<String, String> resultMap = getToken(homeReqDTO);
            String access_token = resultMap.get("access_token");

            Cookie cookie = new Cookie("access_token", access_token);
            cookie.setHttpOnly(true); // JavaScript에서 접근 불가
            cookie.setSecure(true); // HTTPS에서만 전송
            cookie.setPath("/");
            cookie.setMaxAge(session.getMaxInactiveInterval());

            response.addCookie(cookie);
        } catch (Exception e) {
            status = "no";
        }
        return "redirect:/?" + status;
    }

    @ResponseBody
    @PostMapping("/signIn2")
    public boolean signIn2(@RequestBody HomeReqDTO homeReqDTO, HttpServletResponse response, HttpSession session) {
        boolean status = true;
        try {
            Map<String, String> resultMap = getToken(homeReqDTO);
            String access_token = resultMap.get("access_token");

            Cookie cookie = new Cookie("access_token", access_token);
            cookie.setHttpOnly(true); // JavaScript에서 접근 불가
            cookie.setSecure(true); // HTTPS에서만 전송
            cookie.setPath("/");
            cookie.setMaxAge(session.getMaxInactiveInterval());

            response.addCookie(cookie);
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    @ResponseBody
    @PostMapping("/signUp")
    public boolean signUp(@RequestBody HomeReqDTO homeReqDTO) {
        boolean status = true;
        try {
            System.out.println("-------------------------------" + homeReqDTO);
            Map<String, String> resultMap = signUpReq(homeReqDTO);
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    private Map<String, String> signUpReq(HomeReqDTO homeReqDTO) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", homeReqDTO.getId());
        formData.add("client_secret", homeReqDTO.getPwd());
        formData.add("redirectUri", "http://localhost:8000");
        formData.add("postLogoutRedirectUri", "http://localhost:8000");
        formData.add("requireAuthorizationConsent", "1");
        return RestClient.create().post()
                .uri("http://d.0neteam.co.kr/addClient")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body(formData)
                .retrieve()
                .toEntity(Map.class)
                .getBody();
    }

    private Map<String, String> getToken(HomeReqDTO homeReqDTO) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type","client_credentials");
        formData.add("client_id",homeReqDTO.getId());
        formData.add("client_secret",homeReqDTO.getPwd());
        return RestClient.create().post()
                .uri("http://d.0neteam.co.kr/oauth2/token")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body(formData)
                .retrieve()
                .toEntity(Map.class)
                .getBody();
    }

}

class HomeReqDTO {

    private String id;
    private String pwd;
    private String redirectUri;
    private String postLogoutRedirectUri;
    private String requireAuthorizationConsent;

    public void setId(String id) {
        this.id = id;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
    public void setPostLogoutRedirectUri(String postLogoutRedirectUri) { this.postLogoutRedirectUri = postLogoutRedirectUri; }
    public void setRequireAuthorizationConsent(String requireAuthorizationConsent) {
        this.requireAuthorizationConsent = requireAuthorizationConsent;
    }
    public String getId() {
        return this.id;
    }
    public String getPwd() {
        return this.pwd;
    }
    public String getRedirectUri() {
        return this.redirectUri;
    };
    public String getPostLogoutRedirectUri() { return this.postLogoutRedirectUri; };
    public String getRequireAuthorizationConsent() {
        return this.requireAuthorizationConsent;
    };
    public String toString() {
        return "[id:" + this.id + ", pwd:" + this.pwd + "]";
    }

}