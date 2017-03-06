package jp.co.sysevo.model;

import java.io.Serializable;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component("outptData")
public class OutptData implements Serializable {
	
	public String id;
	public String name;
	public int age;
	public String des;
	public String desJp;
	public Date updTs;
	public boolean sendFlg;


	public OutptData() {
	}


	public OutptData(String id, String name, int age, String des) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.des = des;
	}
	
	public OutptData(String name, int age, String des) {
		this.name = name;
		this.age = age;
		this.des = des;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getDesJp() {
		return desJp;
	}

	public void setDesJp(String desJp) {
		this.desJp = desJp;
	}

	public boolean getSendFlg() {
		return sendFlg;
	}

	public void setSendFlg(boolean sendFlg) {
		this.sendFlg = sendFlg;
	}

	public void setUpdTs(Date updTs) {
		this.updTs = updTs;
	}

	public Date getUpdTs() {
		return updTs;
	}


	@Override
	public String toString() {
		return "OutptData [id=" + id + ", name=" + name + ", sendFlg=" + sendFlg
				+ ", des=" + des + "]";
	}
}