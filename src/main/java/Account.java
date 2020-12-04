import java.util.List;
import java.util.Objects;

public class Account {
    private Long id;
    private Client client;
    private List<BankCard> bankCardList;

    public Account(Long id, Client client, List<BankCard> bankCardList) {
        this.id = id;
        this.client = client;
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

    public List<BankCard> getBankCardList() {
        return bankCardList;
    }

    public void setBankCardList(List<BankCard> bankCardList) {
        this.bankCardList = bankCardList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(client, account.client) && Objects.equals(bankCardList, account.bankCardList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, bankCardList);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", client=" + client +
                ", bankCardList=" + bankCardList +
                '}';
    }
}
