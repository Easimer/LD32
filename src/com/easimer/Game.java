package com.easimer;

import java.util.Random;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.window.ContextSettings;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

//Title: Guardian of the Blightland

/**
 *
 * @author easimer
 */
public class Game {
    private static Game instance;
    
    private RenderWindow window;
    private EntityContainer entcont;
    
    private long frameStart, frameEnd;
    
    public int width, height;
    private SoundContainer sc;
    
    public Player player;
    private Random r;
    int plx, ply;
    
    public static Game GetInstance()
    {
        if(instance == null)
        {
            instance = new Game();
        }
        return instance;
    }
    
    private Game()
    {
        entcont = EntityContainer.GetInstance();
        frameStart = 0;
        frameEnd = 0;
    }
    
    public void Run(int width, int height)
    {
        //ContextSettings settings = new ContextSettings(0, 0, 8);
        r = new Random();
        this.width = width;
        this.height = height;
        window = new RenderWindow(new VideoMode(width, height), "Guardian of the Blightland"/*, 4, settings*/);
        player = new Player();
        entcont.AddEntity(player, "player");
        Background bg = new Background();
        entcont.AddEntity(bg, "core_background");
        DebugText dt = new DebugText();
        entcont.AddEntity(dt, "core_dt");
        //Zombie z = new Zombie(0, 0);
        //entcont.AddEntity(z, "zombie");
        /*for(int i = 0; i < 4; i++)
        {
            int x = r.nextInt(10240) - 5120;
            System.out.println(x);
            MonsterMaker mm = new MonsterMaker();
            mm.x = x;
            mm.y = 0;
            EntityContainer.GetInstance().AddEntity(mm);
        }*/
        MonsterMaker mm = new MonsterMaker();
        EntityContainer.GetInstance().AddEntity(mm);
        sc = new SoundContainer();
        entcont.AddEntity(sc, "core_soundcontainer");
        //sc.LoadSound("");
        Color clear = new Color(104, 156, 192);
        while(window.isOpen())
        {
            for(Event e : window.pollEvents())
            {
                entcont.Event(e);
                switch(e.type)
                {
                    case CLOSED:
                        window.close();
                        break;
                }
            }
            entcont.Update(frameStart - frameEnd);
            window.clear(clear);
            entcont.Draw(window);
            window.display();
            frameEnd = frameStart;
            frameStart = System.currentTimeMillis();
        }
    }
    
    public void PlayerMove(int x, int y)
    {
        plx = x;
        ply = y;
    }
    
    public void Draw(Sprite s)
    {
        s.move(-plx, ply - 128);
        window.draw(s);
        s.move(plx, -(ply - 128));
    }
    
    public void Draw(RectangleShape s)
    {
        s.move(-plx, ply - 128);
        window.draw(s);
        s.move(plx, -(ply - 128));
    }
    
    public Random GetRandom()
    {
        return r;
    }
    
    public SoundContainer GetSoundContainer()
    {
        return sc;
    }
    
    public static void DumpInfo()
    {
        String dmsg = "";
        dmsg += "Width: " + instance.width;
        dmsg += "\nHeight: " + instance.height;
        System.out.println(dmsg);
    }
}
