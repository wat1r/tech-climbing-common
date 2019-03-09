package algorithm.leetcode;

import com.alibaba.fastjson.JSON;
import org.omg.CORBA.INTERNAL;

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
            quickSortRecursive(nums, partition[0] + 1, right);
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
        ListNode current = head;
        ListNode next = null, prev = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
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
                        if(!ctrlSet.contains(key)){
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
        int[] nums = {2, 7, 9, 3, 1};
        handler.rob(nums);


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