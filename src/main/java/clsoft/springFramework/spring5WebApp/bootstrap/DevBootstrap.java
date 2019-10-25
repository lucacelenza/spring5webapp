package clsoft.springFramework.spring5WebApp.bootstrap;

import clsoft.springFramework.spring5WebApp.models.Author;
import clsoft.springFramework.spring5WebApp.models.Book;
import clsoft.springFramework.spring5WebApp.models.Publisher;
import clsoft.springFramework.spring5WebApp.repositories.AuthorRepository;
import clsoft.springFramework.spring5WebApp.repositories.BookRepository;
import clsoft.springFramework.spring5WebApp.repositories.PublisherRepository;
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

    private void initData() {
        Author uncleBob = new Author("Robert C.", "Martin");
        Book cleanCode = new Book("Clean Code", "9780132350884");

        Publisher prenticeHall = new Publisher("Prentice Hall", "Upper Saddle River, New Jersey, US");
        publisherRepository.save(prenticeHall);

        cleanCode.setPublisher(prenticeHall);
        uncleBob.getBooks().add(cleanCode);

        cleanCode.getAuthors().add(uncleBob);

        authorRepository.save(uncleBob);
        bookRepository.save(cleanCode);

        Author dino = new Author("Dino", "Esposito");
        Author andrea = new Author("Andrea", "Saltarello");

        Publisher microsoftPress = new Publisher("Microsoft Press", "N/A");
        publisherRepository.save(microsoftPress);

        Book dotNetEnterprise = new Book("Microsoft .NET: Architecting Applications for the Enterprise - Second Edition", "0735685355");
        dotNetEnterprise.setPublisher(microsoftPress);

        dino.getBooks().add(dotNetEnterprise);
        authorRepository.save(dino);

        andrea.getBooks().add(dotNetEnterprise);
        authorRepository.save(andrea);

        dotNetEnterprise.getAuthors().add(dino);
        dotNetEnterprise.getAuthors().add(andrea);

        bookRepository.save(dotNetEnterprise);
    }
}
