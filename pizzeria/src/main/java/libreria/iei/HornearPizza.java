package libreria.iei;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class HornearPizza implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		Pedido pedido = (Pedido) delegateTask.getExecution().getVariable("miPedido");
		System.out.println("Horneando Pizza...\n2 Minutos...");
		
		
	}

}
