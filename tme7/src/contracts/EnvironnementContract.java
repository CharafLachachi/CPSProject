package contracts;

import decorators.EnvironnementDecorator;
import decorators.MapDecorateur;
import exceptions.PreconditionError;
import services.EnvironnementService;

public class EnvironnementContract extends EnvironnementDecorator {

	

	public EnvironnementContract(EnvironnementService delegate) {
		super(delegate);
	}

	public void checkInvariants() {
		// comme MapService n'a pas d'invvariants donc rien a refaire
	}

	
	
	@Override
	public void closeDoor(int x, int y) {
		//pre getCellContent(M,x,y) = No
		if (!(getCellContent(x, y).isPresent() == false)) {
			throw new PreconditionError("EnvironnementService", "closeDoor", "Un objet est sur la case de la porte getCellContent(M,x,y) = No");
		}
		super.closeDoor(x, y);
	}


}
