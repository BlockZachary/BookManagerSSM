package book.manager.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Borrow {
    int id;
    int bid;
    int sid;
    Date date;
}
