package algorithm.leetcode.case2;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2019/2/25 23:46
 * Description
 */
public class Solution {

    public int[] numsSameConsecDiff(int N, int K) {
        if (N == 1) {
            return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            helper(list, i, 0, K, N, 0);
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public void helper(List<Integer> list, int temp, int count, int K, int N, int ans) {
        ans += temp;
        count++;
        if (count == N) {
            list.add(ans);//长度达到N之后结束递归，将结果添加到答案中，返回上一层；
            return;
        }
        if (temp - K >= 0) {
            helper(list, temp - K, count, K, N, ans * 10);
        }
        if (temp + K < 10 && K != 0) {
            helper(list, temp + K, count, K, N, ans * 10);//第一遍提交未判断 K != 0，导致结果出现重复
        }
    }

    public RepeativeCaseII.ListNode reverseList(RepeativeCaseII.ListNode head) {
        if (head == null || head.next == null) return head;
        RepeativeCaseII.ListNode newNode = reverseList(head.next);
        head.next.next = head;
        head.next=null;
        return newNode;
    }

    public static void main(String[] args) {
        Solution handler = new Solution();
//        handler.numsSameConsecDiff(3,7);
        



    }

}

