package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = {};

    Author foundAuthor = new Author();


    @Override
    public boolean save(Author author) {
        Author[] tempAuthors = new Author[authors.length + 1];

        if (!author.equals(findByFullName(author.getName(), author.getLastName()))) {
            System.arraycopy(authors, 0, tempAuthors, 0, authors.length);
            tempAuthors[authors.length] = author;
            authors = Arrays.copyOf(tempAuthors, tempAuthors.length);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        boolean authorFound = false;
        int i = 0;
        foundAuthor = null;

        while ((i < authors.length) && (!authorFound)) {
            if ((authors[i].getName().equals(name)) && (authors[i].getLastName().equals(lastname))) {
                foundAuthor = authors[i];
                authorFound = true;
            }
            i++;
        }
        return foundAuthor;
    }

    @Override
    public boolean remove(Author author) {
        Author[] tempAuthors = new Author[authors.length];
        Author temp;
        boolean sorted = false;
        boolean removed = false;
        int i = 0;

        while ((i < authors.length) && (!removed)) {
            if ((authors[i].getName().equals(author.getName())) && (authors[i].getLastName().equals(author.getLastName()))) {
                authors[i] = null;
                removed = true;
            }
            i++;
        }

        while (!sorted) {
            sorted = true;
            for (int j = 0; j < authors.length - 1; j++) {
                if (authors[j] == null) {
                    temp = authors[j];
                    authors[j] = authors[j + 1];
                    authors[j + 1] = temp;
                    sorted = false;
                }
            }
        }
        if (removed) {
            tempAuthors = Arrays.copyOf(authors, tempAuthors.length - 1);
            authors = Arrays.copyOf(tempAuthors, tempAuthors.length);
        }
        return removed;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
