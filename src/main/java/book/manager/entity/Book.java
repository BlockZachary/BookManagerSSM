package book.manager.entity;

import lombok.Data;

@Data
public class Book {
    int bid;
    String title;
    String des;
    double price;
}
