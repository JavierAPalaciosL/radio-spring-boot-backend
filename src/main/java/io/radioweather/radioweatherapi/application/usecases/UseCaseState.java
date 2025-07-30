package io.radioweather.radioweatherapi.application.usecases;

import io.radioweather.radioweatherapi.application.in.GetMapImageState;
import io.radioweather.radioweatherapi.application.in.GetStatesInCountry;
import io.radioweather.radioweatherapi.application.out.ImageStateAPIPort;
import io.radioweather.radioweatherapi.application.out.StateJPAPort;
import io.radioweather.radioweatherapi.domain.States;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UseCaseState implements GetMapImageState, GetStatesInCountry {

    private final ImageStateAPIPort imageStateAPIPort;
    private final StateJPAPort stateJPAPort;

    public UseCaseState(ImageStateAPIPort imageStateAPIPort, StateJPAPort stateJPAPort) {
        this.imageStateAPIPort = imageStateAPIPort;
        this.stateJPAPort = stateJPAPort;
    }

    @Override
    public String urlImageState(String country, String state) {
        return this.imageStateAPIPort.urlImageStateAPI(country, state);
    }

    @Override
    public List<States> states(String countryIso2, int page, int size) {
        return this.stateJPAPort.findByCountry(countryIso2, page, size);
    }

    @Override
    public long totalStatesByIso2(String countryIso2) {
        return this.stateJPAPort.getTotalStatesByIso2(countryIso2);
    }


}
