package dk.sdu.cbse.collisionsystem;


import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import static java.lang.Math.sqrt;

public class CollisionSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity:world.getEntities()) {
            for (Entity entity1:world.getEntities()){
                if(isCollision(entity, entity1) && entity.getClass() != entity1.getClass()){
                    System.out.println(entity.getClass());
                    System.out.println(entity1.getClass());
                    System.out.println("Asteroids : " + entity.getWidth() + " Bullet : " + entity1.getWidth());

                    System.out.println(entity.getDmg());
                    System.out.println(entity1.getDmg());
                    entity.setHitPoints(entity.getHitPoints()-entity1.getDmg());
                    entity1.setHitPoints(entity1.getHitPoints()-entity.getDmg());

                    // Change hitpoints
                    if(entity1.getHitPoints()<1){
                        world.removeEntity(entity1);
                    }

                    if(entity.getHitPoints()<1){
                        world.removeEntity(entity);
                    }
                }
            }
        }
    }

    public boolean isCollision(Entity e1, Entity e2){

        double x1 = e1.getX();
        double y1 = e1.getY();

        double x2 = e2.getX();
        double y2 = e2.getY();

        double result = sqrt(((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2)));
        double e1Width = e1.getWidth()/2;
        double e2Width = e2.getWidth()/2;


        if (result < e1Width+e2Width){
            return true;
        }

        return false;
    }



}