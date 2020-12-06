package models;

import java.io.Serializable;
import java.util.Objects;

public class BankCard implements Serializable {
    private Long id;
    private Client client;
    private Account account;
    private Long number;
    private Long amount;

    public BankCard(Long id, Client client, Account account, Long number, Long amount) {
        this.id = id;
        this.client = client;
        this.account = account;
        this.number = number;
        this.amount = amount;
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

    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getNumber() {
        return number;
    }
    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankCard bankCard = (BankCard) o;
        return Objects.equals(id, bankCard.id) && Objects.equals(client, bankCard.client) && Objects.equals(account, bankCard.account) && Objects.equals(number, bankCard.number) && Objects.equals(amount, bankCard.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, account, number, amount);
    }

    @Override
    public String toString() {
        return "{ BankCard id= " + id +
                "\nnumber= " + number +
                "\namount= " + amount + " }";
    }
}
