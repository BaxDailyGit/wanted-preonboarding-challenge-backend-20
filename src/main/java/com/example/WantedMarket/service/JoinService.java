package com.example.WantedMarket.service;
import com.example.WantedMarket.domain.dto.JoinDto;
import com.example.WantedMarket.domain.entity.User;
import com.example.WantedMarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public void joinProcess(JoinDto joinDto) {

        // username 중복 검증
        boolean isUser = userRepository.existsByUsername(joinDto.getUsername());
        if (isUser) {
            return;
        }

        /*
        * TODO
        *  가입 불가 문자 정규식 처리
        *     - 아이디의 자리수
        *         - 아이디의 특수문자 포함 불가
        *         - admin과 같은 아이디 사용 불가
        *     - 비밀번호 자리수
        *         - 비밀번호 특수문자 포함 필수
        * */

        String encodedPassword = bCryptPasswordEncoder.encode(joinDto.getPassword());
        User user = User.createUser(joinDto.getUsername(), encodedPassword, "ROLE_SUPERVISOR");

        userRepository.save(user);
    }
}