package com.example.lab1.rest;

import com.example.lab1.entities.Author;
import com.example.lab1.entities.Book;
import com.example.lab1.entities.Genre;
import com.example.lab1.persistence.AuthorsDAO;
import com.example.lab1.rest.dto.AuthorRetrievalDTO;
import com.example.lab1.rest.dto.AuthorCreationDTO;
import com.example.lab1.rest.dto.BookDTO;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/authors")
public class    AuthorController {
    @Inject
    @Setter @Getter
    private AuthorsDAO authorsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Author author = authorsDAO.findOne(id);
        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        AuthorRetrievalDTO authorRetrievalDTO = new AuthorRetrievalDTO();
        authorRetrievalDTO.setName(author.getName());
        authorRetrievalDTO.setLastname(author.getLastname());
        authorRetrievalDTO.setEmail(author.getEmail());
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : author.getBooks()) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setTitle(book.getTitle());
            List<String> genreNames = new ArrayList<>();
            for (Genre genre : book.getGenres()) {
                genreNames.add(genre.getName());
            }
            bookDTO.setGenres(genreNames);
            bookDTOList.add(bookDTO);
        }
        authorRetrievalDTO.setBooks(bookDTOList);

        return Response.ok(authorRetrievalDTO).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Long authorId,
            AuthorCreationDTO authorCreationDTO) {
        try {
            Author existingAuthor = authorsDAO.findOne(authorId);
            if (existingAuthor == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingAuthor.setName(authorCreationDTO.getName());
            existingAuthor.setLastname(authorCreationDTO.getLastname());
            existingAuthor.setEmail(authorCreationDTO.getEmail());
            authorsDAO.update(existingAuthor);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(AuthorCreationDTO authorCreationDTO) {
        Author author = new Author();
        author.setName(authorCreationDTO.getName());
        author.setLastname(authorCreationDTO.getLastname());
        author.setEmail(authorCreationDTO.getEmail());
        author.setVersion(0);
        authorsDAO.persist(author);
        return Response.created(URI.create("http://localhost:8080/Lab1/api/authors/" + author.getId()))
                .entity("http://localhost:8080/Lab1/api/authors/" + author.getId()).build();
    }
}
