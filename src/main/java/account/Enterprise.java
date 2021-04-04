package account;
import java.util.LinkedList;

import soft.Component;

public class Enterprise extends Account implements Component {

    private String nit;
    private String name;
    private String address;

    private LinkedList<Component> children;

    public Enterprise(String email, char[] password, String nit, String name, String address) {
        super(email, password);
        this.nit = nit;
        this.name = name;
        this.address = address;
        children = new LinkedList<>();
    }

    public Enterprise(String email, char[] password) {
        this(email, password, "", "", "");
    }

    public String getData() {
        String str = "\n\nEnterprise:\nNit: " + nit + "\nName: " + name + "\nAddress: " + address;
        for (Component child : children) str += child.getData();
        return str;
    }

    public String getNit() {
        return nit;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean add(Component child) {
        return children.add(child);
    }

    public boolean remove(Component child) {
        return children.remove(child);
    }

    public LinkedList<Component> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<Component> children) {
        this.children = children;
    }

}