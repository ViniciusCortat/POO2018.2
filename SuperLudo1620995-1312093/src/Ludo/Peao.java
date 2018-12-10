package Ludo;

import java.util.ArrayList;

public class Peao {
	private int posX;
	private int posY;
	
	private int StartGamePosX;
	private int StartGamePosY;
	
	public boolean PrimeiroMov;
	
	private int StartDir;
	private int CurrentDir;
	
	private ArrayList<Direction> Dirs = new ArrayList<Direction>(4);
	private int qtdWalked;
	
	public int casaIniX;
	public int casaIniY;
	
	private int MovBackupX;
	private int MovBackupY;
	
	public int cor;
	
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
	
	public void SetBackup(int x, int y)
	{
		this.MovBackupX = x;
		this.MovBackupY = y;
	}
	
	public void ResetToBackup()
	{
		System.out.println("BACKUP");
		this.SetPos(MovBackupX, MovBackupY);
	}
	
	public void SetCasaIni(int x, int y)
	{
		this.casaIniX = x;
		this.casaIniY = y;
	}
	
	public void SetStartDir(int dir)
	{
		this.StartDir = dir;
		SetDir(this.StartDir);
	}
	
	public void SetDir(int dir)
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
	
	public void SetStartGamePos(int x, int y) {
		this.StartGamePosX = x;
		this.StartGamePosY = y;
	}
	
	public int GetStartGamePosX() {
		return this.StartGamePosX;
	}
	
	public int GetStartGamePosY() {
		return this.StartGamePosY;
	}
	public int GetCurrentDir() {
		return this.CurrentDir;
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
		
		if(PrimeiroMov)
		{			
			System.out.println("CINI" + this.casaIniX + "," + this.casaIniY);
			SetPos(this.casaIniX,this.casaIniY);
			this.PrimeiroMov = false;
		}
		else
		{
			System.out.println("\n\nMOV NORMAL");
			SetPos(GetPosX() + (moveQtd * Dirs.get(CurrentDir).GetDirX()),GetPosY() + moveQtd * Dirs.get(CurrentDir).GetDirY());
			qtdWalked += moveQtd;
			System.out.println("DIRECT "+ CurrentDir + ">>>" + Dirs.get(CurrentDir).GetDirX() + "::" + Dirs.get(CurrentDir).GetDirY());
		}
	}
	public void MoveToIni() 
	{
			System.out.println("BACK TO START" + this.casaIniX + "," + this.casaIniY);
			SetPos(this.casaIniX,this.casaIniY);
			SetDir(this.StartDir);
			this.PrimeiroMov = false;
	}
}