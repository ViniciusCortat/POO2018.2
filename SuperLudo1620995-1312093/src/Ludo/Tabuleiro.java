package desenho_regras;
import java.awt.*;


import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import Ludo.Casas;
import Ludo.Peao;
import Ludo.Player;

public class Tabuleiro extends JPanel {
	
	private static Tabuleiro instance = null;
	
	public static  Tabuleiro getInstance() {
		if(instance == null) {
			instance = new Tabuleiro();
		}
		return instance;
	}
	
	private int Largura = 360;
	private int Altura = 360;
	private Casas _casas;
	
	Player play1 = new Player(0);
	Player play2 = new Player(1);
	Player play3 = new Player(2);
	Player play4 = new Player(3);
	
	public class Abrigo {
		int posX;
		int posY;
		boolean abr;
		
		public Abrigo(int x,int y) {
			posX = x;
			posY = y;
			abr = false;
		}
	}
	
	Abrigo abr1 = new Abrigo(1,8);
	Abrigo abr2 = new Abrigo(6,1);
	Abrigo abr3 = new Abrigo(8,13);
	Abrigo abr4 = new Abrigo(13,6);
	
	ArrayList<Player> ListPlayers = new ArrayList<Player>();
	ArrayList<Abrigo> abrigos = new ArrayList<Abrigo>();
	
	//frame final size 885x861
	//square size must be 60x60
	
