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

    public Roof(int id, int slope, RoofType type)
    {
        this.id = id;
        this.slope = slope;
        this.type = type;
        this.parts = new ArrayList<>();
    }

    public Roof(int slope, RoofType type)
    {
        this.slope = slope;
        this.type = type;
        this.parts = new ArrayList<>();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public ArrayList<Part> getParts()
    {
        return parts;
    }

    public void setParts(ArrayList<Part> parts)
    {
        this.parts = parts;
    }

    public void resetParts()
    {
        this.parts = new ArrayList<>();
    }

    public int getSlope()
    {
        return slope;
    }

    public void setSlope(int slope)
    {
        this.slope = slope;
    }

    public RoofType getType()
    {
        return type;
    }

    public void setType(RoofType type)
    {
        this.type = type;
    }
}
