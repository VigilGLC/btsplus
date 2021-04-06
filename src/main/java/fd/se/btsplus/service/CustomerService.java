package fd.se.btsplus.service;

import fd.se.btsplus.model.consts.CreditLevel;
import fd.se.btsplus.model.entity.bts.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@AllArgsConstructor
@Service
public class CustomerService {

    public CreditLevel creditLevel(Customer customer) {
        throw new NotImplementedException();
    }

    public double penaltyInterest(Customer customer) {
        throw new NotImplementedException();
    }
}
