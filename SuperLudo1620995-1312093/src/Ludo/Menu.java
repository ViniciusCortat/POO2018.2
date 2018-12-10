package desenho_regras;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import controle.*;
import controle.Observer;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import java.util.*;


public class Menu extends JPanel implements Observer {
	
	 private static Menu instance = null;
	
	JButton novo_jogo = new JButton("Novo Jogo");
	JButton carrega_jogo = new JButton("Carregar Jogo");
	JButton salva_jogo = new JButton("Salvar");
	JButton lanca_dado = new JButton("Lançar Dado");
	
	int dado;
	
	public boolean wait;
	
	int turno = 0;
	
	private BufferedImage _dadoImagem;
	
	public static  Menu getInstance() {
		if(instance == null) {
			instance = new Menu();
		}
		return instance;
	}
	
	public Menu() {
		wait = true;
		_dadoImagem = null;
		setLayout(null);
		repaint();
		setBounds(901, 0, 293, 902);
		novo_jogo.setBounds(30, 60, 240, 60);
		add(novo_jogo);
		novo_jogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turno = 0;
				for(int i = 0; i < 4; i++) {
					for(int j = 0; j < 4;j++) {
						Tabuleiro.getInstance().ListPlayers.get(i).pecas.get(j).SetPos(
								Tabuleiro.getInstance().ListPlayers.get(i).pecas.get(j).GetStartGamePosX()
								, Tabuleiro.getInstance().ListPlayers.get(i).pecas.get(j).GetStartGamePosY());
					}
				}
				wait = true;
				lanca_dado.setEnabled(true);
				repaint();
				Tabuleiro.getInstance().repaint();
				System.out.println("New GAME");
			}
			});
		carrega_jogo.setBounds(30, 150, 240, 60);
		add(carrega_jogo);
		carrega_jogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				String FileName = null;
                if(cmd.equals("Carregar Jogo")) {
                    JFileChooser chooser =new JFileChooser();
                    FileFilter filefilter = new FileNameExtensionFilter("","txt");
                    chooser.setFileFilter(filefilter);
                    int returnVal = chooser.showOpenDialog(chooser);
                    if(returnVal==JFileChooser.APPROVE_OPTION) {
                        FileName=chooser.getSelectedFile().getName();
                        System.out.println(FileName);
                    }
                    try {
						Scanner scan = new Scanner(new File(FileName));
						for(int i = 0; i < 4; i++) {
							for(int j =0; j < 4; j++) {
								String sx = scan.next();
								String sy = scan.next();
								Tabuleiro.getInstance().ListPlayers.get(i).pecas.get(j).SetPos(
										Integer.parseInt(sx), Integer.parseInt(sy));
							}
						}
						if(scan.next().equals("true"))
							wait = true;
						else
							wait = false;
						if(scan.next().equals("true"))
							lanca_dado.setEnabled(true);
						else
							lanca_dado.setEnabled(false);
						turno = Integer.parseInt(scan.next());
						for(int i = 0; i < 4; i++) {
							for(int j =0; j < 4; j++) {
								String s = scan.next();
								if(s.equals("true")) {
									Tabuleiro.getInstance().ListPlayers.get(i).pecas.get(j).PrimeiroMov = true;
									System.out.println("True\n");
								}
								else {
									Tabuleiro.getInstance().ListPlayers.get(i).pecas.get(j).PrimeiroMov = false;
									System.out.println(s+"false\n");
								}
							}
						}
						for(int i = 0;i < 4; i ++) {
							for(int j = 0; j < 4; j++) {
								Tabuleiro.getInstance().ListPlayers.get(i).pecas.get(j).SetDir(Integer.parseInt(scan.next()));
							}
						}
						dado = Integer.parseInt(scan.next());
						Tabuleiro.getInstance().repaint();
						repaint();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
                }
			}
		});
		salva_jogo.setBounds(30, 240, 240, 60);
		add(salva_jogo);
		salva_jogo.addActionListener(e-> save());
		lanca_dado.setBounds(30, 570, 240, 60);
		add(lanca_dado);
		lanca_dado.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				dado = rand.nextInt(6) + 1;
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
				wait = false;
				lanca_dado.setEnabled(false);
				if(dado == 5) {
					for(int j = 0; j < 4; j++) {
						if(Tabuleiro.getInstance().ListPlayers.get(turno).pecas.get(j).PrimeiroMov == true) {
							Tabuleiro.getInstance().ListPlayers.get(turno).pecas.get(j).SetPos
							(Tabuleiro.getInstance().ListPlayers.get(turno).pecas.get(j).casaIniX,
							 Tabuleiro.getInstance().ListPlayers.get(turno).pecas.get(j).casaIniY);
							Tabuleiro.getInstance().ListPlayers.get(turno).pecas.get(j).PrimeiroMov = false;
							wait = true;
							turno++;
							if(turno >= 4)
								turno = 0;
							lanca_dado.setEnabled(true);
							Tabuleiro.getInstance().repaint();
							break;
						}
					}
				
				}
				else {
					for(int j = 0; j < 4; j++) {
						if(Tabuleiro.getInstance().ListPlayers.get(turno).pecas.get(j).PrimeiroMov == false) {
							break;
						}
						if(j == 3) {
							wait = true;
							turno++;
							if(turno >= 4)
							turno = 0;
							lanca_dado.setEnabled(true);
							Tabuleiro.getInstance().repaint();
						}
					}
				}
				repaint();
			}
		});
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.GRAY);
		if(turno == 0) {
			g.setColor(Color.RED);
		}
		else if(turno == 1) {
			g.setColor(Color.GREEN);
		}
		else if(turno == 2) {
			g.setColor(Color.BLUE);
		}
		else if(turno == 3) {
			g.setColor(Color.YELLOW);
		}
		if(_dadoImagem != null) {
			g.fillRect(68, 673, _dadoImagem.getWidth()*3/2 + 20, _dadoImagem.getHeight()*3/2 + 20);
			g.drawImage(_dadoImagem, 78, 683, _dadoImagem.getWidth()*3/2, _dadoImagem.getHeight()*3/2, null);
		}
	}
	
	private void save() {
		JFileChooser file = new JFileChooser();
		int retrival = file.showSaveDialog(null);
		if(retrival == JFileChooser.APPROVE_OPTION) {
			try(FileWriter fw = new FileWriter(file.getSelectedFile()+".txt")){
				for(int i = 0;i < 4; i ++) {
					for(int j = 0; j < 4; j++) {
						fw.write(Tabuleiro.getInstance().ListPlayers.get(i).pecas.get(j).GetPosX()+ " " +
					             Tabuleiro.getInstance().ListPlayers.get(i).pecas.get(j).GetPosY()+ " ");
					}
				}
				if(wait == true)
					fw.write("true ");
				else
					fw.write("false ");
				if(lanca_dado.isEnabled() == true)
					fw.write("true ");
				else
					fw.write("false ");
				fw.write(turno+ " ");
				for(int i = 0;i < 4; i ++) {
					for(int j = 0; j < 4; j++) {
						if(Tabuleiro.getInstance().ListPlayers.get(i).pecas.get(j).PrimeiroMov == true) {
							fw.write("true ");
						}
						else {
							fw.write("false ");
						}
					}
				}
				for(int i = 0;i < 4; i ++) {
					for(int j = 0; j < 4; j++) {
						fw.write(Tabuleiro.getInstance().ListPlayers.get(i).pecas.get(j).GetCurrentDir()+ " ");
					}
				}
				fw.write(dado);
				
			} catch(Exception ex ) {
				ex.printStackTrace();
			}
		}
	}
	
	@Override
	public void update() 
	{
		System.out.println("SOMEONE WON");
		
	}

	@Override
	public void setSubject(Subject sub) {
		// TODO Auto-generated method stub
		
	}
}