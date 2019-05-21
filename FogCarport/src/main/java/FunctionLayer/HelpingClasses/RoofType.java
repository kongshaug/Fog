/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.HelpingClasses;

/**
 *
 * @author aamandajuhl
 */
public class RoofType
{
    private int id;
    private String name;
    private String roof_class;
    private Material m1;
    private Material m2;

    public RoofType(int id, String name, String roof_class, Material m1, Material m2)
    {
        this.id = id;
        this.name = name;
        this.roof_class = roof_class;
        this.m1 = m1;
        this.m2 = m2;
    }

    public RoofType(int id, String name, String roof_class, Material m1)
    {
        this.id = id;
        this.name = name;
        this.roof_class = roof_class;
        this.m1 = m1;
    }

    public RoofType(String name, String roof_class, Material m1)
    {
        this.name = name;
        this.roof_class = roof_class;
        this.m1 = m1;
    }

    public RoofType(String name, String roof_class, Material m1, Material m2)
    {
        this.name = name;
        this.roof_class = roof_class;
        this.m1 = m1;
        this.m2 = m2;
    }

    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    } 
    
    public void setName(String name)
    {
        this.name = name;
    }

    public String getRoof_class()
    {
        return roof_class;
    }
    
    public void setRoof_class(String roof_class)
    {
        this.roof_class = roof_class;
    }

    public Material getM1()
    {
        return m1;
    }

    public void setM1(Material m1)
    {
        this.m1 = m1;
    }

    public Material getM2()
    {
        return m2;
    }
    
    public void setM2(Material m2)
    {
        this.m2 = m2;
    }
}
