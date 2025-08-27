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
        createInitialAsteroids(gameData, world);
    }

    private void createInitialAsteroids(GameData gameData, World world) {
        Random random = new Random();

        // Create 3-5 initial asteroids
        int numAsteroids = 3 + random.nextInt(3);

        for (int i = 0; i < numAsteroids; i++) {
            Entity asteroid = new Asteroid();

            // Large asteroid coordinates
            asteroid.setPolygonCoordinates(20, 0, 14, 14, 0, 20, -14, 14, -20, 0, -14, -14, 0, -20, 14, -14);

            // Random position on the edges of the screen
            if (random.nextBoolean()) {
                // Left or right edge
                asteroid.setX(random.nextBoolean() ? 0 : gameData.getDisplayWidth());
                asteroid.setY(random.nextInt(gameData.getDisplayHeight()));
            } else {
                // Top or bottom edge
                asteroid.setX(random.nextInt(gameData.getDisplayWidth()));
                asteroid.setY(random.nextBoolean() ? 0 : gameData.getDisplayHeight());
            }

            asteroid.setRotation(random.nextInt(360));
            asteroid.setHitPoints(10);
            asteroid.setDmg(10);

            world.addEntity(asteroid);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Asteroid.class) {
                world.removeEntity(e);
            }
        }
    }

}