package com.qrbats.qrbats.authentication.controller.mobile;

import com.qrbats.qrbats.authentication.dto.JwtAuthenticationResponse;
import com.qrbats.qrbats.authentication.dto.RefreshTokenRequest;
import com.qrbats.qrbats.authentication.dto.mobile.*;
import com.qrbats.qrbats.authentication.entities.student.Student;
import com.qrbats.qrbats.authentication.services.mobile.MobileAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mobile")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StudentAuthenticationController {

    private final MobileAuthenticationService mobileAuthenticationService;

    @PostMapping("/signup")
    public ResponseEntity<Student> signup(@RequestBody StudentSignUpRequest signUpRequest){
        return ResponseEntity.ok(mobileAuthenticationService.signup(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody StudentSigninRequest signinRequest){
        return ResponseEntity.ok(mobileAuthenticationService.signin(signinRequest));
    }

    @PostMapping("/checkstudentemail")
    public ResponseEntity<Boolean> checkStudentEmailIsExist(@RequestBody StudentCheckStudentEmailRequest studentCheckStudentEmailRequest){
        return ResponseEntity.ok(mobileAuthenticationService.checkStudentIsExist(studentCheckStudentEmailRequest.getStudentEmail()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(mobileAuthenticationService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/getallstudents")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(mobileAuthenticationService.getAllStudent());
    }


    @PostMapping("/checkstudentindexno")
    public ResponseEntity<Boolean> checkStudentIndexNoIsExist(@RequestBody StudentCheckStudentIndexNoRequest studentCheckStudentIndexNoRequest){
        return ResponseEntity.ok(mobileAuthenticationService.checkIndexNoIsExist(studentCheckStudentIndexNoRequest.getStudentIndexNo()));
    }

    @PostMapping("/checkstudentusername")
    public ResponseEntity<Boolean> checkStudentUserNameIsExist(@RequestBody StudentCheckUserNameRequest studentCheckUserNameRequest){
        return ResponseEntity.ok(mobileAuthenticationService.checkUserNameIsExist(studentCheckUserNameRequest.getStudentUserName()));
    }

    @PutMapping("/updatestudent")
    public void updateStudent(@RequestBody StudentUpdateRequest studentUpdateRequest){
        mobileAuthenticationService.updateStudentDetails(studentUpdateRequest);
    }



}
