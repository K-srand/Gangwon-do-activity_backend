package com.multicampus.gangwonActivity.service.implement;

import com.multicampus.gangwonActivity.dto.request.mypage.CheckPasswordRequestDto;
import com.multicampus.gangwonActivity.dto.request.mypage.ModifyMyInfoRequestDto;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.GetBoardListResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.dto.response.mypage.GetMyFavoritesListResponseDto;
import com.multicampus.gangwonActivity.dto.response.mypage.ModMyInfoResponseDto;
import com.multicampus.gangwonActivity.dto.response.mypage.ModifyMyInfoResponseDto;
import com.multicampus.gangwonActivity.dto.response.mypage.MyPageResponseDto;
import com.multicampus.gangwonActivity.entity.User;
import com.multicampus.gangwonActivity.mapper.AdminMapper;
import com.multicampus.gangwonActivity.mapper.BoardMapper;
import com.multicampus.gangwonActivity.mapper.MyFavoriteMapper;
import com.multicampus.gangwonActivity.repository.BoardRepository;
import com.multicampus.gangwonActivity.repository.UserRepository;
import com.multicampus.gangwonActivity.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final MyFavoriteMapper myFavoriteMapper;
    private final BoardMapper boardMapper;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public List<GetBoardListResponseDto> getMyPageBoard(String id,  SearchPageDto searchPageDto) {
        Long userNo = userRepository.findUserNoByUserId(id);
        return boardMapper.getBoardListByUserNoPaged(userNo, searchPageDto);
    }


    // 유저 아이디로 쓴 게시글 개수 찾기
    @Override
    public Integer countBoardByUserId(String id) {
        Long userNo = userRepository.findUserNoByUserId(id);
        return boardMapper.getBoardListByUserNo(userNo).size();
    }

    @Override
    public ResponseEntity<? super MyPageResponseDto> deleteMyFavorites(Long placeNo) {
        try{

            myFavoriteMapper.deleteMyFavoritesByPlaceNo(placeNo);

        }catch (Exception e){
            e.printStackTrace();
            return MyPageResponseDto.databaseError();
        }
        return MyPageResponseDto.success();
    }



    @Override
    public List<GetMyFavoritesListResponseDto> getMyFavorites(String id, SearchPageDto searchPageDto) {
        Long userNo = userRepository.findUserNoByUserId(id);
        return myFavoriteMapper.findMyFavoritesByUserNo(userNo, searchPageDto);
    }

    @Override
    public Integer countAllMyFavorites(String id) {
        Long userId = userRepository.findUserNoByUserId(id);
        return myFavoriteMapper.getMyFavoritesByUserNo(userId).size();
    }

    @Override
    public ResponseEntity<? super ModifyMyInfoResponseDto> modifyMyInfo(String id, ModifyMyInfoRequestDto dto) {
        try {
            String modifyPassword = dto.getUserPassword();
            String modifyUserNick = dto.getUserNick();

            User user = userRepository.findByUserId(id);
            if(user == null) {
                return ModifyMyInfoResponseDto.notExistUser();
            }

            if(modifyPassword != null && modifyPassword !="") {
                String encodedChangePassword = passwordEncoder.encode(modifyPassword);
                user.ModifyPassword(encodedChangePassword);
                userRepository.save(user);
            }

            if(modifyUserNick != null && modifyUserNick !=""){
                user.ModifyUserNick(modifyUserNick);
                userRepository.save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ModifyMyInfoResponseDto.success();
    }


    @Override
    public ResponseEntity<? super ModifyMyInfoResponseDto> deleteMyInfo(String id, ModifyMyInfoRequestDto dto) {
        try {
            User user = userRepository.findByUserId(id);
            if(user == null) {
                return ModifyMyInfoResponseDto.notExistUser();
            }
            String userId = dto.getUserId();
            String UserId = user.getUserId();

            if(!UserId.equals(userId)) return ModifyMyInfoResponseDto.idError();

            LocalDateTime ExitTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
            user.ExitTime(ExitTime);
            userRepository.save(user);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ModifyMyInfoResponseDto.success();
    }

    @Override
    public ModMyInfoResponseDto modMyInfo(String id) {

        Long userNo = userRepository.findUserNoByUserId(id);

        return adminMapper.userInfo(userNo);
    }

    @Override
    public ResponseEntity<? super ModifyMyInfoResponseDto> checkPassword(String id, CheckPasswordRequestDto dto) {
        try {
            User user =userRepository.findByUserId(id);
            if (user==null) return ModifyMyInfoResponseDto.notExistUser();

            boolean isMatch = passwordEncoder.matches(dto.getUserPassword(), user.getUserPassword());
            if(!isMatch) return ModifyMyInfoResponseDto.passwordInFailed();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ModifyMyInfoResponseDto.success();
    }


    // 내 추천 코스 ( 민호형 & 수지 )
    @Override
    public List<GetMyPageCourseResponseDto> getMyCourse(String userId) {
        Long userNo = userRepository.findUserNoByUserId(userId);
        return myFavoriteMapper.findMyCourse(userNo);
    }

    @Override
    public int countMyCourse(String userId) {
        return myFavoriteMapper.countMyCourse(myFavoriteMapper.selectUserNo(userId));
    }

    @Override
    public ResponseEntity<? super MyPageResponseDto> deleteMyCourse(Long myCourseNo) {
        try{
            myFavoriteMapper.deleteMyCourse(myCourseNo);

        }catch (Exception e){
            e.printStackTrace();
            return MyPageResponseDto.databaseError();
        }
        return MyPageResponseDto.success();
    }

    @Override
    public int getUserExp(String id) {
        Long userNo = myFavoriteMapper.selectUserNo(id);
        return myFavoriteMapper.getMyExp(userNo);
    }

}
