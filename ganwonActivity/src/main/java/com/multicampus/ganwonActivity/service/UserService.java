package com.multicampus.ganwonActivity.service;

import com.multicampus.ganwonActivity.dto.response.user.GetSignInUserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String id);


}
