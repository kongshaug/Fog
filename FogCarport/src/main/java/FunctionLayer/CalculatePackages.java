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
public class CalculatePackages
{
    public Integer calcPackage100(int amount)
    {
        int packages = amount / 100;
        if (amount % 100 != 0 || packages == 0)
        {
            packages++;
        }
        return packages;
    }

    public Integer calcPackage200(int amount)
    {
        int packages = amount / 200;
        if (amount % 200 != 0 || packages == 0)
        {
            packages++;
        }
        return packages;
    }

    public Integer calcPackage250(int amount)
    {
        int packages = amount / 250;
        if (amount % 250 != 0 || packages == 0)
        {
            packages++;
        }
        return packages;
    }

    public Integer calcPackage300(int amount)
    {
        int packages = amount / 300;
        if (amount % 300 != 0 || packages == 0)
        {
            packages++;
        }
        return packages;
    }

    public Integer calcPackage350(int amount)
    {
        int packages = amount / 350;
        if (amount % 350 != 0 || packages == 0)
        {
            packages++;
        }
        return packages;
    }

    public Integer calcPackage400(int amount)
    {
        int packages = amount / 400;
        if (amount % 400 != 0 || packages == 0)
        {
            packages++;
        }
        return packages;
    }
}
