import dk.sdu.cbse.bulletSystem.BulletControl;
import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Bullet {
    exports dk.sdu.cbse.bulletSystem;
    requires Common;
    requires CommonBullet;
    provides IGamePluginService with dk.sdu.cbse.bulletSystem.BulletPlugin;
    provides BulletSPI with BulletControl;
    provides IEntityProcessingService with BulletControl;
}