package zheng.demo.rewardprogram.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import zheng.demo.rewardprogram.entity.Customer;
import zheng.demo.rewardprogram.entity.TransactionRecord;
import zheng.demo.rewardprogram.model.CustomerDTO;
import zheng.demo.rewardprogram.model.TransactionRecordDTO;

public class DataBuilder {

	public Customer createCustomer(String custName) {
		Customer mary = new Customer();
		mary.setName(custName);
		return mary;
	}

	public TransactionRecord createTransactionRecord(Customer cust, String amount, int minusMonth, int minusDay) {
		TransactionRecord maryTran1 = new TransactionRecord();
		maryTran1.setCustomer(cust);

		maryTran1.setAmount(new BigDecimal(amount));

		LocalDateTime monthAgo = LocalDateTime.now().minusMonths(minusMonth).minusDays(minusDay);

		maryTran1.setPurchaseDate(monthAgo);
		return maryTran1;
	}
	
	public CustomerDTO createCustomerDTO(String custName) {
		CustomerDTO mary = new CustomerDTO();
		mary.setName(custName);
		mary.setId(1l);
		return mary;
	}

	public TransactionRecordDTO createTransactionRecordDTO(CustomerDTO cust, String amount, int minusMonth, int minusDay) {
		TransactionRecordDTO maryTran1 = new TransactionRecordDTO();
		maryTran1.setCustomer(cust);
		maryTran1.setId(1l);

		maryTran1.setAmount(new BigDecimal(amount));

		LocalDateTime monthAgo = LocalDateTime.now().minusMonths(minusMonth).minusDays(minusDay);

		maryTran1.setPurchaseDate(monthAgo);
		return maryTran1;
	}
	
	
}
