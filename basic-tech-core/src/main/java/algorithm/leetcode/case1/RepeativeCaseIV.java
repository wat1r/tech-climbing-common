package algorithm.leetcode.case1;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/2/14 18:48
 * Description
 */
public class RepeativeCaseIV {
    static RepeativeCaseIV handler = new RepeativeCaseIV();

    public String[] permutation(String s) {

        char[] chas = s.toCharArray();
        int n = chas.length;
        Set<String> resultList = new HashSet<>();
        boolean[] visited = new boolean[n];
        dfs(s, visited, resultList, new StringBuilder());
        String[] arr = new String[resultList.size()];
        int index = 0;
        for (String ss : resultList) {
            arr[index++] = ss;
        }
        return arr;
    }

    private void dfs(String s, boolean[] visited, Set<String> resultList, StringBuilder sb) {
        if (sb.length() == s.length()) {
            resultList.add(sb.toString());
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (visited[i]) continue;
            visited[i] = true;
            sb.append(s.charAt(i));
            dfs(s, visited, resultList, sb);
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }

    }


    //spring
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
//        handler.threeSum(nums);
    }
//    winter


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt(), k = sc.nextInt();
            String s = sc.next();
            process(n, k, s);
        }


        private static void process(int n, int k, String s) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            List<Integer> list = new ArrayList<>();
            int res = 0;
            for (int i = 0; i < n; i++) {
                list.add(s.charAt(i) - '0');
                int t = rmod(list, k);
                if (map.containsKey(t)) res += map.get(t);
                map.put(t, map.getOrDefault(t, 0) + 1);
            }
            System.out.println(res);

        }

        //求模的函数
        private static int rmod(List<Integer> list, int k) {
            int res = 0;
            for (int x : list) {
                res = res * 10 + x;
                res %= k;
            }
            return res;
        }

    }


//    int calculateDaysBetweenDates(){
//
//    }


}
