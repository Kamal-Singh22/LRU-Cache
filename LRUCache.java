import java.util.HashMap;

class LRUCache {
    // Doubly Linked List Node class
    class Node {
        int key, value;
        Node prev, next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private int capacity;
    private HashMap<Integer, Node> map;
    private Node head, tail;  // Dummy head and tail for ease of use

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        
        // Initialize dummy head and tail nodes.
        head = new Node(0, 0);
        tail = new Node(0, 0);
        
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            // Move the accessed node to the head (most recently used)
            Node node = map.get(key);
            remove(node);
            addToHead(node);
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // Update the value and move node to head
            Node node = map.get(key);
            node.value = value;
            remove(node);
            addToHead(node);
        } else {
            if (map.size() == capacity) {
                // Remove the least recently used item (tail.prev)
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }
            // Insert the new node and add it to the head
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }
    
    // Helper method to remove a node from the doubly linked list
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    // Helper method to add a node right after the dummy head
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        
        head.next.prev = node;
        head.next = node;
    }
    
    // For testing the LRUCache
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);  // capacity = 2
        
        cache.put(1, 1);    // cache is {1=1}
        cache.put(2, 2);    // cache is {1=1, 2=2}
        System.out.println(cache.get(1)); // returns 1 and moves key 1 to the front
        
        cache.put(3, 3);    // evicts key 2, cache is {1=1, 3=3}
        System.out.println(cache.get(2)); // returns -1 (not found)
        
        cache.put(4, 4);    // evicts key 1, cache is {4=4, 3=3}
        System.out.println(cache.get(1)); // returns -1 (not found)
        System.out.println(cache.get(3)); // returns 3
        System.out.println(cache.get(4)); // returns 4
    }
}
