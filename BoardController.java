/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javax.xml.parsers.ParserConfigurationException;

/**
 * FXML Controller class
 *
 * @author David
 */
public class BoardController implements Initializable {

    @FXML private Button moveButton;
    @FXML private Button actButton;
    @FXML private Button pickRoleButton;
    @FXML private Button upgradeButton;
    @FXML private Button rehearseButton;
    @FXML private Button endTurnButton;

    @FXML private Button roleOne;
    @FXML private Button roleTwo;
    @FXML private Button roleThree;
    @FXML private Button roleFour;
    @FXML private Button roleFive;
    @FXML private Button roleSix;
    @FXML private Button roleSeven;

    @FXML private Button upgradeTwoButton;
    @FXML private Button upgradeThreeButton;
    @FXML private Button upgradeFourButton;
    @FXML private Button upgradeFiveButton;
    @FXML private Button upgradeSixButton;
    @FXML private Button upgradeCreditButton;
    @FXML private Button upgradeMoneyButton;

    @FXML private ImageView player1Icon;
    @FXML private ImageView player2Icon;
    @FXML private ImageView player3Icon;
    @FXML private ImageView player4Icon;
    @FXML private ImageView player5Icon;
    @FXML private ImageView player6Icon;
    @FXML private ImageView player7Icon;
    @FXML private ImageView player8Icon;
    @FXML private ImageView currentPlayerIcon;

    @FXML private GridPane pickRoleTable;
    @FXML private GridPane upgradeTable;
    @FXML private Pane winnerPane;

    @FXML private ImageView tUp;
    @FXML private ImageView tDown;
    @FXML private ImageView tLeft;

    @FXML private ImageView msDown;
    @FXML private ImageView msDown2;
    @FXML private ImageView msLeft;

    @FXML private ImageView saUp;
    @FXML private ImageView saDown;
    @FXML private ImageView saLeft;
    @FXML private ImageView saRight;

    @FXML private ImageView jDown;
    @FXML private ImageView jLeft;
    @FXML private ImageView jRight;

    @FXML private ImageView gsUp;
    @FXML private ImageView gsDown;
    @FXML private ImageView gsLeft;
    @FXML private ImageView gsRight;

    @FXML private ImageView tsRight;
    @FXML private ImageView tsRight2;
    @FXML private ImageView tsDown;

    @FXML private ImageView coUp;
    @FXML private ImageView coRight;
    @FXML private ImageView coDown;

    @FXML private ImageView rUp;
    @FXML private ImageView rDown;
    @FXML private ImageView rLeft;
    @FXML private ImageView rRight;

    @FXML private ImageView shUp;
    @FXML private ImageView shUp2;
    @FXML private ImageView shRight;

    @FXML private ImageView bUp;
    @FXML private ImageView bDown;
    @FXML private ImageView bLeft;
    @FXML private ImageView bRight;

    @FXML private ImageView cUp;
    @FXML private ImageView cRight;
    @FXML private ImageView cLeft;

    @FXML private ImageView hUp;
    @FXML private ImageView hLeft;
    @FXML private ImageView hLeft2;

    @FXML private ImageView mainStreetCard;
    @FXML private ImageView saloonCard;
    @FXML private ImageView hotelCard;
    @FXML private ImageView bankCard;
    @FXML private ImageView secretHideoutCard;
    @FXML private ImageView ranchCard;
    @FXML private ImageView generalStoreCard;
    @FXML private ImageView jailCard;
    @FXML private ImageView trainStationCard;
    @FXML private ImageView churchCard;


    @FXML private ImageView msToken1;
    @FXML private ImageView msToken2;
    @FXML private ImageView msToken3;
    @FXML private ImageView sToken1;
    @FXML private ImageView sToken2;
    @FXML private ImageView bToken;
    @FXML private ImageView hToken1;
    @FXML private ImageView hToken2;
    @FXML private ImageView hToken3;
    @FXML private ImageView cToken1;
    @FXML private ImageView cToken2;
    @FXML private ImageView shToken1;
    @FXML private ImageView shToken2;
    @FXML private ImageView shToken3;
    @FXML private ImageView rToken1;
    @FXML private ImageView rToken2;
    @FXML private ImageView gsToken1;
    @FXML private ImageView gsToken2;
    @FXML private ImageView tsToken1;
    @FXML private ImageView tsToken2;
    @FXML private ImageView tsToken3;
    @FXML private ImageView jToken;

    @FXML private Label player1Label;
    @FXML private Label player2Label;
    @FXML private Label player3Label;
    @FXML private Label player4Label;
    @FXML private Label player5Label;
    @FXML private Label player6Label;
    @FXML private Label player7Label;
    @FXML private Label player8Label;

    @FXML private Label name1Label;
    @FXML private Label name2Label;
    @FXML private Label name3Label;
    @FXML private Label name4Label;
    @FXML private Label name5Label;
    @FXML private Label name6Label;
    @FXML private Label name7Label;
    @FXML private Label name8Label;
    @FXML private Label currentPlayerLabel;
    @FXML private Label winner;


    @FXML private Text rankDisplay;
    @FXML private Text moneyDisplay;
    @FXML private Text creditDisplay;
    @FXML private Text tokenDisplay;
    @FXML private Text dayDisplay;

    ImageView[] playerIcons;
    String[] arrowSources;
    String[] imageSources;
    Label[] playersLabel;
    Label[] nameLabels;
    String[] names;
    Player[] players;
    Player currentPlayer;
    Moderator mod;
    GameBoard board;
    int playersAmount;
    Resources resource;
    String[] locations;
    int locationAmount = 12;
    int storedRank = 0;
    boolean movedThisTurn = false;

