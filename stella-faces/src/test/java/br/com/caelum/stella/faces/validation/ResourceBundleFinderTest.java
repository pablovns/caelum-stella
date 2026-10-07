package br.com.caelum.stella.faces.validation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Fabio Kung
 */
public class ResourceBundleFinderTest {
    private ResourceBundleFinder resourceBundleFinder;
    private FacesContextMocker mocker;

    @Before
    public void setUp() {
        mocker = new FacesContextMocker();
        resourceBundleFinder = new ResourceBundleFinder();
    }

    @Test
    public void deveRetornarMessageBundleDoFacesConfigSeExistir() {
        FacesContext ctx = mock(FacesContext.class);
        mocker.mockMessageBundle(ctx, "messages", new Locale("pt", "BR"));
        ResourceBundle messages = resourceBundleFinder.getForCurrentLocale(ctx);
        String invalidCPFMessage = messages.getString("cpferror.invalid_digits");
        assertEquals("CPF Invalido", invalidCPFMessage);
    }

    @Test
    public void deveRetornarMessageBundleDoStellaCasoNaoExistaNoFacesConfig() {
        FacesContext ctx = mock(FacesContext.class);
        mocker.mockMessageBundle(ctx, null, new Locale("pt", "BR"));
        ResourceBundle stellaMessages = resourceBundleFinder.getForCurrentLocale(ctx);
        String invalidCPFMessage = stellaMessages.getString("cpferror.invalid_check_digits");
        assertEquals("CPF com digito(s) verificador(es) inválido(s)", invalidCPFMessage);
    }
}
