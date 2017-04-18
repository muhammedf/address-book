package sunofkyuss.addressbook.view.utility;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public final class Message {
	
	private Message(){}

	public static void success(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", message));
	}

	public static void failure(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failure!", message));
	}

	public static void warning(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", message));
	}
}
