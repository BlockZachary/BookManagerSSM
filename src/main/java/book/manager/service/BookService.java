package book.manager.service;

import book.manager.entity.Book;
import book.manager.entity.BorrowDetails;

import java.util.List;

public interface BookService {
    List<Book> getAllBook();
    List<Book> getAllBookWithoutBorrow();
    List<Book> getAllBorrowedBookById(int id);
    void deleteBook(int bid);
    void addBook(String title, String des, double price);
    void borrowBook(int bid,int sid);
    void returnBook(int bid,int id);
    List<BorrowDetails> getBorrowDetailsList();

}
