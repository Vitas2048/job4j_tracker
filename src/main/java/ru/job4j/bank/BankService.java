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
        if (findByPassport(passport) != null) {
            if (users.get(findByPassport(passport)).size() > 0) {
                for (Account account1 : users.get(findByPassport(passport))) {
                    if (!account.equals(account1)) {
                        users.get(findByPassport(passport)).add(account);
                        break;
                    }
                }
            } else {
                users.get(findByPassport(passport)).add(account);
            }
        }
    }

    public User findByPassport(String passport) {
        User ruser = null;
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                ruser = user;
            }
            break;
        }
        return ruser;
    }

    public Account findByRequisite(String passport, String requisite) {
        Account adaccount = null;
        if (findByPassport(passport) != null) {
            for (Account account : users.get(findByPassport(passport))) {
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
        if ((findByPassport(srcPassport) != null) && (findByPassport(destPassport) != null)
        && (findByRequisite(srcPassport, srcRequisite).getBalance() >= amount)) {
            findByRequisite(srcPassport, srcRequisite).setBalance(
                    findByRequisite(srcPassport, srcRequisite).getBalance() - amount);
            findByRequisite(destPassport, destRequisite).setBalance(
                    findByRequisite(destPassport, destRequisite).getBalance() + amount);
        }
        return rsl;
    }
}