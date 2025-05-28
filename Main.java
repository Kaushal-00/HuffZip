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

        sc.close();

        FrequencyMap frequencyMapObj = new FrequencyMap();
        HashMap<Character, Integer> frequencyMap = frequencyMapObj.generateFrequencyMap(filePath);

        HuffmanTree huffmanTree = new HuffmanTree();
        HuffmanNode root = huffmanTree.generateHuffmanTree(frequencyMap);

        HuffmanCode huffmanCode = new HuffmanCode();
        HashMap<Character, String> huffmanCodeMap = huffmanCode.generateHuffmanCodeMap(root);
        StringBuffer huffmanTreeCode = huffmanCode.generateHuffmanTreeCode(root);

        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();
        huffmanEncoder.generateCompressedFile(filePath, huffmanCodeMap, huffmanTreeCode);

        HuffmanDecoder decoder = new HuffmanDecoder();
        decoder.decodeFile();
    }
}
