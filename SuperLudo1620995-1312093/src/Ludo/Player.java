package Ludo;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	int inicioX;
	int inicioY;
	
	boolean turno = false;
	
	List<Peao> pecas = new ArrayList<Peao>();
	Peao p1 = new Peao();
	Peao p2 = new Peao();
	Peao p3 = new Peao();
	Peao p4 = new Peao();
	
	
	
	public Player(int cor) {
		pecas.add(p1);
		pecas.add(p2);
		pecas.add(p3);
		pecas.add(p4);
		
		if(cor == 1) {  // RED
			p1.iniX = 1;
			p1.iniY = 1;
			
			p2.iniX = 4;
			p2.iniY = 1;
			
			p3.iniX = 1;
			p3.iniY = 4;
			
			p4.iniX = 4;
			p4.iniY = 4;
			
			inicioX = 1;
			inicioY = 6;
		}
		else if(cor == 2) { // GREEN
			p1.iniX = 10;
			p1.iniY = 1;
			
			p2.iniX = 13;
			p2.iniY = 1;
			
			p3.iniX = 10;
			p3.iniY = 4;
			
			p4.iniX = 13;
			p4.iniY = 4;
			
			inicioX = 8;
			inicioY = 1;
		}
		else if(cor == 3) { // BLUE
			p1.iniX = 1;
			p1.iniY = 10;
			
			p2.iniX = 4;
			p2.iniY = 10;
			
			p3.iniX = 1;
			p3.iniY = 13;
			
			p4.iniX = 4;
			p4.iniY = 13;
			
			inicioX = 6;
			inicioY = 13;
		}
		else if(cor == 4) { // YELLOW
			p1.iniX = 10;
			p1.iniY = 10;
			
			p2.iniX = 13;
			p2.iniY = 10;
			
			p3.iniX = 10;
			p3.iniY = 13;
			
			p4.iniX = 13;
			p4.iniY = 13;
			
			inicioX = 13;
			inicioY = 8;
		}
		p1.inicio();
		p2.inicio();
		p3.inicio();
		p4.inicio();
	}
	public void move(Peao p) {
		int dado = Menu.getInstance().dado;
		
		if(p.posX == p.iniX && p.posY == p.iniY) {
			if(dado == 5) {
				p.posX = inicioX;
				p.posY = inicioY;
			}
		}
		
	}
	
}
