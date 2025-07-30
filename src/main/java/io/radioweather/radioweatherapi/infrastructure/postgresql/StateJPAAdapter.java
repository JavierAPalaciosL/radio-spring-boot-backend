package io.radioweather.radioweatherapi.infrastructure.postgresql;

import io.radioweather.radioweatherapi.application.out.StateJPAPort;
import io.radioweather.radioweatherapi.domain.States;
import io.radioweather.radioweatherapi.infrastructure.api.wikipedia.WikipediaAPI;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.Country;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.State;
import io.radioweather.radioweatherapi.infrastructure.postgresql.repository.JPARepositoryCountry;
import io.radioweather.radioweatherapi.infrastructure.postgresql.repository.JPARepositoryStates;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StateJPAAdapter implements StateJPAPort {

    private final JPARepositoryStates jpaRepositoryStates;
    private final JPARepositoryCountry jpaRepositoryCountry;
    private final WikipediaAPI  wikipediaAPI;

    public  StateJPAAdapter(JPARepositoryStates jpaRepositoryStates, JPARepositoryCountry jpaRepositoryCountry, WikipediaAPI wikipediaAPI) {
        this.jpaRepositoryStates = jpaRepositoryStates;
        this.jpaRepositoryCountry = jpaRepositoryCountry;
        this.wikipediaAPI = wikipediaAPI;
    }

    @Override
    public List<States> findByCountry(String countryIso2, int page, int size) {
        Country countryFound = this.jpaRepositoryCountry.findByIso2(countryIso2);

        Slice<State> listOfStates = this.jpaRepositoryStates.findByCountry(countryFound, PageRequest.of(page, size));

        return listOfStates.stream().map(state ->
        {
            return new States(
                    state.getId(),
                    state.getName().stripTrailing(),
                    new io.radioweather.radioweatherapi.domain.Country(state.getCountry().getName().stripTrailing(), null, Double.parseDouble(state.getCountry().getLatitude()), Double.parseDouble(state.getCountry().getLongitude()), "", "", state.getCountry().getIso2().stripTrailing(), state.getCountry().getEmoji().stripTrailing(), state.getCountry().getEmojiu().stripTrailing()),
                    state.getStateCode().stripTrailing(),
                    state.getLatitude(),
                    state.getLongitude(),
                    this.wikipediaAPI.urlImageStateAPIWithEval(StringUtils.stripAccents(state.getCountry().getName().stripTrailing().replaceAll(" ", "%20")), StringUtils.stripAccents(state.getName().stripTrailing().replaceAll(" ", "%20")))
            );
        }).collect(Collectors.toList());

    }

    @Override
    public long getTotalStatesByIso2(String countryIso2) {
        return this.jpaRepositoryStates.countByCountry(this.jpaRepositoryCountry.findByIso2(countryIso2));
    }


}
