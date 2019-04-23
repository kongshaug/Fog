/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;


import java.util.HashMap;



/**
 *
 * @author sofieamalielandt
 */
public class Carport
{
    private int widgh;
    private int depth;
    private HashMap<String, HashMap<String, String>> materials;
    private roof roof;
    private shed shed;

    public Carport(int widgh, int depth, roof roof, shed shed) {
        this.widgh = widgh;
        this.depth = depth;
        this.roof = roof;
        this.shed = shed;
     
    }

    public Carport(int widgh, int depth, roof roof) {
        this.widgh = widgh;
        this.depth = depth;
        this.roof = roof;
    }


    public int getWidgh() {
        return widgh;
    }

    public int getDepth() {
        return depth;
    }

    public HashMap<String, HashMap<String, String>> getMaterials() {
        return materials;
    }

    public roof getRoof() {
        return roof;
    }

    public shed getShed() {
        return shed;
    }

    public void setMaterials(HashMap<String, HashMap<String, String>> materials) {
        this.materials = materials;
    }
    
    
}
