package application.service.converter;

import application.model.dao.UserDao;
import application.model.dto.UserDto;

public class UserConverter implements Convertible<UserDto, UserDao> {
    @Override
    public UserDto toDto(final UserDao productDao) {
        UserDto userDto = new UserDto();
        userDto.setId(productDao.getId());
        userDto.setEmail(productDao.getEmail());
        userDto.setPassword(productDao.getPassword());
        userDto.setLastName(productDao.getLastName());
        userDto.setFirstName(productDao.getFirstName());
        return userDto;
    }

    @Override
    public UserDao toDao(final UserDto userDto) {
        return new UserDao(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getLastName(),
                userDto.getFirstName()
        );
    }
}
