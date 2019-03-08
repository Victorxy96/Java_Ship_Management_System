/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_5_q1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Launch Class contains the method start to start the Main UI.
 * @author xy
 */
public class Launch extends Application {
    /**
     * Start the Main UI to start the system.
     * @param stage stage enter
     * @throws Exception exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
