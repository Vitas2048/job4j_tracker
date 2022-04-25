package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает работу банковской системы перевода средств с одного счета
 * на другой
 * @author Vitaly Khagai
 * @version 1.0
 */
public class BankService {
    /**
     * Список пользователей в базе
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет нового пользователя, если его нет в базе
     * @param user класс, который добавляется в базу
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод добавляет аккаунт в базу, если пользователь с таким паспортом существует
     * @param passport номер паспорта
     * @param account имя аккаунта
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            if (!users.get(user).contains(account)) {
                users.get(user).add(account);
            }
        }
    }

    /**
     * Метод находит пользователя по паспорту, если такого пользователя нету,
     * возвращает null
     * @param passport номер паспорта
     * @return возвращает найденного по паспорту пользователя
     */
    public User findByPassport(String passport) {
        User ruser = null;
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                ruser = user;
                break;
            }
        }
        return ruser;
    }

    /**
     * Метод находит по реквизитам и паспорту аккаунт, в случае ненахождения
     * возвращает null
     * @param passport номер паспорта
     * @param requisite реквизиты
     * @return возвращает аккаунт по заданным реквизитам
     */
    public Account findByRequisite(String passport, String requisite) {
        Account adaccount = null;
        User passUser = findByPassport(passport);
        if (passUser != null) {
            for (Account account : users.get(passUser)) {
                if (account.getRequisite().equals(requisite)) {
                    adaccount = account;
                    break;
                }
            }
        }
        return adaccount;
    }

    /**
     * метод переводит сумму с одного аккаунта в другой
     * @param srcPassport номер паспорта отправителя
     * @param srcRequisite реквизиты отправителя
     * @param destPassport номер паспорта получателя
     * @param destRequisite реквизиты получателя
     * @param amount сумма перевода
     * @return возвращает true если перевод выполнен, false, если перевод невозможен
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account accFrom = findByRequisite(srcPassport, srcRequisite);
        Account accTo = findByRequisite(destPassport, destRequisite);
        if (accTo != null && accFrom != null && (accFrom.getBalance() >= amount)) {
            accFrom.setBalance(accFrom.getBalance() - amount);
            accTo.setBalance(accTo.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}