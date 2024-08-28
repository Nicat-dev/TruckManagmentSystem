package com.kod95.truckmanagmentsystem.controller.management;

import com.kod95.truckmanagmentsystem.dto.CustomerDto;
import com.kod95.truckmanagmentsystem.dto.CustomerStatusDto;
import com.kod95.truckmanagmentsystem.dto.request.CustomerRequest;
import com.kod95.truckmanagmentsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<CustomerDto> save(@RequestBody CustomerRequest request) {
        final var dto = service.save(request);
        final var location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{id}").build(dto.getId());

        return ResponseEntity.created(location).body(dto);
    }

    @PostMapping("/status/{id}")
    public ResponseEntity<CustomerStatusDto> statusChanger(@RequestBody CustomerRequest request, @PathVariable Long id) {
        final var status = service.statusChanger(id, request.customerStatus());
        final var location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").build(id);
        return ResponseEntity.created(location).body(status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerRequest request, @PathVariable Long id) {
        final var dto = service.update(id, request);
        final var location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{id}").build(dto.getId());
        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        service.activate(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivate(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable Long id) {
        final var dto = service.get(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        final var dtoList = service.getAll();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/activeList")
    public ResponseEntity<List<CustomerDto>> getAllActive(){
        final var dtoList = service.getAllActive();
        return ResponseEntity.ok().body(dtoList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/inactiveList")
    public ResponseEntity<List<CustomerDto>> getAllInactive(){
        final var dtoList = service.getAllInactive();
        return ResponseEntity.ok().body(dtoList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
