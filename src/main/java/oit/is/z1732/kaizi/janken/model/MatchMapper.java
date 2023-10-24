package oit.is.z1732.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchMapper {
  @Select("SELECT * from matches;")
  ArrayList<Match> selectAllMatch();

  @Insert("Insert INTO matches (user1,user2,user1Hand,user2Hand,isActive) VALUES (#{user1},#{user2},#{user1Hand},#{user2Hand},false);")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUserResult(Match match);
}
