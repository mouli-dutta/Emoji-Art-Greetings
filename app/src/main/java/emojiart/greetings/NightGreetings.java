package emojiart.greetings;

import java.util.Random;

public class NightGreetings implements Greetings {
    private NightGreetings() {
    }

    public static String[] getNightGreetings(String[] bCode, int imageSize) {
        Random r = new Random();

        int waterStart = bCode.length == 9 ? Integer.parseInt(bCode[0]) : r.nextInt(imageSize / 2);

        int waterEnd = bCode.length == 9 ? Integer.parseInt(bCode[1]) : (waterStart + r.nextInt(6));

        String nightSky, landBrown, landGrass, water, sand, moonColor;

        nightSky = "⬛";
        landBrown = "🟫";
        landGrass = "🟩";
        water = "🈁";
        sand = "🟨";
        moonColor = "⬜";


        String[] base = Greetings.getBase(imageSize, landBrown);

        String[] ground = Greetings.getGroundAndWater(imageSize, waterStart, waterEnd, landBrown, water);

        String[] grass = Greetings.getGrassAndSand(imageSize, waterStart, waterEnd, nightSky, landBrown, landGrass, sand, ground);

        String[] grassl2 = Greetings.getSecondGrassLayer(imageSize, waterStart, waterEnd, nightSky, landGrass, grass);


        int tp = bCode.length == 9 ? Integer.parseInt(bCode[2]) : r.nextInt(imageSize);

        int tp1 = bCode.length == 9 ? Integer.parseInt(bCode[3]) : r.nextInt(imageSize);

        int sun = bCode.length == 9 ? Integer.parseInt(bCode[4]) : r.nextInt(imageSize);

        int[] rn = new int[4];

        for (int i = 0; i < rn.length; ++i) {
            rn[i] = (r.nextInt(3));
        }

        String[] getArt = getArt(bCode, imageSize, waterStart, waterEnd, nightSky, landBrown, landGrass, moonColor, base, ground, grass, grassl2, tp, tp1, sun, rn);
        String art = "Good Night\n\n" + getArt[0];
        String buildCode = getArt[1];

        return new String[]{art, buildCode};
    }

    private static String[] getArt(String[] bCode, int imageSize, int waterStart, int waterEnd, String nightSky, String landBrown, String landGrass, String moonColor, String[] base, String[] ground, String[] grass, String[] grassl2, int tp, int tp1, int moon, int[] rn) {
        String treesAndSun = getTreesAndMoon(imageSize, nightSky, landBrown, landGrass, moonColor, grassl2, tp, tp1, moon);
        String secondGrassLayer = forEach(grassl2, bCode.length == 9 ? Integer.parseInt(bCode[5]) : rn[0]);
        String grassLayer = forEach(grass, bCode.length == 9 ? Integer.parseInt(bCode[6]) : rn[1]);
        String groundLayer = forEach(ground, bCode.length == 9 ? Integer.parseInt(bCode[7]) : rn[2]);
        String baseLayer = forEach(base, bCode.length == 9 ? Integer.parseInt(bCode[8]) : rn[3]);

        StringBuilder buildCode = new StringBuilder();
        if (bCode.length == 9) {
            buildCode.append(waterStart).append("-").append(waterEnd).append("-").append(tp).append("-").append(tp1).append("-").append(moon).append("-").append(bCode[5]).append("-").append(bCode[6]).append("-").append(bCode[7]).append("-").append(bCode[8]);
        } else {
            buildCode.append(waterStart).append("-").append(waterEnd).append("-").append(tp).append("-").append(tp1).append("-").append(moon).append("-").append(rn[0]).append("-").append(rn[1]).append("-").append(rn[2]).append("-").append(rn[3]);
        }

        return new String[]{treesAndSun + secondGrassLayer + grassLayer + groundLayer + baseLayer, buildCode.toString()};
    }

    private static String getTreesAndMoon(int imageSize, String nightSky, String landBrown, String landGrass, String moonColor, String[] grassl2, int tp, int tp1, int moon) {
        //Trees and moon
        //Allows trees to merge
        //Allows moon to merge with trees
        //Trees will check for land only below
        StringBuilder sb = new StringBuilder();

        if (grassl2[tp].equals(landGrass) || grassl2[tp1].equals(landGrass)) {

            for (int i = 0; i < 5; ++i) {

                for (int j = 0; j < imageSize; ++j) {

                    String s = i == 1 && j == moon + 1 || i == 0 && j == moon || i == 2 && j == moon ? moonColor : nightSky;

                    if (grassl2[tp].equals(landGrass) && !grassl2[tp1].equals(landGrass)) {
                        sb.append(
                                j == tp && i > 1 ? landBrown :
                                        j == tp - 1 | j == tp | j == tp + 1 && i == 1 || j == tp - 1 | j == tp + 1 && i == 2 || j == tp
                                                ? landGrass : s);

                    } else if (grassl2[tp1].equals(landGrass) && !grassl2[tp].equals(landGrass)) {
                        sb.append(
                                j == tp1 && i > 1 ? landBrown :
                                        j == tp1 - 1 | j == tp1 | j == tp1 + 1 && i == 1 || j == tp1 - 1 | j == tp1 + 1 && i == 2 || j == tp1
                                                ? landGrass : s);

                    } else if (grassl2[tp].equals(landGrass) && grassl2[tp1].equals(landGrass)) {
                        sb.append(
                                j == tp1 | j == tp && i > 1 ? landBrown :
                                        j == tp1 - 1 | j == tp - 1 | j == tp1 | j == tp | j == tp1 + 1 | j == tp + 1 && i == 1 ||
                                                j == tp1 - 1 | j == tp - 1 | j == tp1 + 1 | j == tp + 1 && i == 2 ||
                                                j == tp1 | j == tp && i == 0
                                                ? landGrass : s);

                    }
                }

                sb.append("\n");
            }

        } else {

            for (int i = 0; i < 5; ++i) {
                for (int j = 0; j < imageSize; ++j) {
                    sb.append(i == 1 && j == moon + 1 || i == 0 && j == moon || i == 2 && j == moon ? moonColor : nightSky);
                }

                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private static String forEach(String[] l, int t) {
        StringBuilder sb = new StringBuilder();
        for (int o = 0; o < t; ++o) {
            for (String i : l) {
                sb.append(i);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
