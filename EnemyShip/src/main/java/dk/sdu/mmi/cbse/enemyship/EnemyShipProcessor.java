package dk.sdu.mmi.cbse.enemyship;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemyship.EnemyShip;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.ServiceLoader;
import java.util.Collection;
public class EnemyShipProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world){
        for (Entity enemy : world.getEntities(EnemyShip.class)){
            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + changeX);
            enemy.setY(enemy.getY() + changeY);
            }
            if (enemy.getX() < 0) {
                enemy.setX(enemy.getX()-gameData.getDisplayWidth());
            }

            if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(gameData.getDisplayWidth()-1);
            }

            if (enemy.getY() < 0) {
                enemy.setY(enemy.getY()-gameData.getDisplayHeight());
            }

            if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(gameData.getDisplayHeight()-1);
            }


        }
    }
    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
