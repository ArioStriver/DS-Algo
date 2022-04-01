/*
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all 
the intervals in the input.

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]

Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]

Explanation: Intervals [1,4] and [4,5] are considered overlapping.


METHOD:(GREEDY)

TIME: O(NlogN).

SPACE: O(1).
*/

class Solution {
    public int[][] merge(int[][] intervals) {
        
        List<int[]> res = new ArrayList<>();
        
        if(intervals == null || intervals.length == 0){
            return res.toArray(new int[0][]);
        }
        
        // Sort by ascending starting point
        Arrays.sort(intervals, (a, b)->(a[0] - b[0]));
        
        int start = intervals[0][0];
        int end = intervals[0][1];
        
        for(int i = 1; i < intervals.length; i++){
            
            int curStart = intervals[i][0];
            int curEnd = intervals[i][1];
            
            // overlap
            if(curStart <= end){
                end = Math.max(end, curEnd);
            }
            // not overlap
            // if not add it to the result list and make the current start & end 
            // as the new start & end of the next interval
            else{
                res.add(new int[]{start, end});
                start = curStart;
                end = curEnd;
            }
        }
        
        res.add(new int[]{start, end});
        
        return res.toArray(new int[0][]);
    }
}