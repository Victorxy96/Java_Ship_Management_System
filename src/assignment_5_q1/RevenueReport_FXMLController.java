/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_5_q1;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

/**
 * RevenueReport_FXMLController class control the RevenueReport_FXML UI which
 * generate the revenue report that anaylzing the passengers' total money spent
 * based on their cruise experience. It contains the initialize method to load
 * the the pie chart that analyzing based on passengers' nation and load the bar
 * chart that analyzing based on passengers' age.
 *
 * @author xy
 */
public class RevenueReport_FXMLController implements Initializable {

    @FXML
    private Pane paneReportNation;
    @FXML
    private Pane paneReportAge;

    /**
     * Initializes the controller class. This method load the the pie chart that analyzing
     * based on passengers' nation and load the bar chart that analyzing based
     * on passengers' age.
     *
     * @param url url
     * @param rb resource
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDataAge();
        loadDataNation();
    }

    /**
     * Load the the pie chart that analyzing based on passengers' nation
     */
    private void loadDataAge() {
        // Create ArrayList to save nations
        ArrayList<Integer> ages = new ArrayList<>();
        // add ages
        for (Passenger p : Cruise_FXMLController.cruise.pass) {
            if (ages.indexOf(p.age) == -1) {
                ages.add(p.age);
            }
        }

        //save the revenue
        double[] revenueAge = new double[ages.size()];
        int i = 0;
        while (i < revenueAge.length) {
            for (Integer age : ages) {
                for (Passenger p : Cruise_FXMLController.cruise.pass) {
                    if (p.age == age) {
                        revenueAge[i] += p.money;
                    }
                }
                i++;
            }
        }

        // create bar chart report
        paneReportAge.getChildren().clear();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Age");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Revenue");
        BarChart revenueAgeChart = new BarChart(xAxis, yAxis);
        revenueAgeChart.setTitle("Revenue Report Based on Passenger's age");
        XYChart.Series series = new XYChart.Series();
        series.setName("Money the passenger spent");

        i = 0;
        for (Integer age : ages) {
            series.getData().add(new XYChart.Data<>(String.valueOf(age), revenueAge[i]));
            System.out.println(age + "   " + revenueAge[i]);
            i++;
        }

        revenueAgeChart.getData().add(series);
        paneReportAge.getChildren().add(revenueAgeChart);
    }

    /**
     * Load the bar chart that analyzing based on passengers' age.
     */
    private void loadDataNation() {
        // Create ArrayList to save nations
        ArrayList<String> nations = new ArrayList<>();
        // add nations
        for (Passenger p : Cruise_FXMLController.cruise.pass) {
            if (nations.indexOf(p.nationPassenger) == -1) {
                nations.add(p.nationPassenger);
            }
        }
        //save the revenue
        double[] revenueNation = new double[nations.size()];
        int i = 0;
        while (i < revenueNation.length) {
            for (String nation : nations) {
                for (Passenger p : Cruise_FXMLController.cruise.pass) {
                    if ((p.nationPassenger).equals(nation)) {
                        revenueNation[i] += p.money;
                    }
                }
                i++;
            }
        }

        // Create Pie Chart
        paneReportNation.getChildren().clear();
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        i = 0;
        for (String n : nations) {
            list.add(new PieChart.Data(n, revenueNation[i]));
            i++;
        }
        PieChart revenueReportNationChart = new PieChart(list);
        revenueReportNationChart.setTitle("Revenue Report Based on Passenger's Nation");
        paneReportNation.getChildren().add(revenueReportNationChart);
    }

}
