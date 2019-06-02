package algorithm.nowcoder.offer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by FrankCooper
 * Date 2019/5/25 16:15
 * Description 专题的合辑
 */
public class TopicProcess {

    private static TopicProcess handler = new TopicProcess();


    /**
     * 剑指offer29，超过数据一半的数 Partition 排序算法
     */
    public int moreThanHalfNum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int index = partition(arr, 0, arr.length - 1);
        int middle = arr.length / 2;
        while (index != middle) {
            if (index > middle) {
                index = partition(arr, 0, index - 1);
            } else {
                index = partition(arr, index + 1, arr.length - 1);
            }
        }
        if (checkMoreThanHalf(arr, arr[middle])) {
            return arr[middle];
        } else {
            return 0;
        }
    }


    private int partition(int[] arr, int left, int right) {
        int index = left;
        for (int i = left; i < right; i++) {
            if (arr[i] <= arr[right]) {
                swap(arr, i, index++);
            }
        }
        swap(arr, right, index);
        return index;
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    private static boolean checkMoreThanHalf(int[] arr, int num) {
        int times = 0;
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            if (num == arr[i]) {
                times++;
            }
        }
        if (times * 2 <= length) {
            return false;
        }
        return true;
    }


    /**
     * 最小的K个数 剑指offer 30
     *
     * @param arr
     * @param k
     * @return
     */
    public ArrayList<Integer> getLeastNumbers(int[] arr, int k) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        if (arr == null || arr.length == 0 || arr.length < k || k == 0) {
            return resultList;
        }
        int index = partition(arr, 0, arr.length - 1);
        while (index != k - 1) {
            if (index > k - 1) {
                index = partition(arr, 0, index - 1);
            } else {
                index = partition(arr, index + 1, arr.length - 1);
            }
        }
        for (int i = 0; i < k; i++) {
            resultList.add(arr[i]);
        }
        return resultList;
    }

    /**
     * 最小的K个数 剑指offer 30
     *
     * @param arr
     * @param k
     * @return
     */
    public ArrayList<Integer> getLeastNumbers2nd(int[] arr, int k) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        if (arr == null || arr.length == 0 || arr.length < k || k == 0) {
            return resultList;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        //交换堆顶元素与最后一个
        int size = arr.length;
        swap(arr, 0, --size);//n-1位置和0位置交换
        while (size > 0) {
            buildMaxHeap(arr, 0, size);
            swap(arr, 0, --size);
        }
        for (int i = 0; i < k; i++) {
            resultList.add(arr[i]);
        }
        return resultList;
    }

    /**
     * 调整出一个大根堆，完全二叉树：新的一层从左往右是依次补齐的
     *
     * @param arr
     * @param index
     */
    private static void heapInsert(int[] arr, int index) {
        //当前节点的左节点2*i+1,右节点2*i+2,父节点是（i-1）/2
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 构建大顶堆
     */
    private void buildMaxHeap(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            //看当前节点index的左右两个孩子节点大小，取出最大的下标
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            //取出最大的与当前的比较，记录下大的
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            //交换当前的与大的，然后将largest的赋给index，继续寻找左孩子节点
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    /**
     * 最小的K个数 剑指offer 30
     *
     * @param arr
     * @param k
     * @return
     */
    public ArrayList<Integer> getLeastNumbers3rd(int[] arr, int k) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        if (arr == null || arr.length == 0 || arr.length < k || k == 0) {
            return resultList;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int i = 0; i < arr.length; i++) {
            if (maxHeap.size() != k) {
                maxHeap.offer(arr[i]);
            } else if (maxHeap.peek() > arr[i]) {
                maxHeap.poll();
                maxHeap.offer(arr[i]);
            }
        }
        resultList.addAll(maxHeap);
        return resultList;
    }


    /**
     * 寻找和为定值的两个数，编程之法2.2，剑指offer41
     *
     * @param arr
     * @param sum
     * @return
     */
    public ArrayList<Integer> findNumbersWithSum(int[] arr, int sum) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        if (arr == null || arr.length < 2) {
            return resultList;
        }
        int l = 0, r = arr.length - 1;
        long curSum;
        while (l < r) {
            curSum = arr[l] + arr[r];
            if (curSum == sum) {
                resultList.add(arr[l]);
                resultList.add(arr[r]);
                break;
            } else if (curSum < sum) {
                l++;
            } else {
                r--;
            }
        }
        return resultList;
    }


    /**
     * 举一反三：寻找和为定值的两个数的下标 编程之法2.2
     *
     * @param arr
     * @param sum
     * @return
     */
    public int[] findNumbersWithSumIndex(int[] arr, int sum) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] sumIndex = new int[2];
        for (int i = 0; i < arr.length; i++) {
            if (hashMap.containsKey(sum - arr[i])) {
                sumIndex[0] = i + 1;
                sumIndex[1] = hashMap.get(sum - arr[i]) + 1;
                break;
            }
            hashMap.put(arr[i], i);
        }
        return sumIndex;
    }


    /**
     * 剑指offer31， 编程之法2.4 利用数组的特性
     *
     * @param arr
     * @return
     */
    public int findGreatestSumOfSubArray(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        int cur = arr[0], res = cur;
        for (int i = 1; i < arr.length; i++) {
            cur += arr[i];
            res = Math.max(res, cur);
            cur = cur > 0 ? cur : 0;
        }
        return res;
    }


    /**
     * 剑指offer31， 编程之法2.4 DP
     *
     * @param arr
     * @return
     */
    public int findGreatestSumOfSubArray2nd(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        int curSum = 0;
        int maxSum = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (curSum >= 0) {
                curSum += arr[i];
            } else {
                curSum = arr[i];
            }
            if (curSum > maxSum) {
                maxSum = curSum;
            }
        }
        return maxSum;
    }


    /**
     * 数组中子数组的最大累乘积P373，编程之法5.1
     *
     * @param arr
     * @return
     */
    public double maxProductSubString(double[] arr) {
        if (arr == null || arr.length == 0) {
            return 0.0;
        }
        double max = arr[0];
        double min = arr[0];
        double res = arr[0];
        for (int i = 1; i < arr.length; i++) {
            double maxEnd = max * arr[i];
            double minEnd = min * arr[i];
            max = Math.max(Math.max(maxEnd, minEnd), arr[i]);
            min = Math.min(Math.min(maxEnd, minEnd), arr[i]);
            res = Math.max(res, max);
        }
        return res;
    }


    /**
     * 3. 无重复字符的最长子串 双指针
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int slow = 0, fast = 0, len = s.length();
        int res = 0;
        while (slow < len && fast < len) {
            //快指针不在set说明，fast所在的下标到slow所在的值之间没有重复值
            if (!set.contains(s.charAt(fast))) {
                set.add(s.charAt(fast++));
                res = Math.max(res, fast - slow);
            } else {
                set.remove(s.charAt(slow++));
            }
        }
        return res;
    }


    /**
     * 编程之法1.2 方法3 素数相乘
     *
     * @param src
     * @param target
     * @return
     */
    private boolean stringContains(String src, String target) {
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        int multi = 1;
        for (int i = 0; i < src.length(); i++) {
            int x = primes[src.charAt(i) - 'A'];
            if (multi % x != 0) {
                multi *= x;
            }
        }
        for (int j = 0; j < target.length(); j++) {
            int x = primes[target.charAt(j) - 'A'];
            if (multi % x != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 编程之法1.2 方法4:HashTable
     *
     * @param src
     * @param target
     * @return
     */
    private boolean stringContains2nd(String src, String target) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < src.length(); i++) {
            hashMap.put(src.charAt(i), hashMap.getOrDefault(src.charAt(i), 0) + 1);
        }
        for (int j = 0; j < target.length(); j++) {
            if (hashMap.get(target.charAt(j)) == null) {
                return false;
            }
        }
        return true;
    }


    /**
     * 程之法1.2 方法5:位运算
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean stringContain3rd(String a, String b) {
        int hash = 0;
        for (int i = 0; i < a.length(); ++i) {
            int temp = 1 << (a.charAt(i) - 'A');
            hash |= temp;
        }
        for (int i = 0; i < b.length(); ++i) {
            if ((hash & (1 << (b.charAt(i) - 'A'))) == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 242. 有效的字母异位词
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] comprators = new int[26];
        for (int i = 0; i < s.length(); i++) {
            comprators[s.charAt(i) - 'a']++;
            comprators[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < comprators.length; i++) {
            if (comprators[i] != 0) {
                return false;
            }
        }
        return true;
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> resultList = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return resultList;
        }
        Map<String, List<String>> hashmap = new HashMap<>();
        for (String str : strs) {
            char[] chas = str.toCharArray();
            Arrays.sort(chas);
            String temp = String.valueOf(chas);
            hashmap.putIfAbsent(temp, new ArrayList<>());
            hashmap.get(temp).add(str);
        }
        resultList = new ArrayList<>(hashmap.values());
        return resultList;
    }


    /**
     * 46. 全排列 Medium
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> seed = new ArrayList<>();
        for (int num : nums) {
            seed.add(num);
        }
        int n = nums.length;

        permuteBackTracing(seed, output, n, 0);

        return output;
    }

    private void permuteBackTracing(List<Integer> seed, List<List<Integer>> output, int n, int index) {
        if (index == n) {
            output.add(new ArrayList<>(seed));
        }
        for (int i = index; i < n; i++) {
            Collections.swap(seed, index, i);
            permuteBackTracing(seed, output, n, index + 1);
            Collections.swap(seed, index, i);
        }
    }



    private List<String> calcAllPermutation(String str) {
        List<String> output = new ArrayList<>();
        char[] chas = str.toCharArray();
        int n = chas.length;
        calcAllPermutationBackTracing(chas, output, n, 0);
        return output;
    }

    /**
     *
     * @param chas seed char
     * @param output 输出的收集List
     * @param n 总的个数
     * @param level 层数
     */
    private void calcAllPermutationBackTracing(char[] chas, List<String> output, int n, int level) {
        if (level == n) {
            output.add(String.valueOf(chas));
        }
        for (int i = level; i < n; i++) {
            swap(chas, level, i);
            calcAllPermutationBackTracing(chas, output, n, level + 1);
            swap(chas, level, i);
        }
    }

    private void swap(char[] chas, int i, int j) {
        char temp = chas[i];
        chas[i] = chas[j];
        chas[j] = temp;
    }


    public static void main(String[] args) {

//        handler.findGreatestSumOfSubArray(new int[]{1, -2, 3, 10, -4, 7, 2, -5});
//        handler.lengthOfLongestSubstring("abcabcbb");
//        handler.stringContains("ABCAD", "ABE");
//        handler.stringContains2nd("ABCAD", "ABE");
//        handler.stringContain3rd("ABCAD", "ABE");
//        System.out.println(handler.permute(new int[]{1, 2, 3}));
        System.out.println(handler.calcAllPermutation("abc"));


        System.out.println("end");

    }

}
