/*
You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, 
which results in repetition of one number and loss of another number.

You are given an integer array nums representing the data status of this set after the error.

Find the number that occurs twice and the number that is missing and return them in the form of an array.

Example 1:

Input: nums = [1,2,2,4]
Output: [2,3]

Example 2:

Input: nums = [1,1]
Output: [1,2]


METHOD 1:(Using Sorting)

TIME: O(NlogN).

SPACE: O(logN).
*/

public class Solution {
    public int[] findErrorNums(int[] nums) {

        Arrays.sort(nums);

        int dup = -1, missing = 1;

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] == nums[i - 1])
                dup = nums[i];

            else if (nums[i] > nums[i - 1] + 1)
                missing = nums[i - 1] + 1;
        }

        return new int[] {dup, nums[nums.length - 1] != nums.length ? nums.length : missing};
    }
}

/*
METHOD 2:(Using Extra Array)

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public int[] findErrorNums(int[] nums) {
        
        int[] freq = new int[nums.length+1];
        int dup = -1, miss = -1;
        
        for(int i = 0; i < nums.length; i++) {
            freq[nums[i]]++;
        }
        
        for(int i = 1; i <= nums.length; i++) {
            if(freq[i] == 0) {
                miss = i;
            }
            
            if(freq[i] == 2) {
                dup = i;
            }
        }
        
        return new int[]{dup, miss};
    }
}

/*
METHOD 3:(USING XOR METHOD)

TIME: O(5N) == O(N).

SPACE: O(1).
*/

class Solution {
    public int[] findErrorNums(int[] nums) {
        
        int XOR = 0;
        
        // 1. first find the XOR all elemets of the given array
        for(int num : nums) {
            XOR ^= num;
        }
        
        // 2. Then find the XOR of (1 - n) with the previous XOR result
        for(int i = 1; i <= nums.length; i++) {
            XOR ^= i;
        }
        
        // 3. find the right most set bit position
        int rightmostbit = (XOR & ~(XOR-1));
        
        int a = 0, b = 0;
        
        // 4. Seperate the given the array into two buckets depending on the right most set bit
        for(int i = 0; i < nums.length; i++) {
            if((nums[i] & rightmostbit) >= 1) {
                a ^= nums[i];
            }
            else {
                b ^= nums[i];
            }
        }
        
        // 5. Again seperate the (1 - n) elements into two buckets depending on the right most set bit
        for(int i = 1; i <= nums.length; i++) {
            if((i & rightmostbit) >= 1) {
                a ^= i;
            }
            else {
                b ^= i;
            }
        }
        
        // 6. Do a linear search to find the repeating & the missing elements
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == a) {
                return new int[]{a, b};
            }
        }
        
        return new int[]{b, a};
    }
} 