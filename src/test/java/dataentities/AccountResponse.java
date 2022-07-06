package dataentities;

import java.util.List;

public class AccountResponse {

    private List<Account> accounts;

    public AccountResponse() {
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
