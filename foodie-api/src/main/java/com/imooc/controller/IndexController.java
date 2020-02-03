package com.imooc.controller;

import com.imooc.common.enums.YesOrNo;
import com.imooc.common.utils.IMOOCJSONResult;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by eru on 2020/2/3.
 */
@Api(value = "轮播图", tags = {"首页轮播图接口"})
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "首页轮播图", notes = "首页轮播图", httpMethod = "GET")
    @GetMapping("/carousel")
    public IMOOCJSONResult carousel(){
        List<Carousel> carouselList = carouselService.queryAll(YesOrNo.yes.type);
        return IMOOCJSONResult.ok(carouselList);
    }

    @ApiOperation(value = "一级分类", notes = "一级分类", httpMethod = "GET")
    @GetMapping("/cats")
    public IMOOCJSONResult cats(){
        List<Category> categoryList = categoryService.queryAllRootLevelCat();
        return IMOOCJSONResult.ok(categoryList);
    }

    @ApiOperation(value = "根据一级分类查询子分类信息", notes = "根据一级分类查询子分类信息", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public IMOOCJSONResult subCat(@PathVariable @ApiParam(name = "rootCatId", value = "一级分类id") Integer rootCatId){
        if (rootCatId == null){
            return IMOOCJSONResult.errorMsg("查询子分类信息有误");
        }
        List<CategoryVO> subCatList = categoryService.getSubCatList(rootCatId);
        return IMOOCJSONResult.ok(subCatList);
    }
}
