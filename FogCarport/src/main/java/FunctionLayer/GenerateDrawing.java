/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import FunctionLayer.HelpingClasses.Carport;
import FunctionLayer.HelpingClasses.Part;
import java.util.ArrayList;

/**
 *
 * @author benja
 */
public class GenerateDrawing {

    public String drawFlatRoofFromTop(Carport carport) {
        int hight = carport.getDepth();
        int width = carport.getWidth();
        int numberOflægter = 0;

        for (Part part : carport.getRoof().getParts()) {
            if ("45x195 spærtræ ubh.".equals(part.getName())) {
                numberOflægter = part.getQuantity();
                //try to add: to safe time inside loop break;
            }
        }

        double count = 100;

        String drawing = "<SVG viewBox=\"0 0 " + (width + 500) + " " + (hight + 500) + "\" width= \"100%\" hight= \"50%\">"
                + "<rect x=\"100\" y =\"100\" height=\"" + hight + "\" width=\"" + width + "\""
                + "        style=\"stroke:#000000; fill: #ffffff\"/>\n";

        drawing += "<rect x =\"100\" y =\"100\" height=\"" + (hight) + "\" width=\"" + 30 + "\""
                + "        style=\"stroke:#000000; fill: #fff000\"/>";

        drawing += "<rect x =\"" + (width + 70) + "\" y =\"100\" height=\"" + (hight) + "\" width=\"" + 30 + "\""
                + "        style=\"stroke:#000000; fill: #fff000\"/>";

            //first and last spær
        drawing += "<rect x =\"100\" y =\"" + 100 + "\" height=\"" + (15) + "\" width=\"" + (width) + "\""
                + "        style=\"stroke:#000000; fill: #f00000\"/>";

        drawing += "<rect x =\"100\" y =\"" + (hight + 100 - 15) + "\" height=\"" + (15) + "\" width=\"" + (width) + "\""
                + "        style=\"stroke:#000000; fill: #f00000\"/>";
        for (int i = 1; i <= numberOflægter - 2; i++) {
            count += ((hight) / (numberOflægter - 1));
            drawing += "<rect x =\"100\" y =\"" + (count) + "\" height=\"" + (15) + "\" width=\"" + (width) + "\""
                    + "        style=\"stroke:#000000; fill: #ff0000\"/>";

        }

        drawing += "<line x1=\"80\" y1=\"100\" x2=\"80\" y2=\"" + (hight + 100) + "\" style=\"stroke:#000000;\" stroke-width=\"5\" />";

        drawing += "<line x1=\"100\" y1=\"" + (hight + 120) + "\" x2=\"" + (width + 100) + "\" y2=\"" + (hight + 120) + "\" style=\"stroke:#000000;\" stroke-width=\"5\" />";

        drawing += "<text x=\"" + (width / 2) + "\" y=\"" + (hight + 140) + "\" fill=\"blue\">your carport is " + width + " cm wide</text>";

        drawing += "<text  x=\"" + (width + 110) + "\" y=\"" + (hight / 2 + 100) + "\" fill=\"blue\"  >YYYyour carport is " + hight + " cm long</text>";

        drawing += "</svg>";

        return drawing;
    }

