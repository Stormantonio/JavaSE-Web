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
    Map<String, Resume> resumes = new HashMap<>();

    protected BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int count = 0;

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
    public void update(Resume r) throws IOException {
        if (!isNullStorage()) {
            if (!resumeIsNotExist(r)) {
                System.out.println("Введите изменения:");
                String newResume = reader.readLine();
                if (newResume.length() != 0) {
                    Resume temp = new Resume(newResume);
                    if (!resumeIsExist(temp)) {
                        resumes.remove(r.getUuid(), r);
                        resumes.put(temp.getUuid(), temp);
                        System.out.println("Резюме " + newResume + " успешно изменено!");
                    }
                }
            }
        }
    }

    @Override
    public Resume get(String uuid) {
        for (Map.Entry<String, Resume> entry : resumes.entrySet()) {
            if (Objects.equals(entry.getKey(), uuid))
                return resumes.get(uuid);
        }
        // Задать вопрос - правильно ли это???
        /*Resume temp = new Resume(uuid);
        if (containsResume(temp)){
            resumes.get(uuid);
            return temp;
            }*/
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        if (!isNullStorage()) {
            Resume temp = new Resume(uuid);
            if (!resumeIsNotExist(temp))
                resumes.remove(temp.getUuid(), temp);
        }
    }

    @Override
    public Resume[] getAll() {
        int count = 0;
        Resume[] temp = new Resume[resumes.size()];
        for (Map.Entry<String, Resume> entry : resumes.entrySet()) {
            temp[count] = entry.getValue();
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
        return resumes.containsValue(r);
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
