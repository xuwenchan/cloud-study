package com.http.es.service.response;

public abstract class Aggregate {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Aggregate() {

    }

    public Aggregate(String name) {
        this.name = name;
    }

    public static class Pair<K, V> {
        public K key;
        public V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
