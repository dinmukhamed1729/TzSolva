package kz.din.transactions;

import kz.din.transactions.service.implementation.LimitChangesServiceImpl;
import kz.din.transactions.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class TransactionsApplicationTests {


    @Test
	void contextLoads() throws IOException {

	}

	@Test
	void apiConnection()throws IOException{

	}


	@Autowired
	UserServiceImpl userService ;
	@Autowired
	LimitChangesServiceImpl limitChangesService;
	@Test
	void testUserMapper(){
		System.out.println(limitChangesService.getUserLastLimitChange(userService.getByBankAccount("0000000001")));

	}

}
