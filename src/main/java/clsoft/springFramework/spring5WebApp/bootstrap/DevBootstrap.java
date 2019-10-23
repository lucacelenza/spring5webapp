package clsoft.springFramework.spring5WebApp.bootstrap;

import clsoft.springFramework.spring5WebApp.models.Author;
import clsoft.springFramework.spring5WebApp.models.Book;
import clsoft.springFramework.spring5WebApp.repositories.AuthorRepository;
import clsoft.springFramework.spring5WebApp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Author uncleBob = new Author("Robert C.", "Martin");
        Book cleanCode = new Book("Clean Code", "9780132350884", "Robert C. Martin");

        uncleBob.getBooks().add(cleanCode);
        cleanCode.getAuthors().add(uncleBob);

        authorRepository.save(uncleBob);
        bookRepository.save(cleanCode);

        Author dino = new Author("Dino", "Esposito");
        Author andrea = new Author("Andrea", "Saltarello");

        Book dotNetEnterprise = new Book("Microsoft .NET: Architecting Applications for the Enterprise - Second Edition", "0735685355", "Microsoft Press");

        dino.getBooks().add(dotNetEnterprise);
        authorRepository.save(dino);

        andrea.getBooks().add(dotNetEnterprise);
        authorRepository.save(andrea);

        dotNetEnterprise.getAuthors().add(dino);
        dotNetEnterprise.getAuthors().add(andrea);

        bookRepository.save(dotNetEnterprise);
    }
}
