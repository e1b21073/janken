package oit.is.z1732.kaizi.janken.model;

import java.util.ArrayList;

//import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT * from users")
  ArrayList<User> selectAllUser();

  @Select("SELECT * from users where id = #{id}")
  User selectUserById(int id);

  @Select("SELECT * from users where name = #{name}")
  User selectUserByName(String name);
}
