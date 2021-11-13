package it.tdgroup.corso.rest.appconfig;


import it.tdgroup.corso.rest.appconfig.dto.AppConfigDTO;
import it.tdgroup.corso.rest.api.exception.ServiceException;

public interface AppConfigService {

    void setAppConfig(AppConfigDTO appConfig) throws ServiceException;

    AppConfigDTO getAppConfig(String nomeConfig) throws ServiceException;

    void removeAppConfig(String nomeConfig) throws ServiceException;

}
