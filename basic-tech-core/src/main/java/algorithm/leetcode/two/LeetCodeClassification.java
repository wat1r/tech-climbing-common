package algorithm.leetcode.two;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 分类刷LeetCode
 * Created by FrankCooper
 * Date 2019/2/28 20:32
 * Description
 */
public class LeetCodeClassification {

    public static LeetCodeClassification handler = new LeetCodeClassification();

    /*----------------分类刷题--------------*/

    /*---------------二叉树开始--------------*/

    /**
     * 144. 二叉树的前序遍历 Medium
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.add(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                resList.add(root.val);
                if (root.right != null) {
                    stack.push(root.right);
                }
                if (root.left != null) {
                    stack.push(root.left);
                }
            }
        }
        return resList;
    }

    /**
     * 94. 二叉树的中序遍历 Medium
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = root;
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    resList.add(root.val);
                    root = root.right;
                }
            }
        }
        return resList;
    }


    /**
     * 145. 二叉树的后序遍历 Hard
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> s1 = new Stack<>();
            Stack<TreeNode> s2 = new Stack<>();
            s1.push(root);
            while (!s1.isEmpty()) {
                root = s1.pop();
                s2.push(root);
                if (root.left != null) {
                    s1.push(root.left);
                }
                if (root.right != null) {
                    s1.push(root.right);
                }
            }
            while (!s2.isEmpty()) {
                resList.add(s2.pop().val);
            }
        }
        return resList;
    }


    /**
     * 102. 二叉树的层次遍历 Medium
     *
     * @param root
     * @return Thoughts:
     * 1. Non-recursive
     * Use queue to withhold the parent.
     * Poll one parent, add this parent’s value to arrayList
     * Add the children into Arraylist
     * jump to next level
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<>();
        if (root == null) {
            return resList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i != size; i++) {
                TreeNode cur = queue.poll();
                levelList.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            resList.add(levelList);
        }
        return resList;
    }


    /**
     * 107. 二叉树的层次遍历 II Easy
     *
     * @param root
     * @return Thoughts:
     * 1. Non-recursive
     * similar to Binary Tree Level Order Traversal I, just when adding into the final result,
     * add to the top all the time. Then the first added will be at the bottom: result.add(0, list)
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<>();
        if (root == null) {
            return resList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i != size; i++) {
                TreeNode cur = queue.poll();
                levelList.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            resList.add(0, levelList);

        }
        return resList;
    }


    /**
     * 889. 根据前序和后序遍历构造二叉树 Medium
     *
     * @param pre
     * @param post
     * @return
     */
    public TreeNode constructFromPrePost(int[] pre, int[] post) {

        return null;
    }


