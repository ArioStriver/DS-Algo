/*
You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending 
order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval. Insert newInterval into intervals such that intervals is 
still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]

Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].


METHOD:

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        
        List<int[]> res = new ArrayList<>();
        int start = newInterval[0], end = newInterval[1];

        for(int i = 0; i < intervals.length; i++) {

            int curStart = intervals[i][0];
            int curEnd = intervals[i][1];

            // case 1: not overlapping intervals
            // it means the new interval will come before the current interval
            if(end < curStart) {
                res.add(new int[]{start, end});
                start = curStart;     // updating the stat and the end
                end = curEnd;
            }
            // case 1:
            // it means  the new interval will come after the current interval
            else if(start > curEnd) {
                res.add(new int[]{curStart, curEnd});
            }
            // case 2: Overlapping intervals
            else {
                start = Math.min(start, curStart);
                end = Math.max(end, curEnd);
            }
        }

        // adding the last interval
        res.add(new int[]{start, end});

        return res.toArray(new int[0][]);
    }
}