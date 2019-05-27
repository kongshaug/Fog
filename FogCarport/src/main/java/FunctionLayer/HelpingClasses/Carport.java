/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.HelpingClasses;

import java.util.ArrayList;

/**
 *
 * @author sofieamalielandt
 */
public class Carport
{

    private int id;
    private int width;
    private int depth;
    private ArrayList<Part> parts;
    private Roof roof;
    private Shed shed;
    private double total_price;

    /**
     * Initializes the values of a newly created Carport
     * with a shed, sets total_price to 0.0 and
     * creates a list for the parts to the carport.
     * 
     * @param width an Integer
     * @param depth an Integer 
     * @param roof an object of the class Roof
     * @param shed an object of the class Shed
     */
    public Carport(int width, int depth, Roof roof, Shed shed)
    {
        this.width = width;
        this.depth = depth;
        this.roof = roof;
        this.shed = shed;
        this.parts = new ArrayList<>();
        this.total_price = 0.0;
    }

    /**
     * Initializes the values of a newly created Carport 
     * without a shed, sets total_price to 0.0 and
     * creates a list for the parts to the carport.
     * 
     * @param width an Integer
     * @param depth an Integer
     * @param roof an object of the class Roof
     */
    public Carport(int width, int depth, Roof roof)
    {
        this.width = width;
        this.depth = depth;
        this.roof = roof;
        this.parts = new ArrayList<>();
        this.total_price = 0.0;
    }

    /**
     * Initializes the values of a newly created Carport
     * with a shed, sets total_price to 0.0 and
     * creates a list for the parts to the carport.
     * 
     * @param id an Integer
     * @param width an Integer 
     * @param depth an Integer
     * @param roof an object of the class Roof
     * @param shed an object of the class Shed
     */
    public Carport(int id, int width, int depth, Roof roof, Shed shed)
    {
        this.id = id;
        this.width = width;
        this.depth = depth;
        this.roof = roof;
        this.shed = shed;
        this.parts = new ArrayList<>();
        this.total_price = 0.0;
    }

    /**
     * Initializes the values of a newly created Carport 
     * without a shed, sets total_price to 0.0 and
     * creates a list for the parts to the carport.
     * 
     * @param id an Integer
     * @param width an Integer
     * @param depth an Integer 
     * @param roof an object of the class Roof
     */
    public Carport(int id, int width, int depth, Roof roof)
    {
        this.id = id;
        this.width = width;
        this.depth = depth;
        this.roof = roof;
        this.parts = new ArrayList<>();
        this.total_price = 0.0;
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
     * @return the width 
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Sets the width to width.
     * 
     * @param width an Integer
     */
    public void setWidth(int width)
    {
        this.width = width;
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
     * Sets the depth to depth.
     * 
     * @param depth an Integer
     */
    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    /**
     *
     * @return the roof 
     */
    public Roof getRoof()
    {
        return roof;
    }

    /**
     * 
     * @return the shed
     */
    public Shed getShed()
    {
        return shed;
    }

    /**
     * Sets the shed to shed.
     * 
     * @param shed an object from the class Shed
     */
    public void setShed(Shed shed)
    {
        this.shed = shed;
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
     */
    public void resetParts()
    {
        this.parts = new ArrayList<>();
    }

    private double calcTotalPrice()
    {
        double totalPrice = 0.0;
        for (Part part : parts)
        {
            totalPrice += part.getTotal_price();
        }
        for (Part part : getRoof().getParts())
        {
            totalPrice += part.getTotal_price();
        }
        if (shed != null)
        {
            for (Part part : getShed().getParts())
            {
                totalPrice += part.getTotal_price();
            }
        }
        return totalPrice;
    }

    /**
     * Calculates the total_price of an carport
     * with a roof and possible shed.
     * 
     * @return The total price as a double
     */
    public double getTotal_price()
    {
        total_price = calcTotalPrice();
        total_price = Math.round(total_price * 100.0) / 100.0;
        return total_price;
    }
    
}
