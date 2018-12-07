package Ludo;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Tabuleiro extends JPanel {
	
	private int Largura = 360;
	private int Altura = 360;
	private Casas _casas;
	Player play1 = new Player(1);
	Player play2 = new Player(2);
	Player play3 = new Player(3);
	Player play4 = new Player(4);
	
	ArrayList<Player> ListPlayers = new ArrayList<Player>();
	
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
				Peao clickP = ListPlayers.get(Menu.getInstance().turno).GetPeao(mouseX,mouseY);
				if(clickP != null)
				{
					Menu.getInstance().turno++;
					if(Menu.getInstance().turno >= 4)
						Menu.getInstance().turno = 0;
					Menu.getInstance().lanca_dado.setEnabled(true);
					System.out.println("PCLICK:  " + clickP.GetPosX() + "..." + clickP.GetPosX());
					System.out.println("Turno: " + Menu.getInstance().turno);
					//TESTING MOVE
					for(int i = 0;i < 4;i++)
					{
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
					}
					if(clickP.PrimeiroMov) clickP.PrimeiroMov = false;
					repaint();
				}
			}
		});
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
			
			if(value == -4) // TEST CHANGE DIR
			{
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(_posY, _posX, 60, 60); // TODO why is this inverted ?
				g.setColor(Color.BLACK);			
				g.drawRect(_posY, _posX, 60, 60); //TODO why is this inverted too ???
			}
			
			if(value == -3) // TEST CHANGE DIR
			{
				g.setColor(Color.MAGENTA);
				g.fillRect(_posY, _posX, 60, 60); // TODO why is this inverted ?
				g.setColor(Color.BLACK);			
				g.drawRect(_posY, _posX, 60, 60); //TODO why is this inverted too ???
			}
			
			if(value == -2) // TEST CHANGE DIR
			{
				g.setColor(Color.CYAN);
				g.fillRect(_posY, _posX, 60, 60); // TODO why is this inverted ?
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
		/*
		g.setColor(Color.RED);
		g.fillPolygon(RedTriX, RedTriY, 3); // Draw fill red Triag
		
		g.setColor(Color.BLUE);
		g.fillPolygon(BlueTriX, BlueTriY, 3); // Draw fill blue Triag
		
		g.setColor(Color.GREEN);
		g.fillPolygon(GreenTriX, GreenTriY, 3); // Draw fill green Triag
		
		g.setColor(Color.YELLOW);
		g.fillPolygon(YellowTriX, YellowTriY, 3); // Draw fill green Triag
		*/
		g.setColor(Color.BLACK);
		g.drawPolygon(RedTriX, RedTriY, 3);
		g.drawPolygon(BlueTriX, BlueTriY, 3);
		g.drawPolygon(GreenTriX, GreenTriY, 3);
		g.drawPolygon(YellowTriX, YellowTriY, 3);
		
		
		for(int i = 0;i < 4;i++) { // DRAW Players Pawns
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
		}
	}
}