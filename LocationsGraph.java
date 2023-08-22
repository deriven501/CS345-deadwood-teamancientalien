/*
Duy (David) Nghiem
CS 345
Graph of Locations for the GameBoard Class. 
The GameBoard class obtain and return information 
about the board through this graph
 */

import java.util.ArrayList;
import java.util.HashMap; 
import java.util.Map; 
import java.util.*; 

public class LocationsGraph {
    private int vertices;
    private Location locationSpots[];
    private int spotsLeft;
    
    
    //Primary Constructors 
    public LocationsGraph(int vertices) {
        //locationList = new int[vertices][vertices];
        locationSpots = new Location[vertices];
        this.vertices = vertices;     
        spotsLeft = 0;
    }
    
    
    //Establish the link of one location to other adjacent location. 
    //previous indexes of desired link location must be specify so that
    //the location object can reference to the other location object that it was link to
    public void connectVerticesWithEdge(String name1, String name2) {
        if(!isNeighborInVertex(name1, name2)) { //Do nothing if a link already been established between two locations
                if(vertexExist(name1) && vertexExist(name2)) {
                    //locationList[origin][destination] = 1;
                    //locationList[destination][origin] = 1;  
                    getVertex(name1).neighborVertices.add(getVertex(name2));
                    getVertex(name2).neighborVertices.add(getVertex(name1));      
            } 
        }      
    }
    
    //Show all location and its neighbor    
    public void showLocations() {
        
        for(int i = 0; i < vertices; i++) {
            if(vertexExist(i)) {
                System.out.print(locationSpots[i].getName() + ", Takes: " + locationSpots[i].getShots() + " - adjacent locations: ");
                for(int j = 0; j < locationSpots[i].neighborVertices.size(); j++) {
                    System.out.print(locationSpots[i].neighborVertices.get(j).getName() + " ");
                }
                System.out.println();
            }
            
            System.out.println();
        }
    }
    //Show all extra roles from all location on the board
    public void showRolesOnBoard() {
        for(int i = 0; i < vertices; i++) {
            if(vertexExist(i)) {
                Iterator roleIterator = getVertex(i).getRoles().entrySet().iterator();
                System.out.print(locationSpots[i].getName() + " - Extra Roles: ");
                while(roleIterator.hasNext()) {
                    Map.Entry role = (Map.Entry) roleIterator.next();
                    int level = (int) role.getValue();
                    System.out.print("[" + role.getKey() + ",level: " + level + "]  ");
                }
                System.out.println();
            }
            
            System.out.println();
        }
    }
    
    //Show extra roles and its correspond dialogue
    public void showRolesAndDialogues() {
        for(int i = 0; i < vertices; i++) {
            if(vertexExist(i)) {
                Iterator roleIterator = getVertex(i).getRoles().entrySet().iterator();
                System.out.println(locationSpots[i].getName() + " - Extra Roles: ");
                while(roleIterator.hasNext()) {
                    Map.Entry role = (Map.Entry) roleIterator.next();
                    System.out.print(role.getKey() + " ");
                    String dialogue = getVertex(i).getDialogues().get(role.getKey());
                    System.out.print(" - Dialogue: " + "\"" + dialogue + "\"");
                    System.out.println();
                }
                System.out.println();
            }
            
            System.out.println();
        }
    }
    //Add the correct amount of default counters for each suitable location
    public void addShotCounters(String location, int counter) {
        getVertex(location).addShots(counter);
    }
    
    //Add a scene based on location indicated by index from a loop
    public void addScene(Scene scene, int locationIndex) {
        getVertex(locationIndex).addScene(scene);
    }
    
    //Remove a counter from a location
    public void removeCounter(String location) {
        if(vertexExist(location)) {
            getVertex(location).removeShot();
            
            if(getVertex(location).getShots() < 0) {
                getVertex(location).keepZeroShot();
            }
        }
    }
    
    public void removeScene(String location) {
        if(getVertex(location).getScene() != null) {
            getVertex(location).sceneDelete();
        }
    }
    
    public void removeAllScene() {
        for(int i = 0; i < locationSpots.length; i++) {
            if(getVertex(i).getScene() != null) {
            getVertex(i).sceneDelete();
            }
        }
    }
    
    //Return the current counter from a location
    public int getCounter(String location) {
        int counter = 0;
        if(vertexExist(location)) {
            counter = getVertex(location).getShots();
        }
        
        return counter;
    }
    
    //Get a scene object from a location
    public Scene getLocationScene(String location) {
        return getVertex(location).getScene();
    }
    
