package br.com.caelum.stella.validation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.stella.MessageProducer;
import br.com.caelum.stella.SimpleValidationMessage;
import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.error.LengthError;

public class LengthValidatorTest {

    @Test
    public void shouldAssertValidCorrectLengthStrings() {
        LengthValidator validator = new LengthValidator(2);
        validator.assertValid("23");
    }

    @Test(expected = InvalidStateException.class)
    public void shouldNotAssertValidIncorrectLengthStrings() {
        LengthValidator validator = new LengthValidator(2);
        validator.assertValid("230");
    }

    @Test
    public void shouldAssertValidCorrectLengthIntegers() {
        LengthValidator validator = new LengthValidator(4);
        validator.assertValid(1234);
    }

    @Test(expected = InvalidStateException.class)
    public void shouldNotAssertValidIncorrectLengthIntegers() {
        LengthValidator validator = new LengthValidator(4);
        validator.assertValid(123);
    }

    @Test
    public void shouldReturnCorrectValidationMessage() {
        MessageProducer messageProducer = mock(MessageProducer.class);
        LengthValidator lengthValidator = new LengthValidator(messageProducer, 2);
        String message = "O tamanho da entrada é inválido.";
        ValidationMessage validationMessage = new SimpleValidationMessage(message);
        when(messageProducer.getMessage(new LengthError(2))).thenReturn(validationMessage);

        List<ValidationMessage> invalidMessages = lengthValidator.invalidMessagesFor(12345);
        assertEquals(1, invalidMessages.size());
        assertEquals(message, invalidMessages.get(0).getMessage());

        verify(messageProducer).getMessage(new LengthError(2));
    }

    @Test
    public void shouldReturnPrettySimpleMessageValidation() {
        LengthValidator lengthValidator = new LengthValidator(2);
        List<ValidationMessage> invalidMessages = lengthValidator.invalidMessagesFor(12345);
        Assert.assertEquals(1, invalidMessages.size());
        Assert.assertEquals("LengthError : INVALID LENGTH", invalidMessages.get(0).getMessage());
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void shouldThrowExceptionWhenGeneratingRandomValue() {
    	new LengthValidator(2).generateRandomValid();
    }
}
