package com.work.webapp;

import com.work.webapp.model.Resume;
import com.work.webapp.storage.SortedArrayStorage;
import com.work.webapp.storage.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Test for com.urise.webapp.storage.com.work.webapp.storage.ArrayStorage
 */
public class MainArray {
    //    private final static Storage ARRAY_STORAGE = new ArrayStorage();
    private final static Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) throws IOException {
        /*final Resume R1 = new Resume(uuid);
        R1.setUuid("uuid1");
        final Resume R2 = new Resume(uuid);
        R2.setUuid("uuid2");
        final Resume R3 = new Resume(uuid);
        R3.setUuid("uuid3");

        System.out.println(R1 == R2);

        ARRAY_STORAGE.save(R1);
        ARRAY_STORAGE.save(R2);
        ARRAY_STORAGE.save(R3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(R1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));*/

//      System.out.println("Index of r3: "+ Arrays.binarySearch(ARRAY_STORAGE.storage, 0, ARRAY_STORAGE.size(), r3));

        /*printAll();
        ARRAY_STORAGE.delete(R1.getUuid());
        printAll();
        ARRAY_STORAGE.update(R3);
        printAll();
        ARRAY_STORAGE.clear();
        printAll();*/

        System.out.println("Size: " + ARRAY_STORAGE.size());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume r;
        while (true) {
            System.out.print("Введите одну из команд - (list | save uuid | delete uuid | get uuid | size | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 2) {
                System.out.println("Неверная команда.");
                continue;
            }
            String uuid = null;
            if (params.length == 2) {
                uuid = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(ARRAY_STORAGE.size());
                    break;
                case "save":
                    r = new Resume(uuid);
                    ARRAY_STORAGE.save(r);
                    printAll();
                    break;
                case "update":
                    r = new Resume(params[1]);
                    ARRAY_STORAGE.update(r);
                    printAll();
                    break;
                case "delete":
                    ARRAY_STORAGE.delete(uuid);
                    printAll();
                    break;
                case "get":
                    System.out.println(ARRAY_STORAGE.get(uuid));
                    break;
                case "clear":
                    ARRAY_STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    private static void printAll() {
        Resume[] all = ARRAY_STORAGE.getAll();
        System.out.println("----------------------------");
        if (all.length == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }
        System.out.println("----------------------------");
    }
}