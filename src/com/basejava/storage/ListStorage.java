package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.ListIterator;

public class ListStorage extends AbstractStorage {

    public void doDelete() {
        resumes.clear();
    }

    public void doUpdate(Resume r) {
        int index;
        int result;
        ListIterator<Resume> listIterator = resumes.listIterator();
        while (listIterator.hasNext()) {
            result = r.getUuid().compareTo(listIterator.next().getUuid());
            if (result == 0) {
                index = listIterator.nextIndex();
                resumes.add(index, r);
            } else {
                System.out.println("Resume " + r.getUuid() + " not exist");
            }
        }
    }

    public void doSave(Resume r) {
        if (!resumes.contains(r)) {
            resumes.add(r);
        } else {
            System.out.println("Resume " + r.getUuid() + " already exist");
        }
    }

    public Resume doGet(String uuid) {
        return resumes.get(getSearchKey(uuid));
    }

    public Integer getSearchKey(String uuid) {
        for (Resume r : resumes) {
            int result = r.getUuid().compareTo(uuid);
            if (result == 0) {
                return resumes.indexOf(r);
            }
        }
        return null;
    }

    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    public void do(String uuid) {
        int index = getSearchKey(uuid);
        resumes.remove(index);
    }

    public void clear() {
        resumes.clear();
    }

    @Override
    public Resume[] getAll() {
        return resumes.toArray(new Resume[resumes.size()]);
    }

    @Override
    public int size() {
        return resumes.size();
    }
}
