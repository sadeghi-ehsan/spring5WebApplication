package ehsan.springframework.springwebapp.bootstrap;

import ehsan.springframework.springwebapp.model.Author;
import ehsan.springframework.springwebapp.model.Book;
import ehsan.springframework.springwebapp.model.Publisher;
import ehsan.springframework.springwebapp.repositories.AuthorRepository;
import ehsan.springframework.springwebapp.repositories.BookRepository;
import ehsan.springframework.springwebapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
        Publisher publisher = new Publisher();
        publisher.setName("foo");

        publisherRepository.save(publisher);

        Author eric = new Author("Eric" , "Evans");
        Book ddd = new Book("Domain Driven Design", "1234" , publisher);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(ddd);


        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", publisher );

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

    }

}
