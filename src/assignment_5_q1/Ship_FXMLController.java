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
 * Ship_FXMLController class control the Ship_FXML UI which show all the ship
 * information in database. The method Initialize the ship table and load ship
 * data.
 *
 * @author xy
 */
public class Ship_FXMLController implements Initializable {

    /**
     * listShip saved the data of ships
     */
    ObservableList<Ship> listShip = FXCollections.observableArrayList();
    @FXML
    private TableView<Ship> shipView;
    @FXML
    private TableColumn<Ship, String> shipName;
    @FXML
    private TableColumn<Ship, String> shipID;

    /**
     * Initializes the controller class. This method Initialize the ship table
     * and load ships data
     *
     * @param url url
     * @param rb resource
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateCols();
        try {
            loadDataShip();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ship_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Ship_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initiate the ship table
     */
    private void initiateCols() {
        shipName.setCellValueFactory(new PropertyValueFactory<>("nameShip"));
        shipID.setCellValueFactory(new PropertyValueFactory<>("numShip"));
    }

    /**
     * Load the data of ships from the data base.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void loadDataShip() throws ClassNotFoundException, SQLException {
        listShip.removeAll(listShip);
        // load the database
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
// connect the database
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ShipCompany");

        String prepareSQLShip = "SELECT * FROM Ships ";

        PreparedStatement pres = conn.prepareCall(prepareSQLShip);
        ResultSet rest = pres.executeQuery();

        while (rest.next()) {
            listShip.addAll(new Ship(rest.getString(1), rest.getString(2)));
        }
        shipView.getItems().addAll(listShip);
    }

    /**
     * Inner class Ship used to save the data required to create the ship table.
     */
    public static class Ship {

        private final SimpleStringProperty numShip;
        private final SimpleStringProperty nameShip;

        /**
         * Ship constructor method
         *
         * @param numShip ID of ship
         * @param nameShip name of ship
         */
        public Ship(String numShip, String nameShip) {
            this.nameShip = new SimpleStringProperty(nameShip);
            this.numShip = new SimpleStringProperty(numShip);
        }

        /**
         * Get ID of ship
         *
         * @return ID of ship
         */
        public String getNumShip() {
            return numShip.get();
        }

        /**
         * Get name of ship
         *
         * @return name of ship
         */
        public String getNameShip() {
            return nameShip.get();
        }

    }

}
