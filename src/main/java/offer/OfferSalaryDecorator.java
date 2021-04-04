package offer;

public class OfferSalaryDecorator extends OfferDecorator {

    private String salary;

    public OfferSalaryDecorator(Offer offer, String salary) {
        super(offer);
        this.salary = salary;
    }

    @Override
    public String getData() {
        return super.getData() + "\nSalary: " + salary;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
    
}
