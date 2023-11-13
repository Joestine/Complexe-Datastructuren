package main.structures.hashtable;

public class HashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private final Node<K, V>[] buckets;
    private int size;

    public HashTable() {
        buckets = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return hashCode % buckets.length;
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> head = buckets[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        head = buckets[bucketIndex];
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        buckets[bucketIndex] = newNode;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> head = buckets[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }

        return null;
    }

    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> head = buckets[bucketIndex];
        Node<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        size--;

        if (prev != null) {
            prev.next = head.next;
        } else {
            buckets[bucketIndex] = head.next;
        }
        return head.value;
    }
}
