package offer;

public class OfferDecorator implements Offer {

    private Offer offer;

    public OfferDecorator(Offer offer) {
        this.offer = offer;
    }

    @Override
    public String getData() {
        return offer.getData();
    }

    @Override
    public String getCode() {
        return offer.getCode();
    }

    @Override
    public void setCode(String code) {
        offer.setCode(code);
    }
    
}