    //Indicate whether a role exist or not
    public boolean extraRoleExist(String role) {
        for(int i = 0; i < locationSpots.length; i++) {
            HashMap<String, Integer> roles = getVertex(locationSpots[i].getName()).getRoles();
            Iterator roleIterator = roles.entrySet().iterator();
            
            while(roleIterator.hasNext()) {
                Map.Entry roleElement = (Map.Entry) roleIterator.next();
                if(roleElement.getKey().equals(role)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    //Return array of extra roles from a location/set
    public String[] setExtraRoles(String set) {
        String[] extraRoles = null;
        HashMap<String, Integer> roles = getVertex(set).getRoles();
        Iterator roleIterator = roles.entrySet().iterator();
        extraRoles = new String[roles.size()];
        int index = 0;
        while(roleIterator.hasNext()) {
            Map.Entry role = (Map.Entry) roleIterator.next();
            extraRoles[index] = (String) role.getKey();
            index++;
        }
        
        return extraRoles;
    }
    
    //Return array of all roles from a scene of a location
    public String[] sceneRoles(String location) {
        String[] roles = null;
        if(getVertex(location).getScene() != null) {
            roles = getVertex(location).getScene().getRoles();
        }
        return roles;
    }
    
    //Return a level from a extra role from a location/set
    public int getRoleLevel(String set, String role) {
        int level = 0;
        if(vertexExist(set)) {
            level = getVertex(set).getLevel(role);
        }
        
        return level;
    }
    
    //Return the dialogue string from a extra role from a location
    public String getRoleDialogue(String set, String role) {
        String dialogue = "";
        if(vertexExist(set)) {
            dialogue = getVertex(set).getDialogue(role);
        }
        
        return dialogue;
        
    }
    
    public String[] getAdjacentLocations(String name) {
        String[] adjacentLocations = null;
        if(vertexExist(name)) {
            adjacentLocations = new String[getVertex(name).neighborVertices.size()];
            for(int i = 0; i < getVertex(name).neighborVertices.size(); i++) {
                adjacentLocations[i] = getVertex(name).neighborVertices.get(i).getName();
            }
        }   
        
        return adjacentLocations;
    }
    
    //Ensure no duplicate link is establish for any particular pair of location
    private boolean isNeighborInVertex(String name, String neighborName) {
        if(vertexExist(name)) {
            for(int i = 0; i < getVertex(name).neighborVertices.size(); i++) {
                if(getVertex(name).neighborVertices.get(i).getName().equals(neighborName)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    //Add a new location object during creation of the GameBoard class
    public void addVertex(String name) {
        if(spotsLeft < vertices) {
            locationSpots[spotsLeft] = new Location(name);
            spotsLeft++;
        }
    }
    
    //Add new extra role and its level for a location during creation of the GameBoard class
    public void addRole(String set, String role, int level) {
        getVertex(set).addRole(role, level);
    }
    
    //Add new extra role dialogue and its level for a location during creation of the GameBoard class
    public void addDialogue(String set, String role, String dialogue) {
        getVertex(set).addDialogue(role, dialogue);
    }
    
    //Return the amount of locations on the board
    public int locationsAmount() {
        return locationSpots.length;
    }
    
    //Internal methods of LocationsGraph, get a specific location object
    //based on its name
    private Location getVertex(String name) {
        for(int i = 0; i < vertices; i++) {
            if(locationSpots[i] != null && locationSpots[i].getName().equals(name)) {
                return locationSpots[i]; 
            }
        }
        
        return null;
    }
    
    //Internal methods of LocationsGraph, get a specific location object
    //based on a loop index
    private Location getVertex(int index) {
        if(vertexExist(index)) {
            return locationSpots[index];
        }
        return null;
    }
    
    //Return the name of a location based on a loop index
    public String locationName(int index) {
        if(vertexExist(index)) {
            return locationSpots[index].getName();
        }
        return "";
    }
    
    //Indicate whether a location with this name exist or not
    public boolean vertexExist(String name) {
        for(int i = 0; i < vertices; i++) {
            if(locationSpots[i] != null && locationSpots[i].getName().equals(name)) {
                return true;
            }
        }       
        return false;
    }
    
    /*public boolean vertexExist(int index) {
        for(int i = 0; i < vertices; i++) {
            if(locationSpots[i] != null) {
                return true;
            }
        }       
        return false;
    }*/
    
    ////Indicate whether a location with this index exist or not
    public boolean vertexExist(int index) {     
        if(locationSpots[index] != null) {
                return true;
        }
               
        return false;
    }
    //Since each location had a lot of data internal to it
    //It is decided that Location should be an internal class exclusive 
    //LocationsGraph class. Only LocationsGraph have access to Location class
    private class Location {
           String name;
           int shotCounter;
           ArrayList<Location> neighborVertices;
           HashMap<String, Integer> extraRoles;
           HashMap<String, String> rolesDialogues;
           Scene scene;
           
           public Location(String name) {
               this.name = name;
               shotCounter = 0;
               neighborVertices = new ArrayList<Location>();
               extraRoles = new HashMap<>();
               rolesDialogues = new HashMap<>();
           }
           
             
           public void addShots(int counter) {
               shotCounter = counter;
           }
           
           public void addScene(Scene scene) {
               this.scene = scene;
           }
           
           public void addRole(String role, int level) {
               extraRoles.put(role, level);
           }
           
           public void addDialogue(String role, String dialogue) {
               rolesDialogues.put(role, dialogue);
           }
           
           public void removeShot() {
               shotCounter--;
           }
           
           public void keepZeroShot() {
               shotCounter = 0;
           }
           
           public String getName() {
               return name;
           }
           
           public String getDialogue(String role) {
               return rolesDialogues.get(role);
           }
           
           public int getShots() {
               return shotCounter;
           }
           
           public int getLevel(String role) {
               return extraRoles.get(role);
           }
           
           public HashMap<String, Integer> getRoles() {
               return extraRoles;
           }
           
           public HashMap<String, String> getDialogues() {
               return rolesDialogues;
           }
           
           public Scene getScene() {
               return scene;
           }
           
           public void sceneDelete() {
               scene = null;
           }
           
        
    }
}
