package com.multicampus.gangwonActivity.service.implement;

import com.multicampus.gangwonActivity.common.CertificationNumber;
import com.multicampus.gangwonActivity.common.ChangePassword;
import com.multicampus.gangwonActivity.dto.request.auth.CheckCertificationRequestDto;
import com.multicampus.gangwonActivity.dto.request.auth.EmailCertificationRequestDto;
import com.multicampus.gangwonActivity.dto.request.auth.SignInRequestDto;
import com.multicampus.gangwonActivity.dto.request.auth.SignUpRequestDto;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import com.multicampus.gangwonActivity.dto.response.auth.CheckCertificationResponseDto;
import com.multicampus.gangwonActivity.dto.response.auth.EmailCertificationResponseDto;
import com.multicampus.gangwonActivity.dto.response.auth.SignInResponseDto;
import com.multicampus.gangwonActivity.dto.response.auth.SignUpResponseDto;
import com.multicampus.gangwonActivity.entity.User;
import com.multicampus.gangwonActivity.provider.EmailProvider;
import com.multicampus.gangwonActivity.provider.JwtProvider;
import com.multicampus.gangwonActivity.repository.CertificationRepository;
import com.multicampus.gangwonActivity.repository.UserRepository;
import com.multicampus.gangwonActivity.service.AuthService;
import jakarta.servlet.http.HttpSession;
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
    private final CertificationRepository certificationRepository;
    private  final EmailProvider emailProvider;


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
            User user = new User(dto);
            userRepository.save(user);


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
            User user = userRepository.findByUserId(userId);
            if (user == null) return  SignInResponseDto.signInFailed();

            //평문 비번
            String password = dto.getUserPassword();
            //JWT암호화된 비번
            String encodedPassword = user.getUserPassword();

            boolean isMatched = passwordEncoder.matches(password,encodedPassword);
            if(!isMatched) return SignInResponseDto.signInFailed();

            token = jwtProvider.create(userId);


        }catch (Exception e){
            e.printStackTrace();
            return  ResponseDto.databaseError();
        }



        return SignInResponseDto.success(token);
    }

    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto, HttpSession session) {


        try {
            String email = dto.getEmail();
            String certificationNumber = CertificationNumber.getCertificationNumber();

            //이메일 전송
            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if (!isSuccessed) return EmailCertificationResponseDto.mailSendFail();

            // 세션에 이메일과 인증 번호 저장
            session.setAttribute("email", email);
            session.setAttribute("certificationNumber", certificationNumber);
            System.out.println("session에 가장 처음 담는 작업 완료!");
            System.out.println("session-email : " + session.getAttribute("email"));
            System.out.println("session-certiNum : " + session.getAttribute("certificationNumber"));
            System.out.println("------------------------------------------------------------------------------");
//            System.out.println(session.getAttribute("email"));
//            System.out.println(session.getAttribute("certificationNumber"));



        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCertificationResponseDto.success();
    }

    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto, HttpSession session) {
        try {
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            // 세션에서 저장된 이메일과 인증 번호 가져오기
            String sessionEmail = (String) session.getAttribute("email");
            String sessionCertificationNumber = (String) session.getAttribute("certificationNumber");

            System.out.println("check Certification Session Email: " + sessionEmail);
            System.out.println("check Certification Session Certification Number: " + sessionCertificationNumber);

            //세션 예외 처리
            if (sessionEmail == null || sessionCertificationNumber == null) {
                return CheckCertificationResponseDto.certificationFail();
            }

            //인증번호 확인
            boolean isMatch = sessionEmail.equals(email) && sessionCertificationNumber.equals(certificationNumber);
            if (!isMatch) {
                return CheckCertificationResponseDto.certificationFail();
            }

            //회원가입 판별
            boolean checkFind = dto.getUserName() != null || dto.getUserId() != null;
            if(!checkFind) session.invalidate();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CheckCertificationResponseDto.success();
    }

    public ResponseEntity<? super CheckCertificationResponseDto> findIdCertification(CheckCertificationRequestDto dto, HttpSession session) {
        try {
            // 세션에서 저장된 이메일과 인증 번호 가져오기
            String sessionEmail = (String) session.getAttribute("email");
            String sessionCertificationNumber = (String) session.getAttribute("certificationNumber");

            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();
            String userName =dto.getUserName();

            System.out.println("Session Email: " + sessionEmail);
            System.out.println("Session Certification Number: " + sessionCertificationNumber);

            User user =userRepository.findByUserEmail(email);
            if (user == null) return CheckCertificationResponseDto.notExistUser();

            if (sessionEmail == null || sessionCertificationNumber == null) {
                return CheckCertificationResponseDto.certificationFail();
            }

            //이름 유저 정보 확인
            boolean nameNatch = user.getUserName().equals(userName);
            if (!nameNatch) return  CheckCertificationResponseDto.notExistUser();

            //인증번호 확인
            boolean isMatch = sessionEmail.equals(email) && sessionCertificationNumber.equals(certificationNumber);
            if (!isMatch) {
                return CheckCertificationResponseDto.certificationFail();
            }

            //아이디 이메일 전송
            boolean isSuccessed = emailProvider.sendFindIdMail(email, userName, user.getUserId());
            if (!isSuccessed) return EmailCertificationResponseDto.mailSendFail();

            //세션 삭제
            session.invalidate();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CheckCertificationResponseDto.success();
    }

    public ResponseEntity<? super CheckCertificationResponseDto> findPwdCertification(CheckCertificationRequestDto dto, HttpSession session) {
        try {
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();
            String userId = dto.getUserId();

            // 세션에서 저장된 이메일과 인증 번호 가져오기
            String sessionEmail = (String) session.getAttribute("email");
            String sessionCertificationNumber = (String) session.getAttribute("certificationNumber");

            System.out.println("Session Email: " + sessionEmail);
            System.out.println("Session Certification Number: " + sessionCertificationNumber);


            if (sessionEmail == null || sessionCertificationNumber == null) {
                return CheckCertificationResponseDto.certificationFail();
            }

            User user = userRepository.findByUserId(userId);
            if (user == null) return CheckCertificationResponseDto.notExistUser();

            //아이디 유저 정보 확인
            boolean idMatch = user.getUserId().equals(userId);
            if (!idMatch) return CheckCertificationResponseDto.notExistUser();

            //인증번호 확인
            boolean isMatch = sessionEmail.equals(email) && sessionCertificationNumber.equals(certificationNumber);
            if (!isMatch) {
                return CheckCertificationResponseDto.certificationFail();
            }

            //비밀번호 변경 및 변경 된 비밀번호 이메일 전송
            String tempPassword = ChangePassword.generateTemporaryPassword();
            boolean isSuccessed = emailProvider.sendFindPwdMail(email, userId ,tempPassword);
            if (!isSuccessed) return EmailCertificationResponseDto.mailSendFail();

            //jwt 변환 및 유저 정보 저장
            String encodedTempPassword = passwordEncoder.encode(tempPassword);
            user.TempPassword(encodedTempPassword);
            userRepository.save(user);

            //세션 삭제
            session.invalidate();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CheckCertificationResponseDto.success();
    }


    @Override
    public Boolean checkId(String userId) {
        return !userRepository.existsByUserId(userId);
    }

    @Override
    public Boolean checkNickname(String userNick) {
        return !userRepository.existsByUserNick(userNick);
    }


}
