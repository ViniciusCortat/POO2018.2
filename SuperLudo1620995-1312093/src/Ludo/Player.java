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
		
		if(cor == 1) { 			
			
			p1.SetPos(1, 1);
			p2.SetPos(4, 1);
			p3.SetPos(1, 4);
			p4.SetPos(4, 4);
			
			for(Peao p : pecas )
			{
				p.SetCasaIni(1, 6);
				p.SetStartDir(1);
			}
		}
		else if(cor == 2) { // GREEN
			
			p1.SetPos(10, 1);
			p2.SetPos(13, 1);
			p3.SetPos(10, 4);
			p4.SetPos(13, 4);
			
			for(Peao p : pecas )
			{
				p.SetCasaIni(8, 1);
			}
		}
		else if(cor == 3) { // BLUE
			
			p1.SetPos(1, 10);
			p2.SetPos(4, 10);
			p3.SetPos(1, 13);
			p4.SetPos(4, 13);
			
			for(Peao p : pecas )
			{
				p.SetCasaIni(6, 13);
			}
		}
		else if(cor == 4) { // YELLOW
			
			p1.SetPos(10, 10);
			p2.SetPos(13, 10);
			p3.SetPos(10, 13);
			p4.SetPos(13, 13);
			
			for(Peao p : pecas )
			{
				p.SetCasaIni(8, 1);
			}
		}
	}
	
	public Peao GetPeao(int x, int y)
	{
		for(Peao p : pecas)
		{
			if(p.GetPosX() == x && p.GetPosY() == y)
				return p;
		}
		return null;		
	}
	
}
