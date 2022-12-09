package application.controller;

import application.model.dto.ProducerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import application.service.ProducerService;

import java.util.UUID;

@Controller
@RequestMapping("/producers")
@RequiredArgsConstructor
@RestController
public class ProducerController {
    private final ProducerService service;

    @GetMapping
    public ModelAndView getProducers() {
        ModelAndView result = new ModelAndView("producers");
        result.addObject("producers", service.findAll());
        return result;
    }

    @GetMapping("/create")
    public String getCreateForm() {
        return "createProducerForm";
    }

    @GetMapping("/{id}")
    public ModelAndView getProducersById(@PathVariable("id") UUID id) {
        ModelAndView result = new ModelAndView("producerEditForm");
        result.addObject("producer", service.findById(id));
        return result;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ProducerDto update(/*@Valid*/  @RequestBody ProducerDto producerDto, /*BindingResult result,*/
                         @PathVariable("id") UUID id) {
        //if (result.hasErrors()) {
        //    return "producerEditForm";
        //}
        return service.save(id, producerDto);
        //return "producerEditForm";
        //return producerDto;
    }

    @PostMapping("/create")
    public ProducerDto create(/*@Valid*/ @ModelAttribute("producer") ProducerDto producerDto/*, BindingResult result*/) {
        /*if (result.hasErrors()) {
            return "createProducerForm";
        }*/
        return service.save(producerDto);
        //return "redirect:/producers/" + id;
        //return producerDto;
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable("id") UUID id) {
        service.deleteById(id);
        return getProducers();
    }

    @ModelAttribute("producer")
    public ProducerDto getDefaultProducer() {
        return new ProducerDto();
    }

}
