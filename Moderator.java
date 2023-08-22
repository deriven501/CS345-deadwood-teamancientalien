import java.lang.Math;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import javax.xml.parsers.ParserConfigurationException;
import java.util.HashMap;
import java.util.Map;


public class Moderator{

  Player[] playerArray;
  Player currentPlayer;
  int days = 3;
  int scenesOnBoard=10;
  boolean currentDayEnd = false;
  GameBoard gb = new GameBoard();
  //playerQueue
  Queue<Player> qp = new LinkedList<>();
  Map<Player, String> rolesTaken = new HashMap<Player, String>();
  Map<Player, Integer> scoreBoard = new HashMap<Player, Integer>();


  //singleton
  private static Moderator singleInstance = null;
  private Moderator() throws ParserConfigurationException{
    }
  public static Moderator getInstance() throws ParserConfigurationException
    {
        if (singleInstance == null){
            singleInstance = new Moderator();
        }
        return singleInstance;
    }


  public Player[] getPlayers(){
    return playerArray;
  }

//puts names into a Player array
  public void getPlayerAmount(int playerAmnt) {
	  playerArray = new Player[playerAmnt];
  }


  public void getNames(int arrayPosition, String name){
	    playerArray[arrayPosition] = new Player(name);
  }

//sets there resources on given conditions,
  public void initializePlayers()throws ParserConfigurationException{
    Resources r = Resources.getI();
    //initialize resourse hashmaps
    r.initialize();
    if(playerArray.length > 2){
      days = 4;
        if(playerArray.length > 4){
          for(int i = 0;i<5;i++){
            r.giveCredit(playerArray[i], 2);
          }
          if(playerArray.length > 5){
            for(int i = 0;i<5;i++){
              r.giveCredit(playerArray[i], 2);
            }
            r.giveCredit(playerArray[5], 4);
            if(playerArray.length > 6){
              for(int i = 0;i<6;i++){
                r.giveCredit(playerArray[i], -4);
              }
              for(int i = 0;i<7;i++){
                r.rankUp(playerArray[i]);
              }
              if(playerArray.length > 7){
                r.rankUp(playerArray[7]);
              }
            }
          }
        }
      }
    //initialize taken roles hashmap and creates player queue for
    //rotation and sets top player of queue to currentPlayer
    for (int i=0; i<playerArray.length; i++){
      rolesTaken.put(playerArray[i], playerArray[i].getRole());
      qp.add(playerArray[i]);
      currentPlayer = qp.peek();
    }
    //assigns scenes randomly
    gb.assignScene();
  }

//will rotate players through a queue
  public void rotateTurn(){
    qp.add(currentPlayer);
    qp.remove();
  }

//updates current player
  public Player nextTurn(){
    rotateTurn();
    currentPlayer = qp.peek();
    return currentPlayer;
  }
  //Index of current player
  public int currentPlayerIndex(){
    int index = -1;
    for(int i = 0;i<playerArray.length;i++){
      if (currentPlayer.getName().equals(playerArray[i].getName())){
        index = i;
      }
    }
    return index;
  }


//given a certain rank, if the player has only enough of one resources
//if not quickcastwill return false
public boolean quickCast(Player player, int rnk){
  Resources r = Resources.getI();
  int[] availableUpgrades = upgradeAvailable(player);
  boolean doCredit=false;
    //hashmaps for upgrade costs
    Map<Integer, Integer> moneySwap = new HashMap<Integer, Integer>();
    Map<Integer, Integer> creditSwap = new HashMap<Integer, Integer>();
    moneySwap.put(2,4);
    moneySwap.put(3,10);
    moneySwap.put(4,18);
    moneySwap.put(5,28);
    moneySwap.put(6,40);
    creditSwap.put(2,5);
    creditSwap.put(3,10);
    creditSwap.put(4,15);
    creditSwap.put(5,20);
    creditSwap.put(6,25);
            if(moneySwap.get(rnk)>r.currentMoney(player)){
               doCredit =true;
            }
            if(creditSwap.get(rnk)>r.currentCredit(player)){
                r.giveMoney(player, -1*moneySwap.get(rnk));
                r.setRank(player,rnk);
            }
            if(doCredit==true){
                  r.giveCredit(player, -1*creditSwap.get(rnk));
                  r.setRank(player,rnk);
            }
            if((creditSwap.get(rnk)<=r.currentCredit(player)) &&
              (moneySwap.get(rnk)<=r.currentMoney(player))){
                return true;
              }
              return false;
}

  //if player can upgrade with money or credit then they choose
  public void cast(Player player, int rnk, String choice){
    Map<Integer, Integer> moneySwap = new HashMap<Integer, Integer>();
    Map<Integer, Integer> creditSwap = new HashMap<Integer, Integer>();
    moneySwap.put(2,4);
    moneySwap.put(3,10);
    moneySwap.put(4,18);
    moneySwap.put(5,28);
    moneySwap.put(6,40);
    creditSwap.put(2,5);
    creditSwap.put(3,10);
    creditSwap.put(4,15);
    creditSwap.put(5,20);
    creditSwap.put(6,25);
    Resources r = Resources.getI();
    if(choice.equalsIgnoreCase("Money")){
      r.giveMoney(player, -1*moneySwap.get(rnk));
    }
    if(choice.equalsIgnoreCase("Credit")){
      r.giveCredit(player, -1*creditSwap.get(rnk));
    }
    r.setRank(player,rnk);
  }


