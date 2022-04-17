/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Example 1:

Input: nums = [3,2,3]
Output: [3]

Example 2:

Input: nums = [1]
Output: [1]

Example 3:

Input: nums = [1,2]
Output: [1,2]

METHOD 1:(USING HASMAP)

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        
        int n = nums.length;
        List<Integer> res= new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap();
        
        for(int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        for(Map.Entry<Integer, Integer> e : freq.entrySet()) {
            if(e.getValue() > (n / 3)) {
                res.add(e.getKey());
            }
        }
        
        return res;
    }
}

/*
METHOD 2:(USING BOOYER MOORE'S ALGORITHM)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
            
        int count1 = 0, count2 = 0;
        int candidate1 = -1, candidate2 = -1;
        
        // there can be 0, 1 or at most 2 majority elements in the given array 
        for(int ele : nums) {
            
            // current element equal to candidate1
            if(ele == candidate1) {
                count1++;
            }
            // equal to candidate2
            else if(ele == candidate2) {
                count2++;
            }
            // selecting the candidate1
            else if(count1 == 0) {
                candidate1 = ele;
                count1 = 1;
            }
            // selecting the candidate1
            else if(count2 == 0) {
                candidate2 = ele;
                count2 = 1;
            }
            // creating a pair
            else {
                count1--;
                count2--;
            }
        }
        
        /* but after getting the majority element it doesn't assure us that the element
           we got, occurs more than (n/3) times. So we have to further check fro their
           frequency */
        count1 = 0; count2 = 0;
        
        for(int num : nums) {
            if(candidate1 == num) count1++;
            
            else if(candidate2 == num) count2++;
        }
        
        if(count1 > (n/3)) res.add(candidate1);
        if(count2 > (n/3)) res.add(candidate2);
        
        return res;
    }
}