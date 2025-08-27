package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.asteroids.Asteroid;
import dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.ServiceLoader;

public class AsteroidSplitter implements IAsteroidSplitter {
    private Random random = new Random();
    @Override
    public void createSplitAsteroid(Entity destroyedAsteroids, World world, GameData gameData) throws IOException, URISyntaxException, InterruptedException{
        double currentWidth = destroyedAsteroids.getWidth();

        // Only split if the asteroid is large enough (width > 10)
        if (currentWidth > 10) {
            // Create 2-3 smaller asteroids
            int numFragments = 2 + random.nextInt(2); // 2 or 3 fragments

            for (int i = 0; i < numFragments; i++) {
                Entity fragment = createFragment(destroyedAsteroids, currentWidth);
                world.addEntity(fragment);
            }
        }
    }

    private Entity createFragment(Entity originalAsteroid, double originalWidth) {
        Asteroid fragment = new Asteroid();

        // Position near the original asteroid with some random offset
        double offsetX = (random.nextDouble() - 0.5) * 20; // Random offset -10 to +10
        double offsetY = (random.nextDouble() - 0.5) * 20;

        fragment.setX(originalAsteroid.getX() + offsetX);
        fragment.setY(originalAsteroid.getY() + offsetY);

        // Random rotation
        fragment.setRotation(random.nextInt(360));

        // Determine fragment size based on original size
        if (originalWidth > 30) {
            // Large asteroid -> medium fragments
            fragment.setPolygonCoordinates(10, 0, 7, 7, 0, 10, -7, 7, -10, 0, -7, -7, 0, -10, 7, -7);
            fragment.setHitPoints(5);
        } else if (originalWidth > 15) {
            // Medium asteroid -> small fragments
            fragment.setPolygonCoordinates(5, 0, 3, 3, 0, 5, -3, 3, -5, 0, -3, -3, 0, -5, 3, -3);
            fragment.setHitPoints(2);
        } else {
            // Small asteroid -> tiny fragments
            fragment.setPolygonCoordinates(3, 0, 2, 2, 0, 3, -2, 2, -3, 0, -2, -2, 0, -3, 2, -2);
            fragment.setHitPoints(1);
        }

        fragment.setDmg(5); // Fragments do less damage

        return fragment;
    }
}
