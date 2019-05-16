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
public class Shed
{
    private int id;
    private int depth;
    private int width;
    private ArrayList<Part> parts;

    public Shed(int id, int depth, int width)
    {
        this.id = id;
        this.depth = depth;
        this.width = width;
        this.parts = new ArrayList<>();
    }

    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public Shed(int depth, int width)
    {
        this.depth = depth;
        this.width = width;
        this.parts = new ArrayList<>();
    }

    public int getId()
    {
        return id;
    }

    public int getDepth()
    {
        return depth;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getWidth()
    {
        return width;
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
    
}
