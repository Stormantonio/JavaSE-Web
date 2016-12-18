package com.work.webapp.exception;

/**
 * Created by Anton on 18.12.2016.
 */
public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Извините, резюме " + uuid + " уже есть!!!", uuid);
    }
}
