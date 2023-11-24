package com.shopzay.shopzaybackend.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordTest {
    @Test
    public void encoderPass(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawpass ="Phanvannguyen";
        String passEncode = bCryptPasswordEncoder.encode(rawpass);
        System.out.println(passEncode);
        boolean masschest = bCryptPasswordEncoder.matches(rawpass,passEncode);
        assertThat(masschest).isTrue();
    }
}
