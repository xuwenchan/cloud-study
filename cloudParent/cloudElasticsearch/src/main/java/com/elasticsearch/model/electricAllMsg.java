package com.elasticsearch.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName="electric",type="electricAllMsg")
public class electricAllMsg {
	@Id
	private Long electmsgid;
	private Long electricId;
	private Integer status;
	private Date rcvTime;
	private String checkValue;
	private String direct;
	private Integer upFct;
	private Integer downFct;
	private String jsonData;
	private String originalData;
	
	
	private String devNumber;
	private String EUI;
	private Integer loopNo;
	private Integer loopDevType;
	private Integer loopStatus;
	private Integer unit;
	private String checkVal;
	private String setVal;
	private Integer setTopLimit;
	private Integer setBtmLimit;
	
	
	private Long deviceId;
    private String deviceTypeCode;
    private String deviceSN;
    private String deviceQRCode;
    private String deviceImeiCode; //IMEI
    private String deviceSimNo; //IMSI
    private String mcuID;
    private String deviceNumber;
    private String deviceAddr;
    private String deviceGpsLati;
    private String deviceGpsLong;
    private String deviceGpsAlti;
    private String isValid;
    private String workState;
    private String mcuIDHex;
    private String DevEUI;
    private String deviceIccidCode; //ICCID
    
    
    private Long areaID;    /* 区域ID */
    private Long parentAreaID;    /* 所属上级区域，一级区域的上级区域ID为0 */
    private String areaName;    /* 区域名称 */
    private String areaType;    /* 区域类型 V:虚拟区域;R:实体区域 */
    private String areaLocation;    /*  */
    private String areaLocProv;    /* 位置-省 */
    private String areaLocCity;    /* 位置-市 */
    private String areaLocDist;    /* 位置-区 */
    private String areaLocDetail;    /* 位置-详细地址 */
    private String areaIDPath;    /* 区域ID路径 从一级到当前的ID的路径，以分割 */
    private String areaLong;    /* 区域经度 */
    private String areaLati;    /* 区域维度 */
    private Long topId;

