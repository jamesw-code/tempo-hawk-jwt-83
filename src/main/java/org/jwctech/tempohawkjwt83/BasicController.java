package org.jwctech.tempohawkjwt83;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BasicController {

    @RequestMapping("/")
    public String home() {
        return "Home";
    }
}
