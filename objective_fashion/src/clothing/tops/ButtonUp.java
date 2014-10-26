package clothing.tops;

import clothing.generics.Tops;
import clothing.variables.AppropriateTemperature;
import clothing.variables.Color;
import clothing.variables.Formality;
import clothing.variables.Material;

public class ButtonUp extends Tops {

	public ButtonUp(Color color, Formality formality,
			AppropriateTemperature temperature, Material material) {
		super(color, formality, temperature, material);
	}

	public String toString() {
		return this.getColor().toString() + " "
				+ this.getMaterial().toString().toLowerCase() + " button up";
	}
}
