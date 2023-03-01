package ru.Cherkasov.project.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.Cherkasov.project.controllers.requestBodies.Statistics.TransactionStatisticsDTO;
import ru.Cherkasov.project.controllers.requestBodies.Statistics.WalletStatisticsDTO;
import ru.Cherkasov.project.services.AccountService;
import ru.Cherkasov.project.services.AdministrationService;

import java.util.Map;


@RestController
@RequestMapping(value =  "/statistics")
public class StatisticsController {


    @Autowired
    private AdministrationService administrationService;
    @GetMapping("/wallets")
    public Map<String,String> getWalletsStatistics(@RequestBody @Valid WalletStatisticsDTO reqBody, Model model){
        return administrationService.getWalletsStatistics(reqBody);
    }

    @GetMapping("/transactions")
    public Map<String,String> getTransactionStatistics(@RequestBody @Valid TransactionStatisticsDTO reqBody, Model model){
        return administrationService.getTransactionStatistics(reqBody);
    }
}
