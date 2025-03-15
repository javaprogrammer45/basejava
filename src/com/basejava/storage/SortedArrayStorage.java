package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (getIndex(r.getUuid()) >= 0) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            for (int i = 0; i < size; i++) {
                if (Integer.parseInt(r.getUuid().substring(5)) <
                        Integer.parseInt(storage[i].getUuid().substring(5))) {
                    System.arraycopy(storage, i, storage, i + 1, storage.length - 1);
                    storage[i] = r;
                } else {
                    storage[size] = r;
                    size++;
                }
            }
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            System.arraycopy(storage, index + 1, storage, index, storage.length - 1);
            storage[index] = storage[index + 1];
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}