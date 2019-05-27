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

    /**
     * Initializes the values of a newly 
     * created RoofType with two materials
     * 
     * @param id an Integer
     * @param name a String
     * @param roof_class a String
     * @param m1 an object from the class Material
     * @param m2 an object from the class Material
     */
    public RoofType(int id, String name, String roof_class, Material m1, Material m2)
    {
        this.id = id;
        this.name = name;
        this.roof_class = roof_class;
        this.m1 = m1;
        this.m2 = m2;
    }

    /**
     * Initializes the values of a newly 
     * created RoofType with one materials
     * 
     * @param id an Integer
     * @param name a String
     * @param roof_class a String
     * @param m1 an object from the class Material
     */
    public RoofType(int id, String name, String roof_class, Material m1)
    {
        this.id = id;
        this.name = name;
        this.roof_class = roof_class;
        this.m1 = m1;
    }

    /**
     * Initializes the values of a newly 
     * created RoofType with one materials
     * 
     * @param name a String
     * @param roof_class a String
     * @param m1 an object from the class Material
     */
    public RoofType(String name, String roof_class, Material m1)
    {
        this.name = name;
        this.roof_class = roof_class;
        this.m1 = m1;
    }

    /**
     *  Initializes the values of a newly 
     * created RoofType with two materials
     * 
     * @param name a String
     * @param roof_class a String
     * @param m1 an object from the class Material
     * @param m2 an object from the class Material
     */
    public RoofType(String name, String roof_class, Material m1, Material m2)
    {
        this.name = name;
        this.roof_class = roof_class;
        this.m1 = m1;
        this.m2 = m2;
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
     * Sets the id to id
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
     * Sets the name to name 
     * 
     * @param name a String
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     *
     * @return the roof_class 
     */
    public String getRoof_class()
    {
        return roof_class;
    }

    /**
     *
     * @return material
     */
    public Material getM1()
    {
        return m1;
    }

    /**
     * Sets M1 to m1
     * @param m1 an object from the class Material
     */
    public void setM1(Material m1)
    {
        this.m1 = m1;
    }

    /**
     *
     * @return materail
     */
    public Material getM2()
    {
        return m2;
    }
    
    /**
     * Sets M2 to m2
     * @param m2 an object from the class Material
     */
    public void setM2(Material m2)
    {
        this.m2 = m2;
    }
}
