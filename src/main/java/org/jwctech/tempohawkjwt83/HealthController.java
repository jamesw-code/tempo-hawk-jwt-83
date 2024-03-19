package org.jwctech.tempohawkjwt83;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthController {

    @RequestMapping("/")
    public ResponseEntity<?> zero() {
        return ResponseEntity.ok().body("Ok");
    }

    @RequestMapping("/health-check")
    public ResponseEntity<?> check() {
        return ResponseEntity.ok().body("Ok");
    }
}
