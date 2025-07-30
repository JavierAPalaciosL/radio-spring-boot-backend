package io.radioweather.radioweatherapi.adapters.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponseDTO(@JsonProperty("access_token") String accessToken,
                               @JsonProperty("expires_in") String expiresIn,
                               @JsonProperty("refresh_token") String refreshToken,
                               @JsonProperty("scope") String scope,
                               @JsonProperty("token_type") String tokenType,
                               @JsonProperty("id_token") String idToken) {
}