	public Tabuleiro() {
		
		
		setLayout(null);
		repaint();
		setBounds(0, 0, 901,901);
		_casas =  new Casas(225);
		System.out.println(_casas.ToString());
		
		ListPlayers.add(play1);
		ListPlayers.add(play2);
		ListPlayers.add(play3);
		ListPlayers.add(play4);
		
		abrigos.add(abr1);
		abrigos.add(abr2);
		abrigos.add(abr3);
		abrigos.add(abr4);
		
		addMouseListener(new MouseAdapter() { // Mouse Coordinates on Click
			public void mousePressed(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY();
				if(mouseX < 60) {
					mouseX = 0;
				}
				else {
					mouseX = mouseX/60;
				}
				if(mouseY < 60) {
					mouseY = 0;
				}
				else {
					mouseY = mouseY/60;
				}
				System.out.println(mouseX + "," + mouseY);
				
				for(int k = 0; k < 4; k++) { // Check for barriers
					if(ListPlayers.get(k).CheckBar(ListPlayers.get(k).bar1) == false) {
						ListPlayers.get(k).bar1.bar = false;
					}
					else {
						ListPlayers.get(k).bar1.bar = true;
					}
					if(ListPlayers.get(k).CheckBar(ListPlayers.get(k).bar2) == false) {
						ListPlayers.get(k).bar2.bar = false;
					}
					else {
						ListPlayers.get(k).bar2.bar = true;
					}
				}
				
				for(int k = 0; k < 4; k++) { // Check for Abrigos
					if(CheckAbr(abrigos.get(k)) == true) {
						abrigos.get(k).abr = true;
					}
					else {
						abrigos.get(k).abr = false;
					}
				}
				
				// Movement Starts Here
				Peao clickP = ListPlayers.get(Menu.getInstance().turno).GetPeao(mouseX,mouseY);
				if(clickP != null && Menu.getInstance().wait == false)
				{
					if(clickP.GetPosX() != 7 && clickP.GetPosY() != 7)
					{
					Menu.getInstance().wait = true;
					Menu.getInstance().turno++;
					if(Menu.getInstance().turno >= 4)
						Menu.getInstance().turno = 0;
					Menu.getInstance().lanca_dado.setEnabled(true);
					System.out.println("PCLICK:  " + clickP.GetPosX() + "..." + clickP.GetPosX());
					System.out.println("Turno: " + Menu.getInstance().turno);
					//TESTING MOVE
					int ValorDoDado = Menu.getInstance().dado;
					
					if(clickP.PrimeiroMov && ValorDoDado == 5) 
						{
							clickP.Move(1);
							System.out.println("ESSE É O VALOR DO DADO" + Menu.getInstance().dado);
						}
					else if(!clickP.PrimeiroMov)
					{
						moveloop:
						for(int i = 0;i < ValorDoDado;i++)
						{
							if( i == 0 )
							{
								clickP.SetBackup(clickP.GetPosX(), clickP.GetPosY());
							}
							clickP.Move(1);
							if(_casas.GetMatrixValue(clickP.GetPosX(),clickP.GetPosY()) == -2)
							{
								clickP.ChangeDirClock();
							}
							else if(_casas.GetMatrixValue(clickP.GetPosX(),clickP.GetPosY()) == -3)
							{
								clickP.ChangeDirCouClock();
								i--;
							}
							else if(_casas.GetMatrixValue(clickP.GetPosX(),clickP.GetPosY()) == -4)
							{
								if(clickP.GetQtdWalked() > 51)
								{
									clickP.ChangeDirClock();
								}
							}
							else if(_casas.GetMatrixValue(clickP.GetPosX(),clickP.GetPosY()) == -5)
							{
								if(clickP.GetQtdWalked() > 51)
								{
									ListPlayers.get(Menu.getInstance().turno).AddPoints();
									clickP.SetPos(7, 7);									
								}
							}
							for(Abrigo b : abrigos)
							{
								if(b.posX == clickP.GetPosX() && b.posY == clickP.GetPosY())
								{
									if(b.abr)
									{
										clickP.ResetToBackup();
										break moveloop;
									}
								}
							}
							for(Player pl : ListPlayers)
							{
								if(pl.bar1.posX == clickP.GetPosX() && pl.bar1.posY == clickP.GetPosY())
								{
									if(pl.bar1.bar == true)
									{
										clickP.ResetToBackup();
										break moveloop;
									}
								}
								if(pl.bar2.posX == clickP.GetPosX() && pl.bar2.posY == clickP.GetPosY())
								{
									if(pl.bar2.bar == true)
									{
										clickP.ResetToBackup();
										break moveloop;
									}
								}
							}
						}
						
						for(Player pl : ListPlayers)
						{							
							if(pl.cor != clickP.cor)
							{								
								Peao anotherP = pl.GetPeao(clickP.GetPosX(), clickP.GetPosY());
								if(anotherP != null)
								{
									System.out.println(clickP.cor + "  CAPTUROU  " + anotherP.cor);
									anotherP.MoveToIni();
								}
							}
						}			
					}
					
					
				// Movement Ends Here
					Menu.getInstance().repaint();
					repaint();
				}
			}
			}
		});
		Menu.getInstance().repaint();
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	
		setBackground(Color.WHITE);
		g.setColor(Color.RED);
		g.fillRect(0, 0, Largura, Altura); // Draw red square
		int[] RedTriX = {360,360,450};
		int[] RedTriY = {360,540,450};
		
		g.setColor(Color.BLUE);		
		g.fillRect(0, 540, Largura, Altura); // Draw blue square
		int[] BlueTriX = {360,540,450};
		int[] BlueTriY = {540,540,450};
		
		g.setColor(Color.GREEN);
		g.fillRect(540, 0, Largura, Altura); // Draw green square
		int[] GreenTriX = {360,540,450};
		int[] GreenTriY = {360,360,450};
		
		g.setColor(Color.YELLOW);
		int[] YellowTriX = {540,540,450};
		int[] YellowTriY = {540,360,450};
		g.fillRect(540, 540, Largura, Altura );
		
		g.setColor(Color.BLACK);
		
        g.drawRect(0, 0, Largura, Altura);
		g.drawRect(0, 540, Largura, Altura);
		g.drawRect(540, 0, Largura, Altura);
		g.drawRect(540, 540, Largura, Altura );
		
		for(int i = 0;i < _casas.GetCount();i++)
		{
			int _row = i/_casas.GetDimension();
			int _col = i%_casas.GetDimension();	
			int value = _casas.GetMatrixValue(_row, _col);
			
			int _posX = _row*60;
			int _posY = _col*60;
			
			if(value == -5) // TEST CHANGE DIR
			{
				g.setColor(Color.ORANGE);
				g.fillRect(_posY, _posX, 60, 60); // TODO why is this inverted ?
				g.setColor(Color.BLACK);			
				g.drawRect(_posY, _posX, 60, 60); //TODO why is this inverted too ???
			}
			
			if(value == -4) // TEST CHANGE DIR
			{
				//g.setColor(Color.LIGHT_GRAY);
				//g.fillRect(_posY, _posX, 60, 60); // TODO why is this inverted ?
				g.setColor(Color.BLACK);			
				g.drawRect(_posY, _posX, 60, 60); //TODO why is this inverted too ???
			}
			
			if(value == -3) // TEST CHANGE DIR
			{
				//g.setColor(Color.MAGENTA);
				//g.fillRect(_posY, _posX, 60, 60); // TODO why is this inverted ?
				g.setColor(Color.BLACK);			
				g.drawRect(_posY, _posX, 60, 60); //TODO why is this inverted too ???
			}
			
			if(value == -2) // TEST CHANGE DIR
			{
				//g.setColor(Color.CYAN);
				//g.fillRect(_posY, _posX, 60, 60); // TODO why is this inverted ?
				g.setColor(Color.BLACK);			
				g.drawRect(_posY, _posX, 60, 60); //TODO why is this inverted too ???
			}
			
			if(value == 1)
			{
				g.setColor(Color.RED);
				g.fillRect(_posY, _posX, 60, 60); // TODO why is this inverted ?
				g.setColor(Color.BLACK);			
				g.drawRect(_posY, _posX, 60, 60); //TODO why is this inverted too ???
			}
			else if(value == 2)
			{
				g.setColor(Color.YELLOW);
				g.fillRect(_posY, _posX, 60, 60); // TODO why is this inverted ?
				g.setColor(Color.BLACK);			
				g.drawRect(_posY, _posX, 60, 60); //TODO why is this inverted too ???
			}
			else if(value == 3)
			{
				g.setColor(Color.GREEN);
				g.fillRect(_posY, _posX, 60, 60); // TODO why is this inverted ?
				g.setColor(Color.BLACK);			
				g.drawRect(_posY, _posX, 60, 60); //TODO why is this inverted too ???
			}
			else if(value == 4)
			{
				g.setColor(Color.BLUE);
				g.fillRect(_posY, _posX, 60, 60); // TODO why is this inverted ?
				g.setColor(Color.BLACK);			
				g.drawRect(_posY, _posX, 60, 60); //TODO why is this inverted too ???
			}
			else if(value == 5)
			{
				g.setColor(Color.BLACK);
				g.fillRect(_posY, _posX, 60, 60); // TODO why is this inverted ?
				g.setColor(Color.BLACK);			
				g.drawRect(_posY, _posX, 60, 60); //TODO why is this inverted too ???
			}
			else if(value == 6)
			{
				g.setColor(Color.WHITE);
				g.fillOval(_posY, _posX, 60, 60); // TODO why is this inverted ?
				g.setColor(Color.BLACK);			
				g.drawOval(_posY, _posX, 60, 60); //TODO why is this inverted too ???
			}
			else if(value == 0)
			{
				g.setColor(Color.BLACK);			
				g.drawRect(_posX, _posY, 60, 60);
			}
		}
		
		
		g.setColor(Color.RED);
		g.fillPolygon(RedTriX, RedTriY, 3); // Draw fill red Triag
		
		g.setColor(Color.BLUE);
		g.fillPolygon(BlueTriX, BlueTriY, 3); // Draw fill blue Triag
		
		g.setColor(Color.GREEN);
		g.fillPolygon(GreenTriX, GreenTriY, 3); // Draw fill green Triag
		
		g.setColor(Color.YELLOW);
		g.fillPolygon(YellowTriX, YellowTriY, 3); // Draw fill green Triag
		
		g.setColor(Color.BLACK);
		g.drawPolygon(RedTriX, RedTriY, 3);
		g.drawPolygon(BlueTriX, BlueTriY, 3);
		g.drawPolygon(GreenTriX, GreenTriY, 3);
		g.drawPolygon(YellowTriX, YellowTriY, 3);
		
		
		for(int i = 0;i < 4;i++) { // DRAW Players Pawns
			for(int j = 0;j < 4; j++) { // DRAW Barrier
				if(play1.pecas.get(i).GetPosX() == play1.pecas.get(j).GetPosX() &&
				   play1.pecas.get(i).GetPosY() == play1.pecas.get(j).GetPosY() && i != j) {
					for(int d = 0; d < 4; d++) {
						if(play1.pecas.get(i).GetPosX() == ListPlayers.get(d).inicioX &&
						   play1.pecas.get(i).GetPosY() == ListPlayers.get(d).inicioY) {
							break;
						}
						if(d == 3) {
							if(play1.bar1.bar == true) {
								play1.bar2.posX = play1.pecas.get(i).GetPosX();
								play1.bar2.posX = play1.pecas.get(i).GetPosY();
							}
							else {
								play1.bar1.posX = play1.pecas.get(i).GetPosX();
								play1.bar1.posX = play1.pecas.get(i).GetPosY();
							}
							
							g.setColor(Color.WHITE);
							g.fillOval(play1.pecas.get(i).GetPosX()*60+5, play1.pecas.get(i).GetPosY()*60+5, 50, 50);
							g.setColor(Color.RED);
							g.drawOval(play1.pecas.get(i).GetPosX()*60+5, play1.pecas.get(i).GetPosY()*60+5, 50, 50);
						}
					}
				}
			}
			
			for(int j = 0;j < 4; j++) { // DRAW Barrier
				if(play2.pecas.get(i).GetPosX() == play2.pecas.get(j).GetPosX() &&
				   play2.pecas.get(i).GetPosY() == play2.pecas.get(j).GetPosY() && i != j) {
					for(int d = 0; d < 4; d++) {
						if(play2.pecas.get(i).GetPosX() == ListPlayers.get(d).inicioX &&
						   play2.pecas.get(i).GetPosY() == ListPlayers.get(d).inicioY) {
							break;
						}
						if(d == 3) {
							if(play2.bar1.bar == true) {
								play2.bar2.posX = play2.pecas.get(i).GetPosX();
								play2.bar2.posX = play2.pecas.get(i).GetPosY();
							}
							else {
								play2.bar1.posX = play2.pecas.get(i).GetPosX();
								play2.bar1.posX = play2.pecas.get(i).GetPosY();
							}
							
							g.setColor(Color.WHITE);
							g.fillOval(play2.pecas.get(i).GetPosX()*60+5, play2.pecas.get(i).GetPosY()*60+12, 50, 50);
							g.setColor(Color.GREEN);
							g.drawOval(play2.pecas.get(i).GetPosX()*60+5, play2.pecas.get(i).GetPosY()*60+12, 50, 50);
						}
					}
				}
			}
			
			for(int j = 0;j < 4; j++) { // DRAW Barrier
				if(play3.pecas.get(i).GetPosX() == play3.pecas.get(j).GetPosX() &&
				   play3.pecas.get(i).GetPosY() == play3.pecas.get(j).GetPosY() && i != j) {
					for(int d = 0; d < 4; d++) {
						if(play3.pecas.get(i).GetPosX() == ListPlayers.get(d).inicioX &&
						   play3.pecas.get(i).GetPosY() == ListPlayers.get(d).inicioY) {
							break;
						}
						if(d == 3) {
							if(play3.bar1.bar == true) {
								play3.bar2.posX = play3.pecas.get(i).GetPosX();
								play3.bar2.posX = play3.pecas.get(i).GetPosY();
							}
							else {
								play3.bar1.posX = play3.pecas.get(i).GetPosX();
								play3.bar1.posX = play3.pecas.get(i).GetPosY();
							}
							
							g.setColor(Color.WHITE);
							g.fillOval(play3.pecas.get(i).GetPosX()*60+5, play3.pecas.get(i).GetPosY()*60+5, 50, 50);
							g.setColor(Color.BLUE);
							g.drawOval(play3.pecas.get(i).GetPosX()*60+5, play3.pecas.get(i).GetPosY()*60+5, 50, 50);
						}
					}
				}
			}
			
			for(int j = 0;j < 4; j++) { // DRAW Barrier
				if(play4.pecas.get(i).GetPosX() == play4.pecas.get(j).GetPosX() &&
				   play4.pecas.get(i).GetPosY() == play4.pecas.get(j).GetPosY() && i != j) {
					for(int d = 0; d < 4; d++) {
						if(play4.pecas.get(i).GetPosX() == ListPlayers.get(d).inicioX &&
						   play4.pecas.get(i).GetPosY() == ListPlayers.get(d).inicioY) {
							break;
						}
						if(d == 3) {
							if(play4.bar1.bar == true) {
								play4.bar2.posX = play4.pecas.get(i).GetPosX();
								play4.bar2.posX = play4.pecas.get(i).GetPosY();
							}
							else {
								play4.bar1.posX = play4.pecas.get(i).GetPosX();
								play4.bar1.posX = play4.pecas.get(i).GetPosY();
							}
							
							g.setColor(Color.WHITE);
							g.fillOval(play4.pecas.get(i).GetPosX()*60+5, play4.pecas.get(i).GetPosY()*60+5, 50, 50);
							g.setColor(Color.YELLOW);
							g.drawOval(play4.pecas.get(i).GetPosX()*60+5, play4.pecas.get(i).GetPosY()*60+5, 50, 50);
						}
					}
				}
			}
			
			g.setColor(Color.RED);
			g.fillOval(play1.pecas.get(i).GetPosX()*60+15, play1.pecas.get(i).GetPosY()*60+15, 30, 30); 
			g.setColor(Color.BLACK);			
			g.drawOval(play1.pecas.get(i).GetPosX()*60+15, play1.pecas.get(i).GetPosY()*60+15, 30, 30);
			
			g.setColor(Color.GREEN);
			g.fillOval(play2.pecas.get(i).GetPosX()*60+15, play2.pecas.get(i).GetPosY()*60+15, 30, 30); 
			g.setColor(Color.BLACK);			
			g.drawOval(play2.pecas.get(i).GetPosX()*60+15, play2.pecas.get(i).GetPosY()*60+15, 30, 30);
			
			g.setColor(Color.BLUE);
			g.fillOval(play3.pecas.get(i).GetPosX()*60+15, play3.pecas.get(i).GetPosY()*60+15, 30, 30); 
			g.setColor(Color.BLACK);			
			g.drawOval(play3.pecas.get(i).GetPosX()*60+15, play3.pecas.get(i).GetPosY()*60+15, 30, 30);
			
			g.setColor(Color.YELLOW);
			g.fillOval(play4.pecas.get(i).GetPosX()*60+15, play4.pecas.get(i).GetPosY()*60+15, 30, 30); 
			g.setColor(Color.BLACK);			
			g.drawOval(play4.pecas.get(i).GetPosX()*60+15, play4.pecas.get(i).GetPosY()*60+15, 30, 30);
			
			for(int j = 0;j < 4; j++) { //DRAW Abrigo play1 com play2
				if(play1.pecas.get(i).GetPosX() == play2.pecas.get(j).GetPosX() &&
				   play1.pecas.get(i).GetPosY() == play2.pecas.get(j).GetPosY()) {
					for(int d = 0;d < 4; d++) {
						if((play1.pecas.get(i).GetPosX() == ListPlayers.get(d).inicioX &&
						   play1.pecas.get(i).GetPosY() == ListPlayers.get(d).inicioY) ||
						   (play1.pecas.get(i).GetPosX() == abrigos.get(d).posX &&
						   play1.pecas.get(i).GetPosY() == abrigos.get(d).posY)) {
							
							g.setColor(Color.GREEN);
							g.fillOval(play2.pecas.get(i).GetPosX()*60+10, play2.pecas.get(i).GetPosY()*60+10, 40, 40); 
							g.setColor(Color.BLACK);			
							g.drawOval(play2.pecas.get(i).GetPosX()*60+10, play2.pecas.get(i).GetPosY()*60+10, 40, 40);
							
							g.setColor(Color.RED);
							g.fillOval(play1.pecas.get(i).GetPosX()*60+15, play1.pecas.get(i).GetPosY()*60+15, 30, 30); 
							g.setColor(Color.BLACK);			
							g.drawOval(play1.pecas.get(i).GetPosX()*60+15, play1.pecas.get(i).GetPosY()*60+15, 30, 30);
							
							if(play1.pecas.get(i).GetPosX() == abrigos.get(d).posX &&
							   play1.pecas.get(i).GetPosY() == abrigos.get(d).posY) {
								abrigos.get(d).abr = true;
							}
						}
					}
				}
			}
			for(int j = 0;j < 4; j++) { //DRAW Abrigo play1 com play3
				if(play1.pecas.get(i).GetPosX() == play3.pecas.get(j).GetPosX() &&
				   play1.pecas.get(i).GetPosY() == play3.pecas.get(j).GetPosY()) {
					for(int d = 0;d < 4; d++) {
						if((play1.pecas.get(i).GetPosX() == ListPlayers.get(d).inicioX &&
						   play1.pecas.get(i).GetPosY() == ListPlayers.get(d).inicioY) ||
						   (play1.pecas.get(i).GetPosX() == abrigos.get(d).posX &&
						   play1.pecas.get(i).GetPosY() == abrigos.get(d).posY)) {
							
							g.setColor(Color.BLUE);
							g.fillOval(play3.pecas.get(i).GetPosX()*60+10, play3.pecas.get(i).GetPosY()*60+10, 40, 40); 
							g.setColor(Color.BLACK);			
							g.drawOval(play3.pecas.get(i).GetPosX()*60+10, play3.pecas.get(i).GetPosY()*60+10, 40, 40);
							
							g.setColor(Color.RED);
							g.fillOval(play1.pecas.get(i).GetPosX()*60+15, play1.pecas.get(i).GetPosY()*60+15, 30, 30); 
							g.setColor(Color.BLACK);			
							g.drawOval(play1.pecas.get(i).GetPosX()*60+15, play1.pecas.get(i).GetPosY()*60+15, 30, 30);
							
							if(play1.pecas.get(i).GetPosX() == abrigos.get(d).posX &&
							   play1.pecas.get(i).GetPosY() == abrigos.get(d).posY) {
								abrigos.get(d).abr = true;
							}
						}
					}
				}
			}
			for(int j = 0;j < 4; j++) { //DRAW Abrigo play1 com play4
				if(play1.pecas.get(i).GetPosX() == play4.pecas.get(j).GetPosX() &&
				   play1.pecas.get(i).GetPosY() == play4.pecas.get(j).GetPosY()) {
					for(int d = 0;d < 4; d++) {
						if((play1.pecas.get(i).GetPosX() == ListPlayers.get(d).inicioX &&
						   play1.pecas.get(i).GetPosY() == ListPlayers.get(d).inicioY) ||
						   (play1.pecas.get(i).GetPosX() == abrigos.get(d).posX &&
						   play1.pecas.get(i).GetPosY() == abrigos.get(d).posY)) {
							
							g.setColor(Color.YELLOW);
							g.fillOval(play4.pecas.get(i).GetPosX()*60+10, play4.pecas.get(i).GetPosY()*60+10, 40, 40); 
							g.setColor(Color.BLACK);			
							g.drawOval(play4.pecas.get(i).GetPosX()*60+10, play4.pecas.get(i).GetPosY()*60+10, 40, 40);
							
							g.setColor(Color.RED);
							g.fillOval(play1.pecas.get(i).GetPosX()*60+15, play1.pecas.get(i).GetPosY()*60+15, 30, 30); 
							g.setColor(Color.BLACK);			
							g.drawOval(play1.pecas.get(i).GetPosX()*60+15, play1.pecas.get(i).GetPosY()*60+15, 30, 30);
							
							if(play1.pecas.get(i).GetPosX() == abrigos.get(d).posX &&
							   play1.pecas.get(i).GetPosY() == abrigos.get(d).posY) {
								abrigos.get(d).abr = true;
							}
						}
					}
				}
			}
			for(int j = 0;j < 4; j++) { //DRAW Abrigo play2 com play3
				if(play2.pecas.get(i).GetPosX() == play3.pecas.get(j).GetPosX() &&
				   play2.pecas.get(i).GetPosY() == play3.pecas.get(j).GetPosY()) {
					for(int d = 0;d < 4; d++) {
						if((play2.pecas.get(i).GetPosX() == ListPlayers.get(d).inicioX &&
						   play2.pecas.get(i).GetPosY() == ListPlayers.get(d).inicioY) ||
						   (play2.pecas.get(i).GetPosX() == abrigos.get(d).posX &&
						   play2.pecas.get(i).GetPosY() == abrigos.get(d).posY)) {
							
							g.setColor(Color.GREEN);
							g.fillOval(play3.pecas.get(i).GetPosX()*60+10, play3.pecas.get(i).GetPosY()*60+10, 40, 40); 
							g.setColor(Color.BLACK);			
							g.drawOval(play3.pecas.get(i).GetPosX()*60+10, play3.pecas.get(i).GetPosY()*60+10, 40, 40);
							
							g.setColor(Color.BLUE);
							g.fillOval(play2.pecas.get(i).GetPosX()*60+15, play2.pecas.get(i).GetPosY()*60+15, 30, 30); 
							g.setColor(Color.BLACK);			
							g.drawOval(play2.pecas.get(i).GetPosX()*60+15, play2.pecas.get(i).GetPosY()*60+15, 30, 30);
							
							if(play2.pecas.get(i).GetPosX() == abrigos.get(d).posX &&
							   play2.pecas.get(i).GetPosY() == abrigos.get(d).posY) {
								abrigos.get(d).abr = true;
							}
						}
					}
				}
			}
			for(int j = 0;j < 4; j++) { //DRAW Abrigo play2 com play4
				if(play2.pecas.get(i).GetPosX() == play4.pecas.get(j).GetPosX() &&
				   play2.pecas.get(i).GetPosY() == play4.pecas.get(j).GetPosY()) {
					for(int d = 0;d < 4; d++) {
						if((play2.pecas.get(i).GetPosX() == ListPlayers.get(d).inicioX &&
						   play2.pecas.get(i).GetPosY() == ListPlayers.get(d).inicioY) ||
						   (play2.pecas.get(i).GetPosX() == abrigos.get(d).posX &&
						   play2.pecas.get(i).GetPosY() == abrigos.get(d).posY)) {
							
							g.setColor(Color.GREEN);
							g.fillOval(play4.pecas.get(i).GetPosX()*60+10, play4.pecas.get(i).GetPosY()*60+10, 40, 40); 
							g.setColor(Color.BLACK);			
							g.drawOval(play4.pecas.get(i).GetPosX()*60+10, play4.pecas.get(i).GetPosY()*60+10, 40, 40);
							
							g.setColor(Color.YELLOW);
							g.fillOval(play2.pecas.get(i).GetPosX()*60+15, play2.pecas.get(i).GetPosY()*60+15, 30, 30); 
							g.setColor(Color.BLACK);			
							g.drawOval(play2.pecas.get(i).GetPosX()*60+15, play2.pecas.get(i).GetPosY()*60+15, 30, 30);
							
							if(play2.pecas.get(i).GetPosX() == abrigos.get(d).posX &&
							   play2.pecas.get(i).GetPosY() == abrigos.get(d).posY) {
								abrigos.get(d).abr = true;
							}
						}
					}
				}
			}
			for(int j = 0;j < 4; j++) { //DRAW Abrigo play3 com play4
				if(play3.pecas.get(i).GetPosX() == play4.pecas.get(j).GetPosX() &&
				   play3.pecas.get(i).GetPosY() == play4.pecas.get(j).GetPosY()) {
					for(int d = 0;d < 4; d++) {
						if((play3.pecas.get(i).GetPosX() == ListPlayers.get(d).inicioX &&
						   play3.pecas.get(i).GetPosY() == ListPlayers.get(d).inicioY) ||
						   (play3.pecas.get(i).GetPosX() == abrigos.get(d).posX &&
						   play3.pecas.get(i).GetPosY() == abrigos.get(d).posY)) {
							
							g.setColor(Color.BLUE);
							g.fillOval(play4.pecas.get(i).GetPosX()*60+10, play4.pecas.get(i).GetPosY()*60+10, 40, 40); 
							g.setColor(Color.BLACK);			
							g.drawOval(play4.pecas.get(i).GetPosX()*60+10, play4.pecas.get(i).GetPosY()*60+10, 40, 40);
							
							g.setColor(Color.YELLOW);
							g.fillOval(play3.pecas.get(i).GetPosX()*60+15, play3.pecas.get(i).GetPosY()*60+15, 30, 30); 
							g.setColor(Color.BLACK);			
							g.drawOval(play3.pecas.get(i).GetPosX()*60+15, play3.pecas.get(i).GetPosY()*60+15, 30, 30);
							
							if(play3.pecas.get(i).GetPosX() == abrigos.get(d).posX &&
							   play3.pecas.get(i).GetPosY() == abrigos.get(d).posY) {
								abrigos.get(d).abr = true;
							}
						}
					}
				}
			}
		}
	}
	public boolean CheckAbr(Abrigo a) {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(ListPlayers.get(i).pecas.get(j).GetPosX() == a.posX &&
				   ListPlayers.get(i).pecas.get(j).GetPosY() == a.posY) {
					return true;
				}
			}
		}
		return false;
	}
}