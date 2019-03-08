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
 * PassengerReport_FXMLController class control the PassengerReport_FXML UI
 * which generate the passenger report that anaylzing the passengers' total
 * money spent based on their cruise experience. It contains the initialize
 * method to initialize the passenger report table and load passenger data.
 *
 * @author xy
 */
public class PassengerReport_FXMLController implements Initializable {
    /**
     *  listPassengerReport saved the data of passenger for the report
     */
    ObservableList<PassengerReport> listPassengerReport = FXCollections.observableArrayList();
    @FXML
    private TableView<PassengerReport> passengerReportView;
    @FXML
    private TableColumn<PassengerReport, String> passID;
    @FXML
    private TableColumn<PassengerReport, String> passName;
    @FXML
    private TableColumn<PassengerReport, String> passAddress;
    @FXML
    private TableColumn<PassengerReport, String> passNation;
    @FXML
    private TableColumn<PassengerReport, String> passMoneyAll;
    @FXML
    private TableColumn<PassengerReport, String> passDob;

    /**
     * Initializes the controller class. This method Initialize the passenger
     * report table and load passenger data
     *
     * @param url url
     * @param rb resource
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateCols();
        try {
            loadDataPassenger();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PassengerReport_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PassengerReport_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initiate the passenger report table
     */
    private void initiateCols() {
        passID.setCellValueFactory(new PropertyValueFactory<>("numPass"));
        passName.setCellValueFactory(new PropertyValueFactory<>("namePass"));
        passAddress.setCellValueFactory(new PropertyValueFactory<>("addPass"));
        passNation.setCellValueFactory(new PropertyValueFactory<>("nationPass"));
        passMoneyAll.setCellValueFactory(new PropertyValueFactory<>("moneyPass"));
        passDob.setCellValueFactory(new PropertyValueFactory<>("dobPass"));
    }

    /**
     * Load the data of passengers from the data base.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void loadDataPassenger() throws ClassNotFoundException, SQLException {
        listPassengerReport.removeAll(listPassengerReport);
        // Create passTemp ArrayList for saving the information of the passengers with their total money. Then using it for sorting.
        ArrayList<Passenger> passTemp = new ArrayList<>();
        // load
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        // connect the database
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ShipCompany");
        //create a new Statement object
        Statement stat = conn.createStatement();
        double[] moneySpentOnCruise = new double[Cruise_FXMLController.cruise.pass.size()];
        //acquire passengers' total amount of the money that cacluating by the passenger's former spent money and money for this curise. Save the data in the moneySpentOnCruise array.
        int countPass = 0;
        for (Passenger p : Cruise_FXMLController.cruise.pass) {
            String prepareSQL = "SELECT * FROM RecordPassenger WHERE PassengerID = ?";
            PreparedStatement pres = conn.prepareCall(prepareSQL);
            pres.setString(1, String.valueOf(p.numPassenger));
            ResultSet rest = pres.executeQuery();
            double sum = 0;
            double tmp = 0;
            while (rest.next()) {
                tmp = Double.parseDouble(rest.getString(3));
                sum += tmp;
            }
            moneySpentOnCruise[countPass] += sum;
            countPass++;
        }

        // create new passengers with the infomation of total money for sorting, and add these passengers to the passTemp
        int countPassTemp = 0;

        for (Passenger p : Cruise_FXMLController.cruise.pass) {
            Passenger pTemp = new Passenger(p.numPassenger, p.namePassenger, p.homePassenger, p.nationPassenger, p.dobPasseger, moneySpentOnCruise[countPassTemp]);
            passTemp.add(pTemp);
            countPassTemp++;

        }

//Sorting the passTemp based on the total money with comparator
        Collections.sort(passTemp, new Comparator<Passenger>() {
            @Override
            public int compare(Passenger o1, Passenger o2) {
                if (o1.money < o2.money) {
                    return 1;
                } else if (o1.money > o2.money) {
                    return -1;
                }
                return 0;
            }
        });
        // control the double format
        DecimalFormat df = new DecimalFormat("#.00");
        // add to table
        for (Passenger p : passTemp) {
            listPassengerReport.addAll(new PassengerReport(String.valueOf(p.numPassenger), p.namePassenger, p.homePassenger, p.nationPassenger, p.dobPasseger, String.valueOf(df.format(p.money))));
        }
        conn.close();

        passengerReportView.getItems().addAll(listPassengerReport);
    }

    /**
     * Inner class PassengerReport used to save the data required to create the
     * passenger report.
     */
    public static class PassengerReport {

        private final SimpleStringProperty numPass;
        private final SimpleStringProperty namePass;
        private final SimpleStringProperty addPass;
        private final SimpleStringProperty nationPass;
        private final SimpleStringProperty dobPass;
        private final SimpleStringProperty moneyPass;

        /**
         * PassengerReport constructor method.
         *
         * @param numPass ID of passenger
         * @param namePass name of passenger
         * @param addPass address of passenger
         * @param nationPass nation of passenger
         * @param dobPass date of birth of passenger
         * @param moneyPass total money spent of passenger
         */
        public PassengerReport(String numPass, String namePass, String addPass, String nationPass, String dobPass, String moneyPass) {
            this.numPass = new SimpleStringProperty(numPass);
            this.namePass = new SimpleStringProperty(namePass);
            this.addPass = new SimpleStringProperty(addPass);
            this.nationPass = new SimpleStringProperty(nationPass);
            this.dobPass = new SimpleStringProperty(dobPass);
            this.moneyPass = new SimpleStringProperty(moneyPass);
        }

        /**
         * Get ID of passenger
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
         * Get total money of passenger
         *
         * @return total money of passenger
         */
        public String getMoneyPass() {
            return moneyPass.get();
        }

    }

}
