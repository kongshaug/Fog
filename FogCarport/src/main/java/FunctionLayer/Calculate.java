/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FunctionLayer;

import java.util.HashMap;

/**
 *
 * @author benja
 */
public class Calculate {
    
    Carport carport = new Carport(1000,1000, null);
    HashMap map = carport.getMaterials();
    
    
  public void caluclatePostes(int depth)
{
    //a pole is placed for each 2.5 meter on the right and left side of the carport
    int numberOfPoles = depth / 250 * 2;
    HashMap poles = null;
    
    poles.put("navn", "25x200	mm.	trykimp.	Brædt");
    poles.put("Længde", "300");
    poles.put("Antal", numberOfPoles);
    poles.put("Enhed", "stk");
    poles.put("Beskrivelse", "understernbrædder	til	for	&	bag	ende");
    map.put("poles", poles);
 
}  
  
  
}
