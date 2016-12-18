package com.work.webapp.exception;

/**
 * Created by Anton on 18.12.2016.
 */
public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Резюме " + uuid + " нет!!!", uuid);
    }
}
