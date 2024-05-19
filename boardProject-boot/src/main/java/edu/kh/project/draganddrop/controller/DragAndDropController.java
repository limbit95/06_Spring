package edu.kh.project.draganddrop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.project.draganddrop.model.dto.Group;
import edu.kh.project.draganddrop.model.service.GroupService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/groups")
@Slf4j
public class DragAndDropController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public String listGroups(Model model) {
        List<Group> groups = groupService.getAllGroups();
        
        log.debug("aaaaaaaaa={}", groups.toString());
        model.addAttribute("groups", groups);
        return "group/list";
    }	
    
    @PostMapping("move")
    @ResponseBody
    public String aaa(@RequestBody Map<String, Object> map) {
    	
    	String groupId = (String)map.get("groupId");
    	String newParentId = (String) map.get("newParentId");
    	log.debug(groupId);
    	log.debug(newParentId);
    	
    	// grouidId 값은 null 일 수 없음. 무조건 드래그한게 존재하기 때문에 이 컨트롤러로 요청이 왔기 때문에
    	// 무조건 있음. 
    	// 근데, newParentId 같은 경우, null 일 수 있거든?
    	// 만약 어떤 부서에도 속하지 않은 곳으로 뺀다면 null 일 수 있어. 
    	// 그럼 그냥 parent_id 값을 null 로 하면됨. 
    	
    	groupService.changePosition(groupId, newParentId);
    	
    	
    	return "1";
    }
}
