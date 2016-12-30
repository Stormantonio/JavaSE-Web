package com.work.webapp.storage;

import com.work.webapp.exception.StorageException;
import org.junit.Test;

/**
 * Created by Anton on 20.12.2016.
 */
public class SortedArrayStorageTest extends AbstractArrayStorageTest {
    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }
}
