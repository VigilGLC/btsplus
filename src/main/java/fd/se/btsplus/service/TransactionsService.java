package fd.se.btsplus.service;

import fd.se.btsplus.model.entity.bts.Account;
import fd.se.btsplus.model.entity.bts.Transaction;
import fd.se.btsplus.repository.bts.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@AllArgsConstructor
@Service
public class TransactionsService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> query(Integer pageNum, Integer pageSize,
                                   String accountNum, String transactionNum, String transactionCode,
                                   String orderBy, Date beginDate, Date endDate) {
        if (orderBy == null) {
            orderBy = "asc";
        }
        orderBy = orderBy.toLowerCase();
        switch (orderBy) {
            case "asc":
            case "desc":
                break;
            default:
                orderBy = "asc";
        }

        String finalOrderBy = orderBy;
        Stream<Transaction> stream = transactionRepository.findAll().stream();
        stream = stream.filter(tx -> {
            Account ac;
            String acNum;
            Date txDate;

            if (accountNum != null) {
                if ((ac = tx.getAccount()) == null) {
                    return false;
                }
                if ((acNum = ac.getAccountNum()) == null) {
                    return false;
                }
                if (!acNum.equals(accountNum)) {
                    return false;
                }
            }
            if (transactionNum != null) {
                if (!transactionNum.equals(tx.getTransactionCode())) {
                    return false;
                }
            }
            if (transactionCode != null) {
                if (!transactionCode.equals(tx.getTransactionCode())) {
                    return false;
                }
            }
            if (beginDate != null) {
                if ((txDate = tx.getOperatedTime()) == null) {
                    return false;
                }
                if (txDate.before(beginDate)) {
                    return false;
                }
            }
            if (endDate != null) {
                if ((txDate = tx.getOperatedTime()) == null) {
                    return false;
                }
                return !txDate.before(endDate);
            }
            return true;
        });
        stream = stream.sorted((tx1, tx2) -> {
            int result;
            if (tx1.getOperatedTime() == null) {
                result = 1;
            } else if (tx2.getOperatedTime() == null) {
                result = -1;
            } else {
                result = tx1.getOperatedTime().compareTo(tx2.getOperatedTime());
            }
            if (!finalOrderBy.equals("asc")) {
                result = -result;
            }
            return result;
        });

        if (pageNum == null && pageSize == null) {
            return stream.collect(Collectors.toList());
        } else if (pageNum == null || pageSize == null) {
            return Collections.emptyList();
        }

        stream = stream.skip((long) (pageNum - 1) * pageSize).limit(pageSize);
        return stream.collect(Collectors.toList());
    }


}
