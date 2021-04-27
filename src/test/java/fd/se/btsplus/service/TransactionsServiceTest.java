package fd.se.btsplus.service;

import fd.se.btsplus.model.entity.bts.Account;
import fd.se.btsplus.model.entity.bts.Transaction;
import fd.se.btsplus.repository.bts.TransactionRepository;
import fd.se.btsplus.repository.bts.mock.TransactionRepositoryMock;
import fd.se.btsplus.utils.JsonUtils;
import fd.se.btsplus.utils.ResourceUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static fd.se.btsplus.model.consts.Constant.ASC;
import static fd.se.btsplus.model.consts.Constant.DESC;
import static org.junit.jupiter.api.Assertions.*;

class TransactionsServiceTest {
    private static final JsonUtils JSON_UTILS = new JsonUtils();
    private static final ResourceUtils RESOURCE_UTILS = new ResourceUtils();
    private TransactionRepository transactionRepository;
    private TransactionsService transactionsService;
    private List<Transaction> transactions;

    @BeforeEach
    void setUp() {
        transactionRepository = new TransactionRepositoryMock(RESOURCE_UTILS, JSON_UTILS, null);
        // 3 items in transactionRepository
        ((TransactionRepositoryMock) transactionRepository).init("test-json/TransactionServiceTest/transactions.json");
        transactionsService = new TransactionsService(transactionRepository);
    }

    @AfterEach
    void tearDown() {
        transactionRepository = null;
        transactionsService = null;
    }

    @Test
    void queryWithCorrectPage() {
        // find all transactions
        transactions = getTransactionsWithPage(null, null);
        assertEquals(3, transactions.size());


        // find transactions with limit: pageSize = 2
        // page 1
        transactions = getTransactionsWithPage(1, 2);
        assertEquals(2, transactions.size());

        // page 2
        transactions = getTransactionsWithPage(2, 2);
        assertEquals(1, transactions.size());
    }

    @Test
    void queryWithWrongPage() {
        // wrong pageNum
        transactions = getTransactionsWithPage(null, 2);
        assertEquals(0, transactions.size());

        transactions = getTransactionsWithPage(0, 2);
        assertEquals(0, transactions.size());


        // wrong pageSize
        transactions = getTransactionsWithPage(1, null);
        assertEquals(0, transactions.size());

        transactions = getTransactionsWithPage(1, 0);
        assertEquals(0, transactions.size());

        // empty page
        transactions = getTransactionsWithPage(100, 2);
        assertEquals(0, transactions.size());
    }

    @Test
    void queryWithOrderBy() {
        // ASC
        transactions = getTransactionsWithOrderBy(null);
        assertTrue(isOrderCorrect(true));

        transactions = getTransactionsWithOrderBy(ASC);
        assertTrue(isOrderCorrect(true));
        // DESC
        transactions = getTransactionsWithOrderBy(DESC);
        assertTrue(isOrderCorrect(false));
    }

    @Test
    void queryWithAccountNum() {
        // correct
        transactions = getTransactionsWithAccountNum("6161710619136431439");
        assertEquals(1, transactions.size());
        assertEquals(70, transactions.get(0).getId());

        // wrong
        transactions = getTransactionsWithAccountNum("");
        assertEquals(0, transactions.size());
    }

    @Test
    void queryWithTransactionNum() {
        // correct
        transactions = getTransactionsWithTransactionNum("AB21210001206202103251803061");
        assertEquals(1, transactions.size());
        assertEquals(71, transactions.get(0).getId());

        //wrong
        transactions = getTransactionsWithTransactionNum("");
        assertEquals(0, transactions.size());
    }

    @Test
    void queryWithTransactionCode() {
        // correct
        transactions = getTransactionsWithTransactionCode("0001");
        assertEquals(2, transactions.size());

        //wrong
        transactions = getTransactionsWithTransactionCode("");
        assertEquals(0, transactions.size());
    }

    private List<Transaction> getTransactionsWithPage(Integer pageNum, Integer pageSize) {
        return transactionsService.query(
                pageNum, pageSize, null,
                null, null, null,
                null, null);
    }

    private List<Transaction> getTransactionsWithOrderBy(String orderBy) {
        return transactionsService.query(
                null, null, null,
                null, null, orderBy,
                null, null);
    }

    private boolean isOrderCorrect(boolean isAsc) {
        int index;
        if (isAsc) {
            index = 70;
            for (Transaction transaction : transactions) {
                if (transaction.getId() != index)
                    return false;
                index++;
            }
        } else {
            index = 72;
            for (Transaction transaction : transactions) {
                if (transaction.getId() != index)
                    return false;
                index--;
            }
        }
        return true;
    }

    private List<Transaction> getTransactionsWithAccountNum(String accountNum) {
        return transactionsService.query(
                null, null, accountNum,
                null, null, null,
                null, null);
    }

