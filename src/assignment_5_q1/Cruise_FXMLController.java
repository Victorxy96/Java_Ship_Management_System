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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Cruise_FXMLController class control the Cruise_FXML UI. The Curise_FXML UI
 * Allow the user to click the corresponding buttons to add the cruiseID, sail
 * date, return date, Ship, supervisor, sailors, ports and passenger to the
 * cruise and view the data in the database. The class contains the following
 * field: The cruise created: Cruise cruise and its field:int numCruise, String
 * sailDate, String returnDate, Ship ship, arraylist ports, arraylist pass,
 * arraylist sailors. The class contains the method checkDate to check the user
 * input date, the method error to show the error window and methods of button
 * actions to add the fields of cruise and view data.
 *
 * @author xy
 */
public class Cruise_FXMLController implements Initializable {

    @FXML
    private Button addPassenger;
    @FXML
    private Button addSailor;
    @FXML
    private Button addSupervisor;
    @FXML
    private Button addShip;
    @FXML
    private Button addReturnDate;
    @FXML
    private Button addSailDate;
    @FXML
    private Button viewPort;
    @FXML
    private Button viewSailor;
    @FXML
    private Button viewShip;
    @FXML
    private Button viewSupervisor;
    @FXML
    private Button addCruiseID;
    @FXML
    private Button addPort;
    @FXML
    private Button viewPassenger;
    @FXML
    private Button generateReports;
    @FXML
    private TextField returnDateText;
    @FXML
    private TextField shipText;
    @FXML
    private TextField supervisorText;
    @FXML
    private TextField sailorText;
    @FXML
    private TextField portText;
    @FXML
    private TextField passenegrIDText;
    @FXML
    private TextField passengerMoneyText;
    @FXML
    private TextField sailDateText;
    @FXML
    private TextField cruiseIDText;

    /**
     * Cruise for this route
     */
    public static Cruise cruise;
    /**
     * The ID number of this cruise
     */
    public int numCruise;

    /**
     * The sail date of this cruise
     */
    public String sailDate;

    /**
     * The return date of this cruise
     */
    public String returnDate;

    /**
     * The ship of the cruise
     */
    public Ship ship;

    /**
     * Ports of this route
     */
    public ArrayList<Port> ports = new ArrayList<>();

    /**
     * Passengers of this route
     */
    public ArrayList<Passenger> pass = new ArrayList<>();

    /**
     * Sailors of this route
     */
    public ArrayList<Sailor> sailors = new ArrayList<>();
    @FXML
    private Button uploadCruise;
    @FXML
    private SplitPane splitPane;
    @FXML
    private ImageView imageCruise;

