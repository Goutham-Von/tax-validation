package com.txnvalidation;

import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DbConnectionService {

    public static final String DEFAULT_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/txnvalidationDB";
    private static final String DEFAULT_USERNAME = "root";
    private static final String DEFAULT_PASSWORD = "password";
    private static final String COUNTRY_QUERY  = "SELECT code, regex FROM country";
    private static final String BY_COUNTRYNAME = "WHERE countryname = ? ";
    private static final String VALIDATOR_QUERY = "SELECT taxvalidator FROM validators";
    private static final String BY_COUNTRYCODE = "WHERE countrycode = ? ";

    public static Connection connection = null;
    public static DbConnectionService dbService = new DbConnectionService();

    public DbConnectionService(){
        try {
            DriverManager.registerDriver((Driver) Class.forName(DEFAULT_DRIVER_CLASS).newInstance());
            connection = DriverManager.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Pair<String, String> getCountry(String countryName) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(COUNTRY_QUERY + BY_COUNTRYNAME);
            ps.setString(1, countryName);
            rs = ps.executeQuery();
            Pair<String, String> queryResult = null;
            while (rs.next()) {
                queryResult = new Pair<>(rs.getString(1), rs.getString(2));
            }
            return queryResult;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnectionService.close(rs);
            DbConnectionService.close(ps);
        }
        return null;
    }

    public static List<String> getValidators(String countryCode) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(VALIDATOR_QUERY + BY_COUNTRYCODE);
            ps.setString(1, countryCode);
            rs = ps.executeQuery();
            List<String> validators = new ArrayList<>();
            while (rs.next()) {
                validators.add(rs.getString(1));
            }
            return validators;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnectionService.close(rs);
            DbConnectionService.close(ps);
        }
        return null;
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}