/*
The cost of a stock on each day is given in an array. Find the maximum profit that you can make by buying and selling on those days. If the given array of prices is sorted in decreasing order, then profit cannot be earned at all.

Examples:

Input: arr[] = {100, 180, 260, 310, 40, 535, 695}
Output: 865

Explanation: Buy the stock on day 0 and sell it on day 3 => 310 – 100 = 210
             Buy the stock on day 4 and sell it on day 6 => 695 – 40 = 655
             Maximum Profit  = 210 + 655 = 865

Input: arr[] = {4, 2, 2, 2, 4}
Output: 2

Explanation: Buy the stock on day 1 and sell it on day 4 => 4 – 2 = 2
             Maximum Profit  = 2


METHOD:(Maximize Profit using Local Maximum and Local Minimum)
	APPROACH:
		Follow the steps below to solve the problem:  

			1.) Find the local minima and store it as starting index. If it does not exists, return.
			2.) Find the local maxima. And store it as an ending index. If we reach the end, set the end as the ending index.
			3.) Update the solution (Increment count of buy-sell pairs)
			4.) Repeat the above steps if the end is not reached.
TIME: O(N).

SPACE: O(1).
*/

class Solution{
    ArrayList<ArrayList<Integer> > stockBuySell(int price[], int N) {
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        
        int i = 0;
        
        while(i < N-1) {
            
            ArrayList<Integer> temp = new ArrayList<>();
            
            // first find the local minima means the day when we buy the stock
            while(i + 1 < N && price[i+1] <= price[i]) {
                i++;
            }
            
            // when the given prices are in decreasing order
            // If we reached the end, break as no further solution possible
            if(i == N-1) {
                break;
            }
            
            temp.add(i);      // storing the buying index
            i++;     
            
            // now finding the selling day with max profit
            while(i+1 < N && price[i] <= price[i+1]) {
                i++;
            }
            
            temp.add(i);    // storing the selling index
            i++;
            
            res.add(temp);
        }
        
        return res;
    }
}


/*
FINDING THE MAXIMUM PROFIT. PREVIOUSLY WE ARE FINDING THE INDEXES.

METHOD:(Maximize Profit using Valley Peak Approach)
	APPROACH:
		In this approach, we just need to find the next greater element and subtract it from the current element so that the difference keeps increasing until we reach a minimum. If 
		the sequence is a decreasing sequence, so the maximum profit possible is 0.

		Follow the steps below to solve the problem:

			maxProfit = 0
			if price[i] > price[i – 1]
			maxProfit = maxProfit + price[i] – price[i – 1]
TIME: O(N).

SPACE: O(1).
*/

class Solution {
 
    static int maxProfit(int prices[], int size)
    {
 
        // maxProfit adds up the difference between
        // adjacent elements if they are in increasing order
        int maxProfit = 0;
 
        // The loop starts from 1 as its comparing with the previous
        for (int i = 1; i < size; i++)
            if (prices[i] > prices[i - 1])
                maxProfit += prices[i] - prices[i - 1];

        return maxProfit;
    }
}