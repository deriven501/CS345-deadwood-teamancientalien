/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 *
 * @author David
 */
public class IntroductionController implements Initializable {
    
    private Scene cCScene;
    
    public void setNextScene(Scene scene) {
        cCScene = scene;
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Stage characterCreation = (Stage)((Node) event.getSource()).getScene().getWindow();
        characterCreation.setScene(cCScene);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
