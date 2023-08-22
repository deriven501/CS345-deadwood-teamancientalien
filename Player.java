
public class Player{
  String name;
  String location;
  String role;
  boolean inRole;
  int rehearseToken;
  boolean inCasting;
  //constructor
  public Player(String playerName){
    name = playerName;
    location = "Trailer";
    role = "Defalt";
    inRole = false;
    inCasting = false;
    rehearseToken = 0;
  }
  public void setLocation(String location){
    this.location = location;
  }
  public void setRole(String role){
    this.role = role;
  }
  public void setinRole(boolean inRole){
    this.inRole = inRole;
  }
  public void setinCasting(boolean inCasting){
    this.inCasting = inCasting;
  }
  public void rehearse(){
    rehearseToken++;
  }
  public void resetToken(){
    rehearseToken=0;
  }
  public String getName(){
    return this.name;
  }
  public String getLocation(){
    return this.location;
  }
  public String getRole(){
    return this.role;
  }
  public boolean getinRole(){
    return this.inRole;
  }
  public boolean getinCasting(){
    return this.inCasting;
  }
  public int getRehearsalToken(){
    return this.rehearseToken;
  }
}
