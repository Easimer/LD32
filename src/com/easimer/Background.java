package com.easimer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

/**
 *
 * @author easimer
 */
public class Background implements Entity {

    Sprite house, bg, plaguetree, plaguetree02;
    
    int px = 0; //parallax x
    int plx, ply;
    
    long[] random_stuff;
    
    @Override
    public void Init() {
        /*random_stuff = new long[Math.abs(Game.GetInstance().GetRandom().nextInt(512))];
        for(int i = 0; i < random_stuff.length; i++)
        {
            random_stuff[i] = Game.GetInstance().GetRandom().nextInt(4);
        }
        if(house == null)
        {
            Texture t = new Texture();
            try {
                t.loadFromFile(Paths.get("data/house.png"));
            } catch (IOException ex) {
                Logger.getLogger(Background.class.getName()).log(Level.SEVERE, null, ex);
            }
            house = new Sprite();
            house.setTexture(t);
            house.setScale(2.5f, 2.5f);
        }
        if(plaguetree == null)
        {
            Texture t = new Texture();
            try {
                t.loadFromFile(Paths.get("data/plaguetree.png"));
                
            } catch (IOException ex) {
                Logger.getLogger(Background.class.getName()).log(Level.SEVERE, null, ex);
            }
            plaguetree = new Sprite();
            plaguetree.setTexture(t);
            plaguetree.setScale(4, 4);
        }
        if(plaguetree02 == null)
        {
            Texture t = new Texture();
            try {
                t.loadFromFile(Paths.get("data/plaguetree02.png"));
                
            } catch (IOException ex) {
                Logger.getLogger(Background.class.getName()).log(Level.SEVERE, null, ex);
            }
            plaguetree02 = new Sprite();
            plaguetree02.setTexture(t);
            plaguetree02.setScale(4, 4);
        }
        if(bg == null)
        {
            Texture t = new Texture();
            try {
                t.loadFromFile(Paths.get("data/bg_shit.png"));
            } catch (IOException ex) {
                Logger.getLogger(Background.class.getName()).log(Level.SEVERE, null, ex);
            }
            bg = new Sprite();
            bg.setTexture(t);
            bg.setScale(2, 2);
        }*/
        if(bg == null)
        {
            Texture t = new Texture();
            try {
                t.loadFromFile(Paths.get("data/background.png"));
            } catch (IOException ex) {
                Logger.getLogger(Background.class.getName()).log(Level.SEVERE, null, ex);
            }
            bg = new Sprite();
            bg.setTexture(t);
        }
    }

    @Override
    public void Update(long dt) {
        px = -((plx - 512) / 512);
    }

    @Override
    public void Draw(RenderTarget rt) {
        /*int start = Game.GetInstance().player.GetX();
        int end = start + 1024;
        for(int i = 0; i < 32; i++)
        {
            int xc = start + i * 64;
            int yc = Game.GetInstance().height - 59;
            bg.move(xc, yc);
            Game.GetInstance().Draw(bg);
            bg.move(-xc, -yc);
        }
        for(int i = 0; i < random_stuff.length; i++)
        {
            switch((int)random_stuff[i])
            {
                case 0:
                    break;
                case 1:
                    int pty = Game.GetInstance().height - 59 - (plaguetree.getTexture().getSize().y * 4);
                    int ptx = 256 * i - random_stuff.length / 2 * 128; 
                    plaguetree.move(ptx, pty);
                    Game.GetInstance().Draw(plaguetree);
                    plaguetree.move(-ptx, -pty);
                    break;
                case 2:
                    int hy = Game.GetInstance().height - 59 - (int)((float)house.getTexture().getSize().y * 2.5);
                    int hx = 768 * i - random_stuff.length / 2 * 768; 
                    house.move(hx, hy);
                    Game.GetInstance().Draw(house);
                    house.move(-hx, -hy);
                    break;
                case 3:
                    int p2ty = Game.GetInstance().height - 59 - (plaguetree02.getTexture().getSize().y * 4);
                    int p2tx = 256 * i - random_stuff.length / 2 * 128; 
                    plaguetree02.move(p2tx, p2ty);
                    Game.GetInstance().Draw(plaguetree02);
                    plaguetree02.move(-p2tx, -p2ty);
                    break;
                default:
                    System.out.println("CASE " + random_stuff[i]);
                    break;
            }
        }*/
        rt.draw(bg);
    }

    @Override
    public void Event(org.jsfml.window.event.Event e) {
    }
    
    public void PlayerMove(int x, int y)
    {
        plx = x;
        ply = y;
    }

    @Override
    public int GetX() {
        return 0; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int GetY() {
        return 0; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int GetW() {
        return 0;
    }

    @Override
    public int GetH() {
        return 0;
    }
    
}
