package algorithm.leetcode.case1;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/2/11 18:03
 * Description
 */
public class RepeativeCaseIII {

    static RepeativeCaseIII handler = new RepeativeCaseIII();

    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    res += 4;
                    if (i > 0 && grid[i - 1][j] == 1) res -= 2;
                    if (j > 0 && grid[i][j - 1] == 1) res -= 2;
                }
            }
        }
        return res;
    }

    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};//对应了上右下左四个方向
    int rows, cols;
    boolean[][] visited;

    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        rows = grid.length;
        cols = grid[0].length;
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    count += dfsA(grid, i, j);
                }
            }
        }
        return count;
    }

    private int dfsA(int[][] grid, int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= cols) {
            return 0;
        }
        if (grid[x][y] == 1) return 1;
        int res = 1;
        grid[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int nextX = x + directions[i][0];
            int nextY = y + directions[i][1];
            res = Math.min(res, dfsA(grid, nextX, nextY));
        }
        return res;
    }


    public int numberofDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        rows = grid.length;
        cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder path = new StringBuilder();
                    dfs(grid, i, j, path, i, j);
                    set.add(path.toString());
                }
            }
        }
        return set.size();
    }

    /**
     * @param grid  原始的格子
     * @param x     当前的位置 x
     * @param y     当前的位置 y
     * @param path  走过的轨迹
     * @param baseX 这一层dfs开始时是基于哪个x开始的
     * @param baseY 这一层dfs开始时是基于哪个y开始的
     */
    private void dfs(int[][] grid, int x, int y, StringBuilder path, int baseX, int baseY) {
        if (x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] == 0) {
            return;
        }
        grid[x][y] = 0;
        path.append(x - baseX).append(y - baseY);
        for (int i = 0; i < 4; i++) {
            int nextX = x + directions[i][0];
            int nextY = y + directions[i][1];
            dfs(grid, nextX, nextY, path, baseX, baseY);
        }
    }


    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] && !visited[i][j]) {
                    count++;
                    dfs(grid, i, j);
                }

            }
        }

        return count;
    }

    private void dfs(boolean[][] grid, int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nextX = x + directions[i][0];
            int nextY = y + directions[i][1];
            boolean inArea = nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols;
            if (inArea && grid[nextX][nextY] && !visited[nextX][nextY]) {
                dfs(grid, nextX, nextY);
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        dfs(nums, results, new ArrayList<Integer>());
        return results;
    }

    private void dfs(int[] nums, List<List<Integer>> results, List<Integer> sub) {
        if (sub.size() == nums.length) {
            results.add(new ArrayList<>(sub));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (sub.contains(nums[i])) continue;
            sub.add(nums[i]);
            dfs(nums, results, sub);
            sub.remove(sub.size() - 1);
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        Queue<TreeNode> queue = new LinkedList<>();
        int level = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            results.add(new ArrayList<>());
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                results.get(level).add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            ++level;
        }
        return results;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> resluts = new ArrayList<>();
        if (root == null) return resluts;
        dfs(root, sum, resluts, new ArrayList<Integer>());
        return resluts;
    }

    private void dfs(TreeNode root, int sum, List<List<Integer>> resluts, ArrayList<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        if(root.left==null&&root.right==null&&root.val==sum) resluts.add(new ArrayList<>(list));
        dfs(root.left, sum - root.val, resluts, list);
        dfs(root.right, sum - root.val, resluts, list);
        list.remove(list.size() - 1);
    }


    //spring
    public static void main(String[] args) {

//        int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
//        handler.maxAreaOfIsland(grid);
    }
    //winter


}
