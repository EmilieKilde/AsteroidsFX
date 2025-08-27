package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.asteroids.Asteroid;
import dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.score.IScoreSystem;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.ServiceLoader;

public class AsteroidSplitter implements IAsteroidSplitter {
    @Override
    public void createSplitAsteroid(Entity e, World world, GameData gameData) throws IOException, URISyntaxException, InterruptedException{
        if (e.getHitPoints()==1){
            if(e.getPolygonCoordinates()[0]<=8){
                world.removeEntity(e);
                //score impl here
                if (getScoreImp() != null){
                    getScoreImp().addScore(5);
                    getScoreImp().updateScore(gameData);
                }

            } else{
                Asteroid splitAsteroid = initSplit(e);
                splitAsteroid.setX(e.getX()-splitAsteroid.getRadius()-1);
                splitAsteroid.setY(e.getY()-splitAsteroid.getRadius()-1);

                world.addEntity(splitAsteroid);
                world.removeEntity(e);

                //Score impl
                if(getScoreImp() != null){
                    getScoreImp().addScore(10);
                    getScoreImp().updateScore(gameData);
                }
            }
        } else {
            e.setHitPoints(e.getHitPoints()-1);
        }


        }
    private Asteroid initSplit(Entity e) {
        Asteroid asteroid = new Asteroid();
        Random rnd = new Random();

        double[] splitAsteroid = e.getPolygonCoordinates();
        for(int i = 0; i<e.getPolygonCoordinates().length; i++){
            e.getPolygonCoordinates()[i]=e.getPolygonCoordinates()[i]*0.7;
        }
        asteroid.setPolygonCoordinates(splitAsteroid);
        asteroid.setRadius((float) splitAsteroid[0]);

        asteroid.setRotation(rnd.nextInt(90));
        asteroid.setHitPoints(rnd.nextInt(2, 4));

        return asteroid;
    }
    public IScoreSystem getScoreImp(){
        if(ServiceLoader.load(IScoreSystem.class).stream().map(ServiceLoader.Provider::get).findFirst().isPresent()){
            return ServiceLoader.load(IScoreSystem.class).stream().map(ServiceLoader.Provider::get).findFirst().get();

        } else{
            return null;
        }
    }
}
