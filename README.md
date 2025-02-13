# LRU-Cache
Explanation:
Data Structures Used:

HashMap: Maps keys to nodes in the doubly linked list. This enables 
𝑂(1)
O(1) lookup for the get operation.
Doubly Linked List: Maintains the order of usage. The most recently used node is placed right after the dummy head, and the least recently used node is just before the dummy tail.
Operations:

get(key): If the key exists, move the node to the head of the list (to mark it as recently used) and return its value; otherwise, return -1.
put(key, value): If the key exists, update its value and move it to the head. If the key does not exist, insert it at the head. If the cache is at capacity, remove the node at the tail (least recently used) before insertion.
Time Complexity:

Both get and put operations are 
𝑂(1)
O(1) due to combining the HashMap and the doubly linked list.
