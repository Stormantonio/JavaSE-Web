package com.work.webapp.storage;

import com.work.webapp.model.Resume;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final Integer STORAGE_MAX_LENGTH = 10000;
    private Resume[] storage = new Resume[STORAGE_MAX_LENGTH];
    private int size = 0;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (isFull()) {
            System.out.println("Хранилище резюме переполнено!!!");
            return;
        }
        if (find(r.getUuid()) != -1) {
            System.out.println("Извините, резюме " + r + " уже есть!!!");
            return;
        }
        storage[size] = r;
        size++;
    }

    public void update(Resume r) throws IOException {
        if (isNull())
            return;
        int findIndex = find(r.getUuid());
        if (find(r.getUuid()) != -1) {
            storage[findIndex] = r;
            System.out.println("Введите изменения:");
            String newResume = reader.readLine();
            if (find(newResume) == -1) {
                storage[findIndex].setUuid(newResume);
                System.out.println("Резюме " + newResume + " успешно изменено!");
            } else {
                System.out.println("Извините, резюме " + newResume + " уже есть!!!");
            }
        } else
            System.out.println("Резюме " + r + " нет!!!");
    }

    public Resume get(String uuid) {
        if (isNull())
            return null;
        int findUuid = find(uuid);
        if (findUuid != -1)
            return storage[findUuid];
        System.out.println("Резюме " + uuid + " нет!!!");
        return null;
    }

    public void delete(String uuid) {
        if (isNull())
            return;
        int findUuid = find(uuid);
        if (findUuid != -1) {
            storage[findUuid] = storage[size - 1];
            storage[size -1] = null;
            size--;
            return;
        }
        System.out.println("Резюме " + uuid + " нет!!!");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private boolean isFull() {
        return size == STORAGE_MAX_LENGTH;
    }

    private boolean isNull() {
        if (size == 0) {
            System.out.println("Хранилище резюме пустое!!!");
            return true;
        }
        return false;
    }

    private int find(String currentUuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(currentUuid)) {
                return i;
            }
        }
        return -1;
    }
}
