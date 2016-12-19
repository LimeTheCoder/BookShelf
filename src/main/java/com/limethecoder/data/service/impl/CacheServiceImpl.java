package com.limethecoder.data.service.impl;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.limethecoder.data.domain.Book;
import com.limethecoder.data.domain.User;
import com.limethecoder.data.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CacheServiceImpl implements CacheService {

    private static final Logger logger = LoggerFactory
            .getLogger(CacheServiceImpl.class);

    private Jedis jedis;
    private Gson gson = new Gson();

    @Autowired
    public CacheServiceImpl(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void addBooks(List<Book> books, int page, String query) {
        String data = gson.toJson(books);
        String key = buildKey(BOOKS_KEY, page, query);

        if(query.isEmpty()) {
            String rangeKey = RANGE + key;
            String range = books.get(0).getId() + SEPARATOR +
                    books.get(books.size() - 1).getId();
            jedis.set(rangeKey, range);
            jedis.expire(rangeKey, EXPIRE_TIME);
        }

        jedis.set(key, data);
        jedis.expire(key, EXPIRE_TIME);
    }

    @Override
    public void addBooks(String key, List<Book> books) {
        String data = gson.toJson(books);

        jedis.set(key, data);
        jedis.expire(key, EXPIRE_TIME);
    }

    @Override
    public void addUser(User user) {
        String data = gson.toJson(user);
        jedis.set(user.getLogin(), data);
        jedis.expire(user.getLogin(), EXPIRE_TIME);
    }

    @Override
    public void addImage(String key, byte[] image) {
        String data = gson.toJson(image);
        jedis.set(key, data);
        jedis.expire(key, EXPIRE_TIME);
    }

    @Override
    public void add(String key, String value) {
        jedis.set(key, value);
        jedis.expire(key, EXPIRE_TIME);
    }

    @Override
    public List<Book> getBooks(int page, String query) {
        String key = buildKey(BOOKS_KEY, page, query);
        return getBooks(key);
    }

    @Override
    public List<Book> getBooks(String key) {
        String data = jedis.get(key);
        Type type = new TypeToken<List<Book>>(){}.getType();

        return gson.fromJson(data, type);
    }

    @Override
    public User getUser(String login) {
        String data = jedis.get(login);
        return gson.fromJson(data, User.class);
    }

    @Override
    public byte[] getImage(String key) {
        String data = jedis.get(key);
        return gson.fromJson(data, byte[].class);
    }

    @Override
    public String get(String key) {
        jedis.expire(key, EXPIRE_TIME);
        return jedis.get(key);
    }

    @Override
    public boolean exists(String typeKey, int page, String query) {
        String key = buildKey(typeKey, page, query);
        return jedis.exists(key);
    }

    @Override
    public boolean exists(String key) {
        return jedis.exists(key);
    }

    @Override
    public void invalidateCache() {
        jedis.flushDB();
    }

    @Override
    public void invalidate(String key) {
        jedis.del(key);
    }


    @Override
    public void onBookUpdate(Book book) {
        invalidateBooksQueryCache();

        Set<String> keys = jedis.keys("*");
        if(keys == null || keys.isEmpty()) {
            return;
        }

        keys = keys.stream().filter((x) -> x.startsWith("books&"))
                .collect(Collectors.toSet());

        String pageKey = null;

        for(String key : keys) {
            String data = jedis.get(RANGE + key);
            String[] range = data.split(SEPARATOR);
            if(book.getId().compareTo(range[0]) >= 0 &&
                    book.getId().compareTo(range[1]) <= 0) {
                pageKey = key;
                break;
            }
        }

        logger.info("Update " + pageKey);

        if(pageKey == null) {
            return;
        }

        invalidate(pageKey);
        invalidate(RANGE + pageKey);
    }

    @Override
    public void onBookDelete(Book book) {
        invalidateBooksQueryCache();

        Set<String> all = jedis.keys("*");
        if(all == null || all.isEmpty()) {
            return;
        }

        List<String> keys = all.stream().filter((x) -> x.startsWith("books&"))
                .sorted()
                .collect(Collectors.toList());


        boolean isFound = false;
        for(String key : keys) {
            String data = jedis.get(RANGE + key);
            String[] range = data.split(SEPARATOR);

            if (!isFound) {
                if (book.getId().compareTo(range[0]) >= 0 &&
                        book.getId().compareTo(range[1]) <= 0) {
                    isFound = true;
                }
            }

            if(isFound) {
                invalidate(key);
                invalidate(RANGE + key);

                logger.info("Delete " + key);
            }
        }
    }

    @Override
    public void onBookInsert(Book book) {
        invalidateBooksQueryCache();

        Set<String> all = jedis.keys("*");
        if(all == null || all.isEmpty()) {
            return;
        }

        List<String> keys = all.stream()
                .filter((x) -> x.startsWith("books&"))
                .sorted()
                .collect(Collectors.toList());

        if (keys == null || keys.isEmpty()) {
            return;
        }

        String key = keys.get(keys.size() - 1);

        logger.info("Insertion key: " + key);
        invalidate(key);
        invalidate(RANGE + key);
    }


    private String buildKey(String typeKey, int page, String query) {
        StringBuilder key = new StringBuilder(typeKey)
                .append(SEPARATOR)
                .append(PAGE)
                .append(page);

        if(!query.isEmpty()) {
            key.append(SEPARATOR);
            key.append(QUERY);
            key.append(query);
        }

        return key.toString();
    }

    private void invalidateBooksQueryCache() {
        Set<String> keys = jedis.keys("*");
        if(keys != null && !keys.isEmpty()) {
            keys = keys.stream().filter((x) -> x.startsWith("books&")
                    && x.contains("q")).collect(Collectors.toSet());
            for(String key : keys) {
                jedis.del(key);
            }
        }
    }

    private int keyToPage(String key) {
        String [] parts = key.split("=");
        return Integer.valueOf(parts[parts.length - 1]);
    }
}
