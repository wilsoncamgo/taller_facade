package account;

import soft.Component;

public class Candidate extends Account implements Component {

    private String id;
    private String name;

    public Candidate(String email, char[] password, String id, String name) {
        super(email, password);
        this.id = id;
        this.name = name;
    }

    public Candidate(String email, char[] password) {
        this(email, password, "", "");
    }

    @Override
    public String getData() {
        return "\n\nCandidate:\nId: " + id + "\nName: " + name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
