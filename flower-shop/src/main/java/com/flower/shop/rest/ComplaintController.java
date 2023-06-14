package com.flower.shop.rest;


import com.flower.shop.application.domain.services.ClientService;
import com.flower.shop.application.domain.services.ComplaintService;
import com.flower.shop.application.dto.AddressDto;
import com.flower.shop.application.dto.ComplaintDto;
import com.flower.shop.application.dto.ComplaintDto;
import com.flower.shop.application.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/complaint")
@Slf4j
public class ComplaintController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ComplaintService complaintService;

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<ComplaintDto> createComplaint(@RequestBody ComplaintDto complainDto) {
        return new ResponseEntity<>(complaintService.createComplaint(complainDto), HttpStatus.CREATED);
    }
}
