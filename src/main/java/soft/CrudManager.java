package soft;

import java.util.LinkedList;

import account.Account;
import account.Candidate;
import account.Enterprise;
import offer.Offer;
import offer.OfferDescriptionDecorator;
import offer.OfferRoleDecorator;
import offer.OfferSalaryDecorator;
import offer.SimpleOffer;

public class CrudManager {
    
    private static CrudManager instance;

    public static CrudManager getInstance() {
        if (instance == null)
            instance = new CrudManager();
        return instance;
    }

    private final LinkedList<Candidate> candidates;
    private final LinkedList<Enterprise> enterprises;

    private CrudManager() {
        candidates = new LinkedList<>();
        enterprises = new LinkedList<>();
    }

    public Candidate createCandidate(String email, char[] password,
            String id, String name) {
        if (!Account.emailValid(email) || !Account.passwordValid(password)) return null;
        if (existAccount(email) || existCandidate(id)) return null;
        Candidate candidate = new Candidate(email, password, id, name);
        candidates.add(candidate);
        return candidate;
    }

    public Enterprise createEnterprise(String email, char[] password,
            String nit, String name, String address) {
        if (existAccount(email) || existEnterprise(nit)) return null;
        Enterprise enterprise = new Enterprise(email, password, nit, name, address);
        enterprises.add(enterprise);
        return enterprise;
    }

    public Offer createOffer(Enterprise enterprise, String code, String role,
            String salary, String description) {
        if (enterprise == null || existOffer(enterprise, code)) return null;
        Offer offer = new OfferDescriptionDecorator(
            new OfferSalaryDecorator(
                new OfferRoleDecorator(
                    new SimpleOffer(code),
                    role
                ),
                salary
            ),
            description
        );
        enterprise.add(offer);
        return offer;
    }

    public String readCandidate(String id) {
        Candidate candidate = getCandidate(id);
        if (candidate == null) return null;
        return candidate.getData();
    }

    public String readEnterprise(String nit) {
        Enterprise enterprise = getEnterprise(nit);
        if (enterprise == null) return null;
        return enterprise.getData();
    }

    public String readOffer(Enterprise enterprise, String code) {
        Offer offer = getOffer(enterprise, code);
        if (offer == null) return null;
        return offer.getData();
    }

    public Candidate updateCandidate(String id, char[] password, Candidate newData) {
        if (newData == null) return null;
        Candidate candidate = getCandidate(id);
        if (candidate == null) return null;
        if (!candidate.checkPassword(password)) return null;
        if (newData.getEmail() != null
                && !candidate.checkEmail(newData.getEmail())
                && Account.emailValid(newData.getEmail())
                && !existAccount(newData.getEmail())) {
            candidate.setEmail(newData.getEmail());
        }
        if (newData.getPassword() != null
                && !candidate.checkPassword(newData.getPassword())
                && Account.passwordValid(newData.getPassword())) {
            candidate.setPassword(newData.getPassword());
        }
        if (newData.getId() != null
                && !candidate.getId().equals(newData.getId())
                && !existCandidate(newData.getId())) {
            candidate.setId(newData.getId());
        }
        if (newData.getName() != null
                && !candidate.getName().equals(newData.getName())) {
            candidate.setName(newData.getName());
        }
        return candidate;
    }

    public Enterprise updateEnterprise(String nit, char[] password, Enterprise newData) {
        if (newData == null) return null;
        Enterprise enterprise = getEnterprise(nit);
        if (enterprise == null) return null;
        if (!enterprise.checkPassword(password)) return null;
        if (newData.getEmail() != null
                && !enterprise.checkEmail(newData.getEmail())
                && Account.emailValid(newData.getEmail())
                && !existAccount(newData.getEmail())) {
            enterprise.setEmail(newData.getEmail());
        }
        if (newData.getPassword() != null
                && !enterprise.checkPassword(newData.getPassword())
                && Account.passwordValid(newData.getPassword())) {
            enterprise.setPassword(newData.getPassword());
        }
        if (newData.getNit() != null
                && !enterprise.getNit().equals(newData.getNit())
                && !existEnterprise(newData.getNit())) {
            enterprise.setNit(newData.getNit());
        }
        if (newData.getName() != null
                && !enterprise.getName().equals(newData.getName())) {
            enterprise.setName(newData.getName());
        }
        if (newData.getAddress() != null
                && !enterprise.getAddress().equals(newData.getAddress())) {
            enterprise.setAddress(newData.getAddress());
        }
        return enterprise;
    }

    public Offer updateOffer(Enterprise enterprise, String code, Offer newData) {
        if (enterprise == null || newData == null) return null;
        Offer offer = getOffer(enterprise, code);
        if (offer == null) return null;
        if (newData.getCode() != null
                && !offer.getCode().equals(newData.getCode())) {
            offer.setCode(newData.getCode());
        }
        return offer;
    }

    public boolean deleteCandidate(String id) {
        Candidate candidate = getCandidate(id);
        if (candidate == null) return false;
        return candidates.remove(candidate);
    }

    public boolean deleteEnterprise(String nit) {
        Enterprise enterprise = getEnterprise(nit);
        if (enterprise == null) return false;
        return enterprises.remove(enterprise);
    }

    public boolean deleteOffer(Enterprise enterprise, String code) {
        if (enterprise == null) return false;
        Offer offer = getOffer(enterprise, code);
        if (offer == null) return false;
        return enterprise.remove(offer);
    }

    public Account login(String email, char[] password) {
        Account account = getAccount(email);
        if (account == null) return null;
        if (account.checkPassword(password)) return account;
        return null;
    }

    private Candidate getCandidate(String id) {
        for (Candidate candidate : candidates) {
            if (candidate.getId().equals(id)) return candidate;
        }
        return null;
    }

    private Enterprise getEnterprise(String nit) {
        for (Enterprise enterprise : enterprises) {
            if (enterprise.getNit().equals(nit)) return enterprise;
        }
        return null;
    }

    private Offer getOffer(Enterprise enterprise, String code) {
        if (enterprise == null) return null;
        for (Component component : enterprise.getChildren()) {
            if (component instanceof Offer) {
                Offer offer = (Offer) component;
                if (offer.getCode().equals(code)) return offer;
            }
        }
        return null;
    }

    private boolean existCandidate(String id) {
        return getCandidate(id) != null;
    }

    private boolean existEnterprise(String nit) {
        return getEnterprise(nit) != null;
    }

    private boolean existOffer(Enterprise enterprise, String code) {
        return getOffer(enterprise, code) != null;
    }

    private Account getAccount(String email) {
        for (Candidate candidate : candidates) {
            if (candidate.getEmail().equals(email)) return candidate;
        }
        for (Enterprise enterprise : enterprises) {
            if (enterprise.getEmail().equals(email)) return enterprise;
        }
        return null;
    }

    private boolean existAccount(String email) {
        return getAccount(email) != null;
    }

}
