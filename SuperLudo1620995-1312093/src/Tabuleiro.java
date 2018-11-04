import java.awt.*;

import javax.swing.*;

public class Tabuleiro extends JPanel {
	
	private int Largura = 360;
	private int Altura = 340;
	
	//frame final size 885x861
	//square size must be 60x60
	
	public Tabuleiro() {
		setLayout(null);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBounds(0, 0, 885,861);
		setBackground(Color.WHITE);
		g.setColor(Color.RED);
		g.fillRect(0, 0, Largura, Altura);
		g.setColor(Color.BLUE);
		g.fillRect(0, 520, Largura, Altura + 1);
		g.setColor(Color.GREEN);
		g.fillRect(540, 0, Largura, Altura);
		g.setColor(Color.YELLOW);
		g.fillRect(540, 520, Largura, Altura + 1);	
	}
}
