
package Ludo;

public class Main {
	
	public static void main(String[] args) {
		Janela j = new Janela();
		Menu m = new Menu();
		Tabuleiro t = new Tabuleiro();
		j.add(m);
		j.add(t);
	}
}
