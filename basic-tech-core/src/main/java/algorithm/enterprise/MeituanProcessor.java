package algorithm.enterprise;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/8/13 8:27
 * @description: 关联链接：https://blog.csdn.net/wat1r/article/details/119669619
 */

public class MeituanProcessor {

    //小美的用户名
    static class _1st {


        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            int T = scanner.nextInt();
            String[] arr = new String[T];
            for (int i = 0; i < T; i++) {
                arr[i] = scanner.next();
            }
            String regex = "^([a-zA-Z])([a-zA-Z]*)([0-9]{1,})([a-zA-Z0-9]*)$";
//        String regex = "^([a-zA-Z])([a-zA-Z]*)([0-9]+)([a-zA-Z]*)$";
//        String regex = "^([a-zA-Z])([a-zA-Z]*)([0-9]+)+([a-zA-Z0-9]*)$";
            for (int i = 0; i < T; i++) {
                System.out.printf("%s\n", arr[i]);
                boolean res = Pattern.matches(regex, arr[i]);
                if (res) System.out.println("Accept");
                else System.out.println("Wrong");
            }

        }

    }


    static class _2nd {

        public static void main(String[] args) {

        }
    }

    //小美的跑腿代购
    static class _3rd {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt(), m = scanner.nextInt();
            //[0]存编号，[1]存获取该订单编号的价值
            //这里的排序是按价值相同相同时，大的编号排在前面
            //否则按价值从小到大排序，因为下面有个peek 的比较操作
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
                if (a[1] == b[1]) return b[0] - a[0];
                else return a[1] - b[1];
            });
            for (int i = 0; i < n; i++) {
                int v = scanner.nextInt(), w = scanner.nextInt();
                if (pq.size() < m) pq.offer(new int[]{i, v + w * 2});
                else if (pq.peek()[1] < v + w * 2) {
                    pq.poll();
                    pq.offer(new int[]{i, v + w * 2});
                }
            }
            int[] res = new int[m];
            for (int i = 0; i < m; i++) {
                res[i] = pq.poll()[0];
            }
            Arrays.sort(res);
            for (int x : res) {
                System.out.print(x + 1 + " ");
            }
        }
    }


    //小美的跑腿代购
    static class _3rd_1 {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt(), m = scanner.nextInt();
            Integer[][] arr = new Integer[n][2];
            for (int i = 0; i < n; i++) {
                int v = scanner.nextInt(), w = scanner.nextInt();
                arr[i][0] = i;
                arr[i][1] = v + w * 2;
            }
            Arrays.sort(arr, (a, b) -> {
                if (a[1].equals(b[1])) return a[0] - b[0];
                return b[1] - a[1];
            });
            int[] res = new int[m];
            for (int i = 0; i < m; i++) {
                res[i] = arr[i][0] + 1;
            }
            Arrays.sort(res);
            for (int x : res) {
                System.out.print(x + " ");
            }
        }
    }

    //小团的复制粘贴
    static class _4th {

        static final int N = 100010;

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int[] A = new int[N];
            int[] B = new int[N];
            for (int i = 1; i <= n; i++) {
                A[i] = sc.nextInt();
                B[i] = -1;
            }
            int m = sc.nextInt();
            while (m-- > 0) {
                int op = sc.nextInt();
                if (op == 1) {
                    int k = sc.nextInt();
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    for (int i = x, j = y; i < k + x; i++, j++) {
                        B[j] = A[i];
                    }
                } else {
                    int x = sc.nextInt();
                    System.out.printf("%d\n", B[x]);
                }
            }
        }
    }

    //小美的区域会议
    static class _5th {
        static final int MOD = (int) (1e9) + 7;

        static int n, k;
        static List<List<Integer>> edges;
        static int[] level;
        static boolean[] vis;


        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            n = sc.nextInt();
            k = sc.nextInt();
            edges = new ArrayList<>();
            level = new int[n + 1];//级别
            vis = new boolean[n + 1];//访问数组
            for (int i = 0; i <= n; i++) {
                edges.add(new ArrayList());
            }
            for (int i = 1; i < n; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                edges.get(a).add(b);
                edges.get(b).add(a);
            }
            for (int i = 1; i <= n; i++) {
                level[i] = sc.nextInt();
            }
            long res = 0;
            for (int i = 1; i <= n; i++) {
                Arrays.fill(vis, false);//每一轮都初始化
                res = (res + dfs(i, i)) % MOD;
            }
            System.out.println(res);
        }


        private static long dfs(int u, int s) {
            vis[u] = true;//标记已经被访问过
            long res = 1;
            for (int v : edges.get(u)) {
                if (vis[v]) continue;
                //两种情况：1.当前v的级别应该大于s的级别，但是级别差距控制在k以内
                //2.相同级别时，按从小到大开始遍历
                if (level[v] > level[s] && level[v] - level[s] <= k
                        || level[v] == level[s] && v > s) {
                    res = res * (1 + dfs(v, s)) % MOD;
                }
            }
            return res;
        }


    }

    //小团的神秘暗号
    static class _6th {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            String T = sc.next();
            char[] chas = T.toCharArray();
            int l = 0, r = chas.length - 1;
            for (; l < chas.length; l++) {//头部出现过'M'
                if (chas[l] == 'M') {
                    l++;
                    break;
                }
            }
            for (; r >= 0; r--) {//尾部出现过'T'
                if (chas[r] == 'T') {
                    r--;
                    break;
                }
            }
            //当[l] ='T'和 [r] ='M' 同时成立时，while循环结束
            while (!((chas[l] == 'T') && (chas[r] == 'M'))) {
                if (chas[l] != 'T') l++;
                if (chas[r] != 'M') r--;
            }
            System.out.println(T.substring(l + 1, r));
        }
    }

    //小团的选调计划
    static class _7th {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine().trim());
            boolean[] vis = new boolean[n + 1];
            for (int i = 0; i < n; i++) {
                String[] choices = br.readLine().split(" ");
                for (int j = 0; j < choices.length; j++) {
                    int cur = Integer.parseInt(choices[j]);
                    if (!vis[cur]) {
                        vis[cur] = true;
                        System.out.printf("%d ", cur);
                        break;
                    }
                }
            }
        }
    }

    //小团无路可逃
    static class _8th {
        public static void main(String[] args) {

        }
    }
}
