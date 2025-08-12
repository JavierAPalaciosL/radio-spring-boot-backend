package io.radioweather.radioweatherapi.infrastructure.postgresql;

import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.City;
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

}
