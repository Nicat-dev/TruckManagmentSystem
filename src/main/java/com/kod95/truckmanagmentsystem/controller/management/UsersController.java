package com.kod95.truckmanagmentsystem.controller.management;

import com.kod95.truckmanagmentsystem.dto.UsersDto;
import com.kod95.truckmanagmentsystem.dto.request.UserRequest;
import com.kod95.truckmanagmentsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UsersController {

    private final UserService service;


    @GetMapping
    public ResponseEntity<List<UsersDto>> getAll(){
        final var dtoList = service.getList();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDto> get(@PathVariable Long id){
        final var dto = service.get(id);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UsersDto> save(@Valid @RequestBody UserRequest request) throws Exception {
        final var dto = service.save(request);
        final var location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/{id}").build(dto.getId());

        return ResponseEntity.created(location).body(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UsersDto> update(@Valid @RequestBody UserRequest request, @PathVariable Long id){
        final var dto = service.update(id,request);
        final var location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{id}").build(dto.getId());
        return ResponseEntity.created(location).body(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/renewPassword/{id}")
    public ResponseEntity<Void> renewPassword(@PathVariable Long id,@RequestBody String password){
        service.renewPassword(id,password);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/revenue/{id}")
    public ResponseEntity<BigDecimal> getRevenue(@PathVariable Long id){
        final var revenue = service.getRevenue(id);
        return ResponseEntity.ok().body(revenue);
    }
}
