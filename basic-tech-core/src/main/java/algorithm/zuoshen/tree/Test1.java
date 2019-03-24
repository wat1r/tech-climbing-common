package algorithm.zuoshen.tree;

/**
 * Created by FrankCooper
 * Date 2019/3/23 10:57
 * Description
 */
public class Test1 {

    public static class Node {
        int val;
        Node left;
        Node right;

        Node(int x) {
            val = x;
        }
    }

    public static class ReturnData {
        public boolean isBalance;
        public int h;

        public ReturnData(boolean isBalance, int h) {
            this.isBalance = isBalance;
            this.h = h;
        }
    }


    public static ReturnData process(Node head) {
        if (head == null) {
            return new ReturnData(true, 0);
        }
        ReturnData leftData = process(head.left);
        if (!leftData.isBalance) {
            return new ReturnData(false, 0);
        }
        ReturnData rightData = process(head.right);
        if (!rightData.isBalance) {
            return new ReturnData(false, 0);
        }
        if (Math.abs(leftData.h - rightData.h) > 1) {
            return new ReturnData(false, 0);
        }
        return new ReturnData(true, Math.max(leftData.h, rightData.h) + 1);
    }

    /**
     * 左神 判断一棵树是否是平衡二叉树
     * @param head
     * @return
     */
    public static boolean isBalanceTree(Node head) {
        return process(head).isBalance;
    }
}



