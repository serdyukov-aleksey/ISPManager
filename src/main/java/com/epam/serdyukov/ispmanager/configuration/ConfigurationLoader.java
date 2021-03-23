package com.epam.serdyukov.ispmanager.configuration;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationLoader {

    private static final String APPCONFIGFILE = "app.properties";
    private final Properties appProps = new Properties();
    private final Logger log = Logger.getLogger(getClass());

    private void load () {
        try (InputStream is = getClass().getResourceAsStream(APPCONFIGFILE)) {
            this.appProps.load(is);
            log.info("Properties loading");
        } catch (IOException e) {
            log.error("Failed to create FileInputStream: " + e.getMessage());
        }
    }

    public DBConnectionConfig getDBConfig() {
        load();
        String dbHost = appProps.getProperty("db.host");
        String dbUser = appProps.getProperty("db.user");
        String dbPass = appProps.getProperty("db.password");
        return new DBConnectionConfig(dbHost, dbUser, dbPass);
    }

}
