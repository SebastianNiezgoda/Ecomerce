package pl.Sniezgoda.ecomerce.sales;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.Sniezgoda.ecomerce.sales.offering.Offer;
import pl.Sniezgoda.ecomerce.sales.reservation.AcceptOfferRequest;
import pl.Sniezgoda.ecomerce.sales.reservation.ReservationDetails;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalesHTTPTest {
    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate http;

    @Test
    void itAllowsToAcceptOfferHappyPath() {
        var productId = thereIsProduct("product-x");
        var addProductToCartUrl =  String.format(
                "http://localhost:%s/api/cart/add-product/%s",
                port,
                productId);
        ResponseEntity<Void> addProductResponse =
                http.postForEntity(addProductToCartUrl,null, Void.class);

        var getCurrentOfferUrl = String.format(
                "http://localhost:%s/api/current-offer", port);
        ResponseEntity<Offer> offerResponse = http.getForEntity(getCurrentOfferUrl, Offer.class);

        var acceptOfferUrl = String.format(
                "http://localhost:%s/api/accept-offer", port
        );

        var acceptOfferRequest = new AcceptOfferRequest();
        acceptOfferRequest
                .setFirstname("John")
                .setLastname("Dooe")
                .setEmail("john.doe@example.com");

        ResponseEntity<ReservationDetails> acceptOferrResponse = http.postForEntity(
                acceptOfferUrl,acceptOfferRequest,ReservationDetails.class
        );

        assertEquals(HttpStatus.OK ,addProductResponse.getStatusCode());
        assertEquals(HttpStatus.OK ,offerResponse.getStatusCode());
        assertEquals(HttpStatus.OK ,acceptOferrResponse.getStatusCode());
        assertNotNull(acceptOferrResponse.getBody().getPaymentUrl());
        assertNotNull(acceptOferrResponse.getBody().getReservationId());
    }



    private String thereIsProduct(String id) {
        return id;
    }
}
