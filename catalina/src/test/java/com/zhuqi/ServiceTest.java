package com.zhuqi;

import com.zhuqi.config.SpringConfig;
import com.zhuqi.config.SpringMvcConfig;
import com.zhuqi.pojo.User;
import com.zhuqi.service.DeleteService;
import com.zhuqi.service.GetInfoService;
import com.zhuqi.service.RegisterService;
import com.zhuqi.service.UpdateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringMvcConfig.class})
public class ServiceTest{
    private User user = new User();
    @Autowired
    private RegisterService registerService;
    @Autowired
    private GetInfoService getInfoService;
    @Autowired
    private UpdateService updateService;
    @Autowired
    private DeleteService deleteService;
    @Test
    public void testSelect(){
        user.setPassword("123456");
        user.setUsername(null);
        user.setId(13);
        getInfoService.getInfo(user);
//        deleteService.deleteUser(user);
    }
}
