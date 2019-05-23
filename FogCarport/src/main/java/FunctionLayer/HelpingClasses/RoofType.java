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
     * constructor for making a RoofType from the database with assigned id
     * @param id int
     * @param name String
     * @param roof_class String
     * @param m1 Material
     * @param m2 Material
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
     * Constructor used to make Rooftype with assigned id, with only one material associated
     * @param id int
     * @param name String
     * @param roof_class String
     * @param m1 Material
     */
    public RoofType(int id, String name, String roof_class, Material m1)
    {
        this.id = id;
        this.name = name;
        this.roof_class = roof_class;
        this.m1 = m1;
    }

    /**
     * Constructor used to make rooftype object with one material associated wihout id assosiated with it
     * @param name String
     * @param roof_class String
     * @param m1 Material
     */
    public RoofType(String name, String roof_class, Material m1)
    {
        this.name = name;
        this.roof_class = roof_class;
        this.m1 = m1;
    }

    /**
     * Constructor to make Rooftype with 2 materials associated with it
     * @param name Stirng
     * @param roof_class String
     * @param m1 Material
     * @param m2 Material
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
     * @return the id of the rooftype as a int
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * sets the id of the rooftype as a int
     * @param id int
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     *
     * @return the name of the rooftype as a String
     */
    public String getName()
    {
        return name;
    } 
    
    /**
     * Sets the name of the rooftype
     * @param name String
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     *
     * @return the class of the roof type as a String
     */
    public String getRoof_class()
    {
        return roof_class;
    }
    
    /**
     * Sets the class of the rooftype as a String
     * @param roof_class String
     */
    public void setRoof_class(String roof_class)
    {
        this.roof_class = roof_class;
    }

    /**
     *
     * @return the first material associated with the rooftype
     */
    public Material getM1()
    {
        return m1;
    }

    /**
     * sets the first material associated with the rooftype
     * @param m1 Material
     */
    public void setM1(Material m1)
    {
        this.m1 = m1;
    }

    /**
     *
     * @return the second material associated with the rooftype
     */
    public Material getM2()
    {
        return m2;
    }
    
    /**
     * sets the second material associated with the rooftype
     * @param m2 Material
     */
    public void setM2(Material m2)
    {
        this.m2 = m2;
    }
}
