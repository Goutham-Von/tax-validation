package com.txnvalidation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DbConnectionService {

    public static final String DEFAULT_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/txnvalidationDB";
    private static final String DEFAULT_USERNAME = "root";
    private static final String DEFAULT_PASSWORD = "password";
    private static final String VALIDATOR_QUERY = "SELECT country.code, taxvalidators.taxvalidator," +
            " country.regex FROM country INNER JOIN taxvalidators " +
            "ON taxvalidators.id = country.validatorid ";
    private static final String BY_COUNTRYNAME = "WHERE country.countryname= ? ";
    public static Connection connection = null;

    public static DbConnectionService dbService = new DbConnectionService();

    public DbConnectionService(){
//        Connection connection = null;
        try {
            DriverManager.registerDriver((Driver) Class.forName(DEFAULT_DRIVER_CLASS).newInstance());
            connection = DriverManager.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getResults(String countryName) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(VALIDATOR_QUERY + BY_COUNTRYNAME);
            ps.setString(1, countryName);
            rs = ps.executeQuery();
            System.out.println(rs.);
            String code = rs.getString("code");
            String validator = rs.getString("taxvalidator");
            String regex = rs.getString("regex");
            return Arrays.asList(code, validator, regex);
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