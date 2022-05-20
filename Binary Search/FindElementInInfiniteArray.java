/*  (AMAZON)
You are given a sorted and infinite array A[] and an element K. You need to search for the element K in the array. If found, return the index of the element, else return -1.

For Example :

Input: A[] = {1,3,5,8,12,13,17,19,28,39,103,123,140,2040,…}, K = 17

Output: 6

Input: A[] = {10,20,25,30,67,93,159,192,350,1230,1341,4533,…}, K = 23

Output: -1


METHOD 1:(UISNG LINEAR SEARCH)

TIME: O(i), where i be the position of the element to be searched.

SPACE: O(1).
*/

int search_infiniteArray(int A[], int K)
{
    int i = 0
    while (A[i] <= K)
    {
        if(A[i] == K)
            return i
        else
            i = i + 1
    }
   return -1
}

/*
METHOD 2:(using binary search)
	APPROACH:
		If we can track the interval (with the lower and upper bound) where target value reside then we can apply the binary search in that interval. We increase the interval size by 
		an exponential order of 2. We call this approach exponential search which helps us to track the upper bound quickly in comparison to the previous approach.
		
		Let low be pointing to 1st element and high pointing to 2nd element of array, Now compare key with high index element, 
			->if it is greater than high index element then copy high index in low index and double the high index. 
			->if it is smaller, then apply binary search on high and low indices found.

TIME: finding uppar index 'r' of the interval + Binary Search from l to r = O(log i) + O(log i ) = O(log i)
*/

class Solution
{
    static int binarySearch(int arr[], int l, int r, int x)
    {
        if (r>=l)
        {
            int mid = l + (r - l)/2;
            if (arr[mid] == x)
                return mid;

            if (arr[mid] > x)
                return binarySearch(arr, l, mid-1, x);

            return binarySearch(arr, mid+1, r, x);
        }
        return -1;
    }
     
    // NOTE THAT THIS FUNCTION ASSUMES arr[] TO BE OF INFINITE SIZE
    // THEREFORE, THERE IS NO INDEX OUT OF BOUND CHECKING
    static int findPos(int arr[],int key)
    {
        int l = 0, h = 1;
        int val = arr[0];
 
        // Find h to do binary search
        while (val < key)
        {
            l = h;     // store previous high

            //check that 2*h doesn't exceeds array
            //length to prevent ArrayOutOfBoundException
            if(2*h < arr.length-1)
               h = 2*h;            
            else
               h = arr.length-1;
             
            val = arr[h]; // update new val
        }
 
        // at this point we have updated low and high indices, thus use binary search between them
        return binarySearch(arr, l, h, key);
    }
 }