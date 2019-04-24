/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 *
 * @author aamandajuhl
 */
public class RoofType
{

    private int id;
    private String name;
    private Material m1;
    private Material m2;

    public RoofType(int id, String name, Material m1, Material m2)
    {
        this.id = id;
        this.name = name;
        this.m1 = m1;
        this.m2 = m2;
    }

    public RoofType(int id, String name, Material m1)
    {
        this.id = id;
        this.name = name;
        this.m1 = m1;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Material getM1()
    {
        return m1;
    }

    public Material getM2()
    {
        return m2;
    }

}