    @FXML
    private void playerMove(ActionEvent event) {
        int playerIndex = mod.currentPlayerIndex();
        currentPlayer = players[playerIndex];
        moveButton.setDisable(true);
        actButton.setDisable(true);
        pickRoleButton.setDisable(true);
        upgradeButton.setDisable(true);
        pickRoleTable.setDisable(true);
        pickRoleTable.setVisible(false);
        upgradeTable.setDisable(true);
        upgradeTable.setVisible(false);

        if(currentPlayer.getLocation().equals("Trailer")) {
            tUp.setDisable(false);
            File source = new File(arrowSources[0]);
            Image image = new Image(source.toURI().toString());
            tUp.setImage(image);

            tDown.setDisable(false);
            source = new File(arrowSources[1]);
            image = new Image(source.toURI().toString());
            tDown.setImage(image);

            tLeft.setDisable(false);
            source = new File(arrowSources[2]);
            image = new Image(source.toURI().toString());
            tLeft.setImage(image);

        } else if(currentPlayer.getLocation().equals("MainStreet")) {
            msDown.setDisable(false);
            File source = new File(arrowSources[1]);
            Image image = new Image(source.toURI().toString());
            msDown.setImage(image);

            msDown2.setDisable(false);
            source = new File(arrowSources[1]);
            image = new Image(source.toURI().toString());
            msDown2.setImage(image);

            msLeft.setDisable(false);
            source = new File(arrowSources[2]);
            image = new Image(source.toURI().toString());
            msLeft.setImage(image);

        } else if(currentPlayer.getLocation().equals("Saloon")) {
            saUp.setDisable(false);
            File source = new File(arrowSources[0]);
            Image image = new Image(source.toURI().toString());
            saUp.setImage(image);

            saDown.setDisable(false);
            source = new File(arrowSources[1]);
            image = new Image(source.toURI().toString());
            saDown.setImage(image);

            saLeft.setDisable(false);
            source = new File(arrowSources[2]);
            image = new Image(source.toURI().toString());
            saLeft.setImage(image);

            saRight.setDisable(false);
            source = new File(arrowSources[3]);
            image = new Image(source.toURI().toString());
            saRight.setImage(image);

        } else if(currentPlayer.getLocation().equals("Hotel")) {
            hUp.setDisable(false);
            File source = new File(arrowSources[0]);
            Image image = new Image(source.toURI().toString());
            hUp.setImage(image);

            hLeft.setDisable(false);
            source = new File(arrowSources[2]);
            image = new Image(source.toURI().toString());
            hLeft.setImage(image);

            hLeft2.setDisable(false);
            source = new File(arrowSources[2]);
            image = new Image(source.toURI().toString());
            hLeft2.setImage(image);

        } else if(currentPlayer.getLocation().equals("Bank")) {
            bUp.setDisable(false);
            File source = new File(arrowSources[0]);
            Image image = new Image(source.toURI().toString());
            bUp.setImage(image);

            bDown.setDisable(false);
            source = new File(arrowSources[1]);
            image = new Image(source.toURI().toString());
            bDown.setImage(image);

            bLeft.setDisable(false);
            source = new File(arrowSources[2]);
            image = new Image(source.toURI().toString());
            bLeft.setImage(image);

            bRight.setDisable(false);
            source = new File(arrowSources[3]);
            image = new Image(source.toURI().toString());
            bRight.setImage(image);

        } else if(currentPlayer.getLocation().equals("Church")) {
            cUp.setDisable(false);
            File source = new File(arrowSources[0]);
            Image image = new Image(source.toURI().toString());
            cUp.setImage(image);

            cLeft.setDisable(false);
            source = new File(arrowSources[2]);
            image = new Image(source.toURI().toString());
            cLeft.setImage(image);

            cRight.setDisable(false);
            source = new File(arrowSources[3]);
            image = new Image(source.toURI().toString());
            cRight.setImage(image);

        } else if(currentPlayer.getLocation().equals("SecretHideout")) {
            shUp.setDisable(false);
            File source = new File(arrowSources[0]);
            Image image = new Image(source.toURI().toString());
            shUp.setImage(image);

            shUp2.setDisable(false);
            source = new File(arrowSources[0]);
            image = new Image(source.toURI().toString());
            shUp2.setImage(image);

            shRight.setDisable(false);
            source = new File(arrowSources[3]);
            image = new Image(source.toURI().toString());
            shRight.setImage(image);

        } else if(currentPlayer.getLocation().equals("Ranch")) {
            rUp.setDisable(false);
            File source = new File(arrowSources[0]);
            Image image = new Image(source.toURI().toString());
            rUp.setImage(image);

            rDown.setDisable(false);
            source = new File(arrowSources[1]);
            image = new Image(source.toURI().toString());
            rDown.setImage(image);

            rLeft.setDisable(false);
            source = new File(arrowSources[2]);
            image = new Image(source.toURI().toString());
            rLeft.setImage(image);

            rRight.setDisable(false);
            source = new File(arrowSources[3]);
            image = new Image(source.toURI().toString());
            rRight.setImage(image);

        } else if(currentPlayer.getLocation().equals("CastingOffice")) {
            coUp.setDisable(false);
            File source = new File(arrowSources[0]);
            Image image = new Image(source.toURI().toString());
            coUp.setImage(image);

            coRight.setDisable(false);
            source = new File(arrowSources[3]);
            image = new Image(source.toURI().toString());
            coRight.setImage(image);

            coDown.setDisable(false);
            source = new File(arrowSources[1]);
            image = new Image(source.toURI().toString());
            coDown.setImage(image);


        } else if(currentPlayer.getLocation().equals("GeneralStore")) {
            gsUp.setDisable(false);
            File source = new File(arrowSources[0]);
            Image image = new Image(source.toURI().toString());
            gsUp.setImage(image);

            gsDown.setDisable(false);
            source = new File(arrowSources[1]);
            image = new Image(source.toURI().toString());
            gsDown.setImage(image);

            gsLeft.setDisable(false);
            source = new File(arrowSources[2]);
            image = new Image(source.toURI().toString());
            gsLeft.setImage(image);

            gsRight.setDisable(false);
            source = new File(arrowSources[3]);
            image = new Image(source.toURI().toString());
            gsRight.setImage(image);

        } else if(currentPlayer.getLocation().equals("TrainStation")) {
            tsDown.setDisable(false);
            File source = new File(arrowSources[1]);
            Image image = new Image(source.toURI().toString());
            tsDown.setImage(image);

            tsRight.setDisable(false);
            source = new File(arrowSources[3]);
            image = new Image(source.toURI().toString());
            tsRight.setImage(image);

            tsRight2.setDisable(false);
            source = new File(arrowSources[3]);
            image = new Image(source.toURI().toString());
            tsRight2.setImage(image);

        } else if(currentPlayer.getLocation().equals("Jail")) {
            jLeft.setDisable(false);
            File source = new File(arrowSources[2]);
            Image image = new Image(source.toURI().toString());
            jLeft.setImage(image);

            jRight.setDisable(false);
            source = new File(arrowSources[3]);
            image = new Image(source.toURI().toString());
            jRight.setImage(image);

            jDown.setDisable(false);
            source = new File(arrowSources[1]);
            image = new Image(source.toURI().toString());
            jDown.setImage(image);
        }
    }



    @FXML
    private void playerAct(ActionEvent event) {
      actButton.setDisable(true);
      rehearseButton.setDisable(true);
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      String actArray = mod.act(currentPlayer);
      if(actArray.equals("Success")){
        removeCounter();
        if(board.getShotCounters(currentPlayer.getLocation())==0){
          removeScene();
          dayOver();
        }
      }
      rankDisplay.setText(" " + resource.currentRank(currentPlayer));
      moneyDisplay.setText(" " + resource.currentMoney(currentPlayer));
      creditDisplay.setText(" " + resource.currentCredit(currentPlayer));
      tokenDisplay.setText(" " + currentPlayer.getRehearsalToken());
    }



