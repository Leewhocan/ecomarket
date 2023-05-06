//package com.example.course.User;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@Service
//public class UserService {
//    @Autowired
//    private UserRepository userRepository;
//    public List<User> getUsers(String username){
//        if (username!=null){
//            return userRepository.findByUsername(username);
//        }
//        return userRepository.findAll();
//    }
//
//    public User SaveUser(User user){
//        return userRepository.save(user);
//    }
//    public User GetUserById(Long id){
//        return userRepository.findById(id).orElse(null);
//    }
//
//    public void DeleteUser(Long id){
//        userRepository.deleteById(id);
//    }
//}
