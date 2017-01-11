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
    protected List<Resume> resumes = new ArrayList<>();
    protected BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
    public void update(Resume r) throws IOException {
        if (!isNullStorage()) {
            if (!resumeIsNotExist(r)) {
                System.out.println("Введите изменения:");
                String newResume = reader.readLine();
                if (newResume.length() != 0) {
                    Resume resume = new Resume(newResume);
                     if (!resumeIsExist(resume)){
                         resumes.remove(r);
                         resumes.add(resume);
                         System.out.println("Резюме " + newResume + " успешно изменено!");
                     }
                }
            }
        }
    }

    @Override
    public Resume get(String uuid) {
        Resume temp = new Resume(uuid);
        if (containsResume(temp))
            return temp;
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
        int count = 0;
        Resume[] temp = new Resume[resumes.size()];
        for (Resume r : resumes) {
            temp[count] = r;
            count++;
        }
        return temp;
    }

    @Override
    public int size() {
        return resumes.size();
    }

    protected boolean isNullStorage() {
        if (resumes.isEmpty()) {
            System.out.println("Хранилище резюме пустое!!!");
            return true;
        }
        return false;
    }

    protected boolean containsResume(Resume r) {
        return resumes.contains(r);
    }

    protected boolean resumeIsExist(Resume r) {
        if (containsResume(r)) {
            throw new ExistStorageException(r.getUuid());
        }
        return false;
    }

    protected boolean resumeIsNotExist(Resume r) {
        if (!containsResume(r)) {
            throw new NotExistStorageException(r.getUuid());
        }
        return false;
    }
}
