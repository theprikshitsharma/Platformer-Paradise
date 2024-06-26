package utilz;

public class Constants {
    // Inner class for directional constants
    public static class Directions {
        // Constants representing directions
        public static final int LEFT = 0; // Left direction
        public static final int UP = 1; // Up direction
        public static final int RIGHT = 2; // Right direction
        public static final int DOWN = 3; // Down direction
    }

    // Inner class for player-related constants
    public static class PlayerConstants {
    	
        // Constants representing player actions
        public static final int IDLE = 0; // Player idle action
        public static final int RUNNING = 1; // Player running action
        public static final int JUMP = 2; // Player jump action
        public static final int FALLING = 3; // Player falling action
        public static final int GROUND = 4; // Player on ground action
        public static final int HIT = 5; // Player hit action
        public static final int ATTACK_1 = 6; // Player attack 1 action
        public static final int ATTACK_JUMP_1 = 7; // Player attack while jumping action 1
        public static final int ATTACK_JUMP_2 = 8; // Player attack while jumping action 2

        // Method to get the number of sprites for a given player action
        public static int GetSpriteAmount(int player_action) {
        	
            switch(player_action) {
            
                case RUNNING:
                    return 6; // Return the number of sprites for running action
                case IDLE:
                    return 5; // Return the number of sprites for idle action
                case HIT:
                    return 4; // Return the number of sprites for hit action
                case JUMP:
                case ATTACK_1:
                case ATTACK_JUMP_1:
                case ATTACK_JUMP_2:
                    return 3; // Return the number of sprites for jump and attack actions
                case GROUND:
                    return 2; // Return the number of sprites for on ground action
                case FALLING:
                default:
                    return 1; // Default case, return 1 sprite
                    
            }
        }
    }
}
