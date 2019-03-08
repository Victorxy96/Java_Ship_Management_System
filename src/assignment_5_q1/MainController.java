/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_5_q1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLTransactionRollbackException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;

/**
 * MainController class control the Main UI which allow the user to create a new cruise
 * and initialize the original data base. It contains the following method:
 * initialize, createInnerTables, createSailors, createShips, createPorts,
 * createPassengers, recordsPassenger, createCruises and buttons action to
 * create new cruise and initialize the original data base.
 *
 * @author xy
 */
public class MainController implements Initializable {

    @FXML
    private Button createCruise;
    @FXML
    private Button createInnerTables;

    /**
     * Initializes the controller class.
     *
     * @param url url
     * @param rb resource
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void buttonActionCreateCruise(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Cruise_FXML.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void buttonActionCreateInnerTables(ActionEvent event) throws ClassNotFoundException, SQLException {
        // load the database
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        System.out.println("Load successfully!");
        // connect the database
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ShipCompany");
        System.out.println("Connect the database successfully!");
        System.out.println("****************************************************");
        createCruises(conn);
        System.out.println("****************************************************");
        createSailors(conn);
        System.out.println("****************************************************");
        createShips(conn);
        System.out.println("****************************************************");
        createPorts(conn);
        System.out.println("****************************************************");
        createPassengers(conn);
        System.out.println("****************************************************");
        recordsPassenger(conn);
        System.out.println("****************************************************");
        conn.close();
        System.out.println("The Inner Table Finished!");
    }

    /**
     * Create sailor table.
     *
     * @param conn connection of the database
     * @throws SQLException SQL error
     */
    public void createSailors(Connection conn) throws SQLException {
        System.out.println("Create Sailor table!");
        //create a new Statement object
        Statement stat = conn.createStatement();
        try {
            stat.executeUpdate("DROP TABLE Sailors");
            stat.executeUpdate("CREATE TABLE Sailors (SailorID int, "
                    + "Name varchar(255), "
                    + "DateOfBirth varchar(255), Nationality varchar(255), "
                    + "Supervisor varchar(255), " + "Position varchar(255), "
                    + "PRIMARY KEY (SailorID))");
        } catch (SQLTransactionRollbackException e) {
            System.out.println("Table Already exists.");
        }
        // insert data
        try {
            stat.executeUpdate("INSERT INTO Sailors (SailorID, Name, DateOfBirth, Nationality, Supervisor, Position) VALUES (1, 'Mulri', '1980/03/01','Australian','N/A','Supervisor')");
            stat.executeUpdate("INSERT INTO Sailors (SailorID, Name, DateOfBirth, Nationality, Supervisor, Position) VALUES (2, 'Riaz', '1986/05/02','Australian','N/A','Supervisor')");
            stat.executeUpdate("INSERT INTO Sailors (SailorID, Name, DateOfBirth, Nationality, Supervisor, Position) VALUES (3, 'Bodycoat', '1988/06/08','Australian','N/A','Supervisor')");
            stat.executeUpdate("INSERT INTO Sailors (SailorID, Name, DateOfBirth, Nationality, Supervisor, Position) VALUES (4, 'Tan', '1987/03/04','Chinese','Mulri','Sailor')");
            stat.executeUpdate("INSERT INTO Sailors (SailorID, Name, DateOfBirth, Nationality, Supervisor, Position) VALUES (5, 'Tony', '1996/01/01','Australian','Mulri','Sailor')");
            stat.executeUpdate("INSERT INTO Sailors (SailorID, Name, DateOfBirth, Nationality, Supervisor, Position) VALUES (6, 'Sandy', '1997/12/11','Indian','Mulri','Sailor')");
            stat.executeUpdate("INSERT INTO Sailors (SailorID, Name, DateOfBirth, Nationality, Supervisor, Position) VALUES (7, 'Jane', '1993/05/21','Australian','Riaz','Sailor')");
            stat.executeUpdate("INSERT INTO Sailors (SailorID, Name, DateOfBirth, Nationality, Supervisor, Position) VALUES (8, 'Johnson', '1980/03/01','Australian','Riaz','Sailor')");
            stat.executeUpdate("INSERT INTO Sailors (SailorID, Name, DateOfBirth, Nationality, Supervisor, Position) VALUES (9, 'Coffee', '1986/05/11','Australian','Bodycoat','Sailor')");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Record Already exists.");
        }
        System.out.println("Finish the Sailor table!");

    }

