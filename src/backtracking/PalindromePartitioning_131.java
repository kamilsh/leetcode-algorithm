// https://leetcode-cn.com/problems/palindrome-partitioning/
package backtracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning_131 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.partition("aab"));
    }
}

class Solution {
    List<List<String>> res = new ArrayList<>();
    List<String> path = new ArrayList<>();
    public List<List<String>> partition(String s) {
        dfs(s, 0);
        return res;
    }

    public void dfs(String s, int walkingIndex) {
        if (walkingIndex == s.length()) {
            // 已经分割到最后一位了
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = walkingIndex; i < s.length(); ++i) {
            if (isPalindrome(s, walkingIndex, i)) {
                // 如果目前这个子字符串都不是回文串，就没必要继续向后找了
                path.add(s.substring(walkingIndex, i+1));
                dfs(s, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }

    public boolean isPalindrome(String s, int left, int right) {
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}