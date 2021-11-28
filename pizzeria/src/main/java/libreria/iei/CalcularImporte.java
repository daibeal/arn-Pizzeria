package libreria.iei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;
import javax.print.DocFlavor.STRING;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CalcularImporte implements JavaDelegate {
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Pedido pedido = (Pedido) execution.getVariable("miPedido");
		// TODO Auto-generated method stub
		String nombrePizza = (String) execution.getVariable("IDNombrePizza");
		// accede a la variable IDNombrePizza, el método devuelve un Object, se realiza
		// un casting
		// Si la variable no está en el motor devolverá null
		int cantidad = ((Number) execution.getVariable("IDCantidad")).intValue();
		System.out.println("Calculando el precio de " + cantidad + " pizzas " + nombrePizza);
		double precio = 0.0;
		/*
		 * if("margarita".equalsIgnoreCase(NombrePizza)) { precio = cantidad * 10.5; }
		 * if("4 quesos".equalsIgnoreCase(NombrePizza)) { precio = cantidad * 12.9; }
		 * if("carbonara".equalsIgnoreCase(NombrePizza)) { precio = cantidad * 11.0; }
		 * if("barbacoa".equalsIgnoreCase(NombrePizza)) { precio = cantidad * 13.0; }
		 */
        Servicio servicio = new Servicio();

		precio = cantidad * servicio.getPrecio(nombrePizza);

		System.out.println("El precio será: " + precio);
		pedido.setTotalBruto(precio);
		execution.setVariable("miPedido", pedido);
		
		System.out.println("Importe calulado exitosamente!");

	}

	

}
