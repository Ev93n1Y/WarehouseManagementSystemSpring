package application.service.converter;

import application.model.dao.UserDao;
import application.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserConverter implements Convertible<UserDto, UserDao> {
    @Override
    public UserDto toDto(final UserDao productDao) {
        UserDto userDto = new UserDto();
        userDto.setId(productDao.getId());
        userDto.setEmail(productDao.getEmail());
        userDto.setPassword(productDao.getPassword());
        userDto.setLastName(productDao.getLastName());
        userDto.setFirstName(productDao.getFirstName());
        userDto.setRoles(productDao.getRoles());
        //userDto.setRoles(productDao.getRoles().stream()
        //        .map(new RoleConverter()::toDto)
        //        .collect(Collectors.toSet()));
        return userDto;
    }

    @Override
    public UserDao toDao(final UserDto userDto) {
        return new UserDao(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getLastName(),
                userDto.getFirstName(),
                userDto.getRoles()
                //userDto.getRoles().stream()
                //        .map(new RoleConverter()::toDao)
                //        .collect(Collectors.toSet())
        );
    }
}
