//package project.community.user.dao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import project.community.user.config.AuthRepository;
//import project.community.user.dto.CustomUser;
//
//@Repository
//public class AuthRepo implements AuthRepository {
//    AuthRepository authRepository;
//    @Autowired
//    public AuthRepo(AuthRepository authRepository){
//        this.authRepository = authRepository;
//    }
//
//    @Override
//    public CustomUser findCustomUserByEmail(String email) {
//        return this.authRepository.findCustomUserByEmail(email);
//    }
//}
