package project.community.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.community.user.dao.MemberDao;
import project.community.user.dto.MemberDto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class AuthService {
    MemberDao memberDao;

    @Autowired
    public AuthService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public MemberDto singIn(MemberDto memberDto) {
        return memberDao.singIn(memberDto);
    }

    //인증
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        CustomUser customUser = this.authRepo.findCustomUserByEmail(username);
//        if (customUser == null) {
//            throw new UsernameNotFoundException("username " + username + " is not found");
//        }
//        return new CustomUserDetails(customUser);
//    }
//
//    static final class CustomUserDetails extends CustomUser implements UserDetails {
//
//        private static final List<GrantedAuthority> ROLE_USER = Collections
//                .unmodifiableList(AuthorityUtils.createAuthorityList("ROLE_user"));
//
//        CustomUserDetails(CustomUser customUser) {
//            super(customUser.getId(), customUser.getEmail(), customUser.getPassword());
//        }
//
//        @Override
//        public Collection<? extends GrantedAuthority> getAuthorities() {
//            return null;
//        }
//
//        @Override
//        public String getUsername() {
//            return null;
//        }
//
//        @Override
//        public boolean isAccountNonExpired() {
//            return false;
//        }
//
//        @Override
//        public boolean isAccountNonLocked() {
//            return false;
//        }
//
//        @Override
//        public boolean isCredentialsNonExpired() {
//            return false;
//        }
//
//        @Override
//        public boolean isEnabled() {
//            return false;
//        }
//    }
}
