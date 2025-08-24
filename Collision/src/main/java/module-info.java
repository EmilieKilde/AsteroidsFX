import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.collisionsystem.CollisionSystem;

module Collision {
    requires Common;
    provides IEntityProcessingService with CollisionSystem;
}