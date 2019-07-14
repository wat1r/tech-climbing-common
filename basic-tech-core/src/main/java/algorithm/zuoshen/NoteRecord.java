package algorithm.zuoshen;

/**
 * Created by FrankCooper
 * Date 2019/7/14 11:37
 * Description
 */
public class NoteRecord {


    public static void main(String[] args) {

    }


    private static void test1(int[] w, int[] c, int n, int V) {
        int[][] dp = new int[n + 1][V + 1];
        for (int i = 1; i <= n; i++) {
            for (int v = w[i]; v <= V; v++) {
                dp[i][v] = Math.max(dp[i - 1][v], dp[i - 1][v - w[i]] + c[i]);
            }
        }
    }


    private static void test2(int[] w, int[] c, int n, int V) {
        int[] dp = new int[V + 1];
        for (int i = 1; i <= n; i++) {
            for (int v = V; v > -w[i]; v--) {
                dp[v] = Math.max(dp[v], dp[v - w[i]] + c[i]);
            }
        }
    }




}
