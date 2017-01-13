package com.work.webapp.storage;

import com.work.webapp.exception.ExistStorageException;
import com.work.webapp.exception.NotExistStorageException;
import com.work.webapp.model.Resume;

import java.util.*;

/**
 * Created by Anton on 05.01.2017.
 */
public abstract class AbstractStorage implements Storage {
    protected abstract boolean containsResume(Resume r);

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
