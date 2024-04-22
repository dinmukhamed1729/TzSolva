package kz.din.transactions.controller;

import kz.din.transactions.model.dto.SetLimitDTO;
import kz.din.transactions.model.dto.UserDTO;
import kz.din.transactions.service.LimitChangesService;
import kz.din.transactions.service.UserService;
import kz.din.transactions.service.implementation.ApiConnection;
import kz.din.transactions.service.implementation.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final ApiConnection apiConnection;
    private final LimitChangesService limitChangesService;
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.  class);

    @PostMapping("/createNewUser")
    public ResponseEntity<String> createNewUser(@RequestBody UserDTO userDTO) throws IOException {

        userService.save(userDTO);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/setLimit")
    public ResponseEntity<String> SetLimit(@RequestBody SetLimitDTO setLimitDTO) throws IOException {
        limitChangesService.setLimit(setLimitDTO,userService.getByBankAccount(setLimitDTO.bankAccount()));
        return ResponseEntity.ok("ok");
    }


}
