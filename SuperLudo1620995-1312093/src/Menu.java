import javax.swing.*;


public class Menu {
	JButton novo_jogo = new JButton("Novo Jogo");
	JButton carrega_jogo = new JButton("Carregar Jogo");
	JButton salva_jogo = new JButton("Salvar");
	JButton lanca_dado = new JButton("Lançar Dado");
	
	public Menu(Janela j) {
		novo_jogo.setBounds(500, 500, 500, 500);
		j.menu.add(novo_jogo);
		j.menu.add(carrega_jogo);
		j.menu.add(salva_jogo);
		j.menu.add(lanca_dado);
	}
		
	
}
