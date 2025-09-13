package fr.emse.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MoneyBag implements IMoney {
    private List<Money> monies = new ArrayList<>();

    public MoneyBag(Money m1, Money m2) {
        appendMoney(m1);
        appendMoney(m2);
        simplify();
    }

    public MoneyBag(Money[] bag) {
        for (Money money : bag) {
            appendMoney(money);
        }
        simplify();
    }

    private void appendMoney(Money m) {
        boolean found = false;
        for (int i = 0; i < monies.size(); i++) {
            Money existing = monies.get(i);
            if (existing.currency().equals(m.currency())) {
                int newAmount = existing.amount() + m.amount();
                if (newAmount == 0) {
                    monies.remove(i);
                } else {
                    monies.set(i, new Money(newAmount, m.currency()));
                }
                found = true;
                break;
            }
        }
        if (!found && m.amount() != 0) {
            monies.add(m);
        }
    }

    private void simplify() {
        // Si le MoneyBag ne contient qu'une seule monnaie, on pourrait le convertir en Money
        // Mais nous laisserons cette logique pour plus tard
    }

    public List<Money> getMonies() {
        return new ArrayList<>(monies);
    }

    @Override
    public IMoney add(IMoney m) {
        return m.addMoneyBag(this);
    }

    @Override
    public IMoney addMoney(Money money) {
        MoneyBag result = new MoneyBag(this.monies.toArray(new Money[0]));
        result.appendMoney(money);
        return result.simplifyToIMoney();
    }

    @Override
    public IMoney addMoneyBag(MoneyBag moneyBag) {
        MoneyBag result = new MoneyBag(this.monies.toArray(new Money[0]));
        for (Money money : moneyBag.monies) {
            result.appendMoney(money);
        }
        return result.simplifyToIMoney();
    }

    private IMoney simplifyToIMoney() {
        if (monies.size() == 1) {
            return monies.get(0);
        }
        if (monies.isEmpty()) {
            return new Money(0, "USD"); // Devise par défaut
        }
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MoneyBag moneyBag = (MoneyBag) obj;
        if (monies.size() != moneyBag.monies.size()) return false;
        
        for (Money money : monies) {
            boolean found = false;
            for (Money otherMoney : moneyBag.monies) {
                if (money.equals(otherMoney)) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(monies);
    }

    @Override
    public String toString() {
        return "MoneyBag" + monies;
    }
}