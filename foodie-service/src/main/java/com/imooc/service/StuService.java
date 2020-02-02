package com.imooc.service;

import com.imooc.pojo.Stu;

/**
 * Created by eru on 2020/2/2.
 */
public interface StuService {
    Stu getStu(int id);
    void del(int id);
    void add();
    void update(int id);
    void saveParent();
    void saveChildren();
}
