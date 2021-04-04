package offer;

public class OfferDescriptionDecorator extends OfferDecorator {

    private String description;

    public OfferDescriptionDecorator(Offer offer, String description) {
        super(offer);
        this.description = description;
    }

    @Override
    public String getData() {
        return super.getData() + "\nDescription: " + description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
