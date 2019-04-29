/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

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

    public Carport(int width, int depth, Roof roof, Shed shed)
    {
        this.width = width;
        this.depth = depth;
        this.roof = roof;
        this.shed = shed;
        this.parts = new ArrayList<>();
        this.total_price = 0.0;
    }

    public Carport(int width, int depth, Roof roof)
    {
        this.width = width;
        this.depth = depth;
        this.roof = roof;
        this.parts = new ArrayList<>();
        this.total_price = 0.0;
    }

    public Carport(int id, int width, int depth, Roof roof, Shed shed)
    {
        this.width = width;
        this.depth = depth;
        this.roof = roof;
        this.shed = shed;
        this.parts = new ArrayList<>();
        this.total_price = 0.0;
    }

    public Carport(int id, int width, int depth, Roof roof)
    {
        this.width = width;
        this.depth = depth;
        this.roof = roof;
        this.parts = new ArrayList<>();
        this.total_price = 0.0;
    }

    public int getId()
    {
        return id;
    }

    public int getWidth()
    {
        return width;
    }

    public int getDepth()
    {
        return depth;
    }

    public Roof getRoof()
    {
        return roof;
    }

    public Shed getShed()
    {
        return shed;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public ArrayList<Part> getParts()
    {
        return parts;
    }

    public void setParts(ArrayList parts)
    {
        this.parts = parts;
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

    public double getTotal_price()
    {
        total_price = calcTotalPrice();
        return total_price;
    }

}
