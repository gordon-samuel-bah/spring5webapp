package com.samueljgordon.spring5webapp.bootstrap;

import com.samueljgordon.spring5webapp.domain.Author;
import com.samueljgordon.spring5webapp.domain.Book;
import com.samueljgordon.spring5webapp.domain.Publisher;
import com.samueljgordon.spring5webapp.repositories.AuthorRepository;
import com.samueljgordon.spring5webapp.repositories.BookRepository;
import com.samueljgordon.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "12891281982");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());

        Publisher sam = new Publisher("Sam Gordon", "101 Telfair Ln", "Nolensville", "TN", "37135");
        ddd.setPublisher(sam);
        sam.getBooks().add(ddd);

        noEJB.setPublisher(sam);
        sam.getBooks().add(noEJB);

        publisherRepository.save(sam);

        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Publisher number of books: " + sam.getBooks().size());
    }
}
