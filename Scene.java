/*
Duy (David) Nghiem
CS 345
Scene Class: Representation of each individual scene.
Individual instances of Scene object contains information
related to role, budget and level requirement
 */
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Scene {
    private String name;
    private String description;
    private String img;
    private int sceneNum;
    private int budget;
    private HashMap<String, Integer> roles;
    private HashMap<String, String> rolesDialogue;
    private boolean faceUp;

    public Scene(String name, String desc, int num, int budget, String img) {
        this.name = name;
        this.description = desc;
        sceneNum = num;
        faceUp = false;
        this.budget = budget;
        roles = new HashMap<>();
        rolesDialogue = new HashMap<>();
        this.img = img;
    }

    public void addRoleAndLevel(String role, int level) {
        roles.put(role, level);
    }

    public void addRoleDialogue(String role, String dialogue) {
        rolesDialogue.put(role, dialogue);
    }


    public void sceneReveal() {
        faceUp = true;
    }

    public void sceneRemove() {
        faceUp = false;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return description;
    }

    public int getSceneNum() {
        return sceneNum;
    }

    public int getBudget() {
        return budget;
    }
    public String getImg() {
        return img;
    }

    public String getBudgetPresentation() {
        return budget + " million";
    }

    public int getRoleLevel(String role) {
        return roles.get(role);
    }

    public boolean sceneStatus() {
        return faceUp;
    }

    public void setFaceDown(){
         this.faceUp = false;
       }



    public String getRoleDialogue(String role) {
        String dialogue = "";
        String[] roles = getRoles();
        for(int i = 0; i < roles.length; i++) {
            if(roles[i].equals(role)) {
                dialogue = rolesDialogue.get(role);
            }
        }
        return dialogue;
    }

    public void getAllRoles() {
        Iterator roleIterator = roles.entrySet().iterator();
        while(roleIterator.hasNext()) {
            Map.Entry role = (Map.Entry) roleIterator.next();
            System.out.println("Role: " + role.getKey());


        }
    }

    public int rolesAmount() {
        return roles.size();
    }

    public String[] getRoles() {
        String[] rolesList = new String[roles.size()];
        Iterator roleIterator = roles.entrySet().iterator();
        int index = 0;
        while(roleIterator.hasNext()) {
            Map.Entry role = (Map.Entry) roleIterator.next();
            rolesList[index] = (String) role.getKey();
            index++;

        }

        return rolesList;

    }



}
