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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sofieamalielandt
 */
public class CalculateCarport implements Calculate
{

    private DataFacade db;
    private Map<Integer, Material> map;

    /**
     *
     * Used for ininitializing parameter DataFacade as db
     * and create a map with all materials.
     * 
     * @param db an instance of the class DataFacade
     * @throws DataLayer.DataException if initializing not possible
     */
    public CalculateCarport(DataFacade db) throws DataException
    {
        this.db = db;
        this.map = getMaterials();
    }

    /**
     * Calculates the materials used for a carport.
     * 
     * @param carport an object - calculate the materials for a specific carport 
     * @throws DataLayer.DataException if initializing not possible
     */
    public void calcCarport(Carport carport) throws DataException
    {
        calculatepoles(carport);
        calculateRem(carport);
    }

    /**
     * Retrives the material from the map, calculate the amount 
     * of used materials needed to build the carport and adds the materials
     * to the ArrayList of parts to the carport.
     *
     * @param carport an object 
     */
    private void calculatepoles(Carport carport)
    {
        Material pole = map.get(2);
        Material bolt = map.get(26);
        Material disc = map.get(27);

        int depth = carport.getDepth();

        ArrayList<Part> parts = carport.getParts();
        //a pole is placed for each 2 meter on the right and left side of the carport
        int numberOfPoles = (depth / 200 * 2) + 2;
        int boltParts = numberOfPoles * 4;

        Part poles = new Part(pole, 300, numberOfPoles, "Stolper nedgraves 90 cm. i jord");
        Part bolts = new Part(bolt, 0, boltParts, "Til montering af rem	på stolper");
        Part discs = new Part(disc, 0, boltParts, "Til montering af rem	på stolper");
        parts.add(poles);
        parts.add(bolts);
        parts.add(discs);
        carport.setParts(parts);
    }

    /**
     * Calculates the length of the rem needed to bouild the carport and adds it
     * to the list of materials needed.
     *
     * @param carport an object
     * @param rem an Material
     */
    private void calculateRem(Carport carport)
    {
        Material rem = map.get(3);

        int depth = carport.getDepth();

        ArrayList<Part> parts = carport.getParts();
        //calculate rem and put in arraylist

        int remmen = depth;

        Part remmene = new Part(rem, remmen, 2, "Remme i sider, sadles ned i stolper");
        parts.add(remmene);
        carport.setParts(parts);
    }

    /**
     * Retrives a list of materials from the database 
     * and puts the material_id of the material, 
     * together with the material in a Hashmap
     * 
     * @return a materialMap
     * @throws DataException if retrival is not possible
     */
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
