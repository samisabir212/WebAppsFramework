package DBdataSource;

import common.BaseAPIs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.sql.*;
import java.util.List;

/**
 * Created by sami on 7/19/17.
 */
public class Datasource extends BaseAPIs {



    public static final String DB_NAME = "demoqa.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/sami/git-home-repos/WebAppsFramework/DEMOQA/src/data/" + DB_NAME;


    Connection conn = null;

    Statement stmt = null;

    ResultSet resultSet = null;








    /*
    method to open DB connection
        using connection_string
     */
    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }



    public void createAStatement(String query) throws SQLException {
        conn = DriverManager.getConnection(CONNECTION_STRING);
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(query);

    }

    public void compareList(String query, String locator) throws SQLException {

        createAStatement(query);

        WebElement drop_down =driver.findElement(By.id("dropdown_7"));
        Select se = new Select(drop_down);
        List<WebElement> options = se.getOptions();


    }



    public void printDBcountryList(String query) throws SQLException {

        createAStatement(query);

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));

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


    public void printAnyQuery(String query) throws SQLException {

        createAStatement(query);

    }

    public void getList(String xpath) {


    }






}
