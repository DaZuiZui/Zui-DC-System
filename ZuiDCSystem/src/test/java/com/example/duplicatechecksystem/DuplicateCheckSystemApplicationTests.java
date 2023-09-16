package com.example.duplicatechecksystem;

import com.example.duplicatechecksystem.aaa.Ttil;
import com.example.duplicatechecksystem.service.DuplicationCheckerCoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DuplicateCheckSystemApplicationTests {

    @Autowired
    private DuplicationCheckerCoreService duplicationCheckerService ;


    @Test
    void contextLoads() {
        duplicationCheckerService.check(Ttil.a,Ttil.b,null);
    }

}
