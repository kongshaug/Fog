/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FunctionLayer;

import java.util.ArrayList;

/**
 *
 * @author benja
 */
public class CalculateShed {
    
     public void caluclatSlopeRoof(Carport carport, Material søgle, Material bræt, Material skruerne,
            Material beklædning)
    {
     
        
        //CHANGE BELOW TO SHED WEN SHED HAS CONSTRUCTOR
        int depth = carport.getDepth();
        int width = carport.getWidth();
        int slope = carport.getRoof().getSlope();
       
        ArrayList<Part> parts = carport.getParts();
        
        // PUT HERE:  caluclatdoorForShed();

        
        //calculate søjler one in each corner and one by each door side
        
          Part LægteForDoor = new Part(søgle, 300, 6, "søgler til hjørner af skur og sidderne af døren til skuret");
        parts.add(LægteForDoor);
        
        //front and back on shed has 3 beans sides have 2 going all the way across:25x150	mm.	trykimp.	Bræt
        
        
        
        Part SupportBeansForBeklædningsides = new Part(bræt, depth, 4, "support til bagsiden af belædningen til skurets sider");
        parts.add(SupportBeansForBeklædningsides);
        
        Part SupportBeansForBeklædningFrontBack = new Part(bræt, width, 6, "support til bagsiden af belædningen til skuret front og bagside");
        parts.add(SupportBeansForBeklædningFrontBack);
        
        //the beans is screwed on add a pack of screws
         Part screws = new Part(skruerne, 0, 1, "skruer til at sætte support bræt på søjler og brædder til support bræt");
        parts.add(screws);
        
        // the beklædning uses  19x100 mm. trykimp. Bræt
        //each with a 3 cm overlay to the next one
        
        // 42 is retracted because the material for the door has already been added, a door is 84 cm therefor half is removed 
        
        int numberOfBræderForFrontBack = ((width - 42) / 7) *2;
        
        Part beklædningen = new Part(beklædning, 200, numberOfBræderForFrontBack, "beklædning til skurets front og bagside");
        parts.add(beklædningen);
       
         int numberOfBræderForSides = (depth / 7) *2;
        
        Part beklædningenSides = new Part(beklædning, 200, numberOfBræderForSides, "beklædning til skurets sider");
        parts.add(beklædningenSides);
        

        carport.setParts(parts);
    
}

      public void caluclatdoorForShed(Carport carport, Material Lægte, Material stalddørsgrebene, Material hængselet, Material planker)
    {
           ArrayList<Part> parts = carport.getParts();

        
        //here we calculate all the parts needed for a door
        
        Part LægteForDoor = new Part(Lægte, 420, 1, "til z på bagside af dør");
        parts.add(LægteForDoor);
        
        Part stalddørsgreb = new Part(stalddørsgrebene, 0, 1, "Til lås på dør i skur");
        parts.add(stalddørsgreb);
        
        Part hængsel = new Part(hængselet, 0, 1, "Til skurdør");
        parts.add(hængsel);
        
        //the wood to make the door itself, it is: 19x100 mm. trykimp. Bræt
        
        int NumberOfBrædder = 12;
        Part doorWood = new Part(planker, 198, NumberOfBrædder, "brædder til at lave døren af sættes");
        parts.add(doorWood);
        
        
        carport.setParts(parts);

    }
}
