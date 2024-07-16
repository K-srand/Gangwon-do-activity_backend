package com.multicampus.gangwonActivity.service.implement;

import com.multicampus.gangwonActivity.dto.request.auth.SignInRequestDto;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import com.multicampus.gangwonActivity.dto.response.admin.AdminUserListResponseDto;
import com.multicampus.gangwonActivity.dto.response.auth.SignInResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.entity.User;
import com.multicampus.gangwonActivity.mapper.AdminMapper;
import com.multicampus.gangwonActivity.provider.JwtProvider;
import com.multicampus.gangwonActivity.repository.UserRepository;
import com.multicampus.gangwonActivity.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<? super SignInResponseDto> AdminIn(SignInRequestDto dto) {
        String token = null;

        try {
            String userId = dto.getUserId();
            User user = userRepository.findByUserId(userId);
            if (user == null) return SignInResponseDto.signInFailed();

            if (!user.getUserRole().equals("ROLE_ADMIN")) {
                return SignInResponseDto.adminSignInFailed();
            }

            // 평문 비번
            String password = dto.getUserPassword();
            // 암호화된 비번
            String encodedPassword = user.getUserPassword();

            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched) return SignInResponseDto.signInFailed();

            token = jwtProvider.create(userId);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }

    @Override
    public List<AdminUserListResponseDto> userList(SearchPageDto searchPageDto) {
        return adminMapper.allUserList(searchPageDto);
    }

    public int countUser(){return adminMapper.countAllUser();}
}
