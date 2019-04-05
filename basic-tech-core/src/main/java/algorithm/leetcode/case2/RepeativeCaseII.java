package algorithm.leetcode.case2;

/**
 * Created by FrankCooper
 * Date 2019/3/30 0:00
 * Description
 */
public class RepeativeCaseII {

    private RepeativeCaseII handler = new RepeativeCaseII();

    /**
     * 19
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = head;
        ListNode fast = head;
        while (n-- > 0) {
            fast = fast.next;
        }
        if (fast == null) {
            return head.next;
        }
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy;
    }

    /**
     * 24
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null && pre.next.next != null) {
            ListNode node1 = pre.next;
            ListNode node2 = pre.next.next;
            ListNode next = node2.next;
            node2.next = node1;
            node1.next = next;
            pre.next = node2;
            pre = node1;
        }
        return dummy.next;
    }

    /**
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next =evenHead;
        return head;
    }


    public static void main(String[] args) {


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
