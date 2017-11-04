package qdu.java.recruit.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import qdu.java.recruit.entity.DepartmentEntity;
import qdu.java.recruit.service.DepartmentService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentServiceImplTest {
    @Autowired
    private DepartmentService departmentService;

    @Test
    public void getDepartment() throws Exception {

        DepartmentEntity department = departmentService.getDepartment(1);
        Assert.assertEquals("Develop",department.getDepartmentName());
    }

}