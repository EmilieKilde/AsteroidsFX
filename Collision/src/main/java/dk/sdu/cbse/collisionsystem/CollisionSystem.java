package dk.sdu.cbse.collisionsystem;

import dk.sdu.cbse.common.asteroids.Asteroid;
import dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import static java.lang.Math.sqrt;

public class CollisionSystem implements IPostEntityProcessingService, IEntityProcessingService {
    private static int destroyedAsteroids = 0;

    @Override
    public void process(GameData gameData, World world) throws IOException, URISyntaxException, InterruptedException {
        List<Entity> entitiesToRemove = new ArrayList<>();


        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                if (entity1.equals(entity2) ||
                        entitiesToRemove.contains(entity1) ||
                        entitiesToRemove.contains(entity2)) {
                    continue;
                }


                if (isCollision(entity1, entity2) && !entity1.getClass().equals(entity2.getClass())) {

                    // Apply damage
                    entity1.setHitPoints(entity1.getHitPoints() - entity2.getDmg());
                    entity2.setHitPoints(entity2.getHitPoints() - entity1.getDmg());

                    // Check if entities should be destroyed
                    if (entity1.getHitPoints() <= 0 && !entitiesToRemove.contains(entity1)) {
                        entitiesToRemove.add(entity1);

                        // If it's an asteroid being destroyed, try to split it and count points
                        if (entity1 instanceof Asteroid) {
                            handleAsteroidDestruction(entity1, world, gameData);
                            destroyedAsteroids++;
                        }
                    }

                    if (entity2.getHitPoints() <= 0 && !entitiesToRemove.contains(entity2)) {
                        entitiesToRemove.add(entity2);

                        // If it's an asteroid being destroyed, try to split it and count points
                        if (entity2 instanceof Asteroid) {
                            handleAsteroidDestruction(entity2, world, gameData);
                            destroyedAsteroids++;
                        }
                    }
                }
            }
        }

        // Remove all entities marked for destruction
        for (Entity entity : entitiesToRemove) {
            world.removeEntity(entity);
        }
    }

    private void handleAsteroidDestruction(Entity asteroid, World world, GameData gameData) throws IOException, URISyntaxException, InterruptedException {
        // Try to find an asteroid splitter service and use it
        ServiceLoader<IAsteroidSplitter> splitterLoader = ServiceLoader.load(IAsteroidSplitter.class);
        for (IAsteroidSplitter splitter : splitterLoader) {
            splitter.createSplitAsteroid(asteroid, world, gameData);
            break; // Use the first available splitter
        }
    }

    public boolean isCollision(Entity e1, Entity e2) {
        double x1 = e1.getX();
        double y1 = e1.getY();
        double x2 = e2.getX();
        double y2 = e2.getY();

        double distance = sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        double e1Radius = e1.getWidth() / 2;
        double e2Radius = e2.getWidth() / 2;

        return distance < (e1Radius + e2Radius);
    }

    public static int getDestroyedAsteroids() {
        return destroyedAsteroids;
    }

    public static void resetDestroyedAsteroids() {
        destroyedAsteroids = 0;
    }
}