package miniGame;

import javax.swing.JFrame;

public class GameRunner {
	public static final int FRAMERATE = 60; // Frames per Second
	public static final int WIDTH = 550;
	public static final int HEIGHT = WIDTH*2/3;
	public static boolean running = true;

	public static void main(String[] args) throws InterruptedException {

		JFrame application = new JFrame();
		GameDisplay panel = new GameDisplay();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.addKeyListener(panel);

		application.setTitle("FirstGame");
		application.setSize(WIDTH*2, HEIGHT);
		application.setVisible(true);
		application.setFocusable(true);
		// application.setFocusTraversalKeysEnabled(false);

		while (running) {
			panel.repaint();
			Thread.sleep(1000 / FRAMERATE);
		}
	}

}
