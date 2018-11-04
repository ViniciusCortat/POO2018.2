import javax.swing.*;
import java.awt.*;


public class Menu extends JPanel {
	JButton novo_jogo = new JButton("Novo Jogo");
	JButton carrega_jogo = new JButton("Carregar Jogo");
	JButton salva_jogo = new JButton("Salvar");
	JButton lanca_dado = new JButton("Lançar Dado");
	
	public Menu() {
		setLayout(null);
		repaint();
		novo_jogo.setBounds(30, 60, 240, 60);
		add(novo_jogo);
		carrega_jogo.setBounds(30, 150, 240, 60);
		add(carrega_jogo);
		salva_jogo.setBounds(30, 240, 240, 60);
		add(salva_jogo);
		lanca_dado.setBounds(30, 570, 240, 60);
		add(lanca_dado);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBounds(885, 0, 299, 861);
		setBackground(Color.GRAY);
	}
}
