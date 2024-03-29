package algorithm.leetcode.case2;


import java.util.*;

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


    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int slow = 0, fast = 1;
        while (fast < nums.length) {
            if (nums[fast] == nums[slow]) fast++;
            else swap(nums, ++slow, fast++);
        }
        return slow + 1;
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


    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] results = new int[queries.length];
        if (queries == null || queries.length == 0 || words == null || words.length == 0) return results;
        for (int i = 0; i < queries.length; i++) {
            int query = f(queries[i]);
            int count = 0;
            for (int j = 0; j < words.length; j++) {
                int word = f(words[j]);
                if (query < word) count++;
            }
            results[i] = count;
        }
        return results;
    }


    public int f(String str) {
        char[] chas = str.toCharArray();
        int[] arr = new int[26];
        int min = 0;
        for (int i = 0; i < chas.length; i++) {
            arr[chas[i] - 'a']++;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                min = arr[i];
                return min;
            }
        }
        return min;
    }


    public int splitArray(int[] nums, int m) {
        int max = 0;
        long sum = 0;
        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        if (m == 1) {
            return (int) sum;
        }
        //binary search
        long l = max;
        long r = sum;
        while (l <= r) {
            long mid = (l + r) / 2;
            if (valid(mid, nums, m)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) l;
    }

    public boolean valid(long target, int[] nums, int m) {
        int count = 1;
        long total = 0;
        for (int num : nums) {
            total += num;
            if (total > target) {
                total = num;
                count++;
                if (count > m) {
                    return false;
                }
            }
        }
        return true;
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head.val == 0 ? null : head;
        if (head.val == 0) return removeZeroSumSublists(head.next);
        ListNode cur = head.next;
        int num = head.val;
        while (cur != null) {
            num += cur.val;
            if (num == 0) break;
            cur = cur.next;
        }
        if (num == 0) return removeZeroSumSublists(cur.next);
        head.next = removeZeroSumSublists(head.next);
        return head;
    }


    public int countCharacters(String[] words, String chars) {
        if (words == null || words.length == 0 || chars.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length(); i++) {
            char c = chars.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int result = 0;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            boolean sign = false;
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (!map.containsKey(c)) {
                    sign = true;
                    break;
                } else {
                    if (map.get(c) >= 1) map.put(c, map.get(c) - 1);
                    else {
                        sign = true;
                        break;
                    }
                }

            }
            if (sign) continue;
            result += word.length();
        }
        return result;
    }


    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double result = 1;
        for (int i = 0; i < N; i++) {
            result *= x;
        }
        return result;
    }

    public double myPow1st(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return fastPow(x, N);
    }

    private double fastPow(double x, long n) {
        if (n == 0) return 1.0;
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) return half * half;
        else return half * half * x;
    }

    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        int max = 0;
        int maxLevel = 1;
        while (!queue.isEmpty()) {
            int sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            if (sum > max) {
                max = sum;
                maxLevel = level;
            }
            level++;
        }
        return maxLevel;
    }


    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        for (int[] p : prerequisites) {
            indegrees[p[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer pre = queue.poll();
            numCourses--;
            for (int[] p : prerequisites) {
                if (p[1] != pre) continue;
                indegrees[p[0]]--;
                if (indegrees[p[0]] == 0) queue.offer(p[0]);
            }
        }
        return numCourses == 0;
    }


    public boolean canFinish1st(int numCourses, int[][] prerequisites) {
        int[][] adjacency = new int[numCourses][numCourses];
        int[] flags = new int[numCourses];
        for (int[] p : prerequisites) {
            adjacency[p[1]][p[0]] = 1;
        }
        for (int i = 0; i < numCourses; i++) {
            if (!canFinish1stDFS(adjacency, flags, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean canFinish1stDFS(int[][] adjacency, int[] flags, int i) {
        if (flags[i] == 1) return false;
        if (flags[i] == -1) return true;
        flags[i] = 1;
        for (int j = 0; j < adjacency.length; j++) {
            if (adjacency[i][j] == 1 && !canFinish1stDFS(adjacency, flags, j)) {
                return false;
            }
        }
        flags[i] = -1;
        return true;
    }


    public int maxDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) queue.offer(new int[]{i, j});
            }
        }
        if (queue.isEmpty() || queue.size() == m * n) return -1;
        int[][] dx = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int result = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] d : dx) {
                    int nx = cur[0] - d[0];
                    int ny = cur[1] - d[1];
                    if (nx >= m || nx < 0 || ny >= n || ny < 0 || grid[nx][ny] == 1) continue;
                    grid[nx][ny] = 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
            result++;
        }

        return result;
    }

    public int dayOfYear(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8);

        String[] bigMonth = {"1", "3", "5", "7", "8", "10", "12"};
        //默认二月28天
        int two = 28;
        int total = 0;
        //累加月
        for (int i = 1; i < Integer.valueOf(month); i++) {
            if (i == 2) {
                if (Integer.valueOf(year) % 100 == 0) {
                    if (Integer.valueOf(year) % 400 == 0) {
                        two = 29;
                    }
                } else if (Integer.valueOf(year) % 4 == 0) {
                    two = 29;
                }
                total += two;
            } else if (Arrays.asList(bigMonth).contains(String.valueOf(i))) {
                total += 31;
            } else {
                total += 30;
            }
        }
        //天
        total += Integer.valueOf(day);
        return total;

    }

    public int numRollsToTarget(int d, int f, int target) {
        int mod = 1000000007;
        int[][] dp = new int[d + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= d; i++) {
            for (int j = 1; j <= f; j++) {
                for (int k = j; k <= target; k++) {
                    dp[i][k] = (dp[i][k] + dp[i - 1][k - j]) % mod;
                }
            }
        }
        return dp[d][target];
    }

    public int maxRepOpt1(String text) {
        char[] chas = text.toCharArray();
        int len = chas.length;
        int[] total = new int[26];
        int[] left = new int[len];
        int[] right = new int[len];
        for (int i = 0; i < len; i++) total[chas[i] - 'a']++;
        int result = 1;
        int cnt = 1;
        left[0] = 1;//初始化left
        for (int i = 1; i < len; i++) {
            if (chas[i] == chas[i - 1]) cnt++;
            else cnt = 1;
            left[i] = cnt;
            result = Math.max(result, left[i]);
        }
        cnt = 1;
        right[len - 1] = 1;//初始化right
        for (int i = len - 2; i >= 0; i--) {
            if (chas[i] == chas[i + 1]) cnt++;
            else cnt = 1;
            right[i] = cnt;
            result = Math.max(result, right[i]);
        }
        for (int i = 1; i < len - 1; i++) {
            if (total[chas[i - 1] - 'a'] > left[i - 1]) {
                result = Math.max(result, left[i - 1] + 1);
            }
            if (total[chas[i + 1] - 'a'] > right[i + 1]) {
                result = Math.max(result, right[i + 1] + 1);
            }
            if (chas[i - 1] == chas[i + 1] && chas[i - 1] != chas[i]) {
                if (total[chas[i - 1] - 'a'] > (left[i - 1] + right[i + 1])) {
                    result = Math.max(result, left[i - 1] + right[i + 1] + 1);
                } else {
                    result = Math.max(result, left[i - 1] + right[i + 1]);
                }
            }

        }
        return result;
    }


    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        int result = 0;
        boolean b = true;
        while (b) {
            b = false;
            for (int i = 0; i < n; i++) {
                if (i > 0 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                    b = true;
                }
                if (i != (n - 1) && ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                    b = true;
                }
            }
        }
        for (int i = 0; i < n; i++) result += candies[i];
        return result;
    }


    public int candy1st(int[] ratings) {
        int n = ratings.length;
        int[] left2right = new int[n];
        int[] right2left = new int[n];
        Arrays.fill(left2right, 1);
        Arrays.fill(right2left, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) left2right[i] = left2right[i - 1] + 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) right2left[i] = right2left[i + 1] + 1;
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += Math.max(left2right[i], right2left[i]);
        }
        return result;
    }

    public int candy2nd(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) candies[i] = candies[i - 1] + 1;
        }
        int result = candies[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            result += candies[i];
        }
        return result;
    }


    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int secondMax = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        stack.push(nums[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < secondMax) return true;
            else {
                while (!stack.isEmpty() && nums[i] > stack.peek()) {
                    secondMax = Math.max(secondMax, stack.pop());
                }
                stack.push(nums[i]);
            }
        }

        return false;
    }

    public int distributeCandies(int[] candies) {
        int n = candies.length;
        Arrays.sort(candies);
        int cnt = 0;
        for (int i = 1; i < n && cnt < n / 2; i++) {
            if (candies[i] > candies[i - 1]) cnt++;
        }
        return cnt;
    }


    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0, sumB = 0;
        for (int a : A) sumA += a;
        for (int b : B) sumB += b;
        int delta = (sumB - sumA) / 2;
        Set<Integer> set = new HashSet<>();
        for (int b : B) set.add(b);
        for (int a : A) {
            if (set.contains(a + delta)) {
                return new int[]{a, a + delta};
            }
        }
        return null;
    }


    public int movesToMakeZigzag(int[] nums) {
        int[] nums1 = Arrays.copyOf(nums, nums.length);
        int odd = 0;
        int n = nums.length;
        for (int i = 1; i < n; i += 2) {
            if (nums[i] <= nums[i - 1]) {
                int delta = nums[i - 1] - nums[i] + 1;
                nums[i - 1] -= delta;
                odd += delta;
            }
            if (i != (n - 1) && nums[i] <= nums[i + 1]) {
                int delta = nums[i + 1] - nums[i] + 1;
                nums[i + 1] -= delta;
                odd += delta;
            }
        }
        int even = 0;
        for (int i = 0; i < n; i += 2) {
            if (i != 0 && nums1[i] <= nums1[i - 1]) {
                int delta = nums1[i - 1] - nums1[i] + 1;
                nums1[i - 1] -= delta;
                even += delta;
            }
            if (i != (n - 1) && nums1[i] <= nums1[i + 1]) {
                int delta = nums1[i + 1] - nums1[i] + 1;
                nums1[i + 1] -= delta;
                even += delta;
            }
        }
        return Math.min(odd, even);
    }


    public int movesToMakeZigzag1st(int[] nums) {
        int n = nums.length;
        int odd = 0, even = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                int delta1 = (i > 0 && nums[i] >= nums[i - 1]) ? (nums[i] - nums[i - 1] + 1) : 0;
                int delta2 = (i < n - 1 && nums[i] >= nums[i + 1]) ? (nums[i] - nums[i + 1] + 1) : 0;
                even += Math.max(delta1, delta2);
            } else {
                int delta1 = (nums[i] >= nums[i - 1]) ? (nums[i] - nums[i - 1] + 1) : 0;
                int delta2 = (i < n - 1 && nums[i] >= nums[i + 1]) ? (nums[i] - nums[i + 1] + 1) : 0;
                odd += Math.max(delta1, delta2);
            }
        }
        return Math.min(even, odd);
    }

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode node = dfs(root, x);
        int leftCnt = countNode(node.left);
        int rightCnt = countNode(node.right);
        if (Math.max(leftCnt, rightCnt) > n / 2 || (n - leftCnt - rightCnt - 1) > n / 2)
            return true;
        return false;
    }

    public TreeNode dfs(TreeNode root, int target) {
        if (root == null) return null;
        if (root.val == target) return root;//找到第一个着色点
        TreeNode left = dfs(root.left, target);//左子树
        TreeNode right = dfs(root.right, target);//右子树
        if (left != null) return left;
        else if (right != null) return right;
        return null;
    }

    //计算当前节点的左子树节点和右子树节点的数量
    public int countNode(TreeNode root) {
        if (root == null) return 0;
        return countNode(root.left) + countNode(root.right) + 1;
    }

    SnapshotArray snapshotArray = new SnapshotArray(3);

    public String solveEquation(String equation) {
        String[] arr = equation.split("=");
        int[] left = parse(arr[0]);
        int[] right = parse(arr[1]);
        int x = left[0] - right[0];
        int n = right[1] - left[1];
        if (x == 0 && n == 0) return "Infinite solutions";
        else if (x == 0 && n != 0) return "No solution";
        return "x=" + n / x;
    }

    public int[] parse(String segment) {
        if (segment.charAt(0) == 'x') segment = "1" + segment;
        segment = segment.replace("+x", "+1x").replace("-x", "-1x");
        segment = segment.replace("-", "+-");
        String[] arr = segment.split("\\+");
        int xCnt = 0;
        int cCnt = 0;
        for (String a : arr) {
            if (a.endsWith("x")) xCnt += Integer.valueOf(a.substring(0, a.length() - 1));
            if (!a.endsWith("x") && !a.equals("")) cCnt += Integer.valueOf(a);
        }
        return new int[]{xCnt, cCnt};
    }


    public int tribonacci(int n) {
        int[] dp = new int[n + 1];
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }


    public String alphabetBoardPath(String target) {
        Map<Character, int[]> maps = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            maps.put((char) (i + 97), new int[]{i / 5, i % 5});
        }
        StringBuilder sb = new StringBuilder();
        int preX = 0, preY = 0;
        for (int i = 0; i < target.length(); i++) {
            int[] cur = maps.get(target.charAt(i));
            int curX = cur[0], curY = cur[1];
            int deltaX = curX - preX;
            int deltaY = curY - preY;
            if (deltaX < 0) append(sb, "U", deltaX);
            if (deltaY < 0) append(sb, "L", deltaY);
            if (deltaX > 0) append(sb, "D", deltaX);
            if (deltaY > 0) append(sb, "R", deltaY);
            sb.append("!");
            preX = curX;
            preY = curY;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public StringBuilder append(StringBuilder sb, String move, int delta) {
        if (delta < 0) delta = -delta;
        while (delta > 0) {
            sb.append(move);
            delta--;
        }
        return sb;
    }


    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    public int arrayPairSum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }


    public int pairMatch(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int max = 0, min = Integer.MAX_VALUE;
        for (int i = 0, j = n - 1; i < n / 2; i++, j--) {
            int temp = nums[i] + nums[j];
            max = Math.max(max, temp);
            min = Math.min(min, temp);
        }
        return max - min;
    }

    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        int[] counter = new int[n];
        Arrays.fill(counter, 1);
        dp[0] = 1;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = Math.max(dp[j] + 1, dp[i]);
                        counter[i] = counter[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        counter[i] += counter[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLen) result += counter[i];
        }
        return result;
    }

    public int longestIncreasingContinuousSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] start = new int[2];
        Arrays.fill(start, 1);
        int maxStart = 1;
        for (int i = 1; i < n; i++) {
            start[i % 2] = 1;
            if (nums[i] > nums[i - 1]) {
                start[i % 2] += start[(i - 1) % 2];
            }
            maxStart = Math.max(maxStart, start[i % 2]);
        }
        int[] end = new int[2];
        int maxEnd = 1;
        Arrays.fill(end, 1);
        for (int i = n - 2; i >= 0; i--) {
            end[i % 2] = 1;
            if (nums[i] > nums[i + 1]) {
                end[i % 2] += end[(i + 1) % 2];
            }
            maxEnd = Math.max(maxEnd, end[i % 2]);
        }

        return Math.max(maxStart, maxEnd);
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int longestCnt = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int curNum = num;
                int curCnt = 1;
                while (set.contains(curNum + 1)) {
                    curNum++;
                    curCnt++;
                }
                longestCnt = Math.max(longestCnt, curCnt);
            }
        }
        return longestCnt;
    }

    public int longestConsecutive2nd(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int n = nums.length;
        int longestCnt = 1;
        int curCnt = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i - 1] + 1 == nums[i]) {
                    curCnt++;
                } else {
                    longestCnt = Math.max(longestCnt, curCnt);
                    curCnt = 1;
                }
            }
        }
        return Math.max(longestCnt, curCnt);
    }


    public int longestCommonSubsequence(String str1, String str2) {
        if (str1 == null || str2 == null || "".equals(str1) || "".equals(str2)) return 0;
        char[] chas1 = str1.toCharArray();
        char[] chas2 = str2.toCharArray();
        int m = chas1.length, n = chas2.length;
        int[][] dp = new int[m][n];
        dp[0][0] = (chas1[0] == chas2[0]) ? 1 : 0;
        for (int i = 1; i < m; i++) dp[i][0] = (chas1[i] == chas2[0]) ? 1 : 0;
        for (int j = 1; j < n; j++) dp[0][j] = (chas1[0] == chas2[j]) ? 1 : 0;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (chas1[i] == chas2[j]) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
            }
        }
        return dp[m - 1][n - 1];
    }

    public int findLength(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) return 0;
        int m = A.length, n = B.length;
        int[][] dp = new int[m][n];
        int result = 0;
        dp[0][0] = (A[0] == B[0]) ? 1 : 0;
        for (int i = 1; i < m; i++) dp[i][0] = (A[i] == B[0]) ? 1 : 0;
        for (int j = 1; j < n; j++) dp[0][j] = (A[0] == B[j]) ? 1 : 0;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (A[i] == B[j]) dp[i][j] = Math.max(0, dp[i - 1][j - 1] + 1);
                result = Math.max(result, dp[i][j]);
            }
        }
        return result;
    }


    public String shiftingLetters(String S, int[] shifts) {
        if (S == null || S.length() == 0 || shifts == null || shifts.length == 0) return null;
        char[] chas = S.toCharArray();
        for (int i = 0; i < shifts.length; i++) {
            for (int j = 0; j <= i; j++) {
                int t = (int) chas[j] + shifts[i];
                char c = t >= 123 ? (char) ((t - 97) % 26 + 97) : (char) t;
                chas[j] = c;
            }
        }
        System.out.println(String.valueOf(chas));
        return String.valueOf(chas);
    }

    private int guess(int num) {
        return 0;
    }

    public int guessNumber(int n) {
        int l = 1, r = n, mid = 0;
        while (l < r) {
            mid = l + (r - l) / 2;
            int res = guess(mid);
            if (res == 0) return mid;
            else if (res == 1) l = mid + 1;
            else if (res == -1) r = mid - 1;
        }
        return l;
    }


    public int getMoneyAmount1st(int n) {
        return calculate1st(1, n);
    }

    public int calculate1st(int low, int high) {
        if (low >= high) return 0;
        int res = Integer.MAX_VALUE;
        for (int i = low; i <= high; i++) {
            int temp = i + Math.max(calculate1st(low, i - 1), calculate1st(i + 1, high));
            res = Math.min(res, temp);
        }
        return res;
    }

    public int getMoneyAmount2nd(int n) {
        return calculate2nd(1, n);
    }

    private int calculate2nd(int low, int high) {
        if (low >= high) return 0;
        int mid = (low + high) / 2;
        int res = Integer.MAX_VALUE;
        for (int i = mid; i <= high; i++) {
            int temp = i + Math.max(calculate2nd(low, i - 1), calculate2nd(i + 1, high));
            res = Math.min(res, temp);
        }
        return res;
    }


    public boolean PredictTheWinner(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = nums[i];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }


    public boolean PredictTheWinner2nd(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = nums[i];
        for (int step = 2; step <= n; step++) {
            for (int i = 0; i < n - step + 1; i++) {
                int j = i + step - 1;
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }


    public boolean PredictTheWinner3rd(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        int[][] dp = new int[n][n];
        int turn = n & 1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (turn == 1) dp[i][i] = nums[i];
            sum += nums[i];
        }
        for (int step = 2; step <= n; step++) {
            turn ^= 1;
            for (int i = 0; i < n - step + 1; i++) {
                int j = i + step - 1;
                if (turn == 1) dp[i][j] = Math.max(nums[i] + dp[i + 1][j], nums[j] + dp[i][j - 1]);
                else dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return 2 * dp[0][n - 1] >= sum;
    }


    public String getHint(String secret, String guess) {
        int bullsCnt = 0, cowsCnt = 0;
        int[] dp1 = new int[10];
        int[] dp2 = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) bullsCnt++;
            else {
                dp1[secret.charAt(i) - '0']++;
                dp2[guess.charAt(i) - '0']++;
            }
        }
        for (int i = 0; i < dp1.length; i++) {
            cowsCnt += Math.min(dp1[i], dp2[i]);
        }
        return String.format("%dA%dB", bullsCnt, cowsCnt);
    }

    public List<String> generatePossibleNextMoves(String s) {
        List<String> resList = new ArrayList<>();
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+')
                resList.add(s.substring(0, i) + "--" + s.substring(i + 2, s.length()));
        }
        return resList;
    }


    public boolean canWin(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.substring(i, i + 2).equals("++")) {
                String next = s.substring(0, i) + "--" + s.substring(i + 2, s.length());
                if (!canWin(next)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Map<String, Boolean> cacheMap = new HashMap<>();

    public boolean canWin1st(String s) {
        if (cacheMap.containsKey(s)) return cacheMap.get(s);
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.substring(i, i + 2).equals("++")) {
                String next = s.substring(0, i) + "--" + s.substring(i + 2, s.length());
                if (!canWin1st(next)) {
                    cacheMap.put(next, false);
                    return true;
                }
            }
        }
        return false;
    }


    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][][] dp = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            dp[i][i][0] = piles[i];
            dp[i][i][1] = 0;
        }
        for (int step = 0; step <= n; step++) {
            for (int i = 0; i <= n - 1; i++) {
                int j = i + step - 1;
                int left = piles[i] + dp[i + 1][j][1];
                int right = piles[j] + dp[i][j - 1][1];
                if (left > right) {
                    dp[i][j][0] = left;
                    dp[i][j][1] = dp[i + 1][j][0];
                } else {
                    dp[i][j][0] = right;
                    dp[i][j][1] = dp[i][j - 1][0];
                }
            }
        }
        return dp[0][n - 1][0] - dp[0][n - 1][1] >= 0;
    }


    public String findContestMatch(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) list.add(String.valueOf(i));
        while (list.size() != 1) {
            List<String> tempList = new ArrayList<>();
            for (int i = 0; i < list.size() / 2; i++) {
                tempList.add("(" + list.get(i) + "," + list.get(list.size() - 1 - i) + ")");
            }
            list = tempList;
        }
        return list.get(0);
    }


    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";
        int left = 0, right = 0, len = Integer.MAX_VALUE;
        String res = "";
        int[] source = new int[256];
        int[] target = new int[256];
        for (char c : t.toCharArray()) target[c]++;
        while (right < s.length()) {
            if (!valid(source, target)) {
                source[s.charAt(right)]++;
                right++;
            }
            while (valid(source, target)) {
                if (right - left < len) {
                    len = Math.min(len, right - left);
                    res = s.substring(left, right);
                }
                source[s.charAt(left)]--;
                left++;
            }

        }
        return res;
    }


    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> resList = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) return resList;
        int[] source = new int[256];
        int[] target = new int[256];
        for (char c : p.toCharArray()) target[c]++;
        int left = 0, right = 0;
        while (right < s.length()) {
            if (!valid1(source, target)) {
                source[s.charAt(right)]++;
                right++;
            }
            while (valid1(source, target) || (right - left) > p.length()) {
                if (valid1(source, target)) {
                    resList.add(left);
                }
                source[s.charAt(left)]--;
                left++;
            }

        }
        return resList;
    }

    private boolean valid1(int[] source, int[] target) {
        for (int i = 0; i < source.length; i++) {
            if (source[i] != target[i]) return false;
        }
        return true;
    }


    //判断source是否都包含target，包含的话true，不包含的话false
    private boolean valid(int[] source, int[] target) {
        for (int i = 0; i < source.length; i++) {
            if (source[i] < target[i]) return false;
        }
        return true;
    }


    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) return false;
        int[] source = new int[256];
        int[] target = new int[256];
        for (char c : s1.toCharArray()) target[c]++;
        int left = 0, right = 0;
        while (right < s2.length()) {
            if (!valid1(source, target)) source[s2.charAt(right++)]++;
            while (valid1(source, target) || (right - left) > s1.length()) {
                if (valid1(source, target)) return true;
                source[s2.charAt(left++)]--;
            }
        }
        return false;
    }


    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int l = 0, r = 0, res = 0;
        int[] dict = new int[256];
        int maxLen = 0;
        while (r < s.length()) {
            dict[s.charAt(r)]++;
            maxLen = Math.max(maxLen, dict[s.charAt(r)]);
            while ((r - l + 1 - maxLen) > k) {
                dict[s.charAt(l++)]--;
            }
            res = Math.max(res, r - l + 1);
            r++;
        }
        return res;
    }


    public boolean duplicate(int numbers[], int length, int[] duplication) {


        return false;
    }


    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) count++;
            }
            if (count <= mid) l = mid + 1;
            else r = mid;

        }
        return r;
    }

    public int findDuplicate1st(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != (i + 1)) return nums[i];
        }
        return -1;
    }

    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty() || stack.peek() == nums[i]) stack.push(nums[i]);
            else stack.pop();
        }
        return stack.peek();
    }


    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) col--;
            else if (matrix[row][col] < target) row++;
        }
        return false;
    }


    public boolean searchMatrixI(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) col--;
            else if (matrix[row][col] < target) row++;
        }
        return false;
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) return null;
        double[] result = new double[nums.length - k + 1];
        PriorityQueue<Long> maxheap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Long> minheap = new PriorityQueue<>();
        int index = 0;
        boolean isEven = ((k & 1) == 0);
        for (int i = 0; i < nums.length; i++) {
            maxheap.offer((long) nums[i]);
            int total = minheap.size() + maxheap.size();
            if (total > k) {
                boolean b = maxheap.contains((long) nums[i - k]) ? maxheap.remove((long) nums[i - k]) : minheap.remove((long) nums[i - k]);
            }
            if (maxheap.size() > 0 && minheap.size() > 0 && maxheap.peek() > minheap.peek()) {
                minheap.offer(maxheap.poll());
            }
            if (minheap.size() > maxheap.size()) maxheap.offer(minheap.poll());
            if (maxheap.size() > (minheap.size() + 1)) minheap.offer(maxheap.poll());
            total = minheap.size() + maxheap.size();
            if (total == k) {
                if (isEven) {
                    result[index++] = ((double) (maxheap.peek() + minheap.peek())) / 2;
                } else {
                    result[index++] = (double) (maxheap.peek());
                }
            }
        }
        return result;
    }


    public boolean hasPathSumI(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == sum) return true;
        return hasPathSumI(root.left, sum - root.val) || hasPathSumI(root.right, sum - root.val);
    }


    public boolean hasPathSumI1st(TreeNode root, int sum) {
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


    public int pathSumIII(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumIIIHelper(root, sum)
                + pathSumIII(root.left, sum)
                + pathSumIII(root.right, sum);
    }


    public int pathSumIIIHelper(TreeNode root, int sum) {
        if (root == null) return 0;
        int res = 0;
        if (root.val == sum) res++;
        res += pathSumIIIHelper(root.left, sum - root.val);
        res += pathSumIIIHelper(root.right, sum - root.val);
        return res;
    }


    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return maxSum;
    }

    private int maxPathSumHelper(TreeNode root) {
        if (root == null) return 0;
        int leftGain = Math.max(maxPathSumHelper(root.left), 0);
        int rightGain = Math.max(maxPathSumHelper(root.right), 0);
        maxSum = Math.max(maxSum, root.val + leftGain + rightGain);
        return root.val + Math.max(leftGain, rightGain);
    }


    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        int start = nums[0];
        int end = 0;
        boolean isContinue = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == (nums[i - 1] + 1)) {
                end = nums[i];
                isContinue = true;
            } else {
                if (isContinue) {
                    result.add(start + "->" + end);
                    isContinue = false;
                } else {
                    result.add(String.valueOf(start));
                }
                start = nums[i];
            }
        }
        if (isContinue) {
            result.add(start + "->" + end);
        } else {
            result.add(String.valueOf(start));
        }
        return result;
    }


    public List<String> summaryRanges1st(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        int start = 0, i = 0;
        while (i < nums.length) {
            while (i < nums.length - 1 && nums[i] + 1 == nums[i + 1]) {
                i++;
            }
            if (start == i) {
                result.add(nums[start] + "");
            } else {
                result.add(nums[start] + "->" + nums[i]);
            }
            i++;
            start = i;
        }
        return result;
    }

    Map<Integer, Integer> rootMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) rootMap.put(inorder[i], i);

        return buildTreeDFS(preorder, 0, preorder.length - 1
                , inorder, 0, inorder.length);
    }

    /**
     * @param preorder 前序序列
     * @param preStart 前序序列开始索引
     * @param preEnd   前序序列结束索引
     * @param inorder  后序序列
     * @param inStart  后序序列开始索引
     * @param inEnd    后序序列结束索引
     * @return
     */
    public TreeNode buildTreeDFS(int[] preorder, int preStart, int preEnd,
                                 int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int mid = rootMap.get(preorder[preStart]);
        if (mid < 0) return null;
        root.left = buildTreeDFS(preorder, preStart + 1, preStart + (mid - inStart)
                , inorder, inStart, mid - 1);
        root.right = buildTreeDFS(preorder, preStart + (mid - inStart) + 1, preEnd
                , inorder, mid + 1, inEnd);
        return root;
    }

    public String convert(String s, int numRows) {


        return null;
    }


    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        char[] chas1 = word1.toCharArray();
        char[] chas2 = word2.toCharArray();
        int row = chas1.length + 1;
        int col = chas2.length + 1;
        int[][] dp = new int[row][col];
        //init first col
        for (int i = 0; i < row; i++) {
            dp[i][0] = i;
        }
        //init first row
        for (int j = 0; j < col; j++) {
            dp[0][j] = j;
        }
        //general case
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (chas1[i - 1] == chas2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 2;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
            }
        }
        return dp[row - 1][col - 1];
    }

    public ListNode reverseList(ListNode head) {
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


    public ListNode reverseList1st(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList1st(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }


    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = pre.next;
        for (int i = 1; i < m; i++) {
            pre = pre.next;
            cur = cur.next;
        }
        for (int i = 0; i < n - m; i++) {
            ListNode tmp = cur.next;
            cur.next = tmp.next;
            tmp.next = pre.next;
            pre.next = tmp;
        }
        return dummy.next;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) return results;
        boolean[] visited = new boolean[nums.length];
        permuteDFS(nums, results, new ArrayList<>(), visited);
        return results;
    }

    private void permuteDFS(int[] nums, List<List<Integer>> results, ArrayList<Integer> levelList, boolean[] visited) {
        if (levelList.size() == nums.length) {
            results.add(new ArrayList<>(levelList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            levelList.add(nums[i]);
            permuteDFS(nums, results, levelList, visited);
            levelList.remove(levelList.size() - 1);
            visited[i] = false;
        }
    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) return results;
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        permuteUniqueDFS(nums, results, new ArrayList<>(), visited);
        return results;
    }

    private void permuteUniqueDFS(int[] nums, List<List<Integer>> results, ArrayList<Integer> levelList, boolean[] visited) {
        if (levelList.size() == nums.length) {
            results.add(new ArrayList<>(levelList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || ((i - 1) >= 0 && visited[i - 1] && nums[i] == nums[i - 1])) {
                continue;
            }
            visited[i] = true;
            levelList.add(nums[i]);
            permuteUniqueDFS(nums, results, levelList, visited);
            levelList.remove(levelList.size() - 1);
            visited[i] = false;
        }
    }

    //翻转一个链表，返回翻转后的头节点
    public ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //要翻转节点的部分的前一个节点，end为要翻转节点部分的最后一个节点
        ListNode pre = dummy, end = dummy;
        while (end != null) {
            //找到end,如果end指向null，说明不够一组，break掉
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break;
            //start是要翻转节点部分的第1个节点，next是要翻转节点部分的后一个节点
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            //这时的start节点已经翻转了
            start.next = next;
            pre = start;
            end = start;
        }
        return dummy.next;
    }


    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> results = new ArrayList<>();
        combineDFS(results, new ArrayList<>(), 1, n, k);
        return results;
    }

    private void combineDFS(List<List<Integer>> results, ArrayList<Integer> levelList, int index, int n, int k) {
        for (int i = index; i <= n; i++) {
            levelList.add(i);
            if (levelList.size() == k) {
                results.add(new ArrayList<>(levelList));
                levelList.remove(levelList.size() - 1);
                continue;
            }
            combineDFS(results, levelList, i + 1, n, k);
            levelList.remove(levelList.size() - 1);
        }
    }

    public String originalDigits(String s) {
        char[] chas = s.toCharArray();
        int[] count = new int[256];
        for (char c : chas) {
            count[c]++;
        }
        int[] sign = new int[10];
        sign[0] = count['z'];
        sign[2] = count['w'];
        sign[4] = count['u'];
        sign[6] = count['x'];
        sign[8] = count['g'];
        sign[3] = count['h'] - sign[8];
        sign[5] = count['f'] - sign[4];
        sign[7] = count['s'] - sign[6];
        sign[9] = count['i'] - sign[5] - sign[6] - sign[8];
        sign[1] = count['n'] - sign[7] - 2 * sign[9];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < sign[i]; j++) {
                sb.append(i);
            }
        }
        return sb.toString();
    }


    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int len = nums.length;
        int firstIndex = -1;
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstIndex = i;
                break;
            }
        }
        if (firstIndex == -1) {
            reverse(nums, 0, len - 1);
            return;
        }
        int secondIndex = -1;
        for (int i = len - 1; i >= 0; i--) {
            if (nums[i] > nums[firstIndex]) {
                secondIndex = i;
                break;
            }
        }
        swap(nums, firstIndex, secondIndex);
        reverse(nums, firstIndex + 1, len - 1);
    }


    public void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }


    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        return getPermutationDFS(nums, new ArrayList<String>(), used, 0, n, k);
    }

    private String getPermutationDFS(int[] nums, ArrayList<String> levelList, boolean[] used, int depth, int n, int k) {
        if (depth == n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < levelList.size(); i++) {
                sb.append(levelList.get(i));
            }
            return sb.toString();
        }
        int cur = factorial(n - 1 - depth);
        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            if (cur < k) {
                k -= cur;
                continue;
            }
            levelList.add(nums[i] + "");
            used[i] = true;
            return getPermutationDFS(nums, levelList, used, depth + 1, n, k);
        }
        return null;
    }


    public int factorial(int n) {
        int res = 1;
        while (n > 0) {
            res *= n;
            n--;
        }
        return res;
    }

    public ListNode swapPairs1st(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next = head.next;
        head.next = swapPairs1st(head);
        next.next = head;
        return next;
    }


    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = (j == 0) ? 1 : dp[i][j - 1] + 1;
                    int width = dp[i][j];
                    for (int k = i; k >= 0; k--) {
                        width = Math.min(width, dp[k][j]);
                        res = Math.max(res, width * (i - k + 1));
                    }
                }
            }
        }
        return res;
    }

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int maxArea = 0;
        for (int i = 0; i <= n; i++) {
            int curHeight = (i == n) ? -1 : heights[i];
            while (!stack.isEmpty() && curHeight <= heights[stack.peek()]) {
                int stackHeight = heights[stack.pop()];
                int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
                maxArea = Math.max(maxArea, width * stackHeight);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public int maximalRectangle1st(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int maxArea = 0;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[j] = (matrix[i][j] == '1') ? dp[j] + 1 : 0;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(dp));
        }
        return maxArea;
    }


    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        s = s.trim();
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                break;
            }
            res++;
        }
        return res;
    }


    public int[] numsSameConsecDiff(int N, int K) {
        if (N == 1) return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> resultList = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            helper(resultList, i, 0, N, K, 0);
        }
        int[] resArr = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            resArr[i] = resultList.get(i);
        }
        return resArr;
    }

    private void helper(List<Integer> resultList, int temp, int count, int N, int K, int levelAns) {
        levelAns += temp;
        count++;
        if (count == N) {
            resultList.add(levelAns);
            return;
        }
        if (temp - K >= 0) {
            helper(resultList, temp - K, count, N, K, levelAns * 10);
        }
        if (temp + K < 10 && K != 0) {
            helper(resultList, temp + K, count, N, K, levelAns * 10);
        }
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head.next;
        if (fast == null) return slow;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode node = slow.next;
        slow.next = null;
        return node;
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null) return head;
        ListNode dummyBefore = new ListNode(0);
        ListNode before = dummyBefore;
        ListNode dummyAfter = new ListNode(0);
        ListNode after = dummyAfter;
        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        before.next = dummyAfter.next;
        after.next = null;
        return dummyBefore.next;
    }


    public ListNode reverseKGroup1st(ListNode head, int k) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;

        while (head.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break;
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverseList1st(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }


    public List<String> binaryTreePaths(TreeNode root) {
        List<String> resultList = new ArrayList<>();
        if (root == null) return resultList;
        binaryTreePathsDFS(resultList, new ArrayList<>(), root);
        return resultList;
    }

    private void binaryTreePathsDFS(List<String> resultList, List<Integer> levelList, TreeNode node) {
        if (node == null) return;
        levelList.add(node.val);
        if (node.left == null && node.right == null) {
            resultList.add(convertList2Str(levelList));
            levelList.remove(levelList.size() - 1);
            return;
        }

        binaryTreePathsDFS(resultList, levelList, node.left);
        binaryTreePathsDFS(resultList, levelList, node.right);
        levelList.remove(levelList.size() - 1);
    }

    private String convertList2Str(List<Integer> levelList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < levelList.size() - 1; i++) {
            sb.append(levelList.get(i)).append("->");
        }
        sb.append(levelList.get(levelList.size() - 1));
        return sb.toString();
    }


    public int search1st(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] >= nums[left]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return nums[left] == target ? left : -1;
    }

    public int findMin1st(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= nums[r]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }


    public boolean searchII1st(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return true;
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }


    public int mySqrt(int x) {
        if (x == 0) return 0;
        long left = 1, right = x / 2;
        while (left < right) {
            long mid = left + (right - left) / 2;
            System.out.println(String.format("left:%d,mid:%d,right:%d", left, mid, right));
            long tmp = mid * mid;
            if (tmp == x) {
                return (int) mid;
            } else if (tmp > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return (int) left;
    }

    public int balancedStringSplit(String s) {
        int res = 0;
        if (s == null || s.length() == 0) return res;
        int cntR = 0, cntL = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') cntR++;
            else cntL++;
            if (cntR == cntL) {
                res++;
                cntR = 0;
                cntL = 0;
            }
        }
        return res;
    }

    int index = 0;

    public String reverseParentheses(String s) {
        return reverseParenthesesDFS(s.toCharArray()).toString();
    }


    private StringBuilder reverseParenthesesDFS(char[] chas) {
        StringBuilder sb = new StringBuilder();
        while (index < chas.length && chas[index] != '(' && chas[index] != ')') {
            sb.append(chas[index]);
            index++;
        }
        if (index == chas.length) {
            return sb;
        } else if (chas[index] == ')') {
            index++;
            return sb;
        } else if (chas[index] == '(') {
            index++;
            StringBuilder cur = reverseParenthesesDFS(chas);
            StringBuilder next = reverseParenthesesDFS(chas);
            sb.append(cur.reverse().toString()).append(next.toString());
            return sb;
        }
        return null;
    }


    private void reverseArr(char[] src, int start, int end) {
        while (start < end) {
            swap(src, start++, end--);
        }
    }

    private void swap(char[] arr, int x, int y) {
        char tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    public String reverseParentheses1st(String s) {
        StringBuilder res = new StringBuilder();
        char[] chas = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] == '(') {
                stack.push(i);
            } else if (chas[i] == ')') {
                reverseArr(chas, stack.pop() + 1, i - 1);
            }
        }
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] == '(' || chas[i] == ')') {
                continue;
            }
            res.append(chas[i]);
        }
        return res.toString();
    }


    //快慢指针
    public int findDuplicate2nd(int[] nums) {
        int fast = nums[nums[0]];
        int slow = nums[0];

        while (fast != slow) {
            fast = nums[nums[fast]];
            slow = nums[slow];
        }

        slow = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }

        return slow;
    }

    ListNode successor = null;

    // 将链表的前 n 个节点反转（n <= 链表长度）
    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            //记录第n+1个节点
            successor = head.next;
            return head;
        }
        //head.next为起点，需要反转前n-1个节点
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        //反转后的head节点和后面的节点连起来
        head.next = successor;
        return last;
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return l1 == null ? l2 : l1;
        if (l1.val > l2.val) {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        } else {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
    }


    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (sum[i] - sum[j] == k) res++;
            }
        }
        return res;
    }


    public int subarraySum2nd(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        int sumEnd = 0, res = 0;
        for (int i = 0; i < n; i++) {
            sumEnd += nums[i];
            int remain = sumEnd - k;
            if (preSum.containsKey(remain)) res += preSum.get(remain);
            preSum.put(sumEnd, preSum.getOrDefault(sumEnd, 0) + 1);

        }
        return res;
    }


    private int intervalSchedule(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int count = 1;
        int baseEnd = intervals[0][1];
        int len = intervals.length;
        for (int i = 1; i < len; i++) {
            int start = intervals[i][0];
            if (start >= baseEnd) {
                count++;
                baseEnd = intervals[i][1];
            }
        }
        return count;
    }


    public int eraseOverlapIntervals(int[][] intervals) {
        int len = intervals.length;
        return len - intervalSchedule(intervals);
    }


    public int firstMissingPositive(int[] nums) {
        BitSet bitSet = new BitSet();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 1 && nums[i] <= n + 1) {
                bitSet.set(nums[i]);
            }
        }
        for (int i = 1; i <= n; i++) {
            if (!bitSet.get(i)) return i;
        }
        return n + 1;
    }


    public int myAtoi(String str) {
        str = str.trim();
        if (str == null || str.length() == 0) return 0;
        char firstChar = str.charAt(0);
        int sign = 1, start = 0;
        long res = 0;
        if (firstChar == '+') {
            sign = 1;
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }
        for (int i = start; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return (int) (res * sign);
            res = res * 10 + str.charAt(i) - '0';
            if (sign == 1 && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && res > Integer.MAX_VALUE) return Integer.MIN_VALUE;
        }
        return (int) (res * sign);
    }


    public void setZeroes1(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }


    }


    public void setZeroes(int[][] matrix) {
        Boolean isCol = false;
        int R = matrix.length;
        int C = matrix[0].length;

        for (int i = 0; i < R; i++) {

            // Since first cell for both first row and first column is the same i.e. matrix[0][0]
            // We can use an additional variable for either the first row/column.
            // For this solution we are using an additional variable for the first column
            // and using matrix[0][0] for the first row.
            if (matrix[i][0] == 0) {
                isCol = true;
            }

            for (int j = 1; j < C; j++) {
                // If an element is zero, we set the first element of the corresponding row and column to 0
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // Iterate over the array once again and using the first row and first column, update the elements.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // See if the first row needs to be set to zero as well
        if (matrix[0][0] == 0) {
            for (int j = 0; j < C; j++) {
                matrix[0][j] = 0;
            }
        }

        // See if the first column needs to be set to zero as well
        if (isCol) {
            for (int i = 0; i < R; i++) {
                matrix[i][0] = 0;
            }
        }
    }


    public int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int res = 0;
        for (int i = 0; i < s.length(); ) {
            if ((i + 1) < s.length() && map.containsKey(s.substring(i, i + 2))) {
                res += map.get(s.substring(i, i + 2));
                i += 2;
            } else {
                res += map.get(s.substring(i, i + 1));
                i += 1;
            }
        }
        return res;
    }


    public List<String> generateParenthesis(int n) {
        List<String> resList = new ArrayList<>();
        if (n == 0) return resList;
        generateParenthesisDFS("", n, n, resList);
        return resList;
    }

    /**
     * @param curStr  当前层的当前字符
     * @param left    左括号剩余个数
     * @param right   右括号剩余个数
     * @param resList 结果集
     */
    private void generateParenthesisDFS(String curStr, int left, int right, List<String> resList) {
        //返回条件:当左括号剩余的个数与右括号剩余的个数都为0时
        if (left == 0 && right == 0) {
            resList.add(curStr);
            return;
        }
        //左边括号还有剩余时，一直减少，每次left-=1，给curStr带上一个"("
        if (left > 0) {
            generateParenthesisDFS(curStr + "(", left - 1, right, resList);
        }
        //右边括号还有剩余时，一直减少，每次right-=1，给curStr带上一个")",但是右边括号剩余的个数一定要大于左边括号剩余的个数
        if (right > 0 && right > left) {
            generateParenthesisDFS(curStr + ")", left, right - 1, resList);
        }
    }


    public List<String> generateParenthesis2nd(int n) {
        List<String> resList = new ArrayList<>();
        if (n == 0) return resList;
        generateParenthesis2ndDFS("", 0, 0, n, resList);
        return resList;
    }

    /**
     * @param curStr  当前层的当前字符
     * @param left    左括号使用的个数
     * @param right   右括号使用的个数
     * @param limit   左右括号的上限
     * @param resList 结果集
     */
    private void generateParenthesis2ndDFS(String curStr, int left, int right, int limit, List<String> resList) {
        //当左括号使用的个数与右括号使用的个数同到达上限时，收集结果集，返回
        if (left == limit && right == limit) {
            resList.add(curStr);
            return;
        }
        //当左括号使用的个数小于上限值，left+=1,然后curStr组装"(",再次进行回溯
        if (left < limit) {
            generateParenthesis2ndDFS(curStr + "(", left + 1, right, limit, resList);
        }
        //当右括号使用的个数小于上限值并且左括号使用个数要大于右括号使用个数，right+=1,然后curStr组装")",再次进行回溯
        if (right < limit && left > right) {
            generateParenthesis2ndDFS(curStr + ")", left, right + 1, limit, resList);
        }
    }

    public List<String> generateParenthesisII(int n) {
        List<String> resList = new ArrayList<>();
        if (n == 0) return resList;
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));
        int limit = 2 * n;
        while (limit > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curNode = queue.poll();
                if (curNode.left > 0) {
                    queue.offer(new Node(curNode.curStr + "(", curNode.left - 1, curNode.right));
                }
                if (curNode.right > 0 && curNode.right > curNode.left) {
                    queue.offer(new Node(curNode.curStr + ")", curNode.left, curNode.right - 1));
                }
            }
            limit--;
        }
        while (!queue.isEmpty()) {
            resList.add(queue.poll().curStr);
        }
        return resList;
    }


    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int len = lists.length;
        return mergeKLists(lists, 0, len - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        int mid = l + (r - l) / 2;
        ListNode l1 = mergeKLists(lists, l, mid);
        ListNode l2 = mergeKLists(lists, mid + 1, r);
        return mergeTwoSortedListNode(l1, l2);
    }

    private ListNode mergeTwoSortedListNode(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoSortedListNode(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoSortedListNode(l1, l2.next);
            return l2;
        }
    }


    List<List<String>> resList = new ArrayList<>();

    public List<List<String>> partition(String s) {
        if (s == null) return resList;
        partitionDFS(s, new ArrayList<>(), 0);
        return resList;
    }

    /**
     * @param s         字符串
     * @param levelList 符合条件的这一层的结果集
     * @param start     字符的起始位置的index
     */
    private void partitionDFS(String s, List<String> levelList, int start) {
        //当start的位置走到字符串的末尾时，开始返回
        if (start == s.length()) {
            resList.add(new ArrayList<>(levelList));
            return;
        }
        //从当前位置start开始回溯
        for (int end = start; end < s.length(); end++) {
            //判断是否为回问，不是回文的话，开始剪枝
            if (isPalindrome(s, start, end)) {
                levelList.add(s.substring(start, end + 1));
                partitionDFS(s, levelList, end + 1);
                levelList.remove(levelList.size() - 1);
            }
        }
    }

    public boolean isPalindrome(String str, int l, int r) {
        while (l < r) {
            if (str.charAt(l) != str.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }


    public List<List<Integer>> generate1st(int numRows) {
        List<List<Integer>> resList = new ArrayList<>();
        if (numRows == 0) return resList;
        resList.add(new ArrayList<>());
        resList.get(0).add(1);
        for (int i = 1; i < numRows; i++) {
            List<Integer> pre = resList.get(i - 1);
            List<Integer> cur = new ArrayList<>();
            cur.add(1);
            for (int j = 0; j < pre.size() - 1; j++) {
                cur.add(pre.get(j) + pre.get(j + 1));
            }
            cur.add(1);
            resList.add(cur);
        }
        return resList;
    }


    public List<List<Integer>> generate2nd(int numRows) {
        List<List<Integer>> resList = new ArrayList<>();
        if (numRows == 0) return resList;
        for (int i = 0; i < numRows; i++) {
            List<Integer> res = generate2ndHelper(i);
            resList.add(res);
        }

        return resList;
    }


    public String reverseVowels(String s) {
        List<Character> vowel = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        char[] chas = s.toCharArray();
        int left = 0, right = chas.length - 1;
        while (left < right) {
            if (vowel.contains(chas[left]) && vowel.contains(chas[right])) {
                swap(chas, left++, right--);
            } else if (!vowel.contains(chas[left])) {
                left++;
            } else if (!vowel.contains(chas[right])) {
                right--;
            }
        }
        return String.valueOf(chas);
    }


    private List<Integer> generate2ndHelper(int row) {
        List<Integer> cur = new ArrayList<>();
        if (row == 0) {
            cur.add(1);
            return cur;
        }

        List<Integer> pre = generate2ndHelper(row - 1);
        cur.add(1);
        for (int i = 0; i < pre.size() - 1; i++) {
            cur.add(pre.get(i) + pre.get(i + 1));
        }
        cur.add(1);
        return cur;
    }


    public List<Integer> getRow(int rowIndex) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            cur = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                //记录每一行的第一个与最末尾的1
                if (j == 0 || j == i) {
                    cur.add(1);
                } else {
                    cur.add(pre.get(j - 1) + pre.get(j));
                }
            }
            pre = cur;
        }
        return cur;
    }


    public List<Integer> getRow1st(int rowIndex) {
        List<Integer> cur = new ArrayList<>();
        int preValue = 1;
        cur.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            //起始的坐标跳过第一个数，并且在结尾时跳过最后一个数(j<i)
            for (int j = 1; j < i; j++) {
                int tmp = cur.get(j);
                cur.set(j, preValue + cur.get(j));
                preValue = tmp;
            }
            //最后一个数是1
            cur.add(1);
        }
        return cur;
    }


    public List<Integer> getRow2nd(int rowIndex) {
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i - 1; j >= 1; j--) {
                cur.set(j, cur.get(j - 1) + cur.get(j));
            }
            cur.add(1);
        }
        return cur;
    }

    public ListNode reverseList3rd(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode curNode = reverseList3rd(head.next);
        head.next.next = head;
        head.next = null;
        return curNode;
    }


    public int fib(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return fib(N - 1) + fib(N - 2);
    }


    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) nums[index++] = nums[i];
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public void moveZeroes1st(int[] nums) {
        if (nums == null || nums.length == 0) return;
        for (int index = 0, i = 0; i < nums.length; i++) {
            if (nums[i] != 0) swap(nums, nums[index++], nums[i]);
        }
    }


    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                swap(nums, slow++, fast);
            }
        }
        return slow + 1;
    }


    public int removeDuplicatesII(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int slow = 1;
        //第一个条件是fast对应的值与slow（慢指针最后一个停留的值）不相等，进逻辑
        //第二个条件是fast对应的值与slow（慢指针最后一个停留的值）相等，但是和slow前一个值不相等，即满足同一个元素只有最多两个
        for (int fast = 2; fast < nums.length; fast++) {
            if (nums[slow] != nums[fast] || (nums[slow] == nums[fast] && nums[slow - 1] != nums[fast])) {
                nums[++slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>();
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

    public int findKthLargest2nd(int[] nums, int k) {
        int len = nums.length, left = 0, right = len - 1;
        int target = len - k;//这是目标值的位置
        while (true) {
            int index = partition(nums, left, right);
            if (index == target) {
                return nums[index];
            } else if (index > target) {//当index值在target值右边，说明right的范围大了
                right = index - 1;
            } else if (index < target) {
                left = index + 1;
            }
        }
    }


    /**
     * 这个方法是排序中的partition方法，达到一个目的
     * [left,j-1]都是小于nums[left]的数
     * [j] 是nums[left]
     * [j+1,right]是大于等于nums[left]的数
     *
     * @param nums
     * @param left
     * @param right
     * @return 划分好partition后的pivot值的下标
     */
    public int partition(int[] nums, int left, int right) {
        if (left < right) {
            int ranIndex = left + new Random().nextInt(right - left);
            swap(nums, left, ranIndex);
        }
        int pivot = nums[left];//选择此值作为基准值
        int j = left;//j
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {//当比pivot的值小，交换j的下一个位置的数，使得大体完成小于pivot的值在左边
                swap(nums, ++j, i);
            }
        }
        swap(nums, j, left);//这一步是为了将pivot值替换到中间，一开始它在第一个位置
        return j;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int t = m-- + n-- - 1;
        while (m >= 0 && n >= 0) {
            nums1[t--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        while (n >= 0) {
            nums1[t--] = nums2[n--];
        }
    }

    public int[] twoSum(int[] numbers, int target) {
        int start = 0, end = numbers.length - 1;
        while (start < end) {
            int tmp = numbers[start] + numbers[end];
            if (tmp == target) {
                return new int[]{start, end};
            } else if (tmp > target) {
                end--;
            } else if (tmp < target) {
                start++;
            }
        }
        return new int[]{-1, -1};
    }


    public int searchInsert(int[] nums, int target) {
        if (nums[nums.length - 1] < target) return nums.length;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] >= target) {
                right = mid;
            }
        }
        return left;
    }


    public int search9th(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
//            System.out.println(String.format("left:%d,mid:%d,right:%d", left, mid, right));
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    public int searchA(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left < right) {
//            int mid = left + (right - left + 1) / 2;
            int mid = left + (right - left) / 2;
//            int mid = (left + right + 1) >>> 1;
            System.out.println(String.format("left:%d,mid:%d,right:%d", left, mid, right));
            if (nums[mid] < nums[right]) {//这种情况说明[mid,right]区间上是严格递增的
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            } else if (nums[mid] >= nums[right]) {//这种情况说明[left,mid]区间上是严格递增的
                if (nums[left] <= target && target <= nums[mid - 1]) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
        }
        return nums[left] == target ? left : -1;
    }


    public int searchB(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }

        int left = 0;
        int right = len - 1;
        while (left < right) {

            int mid = (left + right + 1) >>> 1;
            System.out.println(String.format("left:%d,mid:%d,right:%d", left, mid, right));
            if (nums[mid] < nums[right]) {

                // 使用上取整的中间数，必须在上面的 mid 表达式的括号里 + 1
                if (nums[mid] <= target && target <= nums[right]) {
                    // 下一轮搜索区间是 [mid, right]
                    left = mid;
                } else {
                    // 只要上面对了，这里不用思考，可以一下子写出来
                    right = mid - 1;
                }

            } else {

                // [left, mid] 有序，但是为了和上一个 if 有同样的收缩行为，
                // 我们故意只认为 [left, mid - 1] 有序
                // 当区间只有 2 个元素的时候 int mid = (left + right + 1) >>> 1; 一定会取到右边
                // 此时 mid - 1 不会越界，就是这么刚刚好

                if (nums[left] <= target && target <= nums[mid - 1]) {
                    // 下一轮搜索区间是 [left, mid - 1]
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
        }

        // 有可能区间内不存在目标元素，因此还需做一次判断
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }


    public int searchC(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            System.out.println(String.format("left:%d,mid:%d,right:%d", left, mid, right));
//            int mid = left + (right - left) / 2;
//            int mid = (left + right + 1) >>> 1;

            if (nums[mid] == target) return mid;
            else if (nums[mid] >= nums[left]) {
                if (nums[mid] >= target && target >= nums[left]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < nums[left]) {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }


        }
        return nums[left] == target ? left : -1;
    }

    public int[] searchRange(int[] nums, int target) {
        int start = helper(nums, target);
        int end = helper(nums, target + 1) - 1;

        return new int[]{start, end};

    }

    /**
     * 此方法返回nums中的等于target的最左边的一个数的index
     *
     * @param nums
     * @param target
     * @return
     */
    public int helper(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            System.out.println(String.format("left:%d,mid:%d,right:%d", left, mid, right));
            if (nums[mid] >= target) {//说明[mid,right]之间都是大于等于target的，数组是排序递增
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] <= arr[mid + k] - x) {
                right = mid;
            } else if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }


    public int longestStrChain(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        int n = words.length;
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            String a = words[i];
            for (int j = i + 1; j < n; j++) {
                String b = words[j];
                if (isPredecessor(a, b)) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                    res = Math.max(dp[j], res);
                }
            }
        }
        return res + 1;
    }

    /**
     * 判断a是否是b的前身 是返回true 如 "bda" 是"bdca"的前身
     *
     * @param a
     * @param b
     * @return
     */
    private boolean isPredecessor(String a, String b) {
        int i = 0, j = 0;
        int m = a.length(), n = b.length();
        if ((m + 1) != n) return false;
        while (i < m && j < n) {
            if (a.charAt(i) == b.charAt(j)) i++;
            j++;
        }
        return i == m;
    }


    public int longestStrChain1st(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int[] arr = new int[17];
        Arrays.fill(arr, -1);
        int n = words.length;
        for (int i = 0; i < n; i++) {
            arr[words[i].length()] = i;
        }
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 0; i < n; i++) {
            int target = words[i].length() - 1;
            int index = arr[target];
            while (index >= 0 && words[index].length() == target) {
                if (isPredecessor(words[index], words[i])) {
                    dp[i] = Math.max(dp[i], dp[index] + 1);
                }
                index--;
            }
            res = Math.max(dp[i], res);
        }
        return res;
    }

    int res = 0;

    public int longestStrChain2nd(String[] words) {
        int min = 0, max = 16;//最小字符长度，最大字符长度
        //K为字符长度，Set为该字符长度的word集合
        Map<Integer, Set<String>> map = new HashMap<>();
        for (String word : words) {
            map.putIfAbsent(word.length(), new HashSet<>());
            map.get(word.length()).add(word);
            min = Math.min(min, word.length());
            max = Math.max(max, word.length());
        }
        for (int len = min; len <= max; len++) {
            Set<String> curSet = map.get(len);
            if (curSet == null) continue;//当set没有值时，无需遍历
            if ((max + 1 - len <= res)) continue;//最大长度+1-当前的长度<=res，res更加符合题意
            for (String cur : curSet) {
                findNext(map, len, cur);
            }
        }
        return res;
    }

    /**
     * @param map
     * @param len      当前字符的长度
     * @param levelStr 当前字符
     */
    private void findNext(Map<Integer, Set<String>> map, int len, String levelStr) {
        res = Math.max(res, levelStr.length() + 1 - len);//记录结果集
        Set<String> curSet = map.get(levelStr.length() + 1);//
        if (curSet == null) return;//退出条件
        Iterator<String> it = curSet.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (isPredecessor(levelStr, next)) {
                findNext(map, len, next);
                it.remove();
            }
        }
    }


    public List<String> letterCasePermutation(String S) {
        int n = S.length();
        List<String> resList = new ArrayList<>();
        if (n == 0) return resList;
        char[] chas = new char[n];
        letterCasePermutationDFS(S, 0, n, chas, resList);
        return resList;
    }


    /**
     * @param s       源字符串
     * @param index   当前的char的位置
     * @param n       len(s)
     * @param chas    组装的char arr
     * @param resList 结果集
     */
    private void letterCasePermutationDFS(String s, int index, int n, char[] chas, List<String> resList) {
        if (index == n) {//出口条件
            resList.add(new String(chas));
            return;
        }
        //字符串本身
        chas[index] = s.charAt(index);
        letterCasePermutationDFS(s, index + 1, n, chas, resList);
        if (Character.isLetter(s.charAt(index))) {//当前字符是否是字母
            chas[index] = (char) (s.charAt(index) ^ (32));
            letterCasePermutationDFS(s, index + 1, n, chas, resList);
        }
    }


    public int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                swap(nums, nums[i], i);
            }
        }
        return -1;
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        int x = 0, y = col - 1;
        while (x < row && y >= 0) {
            if (x < row && y >= 0 && matrix[x][y] == target) {
                return true;
            }
            while (x < row && y >= 0 && matrix[x][y] > target) {
                y--;
            }
            while (x < row && y >= 0 && matrix[x][y] < target) {
                x++;
            }
        }
        return false;
    }

    public String replaceSpace(String s) {
        if (s == null || s.length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') sb.append("%20");
            else sb.append(s.charAt(i));
        }
        return sb.toString();
    }


    public int hammingDistance(int x, int y) {
        int n = x ^ y;
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n >>= 1;
        }
        return count;
    }


    public int totalHammingDistance(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                res += hammingDistance(nums[i], nums[j]);
            }
        }
        return res;
    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        Stack<ListNode> stackA = new Stack<>();
        Stack<ListNode> stackB = new Stack<>();
        while (headA != null) {
            stackA.push(headA);
            headA = headA.next;
        }
        while (headB != null) {
            stackB.push(headB);
            headB = headB.next;
        }
        ListNode res = null;
        while (!stackA.isEmpty() && !stackB.isEmpty() && stackA.peek() == stackB.peek()) {
            res = stackA.pop();
            stackB.pop();
        }
        return res;
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return root;
        if (root.left == null && root.right == null) return root;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        if (root.left != null) {
            mirrorTree(root.left);
        }
        if (root.right != null) {
            mirrorTree(root.right);
        }
        return root;
    }


