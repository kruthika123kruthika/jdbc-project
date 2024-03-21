package com.tns.userdefined;

public class customer {
       private String customerName;
       private int customerID;
       private String customerCity;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getCustomerCity() {
		return customerCity;
	}
	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}
	@Override
	public String toString() {
		return "customer [customerName=" + customerName + ", customerID=" + customerID + ", customerCity="
				+ customerCity + "]";
	}	
      
}
