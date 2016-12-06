package com.work.webapp;

import com.work.webapp.model.Resume;
import com.work.webapp.storage.ArrayStorage;

/**
 * Test for com.urise.webapp.storage.com.work.webapp.storage.ArrayStorage
 */
public class MainTestArrayStorage {
    private final static ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        final Resume R1 = new Resume();
        R1.setUuid("uuid1");
        final Resume R2 = new Resume();
        R2.setUuid("uuid2");
        final Resume R3 = new Resume();
        R3.setUuid("uuid3");

        ARRAY_STORAGE.save(R1);
        ARRAY_STORAGE.save(R2);
        ARRAY_STORAGE.save(R3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(R1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(R1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
