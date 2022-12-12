package application.service.converter;

import application.model.dao.ProducerDao;
import application.model.dao.ProductDao;
import application.model.dto.ProducerDto;
import application.model.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProducerConverter implements Convertible<ProducerDto, ProducerDao> {
    @Override
    public ProducerDto toDto(final ProducerDao producerDao) {
        ProducerDto producerDto = new ProducerDto();
        producerDto.setId(producerDao.getId());
        producerDto.setName(producerDao.getName());
        producerDto.setProducts(producerDao.getProducts());
        //producerDto.setProducts(producerDao.getProducts().stream()
        //        .map(new ProductConverter()::toDto)
        //        .collect(Collectors.toSet()));
        return producerDto;
    }

    @Override
    public ProducerDao toDao(final ProducerDto producerDto) {
        return new ProducerDao(
                producerDto.getId(),
                producerDto.getName(),
                producerDto.getProducts()
                //producerDto.getProducts().stream()
                //        .map(new ProductConverter()::toDao)
                //        .collect(Collectors.toSet())
        );
    }
}
