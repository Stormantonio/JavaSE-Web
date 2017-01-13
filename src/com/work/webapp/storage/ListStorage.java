package com.work.webapp.storage;

import com.work.webapp.exception.ExistStorageException;
import com.work.webapp.exception.NotExistStorageException;
import com.work.webapp.model.Resume;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 05.01.2017.
 */
public class ListStorage extends AbstractStorage {
    private List<Resume> resumes = new ArrayList<>();

    @Override
    public void clear() {
        resumes.clear();
    }

    @Override
    public void save(Resume r) {
        if (!resumeIsExist(r))
            resumes.add(r);
    }

    @Override
    public void update(Resume r) {
        if (!isNullStorage())
            if (!resumeIsNotExist(r)) {
                resumes.remove(r);
                resumes.add(r);
            }
    }

    @Override
    public Resume get(String uuid) {
        Resume r = new Resume(uuid);
        if (containsResume(r))
            return resumes.get(resumes.indexOf(r));
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        if (!isNullStorage()) {
            Resume resume = new Resume(uuid);
            if (!resumeIsNotExist(resume))
                resumes.remove(resume);
        }
    }

    @Override
    public Resume[] getAll() {
        Resume[] temp = new Resume[size()];
        temp = resumes.toArray(temp);
        return temp;
    }

    @Override
    public int size() {
        return resumes.size();
    }

    @Override
    protected boolean containsResume(Resume r) {
        return resumes.contains(r);
    }

    protected boolean isNullStorage() {
        if (resumes.isEmpty()) {
            System.out.println("Хранилище резюме пустое!!!");
            return true;
        }
        return false;
    }
}
