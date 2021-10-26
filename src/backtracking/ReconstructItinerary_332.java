// https://leetcode-cn.com/problems/reconstruct-itinerary/
package backtracking;

import java.util.*;

public class ReconstructItinerary_332 {
    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
//      [["JFK","SFO"],["SFO","ATL"],["SFO","BOS"],["SFO","LHR"],
//      ["ATL","BOS"],["BOS","LHR"],["LHR","BOS"],["BOS","ATL"]]
        tickets.add(List.of("JFK", "SFO"));
        tickets.add(List.of("SFO", "ATL"));
        tickets.add(List.of("SFO", "BOS"));
        tickets.add(List.of("SFO", "LHR"));
        tickets.add(List.of("ATL", "BOS"));
        tickets.add(List.of("BOS", "LHR"));
        tickets.add(List.of("LHR", "BOS"));
        tickets.add(List.of("BOS", "ATL"));
        Solution2 solution2 = new Solution2();
        solution2.findItinerary(tickets);
    }
}

//class Solution2 {
//    List<String> res = new ArrayList<>();
//
//    public List<String> findItinerary(List<List<String>> tickets) {
//        //from,             dest,    number
//        Map<String, TreeMap<String, Integer>> map = new HashMap<>();
//        for (List<String> ticket : tickets) {
//            String from = ticket.get(0);
//            String to = ticket.get(1);
//
//            map.putIfAbsent(from, new TreeMap<>());
//            TreeMap<String, Integer> treeMap = map.get(from);
//            treeMap.put(to, treeMap.getOrDefault(to, 0) + 1);
//        }
//
//        res.add("JFK");
//        backtrack(tickets, map, 0);
//
//        return res;
//    }
//
//    private boolean backtrack(List<List<String>> tickets, Map<String, TreeMap<String, Integer>> map, int progress) {
//        if (progress == tickets.size()) {
//            return true;
//        }
//
//        TreeMap<String, Integer> tos = map.get(res.get(res.size() - 1));
//        if (tos == null || tos.isEmpty() || tos.size() == 0)
//            return false;
//
//        for (String str : tos.keySet()) {
//            if (tos.get(str) == 0)
//                continue;
//
//            res.add(str);
//            tos.put(str, tos.get(str) - 1);
//
//            if (backtrack(tickets, map, progress + 1))
//                return true;
//
//            res.remove(res.size() - 1);
//            tos.put(str, tos.get(str) + 1);
//        }
//        return false;
//    }
//}

class Solution2 {
    Deque<String> res = new LinkedList<>();
    Map<String, TreeMap<String, Integer>> map = new HashMap<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        // initialize map
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            map.putIfAbsent(from, new TreeMap<>());
            TreeMap<String, Integer> treemap = map.get(from);
            treemap.put(to, treemap.getOrDefault(to, 0) + 1);
        }
        // backtrack
        res.addLast("JFK");
        backtrack(tickets, 0);
        return new ArrayList<>(res);
    }

    public boolean backtrack(List<List<String>> tickets, int count) {
        if (count == tickets.size()) {
            return true;
        }
        String from = res.getLast();
        TreeMap<String, Integer> treemap = map.get(from);
        if (treemap == null || treemap.isEmpty()) return false;
        for (String to : treemap.keySet()) {
            if (treemap.get(to) == 0) {
                // 已经走过了，避免进入死循环
                continue;
            }
            // backtrack
            res.addLast(to);
            treemap.put(to, treemap.get(to) - 1);
            if (backtrack(tickets, count + 1)) return true;
            treemap.put(to, treemap.get(to) + 1);
            res.removeLast();
        }
        return false;
    }
}