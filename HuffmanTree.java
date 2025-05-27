import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanTree{

    HuffmanNode generateHuffmanTree(HashMap<Character, Integer> frequencyMap) {

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>((a,b) -> a.frequency - b.frequency);

        for(char ch: frequencyMap.keySet()){
            HuffmanNode huffmanNode = new HuffmanNode(ch, frequencyMap.get(ch));
            priorityQueue.add(huffmanNode);
        }


        while(priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode combined = new HuffmanNode(left.frequency + right.frequency, left, right);

            priorityQueue.add(combined);
        }

        return priorityQueue.poll();
    }
}