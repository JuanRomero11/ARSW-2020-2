/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {
    
    public static void main(String a[]){
    	
    	CountThread conteo1,conteo2,conteo3;
    	
    	conteo1 = new CountThread(0,99);
    	conteo2 = new CountThread(99,199);
    	conteo3 = new CountThread(200,299);
    	
    	conteo1.run();
    	conteo2.run();
    	conteo3.run();
        
    }
    
}
