package com.flower.shop.application.domain.services;
import com.flower.shop.application.dto.ComplaintDto;
import com.flower.shop.application.dto.mapper.ComplaintMapper;
import com.flower.shop.data.dao.ClientDAO;
import com.flower.shop.data.dao.ComplaintDAO;
import com.flower.shop.application.dto.ProductDto;
import com.flower.shop.application.dto.mapper.ProductMapper;
import com.flower.shop.data.dao.ProductDAO;
import com.flower.shop.data.models.Client;
import com.flower.shop.data.models.Complaint;
import com.flower.shop.data.models.Person;
import com.flower.shop.data.models.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ComplaintService {
    @Autowired
    private ComplaintDAO complaintRepository;

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private ClientDAO clientRepository;
    @Autowired
    private ComplaintMapper complaintMapper;

    public ComplaintDto createComplaint(ComplaintDto complaint){
        Client client = (Client)clientDAO.findById(complaint.getClientId()).get();
        Complaint newComplaint = initComplaints(client, complaint);
        complaintRepository.save(newComplaint);
        return complaintMapper.toDto(newComplaint);
    }

    private Complaint initComplaints(Client client, ComplaintDto complaint){
        Complaint complaint1 = new Complaint();
        complaint1.setClient(client);
        complaint1.setTopic(complaint.getTopic());
        complaint1.setDescription(complaint.getDescription());
        return complaint1;
    }

}
