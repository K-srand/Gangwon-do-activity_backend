package com.multicampus.gangwonActivity.common;

public interface ResponseMessage {
    // HTTP Status 200
    String SUCCESS = "Success.";

    //HTTP Status 400
    String VALIDATION_FAILED = "Validation failed.";
    String DUPLICATE_EMAIL = "Duplicate email.";
    String DUPLICATE_NICKNAME = "Duplicate nickname.";
    String DUPLICATE_ID = "Duplicate id.";
    String NOT_EXITSTED_USER = "This user does not exist.";
    String NOT_EXISTED_BOARD = "This board does not exist.";

    // HTTP Status 401
    String SIGN_IN_FAIL = "Login information mismatch.";
    String AUTHORIZATION_FAIL = "Authorization failed.";


    //HTTP Status 403
    String NO_PERMISSION = "Do not have permission.";

    //HTTP Status 500
    String DATABASE_ERROR = "Database error.";

    //작성글 좋아요 중복
    String ALREADY_LIKED = "Already liked user";

    //메일 전송 실패
    String MAIL_FAIL = "Mail send failed";


    //인증번호 불일치
    String NUMBER_ERROR = "Cerfification number incorrect";

    String NOT_EXISTED_CONTENT = "this content does not exist.";

    String ALREADY_REPORTED_CONTENT = "this content already reported by you!";

    String ID_NOT_CORRECT ="id is not correct";
}
