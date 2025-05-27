import java.util.HashMap;

public class HuffmanCode {

    HashMap<Character, String> generateHuffmanCode(HuffmanNode root) {

        HashMap<Character, String> huffmanCodeMap = new HashMap<>();
        generateCode(root, "", huffmanCodeMap);

        return huffmanCodeMap;
    }

    void generateCode(HuffmanNode node, String code, HashMap<Character, String> huffmanCodeMap) {
        if (node == null) {
            return;
        }

        if (node.ch != '\0') {
            huffmanCodeMap.put(node.ch, code);
            return;
        }

        generateCode(node.left, code + "0", huffmanCodeMap);
        generateCode(node.right, code + "1", huffmanCodeMap);
    }
}
