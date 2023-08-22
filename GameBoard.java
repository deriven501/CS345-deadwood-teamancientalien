/*
Duy (David) Nghiem
CS 345
The Board Class, represent the entire board of the game.
This class is responsible for maintaning data of all the locations
and update it if needed.
 */
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Random;
import java.util.ArrayList;


public class GameBoard {
    private LocationsGraph locations;
    private Scene scenesList[];
    private ParseXML boardXML;

    //Constructors
    public GameBoard()
    throws ParserConfigurationException{
        locations = new LocationsGraph(12);
        scenesList = new Scene[40];
        boardXML = new ParseXML();
        setUpBoard();
        sceneInitialization();

    }

    //Display All Locations and its adjacent neighbors
    public void showEntireBoard() {
        locations.showLocations();
        System.out.println();
    }

    //Show all extra roles on all locations
    public void showAllExtraRoles() {
        locations.showRolesOnBoard();
    }

    //Show all roles and its dialogues
    public void showExtraRolesDialogues() {
        locations.showRolesAndDialogues();
    }


    //Show all scenes that been initialized
    public void shuffle() {
        for(int i = 0; i < scenesList.length; i++) {
            scenesList[i].setFaceDown();
        }
    }


    //Return the level of a extra role on a location/set
    public int getExtraRoleLevel(String location, String role) {
        return locations.getRoleLevel(location, role);
    }

    //Return the level of a role from a scene
    public int getSceneRoleLevel(String scene, String role) {
        int level = 0;
        for(int i = 0; i < scenesList.length; i++) {
            if(scene.equals(scenesList[i].getName())) {
                String[] roles = scenesList[i].getRoles();
                for(int j = 0; j < roles.length; j++) {
                    if(role.equals(roles[j])) {
                        level = scenesList[i].getRoleLevel(role);
                    }
                }
            }
        }

        return level;
    }


     //Display all roles from every scenes
    public void showAllRolesAndScenes() {
        for(int i = 0; i < scenesList.length; i++) {
            System.out.println("Scene: " + scenesList[i].getName());
            scenesList[i].getAllRoles();
            System.out.println();
        }
    }

    //Return the int value of the budget of a scene
    public int getSceneBudget(String scene) {
        int budget = 0;
        for(int i = 0; i < scenesList.length; i++) {
            if(scene.equals(scenesList[i].getName())) {
                budget = scenesList[i].getBudget();
            }
        }

        return budget;
    }

    public String getSceneImg(String scene) {
        String img = "";
        for(int i = 0; i < scenesList.length; i++) {
            if(scene.equals(scenesList[i].getName())) {
                img = scenesList[i].getImg();
            }
        }

        return img;
    }

    //Indicate whether a role is an offcard(extra) or on-card
    public boolean isRoleExtra(String role) {
        return locations.extraRoleExist(role);
    }

    //Flip the card up
    public void revealScene(String location) {
        locations.getLocationScene(location).sceneReveal();
    }

    //return the the current shot counter of a location
    public int getShotCounters(String location) {
        return locations.getCounter(location);
    }


    public void removeShotCounter(String location) {
        locations.removeCounter(location);
    }

    //Indicate whether a scene of a location is reveal or not
    public boolean isSceneReveal(String location) {
        boolean status = false;
        if(locations.getLocationScene(location) != null) {
            status = locations.getLocationScene(location).sceneStatus();
        }
        return status;
    }

    //All scene is remove from current location data when a day is over
    public void removeAllScene() {
        locations.removeAllScene();
    }

    //Remove a scene from a location
    public void removeScene(String location) {
        locations.removeScene(location);
    }

    //The shot counters of all scene is reset to it default state when a day is over
    public void resetCounter() {
        for(int i = 0; i < boardXML.amountOfSets(); i++) {
            String setName = boardXML.getSetName(i);
            locations.addShotCounters(setName, boardXML.getShotCounters(i));
        }
    }

    //Return the name of a scene from a location except for Trailer and CastingOffice
    public String getScene(String location) {
        String scene = "";
        if((!location.equals("Trailer")) && (!location.equals("CastingOffice")) &&
                (locations.getLocationScene(location) != null)) {
            scene = locations.getLocationScene(location).getName();
        }
        return scene;
    }


