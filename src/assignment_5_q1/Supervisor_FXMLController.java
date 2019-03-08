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
 * Supervisor_FXMLController class control the Supervisor_FXML UI which show all
 * the supervisor information in database. The method Initialize the supervisor
 * table and load supervisor data.
 *
 *
 * @author xy
 */
public class Supervisor_FXMLController implements Initializable {

    /**
     * listSupervisor saved the data of supervisors
     */
    ObservableList<Supervisor> listSupervisor = FXCollections.observableArrayList();
    @FXML
    private TableView<Supervisor> supervisorView;
    @FXML
    private TableColumn<Supervisor, String> sailorName;
    @FXML
    private TableColumn<Supervisor, String> sailorID;

    /**
     * Initializes the controller class. This method Initialize the supervisor
     * table and load supervisor data
     *
     * @param url url
     * @param rb resource
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateCols();
        try {
            loadDataSupervisor();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Supervisor_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Supervisor_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Load the data of all sailors whose position is supervisor from the data
     * base.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void loadDataSupervisor() throws ClassNotFoundException, SQLException {
        listSupervisor.removeAll(listSupervisor);
        // load the database
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        // connect the database
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ShipCompany");
        String prepareSQLSuper = "SELECT * FROM Sailors WHERE Supervisor = 'N/A'";
        PreparedStatement pres = conn.prepareCall(prepareSQLSuper);
        ResultSet rest = pres.executeQuery();
        while (rest.next()) {
            listSupervisor.addAll(new Supervisor(rest.getString(1), rest.getString(2)));
        }
        supervisorView.getItems().addAll(listSupervisor);
    }

    /**
     * Initiate the supervisor table
     */
    private void initiateCols() {
        sailorName.setCellValueFactory(new PropertyValueFactory<>("nameSupervisor"));
        sailorID.setCellValueFactory(new PropertyValueFactory<>("numSupervisor"));
    }

    /**
     * Inner class Supervisor used to save the data required to create the
     * supervisor table.
     */
    public static class Supervisor {

        private final SimpleStringProperty nameSupervisor;
        private final SimpleStringProperty numSupervisor;

        /**
         * Supervisor constructor method
         *
         * @param numSupervisor ID of supervisor
         * @param nameSupervisor name of supervisor
         */
        public Supervisor(String numSupervisor, String nameSupervisor) {
            this.nameSupervisor = new SimpleStringProperty(nameSupervisor);
            this.numSupervisor = new SimpleStringProperty(numSupervisor);
        }

        /**
         * Get name of supervisor
         *
         * @return name of supervisor
         */
        public String getNameSupervisor() {
            return nameSupervisor.get();
        }

        /**
         * Get ID of supervisor
         *
         * @return ID of supervisor
         */
        public String getNumSupervisor() {
            return numSupervisor.get();
        }

    }
}
