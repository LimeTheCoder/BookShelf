package com.limethecoder.data.service.impl;

import com.limethecoder.data.domain.Book;
import com.limethecoder.data.domain.Like;
import com.limethecoder.data.domain.Rate;
import com.limethecoder.data.repository.BookRepository;
import com.limethecoder.data.repository.LikeRepository;
import com.limethecoder.data.repository.RateRepository;
import com.limethecoder.data.service.BookService;
import com.limethecoder.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class BookServiceImpl extends AbstractMongoService<Book, String>
        implements BookService {

    private BookRepository repository;
    private RateRepository rateRepository;
    private LikeRepository likeRepository;

    @Autowired
    public BookServiceImpl(BookRepository repository,
                           RateRepository rateRepository,
                           LikeRepository likeRepository) {
        this.repository = repository;
        this.rateRepository = rateRepository;
        this.likeRepository = likeRepository;
    }

    @Override
    public Book add(Book book) {
        saveCover(book);
        return repository.save(book);
    }

    @Override
    public Book update(Book book) {
        saveCover(book);
        return repository.save(book);
    }

    @Override
    public void delete(String bookId) {
        List<Rate> rates = rateRepository.findByBookId(bookId);

        Book book = repository.findOne(bookId);
        FileUtil.removeFileIfExists(book.getCoverUrl());

        if(rates != null && !rates.isEmpty()) {
            rateRepository.delete(rates);
        }

        List<Like> likes = likeRepository.findByBookId(bookId);
        if(likes != null && !likes.isEmpty()) {
            likeRepository.delete(likes);
        }

        repository.delete(bookId);
    }

    private void saveCover(Book book) {
        final String COVER_PREFIX = "_cover";
        if(book.getCover() != null && !book.getCover().isEmpty()) {
            String[] parts = book.getCover().getOriginalFilename()
                    .split("\\.");

            String fileExtension = parts[parts.length - 1];
            String filename = book.getId() + COVER_PREFIX +
                    "." + fileExtension;

            FileUtil.saveFile(book.getCover(), filename);
            book.setCoverUrl(filename);
        }
    }

    @Override
    protected MongoRepository<Book, String> getRepository() {
        return repository;
    }
}
