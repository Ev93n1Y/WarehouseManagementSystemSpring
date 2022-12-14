package application.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class ErrorsController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView throwErrorPage(HttpServletRequest request) {
        Throwable exception =(Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        ModelAndView errorPage = new ModelAndView("error");
        String message = " Please, check your url and try again";
        int errorCode = Integer.parseInt(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString());

        switch (errorCode) {
            case 401:
                message = "\n403\n";
                break;
            case 403:
                message = "\n403\n";
                break;
            case 400:
                message = "\n400\n" + message;
                break;
            case 404:
                message = "\n404 Page not found!\n" + message;
                break;
            case 500:
                message = "\nInternal Server Error.\n" + message;
                break;
            default:
                message = exception.getMessage() + message;
                break;
        }
        errorPage.addObject("message", message);
        return errorPage;
    }

}
