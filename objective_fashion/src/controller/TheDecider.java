package controller;

import java.util.Vector;

import userProfile.Preferences;
import userProfile.Wardrobe;
import clothing.Outfit;
import clothing.generics.*;
import clothing.variables.AppropriateTemperature;
import clothing.singletons.Romper;
import clothing.tops.*;

public class TheDecider {
	State state;
	
	public TheDecider() {
		state = new State();
	}
	
	/**
	 * Based on variables, intelligently instantiate one outfit and return it.
	 * @param preferences
	 * @param wardrobe
	 * @return
	 */
	public Outfit decideOutfit(Preferences preferences, Wardrobe wardrobe) {
		AppropriateTemperature temperature = state.getTemperatureState();
		switch (temperature) {
		case VERY_COLD: return createColdOutfit(preferences, wardrobe); //these method calls are very abstract for now 
		case COLD: return createColdOutfit(preferences, wardrobe);  
		case COOL: return createColdOutfit(preferences, wardrobe); 
		case WARM: return createHotOutfit(preferences, wardrobe); 
		case HOT: return createHotOutfit(preferences, wardrobe);  
		default: return createHotOutfit(preferences, wardrobe); 
		}
	}
	
	public Outfit createColdOutfit(Preferences preferences, Wardrobe wardrobe) {
		Vector<Outerwear> outerWear = wardrobe.getOuterwear(state.getTemperatureState(), preferences.getFormality());
		Vector<Tops> tops = wardrobe.getTops(state.getTemperatureState(), preferences.getFormality());
		Vector<Bottoms> bottoms = wardrobe.getBottoms(state.getTemperatureState(), preferences.getFormality());
		Vector<Shoes> shoes = wardrobe.getShoes(state.getTemperatureState(), preferences.getFormality());
		
		//assumes none of these Vectors are null for now. need to implement socks, accessories, singletons
		return new Outfit(tops.get(0), bottoms.get(0), new Socks(), shoes.get(0), outerWear.get(0), new Romper(), new Accessories[1]);
	}
	
	public Outfit createHotOutfit(Preferences preferences, Wardrobe wardrobe) {
		Vector<Outerwear> outerWear = wardrobe.getOuterwear(state.getTemperatureState(), preferences.getFormality());
		Vector<Tops> tops = wardrobe.getTops(state.getTemperatureState(), preferences.getFormality());
		Vector<Bottoms> bottoms = wardrobe.getBottoms(state.getTemperatureState(), preferences.getFormality());
		Vector<Shoes> shoes = wardrobe.getShoes(state.getTemperatureState(), preferences.getFormality());
		
		//assumes none of these Vectors are null for now. need to implement socks, accessories, singletons
		return new Outfit(tops.get(0), bottoms.get(0), new Socks(), shoes.get(0), outerWear.get(0), new Romper(), new Accessories[1]);
	}
}
