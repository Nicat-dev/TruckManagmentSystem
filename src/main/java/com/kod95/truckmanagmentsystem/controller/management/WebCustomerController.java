package com.kod95.truckmanagmentsystem.controller.management;

import com.kod95.truckmanagmentsystem.dto.WebCustomerDto;
import com.kod95.truckmanagmentsystem.dto.request.WebCustomerRequest;
import com.kod95.truckmanagmentsystem.service.WebCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/webCustomer")
@RequiredArgsConstructor
public class WebCustomerController {

    private final WebCustomerService service;

    @GetMapping("/{id}")
    public ResponseEntity<WebCustomerDto> get(@PathVariable Long id){
        final var dto = service.get(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<List<WebCustomerDto>> getAll(){
        final var dtoList = service.getAll();
        return ResponseEntity.ok().body(dtoList);
    }

    @PostMapping
    public ResponseEntity<WebCustomerDto> save(@RequestBody WebCustomerRequest request){
        final var dto = service.save(request);
        final var location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
                .build(dto.getId());
        return ResponseEntity.created(location).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
