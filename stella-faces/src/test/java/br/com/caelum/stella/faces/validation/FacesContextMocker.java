package br.com.caelum.stella.faces.validation;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Locale;

import javax.faces.application.Application;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 * @author Fabio Kung
 */
public class FacesContextMocker {

    public UIViewRoot mockMessageBundle(FacesContext context, String bundleName, Locale locale) {
        Application application = mock(Application.class);
        when(context.getApplication()).thenReturn(application);
        when(application.getMessageBundle()).thenReturn(bundleName);

        UIViewRoot viewRoot = mock(UIViewRoot.class);
        when(context.getViewRoot()).thenReturn(viewRoot);
        when(viewRoot.getLocale()).thenReturn(locale);

        return viewRoot;
    }

}
