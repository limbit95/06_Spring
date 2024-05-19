package edu.kh.project.draganddrop.model.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.draganddrop.model.dto.Group;
import edu.kh.project.draganddrop.model.mapper.GroupRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class GroupService {
	
	private final GroupRepository groupRepository;
	
    public List<Group> getAllGroups() {
        // 모든 그룹을 가져옴. 
        List<Group> allGroups = groupRepository.findAll();
        
        log.debug("sadfjlasdkfjalskjd===={}", allGroups);
       
        // 가나다순 정렬
        //allGroups.sort(Comparator.comparing(Group::getName));
        return buildGroupHierarchy(allGroups);
    }

    private List<Group> buildGroupHierarchy(List<Group> groups) {
        Map<Long, Group> groupMap = new HashMap<>();
        List<Group> rootGroups = new ArrayList<>();

        // 모든 그룹을 맵에 추가
        for (Group group : groups) {
            groupMap.put(group.getId(), group);
        }

        // 각 그룹을 부모 자식 관계로 연결
        for (Group group : groups) {
            if (group.getParentId() != null) {
                Group parentGroup = groupMap.get(group.getParentId());
                if (parentGroup != null) {
                    if (parentGroup.getChildren() == null) {
                        parentGroup.setChildren(new ArrayList<>());
                    }
                    parentGroup.getChildren().add(group);
                }
            } else {
                rootGroups.add(group);
            }
        }

        return rootGroups;
    }

	public void changePosition(String groupId, String newParentId) {
		
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("groupId", groupId);
		paramMap.put("newParentId", newParentId);
		
		groupRepository.changePosition(paramMap);
	}
}
