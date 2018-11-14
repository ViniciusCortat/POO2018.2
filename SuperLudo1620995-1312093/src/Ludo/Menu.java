package Ludo;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.IOException;
import java.util.*;


public class Menu extends JPanel {
	JButton novo_jogo = new JButton("Novo Jogo");
	JButton carrega_jogo = new JButton("Carregar Jogo");
	JButton salva_jogo = new JButton("Salvar");
	JButton lanca_dado = new JButton("Lançar Dado");
	
	private BufferedImage _dadoImagem;
	
	public Menu() {
		setLayout(null);
		repaint();
		setBounds(901, 0, 293, 902);
		novo_jogo.setBounds(30, 60, 240, 60);
		add(novo_jogo);
		carrega_jogo.setBounds(30, 150, 240, 60);
		add(carrega_jogo);
		salva_jogo.setBounds(30, 240, 240, 60);
		add(salva_jogo);
		lanca_dado.setBounds(30, 570, 240, 60);
		add(lanca_dado);
		lanca_dado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				int dado = rand.nextInt(6) + 1;
				System.out.println(dado);
				if(dado == 1) {
					try {
						_dadoImagem = ImageIO.read(getClass().getResourceAsStream("/Dado1.png"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if(dado == 2) {
					try {
						_dadoImagem = ImageIO.read(getClass().getResourceAsStream("/Dado2.png"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if(dado == 3) {
					try {
						_dadoImagem = ImageIO.read(getClass().getResourceAsStream("/Dado3.png"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if(dado == 4) {
					try {
						_dadoImagem = ImageIO.read(getClass().getResourceAsStream("/Dado4.png"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if(dado == 5) {
					try {
						_dadoImagem = ImageIO.read(getClass().getResourceAsStream("/Dado5.png"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if(dado == 6) {
					try {
						_dadoImagem = ImageIO.read(getClass().getResourceAsStream("/Dado6.png"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				repaint();
			}
		});
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.GRAY);
		g.drawImage(_dadoImagem, 78, 683, _dadoImagem.getWidth()*3/2, _dadoImagem.getHeight()*3/2, null);
	}
	
}
