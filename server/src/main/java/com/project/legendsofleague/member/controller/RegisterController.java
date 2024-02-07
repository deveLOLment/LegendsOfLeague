package com.project.legendsofleague.member.controller;
import com.project.legendsofleague.member.dto.RegisterDto;
import com.project.legendsofleague.member.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

//    @GetMapping("/register")
//    public Member member(){
//        return null;
//    }

      @PostMapping("/registerProc")
    public void registerProc(@RequestBody RegisterDto dto){

          registerService.joinProcess(dto);
      }
}
