import java.awt.*;
import javax.swing.*;

public class Janela extends JFrame {
	private static final int LARGURA = 1200;
	private static final int ALTURA = 900;
	
	public Janela() {
		setTitle("Super Ludo");
		getContentPane().setLayout(new BorderLayout());
		setPreferredSize(new Dimension(LARGURA,ALTURA));
		setMaximumSize(new Dimension(LARGURA,ALTURA));
		setMinimumSize(new Dimension(LARGURA,ALTURA));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
