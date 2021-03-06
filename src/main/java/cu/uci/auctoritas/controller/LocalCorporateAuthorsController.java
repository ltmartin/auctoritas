package cu.uci.auctoritas.controller;

import cu.uci.auctoritas.domain.CorporateAuthor;
import cu.uci.auctoritas.domain.Event;
import cu.uci.auctoritas.domain.PersonalAuthor;
import cu.uci.auctoritas.service.LocalAuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/localauthors")
public class LocalCorporateAuthorsController {

    @Autowired
    private LocalAuthorsService localAuthorsService;

    @RequestMapping(value = "/corporateauthor/new", method = RequestMethod.POST)
    public<T> Event postpersonalAuthor(@RequestParam String name, @RequestParam String lastname, @RequestParam String authority, @RequestParam String uri, @RequestParam int datasourceindex) throws Exception {
        return localAuthorsService.post(uri, name, lastname, authority, CorporateAuthor.class);
    }

    @RequestMapping(value = "/corporateauthor/findby", method = RequestMethod.GET)
    private List<PersonalAuthor> getBy(@RequestParam (required = false) String name, @RequestParam(required = false) String lastname, @RequestParam(required = false) String authority, @RequestParam (required = false)String uri, @RequestParam int datasourceindex){

        if ((((name==null)||name.isEmpty())&&((uri==null)||uri.isEmpty())&&((lastname==null)||lastname.isEmpty())&&((authority==null)||authority.isEmpty())))
            return null;
        return localAuthorsService.getByID(uri, name, lastname, authority, CorporateAuthor.class);
    }
    @RequestMapping (value = "/corporateauthor/update", method = RequestMethod.PUT)
    private Event update(@RequestParam (required = false) String name, @RequestParam(required = false) String lastname, @RequestParam(required = false) String authority, @RequestParam String uri, @RequestParam String newuri, @RequestParam int datasourceindex){
        return localAuthorsService.update(uri, name, lastname, authority, CorporateAuthor.class, newuri);
    }

    @RequestMapping (value = "/corporateauthor/delete", method = RequestMethod.DELETE)
    private Event delete(@RequestParam String uri, @RequestParam int datasourceindex){
        return localAuthorsService.delete(uri, CorporateAuthor.class);
    }
}

