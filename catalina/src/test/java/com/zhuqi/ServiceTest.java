package com.zhuqi;

import com.zhuqi.config.SpringMvcConfig;
import com.zhuqi.pojo.User;
import com.zhuqi.service.user.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringMvcConfig.class})
public class ServiceTest{
    @Autowired
    private ListService listService;
    @Test
    public void testListService(){
        listService.getList("", "");
    }
}
