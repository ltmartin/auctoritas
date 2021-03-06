package cu.uci.auctoritas.controller;

import cu.uci.auctoritas.domain.Event;
import cu.uci.auctoritas.domain.UserInfo;
import cu.uci.auctoritas.repository.UserRepository;
import cu.uci.auctoritas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/user")

public class UsersController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Event post(@RequestParam String name, @RequestParam String lastname, @RequestParam String password, @RequestParam String username, @RequestParam String role) throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setLastname(lastname);
        userInfo.setPassword(password);
        userInfo.setRole(role);
        userInfo.setUsername(username);
        return userService.post(userInfo);
    }

    @RequestMapping(value = "/findbyname", method = RequestMethod.GET)
    private List<UserInfo> getByName(@RequestParam String name){
       return userService.findByname(name);
       }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Event update(@RequestParam String username, @RequestParam String newpassword) throws Exception {
       return userService.update(username,newpassword);
    }
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Event delete(@RequestParam String username) throws Exception {
       return userService.delete(username);
    }
}
