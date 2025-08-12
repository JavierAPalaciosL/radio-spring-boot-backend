package io.radioweather.radioweatherapi.infrastructure.postgresql;

import io.radioweather.radioweatherapi.infrastructure.postgresql.repository.JPARepositoryCities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CityJPAAdapterTests {

    @Mock
    private JPARepositoryCities jpaRepositoryCities;

    @Test
    public void testCityJPAAdapter() {

    }

}
