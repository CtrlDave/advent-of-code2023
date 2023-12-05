import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CubeConundrum {

    static Map<String,Integer> cubeInBag = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        int total = 0;
        Scanner scanner = new Scanner(new File("./second-part.txt"));
        while (scanner.hasNextLine()){
            String next = scanner.nextLine();
            int result = secondPart(next);
            total += result;
        }
        System.out.println(total);
    }

    private static int secondPart(String line) {
        String[] sets = getSets(line);
        return fewestCubes(sets);
    }

    private static int firstPart(String line) {
        int id = getGameId(line);
        String[] sets = getSets(line);
        if(!possibleCubes(sets)) return 0;
        return id;
    }

    private static int getGameId(String line) {
        String gamePortionstring = line.substring(0, line.indexOf(":"));
        return Integer.parseInt(
                gamePortionstring.replaceAll("[^0-9]", ""));
    }

    private static String[] getSets(String line) {
        String substring = line.substring(line.indexOf(":")+1);
        return substring.split(";");
    }

    private static boolean possibleCubes(String[] sets) {
        String[] cubes;

        for (String set : sets) {
            cubeInBag.put("red",0);
            cubeInBag.put("green",0);
            cubeInBag.put("blue",0);
            if (set.contains(",")) {
                cubes = set.split(",");
            } else {
                cubes = new String[]{set};
            }
            for (String cube : cubes) {
                int numberOfCubes = Integer.parseInt(cube.replaceAll("[^0-9]", ""));
                if (cube.contains("red")) {
                    cubeInBag.put("red", numberOfCubes);
                } else if (cube.contains("green")) {
                    cubeInBag.put("green", numberOfCubes);
                } else if (cube.contains("blue")) {
                    cubeInBag.put("blue", numberOfCubes);
                }
            }
            if(cubeInBag.get("red") > 12 ||
                    cubeInBag.get("green") > 13 ||
                    cubeInBag.get("blue") > 14){
                return false;
            }
        }
        return true;
    }

    private static int fewestCubes(String[] sets) {
        String[] cubes;
        int fewestRed = 0;
        int fewestGreen = 0;
        int fewestBlue = 0;
        for (String set : sets) {
            if (set.contains(",")) {
                cubes = set.split(",");
            } else {
                cubes = new String[]{set};
            }
            for (String cube : cubes) {
                int numberOfCubes = Integer.parseInt(cube.replaceAll("[^0-9]", ""));
                if(cube.contains("red") && numberOfCubes > fewestRed){
                    fewestRed = numberOfCubes;
                } else if(cube.contains("green") && numberOfCubes > fewestGreen){
                    fewestGreen = numberOfCubes;
                } else if(cube.contains("blue") && numberOfCubes > fewestBlue){
                    fewestBlue = numberOfCubes;
                }
            }
        }
        return fewestRed*fewestGreen*fewestBlue;
    }
}
