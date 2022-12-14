package application.service;

import application.model.dto.RoleDto;
import application.repository.RoleRepository;
import application.service.converter.RoleConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService{
    private final RoleConverter converter;
    private final RoleRepository repository;

    public List<RoleDto> findAll() {
        return repository.findAll()
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    public RoleDto findById(UUID id) {
        return repository.findById(id)
                .map(converter::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public RoleDto findByRole(String role) {
        return converter.toDto(repository.findByRole(role));
    }
}
