import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.collisionsystem.CollisionSystem;

module Collision {
    requires Common;
    requires CommonBullet;
    requires Asteroid;
    provides IEntityProcessingService with CollisionSystem;
}