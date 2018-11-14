package Ludo;
import java.awt.*;

import javax.swing.*;

public class Tabuleiro extends JPanel {
	
	private int Largura = 360;
	private int Altura = 360;
	private Casas _casas;
	
	//frame final size 885x861
	//square size must be 60x60
	
	public Tabuleiro() {
		setLayout(null);
		repaint();
		_casas =  new Casas(225);
		System.out.println(_casas.ToString());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBounds(0, 0, 901,901);	
		setBackground(Color.WHITE);
		
		for(int i = 0;i < _casas.GetCount();i++)
		{
			int _row = i/_casas.GetDimension();
			int _col = i%_casas.GetDimension();	
			int value = _casas.GetMatrixValue(_row, _col);
			
			int _posX = _row*60;
			int _posY = _col*60;
			
			
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
			else if(value == 0)
			{
				g.setColor(Color.BLACK);			
				g.drawRect(_posX, _posY, 60, 60);
			}
		}
		g.setColor(Color.RED);
		g.fillRect(0, 0, Largura, Altura); // Draw red square
		int[] RedTriX = {360,360,450};
		int[] RedTriY = {360,540,450};
		g.fillPolygon(RedTriX, RedTriY, 3); // Draw fill red Triag
		
		g.setColor(Color.BLUE);		
		g.fillRect(0, 540, Largura, Altura); // Draw blue square
		int[] BlueTriX = {360,540,450};
		int[] BlueTriY = {540,540,450};
		g.fillPolygon(BlueTriX, BlueTriY, 3); // Draw fill blue Triag
		
		g.setColor(Color.GREEN);
		g.fillRect(540, 0, Largura, Altura); // Draw green square
		int[] GreenTriX = {360,540,450};
		int[] GreenTriY = {360,360,450};
		g.fillPolygon(GreenTriX, GreenTriY, 3); // Draw fill green Triag
		
		g.setColor(Color.YELLOW);
		int[] YellowTriX = {540,540,450};
		int[] YellowTriY = {540,360,450};
		g.fillPolygon(YellowTriX, YellowTriY, 3); // Draw fill green Triag
		g.fillRect(540, 540, Largura, Altura );
		
		g.setColor(Color.BLACK);
		
		g.drawPolygon(RedTriX, RedTriY, 3);
		g.drawPolygon(BlueTriX, BlueTriY, 3);
		g.drawPolygon(GreenTriX, GreenTriY, 3);
		g.drawPolygon(YellowTriX, YellowTriY, 3);
		
        g.drawRect(0, 0, Largura, Altura);
		g.drawRect(0, 540, Largura, Altura);
		g.drawRect(540, 0, Largura, Altura);
		g.drawRect(540, 540, Largura, Altura );
	}
}
