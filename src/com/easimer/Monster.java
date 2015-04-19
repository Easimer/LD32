package com.easimer;

import org.jsfml.graphics.RenderTarget;

/**
 *
 * @author easimer
 */
public class Monster implements Entity {

    public AI ai;
    private int hp = 100;
    private int maxhp = 100;
    int x, y;
    
    boolean dead = false;
    
    public Monster(){}
    
    public Monster(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void Init() {
    }

    @Override
    public void Update(long dt) {
        
    }

    @Override
    public void Draw(RenderTarget rt) {
        
    }

    @Override
    public void Event(org.jsfml.window.event.Event e) {
        
    }
    
    public void Attack()
    {
        
    }

    @Override
    public int GetX() {
        return x;
    }

    @Override
    public int GetY() {
        return y;
    }
    
    public int GetHealth()
    {
        return hp;
    }
    
    private void SetHealth(int newhp)
    {
        hp = newhp;
    }
    
    public int GetMaxHealth()
    {
        return maxhp;
    }
    
    public void SetMaxHealth(int newmhp)
    {
        maxhp = newmhp;
    }
    
    public void GetDamage(int dmg)
    {
        hp -= dmg;
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
