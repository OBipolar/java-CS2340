/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package m4_demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author zixiangzhu
 */
public class M4_Demo extends Application {
    
    public static String screen1ID = "welcome";
    public static String screen1File = "WelcomeScreen.fxml";
    public static String screen2ID = "configure";
    public static String screen2File = "ConfigurePage.fxml";
    public static String screen3ID = "gamePage";
    public static String screen3File = "GameScreen.fxml";
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(M4_Demo.screen1ID, M4_Demo.screen1File);
        mainContainer.loadScreen(M4_Demo.screen2ID, M4_Demo.screen2File);
        mainContainer.loadScreen(M4_Demo.screen3ID, M4_Demo.screen3File);
        if (mainContainer.getScreen("configure") == null) {
            System.out.println("error");
        } else {
            System.out.println("ok");
        }
        
        mainContainer.setScreen(M4_Demo.screen1ID);
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
