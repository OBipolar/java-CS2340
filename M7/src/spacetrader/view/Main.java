/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.view;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author zixiangzhu
 */
public class Main extends Application {

    public static String screen1ID = "welcome";
    public static String screen1File = "WelcomeScreen.fxml";
    public static String screen2ID = "configure";
    public static String screen2File = "ConfigurePage.fxml";
    public static String screen3ID = "gamePage";
    public static String screen3File = "GameScreen.fxml";
	private Group root;
	private Scene scene;
	public ScreensController mainContainer;
	private ObservableList<Node> children;

    @Override
    public void start(Stage primaryStage) throws Exception {

        mainContainer = new ScreensController();
        mainContainer.loadScreen(Main.screen1ID, Main.screen1File);
        mainContainer.loadScreen(Main.screen2ID, Main.screen2File);
        mainContainer.loadScreen(Main.screen3ID, Main.screen3File);
//        if (mainContainer.getScreen("configure") == null) {
//            System.out.println("error");
//        } else {
//            System.out.println("ok");
//        }

        mainContainer.setScreen(Main.screen1ID);
        root = new Group();
        children = root.getChildren();
        children.addAll(mainContainer);
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args
     * the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
