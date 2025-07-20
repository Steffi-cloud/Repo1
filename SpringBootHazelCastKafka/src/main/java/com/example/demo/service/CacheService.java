package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
@Service
public class CacheService {
	private final IMap<String, String> myMap;

    public CacheService(HazelcastInstance hazelcastInstance) {
        this.myMap = hazelcastInstance.getMap("myCacheName");
    }

    public void put(String key, String value) {
        myMap.put(key, value);
    }

    public String get(String key) {
        return myMap.get(key);
    }

    public void clear() {
        myMap.clear();
    }

    public int size() {
        return myMap.size();
    }
}
