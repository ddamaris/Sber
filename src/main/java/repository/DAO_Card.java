package repository;

import models.BankCard;
import repository.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAO_Card implements DAO<BankCard> {
    private List<BankCard> cards = new ArrayList<>();

    @Override
    public Optional<BankCard> get(long id) {
        return Optional.ofNullable(cards.get((int) id));
    }

    @Override
    public List<BankCard> getAll() {
        return cards;
    }

    @Override
    public void add(BankCard card) {
        cards.add(card);
    }

    @Override
    public void delete(BankCard card) {
        cards.remove(card);
    }


}