    /**
     * Create ship table.
     *
     * @param conn connection of the database
     * @throws SQLException SQL error
     */
    public void createShips(Connection conn) throws SQLException {
        System.out.println("Create the Ship table!");
        //create a new Statement object
        Statement stat = conn.createStatement();
        try {
            stat.executeUpdate("DROP TABLE Ships");
            stat.executeUpdate("CREATE TABLE Ships (ShipID int, "
                    + "Name varchar(255), "
                    + "Weight double, " + "YearBuilt int, "
                    + "Capacity int, "
                    + "PRIMARY KEY (ShipID))");
        } catch (SQLTransactionRollbackException e) {
            System.out.println("Table Already exists.");
        }
        // insert the original data
        try {
            stat.executeUpdate("INSERT INTO Ships (ShipID, Name, Weight, YearBuilt, Capacity) VALUES (1, 'Victoria_1', 20.5, 1960, 200)");
            stat.executeUpdate("INSERT INTO Ships (ShipID, Name, Weight, YearBuilt, Capacity) VALUES (2, 'Victoria_2', 15.5, 1968, 150)");
            stat.executeUpdate("INSERT INTO Ships (ShipID, Name, Weight, YearBuilt, Capacity) VALUES (3, 'Victoria_3', 8.5, 1999, 60)");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Record Already exists.");
        }
        System.out.println("Finish the Ship table!");

    }

    /**
     * Create ports table.
     *
     * @param conn connection of the database
     * @throws SQLException SQL error
     */
    public void createPorts(Connection conn) throws SQLException {
        System.out.println("Create Port table");
        //create a new Statement object
        Statement stat = conn.createStatement();
        try {
            stat.executeUpdate("DROP TABLE Ports");
            stat.executeUpdate("CREATE TABLE Ports (PortName varchar(255), "
                    + "Nation varchar(255), "
                    + "Population double, PassportRequirement boolean, "
                    + "Fee double, IsUsed boolean, "
                    + "PRIMARY KEY (PortName))");
        } catch (SQLTransactionRollbackException e) {
            System.out.println("Table Already exists.");
        }
        // insert the original data
        try {
            stat.executeUpdate("INSERT INTO Ports (PortName, Nation, Population, PassportRequirement, Fee, IsUsed) VALUES ('Harbor_1', 'Australia', 8.9, true, 200.2, true)");
            stat.executeUpdate("INSERT INTO Ports (PortName, Nation, Population, PassportRequirement, Fee, IsUsed) VALUES ('Harbor_2', 'Australia', 9.5, true, 180.5, true)");
            stat.executeUpdate("INSERT INTO Ports (PortName, Nation, Population, PassportRequirement, Fee, IsUsed) VALUES ('Harbor_3', 'China', 6.8, true, 155.2, false)");
            stat.executeUpdate("INSERT INTO Ports (PortName, Nation, Population, PassportRequirement, Fee, IsUsed) VALUES ('Harbor_4', 'India', 5.6, false, 130.5, true)");
            stat.executeUpdate("INSERT INTO Ports (PortName, Nation, Population, PassportRequirement, Fee, IsUsed) VALUES ('Harbor_5', 'America', 11.8, false, 210.2, false)");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Record Already exists.");
        }
        System.out.println("Finish the Port table!");
    }

