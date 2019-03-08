/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_5_q1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Reports_FXMLController class control the Reports_FXML UI which let the user
 * check the revenue, sailor, passenger and evaluation report. It contains the
 * buttons method to show the corresponding report when user click the button.
 *
 * @author xy
 */
public class Reports_FXMLController implements Initializable {

    @FXML
    private Button revenueSwitch;
    @FXML
    private Button sailorSwitch;
    @FXML
    private Button passengerSwitch;
    @FXML
    private Button evaluationSwitch;

    /**
     * Initializes the controller class.
     *
     * @param url url
     * @param rb resource
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Show the revenue report
     *
     * @param event user operation
     * @throws IOException
     */
    @FXML
    private void buttonActionRevenueSwitch(ActionEvent event) throws IOException {
        // Show the revenue report based on nation
        javafx.scene.Parent rootNation = FXMLLoader.load(getClass().getResource("RevenueReport_FXML.fxml"));
        Stage stageNation = new Stage();
        stageNation.setScene(new Scene(rootNation));
        stageNation.setResizable(false);
        stageNation.show();

    }

    /**
     * Show the sailor report
     *
     * @param event user operation
     * @throws IOException
     */
    @FXML
    private void buttonActionSailorSwitch(ActionEvent event) throws IOException {
        javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("SailorReport_FXML.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Show the passenger report
     *
     * @param event user operation
     * @throws IOException
     */
    @FXML
    private void buttonActionPassengerSwitch(ActionEvent event) throws IOException {
        javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("PassengerReport_FXML.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Show the evaluation report
     *
     * @param event user operation
     * @throws IOException
     */
    @FXML
    private void buttonActionEvaluationSwitch(ActionEvent event) throws IOException {
        javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("Evaluation_FXML.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

}