    /**
     * 105. 从前序与中序遍历序列构造二叉树 Medium
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return null;
    }


    /**
     * 106. 从中序与后序遍历序列构造二叉树 Medium
     * https://blog.csdn.net/qq_41855420/article/details/87717203
     *
     * @param inorder
     * @param postorder
     * @return Thinking process:
     * Know that the last element of PostOrder array is the root of the Binary tree.
     * Find this root from the InOrder array. The front-part of the inorder array will be left-tree, the end-part of the inorder array will be the right-tree.
     * Trick part:
     * 1. Need a helper function to perfrom divide/conquer.
     * 2. Need to be careful when cutting the inorder and postorder array.
     * For inorder array, left array: (instart, middlePosition -1), right array: (middlePosition + 1, inend)
     * For postorder array: when cutting, we know the very last node is cut off already, so we just need to evenly split the rest array.
     * left array(postStart, postStart + (middlePosition - instart) - 1).
     * Note: (middlePositon - instart) means the length of the left-array/size of the left-tree
     * However, postStart + left-array-length exceed 1 over postorder-left-tree, hence minus 1 here.
     * right array(postStart + (middlePosition - instart),   postend - 1)
     * Note: postStart + left-tree-length is exactly the starting point of the post-right-array.
     * Because the ending element is cut off previously to serve as root, we need to do (postend - 1) for correct postorder-right-tree.
     */
    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length) {
            return null;
        }
        return buildTreeHelper(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }


    public TreeNode buildTreeHelper(int[] inorder, int inStart, int inEnd
            , int[] postorder, int postStart, int postEnd) {

        if (inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd]);
        int mid = findMid(inorder, inStart, inEnd, postorder[postEnd]);
        root.left = buildTreeHelper(inorder, inStart, mid - 1,
                postorder, postStart, postStart + (mid - inStart) - 1);
        root.right = buildTreeHelper(inorder, mid + 1, inEnd,
                postorder, postStart + (mid - inStart), postEnd - 1);
        return root;
    }


    private int findMid(int[] arr, int start, int end, int postEndValue) {
        for (int i = start; i <= end; i++) {
            if (arr[i] == postEndValue) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 110. 平衡二叉树 I Easy
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        boolean[] arr = new boolean[1];
        arr[0] = true;
        getHeight(root, 1, arr);
        return arr[0];
    }


    private int getHeight(TreeNode head, int level, boolean[] arr) {
        if (head == null) {
            return level;
        }

        int leftHeight = getHeight(head.left, level + 1, arr);
        if (!arr[0]) {
            return level;
        }
        int rightHeight = getHeight(head.right, level + 1, arr);
        if (!arr[0]) {
            return level;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            arr[0] = false;
        }
        return Math.max(leftHeight, rightHeight);
    }

    /**
     * 110. 平衡二叉树 II Easy
     *
     * @param root
     * @return
     */
    public boolean isBalanced1(TreeNode root) {
        if (root != null) {
            // 检查平衡因子
            if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) {
                return false;
            }
            if (!isBalanced1(root.left)) {
                return false;
            }
            return isBalanced1(root.right);
        }
        return true;
    }

    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }






    /*---------------二叉树结束--------------*/
    /*---------------贪心开始--------------*/

    /**
     * 455. 分发饼干 Easy
     *
     * @param g 胃口值
     * @param s 饼干尺寸
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        for (int i = 0, j = 0; i < g.length && j < s.length; ) {
            if (s[j] >= g[i]) {
                res++;
                i++;
            }
            j++;
        }
        return res;
    }

    /**
     * 944. 删列造序 Easy
     *
     * @param A
     * @return
     */
    public int minDeletionSize(String[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < A[0].length(); i++) {
            for (int j = 0; j < A.length - 1; j++) {
                if (A[j].charAt(i) > A[j + 1].charAt(i)) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }


    /**
     * 860. 柠檬水找零 Easy
     *
     * @param bills
     * @return 首先设置两个变量分别表示5元钱的个数和10元钱的个数。然后开始逐个读取输入值：
     * 1.如果顾客给的是5元钱，将5元钱个数+1。然后换下一位顾客 2.顾客如果给的是大于5元钱的话，
     * 判断如果是10元，10元变量+1。while循环开始拿手中的钱减去顾客给的钱，如果顾客给的是20元，
     * 而且我们手中有10元的就优先找零10元的，
     * 否则找5元的，如果顾客给的钱直到我们手中已经没有5元和10元时还没减到零则返回false
     */
    public boolean lemonadeChange(int[] bills) {
        int cnt1 = 0, cnt2 = 0;
        for (int i = 0; i != bills.length; i++) {
            if (bills[i] == 5) {
                cnt1++;
            } else if (bills[i] == 10 && cnt1 > 0) {
                cnt1--;
                cnt2++;
            } else if (bills[i] == 20 && cnt1 > 0 && cnt2 > 0) {
                cnt1--;
                cnt2--;
            } else if (bills[i] == 20 && cnt1 > 2) {
                cnt1 -= 3;
            } else {
                return false;
            }
        }
        return true;
    }


    /**
     * 714. 买卖股票的最佳时机含手续费 Medium
     *
     * @param prices
     * @param fee
     * @return dp思路：dp[0]代表手上没有股票 dp[1]代表手上有股票
     * dp[0]可能就是i之前就没有股票或者在i天卖出了股票。
     * dp[1]可能就是i之前就有股票或者在i天买进了股票。
     * 转移方程就是:
     * dp[0]=max(dp[0],dp[1]+prices[i]-fee);
     * dp[1]=max(dp[1],dp[0]-prices[i]);
     */
    public int maxProfit(int[] prices, int fee) {
        if (prices == null | prices.length == 0) {
            return 0;
        }
        int[] dp = new int[2];
        dp[1] = -prices[0];
        for (int i = 0; i != prices.length; i++) {
            dp[0] = Math.max(dp[0], dp[1] + prices[i] - fee);
            dp[1] = Math.max(dp[1], dp[0] - prices[i]);
        }
        return Math.max(dp[0], dp[1]);
    }

    /*---------------贪心结束--------------*/
    /*---------------动态规划开始--------------*/

    /**
     * 121. 买卖股票的最佳时机 Easy
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i != prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (maxProfit < prices[i] - minPrice) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }


    /**
     * 746. 使用最小花费爬楼梯 Easy
     *
     * @param cost
     * @return dp[i]表示走到第i个阶梯最小花费体力值
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = Math.min(cost[1], cost[0] + cost[1]);
        for (int i = 2; i != cost.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        //最后一步是由上一步和上上一步跳过来的，谁耗费体力小返回谁
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }


    /**
     * 338. 比特位计数 Medium
     *
     * @param num
     * @return dp[i] 第i个数的包含1的个数
     * dp[i >> 1] i右移1位的数比如4（0100）>>1  -->2(0010)并不是物理意义上的前面数字3
     * 如果i这个数的二进制数带1的小尾巴，就加上1，其实就是奇偶数
     */
    public int[] countBits(int num) {
        if (num < 0) {
            return null;
        }
        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            int preNum = i >> 1;
            dp[i] = dp[preNum] + (i % 2);
        }
        return dp;
    }


    /**
     * 983. 最低票价 Medium
     *
     * @param days
     * @param costs
     * @return
     */
    public int mincostTickets(int[] days, int[] costs) {

        return 0;
    }


    /**
     * 64. 最小路径和 Medium
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length <= 0) {
            return 0;
        }
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < grid[0].length; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }


    /**
     * @param triangle
     * @return #### DP
     * - 跟dfs的原理很像, `OR 原理: min(pathA, pathB) + currNode`
     * - init dp[n-1][j] = node values
     * - build from bottom -> top: dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
     * - 跟传统的coordinate dp有所不同, inner for loop 是需要计算 j <= i, 原因是triangle的性质.
     * - 空间: dp[n][n]. space: O(n^2)
     * - 时间:O(n^2). Visit all nodes once: 1 + 2 + 3 + .... n = n^2
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        //从底部到顶部
        if (triangle == null || triangle.size() < 0) {
            return 0;
        }
        int size = triangle.size();
        int[][] dp = new int[size][size];
        //初始化最后一行
        for (int j = 0; j < size; j++) {
            dp[size - 1][j] = triangle.get(size - 1).get(j);
        }
        //i从倒数第二行开始，j上限是i，i行表示有i个数，等于[i][j]的左下方有右下方的最小数加上其自身
        for (int i = size - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }


    /**
     * 931. 下降路径最小和 Medium
     *
     * @param matrix
     * @return
     */
    public int minFallingPathSum(int[][] matrix) {
        if (matrix == null || matrix.length < 0) {
            return 0;
        }
        int[][] dp = new int[matrix.length][matrix[0].length];
        //初始化第一行
        for (int j = 0; j < matrix[0].length; j++) {
            dp[0][j] = matrix[0][j];
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + 1]) + matrix[i][j];
                } else if (j == matrix[0].length - 1) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + matrix[i][j];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]),
                            dp[i - 1][j + 1]) + matrix[i][j];
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < matrix[0].length; j++) {
            res = Math.min(res, dp[matrix.length - 1][j]);
        }
        return res;
    }


    /**
     * 62. 不同路径
     *
     * @param m
     * @param n
     * @return dp[i][j] 表示到达i，j的走法个数，其等于来自上面下来的和左边来的总和
     * 第一行和第一列的走法只有一种，一直右，一直下，都是1种走法
     * <p>
     * Thoughts:
     * 'how many ways' -> Could do DFS, but try DP
     * Robot moves: (0, 1) or (1, 0)
     * gird[x][y]: #paths to reach x,y.
     * There are only 2 ways for getting to (x, y): from (x-1, y) or (x, y-1)
     * Then, the sub problem is grid[x-1,y], and grid[x, y-1].
     * grid[x][y] = Math.min(grid[x-1,y], grid[x, y-1]) + 1;
     * Boundary: when x = 0, grid[0, 0~y] = 0~y; same for y=0, grid[0~x, 0] = 0~x;
     * Path: should go from y++ and y=0, because when we advance +1 row, we'd use previous x/y, which should be calculated already.
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * 63. 不同路径 II
     *
     * @param obstacleGrid
     * @return Thoughts:
     * Last right-bottom corner is always filled by left + up: dp[i][j] = dp[i - 1][j] + dp[i][j - 1].
     * Whenever there is 1, mark the position with 0 ways, because it can get pass through.
     * init: if row has block, all the rest of the row remains 0. If column has a block, the rest of the column remains 0.
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        //初始化第一列
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }
        //初始化第一行
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                break;
            }
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 152. 乘积最大子序列
     *
     * @param nums
     * @return Thoughts:
     * 'Largest', DP.
     * Consider positivie/Negative numbers.
     * f[x] = largest continuous product at index x.
     * NOTE: it's not entire array's largest, need a stand-along variable to hold global max.
     * if nums[x] < 0, want (min of f[x-1]) * nums[x]
     * if nums[x] > 0, want (max of f[x-1]) * nums[x]
     * Consider two different arrays.
     * f[x] = Math.max( min(f[x-1]) * nums[x] if nums[x]<0, or max(f[x-1])*nums[x] if nums[x]>0)
     * initial condition:
     * x = 0 -> nums[0]
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int m = nums.length;
        int[] maxArr = new int[m];
        int[] minArr = new int[m];
        maxArr[0] = nums[0];
        minArr[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < m; i++) {
            if (nums[i] > 0) {
                maxArr[i] = Math.max(nums[i], maxArr[i - 1] * nums[i]);
                minArr[i] = Math.min(nums[i], minArr[i - 1] * nums[i]);
            } else {
                maxArr[i] = Math.max(nums[i], minArr[i - 1] * nums[i]);
                minArr[i] = Math.min(nums[i], maxArr[i - 1] * nums[i]);
            }
            res = Math.max(res, maxArr[i]);
        }
        return res;
    }


    /**
     * 174. 地下城游戏
     *
     * @param dungeon
     * @return
     * dp[i][j]是骑士在[i][j]位置具有在的最小体力值，最小值是1，小于1即挂了
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length, col = dungeon[0].length;
        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = Math.max(1, 1 - dungeon[row - 1][col - 1]);
        //初始化最后一列
        for (int i = row - 2; i >= 0; i--) {
            dp[i][col - 1] = Math.max(1, dp[i + 1][col - 1] - dungeon[i][col - 1]);
        }
        //初始化最后一行
        for (int j = col - 2; j >= 0; j--) {
            dp[row - 1][j] = Math.max(1, dp[row - 1][j + 1] - dungeon[row - 1][j]);
        }
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }


    /*---------------动态规划结束--------------*/
    /*---------------动态规划结束--------------*/


    public static void main(String[] args) {
//        int[] g = new int[]{1, 2, 3};
//        int[] s = new int[]{1, 1};
//        System.out.println(handler.findContentChildren(g, s));
//        String[] A = {"cba", "daf", "ghi"};
//        handler.minDeletionSize(A);
//        int[] arr = {5, 5, 5, 10, 20};
//        System.out.println(handler.lemonadeChange(arr));
//        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
//        System.out.println(handler.minCostClimbingStairs(cost));
////        System.out.println(JSON.toJSON(handler.countBits(8)));
//        int[][] grid = {
//                {1, 3, 1, 2},
//                {1, 5, 1, 3},
//                {4, 2, 1, 4}
//        };
//
//        System.out.println(handler.minPathSum(grid));
//        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        System.out.println(handler.minFallingPathSum(matrix));
//        int[] nums = {2, 3, -2, 4};
//        System.out.println(handler.maxProduct(nums));
        int[][] g = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        System.out.println(handler.calculateMinimumHP(g));
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}