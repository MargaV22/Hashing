import java.util.LinkedList;

class HashNode<K, V> {
    K key;
    V value;

    HashNode<K, V> next;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

class HashTable<K, V> {
    private LinkedList<HashNode<K, V>>[] table;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public void put(K key, V value) {
        int bucketIndex = hash(key);
        LinkedList<HashNode<K, V>> bucket = table[bucketIndex];

        for (HashNode<K, V> node : bucket) {
            if (node.key.equals(key)) {
                node.value = value; 
                return;
            }
        }

        bucket.add(new HashNode<>(key, value));
        size++;
    }

    public V get(K key) {
        int bucketIndex = hash(key);
        LinkedList<HashNode<K, V>> bucket = table[bucketIndex];

        for (HashNode<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }

        return null;
    }

    public void remove(K key) {
        int bucketIndex = hash(key);
        LinkedList<HashNode<K, V>> bucket = table[bucketIndex];

        HashNode<K, V> toRemove = null;
        for (HashNode<K, V> node : bucket) {
            if (node.key.equals(key)) {
                toRemove = node;
                break;
            }
        }

        if (toRemove != null) {
            bucket.remove(toRemove);
            size--;
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printTable() {
        for (int i = 0; i < capacity; i++) {
            System.out.print("Bucket " + i + ": ");
            for (HashNode<K, V> node : table[i]) {
                System.out.print("[" + node.key + ": " + node.value + "] -> ");
            }
            System.out.println("null");
        }
    }
}

public class SimpleHashTable {
    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>(10);

        System.out.println("Simple Hash Table Program");

        hashTable.put("Alice", 30);
        hashTable.put("Bob", 25);
        hashTable.put("Charlie", 35);

        System.out.println("\nInitial Hash Table:");
        hashTable.printTable();

        System.out.println("\nValue for 'Alice': " + hashTable.get("Alice"));

        System.out.println("\nUpdating value for 'Bob'...");
        hashTable.put("Bob", 40);
        hashTable.printTable();

        System.out.println("\nRemoving 'Charlie'...");
        hashTable.remove("Charlie");
        hashTable.printTable();

        System.out.println("\nIs Hash Table empty? " + hashTable.isEmpty());
        System.out.println("Current Size: " + hashTable.getSize());
    }
}
