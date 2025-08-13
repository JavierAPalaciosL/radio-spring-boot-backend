package io.radioweather.radioweatherapi.infrastructure.postgresql;

import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.City;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.State;
import io.radioweather.radioweatherapi.infrastructure.postgresql.repository.JPARepositoryCities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CityJPAAdapterTests {

    @Mock
    private JPARepositoryCities jpaRepositoryCities;

    @InjectMocks
    private CityJPAAdapter cityJPAAdapter;

    @Test
    public void testFindByCityWhenNotExists() {

        String countryIso2 = "MXE";
        String codeState = "VER";
        String cityName = "Veracruz";

        Mockito.when(this.jpaRepositoryCities.findByCity(countryIso2, codeState, cityName)).thenReturn(null);
        Assertions.assertNull(this.cityJPAAdapter.findCityByCountryIso2AndCodeState(countryIso2, codeState, cityName));
    }

    @Test
    public void testFindByCityWhenExists() {

        String countryIso2 = "MX";
        String codeState = "VER";
        String cityName = "Veracruz";

        Mockito.when(this.jpaRepositoryCities.findByCity(countryIso2, codeState, cityName)).thenReturn(new City(543124, "Veracruz", new State(), 19.42, -4.4312, "bmfasg", null));
        io.radioweather.radioweatherapi.domain.City city = this.cityJPAAdapter.findCityByCountryIso2AndCodeState(countryIso2, codeState, cityName);

        Assertions.assertNotNull(city);

    }

    @Test
    public void testCountCityByCountryIso2AndCodeStateWhenExists() {
        String countryIso2 = "MX";
        String codeState = "VER";

        Mockito.when(this.jpaRepositoryCities.countByStateAndCountryWithJoin(countryIso2, codeState)).thenReturn(256L);

        long count = this.cityJPAAdapter.countCityByCountryIso2AndCodeState(countryIso2, codeState);

        Assertions.assertTrue(count >= 1);

    }

    @Test
    public void testCountCityByCountryIso2AndCodeStateWhenNotExists() {
        String countryIso2 = "MXES";
        String codeState = "VER";

        Mockito.when(this.jpaRepositoryCities.countByStateAndCountryWithJoin(countryIso2, codeState)).thenReturn(0L);
        long count = this.cityJPAAdapter.countCityByCountryIso2AndCodeState(countryIso2, codeState);

        Assertions.assertTrue(count <= 0);
    }



}
