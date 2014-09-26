/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication6;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.converter.IntegerStringConverter;
/**
 * FXML Controller class
 *
 * @author Shaohui Xu
 */
public class GameScreenController implements Initializable, ControlledScreen {

    @FXML
    private TitledPane galaChart;

    @FXML
    private TitledPane srChart;
    
    @FXML
    private TitledPane systemInfo;
    
    @FXML
    private TitledPane targetInfo;
    
    @FXML
    private AnchorPane shipyard;
    
    @FXML
    private AnchorPane dock;
    
    @FXML
    private AnchorPane cargoChart;
    
    @Override
    public void setScreenParent(ScreensController screenParent) {}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
}