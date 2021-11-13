package it.tdgroup.corso.rest.api.config;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import lombok.extern.apachecommons.CommonsLog;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
@CommonsLog
public class ApplicationLifeCycle {

    void onStart(@Observes StartupEvent event) {
        log.info("The application is starting with profile:" + ProfileManager.getActiveProfile());

    }

    void onStop(@Observes ShutdownEvent event) {
        log.info("The application is stopping...");
    }

}
