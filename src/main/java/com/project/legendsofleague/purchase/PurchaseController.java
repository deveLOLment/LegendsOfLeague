package com.project.legendsofleague.purchase;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

    @Operation(summary = "테스트 컨트롤러입니다.")
    @GetMapping("/purchase/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("test body", HttpStatus.OK);
    }
}
