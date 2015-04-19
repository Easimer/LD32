package com.easimer;

/**
 *
 * @author easimer
 */
public class BadAI implements AI {

    private Entity attackThis;
    
    @Override
    public void Think(double dt, Entity e) {
        /*double mindistance = Double.MAX_VALUE;
        String nearest = "";
        for(String entid : EntityContainer.GetInstance().GetEntityList())
        {
            if(entid.startsWith("core_"))
                continue;
            double distance = 0.0;
            Entity enemy = EntityContainer.GetInstance().GetEntityByString(entid);
            if(Monster.class.isInstance(e))
            {
                continue;
            }
            distance = Math.sqrt(Math.pow(Math.abs(enemy.GetX() - e.GetX()), 2) + Math.pow(Math.abs(enemy.GetY() - e.GetY()), 2));
            if(distance == 0.0) continue; //it's me
            if(distance < mindistance)
            {
                nearest = entid;
                mindistance = distance;
            }
        }*/
        attackThis = Game.GetInstance().player;
    }

    @Override
    public Entity Attack() {
        return attackThis;
    }
    
}
