import java.io.*;
import java.util.*;

public class NearlySimilarRectangles {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        int sidesRows = Integer.parseInt(ltrim(rtrim(bufferedReader.readLine())));
        int sidesColumns = Integer.parseInt(ltrim(rtrim(bufferedReader.readLine())));
        long[][] sides = new long[sidesRows][sidesColumns];
        for (int i = 0; i < sidesRows; i++) {
            String[] sidesRowItems = split(rtrim(bufferedReader.readLine()));
            for (int j = 0; j < sidesColumns; j++) {
                sides[i][j] = Long.parseLong(sidesRowItems[j]);
            }
        }
        long result = nearlySimilarRectangles(sides);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
        bufferedReader.close();
        bufferedWriter.close();
    }

    public static long nearlySimilarRectangles(long[][] sides) {
        Map<Double, Long> ratioCount = new HashMap<>();
        long count = 0;
        for (long[] side : sides) {
            double ratio = (double) side[0] / side[1];
            ratioCount.put(ratio, ratioCount.getOrDefault(ratio, 0L) + 1);
        }
        for (long value : ratioCount.values()) {
            if (value > 1) {
                count += (value * (value - 1)) / 2;
            }
        }
        return count;
    }
    public static String ltrim(String str) {
        return str.replaceAll("^\\s+", "");
    }
    public static String rtrim(String str) {
        return str.replaceAll("\\s+$", "");
    }
    public static String[] split(String str) {
        return str.split(" ");
    }
}
