package book.manager.mapper;

import book.manager.entity.Book;
import book.manager.entity.Borrow;
import book.manager.entity.BorrowDetails;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("select * from book")
    List<Book> allBook();

    @Select("select * from book where bid = #{bid}")
    Book getBookById(int bid);

    @Delete("delete from book where bid = #{bid}")
    void deleteBook(int bid);

    @Insert("insert into book(title,des,price) values(#{title},#{des},#{price})")
    void addBook(@Param("title") String title,@Param("des") String des,@Param("price") double price);

    @Insert("insert into borrow(bid,sid,`time`) values(#{bid},#{sid},NOW())")
    void addBorrow(@Param("bid") int bid,@Param("sid") int sid);

    @Delete("delete from borrow where bid = #{bid} and sid = #{sid}")
    void deleteBorrow(@Param("bid") int bid,@Param("sid") int sid);

    @Select("select * from borrow")
    List<Borrow> borrowList();

    @Select("select * from borrow where sid = #{sid}")
    List<Borrow> borrowListBySid(int sid);

    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "book_title"),
            @Result(column = "name",property = "user_name"),
            @Result(column = "time",property = "time")
    })
    @Select("select * from borrow LEFT JOIN book on book.bid = borrow.bid "+
            "LEFT JOIN student ON borrow.sid = student.sid")
    List<BorrowDetails> borrowDetailsList();

    @Select("select count(*) from book")
    int getBookCount();

    @Select("select count(*) from borrow")
    int getBorrowCount();
}