    /**
     * Create passengers table.
     *
     * @param conn connection of the database
     * @throws SQLException SQL error
     */
    public void createPassengers(Connection conn) throws SQLException {
        System.out.println("Create Passenger table");
        //create a new Statement object
        Statement stat = conn.createStatement();
        try {
            stat.executeUpdate("DROP TABLE Passengers");
            stat.executeUpdate("CREATE TABLE Passengers (PassengerID int, "
                    + "PassengerName varchar(255), "
                    + "PassengerHome varchar(255), PassengerNationality varchar(255), "
                    + "PassengerDateOfBirth varchar(255), "
                    + "PRIMARY KEY (PassengerID))");
        } catch (SQLTransactionRollbackException e) {
            System.out.println("Table Already exists.");
        }
        // insert the original data
        try {
            stat.executeUpdate("INSERT INTO Passengers (PassengerID, PassengerName, PassengerHome, PassengerNationality, PassengerDateOfBirth) VALUES (1, 'Frank', 'Victoria Street', 'Australia', '1995/05/20')");
            stat.executeUpdate("INSERT INTO Passengers (PassengerID, PassengerName, PassengerHome, PassengerNationality, PassengerDateOfBirth) VALUES (2, 'Marlia', 'Winifred Street', 'Australia', '1996/02/21')");
            stat.executeUpdate("INSERT INTO Passengers (PassengerID, PassengerName, PassengerHome, PassengerNationality, PassengerDateOfBirth) VALUES (3, 'Jason', 'Changyang Street', 'China', '1995/02/10')");
            stat.executeUpdate("INSERT INTO Passengers (PassengerID, PassengerName, PassengerHome, PassengerNationality, PassengerDateOfBirth) VALUES (4, 'Alan', 'Hejian Street', 'China', '1992/12/12')");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Record Already exists.");
        }
        System.out.println("Finish the Port table!");
    }

    /**
     * Create the record table which contains the history cruise of passengers.
     *
     * @param conn connection of the database
     * @throws SQLException SQL error
     */
    public void recordsPassenger(Connection conn) throws SQLException {
        System.out.println("Create Record table that contains the passengers' history cruise");
        //create a new Statement object
        Statement stat = conn.createStatement();
        try {
            stat.executeUpdate("DROP TABLE RecordPassenger");
            stat.executeUpdate("CREATE TABLE RecordPassenger (PassengerID int, "
                    + "CruiseID int, MoneySpentOnCruise double, "
                    + "Rate double "
                    + ")");
        } catch (SQLTransactionRollbackException e) {
            System.out.println("Table Already exists.");
        }
        // insert the original data
        try {
            stat.executeUpdate("INSERT INTO RecordPassenger (PassengerID, CruiseID, MoneySpentOnCruise, Rate) VALUES (1, 1, 2676.2, 8.8)");
            stat.executeUpdate("INSERT INTO RecordPassenger (PassengerID, CruiseID, MoneySpentOnCruise, Rate) VALUES (1, 2, 2216.7, 9.2)");
            stat.executeUpdate("INSERT INTO RecordPassenger (PassengerID, CruiseID, MoneySpentOnCruise, Rate) VALUES (2, 1, 1898.4, 6.6)");
            stat.executeUpdate("INSERT INTO RecordPassenger (PassengerID, CruiseID, MoneySpentOnCruise, Rate) VALUES (2, 2, 2300.2, 9.8)");
            stat.executeUpdate("INSERT INTO RecordPassenger (PassengerID, CruiseID, MoneySpentOnCruise, Rate) VALUES (3, 1, 1622.7, 5.6)");
            stat.executeUpdate("INSERT INTO RecordPassenger (PassengerID, CruiseID, MoneySpentOnCruise, Rate) VALUES (4, 2, 1755.5, 8.4)");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Record Already exists.");
        }
        System.out.println("Finish the Port table!");
    }

    /**
     * Create cruise table.
     *
     * @param conn connection of the database
     * @throws SQLException SQL error
     */
    public void createCruises(Connection conn) throws SQLException {
        System.out.println("Create the Cruises table!");
        //create a new Statement object
        Statement stat = conn.createStatement();
        try {
            stat.executeUpdate("DROP TABLE Cruises");
            stat.executeUpdate("CREATE TABLE Cruises (CruiseID int, "
                    + "SailDate varchar(255), "
                    + "ReturnDate varchar(255), " + "ShipID int, "
                    + "PRIMARY KEY (CruiseID))");
        } catch (SQLTransactionRollbackException e) {
            System.out.println("Table Already exists.");
        }
        System.out.println("Finish the Cruise table!");
        System.out.println("****************************************************");

    }

}
