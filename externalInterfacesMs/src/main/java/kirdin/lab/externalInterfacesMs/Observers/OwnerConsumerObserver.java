package kirdin.lab.externalInterfacesMs.Observers;

import kirdin.lab.externalInterfacesMs.consumers.CatConsumer;
import kirdin.lab.externalInterfacesMs.consumers.OwnerConsumer;
import kirdin.lab.models.dto.CatResponse;
import kirdin.lab.models.dto.OwnerResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OwnerConsumerObserver {
    private final OwnerConsumer ownerConsumer;

    public ResponseEntity<List<OwnerResponse>> getOwners(){
        while (!ownerConsumer.getIsModified()){
        }
        ownerConsumer.setIsModified(false);
        return new ResponseEntity<>(ownerConsumer.getOwnerResponseList(), HttpStatus.OK);
    }

}
