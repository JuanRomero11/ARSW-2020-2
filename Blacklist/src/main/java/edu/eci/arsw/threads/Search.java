package edu.eci.arsw.threads;

import java.util.LinkedList;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

public class Search extends Thread {

	private final int A, B;
	private int servidoresUsados, cantidad;
	private final String ip;
	private LinkedList<Integer> servidoresBad;

	HostBlacklistsDataSourceFacade skds = HostBlacklistsDataSourceFacade.getInstance();
	
	public Search(int x, int y, String ip) {
		A = x;
		B = y;
		this.ip = ip;
		this.servidoresBad = new LinkedList<Integer> ();
	}

	@Override
	public void run() {
		
		for (int i = A; i < B; i++) {

			servidoresUsados++;
			
			if (skds.isInBlackListServer(i, ip)) {
				
				System.out.println("Servidor malicioso numero: " + i);
				servidoresBad.add(i);
				cantidad++;
			}

		}
	}

	public int getServidoresUsados() {
		return servidoresUsados;
	}

	public LinkedList<Integer> getServidoresBad() {
		return servidoresBad;
	}

	public int getCantidad() {
		return cantidad;
	}
	

}
