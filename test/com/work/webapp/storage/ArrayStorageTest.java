package com.work.webapp.storage;

import com.work.webapp.exception.ExistStorageException;
import com.work.webapp.exception.StorageException;
import com.work.webapp.model.Resume;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Anton on 20.12.2016.
 */
public class ArrayStorageTest extends AbstractArrayStorageTest {

    @Test (expected = StorageException.class)
    public void arrayStorageTest() throws Exception {
        Storage storage = new ArrayStorage();
        setStorage(storage);
        super.clear();
        super.save();
        super.update();
        super.get();
        super.delete();
        super.getAll();
        super.size();
        super.getNotExist();
    }
}
