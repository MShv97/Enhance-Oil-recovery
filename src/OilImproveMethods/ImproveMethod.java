/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OilImproveMethods;


/**
 *
 * @author MSh
 */
public class ImproveMethod{
   
    
    void steamMethod(OilFieldsList.OilField of){
        int cnt = 0;
        if(of.getApi() < 20) cnt++;
        if(of.getVicosity() >50) cnt++;
        if(of.getK()>20) cnt++;
        if(of.getSaturation() >35) cnt++;
        if(of.getDepth() < 1500) cnt++;
        if(of.getThickness() < 20) cnt++;
        if(of.getTemprature() > 100) cnt++;
        if(of.getLithology() == "Sandstone") cnt++;
    }
}
