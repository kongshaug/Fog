/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

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
    }

    public Roof(int slope, RoofType type)
    {
        this.slope = slope;
        this.type = type;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setParts(ArrayList<Part> parts)
    {
        this.parts = parts;
    }

    public int getId()
    {
        return id;
    }

    public int getSlope()
    {
        return slope;
    }

    public RoofType getType()
    {
        return type;
    }

    public ArrayList<Part> getParts()
    {
        return parts;
    }

}
