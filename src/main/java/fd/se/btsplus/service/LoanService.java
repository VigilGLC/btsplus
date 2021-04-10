package fd.se.btsplus.service;

import fd.se.btsplus.model.entity.bts.Loan;
import fd.se.btsplus.repository.bts.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LoanService {
    private final LoanRepository loanRepository;

    public List<Loan> queryByIdNum(String idNum) {
        return loanRepository.findByCustomerCode(idNum);
    }
}
