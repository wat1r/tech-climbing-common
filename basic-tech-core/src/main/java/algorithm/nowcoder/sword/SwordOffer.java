package algorithm.nowcoder.sword;

import org.omg.SendingContext.RunTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

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
        handler.ReverseList(l1);

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


}
