package dk.sdu.cbse.common.score;

import dk.sdu.cbse.common.data.GameData;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IScoreSystem {
    void updateScore(GameData gameData) throws URISyntaxException, IOException, InterruptedException;
    void addScore(int add) throws IOException, URISyntaxException, InterruptedException;
}