    private Long orgId;    /* 单位标识 */
    private String orgName;    /* 单位名称 */
    private String industryClass;    /* 所属行业 */
    private Long parentOrgId;    /* 上级单位 */
    private String orgNational;    /* 所在国家 */
    private String orgContact;    /* 单位联系人 */
    private String orgContactPhone;    /* 单位联系人电话 */
    private String orgLong;    /* 单位经度 */
    private String orgLati;    /* 单位维度 */
    private String orgProName;    /* 地址-省名 */
    private String orgCityName;    /* 地址-市 */
    private String orgDistName;    /* 地址-区 */
    private String address;    /* 地址详细 */
    private String orgType;    /* 单位类型 */
	public Long getElectmsgid() {
		return electmsgid;
	}
	public void setElectmsgid(Long electmsgid) {
		this.electmsgid = electmsgid;
	}
	public Long getElectricId() {
		return electricId;
	}
	public void setElectricId(Long electricId) {
		this.electricId = electricId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getRcvTime() {
		return rcvTime;
	}
	public void setRcvTime(Date rcvTime) {
		this.rcvTime = rcvTime;
	}
	public String getCheckValue() {
		return checkValue;
	}
	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}
	public String getDirect() {
		return direct;
	}
	public void setDirect(String direct) {
		this.direct = direct;
	}
	public Integer getUpFct() {
		return upFct;
	}
	public void setUpFct(Integer upFct) {
		this.upFct = upFct;
	}
	public Integer getDownFct() {
		return downFct;
	}
	public void setDownFct(Integer downFct) {
		this.downFct = downFct;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public String getOriginalData() {
		return originalData;
	}
	public void setOriginalData(String originalData) {
		this.originalData = originalData;
	}
	public String getDevNumber() {
		return devNumber;
	}
	public void setDevNumber(String devNumber) {
		this.devNumber = devNumber;
	}
	public String getEUI() {
		return EUI;
	}
	public void setEUI(String eUI) {
		EUI = eUI;
	}
	public Integer getLoopNo() {
		return loopNo;
	}
	public void setLoopNo(Integer loopNo) {
		this.loopNo = loopNo;
	}
	public Integer getLoopDevType() {
		return loopDevType;
	}
	public void setLoopDevType(Integer loopDevType) {
		this.loopDevType = loopDevType;
	}
	public Integer getLoopStatus() {
		return loopStatus;
	}
	public void setLoopStatus(Integer loopStatus) {
		this.loopStatus = loopStatus;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public String getCheckVal() {
		return checkVal;
	}
	public void setCheckVal(String checkVal) {
		this.checkVal = checkVal;
	}
	public String getSetVal() {
		return setVal;
	}
	public void setSetVal(String setVal) {
		this.setVal = setVal;
	}
	public Integer getSetTopLimit() {
		return setTopLimit;
	}
	public void setSetTopLimit(Integer setTopLimit) {
		this.setTopLimit = setTopLimit;
	}
	public Integer getSetBtmLimit() {
		return setBtmLimit;
	}
	public void setSetBtmLimit(Integer setBtmLimit) {
		this.setBtmLimit = setBtmLimit;
	}
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}
	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}
	public String getDeviceSN() {
		return deviceSN;
	}
	public void setDeviceSN(String deviceSN) {
		this.deviceSN = deviceSN;
	}
	public String getDeviceQRCode() {
		return deviceQRCode;
	}
	public void setDeviceQRCode(String deviceQRCode) {
		this.deviceQRCode = deviceQRCode;
	}
	public String getDeviceImeiCode() {
		return deviceImeiCode;
	}
	public void setDeviceImeiCode(String deviceImeiCode) {
		this.deviceImeiCode = deviceImeiCode;
	}
	public String getDeviceSimNo() {
		return deviceSimNo;
	}
	public void setDeviceSimNo(String deviceSimNo) {
		this.deviceSimNo = deviceSimNo;
	}
	public String getMcuID() {
		return mcuID;
	}
	public void setMcuID(String mcuID) {
		this.mcuID = mcuID;
	}
	public String getDeviceNumber() {
		return deviceNumber;
	}
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	public String getDeviceAddr() {
		return deviceAddr;
	}
	public void setDeviceAddr(String deviceAddr) {
		this.deviceAddr = deviceAddr;
	}
	public String getDeviceGpsLati() {
		return deviceGpsLati;
	}
	public void setDeviceGpsLati(String deviceGpsLati) {
		this.deviceGpsLati = deviceGpsLati;
	}
	public String getDeviceGpsLong() {
		return deviceGpsLong;
	}
	public void setDeviceGpsLong(String deviceGpsLong) {
		this.deviceGpsLong = deviceGpsLong;
	}
	public String getDeviceGpsAlti() {
		return deviceGpsAlti;
	}
	public void setDeviceGpsAlti(String deviceGpsAlti) {
		this.deviceGpsAlti = deviceGpsAlti;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getWorkState() {
		return workState;
	}
	public void setWorkState(String workState) {
		this.workState = workState;
	}
	public String getMcuIDHex() {
		return mcuIDHex;
	}
	public void setMcuIDHex(String mcuIDHex) {
		this.mcuIDHex = mcuIDHex;
	}
	public String getDevEUI() {
		return DevEUI;
	}
	public void setDevEUI(String devEUI) {
		DevEUI = devEUI;
	}
	public String getDeviceIccidCode() {
		return deviceIccidCode;
	}
	public void setDeviceIccidCode(String deviceIccidCode) {
		this.deviceIccidCode = deviceIccidCode;
	}
	public Long getAreaID() {
		return areaID;
	}
	public void setAreaID(Long areaID) {
		this.areaID = areaID;
	}
	public Long getParentAreaID() {
		return parentAreaID;
	}
	public void setParentAreaID(Long parentAreaID) {
		this.parentAreaID = parentAreaID;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getAreaLocation() {
		return areaLocation;
	}
	public void setAreaLocation(String areaLocation) {
		this.areaLocation = areaLocation;
	}
	public String getAreaLocProv() {
		return areaLocProv;
	}
	public void setAreaLocProv(String areaLocProv) {
		this.areaLocProv = areaLocProv;
	}
	public String getAreaLocCity() {
		return areaLocCity;
	}
	public void setAreaLocCity(String areaLocCity) {
		this.areaLocCity = areaLocCity;
	}
	public String getAreaLocDist() {
		return areaLocDist;
	}
	public void setAreaLocDist(String areaLocDist) {
		this.areaLocDist = areaLocDist;
	}
	public String getAreaLocDetail() {
		return areaLocDetail;
	}
	public void setAreaLocDetail(String areaLocDetail) {
		this.areaLocDetail = areaLocDetail;
	}
	public String getAreaIDPath() {
		return areaIDPath;
	}
	public void setAreaIDPath(String areaIDPath) {
		this.areaIDPath = areaIDPath;
	}
	public String getAreaLong() {
		return areaLong;
	}
	public void setAreaLong(String areaLong) {
		this.areaLong = areaLong;
	}
	public String getAreaLati() {
		return areaLati;
	}
	public void setAreaLati(String areaLati) {
		this.areaLati = areaLati;
	}
	public Long getTopId() {
		return topId;
	}
	public void setTopId(Long topId) {
		this.topId = topId;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getIndustryClass() {
		return industryClass;
	}
	public void setIndustryClass(String industryClass) {
		this.industryClass = industryClass;
	}
	public Long getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	public String getOrgNational() {
		return orgNational;
	}
	public void setOrgNational(String orgNational) {
		this.orgNational = orgNational;
	}
	public String getOrgContact() {
		return orgContact;
	}
	public void setOrgContact(String orgContact) {
		this.orgContact = orgContact;
	}
	public String getOrgContactPhone() {
		return orgContactPhone;
	}
	public void setOrgContactPhone(String orgContactPhone) {
		this.orgContactPhone = orgContactPhone;
	}
	public String getOrgLong() {
		return orgLong;
	}
	public void setOrgLong(String orgLong) {
		this.orgLong = orgLong;
	}
	public String getOrgLati() {
		return orgLati;
	}
	public void setOrgLati(String orgLati) {
		this.orgLati = orgLati;
	}
	public String getOrgProName() {
		return orgProName;
	}
	public void setOrgProName(String orgProName) {
		this.orgProName = orgProName;
	}
	public String getOrgCityName() {
		return orgCityName;
	}
	public void setOrgCityName(String orgCityName) {
		this.orgCityName = orgCityName;
	}
	public String getOrgDistName() {
		return orgDistName;
	}
	public void setOrgDistName(String orgDistName) {
		this.orgDistName = orgDistName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
    
	
	
}
