package controller;

import clothing.Outfit;
import clothing.accessories.*;
import clothing.bottoms.*;
import clothing.generics.*;
import clothing.outerwear.*;
import clothing.shoes.*;
import clothing.singletons.*;
import clothing.tops.*;
import clothing.variables.AppropriateTemperature;
import clothing.variables.Color;
import clothing.variables.Formality;
import clothing.variables.Material;
import userProfile.Preferences;
import userProfile.UserProfile;
import userProfile.Wardrobe;

/**
 * @author The Squad
 *
 */
public class Controller {
	TheDecider decider;
	UserProfile profile;
	Wardrobe wardrobe; //these field should ultimately be contained in profile. just for testing
	Preferences preferences;
	
	
	public Controller() {
		decider = new TheDecider();
		wardrobe = new Wardrobe();
		preferences = new Preferences(Formality.CASUAL);
		randomPopulateWardrobe();
		profile = new UserProfile(wardrobe, preferences);
	}
	
	public void randomPopulateWardrobe() {
		Accessories ring = new Ring(Color.GREY);
		wardrobe.addAccessories(ring);
		Bottoms pants = new Pants(Color.BLACK, Material.DENIM);
		wardrobe.addBottoms(pants);
		Outerwear bomber = new Bomber(Color.BLACK, Material.SUEDE);
		wardrobe.addOuterwear(bomber);
		Shoes boots = new Boots(Color.BLACK, Material.LEATHER);
		wardrobe.addShoes(boots);
		Singletons romper = new Romper(Color.RED, Material.FLANNEL);
		wardrobe.addSingletons(romper);
		Socks socks = new Socks(Color.GREY, Material.WOOL);
		wardrobe.addSocks(socks);
		Tops buttonup = new ButtonUp(Color.WHITE, Material.LEATHER);
		wardrobe.addTops(buttonup);
	}
	
	/**
	 * Decides outfit.
	 * @return
	 */
	public Outfit decideOutfit() {
		return decider.decideOutfit(accessPreferences(), accessWardrobe());
	}
	
	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}
	
	public Wardrobe accessWardrobe() {
		return profile.getWardrobe();
	}
	
	public Preferences accessPreferences() {
		return profile.getPreferences();
	}
	
	public static void main(String[] args) { //calculates outfit twice, should make global outfit variable
		Controller controller = new Controller();
		System.out.println(controller.decideOutfit()); 
		Mailer.sendOutfit(controller.decideOutfit(), "j5r5myk@gmail.com"); //mail outfit
	}
	

}
