/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

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

    public Material(int id, String name, String unit, String material_class)
    {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.material_class = material_class;
    }

    public String getMaterial_class()
    {
        return material_class;
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

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return id + " " + name + " " + unit;
    }
    
}
