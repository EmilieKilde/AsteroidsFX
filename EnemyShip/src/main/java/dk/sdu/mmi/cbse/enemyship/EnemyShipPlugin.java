package dk.sdu.mmi.cbse.enemyship;

import dk.sdu.mmmi.cbse.common.enemyship.EnemyShip;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;
public class EnemyShipPlugin implements IGamePluginService {

    public EnemyShipPlugin(){

    }
    @Override
    public void start(GameData gameData, World world) {
        Entity enemyShip = createEnemyShip(gameData);
        world.addEntity(enemyShip);
    }
    private Entity createEnemyShip(GameData gameData){
        Entity enemyShip = new EnemyShip();
        Random rnd = new Random();
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemyShip.setX(gameData.getDisplayHeight()/2);
        enemyShip.setY(gameData.getDisplayWidth()/2);
        enemyShip.setRadius(10);
        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemyShip);

    }

}

