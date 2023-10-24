package oit.is.z1732.kaizi.janken.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface MatchInfoMapper {
  @Insert("Insert INTO matchinfo (user1,user2,user1Hand,isActive) VALUES (#{user1},#{user2},#{user1Hand},true);")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatchInfo(MatchInfo matchInfo);
}
