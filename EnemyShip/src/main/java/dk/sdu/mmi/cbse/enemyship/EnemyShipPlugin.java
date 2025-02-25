package dk.sdu.mmi.cbse.enemyship;

import dk.sdu.mmmi.cbse.common.enemyship.EnemyShip;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;
public class EnemyShipPlugin implements IGamePluginService {

    private Random random = new Random();
    @Override
    public void start(GameData gameData, World world) {
        Entity enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }
    @Override
    public void stop(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(EnemyShip.class)) {
            world.removeEntity(enemy);
        }
    }
    private Entity createEnemyShip(GameData gameData){

        Entity enemyShip = new EnemyShip();
        //Random rnd = new Random();
        enemyShip.setPolygonCoordinates(-10,-10,20,5,-10,10);
        enemyShip.setX(random.nextInt(gameData.getDisplayWidth()));
        enemyShip.setY(random.nextInt(gameData.getDisplayHeight()));
        enemyShip.setRadius(15);
        return enemyShip;

    }
}

