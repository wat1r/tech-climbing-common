package company.one;

import java.util.Stack;

/**
 * @Author Frank Cooper(wang zhou)
 * @Date: 2024/09/02/ 18:57
 * @description
 */
public class One {

     static class _1st_1 {

        public static void main(String[] args) {
            _1st_1 handler = new _1st_1();
            int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
            handler.process(height);
        }


        public int process(int[] height) {
            if (height == null || height.length == 0) {
                return 0;
            }
            Stack<Integer> stack = new Stack<>();
            int result = 0;
            stack.push(0);
            for (int i = 1; i < height.length; i++) {
                //如果当前值大于栈顶值，需要弹出栈顶的值，并在弹出的时候执行计算雨水的逻辑
                if (height[i] >= height[stack.peek()]) {
                    int bottom = height[stack.pop()];
                    //当前栈有元素，且当前值，大于bottom后面的值
                    while (!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                        int leftEdge = stack.pop();
                        result += (height[leftEdge] - bottom) * (i - leftEdge - 1);
                        bottom = height[leftEdge];//将bottom移动到左边的边界处
                    }
                    //上面的while循环跑完后，判断栈顶的值比当前值大的话，进下面的逻辑
                    if (!stack.isEmpty() && height[i] < height[stack.peek()]) {
                        result += (height[i] - bottom) * (i - stack.peek() - 1);
                    }
                }
                stack.push(i);
            }
            return result;
        }
    }


     static class _1st_2{

        public static void main(String[] args) {
            _1st_2 handler = new _1st_2();
            int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
            handler.process(height);
        }

        public int process(int[] height) {
            int n = height.length;
            //leftH[i]表示第i个柱子左边最高的柱子的高度
            int[] leftH = new int[n];
            //rightH[i]表示第i个柱子右边最高的柱子的高度
            //上述的两个数组应该是符合单调性的
            int[] rightH = new int[n];
            //最左边的柱子的左边没有柱子了，leftH[0]=0
            for (int i = 0; i < n - 2; i++) {
                leftH[i + 1] = Math.max(leftH[i], height[i]);
            }
            //最右边的柱子的右边没有柱子了，rightH[n-1]=0
            for (int i = n - 2; i >= 0; --i) {
                rightH[i] = Math.max(rightH[i + 1], height[i + 1]);
            }
            int res = 0;
            //每次取左右两侧的最小值，做高度，每次步进1个长度
            for (int i = 1; i < n - 1; i++) {
                int m = Math.min(leftH[i], rightH[i]);
                if (m > height[i]) {
                    res += (m - height[i]);
                }
            }
            return res;
        }
    }


    static class _1st_3 {
        public static void main(String[] args) {
            _1st_3 handler = new _1st_3();
            int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
            handler.process(height);
        }

        public int process(int[] height) {
            int res = 0;
            //左右侧的索引
            int l = 0, r = height.length - 1;
            //l r 对应的height，初始值是MIN
            int left = Integer.MIN_VALUE, right = Integer.MIN_VALUE;
            while (l < r) {
                //获取当前索引 l r的最大高度
                left = Math.max(left, height[l]);
                right = Math.max(right, height[r]);
                //优先低的高度进行计算
                if (left < right) {
                    //l 要强制向右滑动 计算面积，更新左侧的最大高度left
                    res += left - height[l++];
                    left = Math.max(left, height[l]);
                } else {
                    //r 要强制向左滑动 计算面积，更新右侧的最大高度right
                    res += right - height[r--];
                    right = Math.max(right, height[r]);
                }
            }
            return res;
        }
    }
}
