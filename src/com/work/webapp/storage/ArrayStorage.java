package com.work.webapp.storage;

import com.work.webapp.model.Resume;

import java.io.IOException;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

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
        if (isNullStorage())
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


    public void delete(String uuid) {
        if (isNullStorage())
            return;
        int findUuid = find(uuid);
        if (findUuid != -1) {
            storage[findUuid] = storage[size - 1];
            storage[size - 1] = null;
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

    private boolean isFull() {
        return size == STORAGE_MAX_LENGTH;
    }

    protected boolean isNullStorage() {
        if (size == 0) {
            System.out.println("Хранилище резюме пустое!!!");
            return true;
        }
        return false;
    }

    protected int find(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
