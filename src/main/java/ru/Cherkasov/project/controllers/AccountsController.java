package ru.Cherkasov.project.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Cherkasov.project.controllers.requestBodies.Accounts.*;
import ru.Cherkasov.project.exceptions.UserAlreadyExistsException;
import ru.Cherkasov.project.exceptions.UserNotFoundException;
import ru.Cherkasov.project.services.AccountService;

import java.util.Map;


@RestController
@RequestMapping(value = "/account")
public class AccountsController {
    @Autowired
    AccountService accountService;


    @PostMapping("/register")
    public Map<String,String> registerNewUser(@RequestBody @Valid RegisterUserDTO reqBody, Model model){
        if(!accountService.UserExists(reqBody.getEmail(), reqBody.getUsername()))
            return accountService.createNewUser(reqBody);
        else{
            throw new UserAlreadyExistsException("Email or username is occupied");
        }
    }
    @GetMapping("/wallet")
    public Map<String,String> getWalletBalance(@RequestBody @Valid GetWalletBalanceDTO reqBody, Model model){
        if(accountService.UserExists(reqBody.getSecretKey()))
            return accountService.getWalletOfUser(reqBody);
        else throw new UserNotFoundException(reqBody.getSecretKey() + " doesn't correspond to any user");
    }
    @PostMapping("/deposit")
    public Map<String,String> depositToUserWallet(@RequestBody DepositToUserWalletDTO reqBody, Model model){
        if(accountService.UserExists(reqBody.getSecretKey()))
            return accountService.depositMoneyToUser(reqBody);
        else throw new UserNotFoundException(reqBody.getSecretKey() + " doesn't correspond to any user");
    }
    @PostMapping("/withdraw")
    public Map<String, String> withdrawFromUserWallet(@RequestBody WithdrawFromUserWalletDTO reqBody, Model model){
        if(accountService.UserExists(reqBody.getSecretKey()))
                return accountService.withdrawMoneyFromUser(reqBody);
        else throw new UserNotFoundException(reqBody.getSecretKey() + " doesn't correspond to any user");
    }

    @PostMapping("/exchange")
    public Map<String,String> exchangeCurrenciesInUserWallet(@RequestBody ExchangeCurrencyDTO reqBody, Model model){
        if(accountService.UserExists(reqBody.getSecretKey()))
                return accountService.exchangeMoneyOfUser(reqBody);
        else throw new UserNotFoundException(reqBody.getSecretKey() + " doesn't correspond to any user");
    }

}
