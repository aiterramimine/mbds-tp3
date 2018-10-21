package fr.unice.polytech.isa.tcf.interceptors;



import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartCounter implements Serializable {

	private static final Logger log = Logger.getLogger(CartCounter.class.getName());


	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {

		return null;
	}

}
