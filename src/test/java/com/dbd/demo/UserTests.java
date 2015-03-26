package com.dbd.demo;

import com.dbd.demo.domain.User;
import com.dbd.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;

/**
 * Created by niallmilton on 08/03/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppStart.class)
public class UserTests {

    @Autowired
    UserService userService;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUserId("niall");
        user.setFirstName("Niall");
        user.setLastName("Milton");
        user.setPassword("password");

        userService.createUser(user);

        User readBack = userService.findUser("niall");

        assertEquals(user.getPassword(), readBack.getPassword());
    }

}
