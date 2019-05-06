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
public class GenerateDrawing {
    
    public String drawFlatRoofFromTop(Carport carport)
    {
            int hight = carport.getDepth();
            int width = carport.getWidth();
             int numberOflægter = 0
                     ;
            
            for (Part part : carport.getRoof().getParts())
            {
                if ("45x195 spærtræ ubh.".equals(part.getName()))
                {
                    numberOflægter = part.getQuantity();
                   //try to add: to safe time inside loop break;
                }
            }
            
            double count = 100;
            
            String drawing = "<SVG viewBox=\"0 0 "+(width + 500)+" "+(hight+500)+"\" width= \"100%\" hight= \"50%\">" +
"<rect x=\"100\" y =\"100\" height=\""+hight+"\" width=\""+width+"\"" +
"        style=\"stroke:#000000; fill: #ffffff\"/>\n" ;
            
            
            drawing += "<rect x =\"100\" y =\"100\" height=\""+(hight)+"\" width=\""+30+"\"" +
"        style=\"stroke:#000000; fill: #fff000\"/>";
            
            drawing += "<rect x =\""+(width+70)+"\" y =\"100\" height=\""+(hight)+"\" width=\""+30+"\"" +
"        style=\"stroke:#000000; fill: #fff000\"/>";
            
            //first and last spær
            
         drawing += "<rect x =\"100\" y =\""+100+"\" height=\""+(15)+"\" width=\""+(width)+"\"" +
"        style=\"stroke:#000000; fill: #f00000\"/>";
                   
         drawing += "<rect x =\"100\" y =\""+(hight+100-15)+"\" height=\""+(15)+"\" width=\""+(width)+"\"" +
"        style=\"stroke:#000000; fill: #f00000\"/>";
            for (int i = 1; i <= numberOflægter-2; i ++)
            {
                count += ((hight) / (numberOflægter -1));
               drawing += "<rect x =\"100\" y =\""+(count)+"\" height=\""+(15)+"\" width=\""+(width)+"\"" +
"        style=\"stroke:#000000; fill: #ff0000\"/>";
               
            }
            
            drawing += "<line x1=\"80\" y1=\"100\" x2=\"80\" y2=\""+(hight + 100)+"\" style=\"stroke:#000000;\" stroke-width=\"5\" />";
            
            drawing += "<line x1=\"100\" y1=\""+(hight + 120)+"\" x2=\""+(width +100)+"\" y2=\""+(hight + 120)+"\" style=\"stroke:#000000;\" stroke-width=\"5\" />";
            
            drawing += "<text x=\""+(width/2)+"\" y=\""+(hight + 140)+"\" fill=\"blue\">your carport is "+width+" cm wide</text>";
            
             drawing += "<text  x=\""+(width + 110)+"\" y=\""+(hight /2 +100)+"\" fill=\"blue\"  >YYYyour carport is "+hight+" cm long</text>";
            
            drawing += "</svg>";
            
            return drawing;
    }
    
       public String drawSlopeRoofFromTop(Carport carport)
    {
            int hight = carport.getDepth();
            int width = carport.getWidth();
             int numberOfSpær = 0;
             int numberOflægter = 0;
            
            for (Part part : carport.getRoof().getParts())
            {
                if (7 == part.getId())
                {
                    numberOfSpær = part.getQuantity();
                   //try to add: to safe time inside loop break;
                }
                else if (3 == part.getId())
                {
                    numberOfSpær = part.getQuantity();
                   //try to add: to safe time inside loop break;
                }
            }
            
        
            double count = 100;
            double count2 = 100;
            
            String drawing = "<SVG viewBox=\"0 0 "+(width + 500)+" "+(hight+500)+"\" width= \"100%\" hight= \"50%\">" +
"<rect x=\"100\" y =\"100\" height=\""+hight+"\" width=\""+width+"\"" +
"        style=\"stroke:#000000; fill: #ffffff\"/>\n" ;
            
            
            drawing += "<rect x =\"100\" y =\"100\" height=\""+(hight)+"\" width=\""+30+"\"" +
"        style=\"stroke:#000000; fill: #fff000\"/>";
            
            drawing += "<rect x =\""+(width+70)+"\" y =\"100\" height=\""+(hight)+"\" width=\""+30+"\"" +
"        style=\"stroke:#000000; fill: #fff000\"/>";
            
            //first and last spær
            
         drawing += "<rect x =\"100\" y =\""+100+"\" height=\""+(15)+"\" width=\""+(width)+"\"" +
"        style=\"stroke:#000000; fill: #f00000\"/>";
                   
         drawing += "<rect x =\"100\" y =\""+(hight+100-15)+"\" height=\""+(15)+"\" width=\""+(width)+"\"" +
"        style=\"stroke:#000000; fill: #f00000\"/>";
            for (int i = 1; i <= numberOfSpær-2; i ++)
            {
                count += ((hight) / (numberOfSpær -1));
               drawing += "<rect x =\"100\" y =\""+(count)+"\" height=\""+(15)+"\" width=\""+(width)+"\"" +
"        style=\"stroke:#000000; fill: #ff0000\"/>";
               
            }
            
                   drawing += "<rect x =\""+(count2)+"\" y =\"100\" height=\""+(hight)+"\" width=\"5\"" +
"        style=\"stroke:#000000; fill: #ff0000\"/>";
             
             drawing += "<rect x =\""+(width+100)+"\" y =\"100\" height=\""+(hight)+"\" width=\"5\"" +
"        style=\"stroke:#000000; fill: #ff0000\"/>";
                for (int i = 1; i <= numberOflægter-2; i ++)
            {
                 
                count2 += ((width) / (numberOflægter - 1));

               drawing += "<rect x =\""+(count2)+"\" y =\"100\" height=\""+(hight)+"\" width=\"5\"" +
"        style=\"stroke:#000000; fill: #ff0000\"/>";
            }
            
            drawing += "<line x1=\"80\" y1=\"100\" x2=\"80\" y2=\""+(hight + 100)+"\" style=\"stroke:#000000;\" stroke-width=\"5\" />";
            
            drawing += "<line x1=\"100\" y1=\""+(hight + 120)+"\" x2=\""+(width +100)+"\" y2=\""+(hight + 120)+"\" style=\"stroke:#000000;\" stroke-width=\"5\" />";
            
            drawing += "<text x=\""+(width/2)+"\" y=\""+(hight + 140)+"\" fill=\"blue\">your carport is "+width+" cm wide</text>";
            
             drawing += "<text  x=\""+(width + 110)+"\" y=\""+(hight /2 +100)+"\" fill=\"blue\"  >YYYyour carport is "+hight+" cm long</text>";
            
            drawing += "</svg>";
            
            return drawing;
    }
    
}
