package reward.demo.program.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import reward.demo.program.model.CustomerDTO;
import reward.demo.program.model.MonthlyRewards;
import reward.demo.program.model.TransactionRecordDTO;
import reward.demo.program.util.DataBuilder;

public class MonthlyRewardsTest {
	private DataBuilder dataBuilder = new DataBuilder();

	private CustomerDTO mary = dataBuilder.createCustomerDTO("Mary");

	private MonthlyRewards testClass = new MonthlyRewards(mary);

	@Test
	void test_getMonthlyTotalRewardPoint() {

		List<TransactionRecordDTO> trans = new ArrayList<>();
		trans.add(dataBuilder.createTransactionRecordDTO(mary, "90", 2, 1));
		trans.add(dataBuilder.createTransactionRecordDTO(mary, "50", 2, 1));
		testClass.getMonthlyTrans().put(Month.JUNE, trans);

		assertEquals(0, testClass.getMonthlyTotalRewardPoint().get(Month.JUNE).compareTo(new BigDecimal("40")));

	}

}
