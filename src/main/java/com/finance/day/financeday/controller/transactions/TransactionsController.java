//package com.finance.day.financeday.controller.transactions;
//
//import com.finance.day.financeday.entities.User;
//import com.finance.day.financeday.records.transactions.CreateTransactionRecord;
//import com.finance.day.financeday.repositories.UserRepository;
//import com.finance.day.financeday.utils.UserUtils;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/transaction")
//@AllArgsConstructor
//public class TransactionsController {
//
//    private final UserUtils userUtils;
//
//    private UserRepository userRepository;
//
//
//    @PostMapping("/create")
//    public ResponseEntity<?> createTransaction(@RequestBody CreateTransactionRecord createTransactionRecord) {
//
//        UserDetails userDetails = userUtils.getUser();
//
//        if (userDetails == null)
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//
//        User user = (User) userRepository.findByUsername(userDetails.getUsername());
//
//
//    }
//}
