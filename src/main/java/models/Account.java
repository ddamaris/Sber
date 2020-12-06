package models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Account implements Serializable {
    private Long id;
    private Client client;
    private Long accNumber;
    private Long amount;
    private List<BankCard> bankCardList;

    public Account(Long id, Client client, Long amount, List<BankCard> bankCardList) {
        this.id = id;
        this.client = client;
        this.amount = amount;
        this.bankCardList = bankCardList;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public Long getAccNumber() { return accNumber; }
    public void setAccNumber(Long acc_number) { this.accNumber = acc_number; }

    public List<BankCard> getBankCardList() {
        return bankCardList;
    }
    public void setBankCardList(List<BankCard> bankCardList) {
        this.bankCardList = bankCardList;
    }

    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(client, account.client) && Objects.equals(accNumber, account.accNumber) && Objects.equals(amount, account.amount) && Objects.equals(bankCardList, account.bankCardList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, accNumber, amount, bankCardList);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", client=" + client +
                ", accNumber=" + accNumber +
                ", amount=" + amount +
                ", bankCardList=" + bankCardList +
                '}';
    }
}
