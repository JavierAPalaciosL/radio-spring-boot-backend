package io.radioweather.radioweatherapi.adapters.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDTO {

    private @JsonProperty("access_token") String accessToken;
    private @JsonProperty("expires_in") String expiresIn;
    private@JsonProperty("refresh_token") String refreshToken;
    private @JsonProperty("scope") String scope;
    private @JsonProperty("token_type") String tokenType;
    private @JsonProperty("id_token") String idToken;


}