    /**
     * Initializes the controller class. And set a cruise image.
     *
     * @param url the UI url
     * @param rb resource
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set a cruise image
        imageCruise.setImage(new Image("/assignment_5_q1/cruise.jpg"));
    }

    /**
     * Add passenger to the cruise
     *
     * @param event user operation
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    private void buttonActionAddPassenger(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        String passengerID = passenegrIDText.getText();
        try {
            double money = Double.parseDouble(passengerMoneyText.getText());
            int n = Integer.parseInt(passengerID);
            if (n > 0 && n < 5 && money > 0) {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ShipCompany");
                String prepareSQLPassenger = "SELECT * FROM Passengers WHERE PassengerID =" + n;
                PreparedStatement presP = conn.prepareCall(prepareSQLPassenger);
                ResultSet restP = presP.executeQuery();
                while (restP.next()) {
                    Passenger p = new Passenger(restP.getInt(1), restP.getString(2), restP.getString(3), restP.getString(4), restP.getString(5), money);
                    pass.add(p);
                }
                conn.close();
            } else {
                error();
            }
        } catch (Exception e) {
            error();
        }

    }

    /**
     * Add sailor to the cruise. The user should enter the supervisor first, and
     * can only enter the sailor who under the selected supervisor's supervise.
     *
     * @param event user operation
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    private void buttonActionAddSailor(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        // if user do not enter supervisor first, error happen
        if (!sailors.isEmpty()) {

            // save the supervisor name into Arraylist
            ArrayList<String> supervisor = new ArrayList<>();
            for (Sailor s : sailors) {
                supervisor.add(s.nameSailor);
            }

            String sailorID = sailorText.getText();
            try {
                int n = Integer.parseInt(sailorID);
                if (n > 3 && n < 10) {
                    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                    Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ShipCompany");
                    String prepareSQL = "SELECT * FROM Sailors WHERE SailorID =" + n;
                    PreparedStatement pres = conn.prepareCall(prepareSQL);
                    ResultSet rest = pres.executeQuery();
                    // add the sailors whose supervisor's name is in the Arraylist. If not, error happen.
                    while (rest.next()) {
                        if (supervisor.indexOf(rest.getString("Supervisor")) == -1) {
                            error();
                        } else {
                            Sailor sailor = new Sailor(rest.getInt("SailorID"), rest.getString("Name"), rest.getString("DateOfBirth"), rest.getString("Nationality"), rest.getString("Supervisor"));
                            sailors.add(sailor);
                        }

                    }

                    conn.close();
                } else {
                    error();
                }

            } catch (Exception e) {
                error();
            }
        } else {
            error();
        }

    }

    /**
     * Add supervisor to the cruise.
     *
     * @param event user operation
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    private void buttonActionAddSupervisor(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        String supervisorID = supervisorText.getText();
        try {
            int n = Integer.parseInt(supervisorID);
            if (n > 0 && n < 4) {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ShipCompany");
                String prepareSQL = "SELECT * FROM Sailors WHERE SailorID =" + n;
                PreparedStatement pres = conn.prepareCall(prepareSQL);
                ResultSet rest = pres.executeQuery();
                while (rest.next()) {
                    Sailor sailorSuper = new Sailor(rest.getInt("SailorID"), rest.getString("Name"), rest.getString("DateOfBirth"), rest.getString("Nationality"));
                    sailors.add(sailorSuper);
                }
                conn.close();
            } else {
                error();
            }
        } catch (Exception e) {
            error();
        }

    }

    /**
     * Add ship to the cruise
     *
     * @param event user operation
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    private void buttonActionAddShip(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        String shipID = shipText.getText();

        try {
            int n = Integer.parseInt(shipID);
            if (n > 0 && n < 4) {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ShipCompany");
                String prepareSQLShip = "SELECT * FROM Ships WHERE ShipID =" + n;
                PreparedStatement pres = conn.prepareCall(prepareSQLShip);
                ResultSet rest = pres.executeQuery();
                while (rest.next()) {
                    this.ship = new Ship(rest.getInt(1), rest.getString(2), rest.getDouble(3), rest.getInt(4), rest.getInt(5));
                }
                conn.close();
            } else {
                error();
            }
        } catch (Exception e) {
            error();
        }

    }

    /**
     * Add return date to the cruise
     *
     * @param event user operation
     * @throws IOException
     */
    @FXML
    private void buttonActionAddReturnDate(ActionEvent event) throws IOException {
        String date = returnDateText.getText();
        if (checkDate(date)) {
            this.returnDate = date;
        } else {
            error();
        }

    }

    /**
     * Add sail date to the cruise
     *
     * @param event user operation
     * @throws IOException
     */
    @FXML
    private void buttonActionAddSailDate(ActionEvent event) throws IOException {
        String date = sailDateText.getText();
        if (checkDate(date)) {
            this.sailDate = date;
        } else {
            error();
        }

    }