  //calculates an array of upgrades depending in player rank, money, and credit
  public int[] upgradeAvailable(Player player){
    Resources r = Resources.getI();
    int[] upgrades = new int[6];
    if(r.currentMoney(player)<4 && r.currentCredit(player)<5){
    }
    if((r.currentMoney(player)>3 || r.currentCredit(player)>4) && (r.currentRank(player)<=1)){
      upgrades[1]=2;
    }
    if((r.currentMoney(player)>9 || r.currentCredit(player)>9) && (r.currentRank(player)<=2)){
      upgrades[2]=3;
    }
    if((r.currentMoney(player)>17 || r.currentCredit(player)>14) && (r.currentRank(player)<=3)){
      upgrades[3]=4;
    }
    if((r.currentMoney(player)>27 || r.currentCredit(player)>19) && (r.currentRank(player)<=4)){
      upgrades[4]=5;
    }
    if((r.currentMoney(player)>39 || r.currentCredit(player)>24) && (r.currentRank(player)<=5)){
      upgrades[5]=6;
    }
    return upgrades;
    }

//roles that a player can choose given rank, and open roles
   public String[] availableRoles(Player player){
     Resources r = Resources.getI();
     String[] extraRolls = gb.setExtraRoles(player.getLocation());
     int amntExtras = extraRolls.length;
     String[] onCardRolls = gb.setSceneRoles(player.getLocation());
     int amntOnCard = onCardRolls.length;
     int totalRoles = amntOnCard+amntExtras;
     int roleTally = 0;
     String[] openRoles = new String[totalRoles];
     for(int i = 0; i < amntExtras ;i++){
       if(roleOpen(extraRolls[i])==true && gb.getExtraRoleLevel(player.getLocation(), extraRolls[i]) <=
          r.currentRank(player)){
        openRoles[i] = extraRolls[i];
       }
     }
     for(int i = 0; i < amntOnCard ;i++){
         if(roleOpen(onCardRolls[i])==true && gb.getSceneRoleLevel(gb.getScene(player.getLocation()), onCardRolls[i]) <=
            r.currentRank(player)){
            openRoles[i+amntExtras] = onCardRolls[i];
         }
     }
     return openRoles;
    }

//allRoles on Location
     public String[] allRoles(Player player){
       String[] extraRolls = gb.setExtraRoles(player.getLocation());
       int amntExtras = extraRolls.length;
       String[] onCardRolls = gb.setSceneRoles(player.getLocation());
       int amntOnCard = onCardRolls.length;
       int totalRoles = amntOnCard+amntExtras;
       String[] allRoles = new String[totalRoles];
       for(int i = 0; i < amntExtras ;i++){
           allRoles[i] = extraRolls[i];
       }
       for(int i = 0; i < amntOnCard ;i++){
           allRoles[i+amntExtras] = onCardRolls[i];
       }
       return allRoles;
       }

//sets role in hashmap
       public void setRole(Player player, String choice){
         player.setRole(choice);
         rolesTaken.put(player,choice);
         player.setinRole(true);
       }


//checks to see if anybody has taken a role from the rolestaken hashmap
       public boolean roleOpen(String role){
         if(role==null){
           return false;
         }
         boolean open = true;
         for(int i = 0; i<playerArray.length;i++){
           if(role.equals(rolesTaken.get(playerArray[i]))){
             open = false;
           }
         }
         return open;
       }
//returns weather act was succesful or not, handles money, determines whether
// player gets payout and sees if day is over
       public String act(Player player){
         Resources r = Resources.getI();
         String actReturn = "";
         String role = player.getRole();
         int movieBudget = gb.getSceneBudget(gb.getScene(player.getLocation()));
         int dieRoll = rollDie();
         int rollComparison = dieRoll+ player.getRehearsalToken();
         if(rollComparison<movieBudget){
           if(gb.isRoleExtra(player.getRole())==true){
             r.giveMoney(player, 1);
           }
         }
         if(rollComparison>=movieBudget){
           actReturn="Success";
           gb.removeShotCounter(player.getLocation());
           if(gb.isRoleExtra(player.getRole())==true){
             r.giveMoney(player,1);
             r.giveCredit(player,1);
           }
           if(gb.isRoleExtra(player.getRole())==false){
             r.giveCredit(player,2);
           }
           if(gb.getShotCounters(player.getLocation())==0){
             boolean pay = false;
             for(int i = 0; i < gb.setSceneRoles(player.getLocation()).length;i++){
               if(roleOpen(gb.setSceneRoles(player.getLocation())[i])==false){
                 pay=true;
               }
             }
             if(pay==true){
               payout();
             }
             for(int i = 0; i<playerArray.length;i++){
               if(player.getLocation().equals(playerArray[i].getLocation())){
                 playerArray[i].setRole("Defalt");
                 playerArray[i].setinRole(false);
                 playerArray[i].resetToken();
               }
             }
             gb.removeScene(player.getLocation());
             scenesOnBoard--;
             if(scenesOnBoard==1){
               currentDayEnd=true;
             }
           }
         }
         return actReturn;
       }


