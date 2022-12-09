package application.controller;

import application.model.dto.ProductDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import application.service.ProducerService;
import application.service.ProductService;

import java.util.UUID;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final ProducerService producerService;

    @GetMapping
    public ModelAndView getProducts() {
        ModelAndView result = new ModelAndView("products");
        result.addObject("products", service.findAll());
        return result;
    }

    @GetMapping("/create")
    public ModelAndView getCreateForm() {
        ModelAndView result = new ModelAndView("createProductForm");
        result.addObject("producers", producerService.findAll());
        return result;
    }

    @GetMapping("/{id}")
    public ModelAndView getProductById(@PathVariable("id") UUID id) {
        ModelAndView result = new ModelAndView("productEditForm");
        result.addObject("product", service.findById(id));
        result.addObject("producers", producerService.findAll());
        return result;
    }

    @PutMapping("/{id}")
    public ProductDto update(/*@Valid*/ @RequestBody ProductDto productDto, /*BindingResult result,*/
                                        @PathVariable("id") UUID id) {
       /* if (result.hasErrors()){
            return "productEditForm";
        }*/

        //productDto.setProducer(producerService.findById(productDto.getProducer_id()));
        return service.save(id, productDto);
    }

    @PostMapping("/create")
    public ProductDto create(@Valid @ModelAttribute("product") ProductDto productDto/*, BindingResult result*/) {
        productDto.setProducer(producerService.findById(productDto.getProducer_id()));
        return service.save(productDto);
        //return "redirect:/products/" + id;
    }

    @DeleteMapping("/{id}")
    public ProductDto delete(@PathVariable("id") UUID id) {
        ProductDto dto = service.findById(id);
        try {

            service.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //(dto==null)? new ProductDto() : service.deleteById(id);
        //return service.deleteById(id);
        //return getProducts();
        return (dto == null) ? new ProductDto() : dto;
    }

    @ModelAttribute("product")
    private ProductDto getDefaultProduct() {
        return new ProductDto();
    }

}
