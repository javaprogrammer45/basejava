package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.ListIterator;

public class ListStorage extends AbstractStorage {

    public void deleteElements() {
        resumes.clear();
    }

    public void updateElement(Resume r) {
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

    public void saveResume(Resume r) {
        if (!resumes.contains(r)) {
            resumes.add(r);
        } else {
            System.out.println("Resume " + r.getUuid() + " already exist");
        }
    }

    public Resume getResume(String uuid) {
        return resumes.get(getIndex(uuid));
    }

    public int getIndex(String uuid) {
        for (Resume r : resumes) {
            int result = r.getUuid().compareTo(uuid);
            if (result == 0) {
                return resumes.indexOf(r);
            }
        }
        return -1;
    }

    public void removeResume(String uuid) {
        int index = getIndex(uuid);
        resumes.remove(index);
    }

    public Resume[] getAllResumes() {
        return resumes.subList(0, resumes.size()).toArray(new Resume[0]);
    }

    public int sizeStorage() {
        return resumes.size();
    }
}
