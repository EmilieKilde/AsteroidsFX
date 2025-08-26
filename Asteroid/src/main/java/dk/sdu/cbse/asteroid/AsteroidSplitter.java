package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.asteroids.Asteroid;
import dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;

import java.util.Random;

public class AsteroidSplitter implements IAsteroidSplitter {
    @Override
    public void createSplitAsteroid(Entity e, World world) {
        if (e.getHitPoints()==1){
            if(e.getPolygonCoordinates()[0]<=8){
                world.removeEntity(e);
                //score impl here

            } else{
                Asteroid splitAsteroid = initSplit(e);
                splitAsteroid.setX(e.getX()-splitAsteroid.getRadius()-1);
                splitAsteroid.setY(e.getY()-splitAsteroid.getRadius()-1);

                world.addEntity(splitAsteroid);
                world.removeEntity(e);

                //Score impl
            }
        }


        }
    private Asteroid initSplit(Entity e) {
        Asteroid asteroid = new Asteroid();
        Random rnd = new Random();

        double[] splitAsteroid = e.getPolygonCoordinates();
        for(int i = 0; i<e.getPolygonCoordinates().length; i++){
            e.getPolygonCoordinates()[i]=e.getPolygonCoordinates()[i]*0.7;
        }
        asteroid.setPolygonCoordinates(splitAsteroid);
        asteroid.setRadius((float) splitAsteroid[0]);

        asteroid.setRotation(rnd.nextInt(90));
        asteroid.setHitPoints(rnd.nextInt(2, 4));

        return asteroid;
    }
}
