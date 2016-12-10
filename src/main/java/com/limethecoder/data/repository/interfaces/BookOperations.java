package com.limethecoder.data.repository.interfaces;


public interface BookOperations {
    void updateBookRate(String bookId, int valueInc,
                               int cntInc);
}
