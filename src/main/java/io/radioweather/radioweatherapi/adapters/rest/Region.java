package io.radioweather.radioweatherapi.adapters.rest;

import io.radioweather.radioweatherapi.adapters.rest.dtos.CountryRadioDTO;
import io.radioweather.radioweatherapi.application.usecases.UseCaseCountries;
import io.radioweather.radioweatherapi.application.usecases.UseCaseRadio;
import io.radioweather.radioweatherapi.domain.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/regions")
public class Region {

    private final UseCaseCountries useCaseCountries;
    private final UseCaseRadio useCaseRadio;

    public Region(UseCaseCountries useCaseCountries, UseCaseRadio useCaseRadio) {
        this.useCaseCountries = useCaseCountries;
        this.useCaseRadio = useCaseRadio;
    }

    @GetMapping("/{iso2}/city/{name}/stations")
    public ResponseEntity<List<CountryRadioDTO>> getCoordsStations(@PathVariable String iso2,
                                                                   @PathVariable String name,
                                                                   @RequestParam(value = "size", required = false) String size) {

        if(size != null && !size.matches("\\d+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }

        List<Country> countries = useCaseCountries.getCoordsCountry(name,0,0);

        List<CountryRadioDTO> countryRadioDTOS = countries.stream().map(
                country -> {
                    return new CountryRadioDTO(country, this.useCaseRadio.getStationByCodeCountryAndNameCity(country.getCountryCode(), country.getName(), (size != null)? Integer.parseInt(size) : 20));
                }
        ).filter(dto -> dto.getRadios() != null && !dto.getRadios().isEmpty()).collect(Collectors.toList());

        return ResponseEntity.ok(countryRadioDTOS);

    }

    @GetMapping("/{iso2}")
    public ResponseEntity<List<CountryRadioDTO>> getCoordsStations(@PathVariable String iso2) {

        

        return null;
    }

}