    public String drawRoofFromTop(Carport carport) {
        int hight = carport.getDepth();
        int width = carport.getWidth();
        int numberOfSpær = 0;
        int numberOflægter = 0;
        boolean isSlope = carport.getRoof().getSlope() != 0;

        for (Part part : carport.getRoof().getParts()) {
            if (!isSlope && 7 == part.getId()) {
                numberOflægter = part.getQuantity();
               
                //this is for flat roof, what is used for not flat roof?
            } else if (!isSlope && 3 == part.getId()) {
                numberOfSpær = part.getQuantity(); 
            }
         
            else if (isSlope && 3 == part.getId() && part.getDescription().equals("Midterstykker til spær")) {
                numberOflægter = part.getQuantity();
              
            }
             else if (isSlope && 7 == part.getId()) {
                numberOfSpær += part.getQuantity();
              
            }
        }

        double count = 100;
        double count2 = 100;

        String drawing = "<SVG viewBox=\"0 0 " + (width + 500) + " " + (hight + 500) + "\" width= \"100%\" hight= \"50%\">"
                + "<rect x=\"100\" y =\"100\" height=\"" + hight + "\" width=\"" + width + "\""
                + "        style=\"stroke:#000000; fill: #ffffff\"/>\n";

        drawing += "<rect x =\"100\" y =\"100\" height=\"" + (hight) + "\" width=\"" + 30 + "\""
                + "        style=\"stroke:#000000; fill: #fff000\"/>";

        drawing += "<rect x =\"" + (width + 70) + "\" y =\"100\" height=\"" + (hight) + "\" width=\"" + 30 + "\""
                + "        style=\"stroke:#000000; fill: #fff000\"/>";

            //first and last spær
        drawing += "<rect x =\"100\" y =\"" + 100 + "\" height=\"" + (5) + "\" width=\"" + (width) + "\""
                + "        style=\"stroke:#000000; fill: #f00000\"/>";

        drawing += "<rect x =\"100\" y =\"" + (hight + 100 - 15) + "\" height=\"" + (5) + "\" width=\"" + (width) + "\""
                + "        style=\"stroke:#000000; fill: #f00000\"/>";
        for (int i = 1; i <= numberOfSpær - 2; i++) {
            count += ((hight) / (numberOfSpær - 1));
            drawing += "<rect x =\"100\" y =\"" + (count) + "\" height=\"" + (5) + "\" width=\"" + (width) + "\""
                    + "        style=\"stroke:#000000; fill: #ff0000\"/>";

        }

            //first and last lægte
        drawing += "<rect x =\"" + (count2) + "\" y =\"100\" height=\"" + (hight) + "\" width=\"5\""
                + "        style=\"stroke:#000000; fill: #ff0000\"/>";

        drawing += "<rect x =\"" + (width + 100) + "\" y =\"100\" height=\"" + (hight) + "\" width=\"5\""
                + "        style=\"stroke:#000000; fill: #ff0000\"/>";
        for (int i = 1; i <= numberOflægter - 2; i++) {

            count2 += ((width) / (numberOflægter - 1));

            drawing += "<rect x =\"" + (count2) + "\" y =\"100\" height=\"" + (hight) + "\" width=\"5\""
                    + "        style=\"stroke:#000000; fill: #ff0000\"/>";
        }

        drawing += "<line x1=\"80\" y1=\"100\" x2=\"80\" y2=\"" + (hight + 100) + "\" style=\"stroke:#000000;\" stroke-width=\"5\" />";

        drawing += "<line x1=\"100\" y1=\"" + (hight + 120) + "\" x2=\"" + (width + 100) + "\" y2=\"" + (hight + 120) + "\" style=\"stroke:#000000;\" stroke-width=\"5\" />";

        drawing += "<text x=\"" + (width / 2) + "\" y=\"" + (hight + 140) + "\" fill=\"blue\">din carport er " + width + " cm bred</text>";

        drawing += "<text  x=\"" + (width + 110) + "\" y=\"" + (hight / 2 + 100) + "\" fill=\"blue\"  >din carport er " + hight + " cm dyb</text>";

        if (carport.getShed() != null) {

            drawing += drawShed(carport);

        }

        drawing += drawflatFront(carport);
        drawing += drawflatSide(carport);

        drawing += "</svg>";

        return drawing;

    }

    public String drawShed(Carport carport) {
        int carportHight = carport.getDepth();
        int carportWidth = carport.getWidth();
        int shedHight = carport.getShed().getDepth();
        int shedWidth = carport.getShed().getWidth();
        int numberOfStolper = 6;

        String drawing = "";

        drawing += "<SVG viewBox=\"0 " + (400 - carportWidth - shedWidth) + " " + (carportWidth - shedWidth + (shedWidth + 500)) + " " + (shedHight + 500) + "\" width= \"100%\" hight= \"50%\">";
        drawing += "<rect x=\"0\" y =\"0\" height=\"" + shedHight + "\" width=\"" + shedWidth + "\""
                + "        style=\"stroke:#000000; fill: #ffffff\"/>\n";

        drawing += "<rect x=\"0\" y =\"0\" height=\"10\" width=\"10\""
                + "        style=\"stroke:#000000; fill: #ffffff\"/>\n";

        drawing += "<rect x=\"0\" y =\"" + (shedHight - 10) + "\" height=\"10\" width=\"10\""
                + "        style=\"stroke:#000000; fill: #ffffff\"/>\n";

        drawing += "<rect x=\"" + (shedWidth - 10) + "\" y =\"0\" height=\"10\" width=\"10\""
                + "        style=\"stroke:#000000; fill: #ffffff\"/>\n";

        drawing += "<rect x=\"" + (shedWidth - 10) + "\" y =\"" + (shedHight - 10) + "\" height=\"10\" width=\"10\""
                + "        style=\"stroke:#000000; fill: #ffffff\"/>\n";

        drawing += "<rect x=\"" + (shedWidth - 10) + "\" y =\"30\" height=\"10\" width=\"10\""
                + "        style=\"stroke:#000000; fill: #ffffff\"/>\n";

        drawing += "<rect x=\"" + (shedWidth - 10) + "\" y =\"85\" height=\"10\" width=\"10\""
                + "        style=\"stroke:#000000; fill: #ffffff\"/>\n";

        drawing += "<rect x=\"" + (shedWidth - 5) + "\" y =\"40\" height=\"45\" width=\"5\""
                + "        style=\"stroke:#000000; fill: #ffffff\"/>\n";

        drawing += "</svg>";

        return drawing;
    }

