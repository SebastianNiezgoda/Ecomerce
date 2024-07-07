package pl.Sniezgoda.ecomerce.sales.reservation;

public class AcceptOfferRequest {
    static String firstname;
    static String lastname;
    String email;

    public static String getFname() {
        return firstname;
    }

    public AcceptOfferRequest setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public static String getLname() {
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
