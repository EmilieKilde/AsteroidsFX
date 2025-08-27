import dk.sdu.cbse.asteroid.AsteroidProcessor;
import dk.sdu.cbse.asteroid.EntityProcessor;
import dk.sdu.cbse.asteroid.AsteroidSplitter;
import dk.sdu.cbse.asteroid.AsteroidPlugin;
import dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;


module Asteroid {
    exports dk.sdu.cbse.asteroid;
    requires Common;
    requires CommonAsteroid;
    requires CommonBullet;
    provides IGamePluginService with AsteroidPlugin;
    provides IEntityProcessingService with AsteroidProcessor, EntityProcessor;
    provides IAsteroidSplitter with AsteroidSplitter;
}