package application.service.converter;

import application.model.dao.UserDao;
import application.model.dto.RoleDto;
import application.model.dao.RoleDao;
import application.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RoleConverter implements Convertible<RoleDto, RoleDao> {
    @Override
    public RoleDto toDto(RoleDao roleDao) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(roleDao.getId());
        roleDto.setRole(roleDao.getRole());
        roleDto.setRole(roleDto.getRole());
        //roleDto.setUsers(roleDao.getUsers().stream().map(new UserConverter()::toDto).collect(Collectors.toSet()));
        return roleDto;
    }

    @Override
    public RoleDao toDao(RoleDto roleDto) {
        return new RoleDao(
                roleDto.getId(),
                roleDto.getRole(),
                roleDto.getUsers()
                //roleDto.getUsers().stream().map(new UserConverter()::toDao).collect(Collectors.toSet())
        );
    }
}
