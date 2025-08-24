
import dk.sdu.cbse.player.EntityProcessor;
import dk.sdu.cbse.player.PlayerControl;
import dk.sdu.cbse.player.PlayerPlugin;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Player {
    requires Common;
    requires CommonBullet;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with PlayerPlugin;
    provides IEntityProcessingService with PlayerControl, EntityProcessor;
}