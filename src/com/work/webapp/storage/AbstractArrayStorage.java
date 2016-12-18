package com.work.webapp.storage;

import com.work.webapp.exception.ExistStorageException;
import com.work.webapp.exception.NotExistStorageException;
import com.work.webapp.exception.StorageException;
import com.work.webapp.model.Resume;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by Администратор on 06.12.2016.
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final Integer STORAGE_MAX_LENGTH = 10000;

    protected Resume[] storage = new Resume[STORAGE_MAX_LENGTH];
    protected int size = 0;
    protected BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = find(r.getUuid());
        if (size == STORAGE_MAX_LENGTH) {
            throw new StorageException("Хранилище резюме переполнено!!!", r.getUuid());
        } else if (!resumeIsExist(r.getUuid())) {
            insertElement(r, index);
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
                        storage[findIndex] = new Resume(newResume);
                        System.out.println("Резюме " + newResume + " успешно изменено!");
                    }
                } else
                    System.out.println("Неверная комманда!!!");
            }
        }
    }

    protected abstract void insertElement(Resume r, int index);

    public Resume get(String uuid) {
        if (isNullStorage())
            return null;
        int findUuid = find(uuid);
        if (findUuid >= 0)
            return storage[findUuid];
        throw new NotExistStorageException(uuid);
    }

    public void delete(String uuid) {
        int index = find(uuid);
        if (!isNullStorage()) {
            if (!resumeIsNotExist(uuid)) {
                fillDeletedElement(index);
                storage[size - 1] = null;
                size--;
            }
        }
    }

    protected abstract void fillDeletedElement(int index);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected boolean isNullStorage() {
        if (size == 0) {
            System.out.println("Хранилище резюме пустое!!!");
            return true;
        }
        return false;
    }

    protected boolean resumeIsExist(String index) {
        if (find(index) >= 0) {
            throw new ExistStorageException(index);
        }
        return false;
    }

    protected boolean resumeIsNotExist(String index) {
        if (find(index) < 0) {
            throw new NotExistStorageException(index);
        }
        return false;
    }

    protected abstract int find(String uuid);
}
