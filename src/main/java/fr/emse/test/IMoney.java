package fr.emse.test;

public interface IMoney {
    IMoney add(IMoney m);
    IMoney addMoney(Money money);
    IMoney addMoneyBag(MoneyBag moneyBag);
}