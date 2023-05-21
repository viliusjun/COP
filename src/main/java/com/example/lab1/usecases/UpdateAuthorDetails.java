package com.example.lab1.usecases;

import com.example.lab1.entities.Author;
import com.example.lab1.interceptors.LoggedInvocation;
import com.example.lab1.persistence.AuthorsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class UpdateAuthorDetails implements Serializable {
    private Author author;

    @Inject
    private AuthorsDAO authorsDAO;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long authorId = Long.parseLong(requestParameters.get("authorId"));
        this.author = authorsDAO.findOne(authorId);
        System.out.println("author:");
        System.out.println(this.author);
    }

    @Transactional
    @LoggedInvocation
    public String update() {
        try{
            authorsDAO.update(this.author);
            System.out.println("it worked.");
        } catch (OptimisticLockException e) {
            System.out.println("Exception!!!");
            return "/authorEdit.xhtml?faces-redirect=true&authorId=" + this.author.getId() + "&error=optimistic-lock-exception";
        }
        return "books.xhtml?authorId=" + this.author.getId() + "&faces-redirect=true";
    }
}
