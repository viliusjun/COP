package com.example.lab1.services;

import com.example.lab1.mybatis.model.Author;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.regex.Pattern;

@Decorator
public abstract class EmailValidationAuthorServiceDecorator implements AuthorService {

    @Inject @Delegate @Any AuthorService delegate;

    private final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    @Override
    public String createAuthor(Author author) {
        if (!emailPattern.matcher(author.getEmail()).matches()) {
            throw new IllegalArgumentException("Email is not valid");
        }
        return delegate.createAuthor(author);
    }
}
