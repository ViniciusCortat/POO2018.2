package Ludo;

import java.util.ArrayList;

public class Peao {
	private int posX;
	private int posY;
	
	public boolean PrimeiroMov;
	private int CurrentDir;
	
	private ArrayList<Direction> Dirs = new ArrayList<Direction>(4);
	private int qtdWalked;
	
	int casaIniX;
	int casaIniY;
	
	public Peao(int x,int y,int casaIniX,int casaIniY) 
	{
		PrimeiroMov = true;
		SetPos(x,y);
		SetCasaIni(casaIniX,casaIniY);
	}
	
	public Peao() 
	{
		PrimeiroMov = true;	
		Dirs.add(new Direction(0,-1));
		Dirs.add(new Direction(1,0));
		Dirs.add(new Direction(0,1));
		Dirs.add(new Direction(-1,0));
	}
	
	public int GetQtdWalked()
	{
		return qtdWalked;
	}
	
	public int GetPosX()
	{
		return posX;
	}
	
	public int GetPosY()
	{
		return posY;
	}
	
	public void SetPosX(int x)
	{
		if(x < 14 && x > 0)
		{
			this.posX = x;
		}
	}
	
	public void SetPosY(int y)
	{
		if(y < 14 && y > 0)
		{
			this.posY = y;
		}
	}
	
	public void SetPos(int x,int y)
	{
		this.posX = x;
		this.posY = y;
	}
	
	public void SetCasaIni(int x, int y)
	{
		this.casaIniX = x;
		this.casaIniY = y;
	}
	
	public void SetStartDir(int dir)
	{
		this.CurrentDir = dir;
	}
	
	public void ChangeDirClock()
	{
		this.CurrentDir++;
		if(this.CurrentDir > 3)
		{
			this.CurrentDir = 0;
		}
	}
	public void ChangeDirCouClock()
	{
		this.CurrentDir--;
		if(this.CurrentDir < 0)
		{
			this.CurrentDir = 3;
		}
	}
		
		
	public class Direction
	{
		public int dirX;
		public int dirY;
		
		public Direction(int dirX,int dirY)
		{
			this.dirX = dirX;
			this.dirY = dirY;
		}
		public int GetDirX()
		{
			return dirX;
		}
		public int GetDirY()
		{
			return dirY;
		}
	}
	
	public void Move(int moveQtd) 
	{
		System.out.println("PM" + PrimeiroMov);
		if(PrimeiroMov)
		{
			System.out.println("CINI" + this.casaIniX + "," + this.casaIniY);
			SetPos(this.casaIniX,this.casaIniY);
		}
		else
		{
			SetPos(GetPosX() + (moveQtd * Dirs.get(CurrentDir).GetDirX()),GetPosY() + moveQtd * Dirs.get(CurrentDir).GetDirY());
			qtdWalked += moveQtd;
			System.out.println("CINI" + this.GetPosX() + "," + this.GetPosY());
		}
	}
}