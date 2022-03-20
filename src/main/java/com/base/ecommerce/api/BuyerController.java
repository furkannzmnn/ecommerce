package com.base.ecommerce.api;

import com.base.ecommerce.model.user.Buyer;
import com.base.ecommerce.service.BuyerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/buyer")
public class BuyerController extends BaseRestController{

    private final BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @PostMapping("/")
    public void createBuyer(Buyer buyer) {
        buyerService.createBuyerAccount(buyer);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listBuyers(int id) {
        return ResponseEntity.ok(this.buyerService.listOfBuyersType(id));
    }
}
