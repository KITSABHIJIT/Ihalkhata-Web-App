import java.text.ParseException;


import com.cemk.exp.dataentryservice.implementation.DataEntryServiceImpl;
import com.cemk.exp.dataentryservice.interfaces.DataEntryService;
import com.cemk.exp.dataentryservice.interfaces.DataEntryServiceException;
import com.cemk.exp.dataentryservice.interfaces.PaymentDTO;
import com.cemk.exp.date.util.StringToSQLDate;

public class TestPayment {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DataEntryService paymentService = new DataEntryServiceImpl();
		PaymentDTO payData = new PaymentDTO();

		payData.setGiver("sandy");
		//payData.setGiver("poka");
		//payData.setGiver("roy");

		//payData.setReceiver("sandy");
		payData.setReceiver("arani");

		payData.setAmount(1081.00);

		try {
			payData.setDate(StringToSQLDate.toSQLDate("21/08/2011"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if(paymentService.makePayment(payData)){
				System.out.println("Paid successfully");
			}else{
				System.out.println("Couldn't make the payment");
			}
		} catch (DataEntryServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
