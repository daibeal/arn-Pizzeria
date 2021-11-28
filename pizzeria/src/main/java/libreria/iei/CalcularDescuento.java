package libreria.iei;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CalcularDescuento implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("Calculando descuento...");
		Pedido pedido = (Pedido) execution.getVariable("miPedido");
		int cantidad = pedido.getCantidad();
		double bruto = pedido.getTotalBruto();
		double descuento = 0.0;
		String nombrePizza = pedido.getNombrePizza().toLowerCase();
		switch (nombrePizza) {
		case "margarita":
			descuento = bruto * 0.02;
			break;
		case "4 quesos":
			if (cantidad == 2) {
				descuento = bruto * 0.04;
			} else if (cantidad > 2 && cantidad < 6) {
				descuento = bruto * 0.05;
			} else {
				descuento = bruto * 0.07;
			}
			break;
		default:
			if (cantidad > 6) {
				descuento = bruto * 0.1;
			}

		}
		pedido.setNeto(bruto-descuento);
		pedido.setDescuento(descuento);
		execution.setVariable("miPedido", pedido);
		System.out.println("Descuento calculado exitosamente!");
	    

	}

}
