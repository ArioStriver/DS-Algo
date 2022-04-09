/*
(AMAZON, PAYTM, VMWARE,MICROSOFT, INTUIT)

Given an unsorted array Arr of N positive and negative numbers. Your task is to create an array of alternate positive and negative numbers without changing the 
relative order of positive and negative numbers.
Note: Array should start with positive number.
 
Example 1:

Input: 
N = 9

Arr[] = {9, 4, -2, -1, 5, 0, -5, -3, 2}

Output:
9 -2 4 -1 5 -5 0 -3 2

Example 2:

Input: 
N = 10

Arr[] = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8}

Output:
5 -5 2 -2 4 -8 7 1 8 0 


METHOD 1:(USING EXTRA SPACE)

TIME: O(N), where N is the no. of elements

SPACE: O(N), at total.
*/

class Solution {
    void rearrange(int arr[], int n) {
        
        // here we are taking two list to store the positive and negative elements
        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();
        
        // pushing the postive and negative elements into the separate lists
        for(int num : arr){
            if(num < 0){
                negative.add(num);
            }
            else{
                positive.add(num);
            }
        }
        
        int k = 0, i = 0, j = 0;
        
	// adding the elements in the final array one by one 
        while(i < positive.size() || j < negative.size()) {
            
            // even position
            // first putting the postive elements in the array
            if(i < positive.size()) {
                arr[k] = positive.get(i);
                i++;
                k++;
            }
            
            // odd position
            // then put the negative elements in the array
            if(j < negative.size()) {
                arr[k] = negative.get(j);
                j++;
                k++;
            }
        }
    }
}

/*
METHOD 2:(WITHOUT USING EXTRA SPACE)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    
    static void rightRotate(int[] a, int from, int to){
        
        int temp = a[to];
        
        // shifting the elements towards right
        for(int i = to; i > from; i--){
            a[i] = a[i-1];
        }
        a[from] = temp;
    }
    
    void rearrange(int arr[], int n) {
        
	// for marking the wrong index
        int worngIndex = -1;
        
        for(int i = 0; i < n; i++){
            
            // there is a wrong index
            if(worngIndex != -1){
                
                /* now we need to find the element by which we can replace the value 
                   at the worngIndex, so if the value at the worngIndex is -ve then
                   we need to replace it with +ve and vice-versa. */
                if((arr[worngIndex] >= 0 && arr[i] < 0) || (arr[worngIndex] < 0 && arr[i] >= 0)){
                    
                    /* then we have right rotate the values means basically we have to shift 
                       the values towards right, why we are doing that in order to miantain their
                       relative order*/
                    rightRotate(arr, worngIndex, i);
                
                    /* now if the difference between the worngIndex & current i >= 2, it means there are 
                       more than one elemenet which are in wrong position, so we have find them */
                    if(i - worngIndex >= 2){
                          worngIndex += 2;
                    }
                    else{
                          worngIndex = -1;
                    }
                }
            }
            else{
                /* condition is : even index doesn't carry -ve element
                                  odd index doesn't carry +ve element
                   if so then it is a wrongIndex */
                if((i % 2 == 0 && arr[i] < 0) || (i % 2 == 1 && arr[i] >= 0)){
                    worngIndex = i;
                }
            }
        }
    }
}