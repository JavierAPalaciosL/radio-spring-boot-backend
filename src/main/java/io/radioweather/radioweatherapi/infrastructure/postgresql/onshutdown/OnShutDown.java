package io.radioweather.radioweatherapi.infrastructure.postgresql.onshutdown;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class OnShutDown implements ApplicationListener<ContextClosedEvent> {

    private final HikariDataSource ds;

    public OnShutDown(HikariDataSource ds) {
        this.ds = ds;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        if (ds != null && !ds.isClosed()) ds.close();
    }

}
