package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DataSource;
import com.codecool.shop.model.Product;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class CartDaoJdbc implements CartDao {


    private PGSimpleDataSource source;

    private static CartDaoJdbc instance;

    public static CartDaoJdbc getInstance() throws IOException, SQLException {
        if(instance == null){
            instance = new CartDaoJdbc(DataSource.getInstance());
        }
        return instance;
    }

    public CartDaoJdbc(DataSource source) throws SQLException, IOException {
        this.source = source.connect();
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public void remove(Product product) {

    }

    @Override
    public HashMap<Product, Integer> getAll() {
        return null;
    }
}
