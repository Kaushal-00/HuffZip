import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FrequencyMap {

    public HashMap<Character, Integer> frequencyMapGenerator(String filePath) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        FileReader fr = null;

        try {
            fr = new FileReader(filePath);

            int c;
            while ((c = fr.read()) != -1) {
                char ch = (char) c;

                if (frequencyMap.containsKey(ch)) {
                    frequencyMap.put(ch, frequencyMap.get(ch) + 1);
                } else {
                    frequencyMap.put(ch, 1);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());

        } finally {
            try {
                if (fr != null) fr.close();
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }

        return frequencyMap;
    }
}
