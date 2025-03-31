package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertResume(Resume r, int index) {
        index = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }

    @Override
    protected void fillRemoved(int index) {
        int indexDeleted = size - index - 1;
        if (indexDeleted > 0) {
            System.arraycopy(storage, index + 1, storage, index, indexDeleted);
        }
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume();
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}