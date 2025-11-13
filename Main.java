import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("=============================================");
        System.out.println(" HuffZip - Text File Compressor/Decompressor");
        System.out.println(" Using Huffman Coding (Lossless)");
        System.out.println("=============================================");
        System.out.println();

        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1) Compress a .txt file");
            System.out.println("2) Decompress a Huffman encoded .bin file");
            System.out.println("3) Exit");
            System.out.print("Enter choice (1/2/3): ");

            String choice = sc.nextLine().trim();

            if (choice.equals("1")) {
                compressFile(sc);
            } else if (choice.equals("2")) {
                decompressFile(sc);
            } else if (choice.equals("3")) {
                System.out.println("Exiting HuffZip. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }

            System.out.println();
        }

        sc.close();
    }

    private static void compressFile(Scanner sc) {
        System.out.println();
        System.out.println("--- COMPRESSION MODE ---");
        System.out.print("Enter full path of .txt file to compress: ");
        String filePath = sc.nextLine().trim();

        if (!filePath.toLowerCase().endsWith(".txt")) {
            System.out.println("Error: Only .txt files are allowed.");
            return;
        }

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println("Error: File not found. Please check the path.");
            return;
        }

        System.out.println("Processing...");
        long startTime = System.nanoTime();

        // 1) Frequency map
        FrequencyMap frequencyMapObj = new FrequencyMap();
        HashMap<Character, Integer> frequencyMap = frequencyMapObj.generateFrequencyMap(filePath);
        if (frequencyMap.isEmpty()) {
            System.out.println("Error: Input file is empty. Nothing to compress.");
            return;
        }

        // 2) Tree
        HuffmanTree huffmanTree = new HuffmanTree();
        HuffmanNode root = huffmanTree.generateHuffmanTree(frequencyMap);

        // 3) Codes
        HuffmanCode huffmanCode = new HuffmanCode();
        HashMap<Character, String> huffmanCodeMap = huffmanCode.generateHuffmanCodeMap(root);
        StringBuffer huffmanTreeCode = huffmanCode.generateHuffmanTreeCode(root);

        // 4) Encode to Encoded.bin
        String outputBin = "Encoded.bin";
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();
        huffmanEncoder.generateCompressedFile(filePath, huffmanCodeMap, huffmanTreeCode, outputBin);

        long endTime = System.nanoTime();

        // 5) Analysis
        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("Compression completed successfully!");
        System.out.println("Output file: " + outputBin);
        System.out.println("-----------------------------------------------");
        PerformanceAnalyzer analyzer = new PerformanceAnalyzer();
        analyzer.printCompressionSummary(filePath, outputBin, endTime - startTime);
        System.out.println("-----------------------------------------------");
    }

    private static void decompressFile(Scanner sc) {
        System.out.println();
        System.out.println("--- DECOMPRESSION MODE ---");
        System.out.print("Enter full path of encoded .bin file: ");
        String compressedFilePath = sc.nextLine().trim();

        File bin = new File(compressedFilePath);
        if (!bin.exists() || !bin.isFile()) {
            System.out.println("Error: Compressed file not found. Please check the path.");
            return;
        }

        System.out.print("Enter output text file name (e.g., Decoded.txt): ");
        String outputFilePath = sc.nextLine().trim();
        if (outputFilePath.isEmpty()) {
            outputFilePath = "Decoded.txt";
        }

        System.out.println("Processing...");
        long startTime = System.nanoTime();

        HuffmanDecoder decoder = new HuffmanDecoder();
        boolean ok = decoder.decodeFile(compressedFilePath, outputFilePath);

        long endTime = System.nanoTime();

        if (!ok) {
            System.out.println("Error: Decompression failed.");
            return;
        }

        // Analysis
        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("Decompression completed successfully!");
        System.out.println("Output file: " + outputFilePath);
        System.out.println("-----------------------------------------------");
        PerformanceAnalyzer analyzer = new PerformanceAnalyzer();
        analyzer.printDecompressionSummary(compressedFilePath, outputFilePath, endTime - startTime);
        System.out.println("-----------------------------------------------");
    }
}
