package br.com.caelum.stella.faces.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.junit.Before;
import org.junit.Test;

/**
 * StellaIEValidator integration tests
 * 
 * @author Leonardo Bessa
 */
public class StellaIEValidatorTest {

    private StellaIEValidator validator;
    private FacesContextMocker facesContextMocker;

    @Before
    public void init() {
        facesContextMocker = new FacesContextMocker();
        this.validator = new StellaIEValidator();
    }

    @Test
    public void shouldIgnoreComponentIdWhenEstadoIsFilled() {
        // estado tem prioridade sobre o estadoComponentId
        FacesContext context = mock(FacesContext.class);
        facesContextMocker.mockMessageBundle(context, "messages", Locale.getDefault());
        UIComponent component = mock(UIComponent.class);
        validator.setEstado("SP");
        validator.validate(context, component, "P011004243002");
    }

    @Test
    public void shouldNotThrowValidatorExceptionForValidIE() throws Exception {
        final FacesContext context = mock(FacesContext.class);
        final UIComponent component = mock(UIComponent.class);
        final UIInput valueHolder = mock(UIInput.class);
        final String estadoComponentId = "form:estado";
        UIViewRoot viewRoot = facesContextMocker.mockMessageBundle(context, "messages", Locale.getDefault());
        when(viewRoot.findComponent(estadoComponentId)).thenReturn(valueHolder);
        when(valueHolder.getValue()).thenReturn("SP");
        validator.setEstadoComponentId(estadoComponentId);
        validator.validate(context, component, "P011004243002");
    }

    @Test
    public void shouldGiveMessagesFromBrazilianResourceBundleForInvalidIEAndPtBRLocale() throws Exception {
        final FacesContext context = mock(FacesContext.class);
        final UIComponent component = mock(UIComponent.class);
        final UIInput valueHolder = mock(UIInput.class);
        final String estadoComponentId = "form:estado";
        UIViewRoot viewRoot = facesContextMocker.mockMessageBundle(context, "messages", new Locale("pt", "BR"));
        when(viewRoot.findComponent(estadoComponentId)).thenReturn(valueHolder);
        when(valueHolder.getValue()).thenReturn("SP");
        try {
            validator.setEstadoComponentId(estadoComponentId);
            validator.validate(context, component, "P011004245002");
            fail();
        } catch (ValidatorException e) {
            // it should throw exception for invalid IE
            FacesMessage message = e.getFacesMessage();
            assertEquals("IE Invalido", message.getSummary());
        }
    }

    @Test
    public void shouldGiveMessagesFromDefaultResourceBundleForInvalidIEAndEnUSLocale() throws Exception {
        final FacesContext context = mock(FacesContext.class);
        final UIComponent component = mock(UIComponent.class);
        final UIInput valueHolder = mock(UIInput.class);
        final String estadoComponentId = "form:estado";
        UIViewRoot viewRoot = facesContextMocker.mockMessageBundle(context, "messages", new Locale("en"));
        when(viewRoot.findComponent(estadoComponentId)).thenReturn(valueHolder);
        when(valueHolder.getValue()).thenReturn("SP");
        try {
            validator.setEstadoComponentId(estadoComponentId);
            validator.validate(context, component, "P011004245002");
            fail();
        } catch (ValidatorException e) {
            // it should throw exception for invalid IE
            FacesMessage message = e.getFacesMessage();
            assertEquals("Invalid IE", message.getSummary());
        }
    }

}
