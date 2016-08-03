package cn.udrm.dev.demo.bizlog;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.risesoft.soa.framework.bizlog.BizLogModel;

@Entity(name = "DemoBizLog")
@Table(name = "Demo_BizLog")
public class DemoBizLogModel extends BizLogModel {

	@Basic(optional = false)
	private String orderId;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public DemoBizLogModel() {
	}

}
