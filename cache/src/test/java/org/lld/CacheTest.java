package org.lld;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lld.factory.CacheFactory;

import static org.junit.jupiter.api.Assertions.*;

public class CacheTest {
    Cache<Integer, Integer> cache;

    @BeforeEach
    public void CacheTest() {
        this.cache = new CacheFactory<Integer, Integer>().getDefaultCache(3);
    }

    @Test
    public void testCacheAccess() {
        cache.put(1, 1);
        assertEquals(1, cache.get(1));

        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);
        assertNull(cache.get(1));
    }
}
