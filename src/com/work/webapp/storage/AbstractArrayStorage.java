package com.work.webapp.storage;

import com.work.webapp.model.Resume;

import java.io.BufferedReader;
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

    public Resume get(String uuid) {
        if (isNullStorage())
            return null;
        int findUuid = find(uuid);
        if (findUuid != -1)
            return storage[findUuid];
        System.out.println("Резюме " + uuid + " нет!!!");
        return null;
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

    protected boolean isNullStorage() {
        if (size == 0) {
            System.out.println("Хранилище резюме пустое!!!");
            return true;
        }
        return false;
    }

     protected boolean resumeIsExist(String index){
        if (find(index) >= 0){
            System.out.println("Извините, резюме " + index + " уже есть!!!");
            return true;
        }
        return false;
    }

    protected boolean resumeIsNotExist(String index) {
        if (find(index) < 0){
            System.out.println("Резюме " + index + " нет!!!");
            return true;
        }
        return false;
    }

    protected abstract int find(String uuid);
}
