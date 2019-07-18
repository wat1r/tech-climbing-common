package algorithm.leetcode.explore.one;

import basic.callback.one.Li;
import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2019/3/6 23:16
 * Description
 * 2018 年力扣高频
 * 算法面试题汇总
 */
public class LeetCodeExploreI {
    private static LeetCodeExploreI handler = new LeetCodeExploreI();


    /**
     * 136. 只出现一次的数字 Easy
     *
     * @param nums
     * @return ^运算:相同取0，不同取1
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }

    /**
     * 169. 求众数 Easy
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        quickSortRecursive(nums, 0, nums.length - 1);
        return nums[nums.length / 2];
    }


    private void quickSortRecursive(int[] nums, int left, int right) {
        if (nums == null || nums.length < 2) {
            return;
        }
        while (left < right) {
            int[] partition = partition(nums, left, right);
            quickSortRecursive(nums, left, partition[0] - 1);
            quickSortRecursive(nums, partition[1] + 1, right);
        }

    }

    private int[] partition(int[] nums, int left, int right) {
        int less = left - 1;
        int more = right;
        while (left < more) {
            if (nums[left] < nums[right]) {
                swap(nums, ++less, left++);
            } else if (nums[left] > nums[right]) {
                swap(nums, --more, left);
            } else {
                left++;
            }
        }
        swap(nums, more, right);
        return new int[]{less + 1, more};
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 229. 求众数 II Medium
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElementII(int[] nums) {
        List<Integer> resList = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return resList;
        } else if (nums.length == 1) {
            resList.add(nums[0]);
            return resList;
        }
        Arrays.sort(nums);
        int prev = nums[0];
        int count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            count += nums[i] == prev ? 1 : 0;
            validate(count, n, prev, resList);
            if (nums[i] != prev) {
                prev = nums[i];
                count = 1;
            }
        }
        validate(count, n, prev, resList);
        return resList;
    }

    private void validate(int count, int n, int prev, List<Integer> resList) {
        if (!resList.contains(prev) && count > (n / 3)) {
            resList.add(prev);
        }
    }


    /**
     * 240. 搜索二维矩阵 II Medium
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col > -1) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }


    /**
     * 26. 删除排序数组中的重复项 Easy
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int cur = 0, fast = 1;
        while (fast < nums.length) {
            if (nums[fast] == nums[cur]) {
                fast++;
            } else {
                swap(nums, ++cur, fast++);
            }
        }
        return cur + 1;
    }


    /**
     * 122. 买卖股票的最佳时机 II Easy
     *
     * @param prices
     * @return #### DP, sequence dp + status
     * - 想知道前i天的最大profit, 那么用sequence DP:
     * - dp[i]: represents 前i天的最大profit
     * - 当天的是否能卖, 取决于昨天是否买进, 也就是 `昨天买了或者卖了的状态`: 加状态, dp[i][0], dp[i][1]
     * - `买`的状态 `dp[i][0]` = 1. 今天买入, 昨天卖掉的dp[i-1][1]结果 - price[i]; 2. 今天不买, 跟昨天买的status dp[i-1][0] 结果 比较.
     * - `卖`的状态 `dp[i][1]` = 1. 今天卖出, 昨天买进的dp[i-1][0]结果 + price[i]; 2. 今天不卖, 跟昨天卖的status dp[i-1][1] 结果 比较.
     * - 注意init:
     * - dp[0][0] = dp[0][1] = 0; // 0 days,
     * - dp[1][0] = 0; // sell on 1st day, haven't bought, so just 0 profit.
     * - dp[1][0] = -prices[0]; // buy on 1st day, with cost of prices[0]
     * <p>
     * 注意天数第0天，和index=0的索引的区别
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        dp[0][0] = dp[0][1] = 0;
        dp[1][0] = -prices[0];
        dp[1][1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i - 1]);
        }
        return Math.max(dp[n][0], dp[n][1]);
    }


    /**
     * 189. 旋转数组 Easy
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] temp = new int[len];
        for (int i = 0; i < len; i++) {
            temp[(i + k) % len] = nums[i];
        }
        for (int i = 0; i < len; i++) {
            nums[i] = temp[i];
        }
    }

    /**
     * 350. 两个数组的交集 II Easy
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> resList = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums1) {
            map.putIfAbsent(num, 0);
            map.put(num, map.get(num) + 1);
        }
        for (Integer num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                resList.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        int[] resArr = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            resArr[i] = resList.get(i);
        }
        return resArr;
    }


    /**
     * 66. 加一 Easy
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        if (digits.length == 0) {
            return digits;
        }
        digits[digits.length - 1] += 1;
        //Check index digit.length-1 to 1
        for (int i = digits.length - 1; i > 0; i--) {
            if (digits[i] == 10) {
                digits[i] = 0;
                digits[i - 1] += 1;
            } else {
                return digits;
            }
        }

        //Check index 0. If ==0, set it to 0 and carry over 1
        if (digits[0] == 10) {
            int[] output = new int[digits.length + 1];
            output[0] = 1;
            return output;
        } else {
            return digits;
        }
    }


    /**
     * 283. 移动零 Easy
     *
     * @param nums #### Two Pointers
     *             - Outside pointer that moves in certain condition.
     *             - Save appropirate elements
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }


    /**
     * 36. 有效的数独 Medium
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {

        return false;
    }


    /**
     * 48. 旋转图像 Medium
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int tR = 0, tC = 0, dR = matrix.length - 1, dC = matrix[0].length - 1;
        while (tR < dR) {
            rotateProcess(matrix, tR++, tC++, dR--, dC--);
        }
    }

    private void rotateProcess(int[][] matrix, int tR, int tC, int dR, int dC) {
        int times = dC - tC;
        int temp = 0;
        for (int i = 0; i < times; i++) {
            temp = matrix[tR][tC + i];
            matrix[tR][tC + i] = matrix[dR - i][tC];
            matrix[dR - i][tC] = matrix[dR][dC - i];
            matrix[dR][dC - i] = matrix[tR + i][dC];
            matrix[tR + i][dC] = temp;
        }

    }


    /**
     * 7. 整数反转 Easy
     * MAX:2147483647
     * MIN:-2147483648
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            res = res * 10 + pop;

        }
        return res;
    }


    /**
     * 387. 字符串中的第一个唯一字符 Easy
     *
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        int[] times = new int[256];
        for (char c : s.toCharArray()) {
            times[c - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (times[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 242. 有效的字母异位词 Easy
     *
     * @param s
     * @param t
     * @return Thoughts: if only lower case letters, use int[26] for simplicity
     */
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] compartor = new int[26];
        for (int i = 0; i < s.length(); i++) {
            compartor[s.charAt(i) - 'a']++;
            compartor[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < compartor.length; i++) {
            if (compartor[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 28. 实现strStr() Easy
     *
     * @param haystack
     * @param needle
     * @return #### Two Pointer
     * - 找到B在A中的起始位置, 然后看一下从这个点开始的substring是否等于B就可以了
     * - 还挺多坑的, 这些可以帮助优化:
     * - 1. 当B是“”的时候，也就是能在A的其实位置找到B....index = 0.
     * - 2. edge condition: 如果 haystack.length() < needle.length() 的话, 必须错, return -1
     * - 3. 如果在某个index, A后面剩下的长度, 比B的长度短, 也是误解, return -1
     * <p>
     * "mississippi"
     * "issipi"
     */
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) {
            return -1;
        }
        if (needle.length() == 0) {
            return 0;
        }
        int h = haystack.length();
        int n = needle.length();
        for (int i = 0; i < h; i++) {
            if (h - i < n) {
                return -1;
            }
            if (haystack.charAt(i) != needle.charAt(0)) {
                continue;
            }
            if (haystack.substring(i, i + n).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 38. 报数 Easy
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n <= 1) {
            return n + "";
        }
        String str = "11";
        int ind = 2;
        while (ind < n) {
            StringBuffer sb = new StringBuffer();
            char[] arr = str.toCharArray();
            int count = 1;
            int type = Character.getNumericValue(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] == arr[i - 1]) {
                    count++;
                } else {
                    sb.append(count + "" + type);
                    type = Character.getNumericValue(arr[i]);
                    count = 1;
                }
            }
            ind++;
            sb.append(count + "" + type);
            str = sb.toString();
        }
        return str;
    }


    /**
     * 198. 打家劫舍 Easy
     *
     * @param nums
     * @return dp[i] 是前i天的最大打劫钱数，应该等于max(昨天的钱dp[i-1],前天打劫的钱dp[i-2]+今天的打劫的钱的和)
     * 注意dp的index与nums的index的映射关系
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[n];
    }


    /**
     * 237. 删除链表中的节点 Easy
     *
     * @param node 题目给的是删除节点，那说明这个节点可以舍弃了，我们把下一个节点的值拷贝给当前要删除的节点，再删除下一个节点。
     *             大致过程如下（删除3）：
     *             1->2->3->4->5
     *             1->2->4->4->5
     *             1->2->4->5
     */
    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 19. 删除链表的倒数第N个节点  Easy
     *
     * @param head
     * @param n
     * @return 上述算法可以优化为只使用一次遍历。我们可以使用两个指针而不是一个指针。第一个指针从列表的开头向前移动 n+1n+1 步，而第二个指针将从列表的开头出发。现在，这两个指针被 nn 个结点分开。我们通过同时移动两个指针向前来保持这个恒定的间隔，直到第一个指针到达最后一个结点。此时第二个指针将指向从最后一个结点数起的第 nn 个结点。
     * 我们重新链接第二个指针所引用的结点的 next 指针指向该结点的下下个结点。
     * 官网的图很好
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    /**
     * 206. 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode current = head, next, prev = null;
        while (current != null) {
            // 记录后继结点
            next = current.next;
            // 后继指针逆向
            current.next = prev;
            // 记录当前结点
            prev = current;
            // 下一结点成为当前结点
            current = next;
        }
        return prev;
    }


    /**
     * 21. 合并两个有序链表 Easy
     *
     * @param l1
     * @param l2
     * @return Thinking process:
     * 1. Merge sorted list, compare before add to node.next
     * 2. when any of l1 or l2 is null, break out.
     * 3. add the non-null list at the end of node.
     * 4. return dummy.next.
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head = new ListNode(0);
        ListNode node = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        if (l1 != null) {
            node.next = l1;
        } else {
            node.next = l2;
        }
        return head.next;
    }


    /**
     * 98. 验证二叉搜索树
     * Medium
     *
     * @param root
     * @return 如题, 验证是否是BST.
     * <p>
     * #### DFS
     * - 查看每个parent-child关系: leftchild < root < rightChild;
     * - BST 有两个极端: left-most-leaf is the smallest element, and right-most-leaf is largest
     * - imagine we know the two extreme border: Integer.MIN_VALUE, Integer.MAX_VALUE; pass node around and compare node vs. node.parent.
     * - 方法: 把root.val 传下来作为 max 或者 min, 然后检查children
     */
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean dfs(TreeNode node, long minValue, long maxValue) {
        if (node == null) {
            return true;
        }
        return node.val > minValue && node.val < maxValue &&
                dfs(node.left, minValue, node.val) && dfs(node.right, node.val, maxValue);
    }


    /**
     * 101. 对称二叉树 Easy
     *
     * @param root
     * @return 如果同时满足下面的条件，两个树互为镜像：
     * <p>
     * 它们的两个根结点具有相同的值。
     * 每个树的右子树都与另一个树的左子树镜像对称。
     */
    public boolean isSymmetric(TreeNode root) {

        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        return node1.val == node2.val && isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
    }


    /**
     * 108. 将有序数组转换为二叉搜索树
     * Easy
     *
     * @param nums
     * @return #### DFS
     * - Binary Search Tree特点: 左边的node都比右边的node小.
     * - height balance, subtree height 相差<1, 必须左右sub tree均分. 做DFS(num, start, end)
     * - 在每一个level, 找到中间点, 然后分割2半, 继续dfs
     * - Divide and Conquer
     * - time/space: O(n), visit all nodes, no redundant visits.
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return dfs108(nums, 0, nums.length - 1);
    }

    private TreeNode dfs108(int[] nums, int start, int end) {
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        if (start > end || end >= nums.length) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = dfs108(nums, start, mid - 1);
        node.right = dfs108(nums, mid + 1, end);
        return node;
    }


    /**
     * 1. 两数之和 Easy
     *
     * @param nums
     * @param target
     * @return #### Sort array, two pointer
     * - 前后++, --搜索. Sort 用时O(nlogn).
     * - 1. 第一步 two pointer 找 value.
     * - 2. 注意，要利用额外的空间保留original array， 用来时候找index. (此处不能用HashMap，因为以value 为key，但value可能重复)
     * - O(n) space, O(nlogn) time.
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 1) {
            return null;
        }
        //1. 备份原数组，下面要对原数组进行Sort
        int[] backup = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            backup[i] = nums[i];
        }
        //2. 对原数组Sort
        Arrays.sort(nums);
        //3. 找到等于target的两个num值，因为已经排序好了，头尾两个指针做逻辑
        int num1 = -1, num2 = -1;
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int sum = nums[start] + nums[end];
            if (sum == target) {
                num1 = nums[start];
                num2 = nums[end];
                break;
            } else if (sum > target) {
                end--;
            } else {
                start++;
            }
        }
        //4. 找到和等于target的两个数，来找他们的索引，借助backup数组
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        for (int i = 0; i < backup.length; i++) {
            if (backup[i] == num1 || backup[i] == num2) {
                if (res[0] == -1) {
                    res[0] = i;
                } else {
                    //5 .因为是排序的，num2比num1大，当进到else的逻辑，if的逻辑肯定进了，可以break掉结束
                    res[1] = i;
                    break;
                }
            }
        }
        return res;
    }


    /**
     * 167. 两数之和 II - 输入有序数组 Easy
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSumII(int[] numbers, int target) {
        if (numbers == null || numbers.length <= 1) {
            return null;
        }
        int[] res = new int[2];
        int start = 0, end = numbers.length - 1;
        while (start != end) {
            //两个数的和可能会超过Integer.MAX_VLAUE
            long sum = (long) numbers[start] + numbers[end];
            if (sum == target) {
                res[0] = start + 1;
                res[1] = end + 1;
                //找到一组即退出
                break;
            } else if (sum > target) {
                end--;
            } else {
                start++;
            }
        }
        return res;
    }

    /**
     * 18. 四数之和 Medium
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> resList = new ArrayList<>();
        if (nums == null || nums.length <= 3) {
            return resList;
        }
        int n = nums.length;
        Arrays.sort(nums);
        //存储结构，HashSet<List> 可以帮助去重
        Map<Integer, HashSet<List>> ctrlMap = new HashMap<>();
        Set<String> ctrlSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            //拿后一半部分
            for (int k = i + 1; k < n; k++) {
                int sum = nums[i] + nums[k];
                if (ctrlMap.containsKey(target - sum)) {
                    for (List<Integer> prev : ctrlMap.get(target - sum)) {
                        List<Integer> list = Arrays.asList(prev.get(0), prev.get(1), nums[i], nums[k]);
                        //数字混在一起容易引起误解，如12 3 和 1 23 都可以组成123
                        String key = reshapeKey(list);
                        if (!ctrlSet.contains(key)) {
                            resList.add(list);
                            ctrlSet.add(key);
                        }
                    }
                }
            }
            //拿part1的部分放在拿part2的后面，不然[-3,-1,0,2,4,5] 0 -->[[-3,-1,-1,5],[-3,-1,0,4]]
            for (int j = 0; j < i; j++) {
                int sum = nums[j] + nums[i];
                ctrlMap.putIfAbsent(sum, new HashSet<>());
                ctrlMap.get(sum).add(Arrays.asList(nums[j], nums[i]));
            }

        }
        return resList;
    }

    private String reshapeKey(List<Integer> list) {
        StringBuffer sb = new StringBuffer();
        for (Integer num : list) {
            sb.append(num + "#");
        }
        return sb.toString();
    }

    /**
     * 80. 删除排序数组中的重复项 II Medium
     *
     * @param nums
     * @return
     */
    public int removeDuplicatesII(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int index = 1;
        for (int i = 2; i < nums.length; i++) {
            //i 可以理解为快指针，index是慢指针，i从一开始就比index大1
            //第一个条件是i对应的值与index（慢指针最后一个停留的值）不相等，进逻辑
            //第二个条件是i对应的值与index（慢指针最后一个停留的值）相等，但是和index前一个值不相等，即满足同一个元素只有最多两个
            if (nums[i] != nums[index] || (nums[i] == nums[index] && nums[i] != nums[index - 1])) {
                index++;
                nums[index] = nums[i];
            }
        }
        return index + 1;
    }


    /**
     * 287. 寻找重复数  Medium
     *
     * @param nums
     * @return 二分查找法
     */
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = 0;
            //记录下nums中<=mid的count值，这个mid既是索引页数nums中的value
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            //当count<=mid说明，比mid小的数量要还不到nums中间位置了，说明[1,mid]的数不能依次填满
            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    /**
     * 73. 矩阵置零 Medium
     *
     * @param matrix Space: O(m*n)
     */
    public void setZeroes1st(int[][] matrix) {
        if (matrix == null || matrix.length <= 0) {
            return;
        }
        //注意height和width与行列row col下标的对齐
        int height = matrix.length;
        int width = matrix[0].length;
        boolean[][] zero = new boolean[height][width];
        //拿到位置为0值的flag
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 0) {
                    zero[i][j] = true;
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (zero[i][j]) {
                    //这一列都设置为0，行索引增加，列索引不变
                    for (int k = 0; k < height; k++) {
                        matrix[k][j] = 0;
                    }
                    //这一行都设置为0，列索引增加，行索引不变
                    for (int k = 0; k < width; k++) {
                        matrix[i][k] = 0;
                    }

                }
            }
        }
    }

    /**
     * 73. 矩阵置零 Medium
     *
     * @param matrix Space: O(m+n)
     */
    public void setZeroes2nd(int[][] matrix) {
        if (matrix == null || matrix.length <= 0) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] && col[j]) {
                    for (int k = 0; k < m; k++) {
                        matrix[k][j] = 0;
                    }
                    for (int k = 0; k < n; k++) {
                        matrix[i][k] = 0;
                    }
                }
            }
        }
    }


    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRow = false;
        boolean firstCol = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        firstRow = true;
                    }
                    if (j == 0) {
                        firstCol = true;
                    }
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstRow) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        if (firstCol) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     * 516. 最长回文子序列 Medium
     * 注意！subsequence并不是substring, 是可以skip letter / non-continuous character sequence
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        //注意：子序列可以不连续的，可以跳过某些单词，子串是必须连续的
        //dp[i][j]:以chas[i] 开头chas[j] 结尾的[i-j]中的最大子回文子序列的数量
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chas = s.toCharArray();
        int n = chas.length;
        ;
        int[][] dp = new int[n][n];
        //拼接对角线上的 因为是一个字符，dp都为1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        //拼接长度为2的，要是这两个字符相等 dp为2 否则为1
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = chas[i] == chas[i + 1] ? 2 : 1;
        }
        //拼接长度为3的：
        //1.等于左边的和下边的dp值的最大值，（砍头去尾）
        //2. 如果[i] == [j] 对应的值相等，则取左下方对角的值加上2（首尾相等的情况）
        //3. 最终的下半部分的dp是初始化的0，对角线往右上角的值一次动态规划出来
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = len + i - 1;
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                if (chas[i] == chas[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                }
            }
        }
        return dp[0][n - 1];
    }


    /**
     * 647. 回文子串 Medium
     *
     * @param s
     * @return 还有一种解法odd/even split check
     * https://leetcode.com/problems/palindromic-substrings/discuss/105689/Java-solution-8-lines-extendPalindrome
     */
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length(), res = 0;
        boolean[][] isPalin = buildPalin(s);
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                res += isPalin[i][j] ? 1 : 0;
            }
        }
        return res;
    }

    private boolean[][] buildPalin(String s) {
        int n = s.length();
        boolean[][] isPalin = new boolean[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (i == j) {
                    isPalin[i][j] = true;
                } else {
                    isPalin[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 1 || isPalin[i + 1][j - 1]);
                }
            }
        }
        return isPalin;
    }


    /**
     * 55. 跳跃游戏 Medium
     *
     * @param nums
     * @return #### DP
     * - DP[i]: 在i点记录，i点之前的步数是否可以走到i点？ True of false.
     * - 其实j in [0~i)中间只需要一个能到达i 就好了
     * - Function: DP[i] = DP[j] && (A[j] >= i - j), for all j in [0 ~ i)
     * - Return: DP[dp.length - 1];
     * - Time: O(n^2)
     */
    public boolean canJump(int[] nums) {
        //dp[i] 在i点记录，i点之前的步数是否可以走到i点，true或false
        if (nums == null || nums.length == 0) {
            return false;
        }
        int len = nums.length;
        boolean[] dp = new boolean[len];
        dp[0] = true;//初始化
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                //从j到i，需要两个条件：j点可以到达（dp[j]=true）,nums[j]的值要>=j到i的距离
                if (dp[j] && (nums[j] >= i - j)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len - 1];
    }


    /**
     * 2. 两数相加 Meidum
     *
     * @param l1
     * @param l2
     * @return 遍历两个l1, l2把carry-on处理好，每次生成一个新node，最后检查carry-on.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        int carried = 0;
        //哑节点
        ListNode resNode = new ListNode(-1);
        ListNode head = resNode;
        while (l1 != null || l2 != null) {
            //这个sum可以取到上个while循环结尾的进位值carried
            int sum = carried;
            //拿到l1 ,l2的值相加得sum
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            //拿到进位值carried
            carried = sum / 10;
            //sum取模
            sum %= 10;
            resNode.next = new ListNode(sum);
            resNode = resNode.next;
        }
        //判断最后一位是否还有个进位值
        if (carried != 0) {
            resNode.next = new ListNode(carried);
        }
        //返回头节点
        return head.next;
    }


    /**
     * 328. 奇偶链表 Medium
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
        odd.next = evenHead;
        return head;
    }


    /**
     * 17. 电话号码的字母组合 Medium
     *
     * @param digits
     * @return DFS
     * <p>
     * 当string的长度与目标的digits的长度相等时，添加string到list中
     * 当digits用完了即返回最终结果
     */
    Map<Character, List<String>> map = null;

    public List<String> letterCombinations(String digits) {
        List<String> resList = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return resList;
        }
        map = new HashMap<Character, List<String>>() {{
            put('2', Arrays.asList("a", "b", "c"));
            put('3', Arrays.asList("d", "e", "f"));
            put('4', Arrays.asList("g", "h", "i"));
            put('5', Arrays.asList("j", "k", "l"));
            put('6', Arrays.asList("m", "n", "o"));
            put('7', Arrays.asList("p", "q", "r", "s"));
            put('8', Arrays.asList("t", "u", "v"));
            put('9', Arrays.asList("w", "x", "y", "z"));
        }};
        List<String> list = new ArrayList<>();
        dfs(resList, list, digits.toCharArray(), 0);
        return resList;
    }

    private void dfs(List<String> resList, List<String> list, char[] digits, int level) {
        if (list.size() == digits.length) {
            StringBuffer sb = new StringBuffer();
            for (String str : list) {
                sb.append(str);
            }
            resList.add(sb.toString());
            return;
        }
        List<String> letters = map.get(digits[level]);
        for (String letter : letters) {
            list.add(letter);
            dfs(resList, list, digits, level + 1);
            list.remove(list.size() - 1);
        }
    }


    /**
     * 22. 括号生成 Medium
     *
     * @param n
     * @return DFS
     */
    private String LEFT = "(";
    private String RIGHT = ")";

    public List<String> generateParenthesis(int n) {
        List<String> resList = new ArrayList<>();
        if (n == 0) {
            return resList;
        }
        dfs(resList, new StringBuffer(), n, n);

        return resList;
    }

    private void dfs(List<String> resList, StringBuffer sb, int numL, int numR) {
        if (numL == 0 && numR == 0) {
            resList.add(sb.toString());
            return;
        }
        if (numL > 0) {
            dfs(resList, sb.append(LEFT), numL - 1, numR);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (numR > 0 && numL < numR) {
            dfs(resList, sb.append(RIGHT), numL, numR - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }


    /**
     * 78. 子集 Medium
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {


        return null;
    }


    /**
     * 112. 路径总和 Easy
     *
     * @param root
     * @param sum
     * @return 递归
     */
    public boolean hasPathSum1st(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        //当root为叶子节点时（左节点与右节点都是null，且传入的sum值刚好等于这个叶子节点的值，即返回true）
        if (root.left == null && root.right == null && sum == root.val) {
            return true;
        }
        //分别探索root节点的左节点+右节点
        return hasPathSum1st(root.left, sum - root.val) || hasPathSum1st(root.right, sum - root.val);
    }


    /**
     * 112. 路径总和 Easy
     *
     * @param root
     * @param sum
     * @return DFS
     */
    public boolean hasPathSum2nd(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return dfs(root, sum);
    }


    private boolean dfs(TreeNode root, int sum) {
        //当root为叶子节点时（左节点与右节点都是null，且传入的sum值刚好等于这个叶子节点的值，即返回true）
        if (root.left == null && root.right == null && sum == root.val) {
            return true;
        }
        boolean flag = false;
        if (root.left != null) {
            flag = flag || dfs(root.left, sum - root.val);
        }
        if (root.right != null) {
            flag = flag || dfs(root.right, sum - root.val);
        }
        return flag;
    }


    /**
     * 113. 路径总和 II Medium
     *
     * @param root
     * @param sum
     * @return 叶子节点是指没有子节点的节点。
     * <p>
     * #### DFS, Backtracking
     * - 用remaining sum 来检测是否满足 input path sum 条件
     * - 满足的时候add to result list
     * - 两种backtracking:
     * - 1. backtrack 当下node, 加进list, 然后dfs. dfs结束后删掉之前加进去的元素. 非常clean.
     * - 2. backtrack 下一个dfs level增加的value. dfs return 之后, 删掉list里面的末尾元素: 但是删掉的dfs余下的value.
     * - 第一种backtrack更加好掌握.
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> resList = new ArrayList<>();
        if (root == null) {
            return resList;
        }
        dfs(resList, new ArrayList<>(), root, sum);
        return resList;
    }

    private void dfs(List<List<Integer>> resList, List<Integer> list, TreeNode node, int sum) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        //判断是否是叶子节点
        if (node.left == null && node.right == null && node.val == sum) {
            resList.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        //对左右节点探索
        dfs(resList, list, node.left, sum - node.val);
        dfs(resList, list, node.right, sum - node.val);
        //开始回溯
        list.remove(list.size() - 1);
    }


    /**
     * 209. 长度最小的子数组 Medium
     *
     * @param s
     * @param nums
     * @return two pointers
     * 整个过程是左右指针都从0 往末尾推，右指针跑的快些
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, right = 0, sum = 0;
        int res = Integer.MAX_VALUE;
        while (right < nums.length) {
            while (sum < s && right < nums.length) {
                sum += nums[right++];
            }
            while (sum >= s && left >= 0) {
                res = Math.min(res, right - left);
                sum -= nums[left++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }


    /**
     * 349. 两个数组的交集 Easy
     *
     * @param nums1
     * @param nums2
     * @return 两个set做去重，配合使用
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> parentSet = new HashSet<>();
        Set<Integer> childSet = new HashSet<>();
        for (int num : nums1) {
            parentSet.add(num);
        }
        for (int num : nums2) {
            if (parentSet.contains(num)) {
                childSet.add(num);
            }
        }
        int[] resArr = new int[childSet.size()];
        int index = 0;
        for (int value : childSet) {
            resArr[index++] = value;
        }
        return resArr;
    }


    /**
     * 219. 存在重复元素 II Easy
     *
     * @param nums
     * @param k
     * @return 采用滑动数组，k是i j两个索引的绝对值差值
     */
    public boolean containsNearbyDuplicateII(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Set<Integer> helpSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            //当i 不大于k 事表示滑动窗口还未生成，i-k-1是需要code一下
            if (i > k) {
                helpSet.remove(nums[i - k - 1]);
            }
            //有重复数据，add返回false的返回值
            if (!helpSet.add(nums[i])) {
                return true;
            }
        }
        return false;
    }


    /**
     * 220. 存在重复元素 III Medium
     *
     * @param nums
     * @param k
     * @param t
     * @return tags: BST
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        return false;
    }


    /**
     * 75. 颜色分类 Medium
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int left = 0, right = nums.length - 1;
        int i = 0;
        while (i <= right) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 0) {
                swap(nums, left, i);
                left++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, right);
                right--;
            }
        }
    }

    /**
     * 445. 两数相加 II Medium
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();
        Stack<ListNode> result = new Stack<>();
        while (l1 != null) {
            s1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2);
            l2 = l2.next;
        }
        int carried = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int sum = carried;
            if (!s1.empty()) {
                sum += s1.pop().val;
            }
            if (!s2.isEmpty()) {
                sum += s2.pop().val;
            }
            carried = sum / 10;
            sum %= 10;
            result.push(new ListNode(sum));
        }
        //判断最后一位是否还有个进位值
        if (carried != 0) {
            result.push(new ListNode(carried));
        }

        ListNode node = new ListNode(-1);
        ListNode dummpy = node;
        while (!result.isEmpty()) {
            node.next = result.pop();
            node = node.next;
        }
        //返回头节点
        return dummpy.next;
    }


    /**
     * 27. 移除元素 Easy
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, i = 0;
        while (i < nums.length) {
            if (nums[i] != val) {
                nums[left++] = nums[i++];
            } else {
                i++;
            }
        }
        return left;
    }

    /**
     * 263. 丑数 Easy
     *
     * @param num
     * @return
     */
    public boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }
        while (num % 2 == 0) {
            num /= 2;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }
        return num == 1;
    }


    /**
     * 202. 快乐数 Easy
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        HashSet<Long> set = new HashSet<>();
        long sum = n;
        while (sum != 1) {
            sum = getSum(sum);
            if (set.contains(sum)) {
                return false;
            } else {
                set.add(sum);
            }
        }
        return true;
    }

    private long getSum(long sum) {
        long res = 0;
        while (sum != 0) {
            res += Math.pow(sum % 10, 2);
            sum /= 10;
        }
        return res;
    }

    /**
     * 205. 同构字符串 Easy
     *
     * @param s
     * @param t
     * @return 1. 当map有key，value且chas ==chat时，符合题意，chas!=chat时，不符合题意
     * 2.当map不含有key，缺含有了chat时不符合题意
     */
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i != s.length(); i++) {
            char chas = s.charAt(i);
            char chat = t.charAt(i);
            if (map.containsKey(chas)) {
                if (map.get(chas) != chat) {
                    return false;
                }
            } else {
                if (map.containsValue(chat)) {
                    return false;
                }
                map.put(chas, chat);
            }
        }
        return true;
    }

    /**
     * 451. 根据字符出现频率排序 Meidum
     *
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        final Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char chas = s.charAt(i);
            map.put(chas, map.getOrDefault(chas, 0) + 1);
        }
        ArrayList<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o2.getValue() - o1.getValue()));
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<Character, Integer> entry : list) {
            for (int i = 0; i < entry.getValue(); i++) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }

    /**
     * @param s
     * @return
     */
