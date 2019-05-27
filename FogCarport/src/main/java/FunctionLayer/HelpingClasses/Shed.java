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

    /**
     * Initializes the values of a newly created Shed 
     * and creates a list for the parts to the shed.
     * 
     * @param id an Integer 
     * @param depth an Integer 
     * @param width an Integer
     */
    public Shed(int id, int depth, int width)
    {
        this.id = id;
        this.depth = depth;
        this.width = width;
        this.parts = new ArrayList<>();
    }

    /**
     * Initializes the values of a newly created Shed
     * and creates a list for the parts to the shed.
     * 
     * @param depth an Integer
     * @param width an Integer
     */
    public Shed(int depth, int width)
    {
        this.depth = depth;
        this.width = width;
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
     * Sets the id to id 
     * 
     * @param id - an Integer
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     *
     * @return the depth
     */
    public int getDepth()
    {
        return depth;
    }

    /**
     * Sets the depth to depth
     * 
     * @param depth an Integer
     */
    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    /**
     *
     * @return the width 
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Sets the width to width
     * @param width an Integer
     */
    public void setWidth(int width)
    {
        this.width = width;
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

}
