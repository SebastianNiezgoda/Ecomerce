package pl.Sniezgoda.ecomerce.sales.payment;
import pl.Sniezgoda.ecomerce.sales.payment.PaymentDetails;
public interface PaymentGateway {
    PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest);
}
