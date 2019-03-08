/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_5_q1;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Passenger_FXMLController class control the Passenger_FXML UI which show all
 * the passenger information in database. The method Initialize the passenger
 * table and load passenger data.
 *
 * @author xy
 */
public class Passenger_FXMLController implements Initializable {

    /**
     * listPass saved the data of passenger in the data base
     */
    ObservableList<PassengerTable> listPass = FXCollections.observableArrayList();
    @FXML
    private TableView<PassengerTable> passengerView;
    @FXML
    private TableColumn<PassengerTable, String> passName;
    @FXML
    private TableColumn<PassengerTable, String> passID;

    /**
     * Initializes the controller class. The method Initialize the passenger
     * table and load passenger data in the database.
     *
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateCols();
        try {
            loadDataPassenger();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Passenger_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Passenger_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Load the data of passengers from the data base.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void loadDataPassenger() throws ClassNotFoundException, SQLException {
        listPass.removeAll(listPass);
        // load the database
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        // connect the database
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ShipCompany");
        String prepareSQLSuper = "SELECT * FROM Passengers";
        PreparedStatement pres = conn.prepareCall(prepareSQLSuper);
        ResultSet rest = pres.executeQuery();
        while (rest.next()) {
            listPass.addAll(new PassengerTable(rest.getString(1), rest.getString(2)));
        }
        passengerView.getItems().addAll(listPass);
    }

    /**
     * Initiate the passenger table
     */
    private void initiateCols() {
        passName.setCellValueFactory(new PropertyValueFactory<>("namePass"));
        passID.setCellValueFactory(new PropertyValueFactory<>("numPass"));
    }

    /**
     *
     * Inner class Table used to save the data required to create the passenger
     * table.
     */
    public static class PassengerTable {

        private final SimpleStringProperty namePass;
        private final SimpleStringProperty numPass;

        /**
         * PassengerTable constructor method.
         *
         * @param numPass ID of passenger
         * @param namePass name of passenger
         */
        public PassengerTable(String numPass, String namePass) {
            this.namePass = new SimpleStringProperty(namePass);
            this.numPass = new SimpleStringProperty(numPass);
        }

        /**
         * Get name of passenger
         *
         * @return name of passenger
         */
        public String getNamePass() {
            return namePass.get();
        }

        /**
         * Get ID of passenger
         *
         * @return ID of passenger
         */
        public String getNumPass() {
            return numPass.get();
        }

    }

}
