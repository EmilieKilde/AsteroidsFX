package dk.sdu.cbse.score;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication
@RestController
public class ScoreSystem {
    private int pScore= 0;

    public static void main(String[] args){
        SpringApplication.run(ScoreSystem.class, args);
    }

    @PutMapping("/addPoint")
    public int scoreCalc(@RequestParam(value="points") int points){
        pScore += points;
        return pScore;
    }
    @GetMapping("/getScore")
    public int getpScore(){
        return this.pScore;
    }
}
