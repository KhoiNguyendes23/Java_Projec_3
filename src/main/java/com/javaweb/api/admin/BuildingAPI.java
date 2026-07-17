package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private IBuildingService buildingService;
    @PostMapping
    public BuildingDTO addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        return buildingDTO;
    }
    @DeleteMapping("/{ids}")
    public void deleteBuilding(@RequestBody List<Long>ids){
        //Xuống DB để xoá building theo danh sach id gui về

        System.out.println("ok");
    }
    @GetMapping("/{id}/staffs")
    public ResponseDTO loadstaffs(@PathVariable Long id){
        ResponseDTO result = buildingService.listStaffs(id);
        return  result;
    }
    @PostMapping("/assignment")
    public void updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        System.out.println("ok");
    }
}
