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
     * constructor for shed with id from database
     * @param id int 
     * @param depth int 
     * @param width int
     */
    public Shed(int id, int depth, int width)
    {
        this.id = id;
        this.depth = depth;
        this.width = width;
        this.parts = new ArrayList<>();
    }

    /**
     * constructor for new shed
     * @param depth int
     * @param width int
     */
    public Shed(int depth, int width)
    {
        this.depth = depth;
        this.width = width;
        this.parts = new ArrayList<>();
    }

    /**
     *
     * @return the id of the shed
     */
    public int getId()
    {
        return id;
    }

    /**
     * sets the id of the shead
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     *
     * @return the depth of a shead
     */
    public int getDepth()
    {
        return depth;
    }

    /**
     * sets the depth of the shed 
     * @param depth int
     */
    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    /**
     *
     * @return width of the shead
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * sets the width of the shead
     * @param width int
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     *
     * @return a list of all the parts needed to make the shead
     */
    public ArrayList<Part> getParts()
    {
        return parts;
    }

    /**
     * sets a list of all the parts needed to make the shead
     * @param parts list
     */
    public void setParts(ArrayList<Part> parts)
    {
        this.parts = parts;
    }

    /**
     * empties the list of parts needed to make the shead
     */
    public void resetParts()
    {
        this.parts = new ArrayList<>();
    }

}
