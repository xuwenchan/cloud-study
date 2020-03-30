package com.cloud.Model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrgBaseInfoModel {

    private static final long serialVersionUID = 6587365729062135083L;    private Long orgId;    /* 单位标识 */    private String orgName;    /* 单位名称 */    private String industryClass;    /* 所属行业 */    private Long parentOrgId;    /* 上级单位 */    private String orgNational;    /* 所在国家 */    private String regAddr;    /* 注册地址 */    private String officeAddr;    /* 办公地址 */
    private String orgCode;  /*单位编码*/    private String orgContact;    /* 单位联系人 */    private String orgContactPhone;    /* 单位联系人电话 */    private String orgPostCode;    /* 单位邮编 */
    private Date orgCreatTime;  /*单位创建时间*/    private String orgLong;    /* 单位经度 */    private String orgLati;    /* 单位维度 */    private String addrProCode;    /* 地址-省编码 */    private String orgProName;    /* 地址-省名 */    private String orgCityCode;    /* 地址-市编码 */    private String orgCityName;    /* 地址-市 */    private String orgDistCode;    /* 地址-区编码 */    private String orgDistName;    /* 地址-区 */    private String address;    /* 地址详细 */    private String orgNote;    /* 备注 */    private String orgType;    /* 单位类型 */    private String deleteFlag;    /* 删除标志 */    private Date deleteTime;    /* 删除时间 */    private Long deleteUser;    /* 删除的操作者 */    /**    * 设置 orgId  单位标识        * @param _orgId     */    public void setOrgId(Long _orgId){        this.orgId = _orgId;    }    /**    *  获取 orgId  单位标识        */    public Long getOrgId(){        return this.orgId ;    }    /**    * 设置 orgName  单位名称        * @param _orgName     */    public void setOrgName(String _orgName){        this.orgName = _orgName;    }    /**    *  获取 orgName  单位名称        */    public String getOrgName(){        return this.orgName ;    }    /**    * 设置 industryClass  所属行业        * @param _industryClass     */    public void setIndustryClass(String _industryClass){        this.industryClass = _industryClass;    }    /**    *  获取 industryClass  所属行业        */    public String getIndustryClass(){        return this.industryClass ;    }    /**    * 设置 parentOrgId  上级单位        * @param _parentOrgId     */    public void setParentOrgId(Long _parentOrgId){        this.parentOrgId = _parentOrgId;    }    /**    *  获取 parentOrgId  上级单位        */    public Long getParentOrgId(){        return this.parentOrgId ;    }    /**    * 设置 orgNational  所在国家        * @param _orgNational     */    public void setOrgNational(String _orgNational){        this.orgNational = _orgNational;    }    /**    *  获取 orgNational  所在国家        */    public String getOrgNational(){        return this.orgNational ;    }    /**    * 设置 regAddr  注册地址        * @param _regAddr     */    public void setRegAddr(String _regAddr){        this.regAddr = _regAddr;    }    /**    *  获取 regAddr  注册地址        */    public String getRegAddr(){        return this.regAddr ;    }    /**    * 设置 officeAddr  办公地址        * @param _officeAddr     */    public void setOfficeAddr(String _officeAddr){        this.officeAddr = _officeAddr;    }    /**    *  获取 officeAddr  办公地址        */    public String getOfficeAddr(){        return this.officeAddr ;    }    /**    * 设置 orgContact  单位联系人        * @param _orgContact     */    public void setOrgContact(String _orgContact){        this.orgContact = _orgContact;    }    /**    *  获取 orgContact  单位联系人        */    public String getOrgContact(){        return this.orgContact ;    }    /**    * 设置 orgContactPhone  单位联系人电话        * @param _orgContactPhone     */    public void setOrgContactPhone(String _orgContactPhone){        this.orgContactPhone = _orgContactPhone;    }    /**    *  获取 orgContactPhone  单位联系人电话        */    public String getOrgContactPhone(){        return this.orgContactPhone ;    }    /**    * 设置 orgPostCode  单位邮编        * @param _orgPostCode     */    public void setOrgPostCode(String _orgPostCode){        this.orgPostCode = _orgPostCode;    }    /**    *  获取 orgPostCode  单位邮编        */    public String getOrgPostCode(){        return this.orgPostCode ;    }    /**    * 设置 addrProCode  地址-省编码        * @param _addrProCode     */    public void setAddrProCode(String _addrProCode){        this.addrProCode = _addrProCode;    }    /**    *  获取 addrProCode  地址-省编码        */    public String getAddrProCode(){        return this.addrProCode ;    }    /**    * 设置 orgProName  地址-省名        * @param _orgProName     */    public void setOrgProName(String _orgProName){        this.orgProName = _orgProName;    }    /**    *  获取 orgProName  地址-省名        */    public String getOrgProName(){        return this.orgProName ;    }    /**    * 设置 orgCityCode  地址-市编码        * @param _orgCityCode     */    public void setOrgCityCode(String _orgCityCode){        this.orgCityCode = _orgCityCode;    }    /**    *  获取 orgCityCode  地址-市编码        */    public String getOrgCityCode(){        return this.orgCityCode ;    }    /**    * 设置 orgCityName  地址-市        * @param _orgCityName     */    public void setOrgCityName(String _orgCityName){        this.orgCityName = _orgCityName;    }    /**    *  获取 orgCityName  地址-市        */    public String getOrgCityName(){        return this.orgCityName ;    }        public String getOrgDistCode() {
		return orgDistCode;
	}

	public void setOrgDistCode(String orgDistCode) {
		this.orgDistCode = orgDistCode;
	}

	public String getOrgDistName() {
		return orgDistName;
	}

	public void setOrgDistName(String orgDistName) {
		this.orgDistName = orgDistName;
	}

	/**    * 设置 address  地址详细        * @param _address     */    public void setAddress(String _address){        this.address = _address;    }    /**    *  获取 address  地址详细        */    public String getAddress(){        return this.address ;    }    /**    * 设置 orgNote  备注        * @param _orgNote     */    public void setOrgNote(String _orgNote){        this.orgNote = _orgNote;    }    /**    *  获取 orgNote  备注        */    public String getOrgNote(){        return this.orgNote ;    }

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public Date getOrgCreatTime() {
		return orgCreatTime;
	}

	public void setOrgCreatTime(Date orgCreatTime) {
		this.orgCreatTime = orgCreatTime;
	}

	public String getOrgLati() {
		return orgLati;
	}

	public void setOrgLati(String orgLati) {
		this.orgLati = orgLati;
	}

	public String getOrgLong() {
		return orgLong;
	}

	public void setOrgLong(String orgLong) {
		this.orgLong = orgLong;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public Long getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(Long deleteUser) {
		this.deleteUser = deleteUser;
	}	}
