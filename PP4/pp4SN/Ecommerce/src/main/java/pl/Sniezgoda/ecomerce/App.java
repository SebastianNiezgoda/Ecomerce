package pl.Sniezgoda.ecomerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.Sniezgoda.ecomerce.katalog.ArrayListProductStorage;
import pl.Sniezgoda.ecomerce.katalog.ProductCatalog;
import pl.Sniezgoda.ecomerce.sales.SalesFacade;
import pl.Sniezgoda.ecomerce.sales.cart.HashMapCartStorage;
import pl.Sniezgoda.ecomerce.sales.offering.OfferCalculator;
import pl.Sniezgoda.ecomerce.sales.payment.PaymentDetails;
import pl.Sniezgoda.ecomerce.sales.payment.PaymentGateway;
import pl.Sniezgoda.ecomerce.sales.payment.RegisterPaymentRequest;
import pl.Sniezgoda.ecomerce.sales.reservation.ReservationRepository;

@SpringBootApplication

public class App {
    public static void main(String[] args){
        System.out.println("witam");
        SpringApplication.run(App.class,args);
    }
    @Bean
    ProductCatalog createMyCatalog(){
        var catalog = new ProductCatalog(new ArrayListProductStorage());
        catalog.addProduct("100 Smoczych monet","kox");
        catalog.addProduct("200 Smoczych monet","giga kox");
        return catalog;
    }

    @Bean
    SalesFacade createSales()
        {

                return new SalesFacade(
                new HashMapCartStorage(),
                new OfferCalculator(),
                new PaymentGateway() {
                    @Override
                    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
                        return null;
                    }
                },
                new ReservationRepository()
        );
    }
}
