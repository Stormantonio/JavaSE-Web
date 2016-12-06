package com.work.webapp.storage;

import com.work.webapp.model.Resume;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Администратор on 06.12.2016.
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final Integer STORAGE_MAX_LENGTH = 10000;

    protected Resume[] storage = new Resume[STORAGE_MAX_LENGTH];
    protected int size = 0;
    protected BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public int size() {
        return size;
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

    protected abstract boolean isNullStorage();

    protected abstract int find(String uuid);
}