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
 * SailorReport_FXMLController class control the SailorReport_FXML UI which
 * generate the sailor report that shows the supervisor and their sailors of
 * this cruise. It contains the initialize method to initialize the sailor
 * report table and load sailor data.
 *
 * @author xy
 */
public class SailorReport_FXMLController implements Initializable {

    /**
     * listSailorReport saved the data of sailors and supervisor for the report
     */
    ObservableList<SailorReport> listSailorReport = FXCollections.observableArrayList();
    @FXML
    private TableColumn<SailorReport, String> sailorID;
    @FXML
    private TableColumn<SailorReport, String> sailorName;
    @FXML
    private TableColumn<SailorReport, String> sailorDOB;
    @FXML
    private TableColumn<SailorReport, String> sailorNation;
    @FXML
    private TableColumn<SailorReport, String> sailorPosition;
    @FXML
    private TableColumn<SailorReport, String> sailorSuper;
    @FXML
    private TableView<SailorReport> sailorReportView;

    /**
     * Initializes the controller class. This method Initialize the sailor
     * report table and load sailor and supervisor data for this cruise.
     *
     * @param url url
     * @param rb resource
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateCols();
        try {
            loadDataSailorReport();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SailorReport_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SailorReport_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initiate the sailor report table
     */
    private void initiateCols() {
        sailorID.setCellValueFactory(new PropertyValueFactory<>("numSailor"));
        sailorName.setCellValueFactory(new PropertyValueFactory<>("nameSailor"));
        sailorDOB.setCellValueFactory(new PropertyValueFactory<>("dobSailor"));
        sailorNation.setCellValueFactory(new PropertyValueFactory<>("nationSailor"));
        sailorPosition.setCellValueFactory(new PropertyValueFactory<>("positionSailor"));
        sailorSuper.setCellValueFactory(new PropertyValueFactory<>("superSailor"));
    }

    /**
     * Load the data of sailors and supervisor for this cruise.
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    private void loadDataSailorReport() throws ClassNotFoundException, SQLException {
        listSailorReport.removeAll(listSailorReport);
        // load the database
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
// connect the database
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ShipCompany");

        for (Sailor s : Cruise_FXMLController.cruise.sailors) {
            listSailorReport.addAll(new SailorReport(String.valueOf(s.numSailor), s.nameSailor, s.dobSailor, s.nationSailor, s.position, s.supervisor));
        }
        sailorReportView.getItems().addAll(listSailorReport);
    }

    /**
     * Inner class SailorReport used to save the data required to create the sailor report.
     */
    public static class SailorReport {

        private final SimpleStringProperty numSailor;
        private final SimpleStringProperty nameSailor;
        private final SimpleStringProperty dobSailor;
        private final SimpleStringProperty nationSailor;
        private final SimpleStringProperty positionSailor;
        private final SimpleStringProperty superSailor;

        /**
         * SailorReport constructor method.
         * @param numSailor ID of Sailor
         * @param nameSailor name of Sailor
         * @param dobSailor date of birth of Sailor
         * @param nationSailor nation of Sailor
         * @param positionSailor position of Sailor
         * @param superSailor supervisor of Sailor
         */
        public SailorReport(String numSailor, String nameSailor, String dobSailor, String nationSailor, String positionSailor, String superSailor) {
            this.numSailor = new SimpleStringProperty(numSailor);
            this.nameSailor = new SimpleStringProperty(nameSailor);
            this.dobSailor = new SimpleStringProperty(dobSailor);
            this.nationSailor = new SimpleStringProperty(nationSailor);
            this.positionSailor = new SimpleStringProperty(positionSailor);
            this.superSailor = new SimpleStringProperty(superSailor);
        }

        /**
         * Get ID of Sailor
         * @return ID of Sailor
         */
        public String getNumSailor() {
            return numSailor.get();
        }

        /**
         * Get name of Sailor
         * @return name of Sailor
         */
        public String getNameSailor() {
            return nameSailor.get();
        }

        /**
         * Get date of birth of Sailor
         * @return date of birth of Sailor
         */
        public String getDobSailor() {
            return dobSailor.get();
        }

        /**
         * Get nation of Sailor
         * @return nation of Sailor
         */
        public String getNationSailor() {
            return nationSailor.get();
        }

        /**
         * Get position of Sailor
         * @return position of Sailor
         */
        public String getPositionSailor() {
            return positionSailor.get();
        }

        /**
         * Get supervisor of Sailor
         * @return supervisor of Sailor
         */
        public String getSuperSailor() {
            return superSailor.get();
        }

    }

}
