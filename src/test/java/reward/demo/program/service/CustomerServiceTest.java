package reward.demo.program.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import reward.demo.program.TestSpringConfig;
import reward.demo.program.model.TransactionRecordDTO;
import reward.demo.program.service.CustomerService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestSpringConfig.class })
public class CustomerServiceTest {

	@Autowired
	private CustomerService testService;

	@Test
	void test_findThreeMonthTrans() {
		List<TransactionRecordDTO> custTrans = testService.findThreeMonthTrans();
		assertEquals(12, custTrans.size());
	}
}
