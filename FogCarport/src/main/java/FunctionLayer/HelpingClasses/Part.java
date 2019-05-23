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
     * constructor for a part
     * @param material Material object
     * @param length int
     * @param quantity int
     * @param description String
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
     * @return length of part
     */
    public int getLength()
    {
        return length;
    }

    /**
     *
     * @return quantity of part
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     *
     * @return the material used for the part
     */
    public Material getMaterial()
    {
        return material;
    }

    /**
     *
     * @return the class of the material used for the part
     */
    public String getMaterial_class()
    {
        return material_class;
    }

    /**
     *
     * @return description of the part
     */
    public String getDescription()
    {
        return description;
    }

    /**
     *
     * @return the id of the part
     */
    public int getId()
    {
        return id;
    }

    /**
     *
     * @return the name of the part as a string
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @return the unit of the part
     */
    public String getUnit()
    {
        return unit;
    }

    /**
     *
     * @return the price of the part as a int
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
