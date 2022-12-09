package application.service;

import application.model.dto.ProducerDto;
import application.service.converter.ProducerConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import application.repository.ProducerRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProducerService implements CrudService<ProducerDto> {
    private final ProducerConverter converter;
    private final ProducerRepository repository;

    @Override
    public List<ProducerDto> findAll() {
        return repository.findAll(Sort.by("name"))
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProducerDto findById(UUID id) {
        return repository.findById(id)
                .map(converter::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public ProducerDto save(ProducerDto producerDto) {
        return converter.toDto(repository.save(converter.toDao(producerDto)));
    }

    @Override
    public ProducerDto save(UUID id, ProducerDto producerDto) {
        producerDto.setId(id);
        return converter.toDto(repository.findById(id)
                .map(p -> repository.save(converter.toDao(producerDto)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}