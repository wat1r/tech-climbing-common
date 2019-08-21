package algorithm.leetcode.case2;

import java.util.ArrayList;
import java.util.*;
import java.util.List;

/**
 * Created by FrankCooper
 * Date 2019/3/30 0:00
 * Description
 */
public class RepeativeCaseII {

    private static RepeativeCaseII handler = new RepeativeCaseII();

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
        odd.next = evenHead;
        return head;
    }

    /**
     * 78. 子集 Medium
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        subsetsDFS(result, new ArrayList<Integer>(), 0, nums);
        return result;
    }

    private void subsetsDFS(List<List<Integer>> result, ArrayList<Integer> levelList, int depth, int[] nums) {
        //recursive exit
        if (depth >= nums.length) {
            result.add(new ArrayList<>(levelList));
            return;
        }
        //pick nums[depth]
        levelList.add(nums[depth]);
        subsetsDFS(result, levelList, depth + 1, nums);

        //not pick nums[depth]
        levelList.remove(levelList.size() - 1);
        subsetsDFS(result, levelList, depth + 1, nums);
    }


    /**
     * 17. 电话号码的字母组合 Medium
     */
    Map<Character, List<String>> map = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        map.put('2', Arrays.asList("a", "b", "c"));
        map.put('3', Arrays.asList("d", "e", "f"));
        map.put('4', Arrays.asList("g", "h", "i"));
        map.put('5', Arrays.asList("j", "k", "l"));
        map.put('6', Arrays.asList("m", "n", "o"));
        map.put('7', Arrays.asList("p", "q", "r", "s"));
        map.put('8', Arrays.asList("t", "u", "v"));
        map.put('9', Arrays.asList("w", "x", "y", "z"));
        letterCombinationsDFS(result, new ArrayList<String>(), 0, digits.toCharArray());
        return result;
    }

    private void letterCombinationsDFS(List<String> result, ArrayList<String> levelList, int index, char[] digitsChar) {
        if (levelList.size() == digitsChar.length) {
            StringBuffer sb = new StringBuffer();
            for (String str : levelList) {
                sb.append(str);
            }
            result.add(sb.toString());
            return;
        }
        List<String> letters = map.get(digitsChar[index]);
        for (String letter : letters) {
            levelList.add(letter);
            letterCombinationsDFS(result, levelList, index + 1, digitsChar);
            levelList.remove(levelList.size() - 1);
        }

    }


    /**
     * 53. 最大子序和 Easy
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int preMaxSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int num : nums) {
            preMaxSum = Math.max(num, preMaxSum + num);
            maxSum = Math.max(maxSum, preMaxSum);
        }
        return maxSum;
    }


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
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }

    /**
     * 392. 判断子序列 Medium
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int sIndex = 0;
        int tIndex = 0;
        while (tIndex != t.length() && sIndex != s.length()) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                sIndex++;
                tIndex++;
            } else {
                tIndex++;
            }
        }
        return sIndex == s.length();
    }


    /**
     * 122. 买卖股票的最佳时机 II
     *
     * @param prices
     * @return
     */
    public int maxProfitII(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    /**
     * 605. 种花问题 Easy
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 1) {
                continue;
            }
            int pre = i == 0 ? 0 : flowerbed[i - 1];
            int next = i == len - 1 ? 0 : flowerbed[i + 1];
            if (pre == 0 && next == 0) {
                flowerbed[i] = 1;
                count++;
            }
        }
        return count >= n;
    }


    /**
     * 665. 非递减数列 Easy
     *
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= nums[i - 1]) {
                continue;
            }
            count++;
            if (i - 2 >= 0 && nums[i - 2] > nums[i]) {
                nums[i] = nums[i - 1];
            } else {
                nums[i - 1] = nums[i];
            }
        }
        return count <= 1;
    }

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }


    /**
     * 322. 零钱兑换 LeetCode Medium
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
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


    public int coins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    private int process1(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                res += process1(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }


    public int coins2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] map = new int[arr.length + 1][aim + 1];
        return process2(arr, 0, aim, map);
    }

    private int process2(int[] arr, int index, int aim, int[][] map) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            int mapValue = 0;
            for (int i = 0; arr[index] * i <= aim; i++) {
                mapValue = map[index + 1][aim - arr[index] * i];
                if (mapValue != 0) {
                    res += mapValue == -1 ? 0 : mapValue;
                } else {
                    res += process2(arr, index + 1, aim - arr[index] * i, map);
                }
            }
        }
        map[index][aim] = res == 0 ? -1 : res;
        return res;
    }


    public int findMin(int[] nums) {
        //一个元素时，返回其自身
        if (nums.length == 1) return nums[0];
        int left = 0, right = nums.length - 1;
        //1 < 2 < 3 < 4 < 5 < 7 类似于这样严格升序，最后一个元素第一个元素，第一个元素即为最小的数
        if (nums[0] < nums[right]) return nums[0];
        //采用二分查找
        while (left < right) {
            int mid = left + (right - left) / 2;
            //4, 5, 6, 7, 0, 1, 2 中的7对应的index为3，其为mid，但是7>0证明0是最小的,当前的mid是最小数的前一个数
            if (nums[mid] > nums[mid + 1]) return nums[mid + 1];
            //4, 5, 6, 7，2,3到7,2,3时，mid为对应的数为2 7>2 返回mid为2，mid指向的是最小的数
            if (nums[mid - 1] > nums[mid]) return nums[mid];
            //如果[mid]>[0]，说明0到mid没有出现先升后降的趋势，left往mid+1移动
            if (nums[mid] > nums[0]) left = mid + 1;
                //如果[mid]<[0] 说明0到mid出现先升后降的趋势，right往mid-1移动
            else right = mid - 1;
        }
        return -1;
    }

    public int findMin2nd(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= nums[right]) right = mid;
            else left = mid + 1;
        }
        return nums[left];
    }


    public int findMinII(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1, mid = 0;
        while (left < right) {
            //解决2, 2, 2, 0, 1这类case的死循环
            if (left == right - 1) break;
            if (nums[left] < nums[right]) return nums[left];
            mid = left + (right - left) / 2;
            if (nums[left] > nums[mid]) {
                right = mid;
                continue;
            }
            if (nums[mid] > nums[right]) {
                left = mid;
                continue;
            }
            //以下的逻辑是处理相等的情况，case:1,1,1
            while (left < mid) {
                if (nums[left] == nums[mid]) left++;
                else if (nums[left] < nums[mid]) return nums[left];
                else {
                    right = mid;
                    break;
                }
            }
        }
        return Math.min(nums[left], nums[right]);
    }

    public int findMinII2nd(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) left = mid + 1;
            else if (nums[mid] < nums[right]) right = mid;
            else right--;
        }
        return nums[left];
    }


    /**
     * 33. 搜索旋转排序数组
     * 你可以假设数组中不存在重复的元素。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] >= nums[left]) {
                if (nums[left] <= target && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                if (nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }
        }
        return nums[left] == target ? left : -1;
    }

    public int search2nd(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        if (right == 0) return nums[0] == target ? 0 : -1;
        int rotateIndex = findRotateIndex(nums, left, right);
        if (nums[rotateIndex] == target) return rotateIndex;
        if (rotateIndex == 0) return binarySearch(nums, left, right, target);
        if (target < nums[0]) return binarySearch(nums, rotateIndex, right, target);
        else return binarySearch(nums, 0, rotateIndex, target);
    }

    /**
     * 找到旋转数组的旋转点，如4567123中的1对应的index=4
     *
     * @param arr   旋转数组
     * @param left  旋转数组左边界index
     * @param right 旋转数组右边界index
     * @return
     */
    private int findRotateIndex(int[] arr, int left, int right) {
        if (arr[left] < arr[right]) return 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            //此处返回发生时，需要的时前面一段升序数组后面一段降序数组，mid为升序数组的最后一个也就是升序数组的最大值
            if (arr[mid] > arr[mid + 1]) return mid + 1;
            else {
                if (arr[left] > arr[mid]) right = mid - 1;
                else left = mid + 1;
            }
        }
        return 0;
    }

    /**
     * 在一个升序数组arr中找target的index，不存在返回-1
     *
     * @param arr    升序数组
     * @param left   arr的左边界index
     * @param right  arr的右边界index
     * @param target 目标数
     * @return
     */
    private int binarySearch(int[] arr, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            else {
                if (arr[mid] > target) right = mid - 1;
                else left = mid + 1;
            }
        }
        return -1;
    }


    public boolean searchII(int[] arr, int target) {
        if (arr == null || arr.length == 0) return false;
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return true;
            if (arr[left] == arr[mid] && arr[mid] == arr[right]) {
                while (left != mid && arr[left] == arr[mid]) left++;
                if (left == mid) {
                    left = mid + 1;
                    continue;
                }
            }
            if (arr[left] != arr[mid]) {
                if (arr[mid] > arr[left]) {
                    if (target >= arr[left] && target < arr[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    //注意<=
                    if (target > arr[mid] && target <= arr[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            } else {
                //注意<=
                if (arr[mid] < arr[right]) {
                    if (target > arr[mid] && target <= arr[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                } else {
                    if (target >= arr[left] && target < arr[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
        }
        return false;

    }

    public boolean searchII2nd(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return true;
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }
        }
        return false;
    }


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return result;
        combinationSumDFS(result, new ArrayList<Integer>(), 0, candidates, target);
        return result;
    }

    private void combinationSumDFS(List<List<Integer>> result, ArrayList<Integer> levelList, int index, int[] candidates, int target) {
        for (int i = index; i < candidates.length; i++) {
            int cur = candidates[i];
            levelList.add(cur);
            int remain = target - cur;
            if (remain == 0) {
                result.add(new ArrayList<>(levelList));
            } else if (remain > 0) {
                combinationSumDFS(result, levelList, i, candidates, remain);
            }
            levelList.remove(levelList.size() - 1);
        }

    }

    public List<List<Integer>> subsets1st(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        List<Integer> levelList = new ArrayList<>();
        result.add(levelList);
        helper(result, levelList, nums, 0);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> levelList, int[] nums, int index) {
        for (int i = index; i < nums.length; i++) {
            levelList.add(nums[i]);
            result.add(new ArrayList<>(levelList));
            helper(result, levelList, nums, i + 1);
            levelList.remove(levelList.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        Arrays.sort(nums);
        List<Integer> levelList = new ArrayList<>();
        result.add(levelList);
        helperWithDup(result, levelList, nums, 0);
        return result;
    }

    private void helperWithDup(List<List<Integer>> result, List<Integer> levelList, int[] nums, int index) {
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) continue;
            levelList.add(nums[i]);
            result.add(new ArrayList<>(levelList));
            helperWithDup(result, levelList, nums, i + 1);
            levelList.remove(levelList.size() - 1);
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] helper = new int[26];
            char[] chas = str.toCharArray();
            for (char c : chas) {
                helper[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int h : helper) {
                sb.append(h);
            }
            map.putIfAbsent(sb.toString(), new ArrayList<>());
            map.get(sb.toString()).add(str);
        }
        result = new ArrayList<>(map.values());
        return result;
    }

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && nums[j] >= (i - j)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n - 1];
    }

    Index[] memo;

    public boolean canJump1st(int[] nums) {
        memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;
        return canJumpFromPosition(0, nums);
    }

    private boolean canJumpFromPosition(int position, int[] nums) {
        if (memo[position] != Index.UNKNOWN) return memo[position] == Index.GOOD;
        int furtherJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furtherJump; nextPosition++) {
            if (canJumpFromPosition(nextPosition, nums)) {
                memo[nextPosition] = Index.GOOD;
                return true;
            }
        }
        memo[position] = Index.BAD;
        return false;
    }


    public boolean canJump2nd(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;
        for (int i = nums.length - 2; i >= 0; i--) {
            int furtherJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furtherJump; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }
        return memo[0] == Index.GOOD;
    }

    public boolean canJump3rd(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }


    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) return intervals;
        intervals.sort(Comparator.comparing(interval -> interval.start));
        int i = 0;
        while (i < intervals.size() - 1) {
            Interval cur = intervals.get(i);
            Interval next = intervals.get(i + 1);
            if (cur.end >= next.start) {
                cur.end = (cur.end >= next.end) ? cur.end : next.end;
                intervals.remove(i + 1);
                continue;
            }
            i++;
        }
        return intervals;
    }


    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        if (intervals == null || intervals.length == 0) return result.toArray(new int[0][]);
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int i = 0;
        while (i < intervals.length) {
            int curStart = intervals[i][0];
            int curEnd = intervals[i][1];
            while (i < intervals.length - 1 && curEnd >= intervals[i + 1][0]) {
                curEnd = Math.max(curEnd, intervals[i + 1][1]);
                i++;
            }
            result.add(new int[]{curStart, curEnd});
            i++;
        }
        return result.toArray(new int[0][]);
    }

    public boolean canAttendMeetings(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) return true;
        PriorityQueue<Interval> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.start));
        for (Interval interval : intervals) {
            queue.add(interval);
        }
        while (!queue.isEmpty()) {
            Interval cur = queue.poll();
            Interval next = queue.peek();
            while (next != null && cur.end > next.start) {
                return false;
            }
        }
        return true;
    }

    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) return 0;
        intervals.sort(Comparator.comparingInt(o -> o.start));
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int count = 0;
        for (Interval interval : intervals) {
            queue.add(interval.end);
            if (interval.start < queue.peek()) {
                count++;
            } else {
                queue.poll();
            }
        }
        return count;
    }

    public int maxSubArray1st(int[] nums) {
        int pre = nums[0];
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            pre = pre > 0 ? pre + nums[i] : nums[i];
            max = Math.max(pre, max);
        }
        return max;
    }


    public static void main(String[] args) {
//        int[] nums = {1, 2, 3};
//        handler.subsets(nums);
//        handler.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
//        System.out.println(handler.isSubsequence("abc", "ahbgdc"));
//        handler.maxProfitII(new int[]{7, 1, 5, 8, 3, 6, 4});
//        handler.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1);
//        handler.checkPossibility(new int[]{3, 4, 2, 3});
//        handler.findMin(new int[]{4, 5, 6, 7, 0, 1, 2});
//        handler.findMin(new int[]{4, 5, 6, 7, 2, 3});
//        handler.findMin2nd(new int[]{4, 5, 6, 7, 2, 3});
//        handler.findMinII(new int[]{2, 2, 2, 0, 1});
//        handler.findMinII(new int[]{1, 1, 1});
//        handler.findMinII2nd(new int[]{1, 1, 1, 1});
//        handler.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
//        handler.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3);
//        handler.search(new int[]{1, 3}, 1);
        int[] nums = new int[]{4, 5, 6, 7, 8, 1, 2, 3};
//        handler.findRotateIndex(nums, 0, nums.length - 1);
//        handler.search2nd(new int[]{1, 3}, 3);
//        nums = new int[]{1, 3};
//        handler.searchII(nums, 3);
        handler.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});

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

    enum Index {
        GOOD, BAD, UNKNOWN;
    }


    class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}


