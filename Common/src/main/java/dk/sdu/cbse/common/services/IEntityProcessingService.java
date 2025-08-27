package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IEntityProcessingService {

    /**
     *
     *
     * Pre-condition: All gamedata must be loaded and a world must be set.
     * Post-condition: All processes must be terminated
     *
     * @param gameData
     * @param world
     * @throws NullPointerException if no gameData or world
     *
     */
    void process(GameData gameData, World world) throws IOException, URISyntaxException, InterruptedException;
}