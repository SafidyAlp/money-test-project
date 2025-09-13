package fr.emse.test;

import java.util.Objects;

public class Money implements IMoney {
    private int amount;
    private String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public int amount() {
        return amount;
    }

    public String currency() {
        return currency;
    }

    @Override
    public IMoney add(IMoney m) {
        return m.addMoney(this);
    }

    @Override
    public IMoney addMoney(Money money) {
        if (money.currency().equals(currency)) {
            return new Money(amount + money.amount(), currency);
        }
        return new MoneyBag(this, money);
    }

    @Override
    public IMoney addMoneyBag(MoneyBag moneyBag) {
        return moneyBag.addMoney(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return amount == money.amount && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return "Money{" + amount + " " + currency + "}";
    }
}