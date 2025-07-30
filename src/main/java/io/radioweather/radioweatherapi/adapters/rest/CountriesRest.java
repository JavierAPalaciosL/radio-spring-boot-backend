package io.radioweather.radioweatherapi.adapters.rest;

import io.radioweather.radioweatherapi.adapters.rest.dtos.CountryRadioDTO;
import io.radioweather.radioweatherapi.adapters.rest.dtos.GetAllCountriesDTO;
import io.radioweather.radioweatherapi.adapters.rest.dtos.GetStatesByIso2DTO;
import io.radioweather.radioweatherapi.application.usecases.UseCaseCountries;
import io.radioweather.radioweatherapi.application.usecases.UseCaseRadio;
import io.radioweather.radioweatherapi.application.usecases.UseCaseState;
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

@RestController
@RequestMapping("v1/countries")
public class CountriesRest {

    private final UseCaseCountries useCaseCountries;
    private final UseCaseRadio  useCaseRadio;
    private final UseCaseState useCaseState;

    public CountriesRest(UseCaseCountries useCaseCountries, UseCaseRadio useCaseRadio,  UseCaseState useCaseState) {
        this.useCaseCountries = useCaseCountries;
        this.useCaseRadio = useCaseRadio;
        this.useCaseState = useCaseState;
    }

    @GetMapping
    public ResponseEntity<GetAllCountriesDTO> getCountries(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        return ResponseEntity.ok(new GetAllCountriesDTO(this.useCaseCountries.getAllCountries(page, size),this.useCaseCountries.countAllCountries()));
    }

    @GetMapping("/{iso2}")
    public ResponseEntity<List<Country>> getCoordsCountry(@PathVariable String iso2, @RequestParam(value = "page") int page, @RequestParam(value = "size-page") int size) {
        return ResponseEntity.ok(useCaseCountries.getCoordsCountry(iso2,page,size));
    }

    @GetMapping("/{iso2}/states")
    public ResponseEntity<GetStatesByIso2DTO> getStatesByIso2(@PathVariable String iso2, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(new GetStatesByIso2DTO(this.useCaseState.states(iso2, page, size), this.useCaseState.totalStatesByIso2(iso2)));
    }

    @GetMapping("/{name}/stations")
    public ResponseEntity<List<CountryRadioDTO>> getCoordsStations(@PathVariable String name, @RequestParam(value = "size", required = false) String size) {

        if(size != null && !size.matches("\\d+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }

        List<Country> countries = useCaseCountries.getCoordsCountry(name,0,0);

        List<CountryRadioDTO> countryRadioDTOS = countries.stream().map(
                country -> {
                    return new CountryRadioDTO(country, this.useCaseRadio.getStationByCodeCountryAndNameCity(country.getCountryCode(), country.getName(), (size != null)? Integer.parseInt(size) : 20));
                }
        ).filter(dto -> dto.getRadios() != null && !dto.getRadios().isEmpty()).toList();

        return ResponseEntity.ok(countryRadioDTOS);

    }

    @GetMapping("/coords")
    public ResponseEntity<CountryRadioDTO> getCountryByCoords(@RequestParam("lat") double latitude, @RequestParam("lon") double longitude, @RequestParam(value = "size", required = false) String size) {

        if(size != null && !size.matches("\\d+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Country country = useCaseCountries.findByCoords(latitude, longitude);

        return ResponseEntity.ok(new CountryRadioDTO(country, this.useCaseRadio.getStationByCodeCountryAndNameCity(country.getCountryCode(), country.getName(), (size != null)? Integer.parseInt(size) : 20)));
    }

    @GetMapping("/sexo")
    public String sexo(){
        return useCaseState.urlImageState("Argentina", "Santa%20Fe");
    }

}
