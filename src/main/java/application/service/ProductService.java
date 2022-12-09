package application.service;

import application.model.dto.ProductDto;
import application.repository.ProductRepository;
import application.service.converter.ProductConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements CrudService<ProductDto> {
    private final ProductConverter converter;
    private final ProductRepository repository;

    @Override
    public List<ProductDto> findAll() {
        return repository.findAll(Sort.by("name"))
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(UUID id) {
        return repository.findById(id)
                .map(converter::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        return converter.toDto(repository.save(converter.toDao(productDto)));
    }

    @Override
    public ProductDto save(UUID id, ProductDto productDto) {
        productDto.setId(id);
        return converter.toDto(repository.findById(id)
                .map(p -> repository.save(converter.toDao(productDto)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
