package emojiart.greetings;

import java.util.Arrays;

public interface Greetings {
    static String[] getSecondGrassLayer(int imageSize, int waterStart, int waterEnd, String skyBlue, String landGrass, String[] grass) {
        //grass layer 2
        String[] grassl2 = str(imageSize);
        for (int i = 0; i < grassl2.length; ++i) {
            if (grass[i].equals(landGrass)) {
                grassl2[i] = landGrass;
                if (i == waterStart - 2 || i == waterEnd + 2 || i == 0 || i == imageSize - 1) {
                    grassl2[i] = skyBlue;
                }
            } else {
                grassl2[i] = skyBlue;
            }
        }
        return grassl2;
    }

    static String[] getGrassAndSand(int imageSize, int waterStart, int waterEnd, String skyBlue, String landBrown, String landGrass, String sand, String[] ground) {
        //grass & sand
        String[] grass = str(imageSize);
        for (int i = 0; i < grass.length; ++i) {
            if (ground[i].equals(landBrown)) {
                grass[i] = landGrass;
                if (i == waterStart - 1 || i == waterEnd + 1) {
                    grass[i] = sand;
                }
            } else {
                grass[i] = skyBlue;
            }
        }
        return grass;
    }

    static String[] getGroundAndWater(int imageSize, int waterStart, int waterEnd, String landBrown, String water) {
        //ground & water
        String[] ground = str(imageSize);
        for (int x = 0; x < ground.length; ++x) {
            if (x >= waterStart && x <= waterEnd) {
                ground[x] = water;
            } else {
                ground[x] = landBrown;
            }
        }
        return ground;
    }

    static String[] getBase(int imageSize, String landBrown) {
        //base
        String[] base = str(imageSize);
        Arrays.fill(base, landBrown);
        return base;
    }

    static String[] str(int s) {
        return new String[s];
    }
}
