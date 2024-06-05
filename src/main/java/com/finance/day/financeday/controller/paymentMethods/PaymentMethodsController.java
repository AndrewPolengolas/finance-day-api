package com.finance.day.financeday.controller.paymentMethods;

import com.finance.day.financeday.records.accounts.PaymentMethodRecord;
import com.finance.day.financeday.services.paymentMethods.PaymentMethodService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/payment-method")
public class PaymentMethodsController {

    private final PaymentMethodService paymentMethodService;

    @PostMapping("/create/{accountId}")
    public ResponseEntity<?> addPaymentMethod(@PathVariable("accountId") Long accountId,
                                              @RequestBody @Valid PaymentMethodRecord paymentMethodRecord){
        return paymentMethodService.addPaymentMethod(accountId, paymentMethodRecord);
    }

    @PutMapping("/edit/{paymentMethodId}")
    public ResponseEntity<?> editPaymentMethod(@PathVariable("paymentMethodId") Long paymentMethodId,
                                               @RequestBody @Valid PaymentMethodRecord paymentMethodRecord) {
        return paymentMethodService.editPaymentMethod(paymentMethodId, paymentMethodRecord);
    }

    @DeleteMapping("/delete/{paymentMethodId}")
    public ResponseEntity<?> deletePaymentMethod(@PathVariable("paymentMethodId") Long paymentMethodId) {
        return paymentMethodService.deletePaymentMethod(paymentMethodId);
    }

    @GetMapping("/all/{accountId}")
    public ResponseEntity<?> listAllPaymentMethods(@PathVariable("accountId") Long accountId){
        return paymentMethodService.listAllPaymentMethods(accountId);
    }
}
