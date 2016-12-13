package com.work.webapp.storage;

import com.work.webapp.model.Resume;

import java.io.IOException;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        if (size == STORAGE_MAX_LENGTH) {
            System.out.println("Хранилище резюме переполнено!!!");
        } else if (!resumeIsExist(r.getUuid())) {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) throws IOException {
        if (!isNullStorage()) {
            Integer findIndex = find(r.getUuid());
            if (!resumeIsNotExist(r.getUuid())) {
                System.out.println("Введите изменения:");
                String newResume = reader.readLine();
                if (newResume.length() != 0) {
                    if (!resumeIsExist(newResume)) {
                        storage[findIndex].setUuid(newResume);
                        System.out.println("Резюме " + newResume + " успешно изменено!");
                    }
                } else
                    System.out.println("Неверная комманда!!!");
            }
        }
    }

    public void delete(String uuid) {
        if (!isNullStorage()) {
            Integer findUuid = find(uuid);
            if (!resumeIsNotExist(uuid)) {
                storage[findUuid] = storage[size - 1];
                storage[size - 1] = null;
                size--;
            }
        }
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
