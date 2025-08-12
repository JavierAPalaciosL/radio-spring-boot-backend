package io.radioweather.radioweatherapi.adapters.rest;

import io.radioweather.radioweatherapi.adapters.rest.dtos.TokenResponseDTO;
import io.radioweather.radioweatherapi.adapters.rest.dtos.UserInfoDTO;
import io.radioweather.radioweatherapi.adapters.rest.dtos.UserLoginDTO;
import io.radioweather.radioweatherapi.adapters.rest.dtos.UserRegisterDTO;
import io.radioweather.radioweatherapi.application.out.UserJPAPort;
import io.radioweather.radioweatherapi.application.usecases.UseCaseFavorites;
import io.radioweather.radioweatherapi.application.usecases.UseCasesUsers;
import io.radioweather.radioweatherapi.domain.Favorites;
import io.radioweather.radioweatherapi.infrastructure.utils.JWTEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("v1/users")
public class Users {

    private final RestTemplate restTemplate;
    private final UseCasesUsers useCasesUsers;
    private final UseCaseFavorites useCaseFavorites;

    @Value("${spring.google.oauth.client-id}")
    private String clientId;
    @Value("${spring.google.oauth.client-secret}")
    private String clientSecret;
    @Value("${spring.google.oauth.redirect-uri}")
    private String redirectUri;

    public Users(UseCasesUsers useCasesUsers, UseCaseFavorites useCaseFavorites) {
        this.restTemplate = new RestTemplate();
        this.useCasesUsers = useCasesUsers;
        this.useCaseFavorites = useCaseFavorites;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody io.radioweather.radioweatherapi.domain.Users users) {
        io.radioweather.radioweatherapi.domain.Users userFound = this.useCasesUsers.register(users);
        return ResponseEntity.ok(userFound);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        return ResponseEntity.ok(this.useCasesUsers.login(userLoginDTO.email(),  userLoginDTO.password()));
    }

    @GetMapping("/subject")
    public ResponseEntity<?> getSubject() {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(this.useCasesUsers.getUserByEmail(auth.getPrincipal().toString()));
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUser(@PathVariable("email") String email) {
        return ResponseEntity.ok(this.useCasesUsers.getUserByEmail(email));
    }

    @GetMapping("/{email}/favorites")
    public ResponseEntity<?> getFavorties(@PathVariable("email") String email) {

        return ResponseEntity.ok(this.useCasesUsers.getUserByEmail(email));
    }

    @PostMapping("/{email}/favorites")
    public ResponseEntity<?> addFavorite(@PathVariable("email") String email, @RequestBody Favorites favorites) {
        favorites.setEmailUser(email);
        return ResponseEntity.ok(this.useCaseFavorites.saveFavorite(favorites));
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

        System.out.println(profile.toString());

        io.radioweather.radioweatherapi.domain.Users userFound = this.useCasesUsers.register(new io.radioweather.radioweatherapi.domain.Users(profile.getEmail(), "", profile.getGiven_name(), profile.getFamily_name()));
        if (userFound == null) {
            userFound = this.useCasesUsers.findUserByEmail(profile.getEmail());
        }

        String frontendUrl = UriComponentsBuilder
                .fromHttpUrl("http://localhost:4200/auth/google/callback")
                .queryParam("token", JWTEngine.generateNewJWT(userFound.getEmail()))
                .build()
                .toUriString();

        return new RedirectView(frontendUrl);

    }



}
