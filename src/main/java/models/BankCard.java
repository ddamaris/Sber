package models;

import java.io.Serializable;
import java.util.Objects;

public class BankCard implements Serializable {
    private Integer id;
    private Integer ownerId;
    private Integer cardAccId;
    private String number;
    private Integer amount;

    public BankCard(Integer id, Integer ownerId, Integer cardAccId, String number, Integer amount) {
        this.id = id;
        this.ownerId = ownerId;
        this.cardAccId = cardAccId;
        this.number = number;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Client client) { this.ownerId = ownerId; }

    public Integer getCardAccId() {
        return cardAccId;
    }
    public void setAccount(Account account) {
        this.cardAccId = cardAccId;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankCard bankCard = (BankCard) o;
        return Objects.equals(id, bankCard.id) && Objects.equals(ownerId, bankCard.ownerId) &&
               Objects.equals(cardAccId, bankCard.cardAccId) && Objects.equals(number, bankCard.number) &&
               Objects.equals(amount, bankCard.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, cardAccId, number, amount);
    }

    @Override
    public String toString() {
        return "\n{\n\"id\": " + id +
                "\n\"Account ID\": " + cardAccId +
                "\n\"number\": " + number +
                "\n\"amount\": " + amount + "\n}";
    }
}