    //Assign a random scene from a list of 40 total scenes for each approriate location
    //Randomization algorithm is based on answer in thread:
    //https://stackoverflow.com/questions/4040001/creating-random-numbers-with-no-duplicates
    //with no copy and paste but rather modification
    public void assignScene() {
        Random random = new Random();
        int scenesAmount = 40;
        ArrayList<Integer> intList = new ArrayList<Integer>();

        for(int i = 0; i < scenesAmount; i++) {
            intList.add(i);
        }

        int value = 0;
        int valuePick = 0;
        int locationsAmount = 0;
        while(intList.size() > 0 && locationsAmount < 10) {
             value = random.nextInt(intList.size());
             valuePick = intList.remove(value);
             locations.addScene(scenesList[valuePick], locationsAmount);
             locationsAmount++;
        }
    }


    //Indicate whether a location exist or not
    public boolean locationExist(String name) {
        return locations.vertexExist(name);
    }

    //Get dialogue from a role in a particular scene
    public String getSceneRoleDialogue(String scene, String role) {
        String dialogue = "";
        for(int i = 0; i < scenesList.length; i++) {
            if(scene.equals(scenesList[i].getName())) {
                String[] roles = scenesList[i].getRoles();
                for(int j = 0; j < roles.length; j++) {
                    if(role.equals(roles[j])) {
                        dialogue = scenesList[i].getRoleDialogue(role);
                    }
                }
            }
        }

        return dialogue;
    }


    //Return the name of a location based on a particular index
    public String locationAtIndex(int index) {
        return locations.locationName(index);
    }

    public String getExtraRoleDialogue(String location, String part) {
        return locations.getRoleDialogue(location, part);
    }

    //Return array of names of all the neighbor location of a particular location
    public String[] getAdjacentSpots(String name) {
        return locations.getAdjacentLocations(name);
    }

    //Return array of all extra roles for a particular location/set
    public String[] setExtraRoles(String location) {
        return locations.setExtraRoles(location);
    }

    //Return array of all extra roles for a particular location/set
    public String[] setSceneRoles(String location) {
        return locations.sceneRoles(location);
    }

    //Initialize all of the scenes from cards.xml
    private void sceneInitialization() {
        for(int i = 0; i < scenesList.length; i++) {
            scenesList[i] = new Scene(boardXML.getSceneName(i), boardXML.getSceneDesc(i),
                boardXML.getSceneNumber(i), boardXML.getBudget(i),boardXML.getSceneImg(i));

            for(int j = 0; j < boardXML.sceneElementsAmount(i); j++) {
                if(!(boardXML.getSceneRole(i, j).equals(""))) {
                    scenesList[i].addRoleAndLevel(boardXML.getSceneRole(i, j), boardXML.getRoleLevel(i, j));
                    scenesList[i].addRoleDialogue(boardXML.getSceneRole(i, j), boardXML.getSceneDialogue(i, j));
                }

            }
        }
    }

    //The entire board is set up during the constructor call
    private void setUpBoard() {

        for(int i = 0; i < boardXML.amountOfSets(); i++) {
            String setName = boardXML.getSetName(i);
            locations.addVertex(setName);
        }

        for(int i = 0; i < boardXML.amountOfSets(); i++) {
            String setName = boardXML.getSetName(i);
            locations.addShotCounters(setName, boardXML.getShotCounters(i));
            for(int j = 0; j < boardXML.amountOfNeighbors(i); j++) {
                if(!(boardXML.neighborName(i, j).equals(""))) {
                    locations.connectVerticesWithEdge(setName, boardXML.neighborName(i, j));
                }
            }

            for(int k = 0; k < boardXML.amountOfParts(i); k++) {
                if(!(boardXML.getPartName(i, k).equals(""))) {
                    locations.addRole(setName, boardXML.getPartName(i, k), boardXML.partLevel(i, k));
                    locations.addDialogue(setName, boardXML.getPartName(i, k), boardXML.getPartDialogue(i, k));
                }
            }


        }
   }

}
