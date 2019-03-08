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
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
 * Evaluation_FXMLController class control the Evaluation_FXML UI which generate
 * the evaluation report that anaylzing the passengers' rating of their cruise
 * experience. It contains the initialize method to initialize the evaluation
 * table and load passenger data.
 *
 * @author xy
 */
public class Evaluation_FXMLController implements Initializable {
    /**
     * listEvaluationReport saved the data of evaluation report
     */
    ObservableList<EvaluationReport> listEvaluationReport = FXCollections.observableArrayList();
    @FXML
    private TableView<EvaluationReport> evaluationReportView;
    @FXML
    private TableColumn<EvaluationReport, String> passID;
    @FXML
    private TableColumn<EvaluationReport, String> passName;
    @FXML
    private TableColumn<EvaluationReport, String> passAddress;
    @FXML
    private TableColumn<EvaluationReport, String> passNation;
    @FXML
    private TableColumn<EvaluationReport, String> passDob;
    @FXML
    private TableColumn<EvaluationReport, String> passRatingAll;

    /**
     * Initializes the controller class. This method Initialize the evaluation
     * table and load passenger data
     *
     * @param url url
     * @param rb source
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateCols();
        try {
            loadDataPassenger();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Evaluation_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Evaluation_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initiate the evaluation table
     */
    private void initiateCols() {
        passID.setCellValueFactory(new PropertyValueFactory<>("numPass"));
        passName.setCellValueFactory(new PropertyValueFactory<>("namePass"));
        passAddress.setCellValueFactory(new PropertyValueFactory<>("addPass"));
        passNation.setCellValueFactory(new PropertyValueFactory<>("nationPass"));
        passRatingAll.setCellValueFactory(new PropertyValueFactory<>("ratingPass"));
        passDob.setCellValueFactory(new PropertyValueFactory<>("dobPass"));
    }

    /**
     * Load the data of passengers from the data base.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void loadDataPassenger() throws ClassNotFoundException, SQLException {
        listEvaluationReport.removeAll(listEvaluationReport);
        // load
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        // connect the database
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ShipCompany");
        //create a new Statement object
        Statement stat = conn.createStatement();
        double[] ratings = new double[Cruise_FXMLController.cruise.pass.size()];
        //acquire passengers' rating that cacluating by the average rating of their cruise(former and this cruise). Save the data in the ratings array.
        int countPass = 0;
        for (Passenger p : Cruise_FXMLController.cruise.pass) {
            String prepareSQLRatingAll = "SELECT * FROM RecordPassenger WHERE PassengerID = ?";
            String prepareSQLRatingNum = "SELECT COUNT(*) FROM RecordPassenger WHERE PassengerID = ?";
            PreparedStatement presRatingAll = conn.prepareCall(prepareSQLRatingAll);
            PreparedStatement presRatingNum = conn.prepareCall(prepareSQLRatingNum);
            presRatingAll.setString(1, String.valueOf(p.numPassenger));
            presRatingNum.setString(1, String.valueOf(p.numPassenger));
            ResultSet restRatingAll = presRatingAll.executeQuery();
            double sum = 0;
            double tmp = 0;
            int num = 0;
            // acquire the total rating
            while (restRatingAll.next()) {
                tmp = Double.parseDouble(restRatingAll.getString(4));
                sum += tmp;
            }
            // acquire the how many cruise the passenger attend
            ResultSet restRatingNum = presRatingNum.executeQuery();
            while (restRatingNum.next()) {
                num = Integer.parseInt(restRatingNum.getString(1));

            }
            // calculating the average rating 
            ratings[countPass] += sum / num;
            countPass++;
        }

        countPass = 0;
        // control the double format
        DecimalFormat df = new DecimalFormat("#.00");
        // add to table
        for (Passenger p : Cruise_FXMLController.cruise.pass) {
            listEvaluationReport.addAll(new EvaluationReport(String.valueOf(p.numPassenger), p.namePassenger, p.homePassenger, p.nationPassenger, p.dobPasseger, String.valueOf(df.format(ratings[countPass++]))));
        }
        conn.close();

        evaluationReportView.getItems().addAll(listEvaluationReport);
    }

    /**
     * Inner class EvaulationReport used to save the data required to create the
     * evaluation report.
     */
    public static class EvaluationReport {

        private final SimpleStringProperty numPass;
        private final SimpleStringProperty namePass;
        private final SimpleStringProperty addPass;
        private final SimpleStringProperty nationPass;
        private final SimpleStringProperty dobPass;
        private final SimpleStringProperty ratingPass;

        /**
         * EvaulationReport constructor method
         *
         * @param numPass ID of passenger
         * @param namePass name of passenger
         * @param addPass address of passenger
         * @param nationPass nation of passenger
         * @param dobPass date of birth of passenger
         * @param ratingPass rating or passenger
         */
        public EvaluationReport(String numPass, String namePass, String addPass, String nationPass, String dobPass, String ratingPass) {
            this.numPass = new SimpleStringProperty(numPass);
            this.namePass = new SimpleStringProperty(namePass);
            this.addPass = new SimpleStringProperty(addPass);
            this.nationPass = new SimpleStringProperty(nationPass);
            this.dobPass = new SimpleStringProperty(dobPass);
            this.ratingPass = new SimpleStringProperty(ratingPass);
        }

        /**
         * Get the ID of passenger
         *
         * @return ID of passenger
         */
        public String getNumPass() {
            return numPass.get();
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
         * Get address of passenger
         *
         * @return address of passenger
         */
        public String getAddPass() {
            return addPass.get();
        }

        /**
         * Get nation of passenger
         *
         * @return nation of passenger
         */
        public String getNationPass() {
            return nationPass.get();
        }

        /**
         * Get date of birth of passenger
         *
         * @return date of birth of passenger
         */
        public String getDobPass() {
            return dobPass.get();
        }

        /**
         * Get rating of passenger
         *
         * @return Rating of passenger
         */
        public String getRatingPass() {
            return ratingPass.get();
        }

    }

}
