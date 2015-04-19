package com.easimer;

/**
 *
 * @author easimer
 */
public class ludumdare32 {

    public static Game game;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        game = Game.GetInstance();
        try {
        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        game.Run(width, height);
        } catch (ArrayIndexOutOfBoundsException ex)
        {
            game.Run(1024, 768);
        }
    }
    
}
