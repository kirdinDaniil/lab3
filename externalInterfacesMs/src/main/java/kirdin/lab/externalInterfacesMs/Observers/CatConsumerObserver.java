package kirdin.lab.externalInterfacesMs.Observers;

import kirdin.lab.externalInterfacesMs.Security.UserSecurityDetails;
import kirdin.lab.externalInterfacesMs.consumers.CatConsumer;
import kirdin.lab.models.dto.CatResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CatConsumerObserver {

    private CatConsumer catConsumer;

    public ResponseEntity<List<CatResponse>> getCats(){
        while (!catConsumer.getIsModified()){
        }
        catConsumer.setIsModified(false);
        return new ResponseEntity<>(getEnableCats(catConsumer.getCatResponseList()), HttpStatus.OK);
    }

    public Authentication getCurrentAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public List<CatResponse> getEnableCats(List<CatResponse> cats){
        Authentication authentication = getCurrentAuthentication();
        if (authentication.equals(null))
            return cats;
        String authority = authentication.getAuthorities().stream().toList().get(0).getAuthority();
        if (authority.equals("ROLE_ADMIN"))
            return cats;

        UserSecurityDetails user = (UserSecurityDetails) authentication.getPrincipal();
        Long OwnerId = user.getOwnerId();

        return cats.stream().filter(cat -> cat.getOwnerId().equals(OwnerId)).toList();
    }

    public CatResponse getEnableCat(CatResponse cat){
        Authentication authentication = getCurrentAuthentication();
        if (authentication.equals(null))
            return cat;
        String authority = authentication.getAuthorities().stream().toList().get(0).getAuthority();

        UserSecurityDetails user = (UserSecurityDetails) authentication.getPrincipal();
        Long OwnerId = user.getOwnerId();

        if (authority.equals("ROLE_ADMIN") || cat.getOwnerId().equals(OwnerId))
            return cat;

        throw new NoSuchElementException();
    }

}
