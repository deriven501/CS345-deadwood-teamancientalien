/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author David
 */
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javax.xml.parsers.ParserConfigurationException;

public class CharacterCreationController implements Initializable {
    
           
    @FXML private TextField playerInputAmount;
    @FXML private TextField playerNameField;
    @FXML private Text player1;
    @FXML private Text player2;
    @FXML private Text player3;
    @FXML private Text player4;
    @FXML private Text player5;
    @FXML private Text player6;
    @FXML private Text player7;
    @FXML private Text player8;
    @FXML private Button playerAmountSubmit;
    @FXML private Label currentPlayer;
    @FXML private ImageView playerProfile;
    
    Moderator mod;
    String[] imageSources;
    String[] names;
    int playersAmount;
    int playerCounter = 0;
    private Scene bScene;
    private Text[] playerList;
    
        
    @FXML
    private void playerAmountButton(ActionEvent event) {
        String input = playerInputAmount.getText();
        playersAmount = Integer.parseInt(input);
        mod.getPlayerAmount(playersAmount);
        playerListInitialize();
        
        for(int i = 0; i < playersAmount; i++) {
            playerList[i].setText("Player " + (i + 1));
        }
        
        File source = new File(imageSources[0]);
        Image image = new Image(source.toURI().toString());
        playerProfile.setImage(image);
        
        currentPlayer.setText("Player " + (playerCounter + 1));
        playerList[0].setFill(Color.RED);
        playerAmountSubmit.setVisible(false);
    }
    
    @FXML
    private void playerNameButton(ActionEvent event) throws IOException, ParserConfigurationException {
        if(playerCounter < playersAmount) {
            
            if((playerCounter + 1) < playersAmount) {
                File source = new File(imageSources[playerCounter + 1]);
                Image image = new Image(source.toURI().toString());
                playerProfile.setImage(image);
            }
            currentPlayer.setText("Player " + (playerCounter + 2));
            String input = playerNameField.getText();
            names[playerCounter] = input;
            mod.getNames(playerCounter, input);
            playerList[playerCounter].setFill(Color.BLACK);
            
            if((playerCounter + 1) < playersAmount) {
                playerList[playerCounter + 1].setFill(Color.RED);
            }
            playerNameField.clear();
            
            playerCounter++;
        }
        
        if(playerCounter == playersAmount) {
            FXMLLoader boardSceneLoad = new FXMLLoader(getClass().getResource("Board.fxml"));
            Parent bRoot = boardSceneLoad.load();
            Scene boardScene = new Scene(bRoot);
            setNextScene(boardScene);
            
            BoardController bController = (BoardController) boardSceneLoad.getController();
            bController.getPlayersAmount(playersAmount);
            bController.getModerator(mod);
            bController.getPlayers();
            bController.getNames(names);
            bController.getIconSources(imageSources);
            bController.displayPlayers();
            bController.displayNames();
            bController.currentPlayerState();
            bController.setPlayerIcons();
            
            Stage board = (Stage)((Node) event.getSource()).getScene().getWindow();
            board.setScene(bScene);
            
        }
        
    }
    
    
    public void setNextScene(Scene scene) {
        bScene = scene;
    }
    
    private void playerListInitialize() {
        playerList = new Text[8];
        playerList[0] = player1;
        playerList[1] = player2;
        playerList[2] = player3;
        playerList[3] = player4;
        playerList[4] = player5;
        playerList[5] = player6;
        playerList[6] = player7;
        playerList[7] = player8;
        
        names = new String[8];
        
        imageSources = new String[8];
        imageSources[0] = "src/player1.png";
        imageSources[1] = "src/player2.png";
        imageSources[2] = "src/player3.png";
        imageSources[3] = "src/player4.png";
        imageSources[4] = "src/player5.png";
        imageSources[5] = "src/player6.png";
        imageSources[6] = "src/player7.png";
        imageSources[7] = "src/player8.png";
        
        
    }
    
    void exceptionCheckInitialization() throws ParserConfigurationException {
        mod = Moderator.getInstance();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            exceptionCheckInitialization();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CharacterCreationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
