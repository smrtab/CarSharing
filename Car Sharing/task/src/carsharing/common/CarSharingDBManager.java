package carsharing.common;

import java.sql.*;

public class CarSharingDBManager {

    private static final int SELECT = 0;
    private static final int UPDATE = 1;

    private static final String DEFAULT_DB_NAME = "carsharing.mv.db";
    private static final String JDBC_DRIVER = "org.h2.Driver";

    private String dbPathPrefix = "./src/carsharing/db/";
    private String dbPath;

    private static CarSharingDBManager instance;

    private CarSharingDBManager(String dbName) {
        dbPath = "jdbc:h2:" + dbPathPrefix + dbName;
    }

    public static CarSharingDBManager getInstance(String[] args) {
        if (instance == null) {
            instance = new CarSharingDBManager(
                getDbName(args)
            );
        }
        return instance;
    }

    public static CarSharingDBManager getInstance() {
        if (instance == null) {
            instance = new CarSharingDBManager(
                DEFAULT_DB_NAME
            );
        }
        return instance;
    }

    private static String getDbName(String[] args) {
        String dbName = DEFAULT_DB_NAME;
        if  (args.length > 0 && args[0].equals("-databaseFileName")) {
            dbName = args[1];
        }
        return dbName;
    }

    public Connection getConnection() {

        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(dbPath);
            connection.setAutoCommit(true);

            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int executeUpdate(String sql){
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()){
                return statement.executeUpdate(sql);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public void init(){

        //executeUpdate("DROP TABLE IF EXISTS CUSTOMER");
        //executeUpdate("DROP TABLE IF EXISTS CAR");
        //executeUpdate("DROP TABLE IF EXISTS COMPANY");

        executeUpdate("CREATE TABLE IF NOT EXISTS COMPANY " +
            "(ID INTEGER NOT NULL AUTO_INCREMENT, " +
            " NAME VARCHAR(255) NOT NULL UNIQUE, " +
            " PRIMARY KEY (ID))");

        executeUpdate("CREATE TABLE IF NOT EXISTS CAR " +
            "(ID INTEGER NOT NULL AUTO_INCREMENT, " +
            " COMPANY_ID INTEGER NOT NULL, " +
            " NAME VARCHAR(255) NOT NULL UNIQUE, " +
            " PRIMARY KEY (ID)," +
            "CONSTRAINT fk_company FOREIGN KEY (COMPANY_ID) " +
            "REFERENCES COMPANY(ID))");

        executeUpdate("CREATE TABLE IF NOT EXISTS CUSTOMER " +
            "(ID INTEGER NOT NULL AUTO_INCREMENT, " +
            " RENTED_CAR_ID INTEGER NULL, " +
            " NAME VARCHAR(255) NOT NULL UNIQUE, " +
            " PRIMARY KEY (ID)," +
            "CONSTRAINT fk_car FOREIGN KEY (RENTED_CAR_ID) " +
            "REFERENCES CAR(ID))");
    }
}
