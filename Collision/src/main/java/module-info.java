import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.collisionsystem.CollisionSystem;

module Collision {
    uses dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
    requires Common;
    requires CommonAsteroid;
    requires CommonBullet;
    provides IEntityProcessingService with CollisionSystem;
}