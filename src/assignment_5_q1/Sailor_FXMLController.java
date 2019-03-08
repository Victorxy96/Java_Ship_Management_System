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
 * Sailor_FXMLController class control the Sailor_FXML UI which show all the
 * sailor information in database. The method Initialize the sailor table and
 * load sailor data.
 *
 * @author xy
 */
public class Sailor_FXMLController implements Initializable {

    /**
     * listSailor saved the data of sailors
     */
    ObservableList<SailorTable> listSailor = FXCollections.observableArrayList();
    @FXML
    private TableView<SailorTable> sailorView;
    @FXML
    private TableColumn<SailorTable, String> sailorName;
    @FXML
    private TableColumn<SailorTable, String> sailorID;
    @FXML
    private TableColumn<SailorTable, String> sailorSuper;

    /**
     * Initializes the controller class. This method Initialize the sailor table and load sailors data
     *
     * @param url url
     * @param rb resource
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateCols();
        try {
            loadDataSailor();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sailor_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Sailor_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Load the data of all sailors whose position is sailor from the data base.
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    private void loadDataSailor() throws ClassNotFoundException, SQLException {
        listSailor.removeAll(listSailor);
        // load the database
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        // connect the database
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ShipCompany");
        String prepareSQLSuper = "SELECT * FROM Sailors WHERE Position = 'Sailor'";
        PreparedStatement pres = conn.prepareCall(prepareSQLSuper);
        ResultSet rest = pres.executeQuery();
        while (rest.next()) {
            listSailor.addAll(new SailorTable(rest.getString(1), rest.getString(2), rest.getString(5)));
        }
        sailorView.getItems().addAll(listSailor);
    }

    /**
     * Initiate the sailor table
     */
    private void initiateCols() {
        sailorName.setCellValueFactory(new PropertyValueFactory<>("nameSailor"));
        sailorID.setCellValueFactory(new PropertyValueFactory<>("numSailor"));
        sailorSuper.setCellValueFactory(new PropertyValueFactory<>("superSailor"));
    }

    /**
     * Inner class SailorTable used to save the data required to create the sailor table.
     */
    public static class SailorTable {

        private final SimpleStringProperty nameSailor;
        private final SimpleStringProperty numSailor;
        private final SimpleStringProperty superSailor;

        /**
         * SailorTable constructor method
         * @param numSailor ID of sailor
         * @param nameSailor name of sailor
         * @param superSailor supervisor of sailor
         */
        public SailorTable(String numSailor, String nameSailor, String superSailor) {
            this.nameSailor = new SimpleStringProperty(nameSailor);
            this.numSailor = new SimpleStringProperty(numSailor);
            this.superSailor = new SimpleStringProperty(superSailor);
        }

        /**
         * Get name of sailor
         * @return name of sailor
         */
        public String getNameSailor() {
            return nameSailor.get();
        }

        /**
         * Get ID of sailor
         * @return ID of sailor
         */
        public String getNumSailor() {
            return numSailor.get();
        }

        /**
         * Get supervisor of sailor
         * @return supervisor of sailor
         */
        public String getSuperSailor() {
            return superSailor.get();
        }

    }
}
