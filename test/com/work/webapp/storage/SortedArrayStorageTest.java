package com.work.webapp.storage;

import com.work.webapp.exception.StorageException;
import org.junit.Test;

/**
 * Created by Anton on 20.12.2016.
 */
public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    @Test (expected = StorageException.class)
    public void sortedArrayStorageTest() throws Exception {
        Storage storage = new SortedArrayStorage();
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
