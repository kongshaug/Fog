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
     * constructor for a material from the database with a id assigned
     * @param id int
     * @param name String
     * @param unit String
     * @param material_class String
     * @param price dobble
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
     * constructor for a new material without a id assigned
     * @param name int
     * @param unit String
     * @param material_class String
     * @param price dobble
     */
    public Material(String name, String unit, String material_class, double price)
    {
        this.name = name;
        this.unit = unit;
        this.material_class = material_class;
        this.price = price;
    }

    /**
     * gets the price of the material
     * @return price of material
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * sets the price of a material
     * @param price
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     *
     * @return id of material
     */
    public int getId()
    {
        return id;
    }

    /**
     * sets the id of the material
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /** 
     * 
     * @return unit of the material
     */
    public String getUnit()
    {
        return unit;
    }

    /**
     * sets the unit of the material
     * @param unit
     */
    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    /**
     *
     * @return the class of the material
     */
    public String getMaterial_class()
    {
        return material_class;
    }

    /**
     * sets the material class
     * @param material_class
     */
    public void setMaterial_class(String material_class)
    {
        this.material_class = material_class;
    }

    /**
     *
     * @return the name of the material
     */
    public String getName()
    {
        return name;
    }

    /**
     * sets the name of the material
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }
}
