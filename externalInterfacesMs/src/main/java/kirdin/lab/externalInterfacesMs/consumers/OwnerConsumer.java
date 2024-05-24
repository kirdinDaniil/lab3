package kirdin.lab.externalInterfacesMs.consumers;

import kirdin.lab.models.dto.OwnerResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class OwnerConsumer {

    private List<OwnerResponse> ownerResponseList;

    private Boolean isModified = false;


    @RabbitListener(queues = {"${rabbitmq.queue.json.owner.name}"})
    public void ConsumeOwnerResponses(List<OwnerResponse> ownerResponseList){
        this.ownerResponseList = ownerResponseList;
        isModified = true;
    }

}
