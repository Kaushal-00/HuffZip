# **HuffZip:**

- **HuffZip – Huffman Text Compressor/Decompressor** is a Java-based implementation of the **Huffman Coding Algorithm** that allows compression and decompression of `.txt` files through a clean **menu-driven console interface**.
- It achieves **lossless compression** by encoding characters with variable-length binary codes based on their frequencies.
- This updated version features **robust error handling**, **execution time tracking**, and **file size reporting** for both compression and decompression.

---

## **How HuffZip Works:**

### **Compression Phase:**

- **Input:** A plain `.txt` file.
- **Steps:**

  - **1. Frequency Map Generation:** Counts how many times each character appears in the input text.
  - **2. Huffman Tree Construction:** Builds a binary tree where less frequent characters appear deeper.
  - **3. Code Assignment:** Each character receives a unique binary code — shorter for common characters, longer for rare ones.
  - **4. Tree Serialization:** Converts the Huffman tree into a bitstream using preorder traversal:
    - `'1'` followed by the **8-bit ASCII** of a leaf character
    - `'0'` for internal nodes
  - **5. Binary File Encoding:**
    - Writes both the serialized tree and encoded text data into a compact binary file — `Encoded.bin`.

---

### **Decompression Phase:**

- **Input:** The compressed binary file `Encoded.bin`.
- **Steps:**

  - **1. Tree Deserialization:** Rebuilds the original Huffman tree from the binary stream.
  - **2. Bit-by-Bit Decoding:** Reads the encoded data and decodes it using the tree structure.
  - **3. Output Generation:** Writes the recovered text back into a `.txt` file — typically `Decoded.txt`.
  - **4. Performance Report:** Displays file sizes and total decompression time.

---

## **Project Structure:**

```
├── FrequencyMap.java           # Builds frequency table from input text
├── HuffmanNode.java            # Node structure for the Huffman Tree
├── HuffmanTree.java            # Constructs Huffman Tree from frequency map
├── HuffmanCode.java            # Generates code map and serialized tree bits
├── HuffmanEncoder.java         # Encodes text + tree into binary file (Encoded.bin)
├── HuffmanDecoder.java         # Rebuilds Huffman Tree and decodes binary file
├── PerformanceAnalyzer.java    # Displays size, ratio, and time reports
├── Main.java                   # CLI interface for compression/decompression
├── testFile.txt                # Sample text file for testing (~60 KB)
└── README.md                   # Project documentation
```

---

## **Compression Example:**

| Process       | Input File              | Output File            | Size              | Result       |
| ------------- | ----------------------- | ---------------------- | ----------------- | ------------ |
| Compression   | `testFile.txt` (~60 KB) | `Encoded.bin` (~28 KB) |  ~53% smaller     | Successful   |
| Decompression | `Encoded.bin`           | `Decoded.txt` (~60 KB) |  Perfect Recovery | Lossless     |

---

## **Key Features:**

- **Lossless Compression** – Ensures the original text is perfectly recovered.
- **Interactive CLI** – User-friendly terminal interface with options for compression/decompression.
- **Performance Metrics** – Displays file sizes, compression ratio, and execution time in ms & seconds.
- **Error Handling** – Detects invalid files or corrupted data streams.
- **Modular Structure** – Organized into distinct Java classes for clarity and maintainability.

---

## **How to Use HuffZip:**

### **1. Compile the Source Files:**

```bash
javac *.java
```

### **2. Run the Program:**

```bash
java Main
```

### **3. Choose an Option:**

- `1` → Compress a `.txt` file
- `2` → Decompress a `.bin` file
- `3` → Exit

### **4. Outputs:**

- **Compression Mode:**

  - Creates `Encoded.bin` in the current directory.
  - Displays file size reduction and execution time.
- **Decompression Mode:**

  - Creates `Decoded.txt` (or custom name you provide).
  - Confirms successful recovery and shows file stats.

---

### **Example Run (CLI Preview):**

```
=============================================
 HuffZip - Text File Compressor/Decompressor
 Using Huffman Coding (Lossless)
=============================================

Choose an operation:
1) Compress a .txt file
2) Decompress a Huffman encoded .bin file
3) Exit
Enter choice (1/2/3): 1

--- COMPRESSION MODE ---
Enter full path of .txt file to compress: testFile.txt
Processing...

-----------------------------------------------
Compression completed successfully!
Output file: Encoded.bin
-----------------------------------------------
Original File Size      : 60.12 KB
Compressed File Size    : 28.45 KB
Compression Ratio       : 52.67%
Total Execution Time    : 123.45 ms (0.12 s)
-----------------------------------------------
```

---

## **Technical Highlights:**

- Bit-level operations for tree serialization and data compression.
- Efficient use of Java I/O streams (`FileInputStream`, `FileOutputStream`, `FileReader`).
- Uses `System.nanoTime()` for precise time tracking.
- Modular and reusable classes to separate concerns (Encoding, Decoding, Analysis).

---

## **Author:**

- **Kaushal Kathiriya**
- A.D. Patel Institute of Technology – Information Technology (2023–2027)

---