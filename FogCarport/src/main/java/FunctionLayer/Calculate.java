/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import DataLayer.DataException;
import FunctionLayer.HelpingClasses.Material;
import java.util.Map;

/**
 *
 * @author sofieamalielandt
 */
public interface Calculate
{
    Map<Integer, Material> getMaterials() throws DataException;
}
