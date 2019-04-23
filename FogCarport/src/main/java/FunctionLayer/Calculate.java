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
    
    
    
  public void caluclateCarport(Carport carport)
{
    int depth = carport.getDepth();
    HashMap<String, HashMap<String,String>> map = carport.getMaterials();
    //a pole is placed for each 2.5 meter on the right and left side of the carport
    int numberOfPoles = depth / 250 * 2;
    HashMap poles = null;
    
    poles.put("navn", "97x97	mm.	trykimp.	Stolpe");
    poles.put("Længde", "300");
    poles.put("Antal", numberOfPoles);
    poles.put("Enhed", "stk");
    poles.put("Beskrivelse", "Stolper	nedgraves	90	cm.	i	jord");
    map.put("poles", poles);
    
    //calculate rem and put in map
    
    HashMap <String, String> rem = new HashMap<>();
    
    int remmen = depth;
    
    rem.put("navn", "45x195	mm.	spærtræ	ubh.");
    rem.put("Længde", "" +remmen);
    rem.put("Antal",""+ 2);
    rem.put("Enhed", "stk");
    rem.put("Beskrivelse", "Remme	i	sider,	sadles	ned	i	stolper");
    map.put("rem", rem);
    
    //calculate Bræddebolte, skive og møtrik 
    
       HashMap <String, String> bolts = new HashMap<>();
       HashMap <String, String> skive = new HashMap<>();
   
    
    int boltParts = numberOfPoles * 4;
    
    bolts.put("navn", "bræddebolt	10	x	120	mm.");
    bolts.put("Længde", null);
    bolts.put("Antal", "" + boltParts);
    bolts.put("Enhed", "stk");
    bolts.put("Beskrivelse", "Til	montering	af	rem	på	stolper");
    map.put("bolts", bolts);
 
    skive.put("navn", "firkantskiver	40x40x11mm");
    skive.put("Længde", null);
    skive.put("Antal", ""+boltParts);
    skive.put("Enhed", "stk");
    skive.put("Beskrivelse", "Til montering	af	rem	på	stolper");
    map.put("skive", skive);
    
 
    
    
     carport.setMaterials(map);
}  
  
  
}
