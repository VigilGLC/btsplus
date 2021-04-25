package fd.se.btsplus.service;

import fd.se.btsplus.model.entity.bts.Transaction;
import fd.se.btsplus.repository.bts.TransactionRepository;
import fd.se.btsplus.repository.bts.mock.TransactionRepositoryMock;
import fd.se.btsplus.utils.JsonUtils;
import fd.se.btsplus.utils.ResourceUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        ((TransactionRepositoryMock)transactionRepository).init("test-json/TransactionServiceTest/transactions.json");
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
    void queryWithWrongPage(){
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
    void queryWithOrderBy(){
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
    void queryWithAccountNum(){
        // correct
        transactions = getTransactionsWithAccountNum("6161710619136431439");
        assertEquals(1, transactions.size());
        assertEquals(70, transactions.get(0).getId());

        // wrong
        transactions = getTransactionsWithAccountNum("");
        assertEquals(0, transactions.size());
    }

    @Test
    void queryWithTransactionNum(){
        // correct
        transactions = getTransactionsWithTransactionNum("AB21210001206202103251803061");
        assertEquals(1, transactions.size());
        assertEquals(71, transactions.get(0).getId());

        //wrong
        transactions = getTransactionsWithTransactionNum("");
        assertEquals(0, transactions.size());
    }
    @Test
    void queryWithTransactionCode(){
        // correct
        transactions = getTransactionsWithTransactionCode("0001");
        assertEquals(2, transactions.size());

        //wrong
        transactions = getTransactionsWithTransactionCode("");
        assertEquals(0, transactions.size());
    }

    private List<Transaction> getTransactionsWithPage(Integer pageNum, Integer pageSize){
        return transactionsService.query(
                pageNum, pageSize, null,
                null, null, null,
                null, null);
    }
    private List<Transaction> getTransactionsWithOrderBy(String orderBy){
        return transactionsService.query(
                null, null, null,
                null, null, orderBy,
                null, null);
    }
    private boolean isOrderCorrect(boolean isAsc){
        int index;
        if (isAsc){
            index = 70;
            for (Transaction transaction : transactions){
                if (transaction.getId() != index)
                    return false;
                index ++;
            }
        }
        else {
            index = 72;
            for (Transaction transaction : transactions){
                if (transaction.getId() != index)
                    return false;
                index --;
            }
        }
        return true;
    }
    private List<Transaction> getTransactionsWithAccountNum(String accountNum){
        return transactionsService.query(
                null, null, accountNum,
                null, null, null,
                null, null);
    }
    private List<Transaction> getTransactionsWithTransactionNum(String transactionNum){
        return transactionsService.query(
                null, null, null,
                transactionNum, null, null,
                null, null);
    }
    private List<Transaction> getTransactionsWithTransactionCode(String transactionCode){
        return transactionsService.query(
                null, null, null,
                null, transactionCode, null,
                null, null);
    }
}
