package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class Player extends Entity{
	
	private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15; // Variables to control animation speed and state
    private int playerAction = IDLE;
    private boolean moving = false , attacking = false; // Flag to check if the player is moving
    private boolean left , up , right , down, jump;
    private float playerSpeed = 2.0f;
    private int[][] lvlData;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;
    
    // jumping and gravity
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;
    
    
    
    
	public Player(float x, float y, int width , int height) {
		super(x, y, width , height);
		loadAnimations();
		initHitbox(x , y ,20 * Game.SCALE, 27 * Game.SCALE);
		
	}
	
	public void update() {
		updatePos(); // Update the player's position
		updateAnimationTick(); // Update the animation tick
        setAnimation(); // Set the current animation based on player state
	
	}

	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
		drawHitbox(g); // Used for testing purposes 
	}
	
    private void updateAnimationTick() { // Increment the animation tick counter and update animation frame
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) { // If the frame index exceeds the number of frames, reset it
            	aniIndex = 0;
            	attacking = false;
            	
            	
            }
        }
    }

    private void setAnimation() { // Update the player's action based on the moving status
        
    	int startAni = playerAction;
    	if (moving) 
            playerAction = RUNNING;
        else 
            playerAction = IDLE;
    	
    	if(inAir) {
    		if(airSpeed < 0)
    			playerAction = JUMP;
    		else
    			playerAction = FALLING;
    	}
        
        if (attacking)
        	playerAction = ATTACK_1;
        
        if (startAni != playerAction)
        	resetAniTick();
    }
    
    private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {
		moving = false;
		
		if(jump)
			jump();
    	if (!left && !right && !inAir)
    		return;
    	
    	float xSpeed = 0;
    	if(left) 
    		xSpeed -= playerSpeed;  	
    	if(right) 
    		xSpeed += playerSpeed;
    	
    	if (!inAir) 
    		if (!IsEntityOnFloor(hitbox, lvlData)) 
    			inAir = true;
 
	    if(inAir) {
	    	if(CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
	    		hitbox.y += airSpeed;
	    		airSpeed += gravity;
	    		updateXPos(xSpeed);
	    	}else {
	    		hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
	    		if(airSpeed > 0) 
	    			resetInAir();
	    		else
	    			airSpeed = fallSpeedAfterCollision;
	    	}
	    }
	    else 
	    	updateXPos(xSpeed);
    	moving = true;
	}
    
	 private void jump() {
		 if(inAir) 
			 return;
		 inAir = true;
		 airSpeed = jumpSpeed;
	 }
	 private void resetInAir() {
		 inAir = false;
		 airSpeed = 0;
	 }
	 private void updateXPos(float xSpeed) {
		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
	 	hitbox.x += xSpeed;
	 	} else {
	 		hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
	 	}
		
	}

	private void loadAnimations() { // Load animations from the sprite sheet
			
		 	BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
	            animations = new BufferedImage[9][6]; // Initialize the 2D animations array with specific dimensions
		        for (int j = 0; j < animations.length; j++)  // Iterate through the rows (different actions)
		            for (int i = 0; i < animations[j].length; i++)  // Iterate through the columns (frames of each action)
		                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40); // Extract a subimage from the sprite sheet
	            }
	 
	 
	 public void loadLvlData(int [][] lvlData) {
		 this.lvlData = lvlData;
		 if(!IsEntityOnFloor(hitbox, lvlData))
			 inAir = true;
	 }
	 
	 public void resetDirBooleans() {
		 left = false;
		 right = false;
		 up = false;
		 down = false;
	 }
	 
	 public void setAttack(boolean attacking) {
		 this.attacking = attacking;
	 }
 
	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	        
	public void setJump(boolean jump) {
		this.jump = jump;
	}
	 
}
	
