import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'maxSubarrayValue' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static long maxSubarrayValue(List<Integer> arr) {
        List<Long> even = new ArrayList<>();
        List<Long> odd = new ArrayList<>();
        even.add(0L);
        odd.add(0L);

        for (int i = 0; i < arr.size(); i++) {
            if (i % 2 == 0) {
                even.add(even.get(even.size() - 1) + arr.get(i));
                odd.add(odd.get(odd.size() - 1));
            } else {
                even.add(even.get(even.size() - 1));
                odd.add(odd.get(odd.size() - 1) + arr.get(i));
            }
        }

        long ans = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j <= arr.size(); j++) {
                long a = even.get(j) - even.get(i);
                long b = odd.get(j) - odd.get(i);
                ans = Math.max(ans, (a - b) * (a - b));
            }
        }
        return ans;
    }

}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, arrCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        long result = Result.maxSubarrayValue(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
