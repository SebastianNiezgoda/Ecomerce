package pl.Sniezgoda.ecomerce.sales;

public class AcceptOfferRequest {
    String firstname;
    String lastname;
    String email;

    public String getFname() {
        return firstname;
    }

    public AcceptOfferRequest setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public AcceptOfferRequest setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AcceptOfferRequest setEmail(String email) {
        this.email = email;
        return this;
    }
}
