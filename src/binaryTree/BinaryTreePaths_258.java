// https://leetcode-cn.com/problems/binary-tree-paths/
package binaryTree;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();  // result
        if (root == null)   return res;
        List<Integer> path = new ArrayList<>();  // 当前路径的所有值
        // 递归
        traversal(root, res, path);
        return  res;
    }

    public void traversal(TreeNode node, List<String> res, List<Integer> path) {
        path.add(node.val);  // 每到一个节点就向path中添加当前val
        // 递归结束条件，叶子节点
        if (node.left == null && node.right == null) {
            StringBuilder sb =new StringBuilder();
            for (int i = 0; i < path.size() - 1; ++i) {
                sb.append(path.get(i)).append("->");
            }
            sb.append(path.get(path.size() - 1));
            res.add(sb.toString());
            return;
        }
        // 遍历左节点
        if (node.left != null) {
            traversal(node.left, res, path);  // 递归
            path.remove(path.size() - 1);  // 回溯，去掉最后一个节点。也就是删去当前节点下面那个节点。
            // 删去后path里最后一个节点为当前节点，然后继续向右遍历或返回上一层。
        }
        // 遍历右节点
        if (node.right != null) {
            traversal(node.right, res, path);  // 递归
            path.remove(path.size() - 1);  // 回溯，去掉最后一个节点。也就是删去当前节点下面那个节点。
        }
    }
}

public class BinaryTreePaths_258 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        Solution solution = new Solution();
        System.out.println(solution.binaryTreePaths(root));
    }
}
