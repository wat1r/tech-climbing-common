package algorithm.leetcode.case2;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2019/2/25 23:46
 * Description
 */
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        if (root == null) return resList;
        bfs(root, resList);
        return resList;
    }

    public void bfs(TreeNode root, List<Integer> resList) {
        if (root == null) return;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> depthQueue = new LinkedList<>();
        nodeQueue.add(root);
        depthQueue.add(0);
        while (!nodeQueue.isEmpty()) {
            TreeNode curNode = nodeQueue.poll();
            int curDepth = depthQueue.poll();
            if (depthQueue.isEmpty() || depthQueue.peek() != curDepth) {
                resList.add(curNode.val);
            }
            if (curNode.left != null) {
                nodeQueue.add(curNode.left);
                depthQueue.add(curDepth + 1);
            }
            if (curNode.right != null) {
                nodeQueue.add(curNode.right);
                depthQueue.add(curDepth + 1);
            }
        }


    }


    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t3.left = null;
        t3.right = t4;
        t2.left = null;
        t2.right = t5;

        t4.left = null;
        t4.right = null;
        t5.left = null;
        t5.right = null;

        Solution handler = new Solution();
//        handler.rightSideView(t1);


    }




//    public int fib(int N) {
//        return helper(N);
//    }
//
//    private int helper(int N) {
//        if (N == 0) return 0;
//        if (N == 1) return 1;
//        if (memo.get(N) != null) return memo.get(N);
//        memo.put(N, fib(N - 1) + fib(N - 2));
//        return memo.get(N);
//    }

    Map<Integer, Integer> memo = new HashMap<>();

    public int climbStairs(int n) {
        return helper(0, n);
    }

    private int helper(int i, int n) {
        if (i > n) return 0;
        if (i == n) return 1;
        if(memo.get(i)!=null) return  memo.get(i);
        memo.put(i, helper(i + 1, n) + helper(i + 2, n));
        return memo.get(i);
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