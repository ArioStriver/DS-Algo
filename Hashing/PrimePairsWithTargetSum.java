/*
You are given an integer n. We say that two integers x and y form a prime number pair if:

1 <= x <= y <= n
x + y == n
x and y are prime numbers
Return the 2D sorted list of prime number pairs [xi, yi]. The list should be sorted in increasing order of xi. If there are no prime number pairs at all, return an empty array.

Note: A prime number is a natural number greater than 1 with only two factors, itself and 1.

Example 1:

Input: n = 10
Output: [[3,7],[5,5]]
Explanation: In this example, there are two prime pairs that satisfy the criteria. These pairs are [3,7] and [5,5], and we return them in the sorted order as described in the problem statement.

Example 2:

Input: n = 2
Output: []
Explanation: We can show that there is no prime number pair that gives a sum of 2, so we return an empty array. 


METHOD: (TWO SUM + Sieve of Eratosthenes ALGORITHM)

TIME: O(Nlog(logN)), Using Eratosthenes to find the primes number in a range with low time complexity O(Nlog(logN)).

SPACE: O(N).
*/

class Solution {

    // Sieve of Eratosthenes Algorithm - Fatetst algorithm to find the prime numbers
    private void getPrimes(int n, boolean[] prime) {
	
	// initialize all entries it as true
        Arrays.fill(prime, true);

        for(int i = 2; i*i <= n; i++) {
            if(prime[i] == true) {
                for(int p = i*i; p <= n; p+=i) {
                    prime[p] = false;
                }
            }
        }
    }

    public List<List<Integer>> findPrimePairs(int n) {
        boolean[] primes = new boolean[n+1];
        getPrimes(n ,primes);   // getting the list of prime numbers from (2 till n)

        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 2; i <= n / 2; i++) {
            int j = n - i;

            // primes[i] == true, means it is a prime number
            if(primes[i] && primes[j]) {
                List<Integer> temp = new ArrayList<>();
                temp.add(i);
                temp.add(j);
                ans.add(temp);
            }
        }

        return ans;
    }
}