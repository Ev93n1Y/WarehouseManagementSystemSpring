package application.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

public interface CrudController<T> {
    ModelAndView get();

    RedirectView add(T t);

    RedirectView delete(UUID id);

    RedirectView update(UUID id);
}
