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

import static fd.se.btsplus.model.consts.Constant.ASC;
import static fd.se.btsplus.model.consts.Constant.DESC;
import static fd.se.btsplus.service.IDateService.dayAfter;
import static fd.se.btsplus.service.IDateService.dayBefore;

@Slf4j
@AllArgsConstructor
@Service
public class TransactionsService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> query(Integer pageNum, Integer pageSize,
                                   String accountNum, String transactionNum, String transactionCode,
                                   String orderBy, Date beginDate, Date endDate) {


        String finalOrderBy = finalOrderBy(orderBy);
        Stream<Transaction> stream = transactionRepository.findAll().stream();
        stream = stream.filter(tx ->
                accountNumFilter(tx, accountNum)
                        && transactionNumFilter(tx, transactionNum)
                        && transactionCodeFilter(tx, transactionCode)
                        && beginDateFilter(tx, beginDate)
                        && endDateFilter(tx, endDate)
        );
        stream = streamSorted(stream, finalOrderBy);

        if (pageNum == null && pageSize == null) {
            return stream.collect(Collectors.toList());
        }
        else if (pageNum == null || pageSize == null || pageNum <= 0 || pageSize <= 0) {
            return Collections.emptyList();
        }

        stream = stream.skip((long) (pageNum - 1) * pageSize).limit(pageSize);
        return stream.collect(Collectors.toList());
    }
    private String finalOrderBy(String orderBy){
        if (orderBy == null) {
            return ASC;
        }
        orderBy = orderBy.toLowerCase();
        return orderBy.equals(DESC) ? DESC : ASC;
    }
    private Stream<Transaction> streamSorted(Stream<Transaction> stream, String finalOrderBy){
        return stream.sorted((tx1, tx2) -> {
            int result;
            if (tx1.getOperatedTime() == null) {
                result = 1;
            }
            else if (tx2.getOperatedTime() == null) {
                result = -1;
            }
            else {
                result = tx1.getOperatedTime().compareTo(tx2.getOperatedTime());
            }
            if (!finalOrderBy.equals(ASC)) {
                result = -result;
            }
            return result;
        });
    }
    private boolean accountNumFilter(Transaction tx, String accountNum){
        Account ac;
        String acNum;
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
        return true;
    }
    private boolean transactionNumFilter(Transaction tx, String transactionNum){
        if (transactionNum != null) {
            return transactionNum.equals(tx.getTransactionNum());
        }
        return true;
    }
    private boolean transactionCodeFilter(Transaction tx, String transactionCode){
        if (transactionCode != null) {
            return transactionCode.equals(tx.getTransactionCode());
        }
        return true;
    }
    private boolean beginDateFilter(Transaction tx, Date beginDate){
        Date txDate;
        if (beginDate != null) {
            if ((txDate = tx.getOperatedTime()) == null) {
                return false;
            }
            return !dayBefore(txDate, beginDate);
        }
        return true;
    }
    private boolean endDateFilter(Transaction tx, Date endDate){
        Date txDate;
        if (endDate != null) {
            if ((txDate = tx.getOperatedTime()) == null) {
                return false;
            }
            return !dayAfter(txDate, endDate);
        }
        return true;
    }
}
