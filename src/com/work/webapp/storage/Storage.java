package com.work.webapp.storage;

import com.work.webapp.model.Resume;

import java.io.IOException;

/**
 * Created by Администратор on 06.12.2016.
 */
public interface Storage {

    void clear();

    void update(Resume r) throws IOException;

    Resume get(String uuid);

    void delete(String uuid);

    Resume[] getAll();

    int size();
}
