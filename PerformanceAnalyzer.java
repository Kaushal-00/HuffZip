import java.io.File;
import java.text.DecimalFormat;

public class PerformanceAnalyzer {

    private static final DecimalFormat df = new DecimalFormat("#.##");

    // Compression: exactly 4 lines
    public void printCompressionSummary(String originalFilePath, String compressedFilePath, long timeTakenNanos) {
        File originalFile = new File(originalFilePath);
        File compressedFile = new File(compressedFilePath);

        long originalSize = originalFile.length();
        long compressedSize = compressedFile.length();

        double compressionRatio = (originalSize > 0)
                ? ((double) (originalSize - compressedSize) / originalSize) * 100.0
                : 0.0;
        double timeInMs = timeTakenNanos / 1_000_000.0;
        double timeInSeconds = timeTakenNanos / 1_000_000_000.0;

        System.out.println("Original File Size      : " + formatFileSize(originalSize));
        System.out.println("Compressed File Size    : " + formatFileSize(compressedSize));
        System.out.println("Compression Ratio       : " + df.format(compressionRatio) + "%");
        System.out.println("Total Execution Time    : " + df.format(timeInMs) + " ms (" + df.format(timeInSeconds) + " s)");
    }

    // Decompression: Option B (no ratio line, just sizes and time)
    public void printDecompressionSummary(String compressedFilePath, String decompressedFilePath, long timeTakenNanos) {
        File compressedFile = new File(compressedFilePath);
        File decompressedFile = new File(decompressedFilePath);

        long inSize = compressedFile.length();    // .bin size
        long outSize = decompressedFile.length(); // decompressed text size

        double timeInMs = timeTakenNanos / 1_000_000.0;
        double timeInSeconds = timeTakenNanos / 1_000_000_000.0;

        System.out.println("Compressed File Size    : " + formatFileSize(inSize));
        System.out.println("Decompressed File Size  : " + formatFileSize(outSize));
        System.out.println("Total Execution Time    : " + df.format(timeInMs) + " ms (" + df.format(timeInSeconds) + " s)");
    }

    private String formatFileSize(long sizeInBytes) {
        if (sizeInBytes < 1024) {
            return sizeInBytes + " B";
        } else if (sizeInBytes < 1024 * 1024) {
            return df.format(sizeInBytes / 1024.0) + " KB";
        } else {
            return df.format(sizeInBytes / (1024.0 * 1024.0)) + " MB";
        }
    }
}
