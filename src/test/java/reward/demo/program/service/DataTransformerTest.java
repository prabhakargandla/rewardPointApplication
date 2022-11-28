package reward.demo.program.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import reward.demo.program.model.CustomerDTO;
import reward.demo.program.model.MonthlyRewards;
import reward.demo.program.model.TransactionRecordDTO;
import reward.demo.program.service.DataTransformer;
import reward.demo.program.util.DataBuilder;

class DataTransformerTest {

	private DataTransformer transformer = new DataTransformer();

	private DataBuilder testData = new DataBuilder();

	@Test
	void test_countCustomerTranactions() {
		CustomerDTO mary = testData.createCustomerDTO("Mary");
		mary.setId(1l);
		CustomerDTO tom = testData.createCustomerDTO("Tom");
		tom.setId(2l);

		List<TransactionRecordDTO> transRecords = new ArrayList<>();
		transRecords.add(testData.createTransactionRecordDTO(mary, "20", 2, 2));
		transRecords.add(testData.createTransactionRecordDTO(mary, "120", 1, 12));
		transRecords.add(testData.createTransactionRecordDTO(mary, "100", 3, 0));
		transRecords.add(testData.createTransactionRecordDTO(mary, "40", 1, 20));
		transRecords.add(testData.createTransactionRecordDTO(mary, "200", 2, 22));
		transRecords.add(testData.createTransactionRecordDTO(tom, "20", 2, 5));
		transRecords.add(testData.createTransactionRecordDTO(tom, "120", 1, 7));
		transRecords.add(testData.createTransactionRecordDTO(tom, "100", 3, 0));
		transRecords.add(testData.createTransactionRecordDTO(tom, "40", 1, 9));
		transRecords.add(testData.createTransactionRecordDTO(tom, "200", 2, 15));

		Map<CustomerDTO, List<TransactionRecordDTO>> custTrans = transformer.splitByCustomerTransactions(transRecords);
		assertEquals(2, custTrans.size());
	}

	@Test
	void test_toCustomerModel() {
		CustomerDTO mary = transformer.toCustomerModel(testData.createCustomer("Mary"));
		assertEquals("Mary", mary.getName());
	}

	@Test
	void test_calculateMonthlyReport() {

		CustomerDTO mary = testData.createCustomerDTO("Mary");
		mary.setId(1l);
		List<TransactionRecordDTO> transRecords = new ArrayList<>();
		transRecords.add(testData.createTransactionRecordDTO(mary, "20", 2, 4));
		transRecords.add(testData.createTransactionRecordDTO(mary, "20", 2, 3));
		transRecords.add(testData.createTransactionRecordDTO(mary, "20", 2, 2));
		transRecords.add(testData.createTransactionRecordDTO(mary, "120", 1, 12));
		transRecords.add(testData.createTransactionRecordDTO(mary, "100", 1, 0));
		transRecords.add(testData.createTransactionRecordDTO(mary, "40", 1, 20));
		transRecords.add(testData.createTransactionRecordDTO(mary, "200", 2, 5));
		transRecords.add(testData.createTransactionRecordDTO(mary, "200", 0, 2));
		transRecords.add(testData.createTransactionRecordDTO(mary, "200", 0, 3));
		MonthlyRewards monthRewards = transformer.calculateMonthlyReport(mary, transRecords);
		assertEquals(3, monthRewards.getMonthlyTrans().size());

	}

}
