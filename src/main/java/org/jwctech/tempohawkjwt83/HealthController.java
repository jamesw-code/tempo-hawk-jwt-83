package org.jwctech.tempohawkjwt83;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthController {

    @RequestMapping("/health-check")
    public ResponseEntity<?> home() {
        return ResponseEntity.ok().body("Ok");
    }
}
