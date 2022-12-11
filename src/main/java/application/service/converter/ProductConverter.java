package application.service.converter;

import application.model.dao.ProducerDao;
import application.model.dao.ProductDao;
import application.model.dto.ProducerDto;
import application.model.dto.ProductDto;
import org.springframework.stereotype.Service;

@Service
public class ProductConverter implements Convertible<ProductDto, ProductDao> {
    @Override
    public ProductDto toDto(final ProductDao productDao) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productDao.getId());
        productDto.setName(productDao.getName());
        productDto.setPrice(productDao.getPrice());
        productDto.setProducer(productDao.getProducer());
        return productDto;
    }

    @Override
    public ProductDao toDao(final ProductDto productDto) {
        return new ProductDao(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getProducer()
        );
        //productDao.setProducer(new ProducerConverter().toDao(productDto.getProducer()));
        //return productDao;
    }
}