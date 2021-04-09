package fd.se.btsplus.service;

import fd.se.btsplus.model.entity.bts.Bill;
import fd.se.btsplus.repository.bts.BillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BillService {
    private final BillRepository billRepository;

    public List<Bill> query(String iouNum) {
        return billRepository.findByLoanIouNum(iouNum);
    }
}
