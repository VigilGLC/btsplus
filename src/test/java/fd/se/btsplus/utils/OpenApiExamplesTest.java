package fd.se.btsplus.utils;

import fd.se.btsplus.model.domain.LoginData;
import fd.se.btsplus.model.domain.ProductDatum;
import fd.se.btsplus.model.response.ResponseWrapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.apache.commons.lang3.StringEscapeUtils.escapeJava;

@Slf4j
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class OpenApiExamplesTest {
    @Autowired
    RepositoryUtils repositoryUtils;
    @Autowired
    JSONUtils jsonUtils;

    @Test
    void testRepository() {
        Assertions.assertNotNull(repositoryUtils);
    }

    static Map<String, String> resultRecord;

    @Test
    void recordLoginRespOk() {
        final String key = "LoginRespOk";
        resultRecord.put(key, jsonUtils.write(ResponseWrapper.wrap(HTTP_OK,
                new LoginData("3714sgw+10086", new Date())
        )));
    }

    @Test
    void recordCurrUserRespOk() {
        final String key = "CurrUserRespOk";
        resultRecord.put(key, jsonUtils.write(ResponseWrapper.wrap(HTTP_OK,
                repositoryUtils.userRepository.findAll().get(0)
        )));
    }

    @Test
    void recordLoansRespOk() {
        final String key = "LoansRespOk";
        resultRecord.put(key, jsonUtils.write(ResponseWrapper.wrap(HTTP_OK,
                repositoryUtils.loanRepository.findAll()
        )));
    }

    @Test
    void recordBillsRespOk() {
        final String key = "BillsRespOk";
        resultRecord.put(key, jsonUtils.write(ResponseWrapper.wrap(HTTP_OK,
                repositoryUtils.billRepository.findAll()
        )));
    }

    @Test
    void recordPaymentRespOk() {
        final String key = "PaymentRespOk";
        resultRecord.put(key, jsonUtils.write(ResponseWrapper.wrap(HTTP_OK,
                "Success!",
                Boolean.TRUE
        )));
    }

    @Test
    void recordAutoPaymentRespOk() {
        final String key = "AutoPaymentRespOk";
        resultRecord.put(key, jsonUtils.write(ResponseWrapper.wrap(HTTP_OK,
                Boolean.TRUE
        )));
    }

    @Test
    void recordTransactionsRespOk() {
        final String key = "TransactionsRespOk";
        resultRecord.put(key, jsonUtils.write(ResponseWrapper.wrap(HTTP_OK,
                repositoryUtils.transactionRepository.findAll()
        )));
    }

    @Test
    void recordStocksRespOk() {
        final String key = "StocksRespOk";
        resultRecord.put(key, jsonUtils.write(ResponseWrapper.wrap(HTTP_OK,
                repositoryUtils.stockDailyRepository.findAll().stream().
                        map(sd -> new ProductDatum(sd.getStock(), sd, null)).
                        collect(Collectors.toList())
        )));
    }

    @Test
    void recordFundsRespOk() {
        final String key = "FundsRespOk";
        resultRecord.put(key, jsonUtils.write(ResponseWrapper.wrap(HTTP_OK,
                repositoryUtils.fundDailyRepository.findAll().stream().
                        map(fd -> new ProductDatum(fd.getFund(), fd, null)).
                        collect(Collectors.toList())
        )));
    }

    @Test
    void recordTermsRespOk() {
        final String key = "TermsRespOk";
        resultRecord.put(key, jsonUtils.write(ResponseWrapper.wrap(HTTP_OK,
                repositoryUtils.termDailyRepository.findAll().stream().
                        map(td -> new ProductDatum(td.getTerm(), td, null)).
                        collect(Collectors.toList())
        )));
    }

    @Test
    void recordPurchaseRespOk() {
        final String key = "PurchaseRespOk";
        resultRecord.put(key, jsonUtils.write(ResponseWrapper.wrap(HTTP_OK,
                Boolean.TRUE
        )));
    }

    @Test
    void recordFundPurchasesRespOk() {
        final String key = "FundPurchasesRespOk";
        resultRecord.put(key, jsonUtils.write(ResponseWrapper.wrap(HTTP_OK,
                repositoryUtils.fundPurchaseRepository.findAll().stream().
                        map(fp -> new ProductDatum(
                                fp.getFund(),
                                repositoryUtils.fundDailyRepository.findAll().get(0),
                                fp)).
                        collect(Collectors.toList())
        )));
    }

    @Test
    void recordStockPurchasesRespOk() {
        final String key = "StockPurchasesRespOk";
        resultRecord.put(key, jsonUtils.write(ResponseWrapper.wrap(HTTP_OK,
                repositoryUtils.stockPurchaseRepository.findAll().stream().
                        map(sp -> new ProductDatum(
                                sp.getStock(),
                                repositoryUtils.stockDailyRepository.findAll().get(0),
                                sp)).
                        collect(Collectors.toList())
        )));
    }

    @Test
    void recordTermPurchasesRespOk() {
        final String key = "TermPurchasesRespOk";
        resultRecord.put(key, jsonUtils.write(ResponseWrapper.wrap(HTTP_OK,
                repositoryUtils.termPurchaseRepository.findAll().stream().
                        map(tp -> new ProductDatum(
                                tp.getTerm(),
                                repositoryUtils.termDailyRepository.findAll().get(0),
                                tp)).
                        collect(Collectors.toList())
        )));
    }


    //<editor-fold desc="generate">
    @BeforeAll
    static void setUp() {
        resultRecord = new HashMap<>();
    }


    @SneakyThrows
    @AfterAll
    static void tearDown() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : resultRecord.entrySet()) {
            final String line = MessageFormat.format("\tpublic static final String {0} = \"{1}\";\n",
                    entry.getKey(), escapeJava(entry.getValue()));
            sb.append(line).append('\n');
        }
        final PrintWriter writer = new PrintWriter("./OpenApiExamples.java");
        writer.write("package fd.se.btsplus.utils;\n" +
                "\n" +
                "import lombok.AccessLevel;\n" +
                "import lombok.Data;\n" +
                "import lombok.NoArgsConstructor;\n" +
                "\n" +
                "@Data\n" +
                "@NoArgsConstructor(access = AccessLevel.PRIVATE)\n" +
                "public class OpenApiExamples {\n");
        writer.write(sb.toString());
        writer.write("}\n");
        writer.close();
    }
    //</editor-fold>


}
