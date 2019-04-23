/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FunctionLayer;

/**
 *
 * @author benja
 */
public class Part {
    
    private Material material;
    private int length;
    private int quantity;
    private String description;
    private int id;
    private String name;
    private String unit;

    public Part(Material material, int length, int quantity, String description) {
        this.material = material;
        this.id = material.getId();
        this.name = material.getName();
        this.unit = material.getUnit();
        this.length = length;
        this.quantity = quantity;
        this.description = description;
    }

    public int getLength() {
        return length;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }
    
     
    
}
