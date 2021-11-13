package it.tdgroup.corso.rest.appconfig;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import it.tdgroup.corso.rest.appconfig.entity.AppConfig;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class AppConfigRepository implements PanacheMongoRepository<AppConfig> {

}
