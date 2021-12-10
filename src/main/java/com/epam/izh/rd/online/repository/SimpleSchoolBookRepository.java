package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = {};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] tempSchoolBook = new SchoolBook[schoolBooks.length + 1];

        System.arraycopy(schoolBooks, 0, tempSchoolBook, 0, schoolBooks.length);
        tempSchoolBook[schoolBooks.length] = book;
        schoolBooks = Arrays.copyOf(tempSchoolBook, tempSchoolBook.length);
        return true;
    }


    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] foundSchoolBooks = {};
        SchoolBook[] tempFoundSchoolBooks = new SchoolBook[schoolBooks.length];
        int numberOfFoundSchoolBooks = 0;

        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                numberOfFoundSchoolBooks++;
                for (int j = 0; j < numberOfFoundSchoolBooks; j++) {
                    tempFoundSchoolBooks[j] = schoolBook;
                }
                foundSchoolBooks = Arrays.copyOf(tempFoundSchoolBooks, numberOfFoundSchoolBooks);

            }
        }
        return foundSchoolBooks;
    }

    @Override
    public boolean removeByName(String name) {
        SchoolBook[] tempBooks = new SchoolBook[schoolBooks.length];
        int numberOfBooks = 0;
        boolean isRemoved = false;

        for (int i = 0; i < schoolBooks.length; i++) {
            if (!schoolBooks[i].getName().equals(name)) {
                tempBooks[numberOfBooks] = schoolBooks[i];
                numberOfBooks++;
            } else {
                schoolBooks[i] = null;
                isRemoved = true;
            }
        }

        if (isRemoved) {
            schoolBooks = Arrays.copyOf(tempBooks, numberOfBooks);
        }
        return isRemoved;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
