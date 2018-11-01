import java.awt.*;
import javax.swing.*;

public class Janela {
	JFrame janela = new JFrame("Super Ludo");
	JPanel tabuleiro = new JPanel();
	JPanel menu = new JPanel();
	
	public Janela() {
		janela.getContentPane().setLayout(new BorderLayout());
		tabuleiro.setBackground(Color.WHITE);
		menu.setBackground(Color.DARK_GRAY);
		
		janela.add(tabuleiro,BorderLayout.CENTER);
		janela.add(menu,BorderLayout.EAST);
		
		janela.setSize(800, 800);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);
	}
}
