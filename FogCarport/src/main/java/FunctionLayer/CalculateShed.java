/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import static java.lang.Math.sqrt;
import java.util.ArrayList;

/**
 *
 * @author benja
 */
public class CalculateShed
{

    private CalculatePackages cp;

    public CalculateShed(CalculatePackages cp)
    {
        this.cp = cp;
    }

    public void calcShed(Carport carport, Material stolpe, Material bræt, Material skruerne,
            Material beklædning, Material skrue1, Material skrue2, Material vinkelbeslag)
    {

        int depth = carport.getShed().getDepth();
        int width = carport.getShed().getWidth();
        int slope = carport.getRoof().getSlope();
        int widthOfCarport = carport.getWidth();

        ArrayList<Part> parts = carport.getShed().getParts();

        // PUT HERE:  caluclatdoorForShed();
        //calculate stolper, 1 in each corner and 1 by each door side
        Part LægteForDoor = new Part(stolpe, 300, 6, "Stolper til hjørner af skur og siderne af døren til skuret");
        parts.add(LægteForDoor);
        
        //front and back on shed has 3 beans, sides have 2 going all the way across:25x150 mm. trykimp. Bræt
        Part LøsholterSides = new Part(bræt, depth, 4, "Løsholter til siderne af skuret");
        parts.add(LøsholterSides);

        Part LøsholterFrontBack = new Part(bræt, width, 6, "Løsholter til for- og bagende af skuret");
        parts.add(LøsholterFrontBack);

        //there is 10 løsholter, 2 vinkelbeslag pr løsholte
        Part vinkelslag = new Part(vinkelbeslag, 0, 20, "Til montering af løsholter");
        parts.add(vinkelslag);

        //There is 2 surfaces pr beslag, 4 skruer pr surface
        int beslagskruer = (20 * 4) * 2;
        int packageofbeslagskruer = cp.calcPackage250(beslagskruer);

        Part packages = new Part(skruerne, 0, packageofbeslagskruer, "Til montering af vinkelbeslag");
        parts.add(packages);

        // the beklædning uses  19x100 mm. trykimp. Bræt
        //each with a 3 cm overlay to the next one
        // 42 is retracted because the material for the door has already been added, a door is 84 cm therefor half is removed 
        int numberOfBræderForFrontBack = ((width - 42) / 7) * 2;

        Part beklædningen = new Part(beklædning, 200, numberOfBræderForFrontBack, "beklædning til skurets for- og bagside");
        parts.add(beklædningen);

        int numberOfBræderForSides = (depth / 7) * 2;

        Part beklædningenSides = new Part(beklædning, 200, numberOfBræderForSides, "Beklædning til skurets sider");
        parts.add(beklædningenSides);

        int skruerYderbræt = ((numberOfBræderForFrontBack + numberOfBræderForSides) / 2) * 6;
        int packagesYderbræt = cp.calcPackage400(skruerYderbræt);

        Part yderbrætSkruer = new Part(skrue1, 0, packagesYderbræt, "Til montering af yderbræt, ved beklædning af gavl");
        parts.add(yderbrætSkruer);

        int skruerInderbræt = ((numberOfBræderForFrontBack + numberOfBræderForSides) / 2) * 3;
        int packagesInderbræt = cp.calcPackage300(skruerInderbræt);

        Part inderbrætSkruer = new Part(skrue2, 0, packagesInderbræt, "Til montering af inderbræt, ved beklædning af gavl");
        parts.add(inderbrætSkruer);

        //calculate the wood needed to cover the part of the shed from the top of the shed to the roof
        if (slope != 0 && width + 30 < widthOfCarport)
        {

            double halfRoof = (width / 2) / Math.cos(slope);

            //height of roof
            int height = (int) (sqrt(halfRoof * halfRoof - width * width));

            Part beklædningenSidesToTop = new Part(beklædning, height, numberOfBræderForSides + numberOfBræderForFrontBack, "Beklædning til skurets gavl");
            parts.add(beklædningenSidesToTop);
        }

        carport.getShed().setParts(parts);

    }

    public void calculatedoorForShed(Carport carport, Material Lægte, Material stalddørsgrebene, Material hængselet, Material planker)
    {
        ArrayList<Part> parts = carport.getShed().getParts();

        //here we calculate all the parts needed for a door
        Part LægteForDoor = new Part(Lægte, 420, 1, "Til z på bagside af dør");
        parts.add(LægteForDoor);

        Part stalddørsgreb = new Part(stalddørsgrebene, 0, 1, "Til lås på dør i skur");
        parts.add(stalddørsgreb);

        Part hængsel = new Part(hængselet, 0, 1, "Til skurdør");
        parts.add(hængsel);

        //the wood to make the door itself, it is: 19x100 mm. trykimp. Bræt
        int NumberOfBrædder = 12;
        Part doorWood = new Part(planker, 198, NumberOfBrædder, "Brædder til at lave døren af sættes");
        parts.add(doorWood);

        carport.getShed().setParts(parts);

    }
}
