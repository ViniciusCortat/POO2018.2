package Ludo;

import java.util.ArrayList;
import java.util.List;

public class Player implements Subject
{
	private List<Observer> observers;
	
	public class Barrier {
		int posX;
		int posY;
		boolean bar;
		
		public Barrier() {
			posX = 50;
			posY = 50;
			bar = false;
		}
	}
	
	int inicioX;
	int inicioY;
	
	int fimX;
	int fimY;
	
	public int cor;
	private int pontos;
	
	List<Peao> pecas = new ArrayList<Peao>();

	Peao p1 = new Peao();
	Peao p2 = new Peao();
	Peao p3 = new Peao();
	Peao p4 = new Peao();
	
	Barrier bar1 = new Barrier();
	Barrier bar2 = new Barrier();
	
	public Player(int cor) {
		
		this.observers=new ArrayList<>();
		register(Menu.getInstance());
		
		this.cor = cor;
		
		pecas.add(p1);
		pecas.add(p2);
		pecas.add(p3);
		pecas.add(p4);
		
		if(cor == 0) { 			
			
			p1.SetPos(1, 1);
			p2.SetPos(4, 1);
			p3.SetPos(1, 4);
			p4.SetPos(4, 4);
			
			p1.SetStartGamePos(1, 1);
			p2.SetStartGamePos(4, 1);
			p3.SetStartGamePos(1, 4);
			p4.SetStartGamePos(4, 4);
			
			for(Peao p : pecas )
			{
				p.SetCasaIni(1, 6);
				p.SetStartDir(1);
				p.cor = 0;
			}
			
			inicioX = 1;
			inicioY = 6;
			
			fimX = 6;
			fimY = 7;
		}
		else if(cor == 1) { // GREEN
			
			p1.SetPos(10, 1);
			p2.SetPos(13, 1);
			p3.SetPos(10, 4);
			p4.SetPos(13, 4);
			
			p1.SetStartGamePos(10, 1);
			p2.SetStartGamePos(13, 1);
			p3.SetStartGamePos(10, 4);
			p4.SetStartGamePos(13, 4);
			
			for(Peao p : pecas )
			{
				p.SetCasaIni(8, 1);
				p.SetStartDir(2);
				p.cor = 1;
			}
			
			inicioX = 8;
			inicioY = 1;
			
			fimX = 7;
			fimY = 6;
		}
		else if(cor == 2) { // BLUE
			
			p1.SetPos(1, 10);
			p2.SetPos(4, 10);
			p3.SetPos(1, 13);
			p4.SetPos(4, 13);
			
			p1.SetStartGamePos(1, 10);
			p2.SetStartGamePos(4, 10);
			p3.SetStartGamePos(1, 13);
			p4.SetStartGamePos(4, 13);
			
			for(Peao p : pecas )
			{
				p.SetCasaIni(6, 13);
				p.SetStartDir(0);
				p.cor = 2;
			}
			
			inicioX = 6;
			inicioY = 13;
			
			fimX = 7;
			fimY = 8;
		}
		else if(cor == 3) { // YELLOW
			
			p1.SetPos(10, 10);
			p2.SetPos(13, 10);
			p3.SetPos(10, 13);
			p4.SetPos(13, 13);
			
			p1.SetStartGamePos(10, 10);
			p2.SetStartGamePos(13, 10);
			p3.SetStartGamePos(10, 13);
			p4.SetStartGamePos(13, 13);
			
			for(Peao p : pecas )
			{
				p.SetCasaIni(13, 8);
				p.SetStartDir(3);
				p.cor = 3;
			}
			
			inicioX = 13;
			inicioY = 8;
			
			fimX = 8;
			fimY = 7;
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
	
	public void AddPoints()
	{
		pontos++;
		if(pontos >= 4)
		{
			notifyObservers();
		}
	}	
	
	public boolean CheckBar(Barrier b) {
		for(int i = 0; i < 4; i++) 
		{
			if(pecas.get(i).GetPosX() == b.posX && pecas.get(i).GetPosY() == b.posY) 			
			{
				for(int j = 0; j < 4; j++)
				{
					if(pecas.get(j).GetPosX() == b.posX && pecas.get(j).GetPosY() == b.posY && i != j) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void register(Observer obj) 
	{
		if(obj == null) throw new NullPointerException("Null Observer");
		
		if(!observers.contains(obj)) observers.add(obj);
	}

	@Override
	public void unregister(Observer obj)
	{
		if(obj == null) throw new NullPointerException("Null Observer");
		
		if(!observers.contains(obj)) observers.remove(obj);
		
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for(Observer obs : observers) 
		{
			obs.update();
		}		
	}

	@Override
	public Object getUpdate(Observer obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
}