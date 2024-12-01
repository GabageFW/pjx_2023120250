import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class L2023120250_9_Test {

    /**
     * 测试目的：测试 possibleBipartition 方法的功能
     * 用到的测试用例：测试能够成功将人分成两组的情况，以及无法分成两组的情况
     */
    @Test
    public void testPossibleBipartition() {
        Solution9 solution = new Solution9();

        // 用例1：可以分成两组
        int n1 = 4;
        int[][] dislikes1 = {{1, 2}, {1, 3}, {2, 4}};
        assertTrue(solution.possibleBipartition(n1, dislikes1));

        // 用例2：不能分成两组
        int n2 = 3;
        int[][] dislikes2 = {{1, 2}, {1, 3}, {2, 3}};
        assertFalse(solution.possibleBipartition(n2, dislikes2));

        // 用例3：不能分成两组
        int n3 = 5;
        int[][] dislikes3 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}};
        assertFalse(solution.possibleBipartition(n3, dislikes3));

        // 用例4：没有不喜欢的人
        int n4 = 3;
        int[][] dislikes4 = {};
        assertTrue(solution.possibleBipartition(n4, dislikes4));
    }

    /**
     * 测试目的：测试 unit 方法的功能
     * 用到的测试用例：测试两个不同组的人合并时的按秩合并
     */
    @Test
    public void testUnit() {
        Solution9 solution = new Solution9();
        int[] fa = new int[6]; // fa[0]到fa[5]，假设有5个人
        Arrays.fill(fa, -1);

        // 初始状态：fa = {-1, -1, -1, -1, -1, -1}

        // 合并1和2
        solution.unit(1, 2, fa);
        // 合并后：fa = {-1, 2, -1, -1, -1, -1}，1的父亲是2

        // 合并2和3
        solution.unit(2, 3, fa);
        // 合并后：fa = {-1, 2, 2, -1, -1, -1}，2和3合并，1的父亲是2

        // 验证3的父亲是2
        assertEquals(2, solution.findFa(3, fa));
    }

    /**
     * 测试目的：测试 isconnect 方法的功能
     * 用到的测试用例：检查两个元素是否连通
     */
    @Test
    public void testIsconnect() {
        Solution9 solution = new Solution9();
        int[] fa = new int[6]; // fa[0]到fa[5]，假设有5个人
        Arrays.fill(fa, -1);

        // 合并1和2
        solution.unit(1, 2, fa);
        // 合并2和3
        solution.unit(2, 3, fa);

        // 检查是否1和3连通
        assertTrue(solution.isconnect(1, 3, fa));

        // 检查是否1和4连通
        assertFalse(solution.isconnect(1, 4, fa));
    }

    /**
     * 测试目的：测试 findFa 方法的功能
     * 用到的测试用例：验证路径压缩是否正确工作
     */
    @Test
    public void testFindFa() {
        Solution9 solution = new Solution9();
        int[] fa = new int[6]; // fa[0]到fa[5]，假设有5个人
        Arrays.fill(fa, -1);

        // 合并1和2
        solution.unit(1, 2, fa);
        // 合并2和3
        solution.unit(2, 3, fa);

        // 检查1的代表元素
        assertEquals(2, solution.findFa(1, fa));

        // 检查3的代表元素
        assertEquals(2, solution.findFa(3, fa));
    }
}
