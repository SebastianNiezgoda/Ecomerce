package pl.Sniezgoda.ecomerce.sales;


import org.springframework.web.bind.annotation.*;


@RestController
public class SalesController {

    SalesFacade sales;
    private AcceptOfferRequest request;

    public SalesController(SalesFacade sales) {
        
        this.sales = sales;
    }
    @PostMapping("/api/add-product/{productId}")
    void addProduct(@PathVariable String productId){
        String customerId = getCurrentCustomerID();
        sales.addProduct(customerId,productId);
    }
    
    @PostMapping("/api/accept-offer")
    ReservationDetails acceptOffer(){
        String customerId = getCurrentCustomerID();
        
        return sales.acceptOffer(customerId, request);
    }

    @GetMapping("/api/current-offer")
    Offer getCurrentOffer(){
        var customerId = getCurrentCustomerID();
        return sales.getCurrentOffer(customerId);
    }

    private String getCurrentCustomerID() {
        return "guest";
    }


}
