package fd.se.btsplus.model.domain;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Date;

@Getter
public class DateEvent extends ApplicationEvent implements Cloneable {
    private final Date lastDate;
    private final Date newDate;
    public DateEvent(Object source, Date lastDate, Date newDate) {
        super(source);
        this.lastDate = lastDate;
        this.newDate = newDate;
    }
}
