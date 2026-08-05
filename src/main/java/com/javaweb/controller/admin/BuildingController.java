package com.javaweb.controller.admin;



import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IBuildingService buildingService;


    @RequestMapping(value = "/admin/building-list",method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");

        mav.addObject("modelSearch",buildingSearchRequest);
        //Xuong DB -. Lay Data ok roi
        List<BuildingSearchResponse> responseList = buildingService.findBuildings(buildingSearchRequest);
        int totalItems = buildingService.countBuildings(buildingSearchRequest);
        int totalPages = (int) Math.ceil((double) totalItems / buildingSearchRequest.getPageSize());
        mav.addObject("buildingList",responseList);
        mav.addObject("listStaffs",userService.getStaffs());
        mav.addObject("districts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        mav.addObject("totalPages",totalPages);
        mav.addObject("currentPage",buildingSearchRequest.getPage());
        return mav;
    }
    @RequestMapping(value = "/admin/building-edit",method = RequestMethod.GET)
    public ModelAndView buildingEdit(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO,HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districts", districtCode.type());  // ← thêm
        mav.addObject("typeCodes", buildingType.type());
        return mav;

    }
    @RequestMapping(value = "/admin/building-edit-{id}", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@PathVariable("id") Long Id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        //xuong DB tim building theo Id
        BuildingDTO buildingDTO = buildingService.findById(Id);

        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("districts",districtCode.type());
        mav.addObject("typeCodes",buildingType.type());
        return mav;
    }
}
