import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Trebuchet {

    public static void main(String[] args) throws FileNotFoundException {
        int total = 0;
        Scanner scanner = new Scanner(new File("./second-half.txt"));
        while(scanner.hasNext()){
            String next = scanner.next();
            int valueLine = secondHalf(next);
            total += valueLine;
        }
        System.out.println(total);
    }

    private static int firstHalf(String line){
        String numberOnly = line.replaceAll("[^0-9]", "");
        if(numberOnly.length()>=2){
            numberOnly = numberOnly.charAt(0)+""+numberOnly.charAt(numberOnly.length() - 1);
        } else {
            numberOnly = numberOnly.charAt(0)+""+numberOnly.charAt(0);
        }
        System.out.println(numberOnly);
        return Integer.parseInt(numberOnly);
    }

    private static int secondHalf(String line) {
        String stringDigits = getPositionOccurrences(line);
        String calibrationString = stringDigits.charAt(0)+""+stringDigits.charAt(stringDigits.length()-1);
        return Integer.parseInt(calibrationString);
    }

    private static String getPositionOccurrences(String line) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"one");
        map.put(2,"two");
        map.put(3,"three");
        map.put(4,"four");
        map.put(5,"five");
        map.put(6,"six");
        map.put(7,"seven");
        map.put(8,"eight");
        map.put(9,"nine");

        Integer[] digits = new Integer[line.length()];
        for (int i=1;i<=map.size();i++){
            int indexWord = line.indexOf(map.get(i));
            if(indexWord > -1){
                digits[indexWord] = i;
                int lastIndexWord = line.lastIndexOf(map.get(i));
                if(lastIndexWord > -1){
                    digits[lastIndexWord] = i;
                }
            }
            int indexNumber = line.indexOf(String.valueOf(i));
            if(indexNumber > -1) {
                digits[indexNumber] = i;
                int lastIndexNumber = line.lastIndexOf(String.valueOf(i));
                if(lastIndexNumber > -1){
                    digits[lastIndexNumber] = i;
                }
            }
        }
        String orderedDigits = "";
        for(Integer digit : digits){
            if(digit!=null){
                orderedDigits = orderedDigits.concat(String.valueOf(digit));
            }
        }
        return orderedDigits;
    }
}
