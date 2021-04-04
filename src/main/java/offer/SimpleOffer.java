package offer;

public class SimpleOffer implements Offer {

    private String code;

    public SimpleOffer(String code) {
        this.code = code;
    }

    @Override
    public String getData() {
        return "\n\nOffer:\nCode: " + code;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }
    
}
