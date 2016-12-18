package com.work.webapp.storage;

import com.work.webapp.model.Resume;

import java.util.Arrays;

/**
 * Created by Anton on 06.12.2016.
 */
public class SortedArrayStorage extends AbstractArrayStorage {

   /* @Override
    public void save(Resume r) {
        if (size == STORAGE_MAX_LENGTH) {
            System.out.println("Хранилище резюме переполнено!!!");
        } else if (!resumeIsExist(r.getUuid())) {
            paste(r);
        }
    }

    @Override
    public void update(Resume r) throws IOException {
        if (!isNullStorage()) {
            Integer indexToDelete = Arrays.binarySearch(storage, 0, size, r);
            if (!resumeIsNotExist(r.getUuid())) {
                System.out.println("Введите изменения:");
                String newResume = reader.readLine().trim();
                if (newResume.length() != 0) {
                    r.setUuid(newResume);
                    if (!resumeIsExist(newResume)) {
                        remove(indexToDelete);
                        paste(r);
                        System.out.println("Резюме " + newResume + " успешно изменено!");
                    }
                } else
                    System.out.println("Неверная комманда!!!");
            }
        }
    }

    @Override
    public void delete(String uuid) {
        if (!isNullStorage()) {
            Integer index = find(uuid);
            if (!resumeIsNotExist(uuid)) {
                remove(index);
            }
        }
    }*/

    @Override
    protected void insertElement(Resume r, int index) {
//        http://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array#answer-36239
        int insertIdx = -index - 1;
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx);
        storage[insertIdx] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }
    }

    @Override
    protected int find(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    /*private void paste(Resume r) {
        Integer index = Arrays.binarySearch(storage, 0, size, r);
        index = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, storage.length - index - 1);
        storage[index] = r;
        size++;
    }

    private void remove(Integer index) {
        System.arraycopy(storage, index + 1, storage, index, storage.length - index - 1);
        size--;
    }*/
}
