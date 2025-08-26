package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.asteroids.Asteroid;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    //private Entity asteroid;

    @Override
    public void start(GameData gameData, World world) {
        Random random = new Random();
        for (int i=0; i<random.nextInt(2,15); i++) {
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {

        Entity asteroid = new Asteroid();
        Random rnd = new Random();
        int size = rnd.nextInt(10)+5;
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(rnd.nextInt(0,gameData.getDisplayWidth()));
        asteroid.setY(rnd.nextInt(0,gameData.getDisplayHeight()));
        asteroid.setRadius(size);
        asteroid.setRotation(rnd.nextInt(90));
        asteroid.setHitPoints(10);
        asteroid.setDmg(10);
        //asteroid.add(new LifePart(10, 10));

        return asteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities()) {
            if (asteroid.getClass() == Asteroid.class) {
                world.removeEntity(asteroid);
            }
        }
    }

}