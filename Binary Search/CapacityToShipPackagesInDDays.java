/*
A conveyor belt has packages that must be shipped from one port to another within days days.

The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight 
than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.

Example 1:

Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15

Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.

Example 2:

Input: weights = [3,2,2,4,1,4], days = 3
Output: 6

Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4

Example 3:

Input: weights = [1,2,3,1,1], days = 4
Output: 3

Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1


METHOD:(USING BINARY SEARCH)

TIME: O(Nlog(M)), where M is the max Range.

SPACE: O(1).
*/

class Solution {

    private boolean canShip(int[] weights, int maxCapacity, int maxDays) {

        int daysNeeded = 1, currCap = maxCapacity;
        for(int weight : weights) {
            currCap -= weight;

            // if at any point the current capacity becomes < 0, then we need to ship the rest of the packages
            // on another day if have any
            if(currCap < 0) {
                currCap = maxCapacity;    // resetting the current capacity
                currCap -= weight;
                daysNeeded++;
            }
        }

        return daysNeeded <= maxDays;
    }

    public int shipWithinDays(int[] weights, int days) {
        
        int low = 0, high = 0;
        for(int weight : weights) {
            low = Math.max(low, weight);  // bcz minimum that much capacity we needed to ship packages
            high += weight;          // maximum needed is sum of all wights, not more than that
        }

        int minCapacity = 0;

        while(low <= high) {
            int curCapacity = (low + high) / 2;
            System.out.println(high);

            // checking whether we able to ship all the packages or not with the current capacity within given days
            // if so then try to minimize the capacity as much as possible to get the min capacity we needed
            if(canShip(weights, curCapacity, days)) {
                minCapacity = curCapacity;
                high = curCapacity - 1;
            }
            // increase the current capacity to ship more packages with d days
            else {
                low = curCapacity + 1;
            }
        }

        return minCapacity;
    }
}