    private List<Transaction> getTransactionsWithTransactionNum(String transactionNum) {
        return transactionsService.query(
                null, null, null,
                transactionNum, null, null,
                null, null);
    }

    private List<Transaction> getTransactionsWithTransactionCode(String transactionCode) {
        return transactionsService.query(
                null, null, null,
                null, transactionCode, null,
                null, null);
    }

    @SneakyThrows
    Stream<Transaction> invokeStreamSorted(Stream<Transaction> stream, String finalOrderBy) {
        final Method method = transactionsService.getClass().
                getDeclaredMethod("streamSorted", Stream.class, String.class);
        method.setAccessible(true);
        @SuppressWarnings("unchecked") final Stream<Transaction> ret =
                (Stream<Transaction>) method.invoke(transactionsService, stream,
                        finalOrderBy);
        return ret;
    }

    @Test
    void testStreamSortedOperatedTimeNull() {
        final Transaction tx1 = new Transaction();
        final Transaction tx2 = new Transaction();
        tx2.setOperatedTime(new Date(0));
        final Transaction tx3 = new Transaction();
        Stream<Transaction> stream = Stream.of(tx1, tx2, tx3);
        final List<Transaction> list = invokeStreamSorted(stream, ASC).collect(Collectors.toList());
        Assertions.assertNotNull(list.get(0).getOperatedTime());
    }

    @Test
    void testStreamSortedOperatedTimeNotNull() {
        final Transaction tx1 = new Transaction();
        tx1.setOperatedTime(new Date(1));
        final Transaction tx2 = new Transaction();
        tx2.setOperatedTime(new Date(0));
        Stream<Transaction> stream = Stream.of(tx1, tx2);
        final List<Transaction> list = invokeStreamSorted(stream, ASC).collect(Collectors.toList());
        Assertions.assertEquals(tx2, list.get(0));
    }

    @SneakyThrows
    boolean invokeAccountNumFilter(Transaction transaction, String accountNum) {
        final Method method = transactionsService.getClass().
                getDeclaredMethod("accountNumFilter", Transaction.class, String.class);
        method.setAccessible(true);
        return (Boolean) method.invoke(transactionsService, transaction,
                accountNum);
    }

    @Test
    void testAccountNumberFilterInvalidTransaction() {
        final Transaction tx = new Transaction();
        Assertions.assertFalse(invokeAccountNumFilter(tx, "123"));
        tx.setAccount(new Account());
        Assertions.assertFalse(invokeAccountNumFilter(tx, "123456"));
    }

    @Test
    void testAccountNumberFilterAccountNumNull() {
        final Transaction tx = new Transaction();
        Assertions.assertTrue(invokeAccountNumFilter(tx, null));
    }

    @Test
    void testAccountNumberFilterAccountNormal() {
        final Transaction tx = new Transaction();
        final Account account = new Account();
        account.setAccountNum("9527");
        tx.setAccount(account);
        Assertions.assertTrue(invokeAccountNumFilter(tx, "9527"));
    }

    @SneakyThrows
    private boolean invokeBeginDateFilter(Transaction tx, Date beginDate) {
        final Method method = transactionsService.getClass().
                getDeclaredMethod("beginDateFilter", Transaction.class, Date.class);
        method.setAccessible(true);
        return (Boolean) method.invoke(transactionsService, tx, beginDate);
    }

    @SneakyThrows
    private boolean invokeEndDateFilter(Transaction tx, Date endDate) {
        final Method method = transactionsService.getClass().
                getDeclaredMethod("endDateFilter", Transaction.class, Date.class);
        method.setAccessible(true);
        return (Boolean) method.invoke(transactionsService, tx, endDate);
    }

    @Test
    void testDateFilterOperatedTimeNull() {
        final Transaction tx = new Transaction();
        assertFalse(invokeBeginDateFilter(tx, new Date(0)));
        assertFalse(invokeEndDateFilter(tx, new Date(0)));
    }

    @Test
    void testDateFilterNormal() {
        final Transaction tx = new Transaction();
        tx.setOperatedTime(new Date(2_000_000_000));
        assertTrue(invokeBeginDateFilter(tx, new Date(1_000_000_000)));
        assertFalse(invokeBeginDateFilter(tx, new Date(2_100_000_000)));
        assertFalse(invokeEndDateFilter(tx, new Date(436_000_000)));
        assertTrue(invokeEndDateFilter(tx, new Date(2_100_000_000)));
    }

    @Test
    void testQueryByDates() {
        transactionRepository = new TransactionRepositoryMock(RESOURCE_UTILS, JSON_UTILS, null);
        // 3 items in transactionRepository
        ((TransactionRepositoryMock) transactionRepository).init("test-json/TransactionServiceTest/transactions-extra.json");
        transactionsService = new TransactionsService(transactionRepository);
        List<Transaction> list = transactionsService.query(1, 20,
                null, null, null,
                null, new Date(1617033600000L), new Date(1619798400000L));
        Assertions.assertEquals(1,list.size());
    }
}
