package ru.Cherkasov.project.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Cherkasov.project.controllers.requestBodies.Rates.ChangeRatesDTO;
import ru.Cherkasov.project.controllers.requestBodies.Rates.CurrentRatesDTO;
import ru.Cherkasov.project.services.AdministrationService;
import ru.Cherkasov.project.services.InformationService;

import java.util.Map;

@RestController
@RequestMapping(value = "/rates")
public class RatesController {
    @Autowired
    private InformationService informationService;
    @Autowired
    private AdministrationService administrationService;
    @GetMapping("/")
    public Map<String,String> getCurrentRates(@RequestBody @Valid CurrentRatesDTO reqBody, Model model){
        Map<String,String> response = informationService.getCurrentExchangeRates(reqBody.getBaseCurrency());
        return response;
    }


    @PostMapping("/set")
    public Map<String,String> setNewRates(@RequestBody ChangeRatesDTO reqBody, Model model){
        //add checking that the requestBody is valid
        return administrationService.changeRates(reqBody);
    }
}
