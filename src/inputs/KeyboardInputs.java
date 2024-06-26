package inputs;

import java.awt.event.KeyEvent;
import entities.Player;
import java.awt.event.KeyListener;

import main.Game;
import main.GamePanel;

import static utilz.Constants.Directions.*;
public class KeyboardInputs implements KeyListener {

	private GamePanel gamePanel;

	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			gamePanel.getGame().getPlayer().setUp(false);
			break;
		case KeyEvent.VK_A:
			gamePanel.getGame().getPlayer().setLeft(false);
			break;
		case KeyEvent.VK_S:
			gamePanel.getGame().getPlayer().setDown(false);
			break;
		case KeyEvent.VK_D:
			gamePanel.getGame().getPlayer().setRight(false);
			break;
		}

	}

	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			gamePanel.getGame().getPlayer().setUp(true);
			break;
		case KeyEvent.VK_A:
			gamePanel.getGame().getPlayer().setLeft(true);
			break;
		case KeyEvent.VK_S:
			gamePanel.getGame().getPlayer().setDown(true);
			break;
		case KeyEvent.VK_D:
			gamePanel.getGame().getPlayer().setRight(true);
			break;
		}

	}

}