/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.primefinder;

import java.util.Scanner;

/**
 *
 */
public class Control extends Thread {
    
    private final static int NTHREADS = 2;
    private final static int MAXVALUE = 10000;
    private final static int TMILISECONDS = 5000;

    private final int NDATA = MAXVALUE / NTHREADS;
    private boolean flagT = true;
    private PrimeFinderThread pft[];
    
    private Control() {
        super();
        this.pft = new  PrimeFinderThread[NTHREADS];
        
        int i;
        for(i = 0;i < NTHREADS - 1; i++) {
            PrimeFinderThread elem = new PrimeFinderThread(i*NDATA, (i+1)*NDATA);
            pft[i] = elem;
        }
        pft[i] = new PrimeFinderThread(i*NDATA, MAXVALUE + 1);
    }
    
    public static Control newControl() {
        return new Control();
    }
    
    private void flagEnd() {
    	boolean f = true;
    	for (int i=0;i<NTHREADS;++i) {
    		if(pft[i].isAlive()) {
    			f=false;
    			System.out.println("mate el while");
    			break;
    		}
    		
    	}
    	flagT = f;
    }

    @Override
    public void run() {
        for(int i = 0;i < NTHREADS;i++ ) {
            pft[i].start();
        }
        Scanner sc = new Scanner(System.in);
        //flagT = false;
        
        long tiempoLim = System.currentTimeMillis() + TMILISECONDS;
        while(flagT) {
        	
        	if (System.currentTimeMillis() >= tiempoLim) {
        		
        		for (int i=0; i< NTHREADS;++i) {
        			System.out.println("hilo" + i + " tiene " + pft[i].getPrimes().size() + " primos");
        			pft[i].goStop();
        			
        		}
        		flagT = false;
        		
        		boolean enter = true;
				while (enter) {
					System.out.println("presionese el enter");
					if(sc.nextLine().equals("")) {
						
						for (int i= 0;i<NTHREADS;++i) {
							pft[i].notifique();
							if(pft[i].isAlive()) {
								flagT=true;
								//break;
							}
						}
						tiempoLim = System.currentTimeMillis() + TMILISECONDS;
						
						
					}
					enter=false;
					break;
				}
				
				
			}
        	//flagEnd();
        }
        
      System.out.println("termino");
        
    }
    
}
