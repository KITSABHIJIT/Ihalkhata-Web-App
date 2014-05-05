import com.cemk.exp.calculationservice.implementations.CalculatorServiceImpl;
import com.cemk.exp.calculationservice.interfaces.CalculationServiceException;
import com.cemk.exp.calculationservice.interfaces.CalculatorService;


public class TestClaculation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CalculatorService calService = new CalculatorServiceImpl();
		int groupId=100;
		try {
			calService.doCalculationPayments(groupId);
			calService.doCalculationPaymentDetails(groupId);
		} catch (CalculationServiceException e) {
		
			e.printStackTrace();
		}
		
	}

}
