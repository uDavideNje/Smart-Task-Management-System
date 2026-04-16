package me.david.smart.task.management.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    /*
     * TODO: Implement when Spring Security + JWT is added
     *
     * POST /auth/register — hash password, save user, return JWT
     * POST /auth/login    — validate credentials, return JWT
     * POST /auth/refresh  — validate refresh token, return new JWT
     */
}
