package com.multicampus.gangwonActivity.service.implement;

import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import com.multicampus.gangwonActivity.dto.response.user.GetSignInUserResponseDto;
import com.multicampus.gangwonActivity.entity.UserEntity;
import com.multicampus.gangwonActivity.repository.UserRepository;
import com.multicampus.gangwonActivity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String id) {

        UserEntity userEntity = null;

        try {
            userEntity = userRepository.findByUserId(id);
            if(userEntity==null) return GetSignInUserResponseDto.notExistUser();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInUserResponseDto.success(userEntity);
    }
}
