package com.shreyas.javadatabaseconnectivity;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Logger;

public class Main {

    private final static String CONN_STRING = "jdbc:mysql://localhost:3306/shreyas";
    private final static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        // rather than saving the password hardcoded in code or in properties files, we are using Swing to take input of it.
        String username = JOptionPane.showInputDialog(null, "Enter DB Username");
        JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(null, passwordField, "Enter DB Password", JOptionPane.OK_CANCEL_OPTION);
        final char[] password = (option == JOptionPane.OK_OPTION) ? passwordField.getPassword() : null;

//        var dataSource = new MysqlDataSource();
//        dataSource.setURL(CONN_STRING);   // I can set ServerName, port and db name also rather than connection url as well
//        Connection connection = dataSource.getConnection(username, String.valueOf(password));

        String query = "SELECT * FROM music.artists";

        try (Connection connection = DriverManager.getConnection(
                CONN_STRING, username, String.valueOf(password)
        )) {
            log.info("Connection established successfully!");
            Arrays.fill(password, ' ');

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
