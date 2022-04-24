package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            if (!users.get(user).contains(account)) {
                users.get(user).add(account);
            }
        }
    }

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

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account accFrom = findByRequisite(srcPassport, srcRequisite);
        Account accTo = findByRequisite(destPassport, destRequisite);
        if ((findByPassport(srcPassport) != null) && findByPassport(destPassport) != null
                && accTo != null && accFrom != null && (accFrom.getBalance() >= amount)) {
            accFrom.setBalance(accFrom.getBalance() - amount);
            accTo.setBalance(accTo.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}