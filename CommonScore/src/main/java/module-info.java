import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.score.IScoreSystem;
import dk.sdu.cbse.common.score.ScorePlugin;
module CommonScore {
    exports dk.sdu.cbse.common.score;
    requires Common;
    requires javafx.graphics;
    requires java.net.http;
    provides IGamePluginService with ScorePlugin;
    provides IScoreSystem with ScorePlugin;
}