/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.HelpingClasses;

/**
 *
 * @author sofieamalielandt
 */
public class Material
{
    private int id;
    private String name;
    private String unit;
    private String material_class;
    private double price;

    /**
     * Initializes the values of a newly created Material.
     * 
     * @param id an Integer
     * @param name a String
     * @param unit a String
     * @param material_class a String
     * @param price a double
     */
    public Material(int id, String name, String unit, String material_class, double price)
    {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.material_class = material_class;
        this.price = price;
    }

    /**
     * Initializes the values of a newly created Material.
     * 
     * @param name a String
     * @param unit a String
     * @param material_class a String
     * @param price a double
     */
    public Material(String name, String unit, String material_class, double price)
    {
        this.name = name;
        this.unit = unit;
        this.material_class = material_class;
        this.price = price;
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
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name to name.
     * 
     * @param name a String
     */
    public void setName(String name)
    {
        this.name = name;
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
     * Sets the unit to unit.
     * 
     * @param unit a String
     */
    public void setUnit(String unit)
    {
        this.unit = unit;
    }
    
    /**
     * @return the price 
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Sets the price to price.
     * 
     * @param price a double
     */
    public void setPrice(double price)
    {
        this.price = price;
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
     * Sets the material class to material_class.
     * 
     * @param material_class a String
     */
    public void setMaterial_class(String material_class)
    {
        this.material_class = material_class;
    }

}
