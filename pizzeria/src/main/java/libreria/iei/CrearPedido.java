package libreria.iei;

import java.util.Date;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

public class CrearPedido implements ExecutionListener {

	@Override
	public void notify(DelegateExecution delegado) throws Exception {
		// TODO Auto-generated method stub
		String nombreCliente = (String) delegado.getVariable("IDNombre");
		String direccion = (String) delegado.getVariable("IDDireccion");
		int cantidad = ((Number) delegado.getVariable("IDCantidad")).intValue();
		Date fecha = (Date) delegado.getVariable("IDFecha");
		String email = (String) delegado.getVariable("IDCorreo");
		String nombrePizza = (String) delegado.getVariable("IDNombrePizza");
		// añadir también la dirección y la fecha
		// accedemos a las variables del motor
		// que se corresponden con campos del formulario
		Pedido pedido = new Pedido(); // se crea el pedido
		pedido.setNombreCliente(nombreCliente);
		pedido.setCantidad(cantidad);
		pedido.setFecha(fecha);
		pedido.setEmail(email);
		pedido.setNombrePizza(nombrePizza);
		pedido.setDireccion(direccion);
		
		// ... se añade la dirección, la fecha y el resto de atributos con setters
		// salvar el pedido en el motor para luego recuperarlo
		delegado.setVariable("miPedido", pedido); // nombre variable, valor
	}
}
