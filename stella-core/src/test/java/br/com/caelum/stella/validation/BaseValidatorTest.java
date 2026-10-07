package br.com.caelum.stella.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.caelum.stella.MessageProducer;
import br.com.caelum.stella.ValidationMessage;

public class BaseValidatorTest {

    @Test
    public void testGetValidationMessagesT() {
        MessageProducer messageProducer = mock(MessageProducer.class);
        InvalidValue invalidValue = mock(InvalidValue.class);
        ValidationMessage validationMessage = mock(ValidationMessage.class);
        when(messageProducer.getMessage(invalidValue)).thenReturn(validationMessage);

        BaseValidator validator = new BaseValidator(messageProducer);

        List<InvalidValue> invalidValues = Arrays.asList(invalidValue);
        List<ValidationMessage> actual = validator.generateValidationMessages(invalidValues);
        List<ValidationMessage> expected = new ArrayList<ValidationMessage>();
        expected.add(validationMessage);
        assertEquals(expected, actual);

        verify(messageProducer).getMessage(invalidValue);
    }

    @Test
    public void testAssertValidShouldThrowInvalidStateExpectionWhenComesAnInvalidValue() {
        MessageProducer messageProducer = mock(MessageProducer.class);
        InvalidValue invalidValue = mock(InvalidValue.class);
        ValidationMessage validationMessage = mock(ValidationMessage.class);
        when(messageProducer.getMessage(invalidValue)).thenReturn(validationMessage);

        BaseValidator validator = new BaseValidator(messageProducer);
        try {
            List<InvalidValue> invalidValues = Arrays.asList(invalidValue);
            validator.assertValid(invalidValues);
            fail();
        } catch (InvalidStateException e) {
            List<ValidationMessage> messages0 = e.getInvalidMessages();
            List<ValidationMessage> messages1 = new ArrayList<ValidationMessage>();
            messages1.add(validationMessage);
            assertEquals(messages0, messages1);
        } catch (Exception e) {
            fail();
        }

        verify(messageProducer).getMessage(invalidValue);
    }

    @Test
    public void testAssertValidShouldNotThrowInvalidStateExpectionWhenValueIsValid() {
        MessageProducer messageProducer = mock(MessageProducer.class);

        BaseValidator validator = new BaseValidator(messageProducer);
        try {
            List<InvalidValue> invalidValues = new ArrayList<InvalidValue>();
            validator.assertValid(invalidValues);
        } catch (InvalidStateException e) {
            fail();
        }
    }

}
