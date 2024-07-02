package com.multicampus.ganwonActivity.service.implement;

import com.multicampus.ganwonActivity.dto.request.auth.SignInRequestDto;
import com.multicampus.ganwonActivity.dto.request.auth.SignUpRequestDto;
import com.multicampus.ganwonActivity.dto.response.ResponseDto;
import com.multicampus.ganwonActivity.dto.response.auth.SignInResponseDto;
import com.multicampus.ganwonActivity.dto.response.auth.SignUpResponseDto;
import com.multicampus.ganwonActivity.entity.UserEntity;
import com.multicampus.ganwonActivity.provider.JwtProvider;
import com.multicampus.ganwonActivity.repository.UserRepository;
import com.multicampus.ganwonActivity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    //회원가입 서비스 (JWT)
    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        try{
            //제약조건 검사 과정
            String email = dto.getUserEmail();
            boolean existedEmail = userRepository.existsByUserEmail(email);
            if(existedEmail) return SignUpResponseDto.duplicateEmail();

            String nickname = dto.getUserNick();
            boolean existedNickname = userRepository.existsByUserNick(nickname);
            if(existedNickname) return SignUpResponseDto.duplicateNickname();

            String id = dto.getUserId();
            boolean existedId = userRepository.existsByUserId(id);
            if(existedId) return SignUpResponseDto.duplicateId();

            //password를 평문으로 넣으면 안되니까
            String password = dto.getUserPassword();
            //암호화된 상태로 전달
            String encodedPassword = passwordEncoder.encode(password);
            dto.setUserPassword(encodedPassword);

            //DB에 보내기. dto(SignUpRequestDto 형식을 Entity에 담아서)
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);


        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }



        return SignUpResponseDto.success();
    }

    //로그인 서비스(JWT)
    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        String token = null;

        try{
            String userId = dto.getUserId();
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return  SignInResponseDto.signInFailed();

            //평문 비번
            String password = dto.getUserPassword();
            //JWT암호화된 비번
            String encodedPassword = userEntity.getUserPassword();

            boolean isMatched = passwordEncoder.matches(password,encodedPassword);
            if(!isMatched) return SignInResponseDto.signInFailed();

            token = jwtProvider.create(userId);


        }catch (Exception e){
            e.printStackTrace();
            return  ResponseDto.databaseError();
        }



        return SignInResponseDto.success(token);
    }
}
