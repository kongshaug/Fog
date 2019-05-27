/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import DataLayer.DataException;
import DataLayer.DataFacade;
import FunctionLayer.HelpingClasses.Carport;
import FunctionLayer.HelpingClasses.Material;
import FunctionLayer.HelpingClasses.Part;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author benja
 */
public class CalculateShed implements Calculate
{

    private DataFacade db;
    private CalculatePackages cp;
    private Map<Integer, Material> map;

    /**
     * Used for ininitializing parameter DataFacade as db, 
     * CalculatePackages as cp and create a map with all materials.
     * 
     * @param db an instance of the class DataFacade
     * @param cp an object of the class CalculatePackages
     * @throws DataLayer.DataException if initializing not possible
     */
    public CalculateShed(DataFacade db, CalculatePackages cp) throws DataException
    {
        this.db = db;
        this.cp = cp;
        this.map = getMaterials();
    }

    /**
     * 
     * Retrives the material from the map, calculate the amount 
     * of used materials needed to build a shed and adds the materials
     * to the ArrayList of parts to the shed.
     *
     * @param carport an object - calculate the materials for a flat roof connected to the specific carport
     */
    public void calcShed(Carport carport)
    {
        Material stolpe = map.get(2);
        Material bræt = map.get(4);
        Material vinkelbeslag = map.get(22);
        Material skruer = map.get(32);
        Material beklædning = map.get(5);
        Material skrue1 = map.get(33);
        Material skrue2 = map.get(34);

        int depth = carport.getShed().getDepth();
        int width = carport.getShed().getWidth();

        ArrayList<Part> parts = carport.getShed().getParts();
        ArrayList<Integer> skruerForDoor = calculatedoorForShed(carport);

        //calculate poles, 1 in each corner and 1 by each side of the door
        Part poles = new Part(stolpe, 300, 6, "Stolper til hjørner af skur og siderne af døren til skuret");
        parts.add(poles);

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

        Part packages = new Part(skruer, 0, packageofbeslagskruer, "Til montering af vinkelbeslag");
        parts.add(packages);

        // the beklædning uses  19x100 mm. trykimp. Bræt
        //each with a 3 cm overlay to the next one
        // 42 is retracted because the material for the door has already been added, a door is 84 cm therefor half is removed 
        int numberOfBræderForFrontBack = ((width - 42) / 7) * 2;

        Part beklædningen = new Part(beklædning, 200, numberOfBræderForFrontBack, "Beklædning til skurets for- og bagside");
        parts.add(beklædningen);

        int numberOfBræderForSides = (depth / 7) * 2;

        if (carport.getRoof().getSlope() != 0)
        {
            //bekældning is same height as carport, so that the customer can choose where to place it
            int widthOfCarport = carport.getWidth();
            int slope = carport.getRoof().getSlope();

            double cosAngle = Math.cos(Math.toRadians(slope));
            double halfRoofD = (widthOfCarport / 2) / cosAngle;
            int halfRoof = (int) halfRoofD;

            //height of roof
            int height = (int) (sqrt(halfRoof * halfRoof - ((widthOfCarport / 2) * (widthOfCarport / 2))));

            Part beklædningenSides = new Part(beklædning, 200 + height, numberOfBræderForSides, "Beklædning til skurets sider (skal skæres til efter ønsket placering)");
            parts.add(beklædningenSides);
        } else
        {
            Part beklædningenSides = new Part(beklædning, 200, numberOfBræderForSides, "Beklædning til skurets sider");
            parts.add(beklædningenSides);
        }

        int skruerYderbræt = ((numberOfBræderForFrontBack + numberOfBræderForSides) / 2) * 6;
        int packagesYderbræt = cp.calcPackage400(skruerYderbræt + skruerForDoor.get(0));

        Part yderbrætSkruer = new Part(skrue1, 0, packagesYderbræt, "Til montering af yderbræt, ved beklædning af skur");
        parts.add(yderbrætSkruer);

        int skruerInderbræt = ((numberOfBræderForFrontBack + numberOfBræderForSides) / 2) * 3;
        int packagesInderbræt = cp.calcPackage300(skruerInderbræt + skruerForDoor.get(1));

        Part inderbrætSkruer = new Part(skrue2, 0, packagesInderbræt, "Til montering af inderbræt, ved beklædning af skur");
        parts.add(inderbrætSkruer);
    }


    private ArrayList<Integer> calculatedoorForShed(Carport carport)
    {
        Material lægte = map.get(10);
        Material stalddørsgrebene = map.get(20);
        Material hængselet = map.get(21);
        Material planker = map.get(5);

        ArrayList<Part> parts = carport.getShed().getParts();
        ArrayList<Integer> skruer = new ArrayList<>();

        //here we calculate all the parts needed for a door
        Part LægteForDoor = new Part(lægte, 420, 1, "Til z på bagside af dør");
        parts.add(LægteForDoor);

        Part stalddørsgreb = new Part(stalddørsgrebene, 0, 1, "Til lås på dør i skur");
        parts.add(stalddørsgreb);

        Part hængsel = new Part(hængselet, 0, 1, "Til skurdør");
        parts.add(hængsel);

        //the wood to make the door itself, it is: 19x100 mm. trykimp. Bræt
        int NumberOfBrædder = 12;
        Part doorWood = new Part(planker, 198, NumberOfBrædder, "Brædder til at lave døren af sættes");
        parts.add(doorWood);

        int skruerYderbræt = (NumberOfBrædder / 2) * 6;
        int skruerInderbræt = (NumberOfBrædder / 2) * 3;

        skruer.add(skruerYderbræt);
        skruer.add(skruerInderbræt);

        carport.getShed().setParts(parts);

        return skruer;
    }

    @Override
    public Map<Integer, Material> getMaterials() throws DataException
    {
        Map<Integer, Material> materialMap = new HashMap<>();

        List<Material> materials = db.getMaterials();
        for (Material material : materials)
        {
            materialMap.put(material.getId(), material);
        }
        return materialMap;
    }
}
