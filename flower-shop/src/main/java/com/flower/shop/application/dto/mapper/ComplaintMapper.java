package com.flower.shop.application.dto.mapper;

import com.flower.shop.application.dto.ComplaintDto;
import com.flower.shop.application.dto.ProductDto;
import com.flower.shop.data.models.Complaint;
import com.flower.shop.data.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ComplaintMapper {

    public ComplaintDto toDto(Complaint complaint) {
        ComplaintDto complaintDto = new ComplaintDto();
        complaintDto.setDescription(complaint.getDescription());
        complaintDto.setTopic(complaint.getTopic());
        complaintDto.setClientId(complaint.getClient().getId());
        return complaintDto;

    }

}
