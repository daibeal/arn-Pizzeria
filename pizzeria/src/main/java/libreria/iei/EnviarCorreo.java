package libreria.iei;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.text.DecimalFormat;
//puede poner el código en el paquete que quiera 
//el código siguiente es para enviar desde Gmail, vea más adelante para Yahoo 
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

//si el correo se envía desde una tarea de servicio 
public class EnviarCorreo implements JavaDelegate {
	private static final long serialVersionUID = 1L;

	public void execute(DelegateExecution execution) throws Exception {
			Pedido pedido = (Pedido) execution.getVariable("miPedido");
			String nombrePizza = pedido.getNombrePizza();
			int cantidad = pedido.getCantidad();
			double importeBruto = pedido.getTotalBruto();
			double descuento = pedido.getDescuento();
			double neto = pedido.getNeto();
// TODO Auto-generated method stub 
		System.out.println("Inicio de envío de correo");
		
	    String des = new DecimalFormat("#.##").format(descuento);
	    
// Primera variable se introduce en el formulario inicial. 
		String email = pedido.getEmail();
		String asunto = "Informe de su pedido de pizzas";
		String cuerpo = "<h2 style=\"display:inline\">Pizza: </h2> " + nombrePizza.toUpperCase() + "<br>"+
				        "<h2 style=\"display:inline\">Cantidad: </h2> " + cantidad + "<br>" +
				        "<h2 style=\"display:inline\">Importe Bruto: </h2> " + importeBruto + "<br>"  + 
				        "<h2 style=\"display:inline\">Descuento: </h2> " + des + "<br>"  +
				        "<h2 style=\"display:inline\">Importe Neto: </h2>" + neto;
// envío de correo 
		final String username = "allomuupv@yahoo.com"; // desde donde se envía
		final String password = "dxtiphikeipwzxxl"; // el password de YAHOO.

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.mail.yahoo.com");
		// para Yahoo cambie a
		// props.put("mail.smtp.host", "smtp.mail.yahoo.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("allomuupv@yahoo.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email)); // a quién se envía.
			message.setSubject(asunto);
			//message.setText("Hola!" + "\n\nTu pedido: " + cuerpo);
			//StringWriter writer = new StringWriter();
			//IOUtils.copy(new FileInputStream(new File("C:\\Users\\Administrador.WIN-2O4P6U7CI32\\eclipse-workspace\\pizzeria\\src\\main\\java\\libreria\\iei\\index.html>")), writer);
			//message.setContent(cuerpo + "/n" + writer.toString(),"text/html");
			String img = "<img src=\"https://readyseteat.com/sites/g/files/qyyrlu501/files/uploadedImages/img_6941_6018.JPEG\" style=\"border-radius:100%\">";
			message.setContent("<h1>PEDIDO</h1><hr>" + cuerpo +"<br> "+ img , "text/html");
			Transport.send(message);
			System.out.println("Correo enviado exitosamente a: " + email);
		} catch (MessagingException e) {
			System.err.println("Excepción detectada" + e);
			throw new RuntimeException(e);
		}
		// fin envío de correo

	}

}