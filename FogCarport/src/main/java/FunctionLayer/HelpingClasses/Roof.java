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
     * constructor of a roof from the database with an asigned id
     * @param id int
     * @param slope int
     * @param type RoofType object
     */
    public Roof(int id, int slope, RoofType type)
    {
        this.id = id;
        this.slope = slope;
        this.type = type;
        this.parts = new ArrayList<>();
    }

    /**
     * constructor of a new roof
     * @param slope int
     * @param type RoofType object
     */
    public Roof(int slope, RoofType type)
    {
        this.slope = slope;
        this.type = type;
        this.parts = new ArrayList<>();
    }

    /**
     *
     * @return the id of the roof as a int
     */
    public int getId()
    {
        return id;
    }

    /**
     * sets the id of the roof
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     *
     * @return a list of all the parts needed to make the roof
     */
    public ArrayList<Part> getParts()
    {
        return parts;
    }

    /**
     * sets the list of materials needed to make the roof
     * @param parts
     */
    public void setParts(ArrayList<Part> parts)
    {
        this.parts = parts;
    }

    /**
     *
     */
    public void resetParts()
    {
        this.parts = new ArrayList<>();
    }

    /**
     *
     * @return the slope of the roof as a int
     */
    public int getSlope()
    {
        return slope;
    }

    /**
     * sets the slope of the roof as a int
     * @param slope
     */
    public void setSlope(int slope)
    {
        this.slope = slope;
    }

    /**
     *
     * @return the roof type used to make the roof
     */
    public RoofType getType()
    {
        return type;
    }

    /**
     * set the type of the roof
     * @param type
     */
    public void setType(RoofType type)
    {
        this.type = type;
    }
}
