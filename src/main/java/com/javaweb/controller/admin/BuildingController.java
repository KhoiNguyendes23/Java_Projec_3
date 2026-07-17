package com.javaweb.controller.admin;



import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.IUserService;
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
    @RequestMapping(value = "/admin/building-list",method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");

        mav.addObject("modelSearch",buildingSearchRequest);
        //Xuong DB -. Lay Data ok roi
        List<BuildingSearchResponse> responseList = new ArrayList<>();
        BuildingSearchResponse item1 = new BuildingSearchResponse();
        item1.setId(3L);
        item1.setName("ACM Building");
        item1.setAddress("130 Quang Trung, Phạm Ngũ Lão, Quận 1");
        item1.setNumberOfBasement(2L);
        item1.setManagerName("Anh Long");
        item1.setManagerPhone("0900000001");
        item1.setFloorArea(100L);
        item1.setRentArea("100,200,300");

        BuildingSearchResponse item2 = new BuildingSearchResponse();
        item2.setId(5L); // Lưu ý: Có vẻ bạn đang để trùng ID với item1, bạn có thể muốn đổi thành 4L hoặc số khác nếu cần.
        item2.setName("Building MA");
        item2.setAddress("Nguyễn Huệ, Tân Mai, Quận 3");
        item2.setNumberOfBasement(3L);
        item2.setManagerName("Anh Hải");
        item2.setManagerPhone("0900000002");
        item2.setFloorArea(100L);
        item2.setRentArea("150,300,400");

        responseList.add(item1);
        responseList.add(item2);
        mav.addObject("buildingList",responseList);
        mav.addObject("listStaffs",userService.getStaffs());
        mav.addObject("districts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        return mav;
    }
    @RequestMapping(value = "/admin/building-edit",method = RequestMethod.GET)
    public ModelAndView buildingEdit(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO,HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        return mav;

    }
    @RequestMapping(value = "/admin/building-edit-{id}", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@PathVariable("id") Long Id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        //xuong DB tim building theo Id
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setId(Id);
        buildingDTO.setName("MAnh Buildin");
        mav.addObject("buildingEdit", buildingDTO);
        return mav;
    }
}
