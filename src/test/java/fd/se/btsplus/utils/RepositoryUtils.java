package fd.se.btsplus.utils;

import fd.se.btsplus.repository.bts.*;
import fd.se.btsplus.repository.financial.fund.FundDailyRepository;
import fd.se.btsplus.repository.financial.fund.FundPurchaseRepository;
import fd.se.btsplus.repository.financial.fund.FundRepository;
import fd.se.btsplus.repository.financial.stock.StockDailyRepository;
import fd.se.btsplus.repository.financial.stock.StockPurchaseRepository;
import fd.se.btsplus.repository.financial.stock.StockRepository;
import fd.se.btsplus.repository.financial.term.TermDailyRepository;
import fd.se.btsplus.repository.financial.term.TermPurchaseRepository;
import fd.se.btsplus.repository.financial.term.TermRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@AllArgsConstructor
@Component
public class RepositoryUtils {
    public final AccountRepository accountRepository;
    public final BillRepository billRepository;
    public final CustomerRepository customerRepository;
    public final LoanRepository loanRepository;
    public final TransactionRepository transactionRepository;
    public final UserRepository userRepository;
    public final FundDailyRepository fundDailyRepository;
    public final FundPurchaseRepository fundPurchaseRepository;
    public final FundRepository fundRepository;

    public final StockDailyRepository stockDailyRepository;
    public final StockPurchaseRepository stockPurchaseRepository;
    public final StockRepository stockRepository;

    public final TermDailyRepository termDailyRepository;
    public final TermPurchaseRepository termPurchaseRepository;
    public final TermRepository termRepository;
}
