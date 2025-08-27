package dk.sdu.cbse.common.score;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.*;

import java.net.http.HttpClient;

public class ScorePlugin implements IGamePluginService, IScoreSystem{
    private final String baseURL;
    private Text playerScoreText;
    private final HttpClient client;

    public ScorePlugin(){
        this.baseURL="http://localhost:8080";
        this.client= HttpClient.newHttpClient();
    }
    @Override
    public void start(GameData gameData, World world){
        this.playerScoreText = new Text(10, 20, "Score: 0");
        playerScoreText.setFill(Color.WHITE);
        playerScoreText.setId("playerScoreText");
        playerScoreText.toFront();

        gameData.getGameWindow().getChildren().add(playerScoreText);
        playerScoreText.toFront();
    }
    @Override
    public void stop(GameData gameData, World world) {
    }
    @Override
    public void updateScore(GameData gameData) throws URISyntaxException, IOException, InterruptedException {
        try {
            int newScore = getScore();
            for (Node node : gameData.getGameWindow().getChildren()) {
                String nodeId = node.getId();
                if ("playerScoreText".equals(nodeId)) {
                    Text currentScore = (Text) node;
                    currentScore.setText("Score: " + newScore);
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Could not update score: " + e.getMessage());
        }
    }
    public int getScore() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + "/getScore"))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return Integer.parseInt(response.body());
    }
    @Override
    public void addScore(int add) throws IOException, InterruptedException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseURL + "/addPoint?points=" + add))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .header("Content-Type", "application/json")
                    .build();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.err.println("Scoring service unavailable: " + e.getMessage());
        }
    }
}
