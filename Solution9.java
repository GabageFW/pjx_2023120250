import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 *
 * 给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
 *
 * 给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和  bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * 输出：true
 * 解释：group1 [1,4], group2 [2,3]
 * 示例 2：
 *
 * 输入：n = 3, dislikes = [[1,2],[1,3],[2,3]]
 * 输出：false
 * 示例 3：
 *
 * 输入：n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= n <= 2000
 * 0 <= dislikes.length <= 104
 * dislikes[i].length == 2
 * 1 <= dislikes[i][j] <= n
 * ai < bi
 * dislikes 中每一组都 不同
 *
 */
class Solution9 {

    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] fa = new int[n + 1];
        Arrays.fill(fa, -1);  // 初始化并查集

        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; ++i) {
            g[i] = new ArrayList<Integer>();
        }

        // 构建图
        for (int[] p : dislikes) {
            g[p[0]].add(p[1]);
            g[p[1]].add(p[0]);
        }

        // 遍历所有节点，检查每一条边的连通性
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < g[i].size(); ++j) {
                unit(g[i].get(0), g[i].get(j), fa);
                if (isconnect(i, g[i].get(j), fa)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void unit(int x, int y, int[] fa) {
        // 查找 x 和 y 的根
        x = findFa(x, fa);
        y = findFa(y, fa);

        // 如果 x 和 y 已经是同一个根，直接返回
        if (x == y) {
            return;
        }

        // 按秩合并：选择小集合合并到大集合
        if (fa[x] < fa[y]) {
            // 如果 x 的集合比 y 的集合大
            fa[x] += fa[y]; // 将 x 的集合合并到 y 上
            fa[y] = x; // 将 y 的根指向 x
        } else {
            // 如果 y 的集合比 x 的集合大，或者相等
            fa[y] += fa[x]; // 将 y 的集合合并到 x 上
            fa[x] = y; // 将 x 的根指向 y
        }
    }


    public boolean isconnect(int x, int y, int[] fa) {
        x = findFa(x, fa);
        y = findFa(y, fa);
        return x == y;
    }

    public int findFa(int x, int[] fa) {
        // 如果 fa[x] 小于 0，说明 x 是根节点
        if (fa[x] < 0) {
            return x;
        }

        // 递归查找父节点，并进行路径压缩
        fa[x] = findFa(fa[x], fa);  // 路径压缩
        return fa[x];
    }
}
