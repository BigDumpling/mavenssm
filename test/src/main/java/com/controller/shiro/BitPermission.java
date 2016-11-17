package com.controller.shiro;

import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.authz.Permission;


/**
 * Created by ligq01 on 2016/11/7.
 */
public class BitPermission implements Permission {
	private String resourceIdentity;
	private int permissionBit;
	private String instanceId;

	public BitPermission(String permissionString){
		String[] array = permissionString.split("\\+");
		if(array.length > 1){
			resourceIdentity = array[1];
		}
		if(StringUtils.isEmpty(resourceIdentity)){
			resourceIdentity = "*";
		}
		if(array.length > 2){
			permissionBit = Integer.valueOf(array[2]);
		}
		if(array.length > 3){
			instanceId = array[3];
		}
		if(StringUtils.isEmpty(instanceId)){
			instanceId = "*";
		}
	}

	public boolean implies(Permission p) {
		if(!(p instanceof BitPermission)) {
			return false;
		}
		BitPermission other = (BitPermission) p;
		if(!("*".equals(this.resourceIdentity) || this.resourceIdentity.equals(other.resourceIdentity))) {
			return false;
		}
		if(!(this.permissionBit ==0 || (this.permissionBit & other.permissionBit) != 0)) {
			return false;
		}
		if(!("*".equals(this.instanceId) || this.instanceId.equals(other.instanceId))) {
			return false;
		}
		return true;
	}
}
