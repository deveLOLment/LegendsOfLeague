package com.project.legendsofleague.domain.member.dto;

import com.project.legendsofleague.domain.member.domain.ROLE;
import com.project.legendsofleague.domain.member.exception.InvalidEmailException;
import com.project.legendsofleague.domain.member.exception.InvalidIdInputException;
import com.project.legendsofleague.domain.member.exception.InvalidPasswordException;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class RegisterDto {
    // 아이디 정규식
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]{5,20}$";

    // 이메일 정규식
    private static final String EMAIL_PATTERN = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,}$";

    // 비밀번호 정규식
    private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$";

    // 아이디 정규식 패턴
    private static final Pattern usernamePattern = Pattern.compile(USERNAME_PATTERN);

    // 이메일 정규식 패턴
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

    // 비밀번호 정규식 패턴
    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);

    // 아이디
    private String username;

    // 비밀번호
    private String password;

    // 닉네임
    private String nickname;

    // 이메일
    private String email;

    // 권한
    private ROLE role;

    // 프론트서버로부터 받아온 정보들에 대해 정규식으로 재검증
    public static void validate(RegisterDto dto) {

        if (!dto.isUsernameValid()) {
            throw new InvalidIdInputException(dto.getUsername());
        }

        if (!dto.isPasswordValid()) {
            throw new InvalidPasswordException(dto.getPassword());
        }

        if (!dto.isEmailValid()) {
            throw new InvalidEmailException(dto.getEmail());
        }
    }

    public boolean isUsernameValid() {
        Matcher matcher = usernamePattern.matcher(username);
        return matcher.matches();
    }

    public boolean isEmailValid() {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    public boolean isPasswordValid() {
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }
}
