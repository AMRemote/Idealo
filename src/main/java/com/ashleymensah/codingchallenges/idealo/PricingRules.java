package com.ashleymensah.codingchallenges.idealo;

import java.util.HashMap;
import java.util.Map;

public class PricingRules {
	private Map<String, int[]> pricingRuleMap;

	public boolean hasMultiprice(String sku) {
		return pricingRuleMap.get(sku)[1] != 0;
	}
	
	public int getRegularPrice(String sku) {
		return pricingRuleMap.get(sku)[0];
	}
	
	public int getMultipriceQuantity(String sku) {
		return pricingRuleMap.get(sku)[1];
	}
	
	public int getMultiprice(String sku) {
		return pricingRuleMap.get(sku)[2];
	}
		
	public boolean skuHasRule(String sku) {
		if (pricingRuleMap.get(sku) != null) return true;
		return false;
	}
	
	public void addRule(String sku, int regularPrice, int ruleQuantity, int rulePrice) {
		if (pricingRuleMap == null) {
			pricingRuleMap = new HashMap<String, int[]>();
		}
		
		pricingRuleMap.put(sku, new int[] {regularPrice, ruleQuantity, rulePrice});
	}
}