package com.work.webapp.storage;

import com.work.webapp.exception.ExistStorageException;
import com.work.webapp.exception.NotExistStorageException;
import com.work.webapp.model.Resume;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Anton on 05.01.2017.
 */

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> resumes = new HashMap<>();

    @Override
    public void clear() {
        resumes.clear();
    }

    @Override
    public void save(Resume r) {
        if (!resumeIsExist(r))
            resumes.put(r.getUuid(), r);
    }

    @Override
    public void update(Resume r) {
        if (!isNullStorage())
            if (!resumeIsNotExist(r))
                resumes.put(r.getUuid(), r);
    }

    @Override
    public Resume get(String uuid) {
        if (resumes.containsKey(uuid))
            return resumes.get(uuid);
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        if (!isNullStorage())
            resumes.remove(uuid);
    }

    @Override
    public Resume[] getAll() {
        Resume[] temp = new Resume[size()];
        temp = resumes.values().toArray(temp);
        return temp;
    }

    @Override
    public int size() {
        return resumes.size();
    }

    @Override
    protected boolean containsResume(Resume r) {
        return resumes.containsValue(r);
    }

    protected boolean isNullStorage() {
        if (resumes.isEmpty()) {
            System.out.println("Хранилище резюме пустое!!!");
            return true;
        }
        return false;
    }
}
