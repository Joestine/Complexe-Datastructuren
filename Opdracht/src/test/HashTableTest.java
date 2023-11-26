package test;

import main.structures.hashtable.HashTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {
    private HashTable<String, Integer> hashTable;

    @BeforeEach
    public void setUp() {
        hashTable = new HashTable<>();
    }

    @Test
    void testPutAndGet() {
        hashTable.put("key1", 1);
        hashTable.put("key2", 2);

        assertEquals(1, hashTable.get("key1"));
        assertEquals(2, hashTable.get("key2"));
        assertNull(hashTable.get("key3")); // key not present
    }

    @Test
    void testSizeAndIsEmpty() {
        assertTrue(hashTable.isEmpty());
        assertEquals(0, hashTable.size());

        hashTable.put("key1", 1);
        assertFalse(hashTable.isEmpty());
        assertEquals(1, hashTable.size());
    }

    @Test
    void testRemove() {
        hashTable.put("key1", 1);
        hashTable.put("key2", 2);

        assertEquals(2, hashTable.size());
        assertEquals(1, hashTable.remove("key1"));
        assertNull(hashTable.remove("key3")); // key not present
        assertEquals(1, hashTable.size());
    }

    @Test
    void testKeySet() {
        hashTable.put("key1", 1);
        hashTable.put("key2", 2);

        Set<String> keys = hashTable.keySet();
        assertTrue(keys.contains("key1"));
        assertTrue(keys.contains("key2"));
        assertEquals(2, keys.size());
    }

    @Test
    void testContains() {
        hashTable.put("key1", 1);

        assertTrue(hashTable.contains("key1"));
        assertFalse(hashTable.contains("key2")); // key not present
    }

    @Test
    void testGetOrDefault() {
        hashTable.put("key1", 1);

        assertEquals(1, hashTable.getOrDefault("key1", 0));
        assertEquals(0, hashTable.getOrDefault("key2", 0)); // key not present
    }

    @Test
    void testToString() {
        hashTable.put("key1", 1);
        hashTable.put("key2", 2);

        String expected = "HashTable [\n\tkey1: 1\n\tkey2: 2\n]";
        assertEquals(expected, hashTable.toString());
    }
}