    public String drawflatFront(Carport carport) {

        // this method could be change to flat roof side by adding beams from side
        int hight = carport.getDepth();
        int width = carport.getWidth();

        String drawing = "";
        
        

        drawing += "<SVG viewBox=\"-100 -800 " + (width + 500) + " " + (hight + 500) + "\" width= \"100%\" hight= \"50%\">";

        drawing += "<rect x=\"0\" y =\"10\" height=\"200\" width=\"10\""
                + "style=\"stroke:#000000; fill: #ffffff\"/>\n";
        drawing += "<rect x=\"" + (width - 10) + "\" y =\"10\" height=\"200\" width=\"10\""
                + "style=\"stroke:#000000; fill: #ffffff\"/>\n";

        //the line for the flat roof put inside if statment and check for sloap to check if the roof is sloped if it is change to that option
        drawing += "<rect x=\"0\" y =\"0\" height=\"10\" width=\"" + width + "\""
                + "style=\"stroke:#000000; fill: #ffffff\"/>\n";

        if (carport.getShed() != null) {
            int shedWidth = carport.getShed().getWidth();

            drawing += "<rect x=\"10\" y =\"10\" height=\"200\" width=\"" + shedWidth + "\""
                    + "style=\"stroke:#000000; fill: #ffffff\"/>\n";

        }

        drawing += "</svg>";

        return drawing;
    }

    public String drawflatSide(Carport carport) {

        int hight = carport.getDepth();
        int width = carport.getWidth();
        int numberOfPoles = 0;

        for (Part part : carport.getParts()) {
            if (part.getId() == 2) {
                numberOfPoles = part.getQuantity() / 2;
                if(part.getQuantity() % 2 != 0)
                {
                numberOfPoles++;
                break;
                }
            }

        }

        String drawing = "";

        drawing += "<SVG viewBox=\"0 -1100 " + (width + 500) + " " + (hight + 500) + "\" width= \"100%\" hight= \"50%\">";
        


        drawing += "<rect x=\"0\" y =\"10\" height=\"200\" width=\"10\""
                + "style=\"stroke:#000000; fill: #ffffff\"/>\n";
        
      
        
        drawing += "<rect x=\"" + (hight - 10) + "\" y =\"10\" height=\"200\" width=\"10\""
                + "style=\"stroke:#000000; fill: #ffffff\"/>\n";
        
        
        if (carport.getShed() != null) {
            int shedDeapth = carport.getShed().getDepth();
            

            drawing += "<rect x=\"10\" y =\"10\" height=\"200\" width=\"" + shedDeapth + "\""
                    + "style=\"stroke:#000000; fill: #ffffff\"/>\n";
              
            drawing += "<rect x=\""+(10+(shedDeapth/2))+"\" y =\"40\" height=\"170\" width=\"80\""
                + "style=\"stroke:#000000; fill: #ffffff\"/>\n";
            
             drawing +=  "<circle cx=\""+(80+(shedDeapth/2))+"\" cy=\"120\" r=\"5\"/>";

        }
        int count = 0;
        for (int i = 1; i<= numberOfPoles -2 ; i++) {
           count += (hight / (numberOfPoles-1));
            drawing += "<rect x=\"" + (count) + "\" y =\"10\" height=\"200\" width=\"10\""
                    + "style=\"stroke:#000000; fill: #ffffff\"/>\n";

        }

        //the line for the flat roof put inside if statment and check for sloap to check if the roof is sloped if it is change to that option
        drawing += "<rect x=\"0\" y =\"0\" height=\"10\" width=\"" + hight + "\""
                + "style=\"stroke:#000000; fill: #ffffff\"/>\n";

        
         if (carport.getRoof().getSlope() != 0)
            
        {
        drawing += drawSlopeRoofSide(carport);

        
    
    }
         drawing += "</svg>";
         return drawing;
    }
    
     public String drawSlopeRoofSide(Carport carport) {

        int hight = carport.getDepth();
        int width = carport.getWidth();
        int roofHight = 0;
                
                      for (Part part : carport.getRoof().getParts()) {
            if (3 == part.getId() && part.getDescription().equals("Midterstykker til spær")) {
                roofHight = part.getLength();
            }
                      }
       
       String drawing = "";
        
      drawing += "<SVG viewBox=\"0 0 " + (roofHight) + " " + (width) + "\" width= \"100%\" hight= \"50%\">";

        drawing += "<rect x=\""+(0)+"\" y =\"0\" height=\""+(roofHight)+"\" width=\""+hight+"\""
                + "style=\"stroke:#000000; fill: #ffffff\"/>\n";
        
        
        
       
         drawing += "</svg>";
       
       return drawing; 
     }

}
