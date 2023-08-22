/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Separator;


/**
 *
 * @author David
 */
public class Deadwood extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader introScreenLoad = new FXMLLoader(getClass().getResource("Introduction.fxml"));
        Parent intro = introScreenLoad.load();
        Scene introScene = new Scene(intro);
        
        FXMLLoader characterCreationScreenLoad = new FXMLLoader(getClass().getResource("characterCreation.fxml"));
        Parent characterCreation = characterCreationScreenLoad.load();
        Scene characterCreationScene = new Scene(characterCreation);
        
        /*FXMLLoader boardSceneLoad = new FXMLLoader(getClass().getResource("Board.fxml"));
        Parent board = boardSceneLoad.load();
        Scene boardScene = new Scene(board); */
        
        IntroductionController introController = (IntroductionController) introScreenLoad.getController();
        introController.setNextScene(characterCreationScene);
        
        /*CharacterCreationController cCController = 
                (CharacterCreationController) characterCreationScreenLoad.getController();
        cCController.setNextScene(boardScene); */
        
        stage.setTitle("Deadwood");
        stage.setScene(introScene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
