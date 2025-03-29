package com.basejava.storage;

import com.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected void insertResume(Resume r, int index) {
        storage[size] = r;
    }


    protected void fillRemoved(int index) {
        int indexDeleted = size - index - 1;
        if (indexDeleted > 0) {
            storage[index] = storage[size - 1];
        }
    }


    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return null;
    }



}