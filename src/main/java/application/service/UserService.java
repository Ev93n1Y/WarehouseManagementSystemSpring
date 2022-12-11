package application.service;

import application.model.dto.UserDto;
import application.repository.UserRepository;
import application.service.converter.UserConverter;
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
public class UserService implements CrudService<UserDto>{
    private final UserConverter converter;
    private final UserRepository repository;

    @Override
    public List<UserDto> findAll() {
        return repository.findAll(Sort.by("email"))
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(UUID id) {
        return repository.findById(id)
                .map(converter::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public UserDto save(UserDto userDto) {
        return converter.toDto(repository.save(converter.toDao(userDto)));
    }

    @Override
    public UserDto save(UUID id, UserDto userDto) {
        return converter.toDto(repository.findById(id)
                .map(p -> repository.save(converter.toDao(userDto)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