    /**
     * Show the ports table
     *
     * @param event user operation
     * @throws IOException
     */
    @FXML
    private void buttonActionViewPort(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Port_FXML.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Show the sailors table
     *
     * @param event user operation
     * @throws IOException
     */
    @FXML
    private void buttonActionViewSailor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Sailor_FXML.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Show the ship table
     *
     * @param event user operation
     * @throws IOException
     */
    @FXML
    private void buttonActionViewShip(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Ship_FXML.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Show the supervisor table
     *
     * @param event user operation
     * @throws IOException
     */
    @FXML
    private void buttonActionViewSupervisor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Supervisor_FXML.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

    }

    /**
     * Add supervisor to the cruise
     *
     * @param event user operation
     * @throws IOException
     */
    @FXML
    private void buttonActionAddCruiseID(ActionEvent event) throws IOException {

        String cruiseID = cruiseIDText.getText();
        try {
            int numCruise = Integer.parseInt(cruiseID);
            if (numCruise > 0) {
                this.numCruise = numCruise;
            } else {
                error();
            }
        } catch (Exception e) {
            error();
        }

    }

    /**
     * Add ports to the cruise
     *
     * @param event user operation
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @FXML
    private void buttonActionAddPort(ActionEvent event) throws ClassNotFoundException, SQLException {
        String portName = portText.getText();
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ShipCompany");
        String prepareSQL = "SELECT * FROM Ports WHERE PortName = ?";
        PreparedStatement pres = conn.prepareCall(prepareSQL);
        pres.setString(1, portName);
        ResultSet rest = pres.executeQuery();
        while (rest.next()) {
            Port port = new Port(rest.getString(1), rest.getString(2), rest.getDouble(3), rest.getBoolean(4), rest.getDouble(5), rest.getBoolean(6));
            ports.add(port);
        }

        conn.close();
    }

    /**
     * Show the passenger table
     *
     * @param event user operation
     * @throws IOException
     */
    @FXML
    private void buttonActionViewPassenger(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Passenger_FXML.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Generate the reports.
     *
     * @param event user operation
     * @throws IOException
     */
    @FXML
    private void buttonActionGenerateReports(ActionEvent event) throws IOException {
        // Not allowed to generate report before create a cruise
        if (cruise == null) {
            error();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("Reports_FXML.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }

    }

    /**
     * Upload the data of the cruise into database
     *
     * @param event user operation
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    private void buttonActionUploadCruise(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        try {
            // if cruise's field do not defined, upload fail, the number of port must larger thab 2.
            if (numCruise == 0 || sailDate == null || returnDate == null || ship == null || ports.size() < 2 || sailors.isEmpty() || pass.isEmpty()) {
                error();
            } else {
                cruise = new Cruise(numCruise, sailDate, returnDate, ship, ports, sailors, pass);
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                // connect the database
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ShipCompany");
                //create a new Statement object
                Statement stat = conn.createStatement();
                // insert the data into the cruise table
                try {
                    stat.executeUpdate("INSERT INTO Cruises (CruiseID, SailDate, ReturnDate,ShipID) VALUES (" + this.numCruise + ", '" + this.sailDate + "', '" + this.returnDate + "', " + this.ship.numShip + ")");
                } catch (SQLIntegrityConstraintViolationException e) {
                    System.out.println("Record Already exists.");
                }
                // insert the data into the RecordPassenger table
                for (Passenger passenger : this.pass) {
                    try {
                        stat.executeUpdate("INSERT INTO RecordPassenger (PassengerID, CruiseID, MoneySpentOnCruise,Rate) VALUES (" + passenger.numPassenger + ", " + this.numCruise + ", " + passenger.money + ", " + passenger.rate + ")");
                    } catch (SQLIntegrityConstraintViolationException e) {
                        System.out.println("Record Already exists.");
                    }
                }
                conn.close();
            }

        } catch (Exception e) {
            error();
        }
    }

    /**
     * Check the format of inputing sail and return date.
     *
     * @param str the input date
     * @return whether detect error
     * @throws java.io.IOException io error
     */
    public boolean checkDate(String str) throws IOException {

        String[] strs = str.split("/");
        // Ensure the right input format
        if (strs.length != 3 || strs[0].length() != 4 || strs[1].length() != 2 || strs[2].length() != 2) {
            System.out.println("Please enter the right date!");
            return false;
        }
        int year = Integer.parseInt(strs[0]);
        int month = Integer.parseInt(strs[1]);
        int day = Integer.parseInt(strs[2]);
        // Ensure the right date
        if (year < 1900 || month < 1 || month > 12 || day < 1 || day > 31) {
            System.out.println("Please enter the right date!");
            return false;
        }
        return true;

    }

    /**
     * error happen, switch to the error window
     *
     * @throws IOException java.io.IOException
     */
    public void error() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Error_FXML.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

}
