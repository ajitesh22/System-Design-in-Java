class MyHashMap {

    LinkedList<Node>[] bucket;
    final int SIZE = 1009;
    
    class Node {
        int key;
        int val;
        public Node(int key ,int val){
            this.key = key;
            this.val = val;
        }
    }
    
    public MyHashMap() {
        bucket = new LinkedList[SIZE];
        for(int i=0;i<bucket.length;i++){
            bucket[i] = new LinkedList<Node>();
        }
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = getHash(key);
        LinkedList<Node> NodeList = bucket[index];
        if(get(key)!=-1)
            remove(key);
        Node node = new Node(key , value);
        NodeList.add(node);
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
     public int get(int key) {
        int index = getHash(key);
         LinkedList<Node> NodeList = bucket[index];
         Iterator<Node> it = NodeList.iterator();
        while(it.hasNext()){
            Node node = it.next();
            if(node.key==key)
                return node.val;
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = getHash(key);
        LinkedList<Node> NodeList = bucket[index];
        Iterator<Node> it = NodeList.iterator();
        while(it.hasNext()){
            Node node = it.next();
            if(node.key==key)
                it.remove();
        }
    }
    
    public int getHash(int key) {
        return key%SIZE;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
