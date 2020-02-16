package algorithm.leetcode.case1;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/2/11 18:03
 * Description
 */
public class RepeativeCaseIII {

    static RepeativeCaseIII handler = new RepeativeCaseIII();

    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    res += 4;
                    if (i > 0 && grid[i - 1][j] == 1) res -= 2;
                    if (j > 0 && grid[i][j - 1] == 1) res -= 2;
                }
            }
        }
        return res;
    }

    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};//对应了上右下左四个方向
    int rows, cols;
    boolean[][] visited;

    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        rows = grid.length;
        cols = grid[0].length;
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    count += dfsA(grid, i, j);
                }
            }
        }
        return count;
    }

    private int dfsA(int[][] grid, int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= cols) {
            return 0;
        }
        if (grid[x][y] == 1) return 1;
        int res = 1;
        grid[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int nextX = x + directions[i][0];
            int nextY = y + directions[i][1];
            res = Math.min(res, dfsA(grid, nextX, nextY));
        }
        return res;
    }


    public int numberofDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        rows = grid.length;
        cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder path = new StringBuilder();
                    dfs(grid, i, j, path, i, j);
                    set.add(path.toString());
                }
            }
        }
        return set.size();
    }

    /**
     * @param grid  原始的格子
     * @param x     当前的位置 x
     * @param y     当前的位置 y
     * @param path  走过的轨迹
     * @param baseX 这一层dfs开始时是基于哪个x开始的
     * @param baseY 这一层dfs开始时是基于哪个y开始的
     */
    private void dfs(int[][] grid, int x, int y, StringBuilder path, int baseX, int baseY) {
        if (x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] == 0) {
            return;
        }
        grid[x][y] = 0;
        path.append(x - baseX).append(y - baseY);
        for (int i = 0; i < 4; i++) {
            int nextX = x + directions[i][0];
            int nextY = y + directions[i][1];
            dfs(grid, nextX, nextY, path, baseX, baseY);
        }
    }


    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] && !visited[i][j]) {
                    count++;
                    dfs(grid, i, j);
                }

            }
        }

        return count;
    }

    private void dfs(boolean[][] grid, int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nextX = x + directions[i][0];
            int nextY = y + directions[i][1];
            boolean inArea = nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols;
            if (inArea && grid[nextX][nextY] && !visited[nextX][nextY]) {
                dfs(grid, nextX, nextY);
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        dfs(nums, results, new ArrayList<Integer>());
        return results;
    }

    private void dfs(int[] nums, List<List<Integer>> results, List<Integer> sub) {
        if (sub.size() == nums.length) {
            results.add(new ArrayList<>(sub));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (sub.contains(nums[i])) continue;
            sub.add(nums[i]);
            dfs(nums, results, sub);
            sub.remove(sub.size() - 1);
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        Queue<TreeNode> queue = new LinkedList<>();
        int level = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            results.add(new ArrayList<>());
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                results.get(level).add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            ++level;
        }
        return results;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> resluts = new ArrayList<>();
        if (root == null) return resluts;
        dfs(root, sum, resluts, new ArrayList<Integer>());
        return resluts;
    }

    private void dfs(TreeNode root, int sum, List<List<Integer>> resluts, ArrayList<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) resluts.add(new ArrayList<>(list));
        dfs(root.left, sum - root.val, resluts, list);
        dfs(root.right, sum - root.val, resluts, list);
        list.remove(list.size() - 1);
    }


    public int kthLargest(TreeNode root, int k) {
        if (root == null) return 0;
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }
            cur = stack.pop();
            if (cur != null) {
                list.add(cur.val);
                cur = cur.left;
            }

        }
        return list.get(k - 1);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    public void swap(int[] arr, int m, int n) {
        int temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }

    public int[] exchange(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] % 2 == 1) {
                left++;
                continue;
            }
            if (nums[right] % 2 == 0) {
                right--;
                continue;
            }
            swap(nums, left, right);
        }
        return nums;
    }

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public int maxSubArray(int[] nums) {
        int curSum = nums[0], maxSum = curSum;
        for (int i = 1; i < nums.length; i++) {
            curSum = Math.max(nums[i], curSum + nums[i]);
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }


    class CQueue {

        private Stack<Integer> data;
        private Stack<Integer> help;


        public CQueue() {
            data = new Stack<>();
            help = new Stack<>();
        }

        public void appendTail(int value) {
            help.push(value);
            if (data.isEmpty()) {
                while (!help.isEmpty()) {
                    data.push(help.pop());
                }
            }
        }

        public int deleteHead() {
            if (data.isEmpty() && help.isEmpty()) return -1;
            if (data.isEmpty()) {
                while (!help.isEmpty()) {
                    data.push(help.pop());
                }
            }
            return data.pop();
        }

    }

    class MinStack {
        private Stack<Integer> data;
        private Stack<Integer> help;


        /**
         * initialize your data structure here.
         */
        public MinStack() {
            data = new Stack<>();
            help = new Stack<>();
        }

        public void push(int x) {
            //比help栈顶元素小的元素 收录进来
            if (help.isEmpty() || help.peek() >= x) {
                help.push(x);
            }
            data.push(x);
        }

        public void pop() {
            if (data.isEmpty()) throw new RuntimeException("The stack is empty");
            int pop = data.pop();
            if (pop == help.peek()) help.pop();
        }

        public int top() {
            if (data.isEmpty()) throw new RuntimeException("The stack is empty");
            return data.peek();
        }

        public int min() {
            if (help.isEmpty()) throw new RuntimeException("The stack is empty");
            return help.peek();
        }
    }


    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return dfs(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int mid = map.get(preorder[preStart]);
        if (mid < 0) return null;
        root.left = dfs(preorder, preStart + 1, preStart + (mid - inStart), inorder, inStart, mid - 1);
        root.right = dfs(preorder, preStart + (mid - inStart) + 1, preEnd, inorder, mid + 1, inEnd);
        return root;
    }

    public int fib(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }

    public int numWays(int n) {
        if (n == 0 || n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }

    public int search(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map.get(target) == null ? 0 : map.get(target);
    }


    public int nthUglyNumber(int n) {
        if (n <= 0) return 0;
        int[] dp = new int[n];
        dp[0] = 1;
        int a = 0, b = 0, c = 0;
        int aFactor = 2, bFactor = 3, cFactor = 5;
        for (int i = 1; i < n; i++) {
            int min = Math.min(Math.min(aFactor, bFactor), cFactor);
            dp[i] = min;
            if (min == aFactor) aFactor = dp[++a] * 2;
            if (min == bFactor) bFactor = dp[++b] * 3;
            if (min == cFactor) cFactor = dp[++c] * 5;
        }
        return dp[n - 1];
    }


    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        Set<Character> set = new HashSet<>();
        char[] chas = s.toCharArray();
        int slow = 0, fast = 0;
        int n = chas.length;
        int res = 1;
        while (slow < n && fast < n) {
            if (!set.contains(chas[fast])) {
                res = Math.max(res, fast - slow + 1);
                set.add(chas[fast++]);
            } else {
                set.remove(chas[slow++]);
            }
        }
        return res;
    }

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


    public String[] permutation1st(String s) {
        char[] chas = s.toCharArray();
        Arrays.sort(chas);
        int len = chas.length;
        boolean[] visited = new boolean[len];
        List<String> resultList = new LinkedList<>();
        dfs(chas, 0, visited, resultList, new StringBuilder());
        String[] res = new String[resultList.size()];
        return resultList.toArray(res);
    }

    private void dfs(char[] chas, int index, boolean[] visited, List<String> resultList, StringBuilder sb) {
        if (index == chas.length) {
            resultList.add(sb.toString());
            return;
        }

        for (int i = 0; i < chas.length; i++) {
            if (visited[i] || (i > 0 && visited[i - 1] && chas[i] == chas[i - 1])) {
                continue;
            }
            visited[i] = true;
            sb.append(chas[i]);
            dfs(chas, index + 1, visited, resultList, sb);
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }

    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) return ' ';
        char[] chas = s.toCharArray();
        int[] count = new int[256];
        for (int i = 0; i < chas.length; i++) {
            count[chas[i]]++;
        }
        for (int i = 0; i < chas.length; i++) {
            if (count[chas[i]] == 1) return chas[i];
        }
        return ' ';
    }


    public String reverseLeftWords(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = n; i < s.length(); i++) {
            sb.append(s.charAt(i));
        }
        int index = 0;
        while (n != 0) {
            sb.append(s.charAt(index++));
            n--;
        }
        return sb.toString();
    }

    public String reverseWords(String s) {
        char[] chas = s.trim().toCharArray();
        reverse(chas, 0, chas.length - 1);
        String tmpStr = String.valueOf(chas);
        String[] arr = tmpStr.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            char[] tmpChar = arr[i].toCharArray();
            reverse(tmpChar, 0, tmpChar.length - 1);
            sb.append(String.valueOf(tmpChar));
            if (i != arr.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }


    private void reverse(char[] chas, int from, int to) {
        while (from < to) {
            char tmp = chas[from];
            chas[from++] = chas[to];
            chas[to--] = tmp;
        }
    }


    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] >>> i & 1) == 1) {
                    count++;
                }

            }
            if (count % 3 != 0) {
                res |= 1 << i;
            }
        }
        return res;
    }


    class MedianFinder {

        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;
        int count;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            count = 0;
            maxHeap = new PriorityQueue<>(((o1, o2) -> o2 - o1));
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            count++;
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
            if (count % 2 == 1) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (count % 2 == 1) {
                return (double) maxHeap.peek();
            } else {
                return (double) (maxHeap.peek() + minHeap.peek()) / 2;
            }

        }
    }

    class ProductOfNumbers {

        private List<Integer> list;


        public ProductOfNumbers() {
            list = new ArrayList<>();
        }

        public void add(int num) {
            list.add(num);
        }

        public int getProduct(int k) {
            if (list.isEmpty()) return 0;
            int sum = 1;
            int start = list.size()  - k;
            for (int i = start; i < list.size() ; i++) {
                sum *= list.get(i);
            }
            return sum;
        }
    }




    //spring
    public static void main(String[] args) {
//        handler.fib(48);
//        handler.nthUglyNumber(10);
//        handler.lengthOfLongestSubstring("abcabcbb");
//        handler.permutation("abc");
//        handler.permutation1st("abc");
        handler.reverseWords("the sky is blue");
//        int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
//        handler.maxAreaOfIsland(grid);

        TreeNode t5 = new TreeNode(5);
        TreeNode t3 = new TreeNode(3);
        TreeNode t6 = new TreeNode(6);
        TreeNode t2 = new TreeNode(2);
        TreeNode t4 = new TreeNode(4);
        TreeNode t1 = new TreeNode(1);
        t5.left = t3;
        t5.right = t6;
        t3.left = t2;
        t3.right = t4;
        t2.left = t1;
        handler.kthLargest(t5, 3);


    }
    //winter


}
