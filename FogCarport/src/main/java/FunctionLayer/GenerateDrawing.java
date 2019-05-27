/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import FunctionLayer.HelpingClasses.Carport;
import FunctionLayer.HelpingClasses.Part;
/**
 *
 * @author benja
 */
public class GenerateDrawing
{
    /**
     * Drawing of the a specific carport seen from top and calls the other
     * methods to create drawing of the carport seen from the side and front
     *
     * @param carport an object of the class Carport, that is showed on the drawing
     * @return a String including the measurments of the carport and materials used 
     * 
     * @see #drawShed(FunctionLayer.HelpingClasses.Carport) 
     * @see #drawFront(FunctionLayer.HelpingClasses.Carport) 
     * @see #drawSide(FunctionLayer.HelpingClasses.Carport) 
     */
    public String drawCarport(Carport carport)
    {
        int hight = carport.getDepth();
        int width = carport.getWidth();
        int numberOfSpær = 0;
        int numberOflægter = 0;
        boolean isSlope = carport.getRoof().getSlope() != 0;

        for (Part part : carport.getRoof().getParts())
        {
            if (!isSlope && 7 == part.getId())
            {
                numberOflægter = part.getQuantity();

            } else if (!isSlope && 3 == part.getId())
            {
                numberOfSpær = part.getQuantity();
            } else if (isSlope && 3 == part.getId() && part.getDescription().equals("Midterstykker til spær"))
            {
                numberOflægter = part.getQuantity();

            } else if (isSlope && 7 == part.getId())
            {
                numberOfSpær += part.getQuantity();

            }
        }

        double count = 0;
        double count2 = 0;

        String drawing = "<SVG viewBox=\"-100 0 " + (width + 250) + " " + (hight + 250) + "\" width= \"100%\" >"
                + "<rect x=\"0\" y =\"0\" height=\"" + hight + "\" width=\"" + width + "\""
                + "        style=\"stroke:#000000; fill: #ffffff\" />\n";

        drawing += "<rect x =\"0\" y =\"0\" height=\"" + (hight) + "\" width=\"" + 30 + "\""
                + "        style=\"stroke:#000000; fill: #000000\"/>";

        drawing += "<rect x =\"" + (width - 30) + "\" y =\"0\" height=\"" + (hight) + "\" width=\"" + 30 + "\""
                + "        style=\"stroke:#000000; fill: #000000\"/>";

        //first and last spær
        drawing += "<rect x =\"0\" y =\"" + 0 + "\" height=\"" + (5) + "\" width=\"" + (width) + "\""
                + "        style=\"stroke:#000000; fill: #A9A9A9\"/>";

        drawing += "<rect x =\"0\" y =\"" + (hight - 5) + "\" height=\"" + (5) + "\" width=\"" + (width) + "\""
                + "        style=\"stroke:#000000; fill: #A9A9A9\"/>";
        
        for (int i = 1; i <= numberOfSpær - 2; i++)
        {
            count += ((hight) / (numberOfSpær - 1));
            drawing += "<rect x =\"0\" y =\"" + (count) + "\" height=\"" + (5) + "\" width=\"" + (width) + "\""
                    + "        style=\"stroke:#000000; fill: #A9A9A9\"/>";
        }

        //first and last lægte
        drawing += "<rect x =\"" + (count2) + "\" y =\"0\" height=\"" + (hight) + "\" width=\"5\""
                + "        style=\"stroke:#000000; fill: #C0C0C0\"/>";

        drawing += "<rect x =\"" + (width) + "\" y =\"0\" height=\"" + (hight) + "\" width=\"5\""
                + "        style=\"stroke:#000000; fill: #C0C0C0\"/>";
        
        for (int i = 1; i <= numberOflægter - 2; i++)
        {
            count2 += ((width) / (numberOflægter - 1));
            drawing += "<rect x =\"" + (count2) + "\" y =\"0\" height=\"" + (hight) + "\" width=\"5\""
                    + "        style=\"stroke:#000000; fill: #C0C0C0\"/>";
        }

        drawing += "<text x=\"" + (width / 2 - 150) + "\" y=\"" + (hight + 30) + "\" fill=\"white\">Din carport set fra toppen har bredden " + width + " cm.  </text>";
        drawing += "<text x=\"" + (width / 2 - 150) + "\" y=\"" + (hight + 50) + "\" fill=\"white\"> OBS! Skuret og døren til skuret kan placeres efter behov. </text>";
        drawing += "<text  x=\"" + (width + 10) + "\" y=\"" + (hight / 2 + 15) + "\" fill=\"white\"  >dybde " + hight + "</text>";

        if (carport.getShed() != null)
        {
            drawing += drawShed(carport);
        }
        drawing += "</svg>";
        drawing += drawFront(carport);
        drawing += drawSide(carport);

        return drawing;
    }

    /**
     * Drawing of shed for a specific carport seen from the top as SVG format
     *
     * @param carport an object of the class Carport, that is showed on the drawing
     * @return a String containing the drawing of shed seen from top
     */
    private String drawShed(Carport carport)
    {
        int shedHight = carport.getShed().getDepth();
        int shedWidth = carport.getShed().getWidth();

        String drawing = "";

        drawing += "<rect x=\"0\" y =\"0\" height=\"" + shedHight + "\" width=\"" + shedWidth + "\""
                + "        style=\"stroke:#000000; opacity:0.5; fill: #ffffff\"/>\n";

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

        return drawing;
    }

