package reward.demo.program.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reward.demo.program.model.CustomerDTO;
import reward.demo.program.model.TransactionRecordDTO;
import reward.demo.program.repo.CustomerRepository;
import reward.demo.program.repo.TransactionRecordRepository;
import reward.demo.program.service.CustomerService;
import reward.demo.program.service.DataTransformer;
import reward.demo.program.util.DataBuilder;

@Component
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private DataTransformer dataTransformer;

	private DataBuilder testData = new DataBuilder();

	@Autowired
	private TransactionRecordRepository tranRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see zheng.demo.rewardprogram.service.CustomerService#findById(java.lang.Long)
	 */
	@Override
	public CustomerDTO findById(Long custId) {
		Optional<reward.demo.program.entity.Customer> foundCust = custRepo.findById(custId);
		if (foundCust.isPresent()) {
			return dataTransformer.toCustomerModel(foundCust.get());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see zheng.demo.rewardprogram.service.CustomerService#findThreeMonthTrans()
	 */
	@Override
	public List<TransactionRecordDTO> findThreeMonthTrans() {
		List<TransactionRecordDTO> threeMonthTrans = new ArrayList<>();
		setup();
		List<reward.demo.program.entity.TransactionRecord> threeMonthTranRecords = tranRepo
				.findAllInLastThreeMonth();

		threeMonthTranRecords.forEach(source -> {
			TransactionRecordDTO target = new TransactionRecordDTO();
			BeanUtils.copyProperties(source, target);
			target.setCustomer(dataTransformer.toCustomerModel(source.getCustomer()));
			threeMonthTrans.add(target);
		});

		return threeMonthTrans;
	}

	private void setup() {
		if (custRepo.findAll().isEmpty()) {
			reward.demo.program.entity.Customer mary = custRepo.save(testData.createCustomer("Mary"));
			reward.demo.program.entity.Customer tom = custRepo.save(testData.createCustomer("Tom"));

			tranRepo.save(testData.createTransactionRecord(mary, "20", 0, 2));
			tranRepo.save(testData.createTransactionRecord(mary, "120", 1, 2));
			tranRepo.save(testData.createTransactionRecord(mary, "100", 1, 5));
			tranRepo.save(testData.createTransactionRecord(mary, "20", 2, 0));
			tranRepo.save(testData.createTransactionRecord(mary, "60", 2, 4));
			tranRepo.save(testData.createTransactionRecord(mary, "120", 2, 4));

			tranRepo.save(testData.createTransactionRecord(tom, "120", 1, 3));
			tranRepo.save(testData.createTransactionRecord(tom, "20", 2, 1));
			tranRepo.save(testData.createTransactionRecord(tom, "100", 1, 15));
			tranRepo.save(testData.createTransactionRecord(tom, "56", 2, 14));
			tranRepo.save(testData.createTransactionRecord(tom, "110", 0, 15));
			tranRepo.save(testData.createTransactionRecord(tom, "66", 0, 14));
		}
	}

}
