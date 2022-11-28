package reward.demo.program;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reward.demo.program.model.CustomerDTO;
import reward.demo.program.model.MonthlyRewards;
import reward.demo.program.model.TransactionRecordDTO;
import reward.demo.program.service.CustomerService;
import reward.demo.program.service.DataTransformer;

@RestController
@RequestMapping("/api/v1")
public class RewardPointResouce {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private DataTransformer dataTransformer;

	@GetMapping("rewards/customer/{customerId}")
	public MonthlyRewards rewardsByCustomer(@PathVariable(value = "customerId") Long customerId) {
		MonthlyRewards monthlyReward = null;
		CustomerDTO cust = customerService.findById(customerId);

		List<TransactionRecordDTO> threeMonthTrans = customerService.findThreeMonthTrans();
		Map<CustomerDTO, List<TransactionRecordDTO>> custTrans = dataTransformer.splitByCustomerTransactions(threeMonthTrans);
		if (custTrans.containsKey(cust)) {
			monthlyReward = dataTransformer.calculateMonthlyReport(cust, custTrans.get(cust));
		}

		return monthlyReward;

	}

	@GetMapping("/rewards")
	public List<MonthlyRewards> monthlyRewardsReport() {

		List<MonthlyRewards> ret = new ArrayList<>();

		List<TransactionRecordDTO> threeMonthTrans = customerService.findThreeMonthTrans();

		Map<CustomerDTO, List<TransactionRecordDTO>> custTrans = dataTransformer.splitByCustomerTransactions(threeMonthTrans);

		custTrans.forEach((k, v) -> {
			MonthlyRewards monthlyReward = dataTransformer.calculateMonthlyReport(k, v);

			ret.add(monthlyReward);

		});

		return ret;
	}

}