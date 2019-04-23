/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author sofieamalielandt
 */
public class Carport
{
    private int id;
    private int width;
    private int depth;
    private ArrayList<Part> parts;
    private roof roof;
    private shed shed;

    public Carport(int width, int depth, roof roof, shed shed) {
        this.width = width;
        this.depth = depth;
        this.roof = roof;
        this.shed = shed;
     
    }

    public Carport(int width, int depth, roof roof) {
        this.width = width;
        this.depth = depth;
        this.roof = roof;
    }
    
    public Carport(int id, int width, int depth, roof roof, shed shed) {
        this.width = width;
        this.depth = depth;
        this.roof = roof;
        this.shed = shed;
     
    }

    public Carport(int id, int width, int depth, roof roof) {
        this.width = width;
        this.depth = depth;
        this.roof = roof;
    }


    public int getId() {
        return id;
    }
    
    public int getWidth() {
        return width;
    }

    public int getDepth() {
        return depth;
    }


    public roof getRoof() {
        return roof;
    }

    public shed getShed() {
        return shed;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public ArrayList getParts() {
        return parts;
    }

    public void setParts(ArrayList parts) {
        this.parts = parts;
    }
    
    
}
