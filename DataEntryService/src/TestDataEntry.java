import java.util.ArrayList;

import com.cemk.exp.dataentryservice.interfaces.ExpenditureDTO;

public class TestDataEntry {

	private static void roundOff(ExpenditureDTO expDTO) {

		Double roundedOffAmount = Double.valueOf(expDTO.getPrice()
				/ expDTO.getNoOfShareholder());
		double paisa = roundedOffAmount - roundedOffAmount.longValue();
		System.out.println("paisa : " + paisa);
		System.out.println("long value :" + roundedOffAmount.longValue());

		if (paisa < 0.50 && paisa > 0) {
			roundedOffAmount = roundedOffAmount + (0.50 - paisa);
		} else if (paisa > 0.50) {
			roundedOffAmount = roundedOffAmount + ((100 - (paisa * 100)) * .01);
		}

		expDTO.setPrice(roundedOffAmount * expDTO.getNoOfShareholder());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExpenditureDTO expDTO = new ExpenditureDTO();
		ArrayList<String> shareholderList = new ArrayList<String>();

		// shareholderList.add("sandy");
		shareholderList.add("roy");
		shareholderList.add("poka");
		shareholderList.add("arani");

		// expDTO.setCreatorId("poka");
		expDTO.setCreatorId("arani");
		// expDTO.setCreatorId("sandy");
		// expDTO.setCreatorId("roy");

		expDTO.setItem("Daily Expenses");
		// expDTO.setItem("Transportation");
		// expDTO.setItem("Dinner@Sanjog");
		// expDTO.setItem("Lunch");
		// expDTO.setItem("Dinner");
		// expDTO.setItem("Dinner@OmSai");
		// expDTO.setItem("Marketing");
		// expDTO.setItem("Travel");
		// expDTO.setItem("Tea");
		// expDTO.setItem("Movie Ticket");
		// expDTO.setItem("McDonalds");
		// expDTO.setItem("KFC");
		// expDTO.setItem("Ice Cream");
		// expDTO.setItem("Shirt");
		// expDTO.setItem("T-Shirt");
		// expDTO.setItem("Adjustment");
		// expDTO.setItem("Cable Bill");
		// expDTO.setItem("Electric Bill");
		// expDTO.setItem("Internet Bill");
		// expDTO.setItem("Mobile Recharge");
		// expDTO.setItem("Rail Reservation");
		// expDTO.setItem("Shopping");
		// expDTO.setItem("Hair Cut");
		// expDTO.setItem("Bus Fare");
		// expDTO.setItem("Cigarette");
		// expDTO.setItem("Snacks");
		// expDTO.setItem("Maintainence");
		// expDTO.setItem("Miscelleneous");

		expDTO.setNoOfShareholder(3);
		expDTO.setPrice(11.00);
		// expDTO.setItemDesc("Jeans");
		expDTO.setShareholderList(shareholderList);

		roundOff(expDTO);
		System.out.println("per head cost " + expDTO.getPrice()
				/ expDTO.getNoOfShareholder());

		System.out.println("Total cost " + expDTO.getPrice());

	}

}
