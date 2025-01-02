package com.multicampus.gangwonActivity.provider;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailProvider {

    private final JavaMailSender javaMailSender;

    //인증번호 발송
    public Boolean sendCertificationMail(String email, String certificationNumber) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            String htmlContent = getCertificationMessage(certificationNumber);

            messageHelper.setTo(email);
            //이메일 제목
            String title = "[강원 액티비티 강추!] 인증메일입니다.";
            messageHelper.setSubject(title);
            messageHelper.setText(htmlContent, true);
            message.setFrom("2125ks@naver.com");
            javaMailSender.send(message);

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    //아이디 발송
    public Boolean sendFindIdMail(String email,String userName, String userId) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            String htmlContent = getFindMessage(userId, userName);

            messageHelper.setTo(email);
            String findIdTitle = "[강원 액티비티 강추!] 아이디찾기 메일입니다.";
            messageHelper.setSubject(findIdTitle);
            messageHelper.setText(htmlContent, true);
            message.setFrom("2125ks@naver.com");
            javaMailSender.send(message);

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;

    }

    //변경된 비밀번호 발송
    public Boolean sendFindPwdMail(String email, String userId, String userPassword) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            String htmlContent = getFindMessage(userPassword, userId);

            messageHelper.setTo(email);
            String findPwdTitle = "[강원 액티비티 강추!] 임시비밀번호 발급 메일입니다.";
            messageHelper.setSubject(findPwdTitle);
            messageHelper.setText(htmlContent, true);
            message.setFrom("2125ks@naver.com");
            javaMailSender.send(message);

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;

    }

    //인증번호 발송 내용
    private String getCertificationMessage(String certificationNumber) {

        String certificationMessage = "";
        certificationMessage += "<div style='font-family: Arial, sans-serif; text-align: center;'>";
        certificationMessage += "<div style='background-color: #d5e6f5; color: white; text-align: center; padding: 20px;'>";
        certificationMessage += "<h1 style='margin: 0;'>액티비티 강추!</h1>";
        certificationMessage += "</div>";
        certificationMessage += "<div style='padding: 20px;'>";
        certificationMessage += "<h2 style='color: #333; text-align: center;'>이메일 인증</h2>";
        certificationMessage += "<p style='text-align: center;'>안녕하세요.</p>";
        certificationMessage += "<p style='text-align: center;'>다음 코드를 입력하여 등록하세요:</p>";
        certificationMessage += "<h1 style='color: #333; text-align: center;'>" + certificationNumber + "</h1>";
        certificationMessage += "<p style='text-align: center;'>코드를 요청하지 않은 경우 이 이메일을 무시해도 됩니다. 다른 사용자가 실수로 이메일 주소를 입력했을 수 있습니다.</p>";
        certificationMessage += "</div>";
        certificationMessage += "<div style='background-color: #f3f3f3; text-align: center; padding: 20px;'>";
        certificationMessage += "<p>01012345678</p>";
        certificationMessage += "<p>&copy;2024 kkim-ssim-park-lee</p>";
        certificationMessage += "<p>서울시<br>MultiCampus</p>";
        certificationMessage += "</div>";
        certificationMessage += "</div>";
        return certificationMessage;
    }

    //찾은 사용자의 발송 내용
    private String getFindMessage(String userInfo, String userSub) {

        String certificationMessage = "";
        certificationMessage += "<div style='font-family: Arial, sans-serif; text-align: center;'>";
        certificationMessage += "<div style='background-color: #d5e6f5; color: white; text-align: center; padding: 20px;'>";
        certificationMessage += "<h1 style='margin: 0;'>액티비티 강추!</h1>";
        certificationMessage += "</div>";
        certificationMessage += "<div style='padding: 20px;'>";
        certificationMessage += "<h2 style='color: #333; text-align: center;'>이메일 인증</h2>";
        certificationMessage += "<p style='text-align: center;'>안녕하세요."+userSub+"님</p>";
        certificationMessage += "<p style='text-align: center;'>귀하의 인증정보입니다</p>";
        certificationMessage += "<h1 style='color: #333; text-align: center;'>" + userInfo + "</h1>";
        certificationMessage += "</div>";
        certificationMessage += "<div style='background-color: #f3f3f3; text-align: center; padding: 20px;'>";
        certificationMessage += "<p>01012345678</p>";
        certificationMessage += "<p>&copy;2024 kkim-ssim-park-lee</p>";
        certificationMessage += "<p>서울시<br>MultiCampus</p>";
        certificationMessage += "</div>";
        certificationMessage += "</div>";
        return certificationMessage;
    }
}

