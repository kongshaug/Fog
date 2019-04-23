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
    private int length;
    private int hight;
    private HashMap<String, HashMap<String, String>> materials;
    private roof roof;
    private shed shed;

    public Carport(int length, int hight, roof roof, shed shed) {
        this.length = length;
        this.hight = hight;
        this.roof = roof;
        this.shed = shed;
    }
        public Carport(int length, int hight, roof roof) {
        this.length = length;
        this.hight = hight;
        this.roof = roof;
       
    }

    public int getLength() {
        return length;
    }

    public int getHight() {
        return hight;
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
