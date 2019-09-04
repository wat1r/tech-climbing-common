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


    public int uniquePaths(int m, int n) {
        //dp[i][j]是从(0,0)位置走到(i,j)位置的走法
        int[][] dp = new int[m][n];
        for (int j = 0; j < n; j++) dp[0][j] = 1;
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }


    public int uniquePaths1st(int m, int n) {
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) dp[i] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[] dp = new int[n];
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1) break;
            dp[j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                dp[j] = (j == 0) ? dp[j] : dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }


    public int uniquePathsWithObstacles1st(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1) break;
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) continue;
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }


    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length <= 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }


    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int left = 0, right = nums.length - 1;
        int i = 0;
        while (i <= right) {
            if (nums[i] == 0) {
                swap(nums, i, left);
                left++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, right);
                right--;
            }
        }
    }

    public void swap(int[] arr, int m, int n) {
        int temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
//        arr[m] = arr[m] ^ arr[n];
//        arr[n] = arr[m] ^ arr[n];
//        arr[m] = arr[m] ^ arr[n];
    }

    public int numDecodings(String s) {
        char[] chas = s.toCharArray();
        int n = chas.length;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = chas[0] != '0' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            int oneDigit = chas[i - 1] - '0';
            if (oneDigit >= 1 && oneDigit <= 9) {
                dp[i] += dp[i - 1];
            }
            int twoDigit = (chas[i - 1] - '0') + 10 * (chas[i - 2] - '0');
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }


    List<Integer> inoderList = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return inoderList;
        inorderTraversal(root.left);
        inoderList.add(root.val);
        inorderTraversal(root.right);
        return inoderList;
    }

    public List<Integer> inorderTraversal1st(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            result.add(new ArrayList<>());
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                result.get(level).add(cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            ++level;
        }
        return result;
    }

    public void printByLevel(TreeNode head) {
        if (head == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        int level = 0;
        TreeNode last = head;
        TreeNode nLast = null;
        queue.offer(last);
        System.out.print("Level " + (level++) + " : ");
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            System.out.print(cur.val + " ");
            if (cur.left != null) {
                queue.offer(cur.left);
                nLast = cur.left;
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                nLast = cur.right;
            }
            if (cur == last && !queue.isEmpty()) {
                System.out.print("\nLevel" + (level++) + " : ");
                last = nLast;
            }
        }
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();
            int size = queue.size();//一次性倒出queue的一层元素
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                //level记录层数，按奇偶行来添加元素，add(0, cur.val)表示新进来的元素都放在头部
                if (level % 2 == 0) levelList.add(cur.val);
                else levelList.add(0, cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            level++;
            result.add(levelList);
        }
        return result;
    }

    public List<List<Integer>> zigzagLevelOrder1st(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        //odd 奇栈，even 偶栈，FILO
        // 第一层，第二层为从左到右，stackOdd先加right元素，再加left元素
        //第二层，第四为从右到左，stackEven先加left元素，再加right元素
        Stack<TreeNode> stackOdd = new Stack<>();
        Stack<TreeNode> stackEven = new Stack<>();
        stackOdd.add(root);
        List<Integer> levelList = null;
        while (!stackOdd.isEmpty() || !stackEven.isEmpty()) {
            levelList = new ArrayList<>();
            if (!stackOdd.isEmpty()) {
                while (!stackOdd.isEmpty()) {
                    TreeNode odd = stackOdd.pop();
                    levelList.add(odd.val);
                    if (odd.left != null) stackEven.push(odd.left);
                    if (odd.right != null) stackEven.push(odd.right);
                }
            } else {
                while (!stackEven.isEmpty()) {
                    TreeNode even = stackEven.pop();
                    levelList.add(even.val);
                    if (even.right != null) stackOdd.push(even.right);
                    if (even.left != null) stackOdd.push(even.left);
                }
            }
            result.add(levelList);
        }
        return result;
    }


    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && sum == root.val) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }


    public boolean hasPathSum1st(TreeNode root, int sum) {
        if (root == null) return false;
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        nodeStack.push(root);
        sumStack.push(sum - root.val);
        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            Integer curSum = sumStack.pop();
            if (curNode.left == null && curNode.right == null && curSum == 0) return true;
            if (curNode.right != null) {
                nodeStack.push(curNode.right);
                sumStack.push(curSum - curNode.right.val);
            }
            if (curNode.left != null) {
                nodeStack.push(curNode.left);
                sumStack.push(curSum - curNode.left.val);
            }
        }
        return false;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        pathSumDFS(result, new ArrayList<Integer>(), root, sum);
        return result;
    }

    private void pathSumDFS(List<List<Integer>> result, ArrayList<Integer> tmpList, TreeNode root, int sum) {
        if (root == null) return;
        tmpList.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) result.add(new ArrayList<>(tmpList));
        pathSumDFS(result, tmpList, root.left, sum - root.val);
        pathSumDFS(result, tmpList, root.right, sum - root.val);
        tmpList.remove(tmpList.size() - 1);
    }

    int sum = 0;

    public int sumNumbers(TreeNode root) {
        if (root == null) return sum;
        sumNumbersDFS(root, root.val);
        return sum;
    }

    private void sumNumbersDFS(TreeNode root, int curSum) {
        if (root.left == null && root.right == null) {
            sum += curSum;
            return;
        }
        if (root.left != null) {
            sumNumbersDFS(root.left, curSum * 10 + root.left.val);
        }
        if (root.right != null) {
            sumNumbersDFS(root.right, curSum * 10 + root.right.val);
        }
    }

    public int evalRPN(String[] tokens) {
        String[] symbols = new String[]{"+", "-", "*", "/"};
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (Arrays.asList(symbols).contains(token)) {
                int left = stack.pop();
                int right = stack.pop();
                if (token.equals("+")) stack.push(right + left);
                if (token.equals("-")) stack.push(right - left);
                if (token.equals("*")) stack.push(right * left);
                if (token.equals("/")) stack.push(right / left);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }

    public int trailingZeroes(int n) {
        int res = 0;
        while (n > 4) {
            n /= 5;
            res += n;
        }
        return res;
    }


    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = robHelper(nums);
        return dp[nums.length];
    }

    public int[] robHelper(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp;
    }


    public int robII(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int n = nums.length;
        int[] head = Arrays.copyOfRange(nums, 0, n - 1);
        int[] tail = Arrays.copyOfRange(nums, 1, n);
        int[] headDp = robHelper(head);
        int[] tailDp = robHelper(tail);
        return Math.max(headDp[head.length], tailDp[tail.length]);
    }

    //dp[0]表示不选根节点的的max，dp[1]表示选了根节点的max
    public int robIII(TreeNode root) {
        int[] dp = robHelper(root);
        return Math.max(dp[0], dp[1]);
    }

    public int[] robHelper(TreeNode root) {
        int[] dp = new int[2];
        if (root == null) return dp;
        int[] left = robHelper(root.left);
        int[] right = robHelper(root.right);
        //dp[0]=max{左子树选与不选根节点的最大值+右子树选与不选根节点的最大值}
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        //dp[1]=左子树不选根节点的左孩子节点（因为选了根节点root,root.left不能再选了）+
        //右子树不选根节点的右孩子节点（因为选了根节点root,root.right不能再选了）+root.val
        dp[1] = left[0] + right[0] + root.val;
        return dp;
    }


    public int robIII1st(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        return Math.max(robIII1stDFS(root, true), robIII1stDFS(root, false));
    }

    private int robIII1stDFS(TreeNode root, boolean visit) {
        if (root.left == null && root.right == null) {
            return visit ? root.val : 0;
        }
        int left = 0, right = 0;
        //当前节点被visit的过，其孩子节点left和right不可使用，传入 !visit即false，
        // 最后返回左子树和右子树的和，+这个root被访问了，root.val
        if (visit) {
            if (root.left != null) left = robIII1stDFS(root.left, !visit);
            if (root.right != null) right = robIII1stDFS(root.right, !visit);
            return root.val + left + right;
        }
        //当前节点没有被访问，其左孩子可以被访问，也可以不被访问，取Max，右孩子同理
        //返回结果是左子树的Max与右子树的Max值的和，因为没有访问root，root.val的值被舍弃
        else {
            if (root.left != null) left = Math.max(robIII1stDFS(root.left, !visit), robIII1stDFS(root.left, visit));
            if (root.right != null) right = Math.max(robIII1stDFS(root.right, !visit), robIII1stDFS(root.right, visit));
            return left + right;
        }
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            result.add(cur.val);
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
        return result;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode cur = head;
        int n = 1;
        while (cur.next != null) {
            cur = cur.next;
            n++;
        }
        cur.next = head;
        int i = n - k % n - 1;
        cur = head;
        while (i > 0) {
            cur = cur.next;
            i--;
        }
        ListNode newTail = cur;
        ListNode newHead = cur.next;
        newTail.next = null;
        return newHead;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0) return result;
        List<Integer> levelList = new ArrayList<>();
        levelList.add(1);
        result.add(levelList);
        levelList = new ArrayList<>();
        for (int i = 1; i < numRows; i++) {
            levelList.add(1);
            List<Integer> preList = result.get(i - 1);
            for (int j = 0; j < preList.size() - 1; j++) {
                int cur = preList.get(j) + preList.get(j + 1);
                levelList.add(cur);
            }
            levelList.add(1);
            result.add(levelList);
            levelList = new ArrayList<>();
        }
        return result;
    }

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};


    public boolean exist(char[][] board, String word) {
        if (board == null || board[0] == null || board.length == 0 || board[0].length == 0 ||
                word == null || word.length() == 0)
            return false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j] && existDFS(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean existDFS(char[][] board, String word, int x, int y, int index) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length ||
                word.charAt(index) != board[x][y] || board[x][y] == '#')
            return false;
        if (index == word.length() - 1) return true;
        board[x][y] = '#';
        for (int i = 0; i < dx.length; i++) {
            if (existDFS(board, word, x + dx[i], y + dy[i], index + 1)) {
                return true;
            }
        }
        board[x][y] = word.charAt(index);
        return false;
    }


    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0) return -1;
        int total = 0, remain = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            remain += gas[i] - cost[i];
            if (remain < 0) {
                remain = 0;
                start = i + 1;
            }
            total += gas[i] - cost[i];
        }
        if (total < 0) return -1;
        return start;
    }


    public String countAndSay(int n) {
        String str = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            char pre = str.charAt(0);
            int count = 1;
            for (int j = 1; j < str.length(); j++) {
                char cur = str.charAt(j);
                if (cur == pre) count++;
                else {
                    sb.append(count).append(pre);
                    pre = cur;
                    count = 1;
                }
            }
            sb.append(count).append(pre);
            str = sb.toString();
        }
        return str;
    }

    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid + 1]) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null) {
            if (set.contains(node)) return node;
            set.add(node);
            node = node.next;
        }
        return null;
    }


    public ListNode detectCycle1st(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            if (slow == fast) break;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (slow != fast) return null;
        while (head != slow.next) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }


    public ListNode detectCycle2nd(ListNode head) {
        if (head == null || head.next == null) return null;
        //step1：快慢指针找到相遇的节点，没有找到相遇的节点，证明不成环，返回null
        ListNode slow = head;
        ListNode fast = head.next;
        ListNode tmp = null;
        while (fast.next != null && fast.next.next != null) {
            if (fast == slow) {
                tmp = slow;
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != slow) return null;
        //step2:找到环的step
        int step = 1;
        ListNode cur = tmp.next;
        while (cur != tmp) {
            cur = cur.next;
            step++;
        }
        slow = head;
        fast = head;
        //step3：将fast指针移动step步
        for (int i = 0; i < step; i++) {
            fast = fast.next;
        }
        //step4:slow与fast指针同步走，相交即为环的入口
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;
            if (cur.left != null) queue.offer(cur.left);
            if (cur.right != null) queue.offer(cur.right);
        }
        return root;
    }


    public int numPrimeArrangements(int n) {
        int primes = 0;
        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) primes++;
        }
        long result = 1;
        int max = Math.max(primes, n - primes);
        int min = Math.min(primes, n - primes);
        for (int i = 1; i <= max; i++) {
            result *= i;
            result %= 1000000007;
            if (i <= min) {
                result *= i;
                result %= 1000000007;
            }
        }
        return (int) result;
    }


    private boolean isPrime(int n) {
        if (n == 1) return false;
        if (n == 2) return true;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
            else return true;
        }
        return false;
    }

    public int numPrimeArrangements1st(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        //质数的个数 厄拉多塞筛法
        boolean[] sign = new boolean[n + 1];
        int primes = 0;
        for (int i = 2; i <= n; i++) {
            if (!sign[i]) {
                primes++;
                for (int j = i + i; j <= n; j += i) {
                    sign[j] = true;
                }
            }
        }
        //求阶乘
        int max = Math.max(n - primes, primes);
        int min = Math.min(n - primes, primes);
        //模运算复合交换律
        long result = 1;
        for (int i = 1; i <= max; i++) {
            result *= i;
            result %= 1000000007;
            if (i <= min) {
                result *= i;
                result %= 1000000007;
            }
        }
        return (int) result;
    }


    //优化暴力算法
    public int countPrimes(int n) {
        if (n < 3) return 0;
        int count = 1;
        //从3开始，初始值为1，2为质数
        for (int i = 3; i < n; i++) {
            if ((i & 1) == 0) continue;//排除掉偶数，除了2的偶数均不是质数
            boolean sign = true;
            for (int j = 3; j * j <= i; j += 2) {//j+=2排除掉偶数，j*j
                if (i % j == 0) {
                    sign = false;
                    break;
                }
            }
            if (sign) count++;
        }
        return count;
    }

    public int countPrimes1st(int n) {
        if (n < 2) return 0;
        boolean[] signs = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!signs[i]) {
                count++;
                for (int j = i + i; j < n; j += i) {
                    signs[j] = true;
                }
            }
        }
        return count;
    }


    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int scores = 0;
        outer:
        for (int i = 0; i < calories.length; i++) {
            int tmp = 0;
            for (int j = 0; j < k; j++) {
                if ((i + j) > calories.length - 1) break outer;
                tmp += calories[i + j];
            }
            if (tmp < lower) scores--;
            if (tmp > upper) scores++;
        }
        return scores;
    }

    public int dietPlanPerformance1st(int[] calories, int k, int lower, int upper) {
        int scores = 0;
        int tmp = 0;
        //如果k的长度大于数组的长度，证明不可以
        if (k > calories.length) return scores;
        //初始化，并进行统计
        for (int i = 0; i < k; i++) tmp += calories[i];
        if (tmp < lower) scores--;
        else if (tmp > upper) scores++;
        //从k开始，维持一个k的滑动窗口，先+calories[i]，再-窗口的最开始的元素 calories[i - k]
        for (int i = k; i < calories.length; i++) {
            tmp += calories[i];
            tmp -= calories[i - k];
            if (tmp < lower) scores--;
            else if (tmp > upper) scores++;
        }
        return scores;
    }

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> results = new ArrayList<>();
        if (queries == null || queries.length == 0) return results;
        for (int i = 0; i < queries.length; i++) {
            int[] arr = queries[i];
            String cur = s.substring(arr[0], arr[1] + 1);
            int[] counter = new int[26];
            for (int j = 0; j < cur.length(); j++) {
                counter[cur.charAt(j) - 'a']++;
            }
            int odd = 0;
            for (int j = 0; j < counter.length; j++) {
                if (counter[j] % 2 == 1) odd++;
            }
            if (arr[2] >= (odd / 2)) results.add(true);
            else results.add(false);
        }
        return results;
    }


    public List<Boolean> canMakePaliQueries1st(String s, int[][] queries) {
        List<Boolean> results = new ArrayList<>();
        char[] chas = s.toCharArray();
        int len = chas.length;
        int[] dp = new int[len + 1];
        for (int i = 0; i < len; i++) {
            int num = (1 << (chas[i] - 'a'));
            dp[i + 1] = (dp[i] ^ num);
        }
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int start = query[0], end = query[1], k = query[2];
            int cur = (dp[end + 1] ^ dp[start]);
            int odd = 0;
            while (cur != 0) {
                odd++;
                cur &= (cur - 1);
            }
            if (k >= (odd / 2)) results.add(true);
            else results.add(false);
        }
        return results;
    }

    public List<String> invalidTransactions(String[] transactions) {
        if (transactions == null || transactions.length == 0) return new ArrayList<>();
        Set<String> results = new HashSet<>();
        int len = transactions.length;
        String[] names = new String[len];
        int[] times = new int[len];
        int[] amounts = new int[len];
        String[] cities = new String[len];
        for (int i = 0; i < len; i++) {
            String[] arr = transactions[i].split(",");
            names[i] = arr[0];
            times[i] = Integer.valueOf(arr[1]);
            amounts[i] = Integer.valueOf(arr[2]);
            cities[i] = arr[3];
        }
        for (int i = 0; i < len; i++) {
            if (amounts[i] > 1000) results.add(transactions[i]);
            for (int j = i + 1; j < len; j++) {
                if (names[i].equals(names[j]) && !cities[i].equals(cities[j]) && Math.abs(times[i] - times[j]) <= 60) {
                    results.add(transactions[i]);
                    results.add(transactions[j]);
                }
            }
        }
        return new ArrayList<>(results);
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
//        handler.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
//        handler.uniquePathsWithObstacles(new int[][]{{0}, {1}});
        nums = new int[]{2, 0, 2, 1, 1, 0};
//        handler.sortColors(nums);
//        String[] arrs = new String[]{"4", "13", "5", "/", "+"};
//        handler.evalRPN(arrs);
//        handler.generate(1);
//        handler.countAndSay(5);
//        handler.numPrimeArrangements(100);
//        handler.numPrime(100);
//        handler.countPrimes(10);
//        handler.dietPlanPerformance(new int[]{6, 13, 8, 7, 10, 1, 12, 11}, 6, 5, 37);
        String s = "abcda";
        int[][] queries = new int[][]{{3, 3, 0}, {1, 2, 0}, {0, 3, 1}, {0, 3, 2}, {0, 4, 1}};
//        handler.canMakePaliQueries(s, queries);
//        handler.canMakePaliQueries1st(s, queries);
        String[] t = new String[]{"alice,20,800,mtv", "alice,50,100,beijing"};
        handler.invalidTransactions(t);

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

    class NumArray {

        private int[] sum;


        public NumArray(int[] nums) {
            sum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return sum[j + 1] - sum[i];
        }
    }

}


