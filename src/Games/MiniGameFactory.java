package Games;
import java.util.Random;

import Grids.GridGenerator;

public class MiniGameFactory {
    public boolean MiniGameFactory() {
        Random random = new Random();
        int randomGeo = random.nextInt(3);
        int randomLuck = random.nextInt(4);
        int randomMath = random.nextInt(3);
        int randomWord = random.nextInt(2);

        if (GridGenerator.p.currentIsland == "GEO") {
            switch (randomGeo) {
                case 0:
                    return new MiniGeoA().playGame();
                case 1:
                    return new MiniGeoB().playGame();
                case 2:
                    return new MiniGeoC().playGame();
            }
        }
        else if (GridGenerator.p.currentIsland == "MATH") {
            switch (randomMath) {
                case 0:
                    return new MiniMathA().playGame();
                case 1:
                    return new MiniMathB().playGame();
                case 2:
                    return new MiniMathC().playGame();
            }
        }
        else if (GridGenerator.p.currentIsland == "LUCK") {
            switch (randomLuck) {
                case 0:
                    return new MiniLuckA().playGame();
                case 1:
                    return new MiniLuckB().playGame();
                case 2:
                    return new MiniLuckC().playGame();
                case 3:
                    return new MiniLuckD().playGame();
            }
        }
        else {
            switch (randomWord) {
                case 0:
                    return new MiniWordA().playGame();
                case 1:
                    return new MiniWordB().playGame();
            }
        }
    }
}
