// Modified Code based on example file for parsing XML file
//by David (Duy) Nghiem
//Original example code written by Dr. Moushumi Sharmin
//CSCI 345

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ParseXML{

        private Document d;
        private Document c;

        public ParseXML()
        throws ParserConfigurationException{
            this.d = getDocFromFile("board.xml");
            this.c = getDocFromFile("cards.xml");
        }

        // building a document from the XML file
        // returns a Document object after loading the book.xml file.
        private Document getDocFromFile(String filename)
                throws ParserConfigurationException {
            {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = null;

                try{
                    doc = db.parse(filename);
                } catch (Exception ex){
                    System.out.println("XML parse failure");
                    ex.printStackTrace();
                    }
                    return doc;
            } // exception handling
        }

        // reads data from XML file and prints data
        /*
        public void readBookData(Document d){

            Element root = d.getDocumentElement();

            NodeList books = root.getElementsByTagName("book");

            for (int i=0; i<books.getLength();i++){

                System.out.println("Printing information for book "+(i+1));

                //reads data from the nodes
                Node book = books.item(i);
                String bookCategory = book.getAttributes().getNamedItem("category").getNodeValue();
                System.out.println("Category = "+bookCategory);

                //reads data

                NodeList children = book.getChildNodes();

                for (int j=0; j< children.getLength(); j++){

                  Node sub = children.item(j);

                  if("title".equals(sub.getNodeName())){
                     String bookLanguage = sub.getAttributes().getNamedItem("lang").getNodeValue();
                     System.out.println("Language = "+bookLanguage);
                     String title = sub.getTextContent();
                     System.out.println("Title = "+title);

                  }

                  else if("author".equals(sub.getNodeName())){
                     String authorName = sub.getTextContent();
                     System.out.println(" Author = "+authorName);

                  }
                  else if("year".equals(sub.getNodeName())){
                     String yearVal = sub.getTextContent();
                     System.out.println(" Publication Year = "+yearVal);

                  }
                  else if("price".equals(sub.getNodeName())){
                     String priceVal = sub.getTextContent();
                     System.out.println(" Price = "+priceVal);

                  }

                } //for childnodes
                System.out.println("\n");
            }//for book nodes
        }// method*/


        public int amountOfNeighbors(int setIndex) {
            Element root = d.getDocumentElement();
            NodeList sets = root.getElementsByTagName("set");
            Node eachSet = sets.item(setIndex);
            NodeList children = eachSet.getChildNodes();

            int index = 0;
            Node sub = children.item(index);
            while(!("neighbors".equals(sub.getNodeName()))) {
                index++;
                sub = children.item(index);
            }
            NodeList neighbors = sub.getChildNodes();
            return neighbors.getLength();
        }

        public String neighborName(int setIndex, int neighborIndex) {
            String name = "";
            Element root = d.getDocumentElement();
            NodeList sets = root.getElementsByTagName("set");
            Node eachSet = sets.item(setIndex);
            NodeList children = eachSet.getChildNodes();

            for(int j = 0; j < children.getLength(); j++) {
                    Node sub = children.item(j);
                    if("neighbors".equals(sub.getNodeName())) {
                        NodeList neighbors = sub.getChildNodes();
                        Node neighbor = neighbors.item(neighborIndex);
                        if("neighbor".equals(neighbor.getNodeName())) {
                            name = neighbor.getAttributes().getNamedItem("name").getNodeValue();
                        }

                    }
            }

            return name;
        }

        public int amountOfSets() {
            Element root = d.getDocumentElement();
            NodeList sets = root.getElementsByTagName("set");
            return sets.getLength();
        }

        public String getSetName(int index) {
            Element root = d.getDocumentElement();
            NodeList sets = root.getElementsByTagName("set");
            Node eachSet = sets.item(index);
            String setName = eachSet.getAttributes().getNamedItem("name").getNodeValue();
            return setName;

        }

        public int amountOfParts(int setIndex) {
            Element root = d.getDocumentElement();
            NodeList sets = root.getElementsByTagName("set");
            Node eachSet = sets.item(setIndex);
            NodeList children = eachSet.getChildNodes();
            int amount = 0;

            for(int i = 0; i < children.getLength(); i++) {
                Node sub = children.item(i);
                if("parts".equals(sub.getNodeName())) {
                    NodeList parts = sub.getChildNodes();
                    amount = parts.getLength();
                }
            }
            return amount;
        }

        public int partLevel(int setIndex, int partIndex) {
            int level = 0;
            Element root = d.getDocumentElement();
            NodeList sets = root.getElementsByTagName("set");
            Node eachSet = sets.item(setIndex);
            NodeList children = eachSet.getChildNodes();

            for(int j = 0; j < children.getLength(); j++) {
                    Node sub = children.item(j);
                    if("parts".equals(sub.getNodeName())) {
                        NodeList parts = sub.getChildNodes();
                        Node part = parts.item(partIndex);
                        if("part".equals(part.getNodeName())) {
                            level = Integer.parseInt(part.getAttributes().getNamedItem("level").getNodeValue());
                        }
                    }
            }

            return level;
        }

        public String getPartName(int setIndex, int partIndex) {
            String name = "";
            Element root = d.getDocumentElement();
            NodeList sets = root.getElementsByTagName("set");
            Node eachSet = sets.item(setIndex);
            NodeList children = eachSet.getChildNodes();

            for(int j = 0; j < children.getLength(); j++) {
                    Node sub = children.item(j);
                    if("parts".equals(sub.getNodeName())) {
                        NodeList parts = sub.getChildNodes();
                        Node part = parts.item(partIndex);
                        if("part".equals(part.getNodeName())) {
                            name = part.getAttributes().getNamedItem("name").getNodeValue();
                        }

                    }
            }

            return name;
        }

        public String getPartDialogue(int setIndex, int partIndex) {
            String dialogue = "";

            Element root = d.getDocumentElement();
            NodeList sets = root.getElementsByTagName("set");
            Node eachSet = sets.item(setIndex);
            NodeList children = eachSet.getChildNodes();

            for(int j = 0; j < children.getLength(); j++) {
                    Node sub = children.item(j);

                    if("parts".equals(sub.getNodeName())) {
                        NodeList parts = sub.getChildNodes();
                        Node part = parts.item(partIndex);

                        NodeList partChildren = part.getChildNodes();
                        for(int i = 0; i < partChildren.getLength(); i++) {
                            Node line = partChildren.item(i);
                            if("line".equals(line.getNodeName())) {
                                dialogue = line.getTextContent();
                            }
                        }

                    }
            }

            return dialogue;
        }

        public int getShotCounters(int setIndex) {
            int counter = 0;
            Element root = d.getDocumentElement();
            NodeList sets = root.getElementsByTagName("set");
            Node eachSet = sets.item(setIndex);
            NodeList children = eachSet.getChildNodes();

            for(int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if("takes".equals(child.getNodeName())) {
                    NodeList takes = child.getChildNodes();
                    for(int j = 0; j < takes.getLength(); j++) {
                        Node take = takes.item(j);
                        if("take".equals(take.getNodeName())) {
                            counter++;
                        }

                    }
                }
            }

            return counter;
        }

        // reads data from XML file and prints data
        public void readBoardData(){
            Element root = d.getDocumentElement();
            NodeList sets = root.getElementsByTagName("set");

            for (int i=0; i<sets.getLength();i++){
                Node eachSet = sets.item(i);
                String setName = eachSet.getAttributes().getNamedItem("name").getNodeValue();
                System.out.println("Location: " + setName);

                NodeList children = eachSet.getChildNodes();

                for(int j = 0; j < children.getLength(); j++) {
                    Node sub = children.item(j);

                    if("neighbors".equals(sub.getNodeName())) {
                        System.out.println("neighbors:");
                        NodeList neighbors = sub.getChildNodes();
                        for(int k = 0; k < neighbors.getLength(); k++) {
                            Node neighbor = neighbors.item(k);
                            if("neighbor".equals(neighbor.getNodeName())) {
                                String name = neighbor.getAttributes().getNamedItem("name").getNodeValue();
                                System.out.println("neighbor " + k + ": " + name);
                            }
                        }
                    } else if("parts".equals(sub.getNodeName())) {
                        NodeList parts = sub.getChildNodes();
                        for(int k = 0; k < parts.getLength(); k++) {
                            Node inner = parts.item(k);
                            if("part".equals(inner.getNodeName())) {
                                String name = inner.getAttributes().getNamedItem("name").getNodeValue();
                                int value = Integer.parseInt(inner.getAttributes().getNamedItem("level").getNodeValue());
                                System.out.print("part" + k + ": " + name + ", Level: " + value + ", ");

                                NodeList partChildren = inner.getChildNodes();
                                for(int l = 0; l < partChildren.getLength(); l++) {
                                    Node line = partChildren.item(l);
                                    if("line".equals(line.getNodeName())) {
                                        String dialogue = line.getTextContent();
                                        System.out.println("Dialogues: " + dialogue);
                                    }
                                }

                            }
                        }
                    }


                }
                System.out.println();
            }
        }

        public void readCardData() {
            Element root = c.getDocumentElement();
            NodeList cards = root.getElementsByTagName("card");

            for(int i = 0; i < cards.getLength(); i++) {
                Node eachSet = cards.item(i);
                String sceneName = eachSet.getAttributes().getNamedItem("name").getNodeValue();
                System.out.print("Scene: " + sceneName + ", ");
                int budget = Integer.parseInt(eachSet.getAttributes().getNamedItem("budget").getNodeValue());
                System.out.println("budget: " + budget);

                NodeList childrens = eachSet.getChildNodes();
                for(int j = 0; j < childrens.getLength(); j++) {
                    Node child = childrens.item(j);

                    if("scene".equals(child.getNodeName())) {
                        int sceneNumber = Integer.parseInt(child.getAttributes().getNamedItem("number").getNodeValue());
                        String desc = child.getTextContent();
                        System.out.println("Scene number: " + sceneNumber);
                        System.out.println("Description:" + desc);
                    } else if("part".equals(child.getNodeName())) {
                        String partName = child.getAttributes().getNamedItem("name").getNodeValue();
                        int partLevel = Integer.parseInt(child.getAttributes().getNamedItem("level").getNodeValue());
                        System.out.println("Part: " + partName + ", level: " + partLevel);

                    }

                }
                System.out.println();
                System.out.println();

            }
            System.out.println();
        }

        public int sceneAmount() {
            Element root = c.getDocumentElement();
            NodeList cards = root.getElementsByTagName("card");

            return cards.getLength();
        }

        public String getSceneName(int sceneIndex) {
            String name = "";
            Element root = c.getDocumentElement();
            NodeList cards = root.getElementsByTagName("card");

            Node eachSet = cards.item(sceneIndex);
            name = eachSet.getAttributes().getNamedItem("name").getNodeValue();

            return name;
        }

        public String getSceneImg(int sceneIndex) {
            String img = "";
            Element root = c.getDocumentElement();
            NodeList cards = root.getElementsByTagName("card");

            Node eachSet = cards.item(sceneIndex);
            img = eachSet.getAttributes().getNamedItem("img").getNodeValue();

            return img;
        }

        public int getBudget(int sceneIndex) {
            int budget = 0;
            Element root = c.getDocumentElement();
            NodeList cards = root.getElementsByTagName("card");

            Node eachSet = cards.item(sceneIndex);
            budget = Integer.parseInt(eachSet.getAttributes().getNamedItem("budget").getNodeValue());

            return budget;
        }

        public int getSceneNumber(int sceneIndex) {
            int sceneNumber = 0;
            Element root = c.getDocumentElement();
            NodeList cards = root.getElementsByTagName("card");

            Node scene = cards.item(sceneIndex);
            NodeList children = scene.getChildNodes();
            for(int i = 0; i < children.getLength(); i++) {
                Node number = children.item(i);
                if("scene".equals(number.getNodeName())) {
                    sceneNumber = Integer.parseInt(number.getAttributes().getNamedItem("number").getNodeValue());
                }
            }

            return sceneNumber;
        }

        public String getSceneDesc(int sceneIndex) {
            String desc = "";
            Element root = c.getDocumentElement();
            NodeList cards = root.getElementsByTagName("card");

            Node scene = cards.item(sceneIndex);
            NodeList children = scene.getChildNodes();
            for(int i = 0; i < children.getLength(); i++) {
                Node sceneDesc = children.item(i);
                if("scene".equals(sceneDesc.getNodeName())) {
                    desc = sceneDesc.getTextContent();
                }
            }
            return desc;
        }

        public int sceneElementsAmount(int sceneIndex) {
            Element root = c.getDocumentElement();
            NodeList cards = root.getElementsByTagName("card");

            Node scene = cards.item(sceneIndex);
            NodeList children = scene.getChildNodes();

            return children.getLength();
        }

        public String getSceneRole(int sceneIndex, int partIndex) {
            String sceneRole  = "";
            Element root = c.getDocumentElement();
            NodeList cards = root.getElementsByTagName("card");

            Node scene = cards.item(sceneIndex);
            NodeList children = scene.getChildNodes();

            Node part = children.item(partIndex);
            if("part".equals(part.getNodeName())) {
                sceneRole = part.getAttributes().getNamedItem("name").getNodeValue();
            }

            return sceneRole;
        }

        public int getRoleLevel(int sceneIndex, int partIndex) {
            int level = 0;
            Element root = c.getDocumentElement();
            NodeList cards = root.getElementsByTagName("card");

            Node scene = cards.item(sceneIndex);
            NodeList children = scene.getChildNodes();

            Node part = children.item(partIndex);
            if("part".equals(part.getNodeName())) {
                level = Integer.parseInt(part.getAttributes().getNamedItem("level").getNodeValue());
            }

            return level;
        }



        public String getSceneDialogue(int sceneIndex, int partIndex) {
            String dialogue = "";
            Element root = c.getDocumentElement();
            NodeList cards = root.getElementsByTagName("card");

            Node scene = cards.item(sceneIndex);
            NodeList children = scene.getChildNodes();

            Node part = children.item(partIndex);
            if("part".equals(part.getNodeName())) {

                NodeList partChildren = part.getChildNodes();
                for(int i = 0; i < partChildren.getLength(); i++) {
                    Node line = partChildren.item(i);
                    if("line".equals(line.getNodeName())) {
                        dialogue = line.getTextContent();
                    }
                }
            }

            return dialogue;
        }


}//class
