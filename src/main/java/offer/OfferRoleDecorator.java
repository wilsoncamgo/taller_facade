package offer;

public class OfferRoleDecorator extends OfferDecorator {

    private String role;

    public OfferRoleDecorator(Offer offer, String role) {
        super(offer);
        this.role = role;
    }

    @Override
    public String getData() {
        return super.getData() + "\nRole: " + role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
