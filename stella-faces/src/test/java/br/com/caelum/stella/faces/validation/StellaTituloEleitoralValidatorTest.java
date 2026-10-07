package br.com.caelum.stella.faces.validation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.fail;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.junit.Before;
import org.junit.Test;

/**
 * StellaCPFValidator integration tests
 * 
 * @author Leonardo Bessa
 */
public class StellaTituloEleitoralValidatorTest {
    private FacesContextMocker facesContextMocker;
    private StellaTituloEleitoralValidator validator;

    @Before
    public void init() {
        facesContextMocker = new FacesContextMocker();
        this.validator = new StellaTituloEleitoralValidator();
    }

    @Test
    public void shouldNotThrowValidatorExceptionForValidTituloEleitoral() throws Exception {
        final FacesContext context = mock(FacesContext.class);
        final UIComponent component = mock(UIComponent.class);
        facesContextMocker.mockMessageBundle(context, "messages", Locale.getDefault());

        validator.validate(context, component, "245770031481");
    }

    @Test
    public void shouldGiveMessagesFromBrazilianResourceBundleForInvalidTituloEleitoralAndPtBRLocale() throws Exception {
        final FacesContext context = mock(FacesContext.class);
        final UIComponent component = mock(UIComponent.class);
        facesContextMocker.mockMessageBundle(context, "messages", new Locale("pt", "BR"));

        try {
            validator.validate(context, component, "2457700314810");
            fail();
        } catch (ValidatorException e) {
            // it should throw exception for invalid TituloEleitoral
            FacesMessage message = e.getFacesMessage();
            assertEquals("TituloEleitoral Invalido", message.getSummary());
        }
    }

    @Test
    public void shouldGiveMessagesFromDefaultResourceBundleForInvalidTituloEleitoralAndEnUSLocale() throws Exception {
        final FacesContext context = mock(FacesContext.class);
        final UIComponent component = mock(UIComponent.class);
        facesContextMocker.mockMessageBundle(context, "messages", new Locale("en"));

        try {
            validator.validate(context, component, "2457700314810");
            fail();
        } catch (ValidatorException e) {
            // it should throw exception for invalid TituloEleitoral
            FacesMessage message = e.getFacesMessage();
            assertEquals("Invalid TituloEleitoral", message.getSummary());
        }
    }
}
