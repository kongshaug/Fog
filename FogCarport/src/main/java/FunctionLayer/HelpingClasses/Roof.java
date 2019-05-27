/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.HelpingClasses;

import java.util.ArrayList;

/**
 *
 * @author aamandajuhl
 */
public class Roof
{
    private int id;
    private int slope;
    private RoofType type;
    private ArrayList<Part> parts;

    /**
     * Initializes the values of a newly created Roof 
     * and creates a list for the parts to the roof.
     * 
     * @param id an Integer
     * @param slope an Integer
     * @param type an object from the class RoofType
     */
    public Roof(int id, int slope, RoofType type)
    {
        this.id = id;
        this.slope = slope;
        this.type = type;
        this.parts = new ArrayList<>();
    }

    /**
     * Initializes the values of a newly created Roof 
     * and creates a list for the parts to the roof.
     * 
     * @param slope an Integer
     * @param type an object from the class RoofType
     */
    public Roof(int slope, RoofType type)
    {
        this.slope = slope;
        this.type = type;
        this.parts = new ArrayList<>();
    }

    /**
     *
     * @return the id 
     */
    public int getId()
    {
        return id;
    }

    /**
     * Sets the id to id.
     * 
     * @param id an Integer
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     *
     * @return an ArrayList of objects from the class Part
     */
    public ArrayList<Part> getParts()
    {
        return parts;
    }

    /**
     * Sets an Arraylist of objects from the class Part.
     * 
     * @param parts an ArrayList
     */
    public void setParts(ArrayList<Part> parts)
    {
        this.parts = parts;
    }

    /**
     *
     * Removes all parts, and creates a new empty ArrayList.
     * 
     */
    public void resetParts()
    {
        this.parts = new ArrayList<>();
    }

    /**
     *
     * @return the slope
     */
    public int getSlope()
    {
        return slope;
    }

    /**
     * Sets the slope to slope.
     * 
     * @param slope an Integer
     */
    public void setSlope(int slope)
    {
        this.slope = slope;
    }

    /**
     *
     * @return the type
     */
    public RoofType getType()
    {
        return type;
    }

    /**
     * Sets the type to type.
     * 
     * @param type an object of the class RoofType
     */
    public void setType(RoofType type)
    {
        this.type = type;
    }
}
