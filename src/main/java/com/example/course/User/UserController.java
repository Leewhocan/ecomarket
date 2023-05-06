//package com.example.course.User;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//    @Autowired
//    private UserService userService;
//    @GetMapping()
//    public List<User> getUsers(@RequestParam(name = "username",required = false) String username){
//        return userService.getUsers(username);
//    }
//
//    @GetMapping("/{id}")
//    public User getUserById(Long id){
//        User user = userService.GetUserById(id);
//        return user;
//    }
//
//    @PostMapping()
//    public User addUser(@RequestBody User user) {
//       return userService.SaveUser(user);
//    }
//
//}