    @FXML
    private void playerPickRole(ActionEvent event) {
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      pickRoleTable.setDisable(false);
      pickRoleTable.setVisible(true);
      pickRoleButton.setDisable(true);
      pickRoleTable.setStyle("-fx-background-color: #C0C0C0;");
      String[] openRoles = mod.availableRoles(currentPlayer);
      String[] allRoles = mod.allRoles(currentPlayer);
      int amntOfRoles = openRoles.length;
      roleOne.setDisable(true);
      roleTwo.setDisable(true);
      roleThree.setDisable(true);
      roleFour.setDisable(true);
      roleFive.setDisable(true);
      roleSix.setDisable(true);
      roleSeven.setDisable(true);

      if(amntOfRoles>=1){
        roleOne.setText(allRoles[0]);
        if(openRoles[0]!=null){
          roleOne.setDisable(false);
        }
        if(amntOfRoles>=2){
          roleTwo.setText(allRoles[1]);
          if(openRoles[1]!=null){
            roleTwo.setDisable(false);
          }
          if(amntOfRoles>=3){
            roleThree.setText(allRoles[2]);
            if(openRoles[2]!=null){
              roleThree.setDisable(false);
            }
            if(amntOfRoles>=4){
              roleFour.setText(allRoles[3]);
              if(openRoles[3]!=null){
                roleFour.setDisable(false);
              }
              if(amntOfRoles>=5){
                roleFive.setText(allRoles[4]);
                if(openRoles[4]!=null){
                  roleFive.setDisable(false);
                }
                if(amntOfRoles>=6){
                  roleSix.setText(allRoles[5]);
                  if(openRoles[5]!=null){
                    roleSix.setDisable(false);
                  }
                  if(amntOfRoles>=7){
                    roleSeven.setText(allRoles[6]);
                    if(openRoles[6]!=null){
                      roleSeven.setDisable(false);
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    @FXML
    private void playerUpgrade(ActionEvent event) {
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      int[] upgradesAvailable = mod.upgradeAvailable(currentPlayer);
      upgradeTable.setDisable(false);
      upgradeTable.setVisible(true);
      upgradeButton.setDisable(true);
      upgradeTable.setStyle("-fx-background-color: #C0C0C0;");
      upgradeTwoButton.setDisable(true);
      upgradeThreeButton.setDisable(true);
      upgradeFourButton.setDisable(true);
      upgradeFiveButton.setDisable(true);
      upgradeSixButton.setDisable(true);
      upgradeMoneyButton.setDisable(true);
      upgradeCreditButton.setDisable(true);
      if(upgradesAvailable[1]==2){
        upgradeTwoButton.setDisable(false);
      }
      if(upgradesAvailable[2]==3){
        upgradeThreeButton.setDisable(false);
      }
      if(upgradesAvailable[3]==4){
        upgradeFourButton.setDisable(false);
      }
      if(upgradesAvailable[4]==5){
        upgradeFiveButton.setDisable(false);
      }
      if(upgradesAvailable[5]==6){
        upgradeSixButton.setDisable(false);
      }
    }

    @FXML
    private void playerRehearse(ActionEvent event) {
      rehearseButton.setDisable(true);
      actButton.setDisable(true);
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      currentPlayer.rehearse();
      tokenDisplay.setText(" " + currentPlayer.getRehearsalToken());
    }

    @FXML
    private void pickRoleOne(ActionEvent event) {
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      pickRoleTable.setDisable(true);
      moveButton.setDisable(true);
      mod.setRole(currentPlayer, roleOne.getText());
      moveButton.setDisable(true);
      pickRoleTable.setDisable(true);
      pickRoleTable.setVisible(false);
      if(!"".equals(board.getScene(currentPlayer.getLocation())) && movedThisTurn==false){
        actButton.setDisable(false);
        rehearseButton.setDisable(false);
      }
    }
    @FXML
    private void pickRoleTwo(ActionEvent event) {
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      pickRoleTable.setDisable(true);
      moveButton.setDisable(true);
      mod.setRole(currentPlayer, roleTwo.getText());
      moveButton.setDisable(true);
      pickRoleTable.setDisable(true);
      pickRoleTable.setVisible(false);
      if(!"".equals(board.getScene(currentPlayer.getLocation())) && movedThisTurn==false){
        actButton.setDisable(false);
        rehearseButton.setDisable(false);
      }
    }
    @FXML
    private void pickRoleThree(ActionEvent event) {
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      pickRoleTable.setDisable(true);
      moveButton.setDisable(true);
      pickRoleTable.setDisable(true);
      pickRoleTable.setVisible(false);
      mod.setRole(currentPlayer, roleThree.getText());
      moveButton.setDisable(true);
      if(!"".equals(board.getScene(currentPlayer.getLocation())) && movedThisTurn==false){
        actButton.setDisable(false);
        rehearseButton.setDisable(false);
      }
    }
    @FXML
    private void pickRoleFour(ActionEvent event) {
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      pickRoleTable.setDisable(true);
      moveButton.setDisable(true);
      pickRoleTable.setDisable(true);
      pickRoleTable.setVisible(false);
      mod.setRole(currentPlayer, roleFour.getText());
      moveButton.setDisable(true);
      if(!"".equals(board.getScene(currentPlayer.getLocation())) && movedThisTurn==false){
        actButton.setDisable(false);
        rehearseButton.setDisable(false);
      }
    }
    @FXML
    private void pickRoleFive(ActionEvent event) {
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      pickRoleTable.setDisable(true);
      moveButton.setDisable(true);
      pickRoleTable.setDisable(true);
      pickRoleTable.setVisible(false);
      mod.setRole(currentPlayer, roleFive.getText());
      moveButton.setDisable(true);
      if(!"".equals(board.getScene(currentPlayer.getLocation())) && movedThisTurn==false){
        actButton.setDisable(false);
        rehearseButton.setDisable(false);
      }
    }
    @FXML
    private void pickRoleSix(ActionEvent event) {
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      pickRoleTable.setDisable(true);
      moveButton.setDisable(true);
      pickRoleTable.setDisable(true);
      pickRoleTable.setVisible(false);
      mod.setRole(currentPlayer, roleSix.getText());
      moveButton.setDisable(true);
      if(!"".equals(board.getScene(currentPlayer.getLocation())) && movedThisTurn==false){
        actButton.setDisable(false);
        rehearseButton.setDisable(false);
      }
    }
    @FXML
    private void pickRoleSeven(ActionEvent event) {
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      pickRoleTable.setDisable(true);
      moveButton.setDisable(true);
      pickRoleTable.setDisable(true);
      pickRoleTable.setVisible(false);
      mod.setRole(currentPlayer, roleSeven.getText());
      moveButton.setDisable(true);
      if(!"".equals(board.getScene(currentPlayer.getLocation())) && movedThisTurn==false){
        actButton.setDisable(false);
        rehearseButton.setDisable(false);
      }
    }

    @FXML
    private void upgradeRankTwo(ActionEvent event){
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      boolean choose = mod.quickCast(currentPlayer, 2);
      upgradeTwoButton.setDisable(true);
      upgradeThreeButton.setDisable(true);
      upgradeFourButton.setDisable(true);
      upgradeFiveButton.setDisable(true);
      upgradeSixButton.setDisable(true);
      rankDisplay.setText(" " + resource.currentRank(currentPlayer));
      moneyDisplay.setText(" " + resource.currentMoney(currentPlayer));
      creditDisplay.setText(" " + resource.currentCredit(currentPlayer));
      if(choose == true){
        storedRank=2;
        upgradeMoneyButton.setDisable(false);
        upgradeCreditButton.setDisable(false);
      }
    }
    @FXML
    private void upgradeRankThree(ActionEvent event) {
      boolean choose = mod.quickCast(currentPlayer, 3);
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      upgradeTwoButton.setDisable(true);
      upgradeThreeButton.setDisable(true);
      upgradeFourButton.setDisable(true);
      upgradeFiveButton.setDisable(true);
      upgradeSixButton.setDisable(true);
      rankDisplay.setText(" " + resource.currentRank(currentPlayer));
      moneyDisplay.setText(" " + resource.currentMoney(currentPlayer));
      creditDisplay.setText(" " + resource.currentCredit(currentPlayer));
      if(choose == true){
        storedRank=3;
        upgradeMoneyButton.setDisable(false);
        upgradeCreditButton.setDisable(false);
      }

    }
    @FXML
    private void upgradeRankFour(ActionEvent event) {
      boolean choose = mod.quickCast(currentPlayer, 4);
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      upgradeTwoButton.setDisable(true);
      upgradeThreeButton.setDisable(true);
      upgradeFourButton.setDisable(true);
      upgradeFiveButton.setDisable(true);
      upgradeSixButton.setDisable(true);
      rankDisplay.setText(" " + resource.currentRank(currentPlayer));
      moneyDisplay.setText(" " + resource.currentMoney(currentPlayer));
      creditDisplay.setText(" " + resource.currentCredit(currentPlayer));
      if(choose == true){
        storedRank=4;
        upgradeMoneyButton.setDisable(false);
        upgradeCreditButton.setDisable(false);
      }

    }
    @FXML
    private void upgradeRankFive(ActionEvent event) {
       boolean choose = mod.quickCast(currentPlayer, 5);
       int playerIndex = mod.currentPlayerIndex();
       currentPlayer = players[playerIndex];
       upgradeTwoButton.setDisable(true);
       upgradeThreeButton.setDisable(true);
       upgradeFourButton.setDisable(true);
       upgradeFiveButton.setDisable(true);
       upgradeSixButton.setDisable(true);
       rankDisplay.setText(" " + resource.currentRank(currentPlayer));
       moneyDisplay.setText(" " + resource.currentMoney(currentPlayer));
       creditDisplay.setText(" " + resource.currentCredit(currentPlayer));
       if(choose == true){
         storedRank=5;
         upgradeMoneyButton.setDisable(false);
         upgradeCreditButton.setDisable(false);
       }
    }
    @FXML
    private void upgradeRankSix(ActionEvent event) {
      boolean choose = mod.quickCast(currentPlayer, 6);
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      upgradeTwoButton.setDisable(true);
      upgradeThreeButton.setDisable(true);
      upgradeFourButton.setDisable(true);
      upgradeFiveButton.setDisable(true);
      upgradeSixButton.setDisable(true);
      rankDisplay.setText(" " + resource.currentRank(currentPlayer));
      moneyDisplay.setText(" " + resource.currentMoney(currentPlayer));
      creditDisplay.setText(" " + resource.currentCredit(currentPlayer));
      if(choose == true){
        storedRank=6;
        upgradeMoneyButton.setDisable(false);
        upgradeCreditButton.setDisable(false);
      }
    }
    @FXML
    private void upgradeMoney(ActionEvent event) {
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      upgradeMoneyButton.setDisable(true);
      upgradeCreditButton.setDisable(true);
      mod.cast(currentPlayer, storedRank, "Money");
      rankDisplay.setText(" " + resource.currentRank(currentPlayer));
      moneyDisplay.setText(" " + resource.currentMoney(currentPlayer));
    }
    @FXML
    private void upgradeCredit(ActionEvent event) {
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      upgradeMoneyButton.setDisable(true);
      upgradeCreditButton.setDisable(true);
      mod.cast(currentPlayer, storedRank, "Credit");
      rankDisplay.setText(" " + resource.currentRank(currentPlayer));
      creditDisplay.setText(" " + resource.currentCredit(currentPlayer));

    }


    private void removeScene(){
      pickRoleButton.setDisable(true);
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      String location = currentPlayer.getLocation();
      switch(location)
        {
            case "MainStreet":
                mainStreetCard.setImage(null);
                break;
            case "Saloon":
                saloonCard.setImage(null);
                break;
            case "Bank":
                bankCard.setImage(null);
                break;
            case "Hotel":
                hotelCard.setImage(null);
                break;
            case "Church":
                churchCard.setImage(null);
                break;
            case "SecretHideout":
                secretHideoutCard.setImage(null);
                break;
            case "Ranch":
                ranchCard.setImage(null);
                break;
            case "GeneralStore":
                generalStoreCard.setImage(null);
                break;
            case "Jail":
                jailCard.setImage(null);
                break;
            case "TrainStation":
                trainStationCard.setImage(null);
                break;
            default:
                System.out.println("no match");
        }

    }

    private void removeCounter(){
      int playerIndex = mod.currentPlayerIndex();
      currentPlayer = players[playerIndex];
      String location = currentPlayer.getLocation();
      int countersLeft = board.getShotCounters(location);
      switch(location)
        {
            case "MainStreet":
              if(countersLeft==2){
                msToken3.setImage(null);
              }
              if(countersLeft==1){
                msToken2.setImage(null);
              }
              if(countersLeft==0){
                msToken1.setImage(null);
              }

                break;
            case "Saloon":
                if(countersLeft==1){
                  sToken2.setImage(null);
                }
                if(countersLeft==0){
                  sToken1.setImage(null);
                }
                break;
            case "Bank":
                if(countersLeft==0){
                  bToken.setImage(null);
                }
                break;
            case "Hotel":
                if(countersLeft==2){
                  hToken3.setImage(null);
                }
                if(countersLeft==1){
                  hToken2.setImage(null);
                }
                if(countersLeft==0){
                  hToken1.setImage(null);
                }
                break;
            case "Church":
                if(countersLeft==1){
                  cToken2.setImage(null);
                }
                if(countersLeft==0){
                  cToken1.setImage(null);
                }
                break;
            case "SecretHideout":
                if(countersLeft==2){
                  shToken3.setImage(null);
                }
                if(countersLeft==1){
                  shToken2.setImage(null);
                }
                if(countersLeft==0){
                  shToken1.setImage(null);
                }
                break;
            case "Ranch":
                if(countersLeft==1){
                  rToken2.setImage(null);
                }
                if(countersLeft==0){
                  rToken1.setImage(null);
                }
                break;
            case "GeneralStore":
                if(countersLeft==1){
                  gsToken2.setImage(null);
                }
                if(countersLeft==0){
                  gsToken1.setImage(null);
                }
                break;
            case "Jail":
                if(countersLeft==0){
                  jToken.setImage(null);
                }
                break;
            case "TrainStation":
                if(countersLeft==2){
                  tsToken3.setImage(null);
                }
                if(countersLeft==1){
                  tsToken2.setImage(null);
                }
                if(countersLeft==0){
                  tsToken1.setImage(null);
                }
                break;
            default:
                System.out.println("no match");
        }

    }

    @FXML
    private void trailerMoveUp(MouseEvent event) {
        int playerIndex = mod.currentPlayerIndex();
        currentPlayer = players[playerIndex];
        currentPlayer.setLocation("MainStreet");
        if(!"".equals(board.getScene(currentPlayer.getLocation()))){
          pickRoleButton.setDisable(false);
        }
        disableAllMoves();
        disableAllArrows();
        movedThisTurn=true;
        switch (playerIndex) {
            case 0:
                playerIcons[playerIndex].relocate(632, 65);
                break;
            case 1:
                playerIcons[playerIndex].relocate(683, 112);
                break;
            case 2:
                playerIcons[playerIndex].relocate(735, 65);
                break;
            case 3:
                playerIcons[playerIndex].relocate(683, 65);
                break;
            case 4:
                playerIcons[playerIndex].relocate(631, 112);
                break;
            case 5:
                playerIcons[playerIndex].relocate(735, 112);
                break;
            case 6:
                playerIcons[playerIndex].relocate(786, 138);
                break;
            default:
                playerIcons[playerIndex].relocate(843, 138);
                break;

        }
        if(board.isSceneReveal(currentPlayer.getLocation())==false &&
         !"".equals(board.getScene(currentPlayer.getLocation()))){
           File source = new File("src/"+board.getSceneImg(board.getScene(currentPlayer.getLocation())));
           Image image = new Image(source.toURI().toString());
           mainStreetCard.setImage(image);
           board.revealScene(currentPlayer.getLocation());
         }
    }

        @FXML
        private void trailerMoveLeft(MouseEvent event) {
            int playerIndex = mod.currentPlayerIndex();
            currentPlayer = players[playerIndex];
            currentPlayer.setLocation("Saloon");
            if(!"".equals(board.getScene(currentPlayer.getLocation()))){
              pickRoleButton.setDisable(false);
            }
            disableAllMoves();
            disableAllArrows();
            movedThisTurn=true;
            switch (playerIndex) {
                case 0:
                    playerIcons[playerIndex].relocate(594, 194);
                    break;
                case 1:
                    playerIcons[playerIndex].relocate(640, 194);
                    break;
                case 2:
                    playerIcons[playerIndex].relocate(682, 194);
                    break;
                case 3:
                    playerIcons[playerIndex].relocate(733, 194);
                    break;
                case 4:
                    playerIcons[playerIndex].relocate(501, 350);
                    break;
                case 5:
                    playerIcons[playerIndex].relocate(733, 350);
                    break;
                case 6:
                    playerIcons[playerIndex].relocate(645, 350);
                    break;
                default:
                    playerIcons[playerIndex].relocate(688, 350);
                    break;
            }
            if(board.isSceneReveal(currentPlayer.getLocation())==false &&
             !"".equals(board.getScene(currentPlayer.getLocation()))){
               File source = new File("src/"+board.getSceneImg(board.getScene(currentPlayer.getLocation())));
               Image image = new Image(source.toURI().toString());
               saloonCard.setImage(image);
               board.revealScene(currentPlayer.getLocation());
             }
        }

        @FXML
        private void trailerMoveDown(MouseEvent event) {
            int playerIndex = mod.currentPlayerIndex();
            currentPlayer = players[playerIndex];
            currentPlayer.setLocation("Hotel");
            if(!"".equals(board.getScene(currentPlayer.getLocation()))){
              pickRoleButton.setDisable(false);
            }
            disableAllMoves();
            disableAllArrows();
            movedThisTurn=true;
            switch (playerIndex) {
                case 0:
                    playerIcons[playerIndex].relocate(858, 411);
                    break;
                case 1:
                    playerIcons[playerIndex].relocate(807, 411);
                    break;
                case 2:
                    playerIcons[playerIndex].relocate(807, 462);
                    break;
                case 3:
                    playerIcons[playerIndex].relocate(807, 513);
                    break;
                case 4:
                    playerIcons[playerIndex].relocate(807, 564);
                    break;
                case 5:
                    playerIcons[playerIndex].relocate(858, 564);
                    break;
                case 6:
                    playerIcons[playerIndex].relocate(906, 564);
                break;
                default:
                    playerIcons[playerIndex].relocate(948, 564);
                    break;
            }
            if(board.isSceneReveal(currentPlayer.getLocation())==false &&
             !"".equals(board.getScene(currentPlayer.getLocation()))){
               File source = new File("src/"+board.getSceneImg(board.getScene(currentPlayer.getLocation())));
               Image image = new Image(source.toURI().toString());
               hotelCard.setImage(image);
               board.revealScene(currentPlayer.getLocation());
             }
        }

        @FXML
        private void mainStreetMoveDown(MouseEvent event) {
            int playerIndex = mod.currentPlayerIndex();
            currentPlayer = players[playerIndex];
            currentPlayer.setLocation("Trailer");
            disableAllMoves();
            disableAllArrows();
            switch (playerIndex) {
                case 0:
                    playerIcons[playerIndex].relocate(802, 228);
                    break;
                case 1:
                    playerIcons[playerIndex].relocate(853, 228);
                    break;
                case 2:
                    playerIcons[playerIndex].relocate(904, 228);
                    break;
                case 3:
                    playerIcons[playerIndex].relocate(807, 279);
                    break;
                case 4:
                    playerIcons[playerIndex].relocate(863, 279);
                    break;
                case 5:
                    playerIcons[playerIndex].relocate(916, 279);
                    break;
                case 6:
                    playerIcons[playerIndex].relocate(812, 336);
                    break;
                default:
                    playerIcons[playerIndex].relocate(859, 336);
                    break;

            }
        }

        @FXML
        private void mainStreetMoveDown2(MouseEvent event) {
            trailerMoveLeft(event);
        }

        @FXML
        private void mainStreetMoveLeft(MouseEvent event) {
            int playerIndex = mod.currentPlayerIndex();
            currentPlayer = players[playerIndex];
            currentPlayer.setLocation("Jail");
            if(!"".equals(board.getScene(currentPlayer.getLocation()))){
              pickRoleButton.setDisable(false);
            }
            pickRoleButton.setDisable(false);
            disableAllMoves();
            disableAllArrows();
            movedThisTurn=true;
            switch (playerIndex) {
                case 0:
                    playerIcons[playerIndex].relocate(224, 126);
                    break;
                case 1:
                    playerIcons[playerIndex].relocate(264, 158);
                    break;
                case 2:
                    playerIcons[playerIndex].relocate(315, 158);
                    break;
                case 3:
                    playerIcons[playerIndex].relocate(366, 167);
                    break;
                case 4:
                    playerIcons[playerIndex].relocate(418, 158);
                    break;
                case 5:
                    playerIcons[playerIndex].relocate(199, 82);
                    break;
                case 6:
                    playerIcons[playerIndex].relocate(199, 31);
                    break;
                default:
                    playerIcons[playerIndex].relocate(458, 116);
                    break;

            }
            if(board.isSceneReveal(currentPlayer.getLocation())==false &&
             !"".equals(board.getScene(currentPlayer.getLocation()))){
               File source = new File("src/"+board.getSceneImg(board.getScene(currentPlayer.getLocation())));
               Image image = new Image(source.toURI().toString());
               jailCard.setImage(image);
               board.revealScene(currentPlayer.getLocation());
             }
        }


        @FXML
        private void SaloonMoveUp(MouseEvent event) {
            trailerMoveUp(event);
        }

        @FXML
        private void SaloonMoveDown(MouseEvent event) {
            int playerIndex = mod.currentPlayerIndex();
            currentPlayer = players[playerIndex];
            currentPlayer.setLocation("Bank");
            if(!"".equals(board.getScene(currentPlayer.getLocation()))){
              pickRoleButton.setDisable(false);
            }
            disableAllMoves();
            disableAllArrows();
            movedThisTurn=true;
            switch (playerIndex) {
                case 0:
                    playerIcons[playerIndex].relocate(496, 525);
                    break;
                case 1:
                    playerIcons[playerIndex].relocate(547, 525);
                    break;
                case 2:
                    playerIcons[playerIndex].relocate(598, 525);
                    break;
                case 3:
                    playerIcons[playerIndex].relocate(649, 525);
                    break;
                case 4:
                    playerIcons[playerIndex].relocate(701, 525);
                    break;
                case 5:
                    playerIcons[playerIndex].relocate(751, 525);
                    break;
                case 6:
                    playerIcons[playerIndex].relocate(692, 401);
                    break;
                default:
                    playerIcons[playerIndex].relocate(693, 444);
                    break;

            }
            if(board.isSceneReveal(currentPlayer.getLocation())==false &&
             !"".equals(board.getScene(currentPlayer.getLocation()))){
               File source = new File("src/"+board.getSceneImg(board.getScene(currentPlayer.getLocation())));
               Image image = new Image(source.toURI().toString());
               bankCard.setImage(image);
               board.revealScene(currentPlayer.getLocation());
             }
        }

        @FXML
        private void SaloonMoveLeft(MouseEvent event) {
            int playerIndex = mod.currentPlayerIndex();
            currentPlayer = players[playerIndex];
            currentPlayer.setLocation("GeneralStore");
            if(!"".equals(board.getScene(currentPlayer.getLocation()))){
              pickRoleButton.setDisable(false);
            }
            disableAllMoves();
            disableAllArrows();
            movedThisTurn=true;
            switch (playerIndex) {
                case 0:
                    playerIcons[playerIndex].relocate(148, 228);
                    break;
                case 1:
                    playerIcons[playerIndex].relocate(156, 271);
                    break;
                case 2:
                    playerIcons[playerIndex].relocate(148, 313);
                    break;
                case 3:
                    playerIcons[playerIndex].relocate(156, 358);
                    break;
                case 4:
                    playerIcons[playerIndex].relocate(207, 358);
                    break;
                case 5:
                    playerIcons[playerIndex].relocate(220, 203);
                    break;
                case 6:
                    playerIcons[playerIndex].relocate(271, 203);
                    break;
                default:
                    playerIcons[playerIndex].relocate(313, 203);
                    break;

            }
            if(board.isSceneReveal(currentPlayer.getLocation())==false &&
             !"".equals(board.getScene(currentPlayer.getLocation()))){
               File source = new File("src/"+board.getSceneImg(board.getScene(currentPlayer.getLocation())));
               Image image = new Image(source.toURI().toString());
               generalStoreCard.setImage(image);
               board.revealScene(currentPlayer.getLocation());
             }
        }

        @FXML
        private void SaloonMoveRight(MouseEvent event) {
            mainStreetMoveDown(event);
        }

        @FXML
        private void jailMoveLeft(MouseEvent event) {
            int playerIndex = mod.currentPlayerIndex();
            currentPlayer = players[playerIndex];
            currentPlayer.setLocation("TrainStation");
            if(!"".equals(board.getScene(currentPlayer.getLocation()))){
              pickRoleButton.setDisable(false);
            }
            disableAllMoves();
            disableAllArrows();
            movedThisTurn=true;
            switch (playerIndex) {
                case 0:
                    playerIcons[playerIndex].relocate(14, 193);
                    break;
                case 1:
                    playerIcons[playerIndex].relocate(-5, 253);
                    break;
                case 2:
                    playerIcons[playerIndex].relocate(-5, 304);
                    break;
                case 3:
                    playerIcons[playerIndex].relocate(-5, 349);
                    break;
                case 4:
                    playerIcons[playerIndex].relocate(46, 355);
                    break;
                case 5:
                    playerIcons[playerIndex].relocate(99, 338);
                    break;
                case 6:
                    playerIcons[playerIndex].relocate(114, 237);
                    break;
                default:
                    playerIcons[playerIndex].relocate(136, 183);
                    break;

            }
            if(board.isSceneReveal(currentPlayer.getLocation())==false &&
             !"".equals(board.getScene(currentPlayer.getLocation()))){
               File source = new File("src/"+board.getSceneImg(board.getScene(currentPlayer.getLocation())));
               Image image = new Image(source.toURI().toString());
               trainStationCard.setImage(image);
               board.revealScene(currentPlayer.getLocation());
             }
        }

        @FXML
        private void jailMoveRight(MouseEvent event) {
            trailerMoveUp(event);
        }

        @FXML
        private void jailMoveDown(MouseEvent event) {
            SaloonMoveLeft(event);
        }

        @FXML
        private void generalstoreMoveUp(MouseEvent event) {
            mainStreetMoveLeft(event);
        }

        @FXML
        private void generalstoreMoveDown(MouseEvent event) {
            int playerIndex = mod.currentPlayerIndex();
            currentPlayer = players[playerIndex];
            currentPlayer.setLocation("Ranch");
            if(!"".equals(board.getScene(currentPlayer.getLocation()))){
              pickRoleButton.setDisable(false);
            }
            disableAllMoves();
            disableAllArrows();
            movedThisTurn=true;
            switch (playerIndex) {
                case 0:
                    playerIcons[playerIndex].relocate(175, 521);
                    break;
                case 1:
                    playerIcons[playerIndex].relocate(297, 529);
                    break;
                case 2:
                    playerIcons[playerIndex].relocate(201, 572);
                    break;
                case 3:
                    playerIcons[playerIndex].relocate(252, 572);
                    break;
                case 4:
                    playerIcons[playerIndex].relocate(296, 580);
                    break;
                case 5:
                    playerIcons[playerIndex].relocate(384, 465);
                    break;
                case 6:
                    playerIcons[playerIndex].relocate(435, 465);
                    break;
                default:
                    playerIcons[playerIndex].relocate(447, 521);
                    break;

            }
            if(board.isSceneReveal(currentPlayer.getLocation())==false &&
             !"".equals(board.getScene(currentPlayer.getLocation()))){
               File source = new File("src/"+board.getSceneImg(board.getScene(currentPlayer.getLocation())));
               Image image = new Image(source.toURI().toString());
               ranchCard.setImage(image);
               board.revealScene(currentPlayer.getLocation());
             }
        }

        @FXML
        private void generalstoreMoveLeft(MouseEvent event) {
            jailMoveLeft(event);
        }

        @FXML
        private void generalstoreMoveRight(MouseEvent event) {
            trailerMoveLeft(event);
        }

        @FXML
        private void trainStationMoveRight(MouseEvent event) {
            mainStreetMoveLeft(event);
        }

        @FXML
        private void trainStationMoveRight2(MouseEvent event) {
            SaloonMoveLeft(event);
        }

         @FXML
        private void trainStationMoveDown(MouseEvent event) {
            int playerIndex = mod.currentPlayerIndex();
            currentPlayer = players[playerIndex];
            currentPlayer.setLocation("CastingOffice");
            currentPlayer.setinCasting(true);
            upgradeButton.setDisable(false);
            pickRoleButton.setDisable(true);
            actButton.setDisable(true);
            disableAllMoves();
            disableAllArrows();
            switch (playerIndex) {
                case 0:
                    playerIcons[playerIndex].relocate(7, 444);
                    break;
                case 1:
                    playerIcons[playerIndex].relocate(60, 444);
                    break;
                case 2:
                    playerIcons[playerIndex].relocate(109, 444);
                    break;
                case 3:
                    playerIcons[playerIndex].relocate(7, 495);
                    break;
                case 4:
                    playerIcons[playerIndex].relocate(53, 495);
                    break;
                case 5:
                    playerIcons[playerIndex].relocate(109, 495);
                    break;
                case 6:
                    playerIcons[playerIndex].relocate(12, 546);
                    break;
                default:
                    playerIcons[playerIndex].relocate(60, 546);
                    break;

            }
        }

         @FXML
        private void castingofficeMoveUp(MouseEvent event) {
            jailMoveLeft(event);
            currentPlayer.setinCasting(false);
            upgradeButton.setDisable(true);
        }

        @FXML
        private void castingofficeMoveRight(MouseEvent event) {
            generalstoreMoveDown(event);
            currentPlayer.setinCasting(false);
            upgradeButton.setDisable(true);
        }

        @FXML
        private void castingofficeMoveDown(MouseEvent event) {
            int playerIndex = mod.currentPlayerIndex();
            currentPlayer = players[playerIndex];
            currentPlayer.setLocation("SecretHideout");
            currentPlayer.setinCasting(false);
            upgradeButton.setDisable(true);
            if(!"".equals(board.getScene(currentPlayer.getLocation()))){
              pickRoleButton.setDisable(false);
            }
            disableAllMoves();
            disableAllArrows();
            movedThisTurn=true;
            switch (playerIndex) {
                case 0:
                    playerIcons[playerIndex].relocate(201, 723);
                    break;
                case 1:
                    playerIcons[playerIndex].relocate(252, 723);
                    break;
                case 2:
                    playerIcons[playerIndex].relocate(303, 723);
                    break;
                case 3:
                    playerIcons[playerIndex].relocate(201, 635);
                    break;
                case 4:
                    playerIcons[playerIndex].relocate(253, 635);
                    break;
                case 5:
                    playerIcons[playerIndex].relocate(14, 596);
                    break;
                case 6:
                    playerIcons[playerIndex].relocate(65, 596);
                    break;
                default:
                    playerIcons[playerIndex].relocate(115, 596);
                    break;

            }
            if(board.isSceneReveal(currentPlayer.getLocation())==false &&
             !"".equals(board.getScene(currentPlayer.getLocation()))){
               File source = new File("src/"+board.getSceneImg(board.getScene(currentPlayer.getLocation())));
               Image image = new Image(source.toURI().toString());
               secretHideoutCard.setImage(image);
               board.revealScene(currentPlayer.getLocation());
             }

        }

        @FXML
        private void secrethideoutMoveUp(MouseEvent event) {
            generalstoreMoveDown(event);
        }

        @FXML
        private void secrethideoutMoveUp2(MouseEvent event) {
            trainStationMoveDown(event);
        }

        @FXML
        private void secrethideoutMoveRight(MouseEvent event) {
            int playerIndex = mod.currentPlayerIndex();
            currentPlayer = players[playerIndex];
            currentPlayer.setLocation("Church");
            if(!"".equals(board.getScene(currentPlayer.getLocation()))){
              pickRoleButton.setDisable(false);
            }
            disableAllMoves();
            disableAllArrows();
            movedThisTurn=true;
            switch (playerIndex) {
                case 0:
                    playerIcons[playerIndex].relocate(468, 628);
                    break;
                case 1:
                    playerIcons[playerIndex].relocate(468, 679);
                    break;
                case 2:
                    playerIcons[playerIndex].relocate(468, 730);
                    break;
                case 3:
                    playerIcons[playerIndex].relocate(519, 756);
                    break;
                case 4:
                    playerIcons[playerIndex].relocate(649, 756);
                    break;
                case 5:
                    playerIcons[playerIndex].relocate(598, 603);
                    break;
                case 6:
                    playerIcons[playerIndex].relocate(649, 603);
                    break;
                default:
                    playerIcons[playerIndex].relocate(691, 603);
                    break;

            }
            if(board.isSceneReveal(currentPlayer.getLocation())==false &&
             !"".equals(board.getScene(currentPlayer.getLocation()))){
               File source = new File("src/"+board.getSceneImg(board.getScene(currentPlayer.getLocation())));
               Image image = new Image(source.toURI().toString());
               churchCard.setImage(image);
               board.revealScene(currentPlayer.getLocation());
             }
           }
         @FXML
        private void ranchMoveUp(MouseEvent event) {
            SaloonMoveLeft(event);
        }

        @FXML
        private void ranchMoveDown(MouseEvent event) {
            castingofficeMoveDown(event);
        }

        @FXML
        private void ranchMoveLeft(MouseEvent event) {
            trainStationMoveDown(event);
        }

        @FXML
        private void ranchMoveRight(MouseEvent event) {
            SaloonMoveDown(event);
        }

        @FXML
        private void bankMoveUp(MouseEvent event) {
            trailerMoveLeft(event);
        }

        @FXML
        private void bankMoveDown(MouseEvent event) {
            secrethideoutMoveRight(event);
        }

        @FXML
        private void bankMoveLeft(MouseEvent event) {
            generalstoreMoveDown(event);
        }

        @FXML
        private void bankMoveRight(MouseEvent event) {
            trailerMoveDown(event);
        }

        @FXML
        private void churchMoveUp(MouseEvent event) {
            SaloonMoveDown(event);
        }

        @FXML
        private void churchMoveLeft(MouseEvent event) {
            castingofficeMoveDown(event);
        }

        @FXML
        private void churchMoveRight(MouseEvent event) {
            trailerMoveDown(event);
        }

        @FXML
        private void hotelMoveUp(MouseEvent event) {
            mainStreetMoveDown(event);
        }

        @FXML
        private void hotelMoveLeft(MouseEvent event) {
            SaloonMoveDown(event);
        }

        @FXML
        private void hotelMoveLeft2(MouseEvent event) {
            secrethideoutMoveRight(event);
        }

    @FXML
    private void playerTurnEnd(ActionEvent event) {
        int playerIndex = mod.currentPlayerIndex();
        playersLabel[playerIndex].setTextFill(Color.BLACK);
        nameLabels[playerIndex].setTextFill(Color.BLACK);
        movedThisTurn=false;

        mod.tallyScores();
        for(int i = 0;i<players.length;i++){
          nameLabels[i].setText(Integer.toString(mod.getScore(players[i])));
        }
        pickRoleTable.setDisable(true);
        pickRoleTable.setVisible(false);
        upgradeTable.setDisable(true);
        upgradeTable.setVisible(false);

        disableAllMoves();
        disableAllArrows();

        pickRoleTable.setDisable(true);
        /*
        tUp.setDisable(true);
        tUp.setImage(null);
        tDown.setDisable(true);
        tDown.setImage(null);
        tLeft.setDisable(true);
        tLeft.setImage(null);

        msDown.setDisable(true);
        msDown.setImage(null);
        msDown2.setDisable(true);
        msDown2.setImage(null);
        msLeft.setDisable(true);
        msLeft.setImage(null);

        saUp.setDisable(true);
        saUp.setImage(null);
        saDown.setDisable(true);
        saDown.setImage(null);
        saLeft.setDisable(true);
        saLeft.setImage(null);
        saRight.setDisable(true);
        saRight.setImage(null);*/

        mod.nextTurn();
        currentPlayerState();
    }

    public void getPlayersAmount(int amount) {
        playersAmount = amount;
    }

    public void currentPlayerState() {
        int playerIndex = mod.currentPlayerIndex();
        currentPlayer = players[playerIndex];
        currentPlayerLabel.setText(currentPlayer.getName());
        playersLabel[playerIndex].setTextFill(Color.RED);
        nameLabels[playerIndex].setTextFill(Color.RED);
        dayDisplay.setText(" " + Integer.toString(mod.getDays()));


        File source = new File(imageSources[playerIndex]);
        Image image = new Image(source.toURI().toString());
        currentPlayerIcon.setImage(image);
        rankDisplay.setText(" " + resource.currentRank(currentPlayer));
        moneyDisplay.setText(" " + resource.currentMoney(currentPlayer));
        creditDisplay.setText(" " + resource.currentCredit(currentPlayer));
        tokenDisplay.setText(" " + currentPlayer.getRehearsalToken());


        if(currentPlayer.getinRole()==true){
          moveButton.setDisable(true);
          pickRoleButton.setDisable(true);
          actButton.setDisable(false);
          rehearseButton.setDisable(false);
          if(board.getSceneBudget(board.getScene(currentPlayer.getLocation()))-2<currentPlayer.getRehearsalToken()){
            rehearseButton.setDisable(true);
          }
        }
        if(currentPlayer.getinRole()==false){
          //probably check if role is available
          moveButton.setDisable(false);
          actButton.setDisable(true);
          pickRoleButton.setDisable(false);
          rehearseButton.setDisable(true);
        }
        if(currentPlayer.getinCasting()==false){
          upgradeButton.setDisable(true);
        }
        if(currentPlayer.getinCasting()==true){
          upgradeButton.setDisable(false);
          pickRoleButton.setDisable(true);
          actButton.setDisable(true);
        }
        if((currentPlayer.getLocation()).equals("Trailer")){
          pickRoleButton.setDisable(true);
          actButton.setDisable(true);
        }
        if("".equals(board.getScene(currentPlayer.getLocation()))){
          pickRoleButton.setDisable(true);
        }
    }
    public void dayOver(){
      boolean dayCheck = mod.dayOver();
      if(dayCheck==true){
        dayDisplay.setText(" " + Integer.toString(mod.getDays()));
        moveButton.setDisable(false);
        setCardBacks();
        setCounters();
        disableAllMoves();
        disableAllArrows();
        playerIcons[0].relocate(802, 228);
        playerIcons[1].relocate(853, 228);
        playerIcons[2].relocate(904, 228);
        playerIcons[3].relocate(807, 279);
        playerIcons[4].relocate(863, 279);
        playerIcons[5].relocate(916, 279);
        playerIcons[6].relocate(812, 336);
        playerIcons[7].relocate(859, 336);
        if(mod.gameOver()==true){
          String gameWinner = mod.winner();
          moveButton.setDisable(true);
          endTurnButton.setDisable(true);
          winnerPane.setVisible(true);
          winnerPane.setStyle("-fx-background-color: #C0C0C0;");
          winner.setText(gameWinner + " wins!");
        }
      }

    }
    public void setPlayerIcons() {
        for(int i = 0; i < playersAmount; i++) {
            File source = new File(imageSources[i]);
            Image image = new Image(source.toURI().toString());
            playerIcons[i].setImage(image);
        }
    }

    public void setCardBacks(){
      File sourceBack = new File("src/CardBack-small.jpg");
      Image imageBack = new Image(sourceBack.toURI().toString());
      mainStreetCard.setImage(imageBack);
      saloonCard.setImage(imageBack);
      hotelCard.setImage(imageBack);
      bankCard.setImage(imageBack);
      secretHideoutCard.setImage(imageBack);
      ranchCard.setImage(imageBack);
      generalStoreCard.setImage(imageBack);
      jailCard.setImage(imageBack);
      trainStationCard.setImage(imageBack);
      churchCard.setImage(imageBack);
    }

    public void setCounters(){
      File sourceCounter = new File("src/shotCounter.png");
      Image shotcounter = new Image(sourceCounter.toURI().toString());
      msToken1.setImage(shotcounter);
      msToken2.setImage(shotcounter);
      msToken3.setImage(shotcounter);
      sToken1.setImage(shotcounter);
      sToken2.setImage(shotcounter);
      bToken.setImage(shotcounter);
      hToken1.setImage(shotcounter);
      hToken2.setImage(shotcounter);
      hToken3.setImage(shotcounter);
      cToken1.setImage(shotcounter);
      cToken2.setImage(shotcounter);
      shToken1.setImage(shotcounter);
      shToken2.setImage(shotcounter);
      shToken3.setImage(shotcounter);
      rToken1.setImage(shotcounter);
      rToken2.setImage(shotcounter);
      gsToken1.setImage(shotcounter);
      gsToken2.setImage(shotcounter);
      tsToken1.setImage(shotcounter);
      tsToken2.setImage(shotcounter);
      tsToken3.setImage(shotcounter);
      jToken.setImage(shotcounter);
    }

    public void getModerator(Moderator input) throws ParserConfigurationException {
        mod = input;
        mod.initializePlayers();
        board = mod.getGameBoard();
        resource = Resources.getI();
        locationInitialize();
        disableAllMoves();
    }

    public void getNames(String[] input) {
        names = input;
    }

    public void displayNames() {
      mod.tallyScores();
      for(int i = 0; i < playersAmount; i++) {
          nameLabels[i].setText(Integer.toString(mod.getScore(players[i])));
      }
    }

    public void displayPlayers() {
        for(int i = 0; i < playersAmount; i++) {
           playersLabel[i].setText(names[i]);
       }
    }

    public void getPlayers() {
        players = mod.getPlayerArray();
    }

    public void getIconSources(String[] input) {
        imageSources = input;
    }

    private void locationInitialize() {
        locations = new String[locationAmount];

        for(int i = 0; i < locationAmount; i++) {
            locations[i] = board.locationAtIndex(i);
        }
    }

    private void disableAllArrows() {
        tUp.setImage(null);
        tDown.setImage(null);
        tLeft.setImage(null);


        msDown.setImage(null);
        msDown2.setImage(null);
        msLeft.setImage(null);


        saUp.setImage(null);
        saDown.setImage(null);
        saLeft.setImage(null);
        saRight.setImage(null);

        jDown.setImage(null);
        jLeft.setImage(null);
        jRight.setImage(null);

        gsUp.setImage(null);
        gsDown.setImage(null);
        gsLeft.setImage(null);
         gsRight.setImage(null);

        tsRight.setImage(null);
        tsRight2.setImage(null);
        tsDown.setImage(null);

        coUp.setImage(null);
        coRight.setImage(null);
        coDown.setImage(null);

        rUp.setImage(null);
        rDown.setImage(null);
        rLeft.setImage(null);
        rRight.setImage(null);

        shUp.setImage(null);
        shUp2.setImage(null);
        shRight.setImage(null);

        bUp.setImage(null);
        bDown.setImage(null);
        bLeft.setImage(null);
        bRight.setImage(null);

        cUp.setImage(null);
        cRight.setImage(null);
        cLeft.setImage(null);

        hUp.setImage(null);
        hLeft.setImage(null);
        hLeft2.setImage(null);
    }

    private void disableAllMoves() {
        tUp.setDisable(true);
        tDown.setDisable(true);
        tLeft.setDisable(true);

        msDown.setDisable(true);
        msDown2.setDisable(true);
        msLeft.setDisable(true);

        saUp.setDisable(true);
        saDown.setDisable(true);
        saLeft.setDisable(true);
        saRight.setDisable(true);

        jDown.setDisable(true);
        jLeft.setDisable(true);
        jRight.setDisable(true);

        gsUp.setDisable(true);
        gsDown.setDisable(true);
        gsLeft.setDisable(true);
         gsRight.setDisable(true);

        tsRight.setDisable(true);
        tsRight2.setDisable(true);
        tsDown.setDisable(true);

        coUp.setDisable(true);
        coRight.setDisable(true);
        coDown.setDisable(true);

        rUp.setDisable(true);
        rDown.setDisable(true);
        rLeft.setDisable(true);
        rRight.setDisable(true);

        shUp.setDisable(true);
        shUp2.setDisable(true);
        shRight.setDisable(true);

        bUp.setDisable(true);
        bDown.setDisable(true);
        bLeft.setDisable(true);
        bRight.setDisable(true);

        cUp.setDisable(true);
        cRight.setDisable(true);
        cLeft.setDisable(true);

        hUp.setDisable(true);
        hLeft.setDisable(true);
        hLeft2.setDisable(true);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        playersLabel = new Label[8];
        playersLabel[0] = player1Label;
        playersLabel[1] = player2Label;
        playersLabel[2] = player3Label;
        playersLabel[3] = player4Label;
        playersLabel[4] = player5Label;
        playersLabel[5] = player6Label;
        playersLabel[6] = player7Label;
        playersLabel[7] = player8Label;

        nameLabels = new Label[8];
        nameLabels[0] = name1Label;
        nameLabels[1] = name2Label;
        nameLabels[2] = name3Label;
        nameLabels[3] = name4Label;
        nameLabels[4] = name5Label;
        nameLabels[5] = name6Label;
        nameLabels[6] = name7Label;
        nameLabels[7] = name8Label;

        playerIcons = new ImageView[8];
        playerIcons[0] = player1Icon;
        playerIcons[1] = player2Icon;
        playerIcons[2] = player3Icon;
        playerIcons[3] = player4Icon;
        playerIcons[4] = player5Icon;
        playerIcons[5] = player6Icon;
        playerIcons[6] = player7Icon;
        playerIcons[7] = player8Icon;

        arrowSources = new String[4];
        arrowSources[0] = "src/singleUpArrow.png";
        arrowSources[1] = "src/singleDownArrow.png";
        arrowSources[2] = "src/singleLeftArrow.png";
        arrowSources[3] = "src/singleRightArrow.png";
        setCardBacks();
        setCounters();

    }

}
