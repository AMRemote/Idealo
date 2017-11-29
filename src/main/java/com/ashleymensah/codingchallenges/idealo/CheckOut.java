package com.ashleymensah.codingchallenges.idealo;

import java.util.HashMap;
import java.util.Map;

public class CheckOut {
	private PricingRules pricingRules;
	private Map<String, Integer> basket;
	int total; 
	
	public CheckOut(PricingRules pricingRules) {
		this.pricingRules = pricingRules;
	}
	
	public boolean scan(String sku) {
		if (basket == null) {
			basket = new HashMap<String, Integer>(); // create new basket if scanning first item
			total = 0;
		}
		
		if (sku.equals("") || sku.equals(null) || !pricingRules.skuHasRule(sku)) {
			return false;
		}
		
		if (basket.get(sku) == null) { // if scanning item for the first time 
			basket.put(sku, 1);
		} else { 
			basket.put(sku, basket.get(sku) + 1);
		}
		
		calculateTotal();
		return true;
	} 
	
	public void calculateTotal() {
		total = 0; 
		
		for (Map.Entry<String, Integer> currentItem : basket.entrySet()) { // loop items in the basket HashMap
			String currentSKU = currentItem.getKey();
			int currentItemQuantity = currentItem.getValue();
			int regularPrice = pricingRules.getRegularPrice(currentSKU);
						
			if (pricingRules.hasMultiprice(currentSKU) && currentItemQuantity >= pricingRules.getMultipriceQuantity(currentSKU)) {  
				int multiprice = pricingRules.getMultiprice(currentSKU);
				int multipriceQuantity = pricingRules.getMultipriceQuantity(currentSKU);

				int remainder = currentItemQuantity % multipriceQuantity; 
				currentItemQuantity = currentItemQuantity - remainder;
					
				total = total + ((currentItemQuantity / multipriceQuantity) * multiprice) + (remainder * regularPrice);
			} else {
				total = total + (currentItemQuantity * regularPrice);
			} 
		}
	}
}