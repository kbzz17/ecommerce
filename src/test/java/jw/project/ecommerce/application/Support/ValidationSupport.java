package jw.project.ecommerce.application.Support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Component
public class ValidationSupport {
    @Autowired
    public Validator validatorInjected;

    public <T> List<String> getValidationMessage(T request) {
        Set<ConstraintViolation<T>> validate = validatorInjected.validate(request);
        // then

        Iterator<ConstraintViolation<T>> iterator = validate.iterator();

        List<String> messages = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> next = iterator.next();
            messages.add(next.getMessage());
        }
        return messages;
    }
}
