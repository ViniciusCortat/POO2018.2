package Ludo;

public class Main {
	
	public static void main(String[] args) {
		Janela j = new Janela();
		Menu m = Menu.getInstance();
		Tabuleiro t = new Tabuleiro();
		j.add(m);
		j.add(t);
	}
}