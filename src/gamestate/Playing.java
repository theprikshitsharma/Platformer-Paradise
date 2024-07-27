package gamestate;

import static utilz.Constants.PlayerConstants.ATTACK_1;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import levels.LevelManager;
import main.Game;

public class Playing extends State implements Statemethods{
    private Player player;
    private LevelManager levelManager;
    public Playing(Game game) {
		super(game);
		initClasses();
	}

	private void initClasses() {
    	levelManager = new LevelManager(game);
		player = new Player(200, 200, (int) (32 * Game.SCALE), (int) (32 * Game.SCALE));
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());

	}


	public void update() {
		levelManager.update();
		player.update();
		
	}

	public void draw(Graphics g) {
		levelManager.draw(g);
		player.render(g);
		
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			player.setAttack(true);
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		}
		
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		case KeyEvent.VK_BACK_SPACE:
			System.out.println("Backspace key pressed");
			Gamestate.state = Gamestate.MENU;
			break;
		}
	}
	   
    public void windowFocusLost () {
    	player.resetDirBooleans();
    }

    public Player getPlayer() {
    	return player;
    }

}
