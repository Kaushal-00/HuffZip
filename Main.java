import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the full path of your .txt file:");
        String filePath = sc.nextLine();

        if (!filePath.toLowerCase().endsWith(".txt")) {
            System.out.println("Only .txt files are allowed.");

            sc.close();
            return;
        }

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File not found. Please check the path.");

            sc.close();
            return;
        }

        FrequencyMap frequencyMapObj = new FrequencyMap();
        HashMap<Character, Integer> frequencyMap = frequencyMapObj.frequencyMapGenerator(filePath);

        System.out.println("Character Frequencies:");
        System.out.println(frequencyMap);

        sc.close();
    }
}