       public int scenesLeft(){
         return scenesOnBoard;
       }

//Resets player conditions, shuffles the deck and deals again, resets counters, and will return
//and will return true if day is over
       public boolean dayOver(){
         if(currentDayEnd==true){
           for(int i=0;i<playerArray.length;i++){
             playerArray[i].setLocation("Trailer");
             playerArray[i].setRole("Defalt");
             playerArray[i].setinRole(false);
             playerArray[i].setinCasting(false);
             playerArray[i].resetToken();
             rolesTaken.put(playerArray[i],null);
           }
           scenesOnBoard=10;
           gb.resetCounter();
           gb.removeAllScene();
           gb.shuffle();
           gb.assignScene();
           currentDayEnd=false;
           days--;
           return true;
         }
         return false;
       }


       public boolean gameOver(){
         if(days==0){
           return true;
         }
         return false;
       }

//checks to see who scored most on scoreboard
       public String winner(){
         tallyScores();
         String winner = playerArray[0].getName();
         for(int i = 1;i<playerArray.length;i++){
           if(scoreBoard.get(playerArray[i])>scoreBoard.get(playerArray[i-1])){
             winner=playerArray[i].getName();
           }
         }
         return winner;
       }

//Payout the players that were on card
       public void payout(){
         int[] amntDice = new int[gb.getSceneBudget(gb.getScene(currentPlayer.getLocation()))];
         Player[] playersOnCard = new Player[gb.setSceneRoles(currentPlayer.getLocation()).length];
         String[] sortedOnCardRoles = gb.setSceneRoles(currentPlayer.getLocation());
         //Randomizes correct amount of dice
         for(int i =0; i<amntDice.length; i++){
           amntDice[i]=rollDie();
         }
         //sorts dice high to low
         for(int n = amntDice.length-1; n > 0; n--){
           for(int i=0; i<n; i++){
             if(amntDice[i]<amntDice[i+1]){
               int temp = amntDice[i];
               amntDice[i] = amntDice[i+1];
               amntDice[i+1] = temp;
             }
           }
         }

         //sorts the on card roles by role level high to low
         for(int i=sortedOnCardRoles.length-1; i>0; i--){
           for(int j=0; j<i; j++){
             if(gb.getSceneRoleLevel(gb.getScene(currentPlayer.getLocation()), sortedOnCardRoles[j]) <
             gb.getSceneRoleLevel(gb.getScene(currentPlayer.getLocation()), sortedOnCardRoles[j+1]))
             {
               String temp = sortedOnCardRoles[j];
               sortedOnCardRoles[j] = sortedOnCardRoles[j+1];
               sortedOnCardRoles[j+1] = temp;
             }
           }
         }
         //if player is on a on card roll this will assign them to a new player array
         //i amnt of scenes j amnt of players if playerj role == role i, put that player into the array at location i
         for(int i =0; i<playersOnCard.length; i++){
           for(int j=0;j<playerArray.length;j++){
             if(playerArray[j]!=null){
               if(playerArray[j].getRole().equals(sortedOnCardRoles[i])){
                 playersOnCard[i] = playerArray[j];
               }
             }
           }
         }
         //die mod player would give position
         Resources r = Resources.getI();
         for(int p = 0; p<amntDice.length; p++){
           if(playersOnCard[p%playersOnCard.length]!=null){
             r.giveMoney(playersOnCard[p%playersOnCard.length], amntDice[p]);
           }
         }
         for(int i = 0; i<playerArray.length;i++){
           for(int j=0;j< gb.setExtraRoles(currentPlayer.getLocation()).length;j++){
             if(playerArray[i].getRole().equals(gb.setExtraRoles(currentPlayer.getLocation())[j])){
               r.giveMoney(playerArray[i], gb.getExtraRoleLevel(currentPlayer.getLocation(), gb.setExtraRoles(currentPlayer.getLocation())[j]));
             }
           }
         }
       }


       public void rehearse(){
         int movieBudget = gb.getSceneBudget(gb.getScene(currentPlayer.getLocation()));
         currentPlayer.rehearse();
       }


       public int rollDie(){
         return (int)(Math.random()*6)+1;
       }

//tallies scores and inserts them into scoreboard
       public void tallyScores(){
         Resources r = Resources.getI();
         for(int i = 0;i<playerArray.length;i++){
           scoreBoard.put(playerArray[i],r.currentRank(playerArray[i])*5+r.currentMoney(playerArray[i])+r.currentCredit(playerArray[i]));
         }
       }

       public int getScore(Player player){
         return scoreBoard.get(player);
       }


       public int getDays(){
         return days;
       }


       public Player[] getPlayerArray(){
         return playerArray;
       }


       public GameBoard getGameBoard(){
         return gb;
       }
     }
