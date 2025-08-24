package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;

import java.util.Random;

public class Asteroids extends Entity {



    public Entity asteroidSplit(Entity entity, int size){

        Random randomNum = new Random();


        Entity asteroid1 = new Asteroids();
        asteroid1.setX(entity.getX());
        asteroid1.setY(entity.getY());
        asteroid1.setRotation(entity.getRotation()+randomNum.nextInt(360));
        asteroid1.setHitPoints(7);


        switch (size){
            case 1:
                asteroid1.setPolygonCoordinates(3, 0, 2, 2, 0, 3, -2, 2, -3, 0, -2, -2, 0, -3, 2, -2);

                break;
            case 2:
                asteroid1.setPolygonCoordinates(5, 0, 3, 3, 0, 5, -3, 3, -5, 0, -3, -3, 0, -5, 3, -3);

                break;


        }
        return asteroid1;




    }


}