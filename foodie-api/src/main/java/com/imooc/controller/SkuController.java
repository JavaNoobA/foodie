package com.imooc.controller;

import com.imooc.pojo.Stu;
import com.imooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by eru on 2020/2/2.
 */
@RestController
public class SkuController {

    @Autowired
    private StuService stuService;

    @GetMapping("/getStu")
    public Stu getStu(int id){
        return stuService.getStu(id);
    }

    @PostMapping("/add")
    public Object add(){
        stuService.add();
        return "add";
    }

    @PostMapping("/del")
    public Object del(int id){
        stuService.del(id);
        return "del";
    }

    @PostMapping("/update")
    public Object update(int id){
        stuService.update(id);
        return "update";
    }
}
