package com.epam.serdyukov.ispmanager.configuration;

public class DBConnectionConfig {
    private final String dbHost;
    private final String dbUser;
    private final String dbPass;

    public DBConnectionConfig(String dbHost, String dbUser, String dbPass) {
        this.dbHost = dbHost;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    public String getDbHost() {
        return dbHost;
    }

    public String getDbPass() {
        return dbPass;
    }

    public String getDbUser() {
        return dbUser;
    }

}
