import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.collisionsystem.CollisionSystem;

module Collision {
    uses dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
    requires Common;
    requires CommonAsteroid;
    requires CommonBullet;
    provides IPostEntityProcessingService with CollisionSystem;
}