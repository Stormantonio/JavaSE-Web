package com.work.webapp.storage;

import com.work.webapp.model.Resume;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Администратор on 06.12.2016.
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void clear() {

    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public void update(Resume r) throws IOException {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    protected boolean isNullStorage() {
        return false;
    }

    @Override
    protected int find(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
