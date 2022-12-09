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
        return producerDto;
    }

    @Override
    public ProducerDao toDao(final ProducerDto producerDto) {
        return new ProducerDao(
                producerDto.getId(),
                producerDto.getName()
        );
    }
}
