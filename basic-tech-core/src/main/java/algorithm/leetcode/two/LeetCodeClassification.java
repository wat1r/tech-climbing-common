package algorithm.leetcode.two;

import basic.callback.one.Li;

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
    private Map<Integer, Integer> hashmap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            hashmap.put(inorder[i], i);
        }
        return buildTreeDFS(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }


    private TreeNode buildTreeDFS(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        //root结点的中序数组的下标
        int mid = hashmap.get(preorder[preStart]);
        if (mid < 0) {
            return null;
        }
        root.left = buildTreeDFS(preorder, preStart + 1, preStart + (mid - inStart), inorder, inStart, mid - 1);
        root.right = buildTreeDFS(preorder, preStart + (mid - inStart) + 1, preEnd, inorder, mid + 1, inEnd);
        return root;
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
     * @return dp[i][j]是骑士在[i][j]位置具有在的最小体力值，最小值是1，小于1即挂了
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


    /**
     * 322. 零钱兑换
     *
     * @param coins
     * @param amount
     * @return #### DP
     * - 找对方程dp[x], 积累到amount x最少用多少个coin: #coin是value, index是 [0~x].
     * - 子问题的关系是: 如果用了一个coin, 那么就应该是f[x - coinValue]那个位置的#coins + 1
     * <p>
     * ##### initialization
     * - 处理边界, 一开始0index的时候, 用value0.
     * - 中间利用Integer.MAX_VALUE来作比较, initialize dp[x]
     * - 注意, 一旦 Integer.MAX_VALUE + 1 就会变成负数. 这种情况会在coin=0的时候发生.
     * <p>
     * ##### Optimization
     * - 方法1: 直接用Integer.MAX_VALUE
     * - 方法2: 用-1, 稍微简洁一点, 每次比较dp[i]和 dp[i - coin] + 1, 然后save. 不必要做多次min比较.
     * <p>
     * #### Memoization
     * - dp[i] 依然表示: min # of coints to make amount i
     * - initialize dp[i] = Integer.MAX_VALUE
     * - 先选最后一步(遍历coins),  然后dfs做同样的操作
     * - 记录dp[amount] 如果已经给过value, 不要重复计算, 直接return.
     * - 但是这道题没必要强行做memoization, 普通DP的状态和方程相对来说很好找到
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                //遍历coins的，每次拿最小的拼装硬币
                if (i >= coin && dp[i - coin] != -1 && coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
            if (dp[i] == Integer.MAX_VALUE) {
                dp[i] = -1;
            }
        }
        return dp[amount];
    }


    /**
     * 300. 最长上升子序列
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 300. 最长上升子序列
     *
     * @param nums
     * @return
     */
    public int lengthOfLISII(int[] nums) {
        int[] dp = new int[nums.length + 1];
        int maxL = 0;
//dp[i]记录的是在当下每个num 进行插入以后更新的最后一版本
//记录，例如2341234这组数列，当读取234时，则dp的前三位是234，但当读取
//到1时，则dp变成134，2被替代，当再读取2、3时，则2替代3,3替代4，然后读取到4时
//则最大值进行更新，更新后maxL进行更新。
//这种更新dp的算法，能实时保证dp[i]都是遍历到当前（类似照片）的最小值，这样在做更新的时候
//高版本（序号靠后）的序列总能覆盖掉低版本的值，如果出现最大值，则最大值比之前的最高值高的，总比当前的最高值高。
        for (int num : nums) {
            int lo = 0, hi = maxL;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (dp[mid] < num) lo = mid + 1;
                else hi = mid;
            }
            dp[lo] = num;
            if (lo == maxL)
                maxL++;

        }
        return maxL;
    }

    /**
     * 279. 完全平方数 Medium BFS与DP均可
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        List<Integer> squares = generateSquares(n);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] marked = new boolean[n + 1];
        queue.offer(n);
        marked[n] = true;
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            while (size-- > 0) {
                int cur = queue.poll();
                for (int square : squares) {
                    int remain = cur - square;
                    if (remain < 0) {
                        break;
                    }
                    if (remain == 0) {
                        return level;
                    }
                    if (marked[remain]) {
                        continue;
                    }
                    marked[remain] = true;
                    queue.add(remain);
                }
            }
        }
        return -1;
    }


    private List<Integer> generateSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        int base = (int) Math.sqrt(n);
        for (int i = 1; i <= base; i++) {
            squares.add((int) Math.pow(i, 2));
        }
        return squares;
    }


    /**
     * 127. 单词接龙 Medium
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || !wordList.contains(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        HashSet<String> words = new HashSet<>(wordList);
        queue.offer(beginWord);
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            while (size-- > 0) {
                String curWord = queue.poll();
                List<String> candidates = transform(words, curWord);
                for (String candidate : candidates) {
                    if (candidate.equals(endWord)) {
                        return count;
                    }
                    queue.offer(candidate);
                }
            }
        }
        return 0;
    }

    private List<String> transform(Set<String> words, String word) {
        List<String> candidates = new ArrayList<>();
        StringBuffer sb = new StringBuffer(word);
        for (int i = 0; i < sb.length(); i++) {
            char cur = sb.charAt(i);
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == cur) {
                    continue;
                }
                sb.setCharAt(i, c);
                String tempWord = sb.toString();
                if (words.remove(tempWord)) {
                    candidates.add(tempWord);
                }
            }
            sb.setCharAt(i, cur);
        }
        return candidates;
    }


    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int m, n;

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != '0') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        for (int[] direction : directions) {
            dfs(grid, i + direction[0], j + direction[1]);
        }
    }


    /**
     * 130. 被围绕的区域 Medium
     *
     * @param board
     */

    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    int row, col;

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        row = board.length;
        col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    dfs130(board, i, j);
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'M') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs130(char[][] board, int x, int y) {
        if (x < 0 || x >= row || y < 0 || y >= col || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'M';
        for (int i = 0; i < dx.length; i++) {
            dfs130(board, x + dx[i], y + dy[i]);
        }

    }


    /**
     * 695. 岛屿的最大面积
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        row = grid.length;
        col = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                maxArea = Math.max(maxArea, dfs695(grid, i, j));
            }
        }
        return maxArea;
    }

    /**
     * 计算面积，即个数,注意area的起始值是1
     *
     * @param grid
     * @param x
     * @param y
     * @return
     */
    private int dfs695(int[][] grid, int x, int y) {
        if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] == 0) {
            return 0;
        }
        int area = 1;
        grid[x][y] = 0;
        for (int i = 0; i < dx.length; i++) {
            area += dfs695(grid, x + dx[i], y + dy[i]);
        }
        return area;
    }


    /**
     * 547. 朋友圈 Medium
     *
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        int count = 0;
        int n = M.length;
        //使用一个visited数组, 依次判断每个节点, 如果其未访问, 朋友圈数加1并对该节点进行dfs搜索标记所有访问到的节点
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs547(M, i, visited);
                count++;
            }
        }
        return count;
    }

    private void dfs547(int[][] M, int i, boolean[] hasVisited) {
        hasVisited[i] = true;
        for (int j = 0; j < M[0].length; j++) {
            if (M[i][j] == 1 && !hasVisited[j]) {
                dfs547(M, j, hasVisited);
            }
        }
    }

    /**
     * 77. 组合 Medium
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 0 || k <= 0) {
            return result;
        }
        dfs(result, new ArrayList<Integer>(), 1, n, k);
        return result;
    }

    /**
     * @param result 收集结果的result
     * @param list   每一轮for loop的list
     * @param index  下一个数 ，比如当前从2 开始，当做完2 后，加上做3
     * @param n      最大值n
     * @param k      k个数
     */
    private void dfs(List<List<Integer>> result, ArrayList<Integer> list, int index, int n, int k) {
        for (int i = index; i <= n; i++) {
            list.add(i);
            if (list.size() == k) {
                result.add(new ArrayList<>(list));
                list.remove(list.size() - 1);
                continue;
            }
            dfs(result, list, i + 1, n, k);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 46. 全排列 Medium
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        permuteDFS(result, new ArrayList<Integer>(), nums);
        return result;
    }

    private void permuteDFS(List<List<Integer>> result, ArrayList<Integer> levelList, int[] nums) {
        if (levelList.size() == nums.length) {
            result.add(new ArrayList<>(levelList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (levelList.contains(nums[i])) {
                continue;
            }
            levelList.add(nums[i]);
            permuteDFS(result, levelList, nums);
            levelList.remove(levelList.size() - 1);
        }
    }


    /**
     * 39. 组合总和 Medium
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        combinationSumDFS(result, new ArrayList<Integer>(), 0, candidates, target);
        return result;
    }

    private void combinationSumDFS(List<List<Integer>> result, ArrayList<Integer> levelList, int index, int[] candidates, int target) {
        //当前的遍历只会在当前后面进行，如{2, 3, 6, 7} 2 扫完 [2,2,3],当扫到3时，不会出现[3,2,2]的重复情况，从 3 3 开始扫
        for (int i = index; i < candidates.length; i++) {
            int cur = candidates[i];
            levelList.add(cur);
            int remain = target - cur;//目标值减去当前值会后剩下的值
            if (remain == 0) {
                result.add(new ArrayList<>(levelList));
            } else if (remain > 0) {
                combinationSumDFS(result, levelList, i, candidates, remain);
            }
            levelList.remove(levelList.size() - 1);
        }
    }


    /**
     * 40. 组合总和 II Medium
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        combinationSum2DFS(result, new ArrayList<Integer>(), 0, candidates, target);
        return result;
    }

    private void combinationSum2DFS(List<List<Integer>> result, ArrayList<Integer> levelList, int index, int[] candidates, int target) {
        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            int cur = candidates[i];
            levelList.add(cur);
            int remain = target - cur;
            if (remain == 0) {
                result.add(new ArrayList<>(levelList));
            } else if (remain > 0) {
                combinationSum2DFS(result, levelList, i + 1, candidates, remain);
            }
            levelList.remove(levelList.size() - 1);
        }

    }


    /**
     * 47. 全排列 II Medium
     *
     * @param nums
     * @return
     */
    boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        visited = new boolean[nums.length];
        permuteUniqueDFS(result, new ArrayList<Integer>(), nums);
        return result;
    }

    private void permuteUniqueDFS(List<List<Integer>> result, ArrayList<Integer> levelList, int[] nums) {
        if (levelList.size() == nums.length) {
            result.add(new ArrayList<>(levelList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (i - 1 >= 0 && visited[i - 1] && nums[i] == nums[i - 1])) {
                continue;
            }
            visited[i] = true;
            levelList.add(nums[i]);
            permuteUniqueDFS(result, levelList, nums);
            visited[i] = false;
            levelList.remove(levelList.size() - 1);
        }
    }


    /**
     * 78. 子集 Medium
     * 采用的dfs的loop ，还有位操作的解法
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        List<Integer> levelList = new ArrayList<>();
        result.add(new ArrayList<>(levelList));
        subsetsDFS(result, levelList, 0, nums);
        return result;
    }

    private void subsetsDFS(List<List<Integer>> result, List<Integer> levelList, int index, int[] nums) {
        for (int i = index; i < nums.length; i++) {
            // [1] -> [1,2]
            levelList.add(nums[i]);
            result.add(new ArrayList<>(levelList));
            // 寻找所有以 [1,2] 开头的集合，并扔到 results
            subsetsDFS(result, levelList, i + 1, nums);
            // [1,2] -> [1]  回溯
            levelList.remove(levelList.size() - 1);
        }
    }

    /**
     * 90. 子集 II Medium
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        List<Integer> levelList = new ArrayList<>();
        result.add(new ArrayList<>(levelList));
        subsetsWithDupDFS(result, levelList, 0, nums);
        return result;


    }

    private void subsetsWithDupDFS(List<List<Integer>> result, List<Integer> levelList, int depth, int[] nums) {
        for (int i = depth; i < nums.length; i++) {
            if (i > depth && nums[i - 1] == nums[i]) {
                continue;
            }
            levelList.add(nums[i]);
            result.add(new ArrayList<>(levelList));
            subsetsWithDupDFS(result, levelList, i + 1, nums);
            levelList.remove(levelList.size() - 1);
        }

    }


    /**
     * 93. 复原IP地址 Medium
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {


        return null;
    }


    private boolean isValidIP(String str) {
        if (str.charAt(0) == '0') {
            return str.equals("0");
        }
        int num = Integer.valueOf(str);
        return num <= 255 && num >= 0;
    }


    /**
     * 468. 验证IP地址 Medium
     *
     * @param IP
     * @return
     */
    public String validIPAddress(String IP) {


        return null;
    }


    /**
     * 216. 组合总和 III
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (k <= 0 || n <= 0) {
            return result;
        }
        combinationSum3DFS(result, new ArrayList<Integer>(), 1, k, n);
        return result;
    }

    private void combinationSum3DFS(List<List<Integer>> result, ArrayList<Integer> levelList, int index, int k, int n) {

        for (int i = index; i <= 9; i++) {
            levelList.add(i);
            if (n == i && levelList.size() == k) {
                result.add(new ArrayList<>(levelList));
            } else if (n > i) {
                combinationSum3DFS(result, levelList, i + 1, k, n - i);
            }
            levelList.remove(levelList.size() - 1);
        }
    }


    /**
     * 123. 买卖股票的最佳时机 III Hard
     *
     * @param prices
     * @return
     */
    public int maxProfitIII(int[] prices) {


        return 0;
    }


    /**
     * 239. 滑动窗口最大值 Hard
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int n = nums.length;
        int[] resultArr = new int[n - k + 1];
        LinkedList<Integer> deque = new LinkedList<>();
        int index = 0;
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
            if ((i - k) == deque.peekFirst()) {
                deque.pollFirst();
            }
            if (i >= k - 1) {
                resultArr[index++] = nums[deque.peekFirst()];
            }

        }
        return resultArr;
    }


    /**
     * 204. 计数质数 Easy
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if (n <= 0) {
            return 0;
        }
        boolean[] isPrimes = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrimes[i] = true;
        }
        for (int i = 2; i * i < n; i++) {
            if (!isPrimes[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += i) {
                isPrimes[j] = false;
            }
        }
        int result = 0;
        for (int i = 2; i < n; i++) {
            result += isPrimes[i] ? 1 : 0;
        }
        return result;
    }


    /**
     * 找到n以内质数
     *
     * @param n
     * @return
     */
    public List<Integer> findPrime2(int n) {
        List<Integer> primes = new ArrayList<Integer>();
        primes.add(2);
        for (int i = 3; i <= n; i++) {
            int tmp = (int) Math.sqrt(i) + 1;
            for (int j = 2; j <= tmp; j++) {
                if (i % j == 0) {
                    break;
                }
                if (j == tmp) {
                    primes.add(i);
                }
            }
        }
        return primes;
    }


    public void printPrime(int target) {
        int i = 1;
        while (i <= target) {
            i += 2;
            if (target % i == 0) {
                System.out.println("发现: " + target + " / " + i + " = " + (target / i));
            }
        }
    }


    /**
     * 264. 丑数 II Medium
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        int a = 0, b = 0, c = 0;
        int aFactor = 2, bFactor = 3, cFactor = 5;
        for (int i = 1; i < n; i++) {
            int min = Math.min(Math.min(aFactor, bFactor), cFactor);
            //每一轮的dp值时三个factor的最小值，因为每一轮的factor都在刷新
            //如2 ：4 6 8 10 3: 6 9 12 5: 10 15 20
            dp[i] = min;
            if (aFactor == min) {
                aFactor = dp[++a] * 2;
            }
            if (bFactor == min) {
                bFactor = dp[++b] * 3;
            }
            if (cFactor == min) {
                cFactor = dp[++c] * 5;
            }
        }
        return dp[n - 1];
    }


    /**
     * 230. 二叉搜索树中第K小的元素 Medium 解法1
     */
    int count = 0;
    int value = 0;

    public int kthSmallest(TreeNode root, int k) {
        kthSmallestInOrder(root, k);
        return value;
    }

    private void kthSmallestInOrder(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        kthSmallestInOrder(node.left, k);
        count++;
        if (k == count) {
            value = node.val;
            return;
        }
        kthSmallestInOrder(node.right, k);
    }

    /**
     * 230. 二叉搜索树中第K小的元素 Medium DFS
     *
     * @param root
     * @param k
     * @return
     */
    private List<Integer> values = new ArrayList<>();

    public int kthSmallest2nd(TreeNode root, int k) {
        kthSmallest2ndDFS(root);
        for (int i = 0; i < values.size(); i++) {
            if (i == k - 1) {
                return values.get(i);
            }
        }
        return -1;
    }

    private void kthSmallest2ndDFS(TreeNode node) {
        if (node == null) {
            return;
        }
        kthSmallest2ndDFS(node.left);
        values.add(node.val);
        kthSmallest2ndDFS(node.right);

    }


    /**
     * 347. 前K个高频元素 Medium
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> resList = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return resList;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] bucket = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            if (bucket[count] == null) {
                bucket[count] = new ArrayList<>();
            }
            bucket[count].add(entry.getKey());
        }
        for (int i = bucket.length - 1; i >= 0 && resList.size() < k; i--) {
            if (bucket[i] != null) {
                resList.addAll(bucket[i]);
            }
        }
        return resList;
    }


    /**
     * 60. 第k个排列
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        StringBuffer sb = new StringBuffer();
        int[] factorials = new int[n + 1];
        factorials[0] = 1;
        List<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            candidates.add(i);
            factorials[i] = i * factorials[i - 1];
        }
        k = k - 1;
        for (int i = n - 1; i >= 0; i--) {
            int index = k / factorials[i];
            sb.append(candidates.remove(index));
            k -= factorials[i] * index;
        }

        return sb.toString();
    }


    /**
     * 692. 前K个高频单词 Medium Bucket
     *
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        List<String> resList = new ArrayList<>();
        if (words == null || words.length == 0) {
            return resList;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        List<String>[] bucket = new List[words.length + 1];
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            if (bucket[count] == null) {
                bucket[count] = new ArrayList<>();
            }
            bucket[count].add(entry.getKey());
        }
        for (List<String> list : bucket) {
            if (list != null) {
                Collections.sort(list);
            }
        }
        flag:
        for (int i = bucket.length - 1; i >= 0 && resList.size() < k; i--) {
            if (bucket[i] != null) {
                for (String item : bucket[i]) {
                    if (resList.size() == k) {
                        break flag;
                    }
                    resList.add(item);
                }
            }
        }
        return resList;
    }


    /**
     * 218. 天际线问题 Hard
     *
     * @param buildings
     * @return
     */
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> resList = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length == 0) {
            return resList;
        }
        //定义比较器，先按pos从小到大排序，pos相等，按height从小到大排
        PriorityQueue<Point> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.pos != o2.pos) {
                return o1.pos - o2.pos;
            }
            if (o1.height != o2.height) {
                return o1.height - o2.height;
            }
            return 0;
        });
        //生成queue，<第一个位置，负高度>，<第二个位置，正高度>
        for (int i = 0; i < buildings.length; i++) {
            queue.offer(new Point(buildings[i][0], -buildings[i][2]));
            queue.offer(new Point(buildings[i][1], buildings[i][2]));
        }
        PriorityQueue<Integer> maxHeightQueue = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeightQueue.offer(0);
        int prePeak = maxHeightQueue.peek();
        while (!queue.isEmpty()) {
            //当当前高度是负数，说明是上升的，加到maxHeightQueue，反之移除掉
            Point point = queue.poll();
            if (point.height < 0) {
                maxHeightQueue.offer(-point.height);
            } else {
                maxHeightQueue.remove(point.height);
            }
            int curPeak = maxHeightQueue.peek();
            if (curPeak != prePeak) {
                resList.add(new int[]{point.pos, curPeak});
                prePeak = curPeak;
            }
        }
        return resList;
    }

    /**
     * 215. 数组中的第K个最大元素 Medium
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k || nums[i] > queue.peek()) {
                queue.offer(nums[i]);
            }
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }


    /**
     * 309. 最佳买卖股票时机含冷冻期
     *
     * @param prices
     * @return
     */
    public int maxProfitI(int[] prices) {

        return 0;
    }

    /*---------------动态规划结束--------------*/
    /*---------------动态规划结束--------------*/


    /**
     * 编程之法1.5 举一反三 链表回文
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        ListNode right = reverseList(mid);
        ListNode left = head;
        while (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }


    /**
     * 返回翻转后的链表的最开始的node
     *
     * @param head
     * @return
     */
    private ListNode reverseList(ListNode head) {
        ListNode current = head, next, pre = null;
        while (current != null) {
            // 记录后继结点
            next = current.next;
            // 后继指针逆向
            current.next = pre;
            // 记录当前结点
            pre = current;
            // 下一结点成为当前结点
            current = next;
        }
        return pre;
    }


    /*
    栈回文 编程之法1.5 举一反三
     */
    public boolean isPalindrome(String str) {
        if (str == null) {
            return true;
        }
        char[] chas = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chas.length; i++) {
            stack.push(chas[i]);
        }
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] != stack.pop()) {
                return false;
            }
        }
        return true;
    }


    /**
     * 409. 最长回文串 Easy [LeetCode]
     *
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {

        char[] chas = s.toCharArray();
        int[] count = new int[128];
        for (int i = 0; i < chas.length; i++) {
            count[chas[i]]++;
        }
        int result = 0;
        for (int i = 0; i < count.length; i++) {
            //当count中的元素是大于等于2个时，result等于其小于count[i]的最大的偶数
            result += count[i] / 2 * 2;
            //当count[i]为奇数时，且result的数量为偶数时，这个数可以放在中间组成回文的middle
            if (count[i] % 2 == 1 && result % 2 == 0) {
                result++;
            }
        }
        return result;
    }


    /**
     * 409. 最长回文串 Easy [LeetCode]
     *
     * @param s
     * @return
     */
    public int longestPalindrome2nd(String s) {
        char[] chas = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chas.length; i++) {
            map.put(chas[i], map.getOrDefault(chas[i], 0) + 1);
        }
        int result = 0;
        for (int cnt : map.values()) {
            result += cnt / 2 * 2;
            if (cnt % 2 == 1 && result % 2 == 0) {
                result++;
            }
        }
        return result;
    }


    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int result = 0;
        boolean[][] dp = buildDPForCountSubstrings(s);
        for (int j = 0; j < dp.length; j++) {
            for (int i = 0; i <= j; i++) {
                if (dp[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }


    private boolean[][] buildDPForCountSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        //注意i 和j 的边界，只计算上半部分，j - i <= 1是为了处理边界，dp[i + 1][j - 1]是dp[i][j]砍头去尾后的是否是回文
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (i == j) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1]);
                }
            }
        }
        return dp;
    }


    /**
     * @param s
     * @return
     */
    public int countSubstrings2nd(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            //以当前点i位置，向两边扩展,以i i+1位置向两边扩展
            result += countSegment(s, i, i);
            result += countSegment(s, i, i + 1);
        }
        return result;
    }


    public int countSegment(String s, int start, int end) {
        int count = 0;
        //start往左边跑，end往右边跑，注意边界
        while (start >= 0 && end < s.length() && s.charAt(start--) == s.charAt(end++)) {
            count++;
        }
        return count;
    }


    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        char[] chas = s.toCharArray();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = chas[i] == chas[i + 1] ? 2 : 1;
        }
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = len + i - 1;
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                if (chas[i] == chas[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                }
            }
        }
        return dp[0][n - 1];
    }


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
//        int[][] g = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
//        System.out.println(handler.calculateMinimumHP(g));

