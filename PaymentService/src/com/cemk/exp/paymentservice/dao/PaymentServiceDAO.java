package com.cemk.exp.paymentservice.dao;

import com.cemk.exp.paymentservice.interfaces.PaymentDTO;

public interface PaymentServiceDAO {
	
	public boolean makePayment(PaymentDTO paymentData);
}
