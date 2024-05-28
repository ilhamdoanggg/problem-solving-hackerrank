import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BalanceSystemFilePartition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int parentCount = scanner.nextInt();
        int[] parent = new int[parentCount];
        for (int i = 0; i < parentCount; i++) {
            parent[i] = scanner.nextInt();
        }
        int filesSizeCount = scanner.nextInt();
        int[] filesSize = new int[filesSizeCount];
        for (int i = 0; i < filesSizeCount; i++) {
            filesSize[i] = scanner.nextInt();
        }
        int result = mostBalancedPartition(parent, filesSize);
        System.out.println(result);
        scanner.close();
    }

    public static int mostBalancedPartition(int[] parent, int[] filesSize) {
        int n = parent.length;
        List<List<Integer>> children = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            children.get(parent[i]).add(i);
        }
        int[] sizeSums = new int[n];
        calculateSizeSums(0, children, filesSize, sizeSums);
        int totalSum = sizeSums[0];
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int diff = Math.abs(totalSum - 2 * sizeSums[i]);
            if (diff < minDiff) {
                minDiff = diff;
            }
        }
        return minDiff;
    }

    private static int calculateSizeSums(int node, List<List<Integer>> children, int[] filesSize, int[] sizeSums) {
        int sum = filesSize[node];
        for (int child : children.get(node)) {
            sum += calculateSizeSums(child, children, filesSize, sizeSums);
        }
        sizeSums[node] = sum;
        return sum;
    }
}
