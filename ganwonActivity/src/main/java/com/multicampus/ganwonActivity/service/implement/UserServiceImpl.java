package com.multicampus.ganwonActivity.service.implement;

import com.multicampus.ganwonActivity.dto.response.ResponseDto;
import com.multicampus.ganwonActivity.dto.response.user.GetSignInUserResponseDto;
import com.multicampus.ganwonActivity.entity.UserEntity;
import com.multicampus.ganwonActivity.repository.UserRepository;
import com.multicampus.ganwonActivity.service.UserService;
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
