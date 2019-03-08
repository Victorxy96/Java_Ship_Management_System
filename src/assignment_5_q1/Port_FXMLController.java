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
 * Port_FXMLController class control the Port_FXML UI which show all the port
 * information in database. The method Initialize the port table and load port
 * data.
 *
 * @author xy
 */
public class Port_FXMLController implements Initializable {

    /**
     * listPort saved the data of ports
     */
    ObservableList<PortTable> listPort = FXCollections.observableArrayList();
    @FXML
    private TableView<PortTable> portView;
    @FXML
    private TableColumn<PortTable, String> portName;
    @FXML
    private TableColumn<PortTable, String> portNation;

    /**
     * Initializes the controller class. This method Initialize the port table
     * and load port data.
     *
     * @param url url
     * @param rb resource
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateCols();
        try {
            loadDataPort();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Port_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Port_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Load the data of ports from the data base.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void loadDataPort() throws ClassNotFoundException, SQLException {
        listPort.removeAll(listPort);
        // load the database
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        // connect the database
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ShipCompany");
        String prepareSQL = "SELECT * FROM Ports";
        PreparedStatement pres = conn.prepareCall(prepareSQL);
        ResultSet rest = pres.executeQuery();
        while (rest.next()) {
            listPort.addAll(new PortTable(rest.getString(1), rest.getString(2)));
        }
        portView.getItems().addAll(listPort);
    }

    /**
     * Initiate the ports table
     */
    private void initiateCols() {
        portName.setCellValueFactory(new PropertyValueFactory<>("namePort"));
        portNation.setCellValueFactory(new PropertyValueFactory<>("nationPort"));
    }

    /**
     * Inner class PortTable used to save the data required to create the port
     * table.
     */
    public static class PortTable {

        private final SimpleStringProperty namePort;
        private final SimpleStringProperty nationPort;

        /**
         * PortTable Constructor method
         *
         * @param namePort name of port
         * @param nationPort nation of port
         */
        public PortTable(String namePort, String nationPort) {
            this.namePort = new SimpleStringProperty(namePort);
            this.nationPort = new SimpleStringProperty(nationPort);
        }

        /**
         * Get name of port
         *
         * @return name of port
         */
        public String getNamePort() {
            return namePort.get();
        }

        /**
         * Get nation of port
         *
         * @return nation of port
         */
        public String getNationPort() {
            return nationPort.get();
        }

    }

}