//    public int[] levelOrder(TreeNode root) {
//        if (root == null) return new int[0];
//        List<Integer> results = new ArrayList<>();
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            TreeNode cur = queue.poll();
//            results.add(cur.val);
//            if (cur.left != null) queue.offer(cur.left);
//            if (cur.right != null) queue.offer(cur.right);
//        }
//        return results.stream().mapToInt(Integer::intValue).toArray();
//    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int parentVal = root.val;
        if (p.val > parentVal && q.val > parentVal) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (p.val < parentVal && q.val < parentVal) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }


    public int[] constructArr(int[] a) {
        if (a == null || a.length == 0) return new int[]{};
        int n = a.length;
        int[] b = new int[n];
        b[0] = 1;
        for (int i = 1; i < n; i++) {
            b[i] = a[i - 1] * b[i - 1];
        }
        int tmp = 1;
        for (int i = n - 2; i >= 0; i--) {
            tmp *= a[i + 1];
            b[i] *= tmp;
        }
        return b;
    }


    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length != popped.length) return false;
        int index = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }


    public int[][] findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();
        int l = 1, r = 2;
        int sum = 0;
        int[][] result = new int[0][];
        while (l < r && r <= (target / 2 + 1)) {
            if (sum == 0) sum = l + r;
            if (sum < target) {
                r++;
                sum += r;
            } else if (sum > target) {
                sum -= l;
                l++;
            } else if (sum == target) {
//                收集结果集
                int[] sub = new int[r - l + 1];
                for (int i = l; i <= r; i++) {
                    sub[i - l] = i;
                }
                result = buildRes(result, sub);
                sum -= l;
                l++;
            }
        }

        return result;
    }


    public int[][] buildRes(int[][] result, int[] sub) {
        int[][] tmp = Arrays.copyOf(result, result.length + 1);
        tmp[tmp.length - 1] = sub;
        return tmp;
    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int index = 0;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
            if ((i - k) == deque.peekFirst()) {
                deque.pollFirst();
            }
            if (i >= (k - 1)) {
                res[index++] = nums[deque.peekFirst()];
            }
        }
        return res;
    }


    public int missingNumber(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (mid == nums[mid]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[l] == l ? nums[l] + 1 : l;
    }

    public int hammingWeight(int n) {

        int count = 0;
        while (n != 0) {
            count++;
            n &= (n - 1);
        }
        return count;
    }

    public int[] printNumbers(int n) {
        int max = (int) Math.pow(10, n);
        int[] res = new int[max - 1];
        for (int i = 0; i < max - 1; i++) {
            res[i] = i + 1;
        }
        return res;
    }


    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int i = 0;
        while (i < n) {
            if ((i + 2) < n && s.charAt(i + 2) == '#') {
                sb.append((char) (Integer.valueOf(s.substring(i, i + 2)) + 96));
                i += 2;
            } else {
                sb.append((char) (Integer.valueOf(s.substring(i, i + 1)) + 96));
            }
            i += 1;
        }
        return sb.toString();
    }


    public boolean isSubStructure(TreeNode A, TreeNode B) {
        boolean res = false;
        if (A != null && B != null) {
            if (A.val == B.val) res = helper(A, B);
            if (!res) res = isSubStructure(A.left, B);
            if (!res) res = isSubStructure(A.right, B);
        }
        return res;
    }


    public boolean helper(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root1.val != root2.val) return false;
        return helper(root1.left, root2.left) && helper(root1.right, root2.right);
    }


    public int sumNums(int n) {
        int res = 0;
        boolean b = (n > 0) && (res = n + sumNums(n - 1)) > 0;
        return res;
    }

    class One {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean res = false;
        int row, col;

        public boolean exist(char[][] board, String word) {


            row = board.length;
            col = board[0].length;
            boolean[][] visited = new boolean[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    dfs(board, i, j, visited, word, 0);
                }
            }
            return res;
        }

        private void dfs(char[][] board, int i, int j, boolean[][] visited, String word, int index) {
            if (i < 0 || i >= row || j < 0 || j >= col || visited[i][j] ||
                    res || index >= word.length()) return;
            if (board[i][j] == word.charAt(index)) {
                if (index == word.length() - 1) {
                    res = true;
                    return;
                }

                for (int k = 0; k < directions.length; k++) {
                    visited[i][j] = true;
                    dfs(board, i + directions[k][0], j + directions[k][1], visited, word, index + 1);
                    visited[i][j] = false;
                }

            }

        }
    }


    class MaxQueue {

        private Queue<Integer> queue;
        private Deque<Integer> deque;


        public MaxQueue() {
            queue = new LinkedList<>();
            deque = new LinkedList<>();
        }

        public int max_value() {
            return deque.isEmpty() ? -1 : deque.peekFirst();
        }

        public void push_back(int value) {
            while (!deque.isEmpty() && deque.peekLast() < value) {
                deque.pollLast();
            }
            deque.offer(value);
            queue.offer(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) return -1;
            int res = queue.poll();
            if (!deque.isEmpty() && res == deque.peekFirst()) {
                deque.pollFirst();
            }
            return res;
        }
    }


    class Two {
        int index = 0;

        public int[] spiralOrder(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return new int[]{};
            }

            int topR = 0, topC = 0;
            int downR = matrix.length - 1, downC = matrix[0].length - 1;
            int[] arr = new int[(downR + 1) * (downC + 1)];
            while (topR <= downR && topC <= downC) {
                helper(matrix, arr, topR++, topC++, downR--, downC--);
            }
            return arr;
        }

        private void helper(int[][] matrix, int[] arr, int topR, int topC, int downR, int downC) {
            //只有一行
            if (topR == downR) {
                for (int i = topC; i <= downC; i++) {
                    arr[index++] = matrix[topR][i];
                }
            } else if (topC == downC) {//只有一列
                for (int i = topR; i <= downR; i++) {
                    arr[index++] = matrix[i][topC];
                }
            } else {
                int curR = topR, curC = topC;
                while (curC != downC) arr[index++] = matrix[topR][curC++];
                while (curR != downR) arr[index++] = matrix[curR++][downC];
                while (curC != topC) arr[index++] = matrix[downR][curC--];
                while (curR != topR) arr[index++] = matrix[curR--][topC];
            }


        }
    }

    public int singleNumberII(int[] nums) {
        int ans = 0;
        //考虑每一位
        for (int i = 0; i < 32; i++) {
            int count = 0;
            //考虑每一个数
            for (int j = 0; j < nums.length; j++) {
                //当前位是否是 1
                if ((nums[j] >>> i & 1) == 1) {
                    count++;
                }
            }
            //1 的个数是否是 3 的倍数
            if (count % 3 != 0) {
                ans = ans | 1 << i;
            }
        }
        return ans;
    }


    public int numDecodings1st(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            if (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    public String decodeString(String s) {

        LinkedList<Integer> numList = new LinkedList<>();
        LinkedList<String> strList = new LinkedList<>();
        int multi = 0;
        StringBuilder res = new StringBuilder();
        char[] chas = s.toCharArray();
        for (int i = 0; i < chas.length; i++) {
            char c = chas[i];
            if (c == '[') {
                numList.addLast(multi);
                strList.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int curMulti = numList.pollLast();
                for (int j = 0; j < curMulti; j++) {
                    tmp.append(res);
                }
                res = new StringBuilder(strList.pollLast() + tmp);
            } else if (c >= '0' && c <= '9') {
                multi = multi * 10 + Integer.valueOf(c + "");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    public double myPow2nd(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return fastPow1st(x, N);

    }

    public double fastPow1st(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double half = fastPow1st(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    class Week176 {
        public int countNegatives(int[][] grid) {
            int count = 0;
            if (grid == null || grid.length == 0 || grid[0].length == 0) return count;
            int row = grid.length;
            int col = grid[0].length;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] < 0) count++;
                }
            }
            return count;
        }
    }

    static class Three {
        public int[][] merge(int[][] intervals) {
            List<int[]> result = new ArrayList<>();
            if (intervals == null || intervals.length == 0) return result.toArray(new int[0][]);
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            int len = intervals.length;
            int i = 0;
            while (i < len) {
                int curStart = intervals[i][0];
                int curEnd = intervals[i][1];
                while (i < len - 1 && curEnd >= intervals[i + 1][0]) {
                    curEnd = Math.max(curEnd, intervals[i + 1][1]);
                    i++;
                }
                result.add(new int[]{curStart, curEnd});
                i++;
            }
            return result.toArray(new int[0][]);
        }

        public List<String> summaryRanges(int[] nums) {
            List<String> result = new ArrayList<>();
            if (nums == null || nums.length == 0) return result;
            int len = nums.length;
            int start = 0, i = 0;
            while (i < len) {
                while (i < len - 1 && nums[i] + 1 == nums[i + 1]) {
                    i++;
                }
                if (i == start) result.add(nums[start] + "");
                else result.add(nums[start] + "->" + nums[i]);
                i++;
                start = i;
            }

            return result;
        }
    }

    public int maxEvents(int[][] events) {

        Arrays.sort(events, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int i = 0;
        int count = 0;
        int n = events.length;
        int curD = 1;
        while (i < n || !queue.isEmpty()) {
            while (i < n && events[i][0] <= curD) {
                queue.offer(events[i]);
                i++;
            }

            while (!queue.isEmpty()) {
                int[] curEvent = queue.poll();
                if (curEvent[1] >= curD) {
                    count++;
                    break;
                }
            }
            curD++;
        }
        return count;
    }

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> list = new ArrayList<>();
        int lenA = A.length, lenB = B.length;
        int i = 0, j = 0;
        while (i < lenA && j < lenB) {
            int a1 = A[i][0], a2 = A[i][1];
            int b1 = B[j][0], b2 = B[j][1];
            if (a2 >= b1 && a1 <= b2) {
                list.add(new int[]{Math.max(a1, b1), Math.min(a2, b2)});
            }
            if (b2 < a2) j++;
            else i++;
        }
        return list.toArray(new int[0][]);
    }


    public int minArray(int[] numbers) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] < numbers[right]) {
                right = mid;
            } else if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else {
                while (mid < right && numbers[mid] == numbers[right]) {
                    right--;
                }
            }
        }
        return numbers[left];
    }


    public void testTreeMap() {
        // creating tree map
        NavigableMap<Integer, String> treemap = new TreeMap<Integer, String>();

        // populating tree map
        treemap.put(2, "two");
        treemap.put(1, "one");
        treemap.put(3, "three");
        treemap.put(6, "six");
        treemap.put(5, "five");

        System.out.println("Ceiling entry for 4: " + treemap.ceilingEntry(1));
        System.out.println("Ceiling entry for 4: " + treemap.ceilingEntry(4));
        System.out.println("Ceiling entry for 5: " + treemap.ceilingEntry(5));


    }


    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (set.contains(num)) return true;
            set.add(num);
            if (num % 2 == 0) set.add(num / 2);
        }
        return false;
    }


    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        char[] chas = s.toCharArray();
        for (char c : chas) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (!stack.isEmpty() && stack.peek() == map.get(c)) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }


    class Twitter {
        //<k,v> k是当前的用户id，v是关注了当前用户id的粉丝或者说关注者id的列表
        Map<Integer, Set<Integer>> followeeMap;
        //<k,v>k 表示当前的推特的id，v表示哪个用户发的这条推特
        Map<Integer, Integer> tweetUserIdMap;
        //
        List<Integer> list;

        /**
         * Initialize your data structure here.
         */
        public Twitter() {
            followeeMap = new HashMap<>();
            tweetUserIdMap = new HashMap<>();
            list = new ArrayList<>();
        }

        /**
         * Compose a new tweet.
         */
        public void postTweet(int userId, int tweetId) {
            tweetUserIdMap.put(tweetId, userId);
            list.add(tweetId);
        }

        /**
         * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
         */
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> result = new ArrayList<>();
            for (int i = list.size() - 1; i >= 0 && result.size() < 10; i--) {
                //获取到当前这条推特是哪位用户发的
                int owner = tweetUserIdMap.get(list.get(i));
                //如果这个推特的作者是和外部的userId是同一个人，这条推特有效
                //或者这个userId在followeeMap中并且在其粉丝的列表中
                if (owner == userId || (followeeMap.containsKey(userId) && followeeMap.get(userId).contains(owner))) {
                    result.add(list.get(i));
                }
            }
            return result;

        }

        /**
         * Follower follows a followee. If the operation is invalid, it should be a no-op.
         */
        public void follow(int followerId, int followeeId) {
            followeeMap.putIfAbsent(followerId, new HashSet<>());
            followeeMap.get(followerId).add(followeeId);
        }

        /**
         * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
         */
        public void unfollow(int followerId, int followeeId) {
            if (followeeMap.containsKey(followerId)) {
                followeeMap.get(followerId).remove(followeeId);
            }
        }
    }

    public boolean isValidBST2nd(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        //Double.MIN_VALUE是一个非零非负的常量，不是最小的
        double MIN = -Double.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            int val = root.val;
            if (val <= MIN) {
                return false;
            }
            MIN = val;
            root = root.right;
        }
        return true;
    }


    public List<String> restoreIpAddresses(String s) {
        List<String> resList = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) return resList;
        backtracing(s, 0, new ArrayList<>(), resList);
        return resList;
    }


    /**
     * @param s       原始字符串
     * @param curPos  当前的字符索引的位置
     * @param tmpList 当前收集到的结果集
     * @param resList 最终的结果集
     */
    public void backtracing(String s, int curPos, List<String> tmpList, List<String> resList) {
        //当当前收集的结果集是4段时，表示ip段已经收集完成，且当前的位置curPos到达了字符s的末尾
        if (tmpList.size() == 4 && curPos == s.length()) {
            resList.add(String.join(".", tmpList));
            return;
        }
        for (int i = 1; i <= 3; i++) {
            //当结束位置(curPos + i)超过字符串的位置或者当前的结果集大于4的时候(ip段至多有4段)
            if (curPos + i > s.length() || tmpList.size() > 4) break;
            String tmp = s.substring(curPos, curPos + i);
            //当当前的ip段长度大于1，以0开头，如012，01这样的，不合法，单独的0是合法的
            //当当前的加的长度为3，ip段的值大于255，不合法，如312
            if ((tmp.startsWith("0") && tmp.length() > 1) || (i == 3 && Integer.valueOf(tmp) > 255)) {
                continue;
            }
            tmpList.add(tmp);
            backtracing(s, curPos + i, tmpList, resList);
            tmpList.remove(tmpList.size() - 1);
        }

    }


    public int orangesRotting(int[][] grid) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int row = grid.length, col = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) fresh++;
                if (grid[i][j] == 2) queue.add(new int[]{i, j});
            }
        }

        int round = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            round++;
            for (int i = 0; i < n; i++) {
                int[] cur = queue.poll();
                for (int k = 0; k < directions.length; k++) {
                    int curX = cur[0] + directions[k][0];
                    int curY = cur[1] + directions[k][1];
                    if (curX >= 0 && curX < row && curY >= 0 && curY < col && grid[curX][curY] == 1) {
                        grid[curX][curY] = 2;
                        fresh--;
                        queue.add(new int[]{curX, curY});
                    }
                }
            }

        }
        return fresh > 0 ? -1 : round;
    }

    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        int round = 1;
        while (candies > 0) {
            for (int i = 0; i < num_people; i++) {
                if (candies < 0) break;
                res[i] += (round < candies) ? round : candies;
                candies -= round;
                round++;
            }
        }
        return res;
    }





    //spring
    public static void main(String[] args) {
        Three three = new Three();
//        int[][] intervals = {{1, 3}, {8, 10}, {15, 18}, {2, 6}};
//        three.merge(intervals);
//        three.summaryRanges(new int[]{0, 1, 2, 4, 5, 7});


//        handler.eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}});
//        handler.maxEvents(new int[][]{{1, 2}, {2, 3}, {3, 4}});
        int[][] A = {{0, 2}, {5, 10}, {13, 23}, {24, 25}}, B = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
//        handler.intervalIntersection(A, B);
//        handler.hammingDistance(1, 4);
//        handler.testTreeMap();
//
//        handler.restoreIpAddresses("25525511135");
//        handler.distributeCandies(10, 3);
// handler.constructArr(new int[]{1, 2, 3, 4, 5});
//        handler.missingNumber(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 9});
//        handler.missingNumber(new int[]{0});
//        handler.freqAlphabets("10#11#12");
//        handler.numDecodings1st("11204");
//        handler.numDecodings1st("200");
//        handler.numDecodings1st("170");
//        handler.decodeString("3[a]2[bc]");
//        handler.singleNumberII(new int[]{1, 2, 6, 1, 1, 2, 2, 3, 3, 3});
//        handler.nE
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
//        int[][] queries = new int[][]{{3, 3, 0}, {1, 2, 0}, {0, 3, 1}, {0, 3, 2}, {0, 4, 1}};
//        handler.canMakePaliQueries(s, queries);
//        handler.canMakePaliQueries1st(s, queries);
//        String[] t = new String[]{"alice,20,800,mtv", "alice,50,100,beijing"};
//        handler.invalidTransactions(t);
//        String[] queries = new String[]{"bbb", "cc"};
//        String[] words = new String[]{"a", "aa", "aaa", "aaaa"};
//        queries = new String[]{"bba", "abaaaaaa", "aaaaaa", "bbabbabaab", "aba", "aa", "baab", "bbbbbb", "aab", "bbabbaabb"};
//        words = new String[]{"aaabbb", "aab", "babbab", "babbbb", "b", "bbbbbbbbab", "a", "bbbbbbbbbb", "baaabbaab", "aa"};
//        handler.numSmallerByFrequency(queries, words);
//        handler.splitArray(new int[]{7, 2, 5, 10, 8}, 2);
        int[][] prerequisites = new int[][]{{1, 0}, {2, 1}, {3, 1}, {3, 2}};
        prerequisites = new int[][]{{1, 0}, {3, 0}, {3, 1}, {2, 1}, {4, 2}, {4, 3}};
        prerequisites = new int[][]{{0, 1}};
//        handler.canFinish(2, prerequisites);
//        handler.dayOfYear("2016-02-29");
//        handler.numRollsToTarget(30, 30, 500);
//        handler.maxRepOpt1("aaabaaa");
//        handler.find132pattern(new int[]{3, 6, 1, 4, 5, 2});
//        handler.movesToMakeZigzag(new int[]{2, 7, 10, 9, 8, 9});
//        handler.movesToMakeZigzag(new int[]{2, 1, 2});
//        handler.movesToMakeZigzag(new int[]{7, 4, 8, 9, 7, 7, 5});
//        handler.movesToMakeZigzag(new int[]{10, 1, 1, 6, 6, 6, 1, 8, 8, 5, 1, 2, 6, 6, 6, 4, 4, 8, 7, 1});
//        handler.movesToMakeZigzag1st(new int[]{9, 6, 1, 6, 2});
//        handler.solveEquation("x+5-3+x=6+x-2");
//        handler.solveEquation("-x=-1");
//        handler.tribonacci(4);
//            handler.alphabetBoardPath("leet");
//        handler.alphabetBoardPath("zdz");
//        handler.findNumberOfLIS(new int[]{1, 3, 5, 4, 7});
//        handler.findNumberOfLIS(new int[]{2, 2, 2, 2, 2});
//        handler.findNumberOfLIS(new int[]{1, 2, 4, 3, 5, 4, 7, 2});
//        handler.findNumberOfLIS(new int[]{1});
//        handler.longestIncreasingContinuousSubsequence(new int[]{5, 4, 2, 1, 3});
//        handler.longestIncreasingContinuousSubsequence(new int[]{8, 4, 2, 1});
//        nums = new int[10009];
//        for (int i = 10010; i >= 2; i--) {
//            nums[i - 2] = i;
//        }
//        handler.longestIncreasingContinuousSubsequence(nums);
//        handler.longestConsecutive2nd(new int[]{0, -1});
//        handler.shiftingLetters("abc", new int[]{3, 5, 9});
//        handler.shiftingLetters("bad", new int[]{10, 20, 30});
//        handler.shiftingLetters("gdhbjaph", new int[]{74, 34, 65, 30, 43, 91, 14, 10});
//        handler.shiftingLetters2nd("gdhbjaph", new int[]{74, 34, 65, 30, 43, 91, 14, 10});
//        handler.PredictTheWinner(new int[]{1, 5, 233, 7});
//        handler.PredictTheWinner2nd(new int[]{1, 5, 233, 7});
//        handler.PredictTheWinner3rd(new int[]{1, 233, 5, 7, 230});
//        handler.canWin("++++");
//        handler.findContestMatch(8);
//        handler.minWindow("ADOBECODEBANC", "ABC");
//        handler.findAnagrams("cbaebabacd", "abc");
//        handler.findAnagrams("baa", "aa");
//        handler.characterReplacement("ABAB", 2);
//        handler.characterReplacement("AABABBA", 1);
//        handler.findDuplicate(new int[]{1, 3, 4, 2, 2});
//        handler.findDuplicate1st(new int[]{1, 3, 4, 2, 2});
//        handler.majorityElement(new int[]{1, 2, 3, 2, 2, 2, 5, 4});
//        MedianFinder finder = new MedianFinder();
//        finder.addNum(1);
//        finder.addNum(2);
//        finder.findMedian();
//        finder.addNum(3);
//        finder.findMedian();
//        handler.medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
//        handler.medianSlidingWindow(new int[]{2147483647, 2147483647}, 2);
//        TreeNode node5 = new TreeNode(5);
//        TreeNode node4 = new TreeNode(4);
//        node4.right = node5;
//
//        TreeNode node3 = new TreeNode(3);
//        node3.right = node4;
//
//        TreeNode node2 = new TreeNode(2);
//        node2.right = node3;
//
//        TreeNode node1 = new TreeNode(1);
//        node1.right = node2;
//
//
//        System.out.println(handler.pathSumIII(node1, 3));
//        handler.summaryRanges1st(new int[]{0, 2, 3, 4, 6, 8, 9});
//        handler.nextPermutation(new int[]{1, 2, 7, 4, 3, 1});
//
//        handler.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3});
//        handler.maximalRectangle1st(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}});
//        handler.lengthOfLastWord("a ");
//        handler.numsSameConsecDiff(3, 7);
        ListNode i1 = new ListNode(1);
        ListNode i2 = new ListNode(2);
        ListNode i3 = new ListNode(3);
        ListNode i4 = new ListNode(4);
        ListNode i5 = new ListNode(5);
        ListNode i6 = new ListNode(6);
        i1.next = i2;
        i2.next = i3;
        i3.next = i4;
        i4.next = i5;
        i5.next = i6;
        i6.next = null;
//        handler.reverseList1st(i1);
//        handler.middleNode(i1);
//        handler.findMin1st(new int[]{4, 5, 6, 7, 0, 1, 2});
//        handler.search1st(new int[]{4, 5, 6, 7, 0, 1, 2}, 4);
//        handler.mySqrt(8);
//        handler.balancedStringSplit("RLRRLLRLRL");
//        handler.reverseParentheses("(u(love)i)");
//        handler.reverseParentheses1st("(u(love)i)");
//        handler.findDuplicate2nd(new int[]{3, 1, 3, 4, 2});
//        handler.subarraySum(new int[]{1, 1, 1}, 2);
//        handler.subarraySum2nd(new int[]{1, 1, 1}, 2);

//        handler.setZeroes(new int[][]{{1, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}});
//        handler.romanToInt("MCMXCIV");
//        handler.partition("aab");
//        handler.generate2nd(5);
//        handler.getRow(3);
//        handler.getRow1st(3);
//        handler.getRow2nd(3);
//        handler.moveZeroes(new int[]{0,1,0,3,12});
//        handler.merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
//        handler.reverseVowels("leetcode");
//        handler.searchInsert(new int[]{1, 3, 5, 6}, 5);
//        handler.mySqrt(4);
//        handler.search9th(new int[]{-1, 0, 3, 5, 9, 12}, 9);
//        handler.search9th(new int[]{5}, 5);
//        handler.searchA(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
//        handler.searchA(new int[]{1, 3}, 1);
//        handler.searchB(new int[]{1, 3}, 1);
//        handler.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
//        handler.longestStrChain(new String[]{"a", "b", "ba", "bca", "bda", "bdca"});
//        handler.longestStrChain1st(new String[]{"a", "b", "ba", "bca", "bda", "bdca"});
//        handler.longestStrChain2nd(new String[]{"a", "b", "ba", "bca", "bda", "bdca"});
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
//        handler.findNumberIn2DArray(matrix, 20);
        matrix = new int[][]{{-5}};
//        handler.findNumberIn2DArray(matrix, -10);

    }