//        int[] coins = {1, 2, 5};
//        System.out.println(handler.coinChange(coins, 11));
//        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
//        System.out.println(handler.lengthOfLIS(nums));

//        handler.generateSquares(13);
//        handler.numSquares(12);

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<String>() {{
            add("hot");
            add("dot");
            add("dog");
            add("lot");
            add("log");
            add("cog");
        }};

//        handler.transform(new HashSet<>(wordList), beginWord);


//        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
//        handler.solve(board);
//        int[][] grid = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
//        handler.maxAreaOfIsland(grid);
//        int[][] M = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
//        handler.findCircleNum(M);

//        int n = 4, k = 2;
//        handler.combine(n, k);


//        handler.combinationSum(new int[]{2, 3, 6, 7}, 7);

//        int[] nums = {1, 2, 3};
//        handler.subsets(nums);

//        handler.subsetsWithDup(new int[]{1, 2, 2});
//        handler.combinationSum3(3, 9);
//        handler.countPrimes(100);
//        handler.findPrime2(100);
//        handler.printPrime(707829217);

//        handler.nthUglyNumber(10);
//        handler.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
//        handler.getPermutation(4, 15);
//        handler.topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2);
//        handler.getSkyline(new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}});
//        handler.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);
//        handler.isPalindrome("abcdcba");
//        handler.buildDPForCountSubstrings("abcba");
        handler.countSubstrings2nd("abc");


    }
}

class Point {
    int pos, height;

    public Point(int pos, int height) {
        this.pos = pos;
        this.height = height;
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


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
