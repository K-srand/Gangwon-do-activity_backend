package com.multicampus.gangwonActivity.common;

public interface ResponseCode {
    // HTTP Status 200
    String SUCCESS = "SU";

    // HTTP Status 400
    String VALIDATION_FAILED = "VF";
    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_NICKNAME = "DN";
    String DUPLICATE_ID = "DI";
    String NOT_EXITSTED_USER = "NU";
    String NOT_EXISTED_BOARD = "NB";
    String INVALID_COURSE_DATA = "ICD";

    // HTTP Status 401
    String SIGN_IN_FAIL = "SF";
    String AUTHORIZATION_FAIL = "AF";
    String INVALID_USER = "IU";

    // HTTP Status 403
    String NO_PERMISSION = "NP";

    // HTTP Status 409 (Conflict)
    String COURSE_EXISTS = "CE";

    // HTTP Status 500
    String DATABASE_ERROR = "DBE";

    String ALREADY_LIKED = "AL";

    String MAIL_FAIL = "MF";

    String NUMBER_ERROR = "NE";

    String NOT_EXISTED_CONTENT = "NC";
    String ALREADY_REPORTED_CONTENT = "AR";
    //제재 -> 이미 제재된 유저
    String ALREADY_SANCTIONED_USER = "AS";
    //콘첸츠 제재
    String ALREADY_SANCTIONED_CONTENT = "AC";
    //아이디 변경 -> 아이디 불일치
    String ID_NOT_CORRECT ="IC";

    // 찜 중복
    String FAVORITE_EXISTS = "FE";

    //세션 값 널
    String SESSION_NULL = "SN";
}
