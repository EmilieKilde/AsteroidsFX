package dk.sdu.cbse.collisionsystem;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class CollisionSystem implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        // Create lists to avoid concurrent modification
        List<Entity> bullets = new ArrayList<>(world.getEntities(Bullet.class));
        List<Entity> allEntities = new ArrayList<>(world.getEntities());
        List<Entity> toRemove = new ArrayList<>();

        // Check bullet collisions with other entities
        for (Entity bullet : bullets) {
            if (toRemove.contains(bullet)) continue;

            for (Entity other : allEntities) {
                if (toRemove.contains(other)) continue;
                if (bullet.equals(other)) continue; // Same entity
                if (other instanceof Bullet) continue; // Bullet vs bullet

                if (isCollision(bullet, other)) {
                    System.out.println("Collision detected: " + bullet.getClass().getSimpleName() +
                            " hit " + other.getClass().getSimpleName());

                    // Apply damage
                    other.setHitPoints(other.getHitPoints() - bullet.getDmg());
                    bullet.setHitPoints(bullet.getHitPoints() - other.getDmg());

                    // Mark for removal if destroyed
                    if (bullet.getHitPoints() <= 0) {
                        toRemove.add(bullet);
                    }
                    if (other.getHitPoints() <= 0) {
                        toRemove.add(other);
                    }

                    // Break to avoid multiple collisions for same bullet
                    break;
                }
            }
        }

        // Remove destroyed entities
        for (Entity entity : toRemove) {
            world.removeEntity(entity);
        }
    }

    public boolean isCollision(Entity e1, Entity e2) {
        double x1 = e1.getX();
        double y1 = e1.getY();
        double x2 = e2.getX();
        double y2 = e2.getY();

        double distance = sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
        double e1Radius = e1.getWidth() / 2;
        double e2Radius = e2.getWidth() / 2;

        return distance < (e1Radius + e2Radius);
    }
}