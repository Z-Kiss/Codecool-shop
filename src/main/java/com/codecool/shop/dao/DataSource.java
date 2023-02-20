package com.codecool.shop.dao;

import org.postgresql.ds.PGSimpleDataSource;
import utils.PropertiReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public  class DataSource {
    private static DataSource INSTANCE;

    private Properties properties;
    private DataSource() throws IOException {
        this.properties = PropertiReader.getProperty();
    };

    public static DataSource getInstance() throws IOException {
        if(INSTANCE == null) {
            INSTANCE = new DataSource();
        }
        return INSTANCE;
    }

    public PGSimpleDataSource connect() throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/connection.properties"));

        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setDatabaseName(properties.getProperty("database"));
        dataSource.setUser(properties.getProperty("user"));
        dataSource.setPassword(properties.getProperty("password"));
        reader.close();

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;

    }
}


