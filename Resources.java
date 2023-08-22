import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;

public class Resources{
  private static Resources singleI = null;
  private Resources(){
    }
  public static Resources getI()
    {
        if (singleI == null){
            singleI= new Resources();
        }
        return singleI;
    }

  Map<Player, Integer> playerRank = new HashMap<Player, Integer>();
  Map<Player, Integer> playerMoney = new HashMap<Player, Integer>();
  Map<Player, Integer> playerCredit = new HashMap<Player, Integer>();


  public void initialize()throws ParserConfigurationException{
    Moderator mod = Moderator.getInstance();
    for(int i = 0; i < mod.getPlayers().length; i++){
      playerRank.put(mod.getPlayers()[i], 1);
      playerMoney.put(mod.getPlayers()[i], 0);
      playerCredit.put(mod.getPlayers()[i], 0);

    }
  }

  public void giveMoney(Player name, int money){
    playerMoney.compute(name, (key, val) -> (val == null)? 0: val + money);
  }
  public void giveCredit(Player name, int credit){
    playerCredit.compute(name, (key, val) -> (val == null)? 0: val + credit);
  }
  public void rankUp(Player name){
    playerRank.compute(name, (key, val) -> (val == null)? 1: val + 1);
  }
  public void setRank(Player name, int rank){
    playerRank.put(name, rank);
  }

  public int currentRank(Player name){
    return playerRank.get(name);
  }
  public int currentMoney(Player name){
    return playerMoney.get(name);
  }
  public int currentCredit(Player name){
    return playerCredit.get(name);
  }
  public void checkResourse(Player name){
    Resources r = Resources.getI();
    int[] check = {currentRank(name), currentMoney(name), currentCredit(name)};
    for(int i =0;i<check.length;i++){
      System.out.println(check[i]);
    }
    System.out.println();
    System.out.println();
  }
}
