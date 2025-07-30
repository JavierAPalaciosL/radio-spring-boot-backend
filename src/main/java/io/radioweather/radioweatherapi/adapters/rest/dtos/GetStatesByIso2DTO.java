package io.radioweather.radioweatherapi.adapters.rest.dtos;

import io.radioweather.radioweatherapi.domain.States;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetStatesByIso2DTO {

    private List<States> state;
    private long size;

}
