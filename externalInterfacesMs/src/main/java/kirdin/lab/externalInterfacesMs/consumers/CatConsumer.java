package kirdin.lab.externalInterfacesMs.consumers;

import com.ea.async.Async;
import kirdin.lab.externalInterfacesMs.Security.UserSecurityDetails;
import kirdin.lab.models.dto.CatResponse;
import kirdin.lab.models.dto.OwnerResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Getter
@Service
@Setter
public class CatConsumer {

    private List<CatResponse> catResponseList;

    private Boolean isModified = false;

    @RabbitListener(queues = {"${rabbitmq.queue.json.cat.name}"})
    public void ConsumeCatResponses(List<CatResponse> catResponseList){
        this.catResponseList = catResponseList;
        isModified = true;
    }
}
