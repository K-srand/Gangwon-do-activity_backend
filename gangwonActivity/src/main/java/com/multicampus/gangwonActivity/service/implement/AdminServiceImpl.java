package com.multicampus.gangwonActivity.service.implement;

import com.multicampus.gangwonActivity.dto.request.auth.SignInRequestDto;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import com.multicampus.gangwonActivity.dto.response.admin.AdminUserListResponseDto;
import com.multicampus.gangwonActivity.dto.response.auth.SignInResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.dto.response.sanction.SanctionContentResponseDto;
import com.multicampus.gangwonActivity.dto.response.sanction.SanctionedUserResponseDto;
import com.multicampus.gangwonActivity.mapper.AdminMapper;
import com.multicampus.gangwonActivity.mapper.ReportMapper;
import com.multicampus.gangwonActivity.provider.JwtProvider;
import com.multicampus.gangwonActivity.repository.UserRepository;
import com.multicampus.gangwonActivity.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;
    private final ReportMapper reportMapper;


    @Override
    public List<AdminUserListResponseDto> userList(SearchPageDto searchPageDto) {
        return adminMapper.allUserList(searchPageDto);
    }

    public int countUser(){return adminMapper.countAllUser();}

    @Override
    public ResponseEntity<? super SanctionedUserResponseDto> sanctionUser(String id, Long userNo) {
//        Long adminNo = userRepository.findUserNoByUserId(id);

        try{
            if(reportMapper.alreadySanctionedUser(userNo)) return SanctionedUserResponseDto.alreadySanctionedUser();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();

        }
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        reportMapper.sanctionUser(userNo, localDateTime);


        return SanctionedUserResponseDto.success();
    }
    @Override
    public ResponseEntity<? super SanctionedUserResponseDto> disSanctionUser(String id, Long userNo) {

        try{
            if(!reportMapper.alreadySanctionedUser(userNo)) return SanctionedUserResponseDto.alreadySanctionedUser();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();

        }

        reportMapper.desanctionUser(userNo);
        return SanctionedUserResponseDto.success();
    }

    //콘텐츠 제재
    @Override
    public ResponseEntity<? super SanctionContentResponseDto> sanctionContent(Long reportedContentNo) {
        try {
            if(!reportMapper.alreadySanctionContent(reportedContentNo)) return SanctionContentResponseDto.alreadySanctionContent();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        reportMapper.sanctionContent(reportedContentNo,localDateTime);
        return SanctionContentResponseDto.success();
    }

    //콘텐츠 제재 해제
    @Override
    public ResponseEntity<? super SanctionContentResponseDto> disSanctionContent(Long reportedContentNo) {

        try {
            if (!reportMapper.alreadySanctionContent(reportedContentNo))
                return SanctionContentResponseDto.alreadySanctionContent();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        reportMapper.desanctionContent(reportedContentNo);
        return SanctionContentResponseDto.success();
    }

}
