package fd.se.btsplus.service;

import fd.se.btsplus.bts.http.IBtsHttpCaller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@AllArgsConstructor
@Service
public class CustomerService {
    private final IBtsHttpCaller caller;


    public int creditLevel(String customerCode) {
        throw new NotImplementedException();
    }

    public long fineAmount(String customerCode) {
        return 0;
    }
}
