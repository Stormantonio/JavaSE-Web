package com.work.webapp.storage;

import com.work.webapp.exception.ExistStorageException;
import com.work.webapp.exception.NotExistStorageException;
import com.work.webapp.exception.StorageException;
import com.work.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Anton on 20.12.2016.
 */
public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    /*@Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }*/

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void save() throws Exception {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void update() throws Exception {
        final String UUID_4 = "uuid4";
        storage.update(new Resume(UUID_4));
    }

    @Test(expected = NotExistStorageException.class)
    public void get() throws Exception {
        storage.get("uuid4");
    }

    @Test
    public void delete() throws Exception {
        storage.delete("uuid2");
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void getAll() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    void setStorage(Storage storage) {
        this.storage = storage;
    }
}
