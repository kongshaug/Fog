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

    public Material(int id, String name, String unit, String material_class, double price)
    {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.material_class = material_class;
        this.price = price;
    }

    public Material(String name, String unit, String material_class, double price)
    {
        this.name = name;
        this.unit = unit;
        this.material_class = material_class;
        this.price = price;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getMaterial_class()
    {
        return material_class;
    }

    public void setMaterial_class(String material_class)
    {
        this.material_class = material_class;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
