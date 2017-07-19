package DBdataSource;

import common.BaseAPIs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by sami on 7/19/17.
 */
public class Datasource extends BaseAPIs {



    public static final String DB_NAME = "demoqa.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/sami/git-home-repos/WebAppsFramework/DEMOQA/src/data/" + DB_NAME;


    private Connection conn;


    /*
    method to open DB connection
        using connection_string
     */
    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            System.out.println("connected");
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //call the close method after finshing up using the Database
    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
            e.printStackTrace();
        }
    }


    



}