//winter


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class ListNode {
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


    class SnapshotArray {
        private Map<Integer, Map<Integer, Integer>> map = null;
        private int snapId = 0;


        public SnapshotArray(int length) {
            map = new HashMap<>(length);
            while (length-- > 0) {
                map.put(length, new HashMap<>());
            }
        }

        public void set(int index, int val) {
            map.get(index).put(snapId, val);
        }

        public int snap() {
            return snapId++;
        }

        public int get(int index, int snap_id) {
            while (snap_id >= 0) {
                if (map.get(index).get(snap_id) != null) {
                    return map.get(index).get(snap_id);
                }
                snap_id--;
            }
            return 0;
        }
    }

    class MedianFinder {


        private PriorityQueue<Integer> maxheap = null;
        private PriorityQueue<Integer> minheap = null;
        private int count = 0;


        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            count = 0;
            maxheap = new PriorityQueue<Integer>(Comparator.reverseOrder());
            minheap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            count++;
            maxheap.offer(num);
            minheap.offer(maxheap.poll());
            if ((count & 1) != 0) maxheap.offer(minheap.poll());
        }

        public double findMedian() {
            if ((count & 1) == 0) return ((double) (maxheap.peek() + minheap.peek())) / 2;
            else return (double) maxheap.peek();
        }
    }


    class Node {
        private String curStr;
        private int left;
        private int right;


        public Node(String curStr, int left, int right) {
            this.curStr = curStr;
            this.left = left;
            this.right = right;
        }
    }


/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */


}


