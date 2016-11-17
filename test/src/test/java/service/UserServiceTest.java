package service;

import com.dal.pojo.UserBo;
import com.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void selectByUsername(){
		String username = "test";
		UserBo userBo = userService.selectByUsername(username);
		Assert.assertNotNull(userBo);
		Assert.assertEquals(username, userBo.getUsrName());
	}
}
