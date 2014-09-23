/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author zixiangzhu
 */
public class JavaFXApplication6 extends Application {
    
    public static String screen1ID = "welcome";
    public static String screen1File = "FXMLDocument.fxml";
    public static String screen2ID = "configure";
    public static String screen2File = "configurePage.fxml";
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(JavaFXApplication6.screen1ID, JavaFXApplication6.screen1File);
        mainContainer.loadScreen(JavaFXApplication6.screen2ID, JavaFXApplication6.screen2File);
        if (mainContainer.getScreen("configure") == null) {
            System.out.println("error");
        } else {
            System.out.println("ok");
        }
        
        mainContainer.setScreen(JavaFXApplication6.screen1ID);
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
