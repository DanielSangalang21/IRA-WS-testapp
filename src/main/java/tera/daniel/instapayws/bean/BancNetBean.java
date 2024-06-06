package tera.daniel.instapayws.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name = "TBL_BANCNET")
public class BancNetBean {
	@Id
	private String bicfi;
	
	@Column(name = "COL_STATUS")
	private String status;
	
	@Column(name = "BANK_NAME")
	private String bank;
	
	@Column(name="UPDATE_BY")
	private String updateBy;
	
	@Transient
	private boolean isSignOn;
	
	public String getBicfi() {
		return bicfi;
	}
	public void setBicfi(String bicfi) {
		this.bicfi = bicfi;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public boolean getIsSignOn() {
		return isSignOn;
	}
	public void setIsSignOn(boolean isSignOn) {
		this.isSignOn = isSignOn;
	}
}
