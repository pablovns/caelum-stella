package br.com.caelum.stella.faces.validation;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import javax.el.ELContext;
import javax.faces.validator.Validator;

import org.junit.Test;

/**
 * @author Leonardo Bessa
 */
public class StellaIEValidatorTagTest {

    @SuppressWarnings("serial")
    @Test
    public void shouldReturnTheStellaValidator() throws Exception {
        final ELContext elContext = mock(ELContext.class);
        StellaIEValidatorTag tag = new StellaIEValidatorTag() {
            @Override
            protected ELContext getELContext() {
                return elContext;
            }
        };

        Validator validator = tag.createValidator();
        assertTrue(validator instanceof StellaIEValidator);
    }
}
