package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
/**
 *
 * @author jcs
 */
public interface IGamePluginService {

    /**
     *
     *
     * Pre-condition: All gamedata must be loaded and a world must be set. And the game is either started or stopped
     * Post-condition: The game is either started or stopped
     *
     * @param gameData
     * @param world
     * @throws IllegalStateException if no gameData or world
     *
     */

    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}