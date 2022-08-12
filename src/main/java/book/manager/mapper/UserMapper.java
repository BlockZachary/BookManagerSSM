package book.manager.mapper;

import book.manager.entity.AuthUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from users where name = #{username}")
    AuthUser getPasswordByUsername(String username);

    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    @Insert("insert into users(name, role, password) values(#{name}, #{role}, #{password})")
    int registerUser(AuthUser user);

    @Insert("insert into student(uid, name, grade, gender) values(#{uid}, #{name}, #{grade}, #{gender})")
    int addStudentInfo(@Param("uid") int uid,@Param("name") String name,@Param("grade") String grade,@Param("gender") String gender);

    @Select("select sid from student where uid = #{uid}")
    Integer getSidByUserId(int uid);

    @Select("select count(*) from student")
    int getStudentCount();


}
