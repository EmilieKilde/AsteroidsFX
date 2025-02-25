import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module EnemyShip {
    requires Common;
    requires CommonEnemyShip;
    requires CommonBullet;
    provides IGamePluginService with dk.sdu.mmmi.cbse.enemyship.EnemyShipPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.enemyship.EnemyShipProcessor;

}