import dk.sdu.cbse.asteroid.AsteroidProcessor;
import dk.sdu.cbse.asteroid.EntityProcessor;
import dk.sdu.cbse.asteroid.AsteroidPlugin;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;


module Asteroid {
    uses dk.sdu.cbse.common.score.IScoreSystem;
    exports dk.sdu.cbse.asteroid;
    requires Common;
    requires CommonAsteroid;
    requires CommonScore;
    requires CommonBullet;
    provides IGamePluginService with AsteroidPlugin;
    provides IEntityProcessingService with AsteroidProcessor, EntityProcessor;
}