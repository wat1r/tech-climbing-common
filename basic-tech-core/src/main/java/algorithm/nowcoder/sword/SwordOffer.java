package algorithm.nowcoder.sword;

import org.omg.SendingContext.RunTime;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2019/7/14 20:58
 * Description
 */
public class SwordOffer {

    static SwordOffer handler = new SwordOffer();


    public String replaceSpace(StringBuffer str) {
        if (str == null || str.toString() == "") {
            return "";
        }
        char[] chas = str.toString().toCharArray();
        int space = 0;
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] == ' ') {
                space++;
            }
        }

        int dest = chas.length + space * 2;
        char[] res = new char[dest];
        int originalIndex = chas.length - 1;
        int destIndex = dest - 1;
        while (originalIndex < dest && originalIndex >= 0) {
            if (chas[originalIndex] == ' ') {
                res[destIndex--] = '0';
                res[destIndex--] = '2';
                res[destIndex--] = '%';
            } else {
                res[destIndex--] = chas[originalIndex];
            }
            --originalIndex;
        }
        return String.valueOf(res);
    }


    public int NumberOf1(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n &= n - 1;
        }
        return res;
    }


    public int Fibonacci(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    public int JumpFloor(int target) {
        int[] dp = new int[target + 1];
        dp[0] = 0;
        dp[1] = 1;
        if (target <= 1) return dp[target];
        dp[2] = 2;
        for (int i = 3; i <= target; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[target];
    }


    public double Power(double base, int exponent) {
        if (exponent == 0) return 1;
        if (exponent == 1) return base;
        double res = Power(base, exponent >> 1);
        res *= res;
        if ((exponent % 2) != 0) {
            res *= base;
        }
        return res;
    }


    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> resList = new ArrayList<>();
        Stack<ListNode> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            resList.add(stack.pop().val);
        }
        return resList;
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null) return null;
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            hashmap.put(in[i], i);
        }
        return preIn(pre, 0, pre.length - 1, in, 0, in.length - 1, hashmap);
    }

    private TreeNode preIn(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd, HashMap<Integer, Integer> hashmap) {
        if (preStart > preEnd) {
            return null;
        }
        TreeNode head = new TreeNode(pre[preStart]);
        Integer index = hashmap.get(pre[preStart]);
        //左子树：前序遍历中起始值为之前的起始值加一，终点值为前序起始值加上（中序的根值i-中序的起始值，即得到左子树个数）
        //	  中序遍历中起始值为之前中序起始值，终点值为中序根结点减一即i-1
        head.left = preIn(pre, preStart + 1, preStart + index - inStart, in, inStart, index - 1, hashmap);
        //右子树：前序遍历中起始值为前序起始值加上左子树个数（i-startIn）再加1，终点值为前序的终点值。
        //	  中序遍历中起始值为中序根结点加一（i+1），终点值为之前中序的终点值
        head.right = preIn(pre, preStart + index - inStart + 1, preEnd, in, index + 1, inEnd, hashmap);
        return head;
    }


    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            throw new RuntimeException("the queue is empty");
        } else if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }


    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k == 0) return null;
        ListNode slow = head, fast = head;
        int i = 0;
        while (i < k - 1) {
            fast = fast.next;
            i++;
            if (fast == null) {
                return null;
            }
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    public ListNode ReverseList(ListNode head) {
        if (head == null) return null;
        ListNode pre = null, cur = head, next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                node.next = list1;
                list1 = list1.next;
            } else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }
        if (list1 != null) {
            node.next = list1;
        }
        if (list2 != null) {
            node.next = list2;
        }
        return dummy.next;
    }


    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        boolean res = false;
        if (root1 != null && root2 != null) {
            if (root1.val == root2.val) res = doesTreeHasSubTree(root1, root2);
            if (!res) res = HasSubtree(root1.left, root2);
            if (!res) res = HasSubtree(root1.right, root2);
        }
        return res;
    }

    private boolean doesTreeHasSubTree(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root1.val != root2.val) return false;
        return doesTreeHasSubTree(root1.left, root2.left) && doesTreeHasSubTree(root1.right, root2.right);
    }

    public void Mirror(TreeNode root) {
        if (root == null) return;
        if (root.right == null && root.left == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        if (root.left != null) Mirror(root.left);
        if (root.right != null) Mirror(root.right);
    }

    ArrayList<Integer> printList = new ArrayList<>();

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        int topRow = 0, topCol = 0;
        int downRow = matrix.length - 1, downCol = matrix[0].length - 1;
        while (topRow <= downRow && topCol <= downCol) {
            printMatrixSegment(matrix, topRow++, topCol++, downRow--, downCol--);
        }
        return printList;
    }

    private void printMatrixSegment(int[][] matrix, int topRow, int topCol, int downRow, int downCol) {
        if (topRow == downRow) {
            while (topCol <= downCol) {
                printList.add(matrix[topRow][topCol]);
                topCol++;
            }
        } else if (topCol == downCol) {
            while (topRow <= downRow) {
                printList.add(matrix[topRow][topCol]);
                topRow++;
            }
        } else {
            int curRow = topRow, curCol = topCol;
            while (curCol < downCol) {
                printList.add(matrix[topRow][curCol]);
                curCol++;
            }
            while (curRow < downRow) {
                printList.add(matrix[curRow][downCol]);
                curRow++;
            }
            while (curCol > topCol) {
                printList.add(matrix[downRow][curCol]);
                curCol--;
            }
            while (curRow > topRow) {
                printList.add(matrix[curRow][topCol]);
                curRow--;
            }
        }
    }


    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA == null || popA == null || pushA.length == 0 || popA.length == 0 || pushA.length != popA.length)
            return false;
        Stack<Integer> stack = new Stack<>();
        int i = 0, j = 0;
        stack.push(pushA[i++]);
        while (j != popA.length) {
            while (popA[j] != stack.peek()) {
                if (i == pushA.length) return false;
                stack.push(pushA[i++]);
            }
            j++;
            stack.pop();
        }
        return true;
    }


    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> results = new ArrayList<>();
        if (root == null) return results;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            results.add(current.val);
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return results;
    }


    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) return false;
        int len = sequence.length;
        int root = sequence[len - 1];
        int i = 0;
        for (; i < len - 1; i++) {
            if (sequence[i] > root) break;
        }
        int j = i;
        for (; j < len - 1; j++) {
            if (sequence[j] < root) return false;
        }
        boolean left = true;
        if (i > 0) left = VerifySquenceOfBST(Arrays.copyOfRange(sequence, 0, i));
        boolean right = true;
        if (i < len - 1) right = VerifySquenceOfBST(Arrays.copyOfRange(sequence, i, len - 1));
        return left && right;
    }

    ArrayList<ArrayList<Integer>> resList = new ArrayList<>();



    public static void main(String[] args) {

//        handler.replaceSpace(new StringBuffer("We Are Happy"));
//        handler.NumberOf1(9);
//        handler.Fibonacci(2);
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = null;
//        handler.FindKthToTail(l1, 5);
//        handler.ReverseList(l1);
//        handler.printMatrix(new int[][]{{1, 3}, {3, 4}});
//        handler.IsPopOrder(new int[]{1}, new int[]{2});

    }
    //winter


    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
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


    class MyMinStack {

        Stack<Integer> dataStack = null;
        Stack<Integer> helpStack = null;

        public MyMinStack() {
            this.dataStack = new Stack<>();
            this.helpStack = new Stack<>();
        }

        public void push(int node) {
            if (helpStack.isEmpty()) {
                helpStack.push(node);
            } else if (node <= this.min()) {
                helpStack.push(node);
            }
            dataStack.push(node);
        }

        public void pop() {
            if (this.dataStack.isEmpty()) {
                throw new RuntimeException("empty stack");
            }
            int value = dataStack.pop();
            if (value == this.min()) {
                helpStack.pop();
            }
        }

        public int top() {
            if (this.dataStack.isEmpty()) {
                throw new RuntimeException("empty stack");
            }
            int value = dataStack.peek();
            return value;
        }

        public int min() {
            if (this.helpStack.isEmpty()) {
                throw new RuntimeException("empty stack");
            }
            return this.helpStack.peek();
        }

    }


}