    /**
     * Drawing of the carport with the roof (either sloped or flat) seen from the front as svg format
     * 
     * @param carport an object of the class Carport, that is showed on the drawing
     * @return a String containing the drawing of carport seen from the front
     */
    private String drawFront(Carport carport)
    {
        int hight = carport.getDepth();
        int width = carport.getWidth();
        int slope = carport.getRoof().getSlope();
        int roofHight = 0;

        String drawing = "";

        drawing += "<SVG viewBox=\"-30 0 " + (width + 100) + " " + (hight + 100) + "\" width= \"100%\" hight= \"50%\">";

        if (slope != 0)
        {
            for (Part part : carport.getRoof().getParts())
            {
                if (3 == part.getId() && part.getDescription().equals("Midterstykker til spær"))
                {
                    roofHight = part.getLength();
                }
            }

            drawing += "<polygon points=\"0," + roofHight + " " + (width / 2) + ",0 " + (width) + "," + roofHight + "\" style=\"fill:white;stroke:white;stroke-width:1\" />";
        }
        
        drawing += "<rect x=\"0\" y =\"" + (roofHight + 10) + "\" height=\"200\" width=\"10\""
                + "style=\"stroke:#000000; fill: #ffffff\"/>\n";
        drawing += "<rect x=\"" + (width - 10) + "\" y =\"" + (roofHight + 10) + "\" height=\"200\" width=\"10\""
                + "style=\"stroke:#000000; fill: #ffffff\"/>\n";

        //the line for the flat roof put inside if statment and check for sloap to check if the roof is sloped if it is change to that option
        drawing += "<rect x=\"0\" y =\"" + (roofHight) + "\" height=\"10\" width=\"" + width + "\""
                + "style=\"stroke:#000000; fill: #ffffff\"/>\n";

        if (carport.getShed() != null)
        {
            int shedWidth = carport.getShed().getWidth();

            drawing += "<rect x=\"10\" y =\"" + (roofHight + 10) + "\" height=\"200\" width=\"" + shedWidth + "\""
                    + "style=\"stroke:#000000; fill: #ffffff\"/>\n";
        }

        drawing += "<text x=\"" + (width / 2 - 130) + "\" y=\"" + (roofHight + 230) + "\" fill=\"white\">din carport set fra fronten har bredden " + width + " cm</text>";

        drawing += "</svg>";

        return drawing;
    }

    /**
     * Drawing of the carport seen from the side as svg format
     *
     * @param carport an object of the class Carport, that is showed on the drawing
     * @return a String containing the drawing of carport seen from the side
     */
    private String drawSide(Carport carport)
    {
        int hight = carport.getDepth();
        int width = carport.getWidth();
        int numberOfPoles = 0;

        for (Part part : carport.getParts())
        {
            if (part.getId() == 2)
            {
                numberOfPoles = part.getQuantity() / 2;
                if (part.getQuantity() % 2 != 0)
                {
                    numberOfPoles++;
                    break;
                }
            }
        }

        String drawing = "";

        drawing += "<SVG viewBox=\"-50 0 " + (width + 100) + " " + (hight + 20) + "\" width= \"100%\" hight= \"50%\">";
        int roofHight = 0;
        if (carport.getRoof().getSlope() != 0)
        {

            for (Part part : carport.getRoof().getParts())
            {
                if (3 == part.getId() && part.getDescription().equals("Midterstykker til spær"))
                {
                    roofHight = part.getLength();
                }
            }
            drawing += "<rect x=\"" + (0) + "\" y =\"0\" height=\"" + (roofHight) + "\" width=\"" + hight + "\""
                    + "style=\"stroke:#000000; fill: #ffffff\"/>\n";
        }

        drawing += "<rect x=\"0\" y =\"" + (roofHight + 10) + "\" height=\"200\" width=\"10\""
                + "style=\"stroke:#000000; fill: #ffffff\"/>\n";

        drawing += "<rect x=\"" + (hight - 10) + "\" y =\"" + (roofHight + 10) + "\" height=\"200\" width=\"10\""
                + "style=\"stroke:#000000; fill: #ffffff\"/>\n";

        if (carport.getShed() != null)
        {
            int shedDeapth = carport.getShed().getDepth();

            drawing += "<rect x=\"10\" y =\"" + (roofHight + 10) + "\" height=\"200\" width=\"" + shedDeapth + "\""
                    + "style=\"stroke:#000000; fill: #ffffff\"/>\n";

            drawing += "<rect x=\"" + (10 + (shedDeapth / 2)) + "\" y =\"" + (roofHight + 40) + "\" height=\"170\" width=\"80\""
                    + "style=\"stroke:#000000; fill: #C0C0C0\"/>\n";

            drawing += "<circle cx=\"" + (80 + (shedDeapth / 2)) + "\" cy=\"" + (roofHight + 120) + "\" r=\"5\"/>";
        }
        
        int count = 0;
        for (int i = 1; i <= numberOfPoles - 2; i++)
        {
            count += (hight / (numberOfPoles - 1));
            drawing += "<rect x=\"" + (count) + "\" y =\"" + (roofHight + 10) + "\" height=\"200\" width=\"10\""
                    + "style=\"stroke:#000000; fill: #ffffff\"/>\n";
        }

        //the line for the flat roof put inside if statment and check for sloap to check if the roof is sloped if it is change to that option
        drawing += "<rect x=\"0\" y =\"" + (roofHight) + "\" height=\"10\" width=\"" + hight + "\""
                + "style=\"stroke:#000000; fill: #ffffff\"/>\n";

        drawing += "</svg>";
        return drawing;
    }
}
