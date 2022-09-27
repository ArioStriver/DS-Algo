/*
You need to design a data structure which support below operation in O(1) time complexity 1) Insert() which adds an element to the data structure 2) remove() which removes an element from 
the data structure 3) findMiddle() which will return middle element 4) deleteMiddle() which will delete the middle element. 
Insert(1) --- O(1) time complexity 
Insert(2) Insert(3) 
findMiddle() – 2 in O(1) time complexity 
Insert(4) Insert(5) 
deleteMiddle() - remove 3 in O(1) time complexity 
remove(5) – in O(1) time complexit


METHOD:
	APPROACH:
		The important question is, whether to use a linked list or array for the implementation of the stack? 
		Please note that we need to find and delete the middle element. Deleting an element from the middle is not O(1) for the array. Also, we may need to move the middle pointer up 
		when we push an element and move down when we pop(). In a singly linked list, moving the middle pointer in both directions is not possible. 
		The idea is to use a Doubly Linked List (DLL). We can delete the middle element in O(1) time by maintaining mid pointer. We can move the mid pointer in both directions using 
		previous and next pointers. 
		Following is implementation of push(), pop() and findMiddle() operations. If there are even elements in stack, findMiddle() returns the second middle element. For example, if 
		stack contains {1, 2, 3, 4}, then findMiddle() would return 3. 

TIME: o(1).
*/

class DLLNode {
    DLLNode prev;
    DLLNode next;
    int data;
    
    DLLNode(int data) {
        this.data = data;
    }
}

class myStack {
    
    DLLNode head;
    DLLNode prev;
    DLLNode next;
    DLLNode mid;
    int size;
    
    void push(int val) {
        
        DLLNode newNode = new DLLNode(val);
        
        if(size == 0) {
            head = newNode;
            mid = newNode;
            size++;
            return;
        }
        
        head.next = newNode;
        newNode.prev = head;
        
        head = head.next;
        size++;
        
        // from odd to even size the middle element changes
        // from even to odd the middle element remains same
        if(size % 2 == 0) {
            mid = mid.next;
        }
    }
    
    int pop() {
        
        int val = -1;
        
        if(size == 0) {
            return -1;
        }
        
        if(size != 0) {
            if(size == 1) {
                head = null;
                mid = null;
            }
            else {
                val = head.data;
                head = head.prev;
                head.next = null;
                size--;
                
                // from even to odd the middle element changes
                if(size % 2 == 1) {
                    mid = mid.prev;
                }
            }
        }
        
        return val;
    }
    
    int findMiddle() {
        if(size == 0) 
            return -1;
            
        return mid.data;
    }
    
    void deleteMiddleElement() {
        
        if(size != 0) {
            if(size == 1) {
                head = null;
                mid = null;
            }
            else if(size == 2) {
                head = head.prev;
                head.next = null;
                mid = mid.prev;
            }
            else {
                mid.next.prev = mid.prev;
                mid.prev.next = mid.next;
                size--;
                
                // after the deleting the middle element
                // if the size is even, then move mid to next
                if(size % 2 == 0) {
                    mid = mid.next;
                }
                // otherwise move mid to previous as it is odd
                else {
                    mid = mid.prev;
                }
            }
        }    
    }
    
    public static void main(String[] args) {
        
        myStack ms = new myStack();
        ms.push(11);
        ms.push(22);
        ms.push(33);
        ms.push(44);
        ms.push(55);
        ms.push(66);
        ms.push(77);
        ms.push(88);
        ms.push(99);
 
        System.out.println("Popped : " + ms.pop()+"\n");
        System.out.println("Popped : " + ms.pop()+"\n");
        System.out.println("Middle Element : "+ ms.findMiddle()+"\n");
        ms.deleteMiddleElement();
        System.out.println("New Middle Element : "+ ms.findMiddle()+"\n");
    }
}