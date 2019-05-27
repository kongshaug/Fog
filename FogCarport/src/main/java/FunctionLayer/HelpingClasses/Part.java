/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.HelpingClasses;

/**
 *
 * @author benja
 */
public class Part
{
    private Material material;
    private int length;
    private int quantity;
    private String description;
    private int id;
    private String name;
    private String unit;
    private String material_class;
    private double total_price;

    /**
     * Initializes the values of a newly created Part
     * as well as calculate the total_price of the part.
     * 
     * @param material an object from the class Material
     * @param length an Integer
     * @param quantity an Integer
     * @param description a String
     */
    public Part(Material material, int length, int quantity, String description)
    {
        this.material = material;
        this.id = material.getId();
        this.name = material.getName();
        this.unit = material.getUnit();
        this.material_class = material.getMaterial_class();
        this.length = length;
        this.quantity = quantity;
        this.description = description;
        this.total_price = calcTotalPrice();
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
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     *
     * @return the unit
     */
    public String getUnit()
    {
        return unit;
    }

    /**
     *
     * @return the length
     */
    public int getLength()
    {
        return length;
    }

    /**
     *
     * @return the quantity
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     *
     * @return an object from the class Material
     */
    public Material getMaterial()
    {
        return material;
    }

    /**
     *
     * @return the material_class
     */
    public String getMaterial_class()
    {
        return material_class;
    }

    /**
     *
     * @return the description 
     */
    public String getDescription()
    {
        return description;
    }

    /**
     *
     * @return a double, the total_price 
     */
    public double getTotal_price()
    {
        return total_price;
    }

    private double calcTotalPrice()
    {
        double price;

        if (length == 0)
        {
            price = (material.getPrice() * quantity);
        } else
        {
            double length_meter = length;
            double Qua = quantity;
            price = ((Qua * length_meter) / 100.0) * material.getPrice();
        }

        price = Math.round(price * 100.0) / 100.0;
        return price;
    }

}
