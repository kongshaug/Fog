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

    public int getLength()
    {
        return length;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public Material getMaterial()
    {
        return material;
    }

    public String getMaterial_class()
    {
        return material_class;
    }

    public String getDescription()
    {
        return description;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getUnit()
    {
        return unit;
    }

    public double getTotal_price()
    {
        return total_price;
    }

    private double calcTotalPrice()
    {
        if (length == 0)
        {
            return (material.getPrice() * quantity);
        } else
        {
            double length_meter = length;
            double Qua = quantity;

            return ((Qua * length_meter) / 100.0) * material.getPrice();
        }
    }

}
