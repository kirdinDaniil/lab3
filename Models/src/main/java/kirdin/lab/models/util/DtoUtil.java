package kirdin.lab.models.util;

import kirdin.lab.models.dto.CatRequest;
import kirdin.lab.models.dto.CatResponse;
import kirdin.lab.models.dto.OwnerRequest;
import kirdin.lab.models.jpa.Cat;
import kirdin.lab.models.jpa.Owner;
import kirdin.lab.models.dto.OwnerResponse;

import java.util.ArrayList;
import java.util.List;

public class DtoUtil {
    public static List<CatResponse> castCats(List<Cat> cats){
        List<CatResponse> catResponses = new ArrayList<>();
        for (Cat cat : cats){
            catResponses.add(new CatResponse(cat));
        }
        return catResponses;
    }

    public static List<OwnerResponse> castOwners(List<Owner> owners){
        List<OwnerResponse> ownersResponses = new ArrayList<>();
        for (Owner owner : owners){
            ownersResponses.add(new OwnerResponse(owner));
        }
        return ownersResponses;
    }

    public static Cat castCatRequestToCat(CatRequest catRequest){
        return new Cat(catRequest.getName(), catRequest.getBirthdate(), catRequest.getBreed(), catRequest.getColoring(), catRequest.getOwner());
    }

    public static Owner castOwnerRequestToOwner(OwnerRequest ownerRequest){
        return new Owner(ownerRequest.getName(), ownerRequest.getBirthdate());
    }
}
