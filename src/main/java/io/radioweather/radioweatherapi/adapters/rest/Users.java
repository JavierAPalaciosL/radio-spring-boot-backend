package io.radioweather.radioweatherapi.adapters.rest;

import io.radioweather.radioweatherapi.adapters.rest.dtos.TokenResponseDTO;
import io.radioweather.radioweatherapi.adapters.rest.dtos.UserInfoDTO;
import io.radioweather.radioweatherapi.adapters.rest.dtos.UserLoginDTO;
import io.radioweather.radioweatherapi.adapters.rest.dtos.UserRegisterDTO;
import io.radioweather.radioweatherapi.application.out.UserJPAPort;
import io.radioweather.radioweatherapi.infrastructure.utils.JWTEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("v1/users")
public class Users {

    private UserJPAPort userJPAPort;
    private final RestTemplate restTemplate;

    @Value("${spring.google.oauth.client-id}")
    private String clientId;
    @Value("${spring.google.oauth.client-secret}")
    private String clientSecret;
    @Value("${spring.google.oauth.redirect-uri}")
    private String redirectUri;

    public Users(UserJPAPort userJPAPort) {
        this.userJPAPort = userJPAPort;
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/register")
    public ResponseEntity<?> register(@RequestBody io.radioweather.radioweatherapi.domain.Users users) {
        io.radioweather.radioweatherapi.domain.Users userSave = this.userJPAPort.register(users);
        return ResponseEntity.ok(new UserRegisterDTO(userSave.getEmail(), userSave.getName(), userSave.getFirstName(), JWTEngine.generateNewJWT(userSave.getEmail())));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        io.radioweather.radioweatherapi.domain.Users userFound = this.userJPAPort.login(userLoginDTO.email(), userLoginDTO.password());

        if(userFound == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(new UserRegisterDTO(userLoginDTO.email(), userFound.getName(), userFound.getFirstName(), JWTEngine.generateNewJWT(userLoginDTO.email())));
    }

    @GetMapping("/oauth")
    public RedirectView getOauth(@RequestParam("code") String code) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        System.out.println("code received: " + code);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();

        form.add("code", code);
        form.add("client_id", this.clientId);
        form.add("client_secret", this.clientSecret);
        form.add("redirect_uri", this.redirectUri);
        form.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);

        ResponseEntity<TokenResponseDTO> response = restTemplate
                .postForEntity(
                        "https://oauth2.googleapis.com/token",
                        request,
                        TokenResponseDTO.class
                );

        TokenResponseDTO tokens = response.getBody();
        System.out.println(tokens.toString());

        /*RECALL*/
        HttpHeaders headersProfile = new HttpHeaders();
        headersProfile.setBearerAuth(tokens.accessToken());
        HttpEntity<Void> entity = new HttpEntity<>(headersProfile);

        ResponseEntity<UserInfoDTO> userInfoResp = restTemplate.exchange(
                "https://openidconnect.googleapis.com/v1/userinfo",
                HttpMethod.GET,
                entity,
                UserInfoDTO.class
        );

        UserInfoDTO profile = userInfoResp.getBody();
        System.out.println("Email: " + profile.getEmail());
        System.out.println("Picture: " + profile.getPicture());
        System.out.println("Name: " + profile.getName());

        System.out.println(profile.toString());

        String frontendUrl = UriComponentsBuilder
                .fromHttpUrl("http://localhost:4200")
                .queryParam("token", JWTEngine.generateNewJWT(profile.getEmail()))
                .build()
                .toUriString();

        return new RedirectView(frontendUrl);

    }



}
