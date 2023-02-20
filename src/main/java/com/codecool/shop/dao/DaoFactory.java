package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.jdbc.CartDaoJdbc;
import com.codecool.shop.dao.implementation.mem.CartDaoMem;
import utils.PropertiReader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {

    private Properties properties;
    private static DaoFactory instance;

    public static DaoFactory getInstance() throws IOException {
        if (instance == null){
            instance = new DaoFactory();
        }
        return instance;
    }

    public DaoFactory() throws IOException {
        this.properties = PropertiReader.getProperty();
    }

    public CartDao getCartDao() throws SQLException, IOException {
        if(properties.getProperty("dao").equals("jdbc")){
            return CartDaoJdbc.getInstance();
        }else{
            return CartDaoMem.getInstance();
        }
    }
}
