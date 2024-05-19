package edu.kh.project.draganddrop.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.draganddrop.model.dto.Group;

@Mapper
public interface GroupRepository {

	List<Group> findAll();

	void changePosition(Map<String, String> paramMap);

}