package fd.se.btsplus.controller;

import fd.se.btsplus.model.entity.bts.Transaction;
import fd.se.btsplus.model.response.ResponseWrapper;
import fd.se.btsplus.repository.bts.AccountRepository;
import fd.se.btsplus.repository.bts.TransactionRepository;
import fd.se.btsplus.repository.bts.mock.TransactionRepositoryMock;
import fd.se.btsplus.service.TransactionsService;
import fd.se.btsplus.utils.JsonUtils;
import fd.se.btsplus.utils.ResourceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionControllerTest {
    private static final JsonUtils JSON_UTILS = new JsonUtils();
    private static final ResourceUtils RESOURCE_UTILS = new ResourceUtils();
    private TransactionController transactionController;
    @BeforeEach
    void setUp() {
        TransactionRepositoryMock transactionRepository = new TransactionRepositoryMock(RESOURCE_UTILS, JSON_UTILS, null);
        // 3 items in transactionRepository
        transactionRepository.init("test-json/TransactionServiceTest/transactions.json");
        TransactionsService transactionsService = new TransactionsService(transactionRepository);
        transactionsService = new TransactionsService(transactionRepository);
        transactionController = new TransactionController(transactionsService);
    }
    @Test
    void transactions() {
        ResponseWrapper responseWrapper;
        // all transactions
         responseWrapper = (ResponseWrapper)(transactionController.transactions(null, null,
                null, null, null,
                null, null,null).getBody());
         assertNotNull(responseWrapper);
         assertNotEquals("[]", responseWrapper.getData().toString());

        // none transaction
        responseWrapper = (ResponseWrapper)(transactionController.transactions(-1, null,
                null, null, null,
                null, null,null).getBody());
        assertNotNull(responseWrapper);
        assertEquals("[]", responseWrapper.getData().toString());
    }
}
