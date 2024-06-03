package pl.Sniezgoda.ecomerce.sales;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class SalesTest {
    @Test
    void idAllowsToAddProductToCart(){
        SalesFacade sales = thereIsSalesFacade();
        String customerId = thereIsCustomer("kuba");
        String productId = thereIsProduct("x",BigDecimal.valueOf(10.10));

        sales.addProductToCart(customerId,productId);

        Offer offer = sales.getCurrentOffer(customerId);

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(10.10));
        assertThat(offer.getItemsCount()).isEqualTo(1);

    }

    private String thereIsProduct(String name, BigDecimal bigDecimal) {
        return null;
    }


    @Test
    void itAllowsToRemoveProductFromCart(){

    }


    @Test
    void itShowsCurrentOffer(){
        SalesFacade sales = thereIsSalesFacade();
        String customerId = thereIsCustomer("Kuba");

        Offer offer = sales.getCurrentOffer(customerId);
        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(0));
        assertThat(offer.getItemsCount()).isEqualTo(0);
    }

    private String thereIsCustomer(String name){
        return name;

    };

    private SalesFacade thereIsSalesFacade(){
        return new SalesFacade();
    };
}
