package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DataSource;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.IOException;
import java.sql.*;


public class UserDaoJdbc implements UserDao {

    private PGSimpleDataSource source;


    public UserDaoJdbc(DataSource source) throws SQLException, IOException {
        this.source = source.connect();
    }

    @Override
    public void register(User user) {
        try (Connection conn = source.getConnection()) {
            String sql = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.NO_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getPasswordByEmail(String email) {
        try (Connection conn = source.getConnection()) {
            String sql = "SELECT password FROM users WHERE email = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,email);
            ResultSet result = statement.executeQuery();

            if (!result.next()){
                return null;
            }
            return  result.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNameByEmail(String email) {
        try (Connection conn = source.getConnection()) {
            String sql = "SELECT name FROM users WHERE email = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,email);
            ResultSet result = statement.executeQuery();

            if (!result.next()){
                return null;
            }
            return  result.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer getIdByEmail(String email){
        try (Connection conn = source.getConnection()) {
            String sql = "SELECT id FROM users WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,email);
            ResultSet result = statement.executeQuery();

            if (!result.next()){
                return null;
            }
            return  result.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createCart(int id) {
        try (Connection conn = source.getConnection()) {
            String sql = "Insert Into cart(user_id) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.NO_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