//    public List<List<String>> partition(String s) {
//
//        return null;
//    }
    List<List<String>> resList = new ArrayList<>();

    public List<List<String>> partition(String s) {

        if (s == null || s.length() == 0) {
            return resList;
        }
        dfs(s, new ArrayList<String>(), 0);
        return resList;
    }

    private void dfs(String s, List<String> list, int l) {
        //当l==s的len时，表示已经到底了
        if (l == s.length()) {
            resList.add(new ArrayList<>(list));
            return;
        }
        for (int r = l; r < s.length(); r++) {
            //判断s 的l 到r 上是否是回文
            if (isPalindrome(s, l, r)) {
                list.add(s.substring(l, r + 1));
                //递归，r+1，再往右多取一个字符
                dfs(s, list, r + 1);
                list.remove(list.size() - 1);
            }
        }
    }


    /**
     * 判断是否是回文，指针碰撞
     */
    private boolean isPalindrome(String str, int l, int r) {
        while (l < r && str.charAt(l) == str.charAt(r)) {
            l++;
            r--;
        }
        return l >= r;
    }


    /**
     * 139. 单词拆分 Medium
     *
     * @param s
     * @param wordDict
     * @return dp[i]表示 s 中以 i - 1 结尾的字符串是否可被 wordDict 拆分
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }


    /**
     * 221. 最大正方形 Medium
     *
     * @param matrix
     * @return
     */
    //当我们判断以某个点为正方形右下角时最大的正方形时，那它的上方，
    // 左方和左上方三个点也一定是某个正方形的右下角，否则该点为右下角的正方形最大就是它自己了。
    // 这是定性的判断，那具体的最大正方形边长呢？我们知道，该点为右下角的正方形的最大边长，
    // 最多比它的上方，左方和左上方为右下角的正方形的边长多1，最好的情况是是它的上方，
    // 左方和左上方为右下角的正方形的大小都一样的，这样加上该点就可以构成一个更大的正方形。
    //       但如果它的上方，左方和左上方为右下角的正方形的大小不一样，合起来就会缺了某个角落
    // ，这时候只能取那三个正方形中最小的正方形的边长加1了。
    // 假设dpi表示以i,j为右下角的正方形的最大边长，则有
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        //init
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = matrix[i][j] == '1' ? 1 : 0;
                max = Math.max(max, dp[i][j]);
            }
        }
        //general
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }


    /**
     * 115. 不同的子序列 Hard
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }
        int m = s.length() + 1;
        int n = t.length() + 1;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        //init first col
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }
        //init first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * 92. 反转链表 II Medium
     *
     * @param head
     * @param m
     * @param n
     * @return 头插法
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = dummy.next;
        for (int i = 1; i < m; i++) {
            pre = pre.next;
            cur = cur.next;
        }
        for (int i = 0; i < n - m; i++) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        return dummy.next;
    }


    /**
     * 24. 两两交换链表中的节点 Medium
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
            ListNode node2 = node1.next;
            ListNode next = node2.next;
            node2.next = node1;
            node1.next = next;
            pre.next = node2;
            pre = node1;//将pre节点转移到新的node1上面来
        }
        return dummy.next;
    }


    /**
     * 160. 相交链表 Easy
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA;
        ListNode l2 = headB;
        while (l1 != l2) {
            l1 = (l1 == null) ? headB : l1.next;
            l2 = (l2 == null) ? headA : l2.next;
        }
        return l1;
    }


    /**
     * 83. 删除排序链表中的重复元素 Easy
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }


    /**
     * 234. 回文链表 Easy 采用空间复杂度O（1）的解法
     *
     * @param head
     * @return
     */
    public boolean isPalindromeIII(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        //1.1此处到洗余下处是做一个slow 和fast指针，当遍历结束的时候，slow指向中部位置，fast指向尾部位置
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //2.1获取当前slow的下一个节点为mid节点，slow的next节点指向null，断开左右两部分
        ListNode mid = slow.next;
        slow.next = null;
        //2.2右半部分的pre节点是mide，传入后翻转右半部分节点
        ListNode right = reverseList(mid);
        //3.1令left节点为左半部分的头节点，right为右半部分的头结点，比较值
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
     * 56. 合并区间 Medium
     *
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }
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


    /**
     * 56. 合并区间 Medium PriorityQueue
     *
     * @param intervals
     * @return
     */
    public List<Interval> merge2nd(List<Interval> intervals) {
        List<Interval> resList = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return resList;
        }
        //构造优先队列 --->[1,1],[2,1],[3,-1]...
        PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparing(p -> p.val));
        for (Interval interval : intervals) {
            queue.offer(new Point(interval.start, 1));
            queue.offer(new Point(interval.end, -1));
        }
        int count = 0;
        //执行优先队列的主逻辑
        Interval sub = new Interval();
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (count == 0) {
                sub.start = cur.val;
            }
            count += cur.flag;
            //此处为了处理前一个Point的值与后一个Point的值重叠的情况，如[1,4] [4,5]
            while (!queue.isEmpty() && cur.val == queue.peek().val) {
                Point temp = queue.poll();
                count += temp.flag;
            }
            //当count值为0时，表示要执行合并区间的逻辑了
            if (count == 0) {
                sub.end = cur.val;
                resList.add(sub);
                sub = new Interval();
            }
        }
        return resList;

    }


    /**
     * 179. 最大数 Medium
     *
     * @param nums
     * @return - String.compareTo() 是按照 lexicographically, 字典顺序排列的
     * - 利用compareTo, 来倒序排列 string, 刚好就得到我们要的结果.
     */
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        String[] strArr = new String[nums.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strArr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo((o1 + o2));
            }
        });
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strArr.length; i++) {
            sb.append(strArr[i]);
        }
        String result = sb.toString();
        if (result.charAt(0) == '0') {
            result = "0";
        }
        return result;
    }


    /**
     * 148. 排序链表 Medium
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middle = findMiddleListNode(head);
        ListNode right = sortList(middle.next);
        middle.next = null;
        ListNode left = sortList(head);
        return mergeLeftAndRight(left, right);
    }

    private ListNode mergeLeftAndRight(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        if (l1 != null) {
            node.next = l1;
        } else if (l2 != null) {
            node.next = l2;
        }
        return dummy.next;
    }

    /**
     * 快慢指针找到中间节点middle
     *
     * @param head
     * @return
     */
    private ListNode findMiddleListNode(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    /**
     * 147. 对链表进行插入排序 Medium
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            ListNode pre = dummy;//每次while循环，都将pre置为dummy节点
            //这一步是为了找到当前的cur的节点要插在哪个节点后面
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }
            //当前节点的下一个节点的值为pre节点的后一个节点
            cur.next = pre.next;
            //pre节点的后一个节点指向cur节点
            pre.next = cur;
            //移动cur的节点到next节点，再次while循环
            cur = next;
        }
        return dummy.next;
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
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }
        permuteDFS(result, new ArrayList<Integer>(), numList);
        return result;
    }

    private void permuteDFS(List<List<Integer>> result, ArrayList<Integer> levelList, List<Integer> remainList) {
        if (remainList.size() == 0) {
            result.add(new ArrayList<>(levelList));
            return;
        }
        for (int i = 0; i < remainList.size(); i++) {
            int cur = remainList.get(i);
            levelList.add(cur);
            remainList.remove(i);
            permuteDFS(result, levelList, remainList);
            levelList.remove(levelList.size() - 1);
            remainList.add(i, cur);//此处很精妙，remainList与levelList维持着此消彼长的关系
        }
    }


    /**
     * 435. 无重叠区间 Medium
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o.end));
        int count = 1;
        int initialEnd = intervals[0].end;
        int len = intervals.length;
        for (int i = 1; i < len; i++) {
            if (intervals[i].start < initialEnd) {
                continue;
            }
            initialEnd = intervals[i].end;
            count++;
        }
        return len - count;

    }

    /**
     * 452. 用最少数量的箭引爆气球 Meidum
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int count = 1;
        int initailEnd = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= initailEnd) {
                continue;
            }
            initailEnd = points[i][1];
            count++;
        }
        return count;
    }


    /**
     * 69. x 的平方根 Easy
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int l = 1, h = x;
        while (l <= h) {
            int m = l + (h - l) / 2;
            int sqrt = x / m;
            if (sqrt == m) {
                return m;
            } else if (m > sqrt) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return h;

    }


    /**
     * 744. 寻找比目标字母大的最小字母 Easy
     *
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {
        if (letters == null || letters.length == 0) {
            return target;
        }
        int len = letters.length;
        int l = 0, r = len - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (letters[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l < len ? letters[l] : letters[0];
    }


    /**
     * 540. 有序数组中的单一元素 Medium
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (m % 2 == 1) {
                m--;   // 保证 l/h/m 都在偶数位，使得查找区间大小一直都是奇数
            }
            if (nums[m] == nums[m + 1]) {
                l = m + 2;
            } else {
                h = m;
            }
        }
        return nums[l];
    }


    /**
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (isBadVersion(mid)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return l;
    }


    /**
     * 153. 寻找旋转排序数组中的最小值 Medium
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
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


    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置 Medium
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int first = binarySearch(nums, target);
        int last = binarySearch(nums, target + 1) - 1;
        if (first == nums.length || nums[first] != target) {
            return new int[]{-1, -1};
        } else {
            return new int[]{first, Math.max(first, last)};
        }
    }

    private int binarySearch(int[] nums, int target) {
        int l = 0, h = nums.length; // 注意 h 的初始值
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] >= target) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }


    boolean isBadVersion(int version) {
        return false;
    }


    /**
     * 572. 另一个树的子树 Easy
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return s == null && t == null;
        }
        return isSameTree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }


    public boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return s == null && t == null;
        }
        return s.val == t.val && isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }


    /**
     * 25. k个一组翻转链表 Hard
     *
     * @param head
     * @param k
     * @return
     */
    //TODO
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = dummy.next;
        int index = 1;
        while (index != k) {
            index++;
        }
        return null;
    }

    /**
     * 739. 每日温度 Medium
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] resArr = new int[n];
        Stack<Integer> indexStack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!indexStack.isEmpty() && T[indexStack.peek()] < T[i]) {
                int curIndex = indexStack.pop();
                resArr[curIndex] = i - curIndex;
            }
            indexStack.push(i);
        }
        return resArr;
    }

    /**
     * 496. 下一个更大元素 I
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] resArr = new int[nums1.length];
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        for (int i = 0; i < nums1.length; i++) {
            resArr[i] = map.getOrDefault(nums1[i], -1);
        }
        return resArr;
    }


    /**
     * 503. 下一个更大元素 II Medium
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] resArr = new int[n];
        Arrays.fill(resArr, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * n; i++) {
            int curNum = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < curNum) {
                resArr[stack.pop()] = curNum;
            }
            if (i < n) {
                stack.push(i);
            }
        }
        return resArr;
    }


    /**
     * 920. 会议室 LintCode
     *
     * @param intervals
     * @return
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return true;
        }
        PriorityQueue<Interval> queue = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);
        for (Interval interval : intervals) {
            queue.add(interval);
        }
        while (!queue.isEmpty()) {
            Interval cur = queue.poll();
            Interval next = queue.peek();
            if (next != null && cur.end > next.start) {
                return false;
            }
        }
        return true;
    }


    /**
     * 919. 会议室 II  Medium
     *
     * @param intervals
     * @return
     */
    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) return 0;
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int count = 0;
        //当新会议的开始时间大于minHeap中的最早结束时间，说明区间未重叠，不需要开新的会议室
        for (Interval interval : intervals) {
            minHeap.offer(interval.end);
            if (interval.start < minHeap.peek()) {
                count++;
            } else {
                minHeap.poll();
            }
        }
        return count;
    }


    /**
     * 57. 插入区间 Hard
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> resList = new ArrayList<>();
        if (intervals == null || intervals.size() == 0 || newInterval == null) {
            if (newInterval != null) {
                resList.add(newInterval);
            }
            return resList;
        }
        PriorityQueue<Point> queue = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.val - o2.val;
            }
        });
        queue.add(new Point(newInterval.start, 1));
        queue.add(new Point(newInterval.end, -1));
        for (Interval interval : intervals) {
            queue.add(new Point(interval.start, 1));
            queue.add(new Point(interval.end, -1));
        }
        int count = 0;
        Interval temp = new Interval();
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (count == 0) {
                temp.start = cur.val;
            }
            count += cur.flag;
            while (!queue.isEmpty() && cur.val == queue.peek().val) {
                cur = queue.poll();
                count += cur.flag;
            }
            if (count == 0) {
                temp.end = cur.val;
                resList.add(temp);
                temp = new Interval();
            }
        }
        return resList;
    }

    /**
     * 725. 分隔链表 Medium
     *
     * @param root
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode root, int k) {


        return null;
    }


    /**
     * 447. 回旋镖的数量 Easy
     *
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {

        return 0;
    }

    /**
     * 424. 替换后的最长重复字符 Medium
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {

        return 0;
    }


    /**
     * 140. 单词拆分 II
     *
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreakII(String s, List<String> wordDict) {


        return null;
    }

    /**
     * 150. 逆波兰表达式求值 Medium
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {


        return 0;
    }


    /**
     * 373. 查找和最小的K对数字 Medium
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Pair> queue = new PriorityQueue<>(k);
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            for (int j = 0; j < Math.min(nums2.length, k); j++) {
                Pair temp = new Pair(nums1[i], nums2[j]);
                if (queue.size() < k) {
                    queue.add(temp);
                } else if (queue.peek().sum > temp.sum) {
                    queue.poll();
                    queue.add(temp);
                }
            }
        }
        List<int[]> resList = new ArrayList<>();
        while (!queue.isEmpty()) {
            resList.add(queue.poll().A);
        }
        return resList;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> resList = new ArrayList<>();
        if (root == null) {
            return resList;
        }
        binaryTreePathsDFS(resList, new ArrayList<>(), root);

        return resList;
    }

    private void binaryTreePathsDFS(List<String> resList, List<Integer> levelList, TreeNode node) {
        if (node == null) {
            return;
        }
        levelList.add(node.val);
        if (node.left == null && node.right == null) {
            resList.add(convertList2Str(levelList));
            levelList.remove(levelList.size() - 1);
            return;
        }
        binaryTreePathsDFS(resList, levelList, node.left);
        binaryTreePathsDFS(resList, levelList, node.right);
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


    /**
     * 在递归遍历二叉树时，需要考虑当前的节点和它的孩子节点。如果当前的节点不是叶子节点，则在当前的路径末尾添加该节点，并递归遍历该节点的每一个孩子节点。如果当前的节点是叶子节点，则在当前的路径末尾添加该节点后，就得到了一条从根节点到叶子节点的路径，可以把该路径加入到答案中。
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths2nd(TreeNode root) {
        List<String> resList = new ArrayList<>();
        binaryTreePaths2ndRecur(resList, "", root);
        return resList;
    }

    private void binaryTreePaths2ndRecur(List<String> resList, String level, TreeNode root) {
        if (root != null) {
            level += String.valueOf(root.val);
            if (root.left == null && root.right == null) {//当前节点是叶子节点
                resList.add(level);
            } else {//当前节点是非叶子节点
                level += "->";
                binaryTreePaths2ndRecur(resList, level, root.left);
                binaryTreePaths2ndRecur(resList, level, root.right);
            }
        }
    }


    public int peakIndexInMountainArray(int[] A) {
        int l = 1, r = A.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (A[mid] > A[mid - 1] && A[mid] < A[mid + 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    /**
     * 941. 有效的山脉数组 LeetCode Easy
     *
     * @param A
     * @return
     */
    public boolean validMountainArray(int[] A) {
        if (A == null || A.length < 3) {
            return false;
        }
        int n = A.length - 1;
        int l = 0, r = n;
        while (l < n) {
            if (A[l] < A[l + 1]) {
                l++;
            } else {
                break;
            }
        }
        while (r >= 1) {
            if (A[r] < A[r - 1]) {
                r--;
            } else {
                break;
            }
        }
        return l > 0 && r < n && l == r;
    }

    /**
     * 845. 数组中的最长山脉 LeetCode Medium
     *
     * @param A
     * @return
     */
    public int longestMountain(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < A.length - 1; i++) {
            //find the maxium number [peak mountain]
            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                //get the left pointer and right pointer, expand from the center ,get the maximum boundary
                int l = i - 1;
                int r = i + 1;
                while (l > 0 && A[l - 1] < A[l]) {
                    l--;
                }
                while (r < A.length - 1 && A[r] > A[r + 1]) {
                    r++;
                }
                //record the maximum len
                res = Math.max(res, r - l + 1);
            }
        }
        return res;
    }

    /**
     * @param s
     * @return
     */
    public boolean checkRecord(String s) {
        char[] chas = s.toCharArray();
        int absent = 0;
        int late = 0;

        for (int i = 0; i < chas.length; i++) {
            if (chas[i] == 'A') {
                absent++;
                late = 0;
                if (absent > 1) {
                    return false;
                }
            } else if (chas[i] == 'L') {
                late++;
                if (late > 2) {
                    return false;
                }
            } else {
                late = 0;
            }
        }
        return true;
    }

    /**
     * 62. 不同路径 O(1) 空间压缩 DP
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }


    public int minPathSum(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[] arr = new int[col];
        arr[0] = grid[0][0];
        for (int j = 1; j < col; j++) {
            arr[j] += arr[j - 1] + grid[0][j];
        }
        for (int i = 1; i < row; i++) {
            arr[0] = arr[0] + grid[i][0];
            for (int j = 1; j < col; j++) {
                arr[j] = Math.min(arr[j], arr[j - 1]) + grid[i][j];
            }
        }
        return arr[col - 1];
    }


    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int j = 1; j <= amount; j++) {
            dp[0][j] = Integer.MAX_VALUE;
            if (j - coins[0] >= 0 && dp[0][j - coins[0]] != Integer.MAX_VALUE) {
                dp[0][j] = dp[0][j - coins[0]] + 1;
            }
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                int temp = Integer.MAX_VALUE;
                if (j - coins[i] >= 0 && dp[i][j - coins[i]] != Integer.MAX_VALUE) {
                    temp = dp[i][j - coins[i]] + 1;
                }
                dp[i][j] = Math.min(dp[i - 1][j], temp);
            }
        }
        return dp[coins.length - 1][amount] == Integer.MAX_VALUE ? -1 : dp[coins.length - 1][amount];
    }

    public int coinChange2nd(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int j = 1; j <= amount; j++) {
            dp[j] = Integer.MAX_VALUE;
            if (j - coins[0] >= 0 && dp[j - coins[0]] != Integer.MAX_VALUE) {
                dp[j] = dp[j - coins[0]] + 1;
            }
        }

        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                int temp = Integer.MAX_VALUE;
                if (j - coins[i] >= 0 && dp[j - coins[i]] != Integer.MAX_VALUE) {
                    temp = dp[j - coins[i]] + 1;
                }
                dp[j] = Math.min(temp, dp[j]);

            }
        }
        return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
    }


    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < S || (sum + S) % 2 == 1) return 0;
        int n = (sum + S) / 2;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = n; j >= nums[i]; j--) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[n];
    }


    public boolean canConstruct(String ransomNote, String magazine) {
        int[] counter = new int[256];
        char[] ransomChar = ransomNote.toCharArray();
        for (int i = 0; i < ransomChar.length; i++) {
            counter[ransomChar[i] - 'a']++;
        }
        char[] magazineChar = magazine.toCharArray();
        for (int i = 0; i < magazineChar.length; i++) {
            counter[magazineChar[i] - 'a']--;
        }
        for (int count : counter) {
            if (count > 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {

//        int[] nums = {3, 2, 3};
//        System.out.println(JSON.toJSON(handler.majorityElementII(nums)));

//        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//        System.out.println(handler.removeDuplicates(nums));
//        int[] prices = {7, 1, 5, 3, 6, 4};
//        System.out.println(handler.maxProfit(prices));
//        int[] nums = {1, 2, 3, 4, 5, 6, 7};
//        handler.rotate(nums, 3);
//        int[] digits = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
//        handler.plusOne(digits);
//        int[] nums = {0, 1, 0, 3, 12};
//        handler.moveZeroes(nums);
//        handler.reverse(1534236469);

//        System.out.println(handler.firstUniqChar("loveleetcode"));
//        handler.strStr("mississippi", "issipi");
//        handler.countAndSay(4);
//        int[] nums = {2, 7, 9, 3, 1};
//        handler.rob(nums);
//        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
//        handler.removeDuplicatesII(nums);
//        int[] nums = {1, 3, 4, 2, 2};
//        handler.findDuplicate(nums);
//        int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
//        handler.setZeroes(matrix);
//        handler.longestPalindromeSubseq("abdcbe");
//        handler.countSubstrings("aaa");
//        int[] nums = {2, 3, 1, 1, 4};
//        handler.canJump(nums);
//        System.out.println(handler.letterCombinations("234"));

//        handler.generateParenthesis(3);
//        int s = 7;
//        int[] nums = {2, 3, 1, 2, 4, 3};
//        handler.minSubArrayLen(s, nums);
//        int[] nums = {1,2,3,1,2,3};
//        int k = 2;
//        int[] nums = {2, 2};
//        int k = 3;
//        handler.containsNearbyDuplicateII(nums, k);
//        int[] nums = {2, 0, 2, 1, 1, 0};
//        int[] nums = {2, 0, 1};
//        handler.sortColors(nums);
//        int[] nums = {3, 2, 2, 3};
//        int val = 3;
//        handler.removeElement(nums, val);
//        handler.isUgly(60);

//        handler.isHappy(19);
//        handler.buildPalin("abcba");
//        handler.countSubstrings("abcbdbcf");
//        System.out.println(handler.partition("aab"));
//        String str = "abcdefcba";
//        handler.isPalindrome(str, 0, str.length() - 1);

//        String s = "applepenapple";
//        List<String> wordDict = Arrays.asList("apple", "pen");
//        handler.wordBreak(s, wordDict);
//        char[][] matrix ={{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
//        char[][] matrix = {{'0', '0', '0', '1'}, {'1', '1', '0', '1'}, {'1', '1', '1', '1'}, {'0', '1', '1', '1'}, {'0', '1', '1', '1'}};
//        char[][] matrix = {{'1'}};
//        handler.maximalSquare(matrix);
//        String s = "babgbag", t = "bag";
//        System.out.println(handler.numDistinct(s, t));

//        System.out.println(handler.characterReplacement("AABABBA", 1));
//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = null;
////        ListNode resHead = handler.removeNthFromEnd(node1, 2);
////        System.out.println(JSON.toJSON(resHead.val));
////        handler.reverseList(node1);
//        ListNode resultHead = handler.reverseBetween(node1, 2, 4);
//        System.out.println(JSON.toJSON(resultHead.val));


//        ListNode anode1 = new ListNode(4);
//        ListNode anode2 = new ListNode(1);
//
//        ListNode bnode1 = new ListNode(5);
//        ListNode bnode2 = new ListNode(0);
//        ListNode bnode3 = new ListNode(1);
//
//        ListNode cnode1 = new ListNode(8);
//        ListNode cnode2 = new ListNode(4);
//        ListNode cnode3 = new ListNode(5);
//
//        anode1.next = anode2;
//        anode2.next = cnode1;
//
//        bnode1.next = bnode2;
//        bnode2.next = bnode3;
//        bnode3.next = cnode1;
//
//        cnode1.next = cnode2;
//        cnode2.next = cnode3;
//        cnode3.next = null;

//        handler.getIntersectionNode(anode1, bnode1);


//        ListNode n1 = new ListNode(1);
//        ListNode n2 = new ListNode(2);
//        ListNode n3 = new ListNode(2);
//        ListNode n4 = new ListNode(1);
//        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = null;
//        handler.isPalindromeIII(n1);

//[1,3],[2,6],[8,10],[15,18]
        List<Interval> intervals = new ArrayList<Interval>() {
            {
//                add(new Interval(1, 3));
//                add(new Interval(2, 6));
//                add(new Interval(8, 10));
//                add(new Interval(15, 18));

                //
//                add(new Interval(1, 4));
//                add(new Interval(4, 5));
                //canAttendMeetings
                add(new Interval(0, 30));
                add(new Interval(5, 10));
                add(new Interval(15, 20));
            }
        };

//        print(handler.merge(intervals));
//        handler.merge2nd(intervals);
//        handler.canAttendMeetings(intervals);
//        handler.minMeetingRooms(intervals);
//        int[] nums = {3, 30, 34, 5, 9};
//        print(handler.largestNumber(nums));


//        ListNode l1 = new ListNode(40);
//        ListNode l2 = new ListNode(20);
//        ListNode l3 = new ListNode(10);
//        ListNode l4 = new ListNode(30);
//        l1.next = l2;
//        l2.next = l3;
//        l3.next = l4;
//        l4.next = null;
//        handler.insertionSortList(l1);
//        int[] nums = {1, 2, 3};
//        handler.permute(nums);
//        handler.mySqrt(8);
//        char[] chars = {'e', 'e', 'e', 'e', 'e', 'e', 'n', 'n', 'n', 'n'};
//        handler.nextGreatestLetter(chars, 'e');
//        handler.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
//        handler.nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2});
//        handler.nextGreaterElements(new int[]{1, 2, 1});

//        handler.merge()
//        handler.peakIndexInMountainArray(new int[]{0, 2, 1, 0});
//        handler.validMountainArray(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
//        handler.longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5});
//        handler.checkRecord("PPALLPL");
//        handler.uniquePaths(3, 2);
//        handler.minPathSum(new int[][]{{1, 3, 1},
//                {1, 5, 1},
//                {4, 2, 1}});
        handler.findTargetSumWays(new int[]{7, 9, 3, 8, 0, 2, 4, 8, 3, 9}, 0);
    }


//    winter

    private static void print(Object obj) {
        System.out.println(JSON.toJSONString(obj));
    }


    class Point {
        int val;
        int flag;

        public Point(int val, int flag) {
            this.val = val;
            this.flag = flag;
        }
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
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

    static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }


    }


    class Pair implements Comparable<Pair> {
        int[] A = new int[2];
        int sum;

        public Pair(int row, int col) {
            A[0] = row;
            A[1] = col;
            this.sum = row + col;
        }


        @Override
        public int compareTo(Pair o) {
            return o.sum - this.sum;
        }
    }


    /**
     * 703. 数据流中的第K大元素 Easy
     */
    class KthLargest {

        final PriorityQueue<Integer> queue;
        final int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            queue = new PriorityQueue<Integer>(k);
            for (int i : nums) {
                add(i);
            }
        }

        public int add(int val) {
            if (queue.size() < k) {
                queue.offer(val);
            } else if (queue.peek() < val) {
                queue.poll();
                queue.offer(val);
            }
            return queue.peek();
        }

    }
}

