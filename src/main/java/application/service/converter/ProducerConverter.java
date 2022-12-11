package application.service.converter;

import application.model.dao.ProducerDao;
import application.model.dto.ProducerDto;
import org.springframework.stereotype.Service;

@Service
public class ProducerConverter implements Convertible<ProducerDto, ProducerDao> {
    @Override
    public ProducerDto toDto(final ProducerDao producerDao) {
        ProducerDto producerDto = new ProducerDto();
        producerDto.setId(producerDao.getId());
        producerDto.setName(producerDao.getName());
        producerDto.setProducts(producerDao.getProducts());
        return producerDto;
        /*return new ProducerDto(
                producerDao.getId(),
                producerDao.getName(),
                producerDao.getProducts()
        );*/
    }

    @Override
    public ProducerDao toDao(final ProducerDto producerDto) {
        return new ProducerDao(
                producerDto.getId(),
                producerDto.getName(),
                producerDto.getProducts()
        );
    }
}
