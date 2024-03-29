/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.34
 * Generated at: 2019-08-18 02:22:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.wappay;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.alipay.config.AlipayConfig;
import com.alipay.api.domain.AlipayDataDataserviceBillDownloadurlQueryModel;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;

public final class downloadurl_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest");
    _jspx_imports_classes.add("com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse");
    _jspx_imports_classes.add("com.alipay.api.DefaultAlipayClient");
    _jspx_imports_classes.add("com.alipay.config.AlipayConfig");
    _jspx_imports_classes.add("com.alipay.api.domain.AlipayDataDataserviceBillDownloadurlQueryModel");
    _jspx_imports_classes.add("com.alipay.api.AlipayClient");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
 
/* *
 * 功能：支付宝手机网站alipay.data.dataservice.bill.downloadurl.query (查询对账单下载地址)接口调试入口页面
 * 版本：2.0
 * 修改日期：2016-11-01
 * 说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 请确保项目文件有可写权限，不然打印不了日志。
 */
 
      out.write('\r');
      out.write('\n');

 if(request.getParameter("WIDbill_type")!=null&&request.getParameter("WIDbill_date")!=null){
	// 账单类型，商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型：trade、signcustomer；
	// trade指商户基于支付宝交易收单的业务账单；signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单；
	String bill_type = new String(request.getParameter("WIDbill_type").getBytes("ISO-8859-1"),"UTF-8");
	// 账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM。
	String bill_date = new String(request.getParameter("WIDbill_date").getBytes("ISO-8859-1"),"UTF-8");
	/**********************/
	// SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签     
	 AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
	AlipayDataDataserviceBillDownloadurlQueryRequest alipay_request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
	
	AlipayDataDataserviceBillDownloadurlQueryModel model =new AlipayDataDataserviceBillDownloadurlQueryModel();
	model.setBillType(bill_type);
	model.setBillDate(bill_date);
    alipay_request.setBizModel(model);
    
	AlipayDataDataserviceBillDownloadurlQueryResponse alipay_response = client.execute(alipay_request);
	System.out.println(alipay_response.getBillDownloadUrl());
 }

      out.write(" \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t<title>alipay.data.dataservice.bill.downloadurl.query(查询对账单下载地址)</title>\r\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n");
      out.write("<style>\r\n");
      out.write("    *{\r\n");
      out.write("        margin:0;\r\n");
      out.write("        padding:0;\r\n");
      out.write("    }\r\n");
      out.write("    ul,ol{\r\n");
      out.write("        list-style:none;\r\n");
      out.write("    }\r\n");
      out.write("    body{\r\n");
      out.write("        font-family: \"Helvetica Neue\",Helvetica,Arial,\"Lucida Grande\",sans-serif;\r\n");
      out.write("    }\r\n");
      out.write("    .hidden{\r\n");
      out.write("        display:none;\r\n");
      out.write("    }\r\n");
      out.write("    .new-btn-login-sp{\r\n");
      out.write("        padding: 1px;\r\n");
      out.write("        display: inline-block;\r\n");
      out.write("        width: 75%;\r\n");
      out.write("    }\r\n");
      out.write("    .new-btn-login {\r\n");
      out.write("        background-color: #02aaf1;\r\n");
      out.write("        color: #FFFFFF;\r\n");
      out.write("        font-weight: bold;\r\n");
      out.write("        border: none;\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        height: 30px;\r\n");
      out.write("        border-radius: 5px;\r\n");
      out.write("        font-size: 16px;\r\n");
      out.write("    }\r\n");
      out.write("    #main{\r\n");
      out.write("        width:100%;\r\n");
      out.write("        margin:0 auto;\r\n");
      out.write("        font-size:14px;\r\n");
      out.write("    }\r\n");
      out.write("    .red-star{\r\n");
      out.write("        color:#f00;\r\n");
      out.write("        width:10px;\r\n");
      out.write("        display:inline-block;\r\n");
      out.write("    }\r\n");
      out.write("    .null-star{\r\n");
      out.write("        color:#fff;\r\n");
      out.write("    }\r\n");
      out.write("    .content{\r\n");
      out.write("        margin-top:5px;\r\n");
      out.write("    }\r\n");
      out.write("    .content dt{\r\n");
      out.write("        width:100px;\r\n");
      out.write("        display:inline-block;\r\n");
      out.write("        float: left;\r\n");
      out.write("        margin-left: 20px;\r\n");
      out.write("        color: #666;\r\n");
      out.write("        font-size: 13px;\r\n");
      out.write("        margin-top: 8px;\r\n");
      out.write("    }\r\n");
      out.write("    .content dd{\r\n");
      out.write("        margin-left:120px;\r\n");
      out.write("        margin-bottom:5px;\r\n");
      out.write("    }\r\n");
      out.write("    .content dd input {\r\n");
      out.write("        width: 85%;\r\n");
      out.write("        height: 28px;\r\n");
      out.write("        border: 0;\r\n");
      out.write("        -webkit-border-radius: 0;\r\n");
      out.write("        -webkit-appearance: none;\r\n");
      out.write("    }\r\n");
      out.write("    #foot{\r\n");
      out.write("        margin-top:10px;\r\n");
      out.write("        position: absolute;\r\n");
      out.write("        bottom: 15px;\r\n");
      out.write("        width: 100%;\r\n");
      out.write("    }\r\n");
      out.write("    .foot-ul{\r\n");
      out.write("        width: 100%;\r\n");
      out.write("    }\r\n");
      out.write("    .foot-ul li {\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        text-align:center;\r\n");
      out.write("        color: #666;\r\n");
      out.write("    }\r\n");
      out.write("    .note-help {\r\n");
      out.write("        color: #999999;\r\n");
      out.write("        font-size: 12px;\r\n");
      out.write("        line-height: 130%;\r\n");
      out.write("        margin-top: 5px;\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        display: block;\r\n");
      out.write("    }\r\n");
      out.write("    #btn-dd{\r\n");
      out.write("        margin: 20px;\r\n");
      out.write("        text-align: center;\r\n");
      out.write("    }\r\n");
      out.write("    .foot-ul{\r\n");
      out.write("        width: 100%;\r\n");
      out.write("    }\r\n");
      out.write("    .one_line{\r\n");
      out.write("        display: block;\r\n");
      out.write("        height: 1px;\r\n");
      out.write("        border: 0;\r\n");
      out.write("        border-top: 1px solid #eeeeee;\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        margin-left: 20px;\r\n");
      out.write("    }\r\n");
      out.write("    .am-header {\r\n");
      out.write("        display: -webkit-box;\r\n");
      out.write("        display: -ms-flexbox;\r\n");
      out.write("        display: box;\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        position: relative;\r\n");
      out.write("        padding: 7px 0;\r\n");
      out.write("        -webkit-box-sizing: border-box;\r\n");
      out.write("        -ms-box-sizing: border-box;\r\n");
      out.write("        box-sizing: border-box;\r\n");
      out.write("        background: #1D222D;\r\n");
      out.write("        height: 50px;\r\n");
      out.write("        text-align: center;\r\n");
      out.write("        -webkit-box-pack: center;\r\n");
      out.write("        -ms-flex-pack: center;\r\n");
      out.write("        box-pack: center;\r\n");
      out.write("        -webkit-box-align: center;\r\n");
      out.write("        -ms-flex-align: center;\r\n");
      out.write("        box-align: center;\r\n");
      out.write("    }\r\n");
      out.write("    .am-header h1 {\r\n");
      out.write("        -webkit-box-flex: 1;\r\n");
      out.write("        -ms-flex: 1;\r\n");
      out.write("        box-flex: 1;\r\n");
      out.write("        line-height: 18px;\r\n");
      out.write("        text-align: center;\r\n");
      out.write("        font-size: 18px;\r\n");
      out.write("        font-weight: 300;\r\n");
      out.write("        color: #fff;\r\n");
      out.write("    }\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body text=#000000 bgColor=\"#ffffff\" leftMargin=0 topMargin=4>\r\n");
      out.write("<header class=\"am-header\">\r\n");
      out.write("        <h1>支付宝手机网站查询对账单下载地址(接口名：alipay.data.dataservice.bill.downloadurl.query)</h1>\r\n");
      out.write("</header>\r\n");
      out.write("<div id=\"main\">\r\n");
      out.write("        <form name=alipayment action='' method=post target=\"_blank\">\r\n");
      out.write("            <div id=\"body\" style=\"clear:left\">\r\n");
      out.write("                <dl class=\"content\">\r\n");
      out.write("                    <dt>账单类型：</dt>\r\n");
      out.write("                    <dd>\r\n");
      out.write("                        <input id=\"WIDbill_type\" name=\"WIDbill_type\" value=\"trade\" />\r\n");
      out.write("                    </dd>\r\n");
      out.write("                    <hr class=\"one_line\">\r\n");
      out.write("                    <dt>账单时间：</dt>\r\n");
      out.write("                    <dd>\r\n");
      out.write("                        <input id=\"WIDbill_date\" name=\"WIDbill_date\" />\r\n");
      out.write("                    </dd>\r\n");
      out.write("                    <hr class=\"one_line\">\r\n");
      out.write("                    <dt></dt>\r\n");
      out.write("                    <dd>\r\n");
      out.write("                        <span style=\"line-height: 28px; color:red;\">注意：账单类型和账单时间不能为空！账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM。</span>\r\n");
      out.write("                    </dd>\r\n");
      out.write("                    <dd id=\"btn-dd\">\r\n");
      out.write("                        <span class=\"new-btn-login-sp\">\r\n");
      out.write("                            <button class=\"new-btn-login\" type=\"submit\" style=\"text-align:center;\">确 认</button>\r\n");
      out.write("                        </span>\r\n");
      out.write("                        <span class=\"note-help\">如果您点击“确认”按钮，即表示您同意该次的执行操作。</span>\r\n");
      out.write("                    </dd>\r\n");
      out.write("                </dl>\r\n");
      out.write("            </div>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("        <div id=\"foot\">\r\n");
      out.write("\t\t\t<ul class=\"foot-ul\">\r\n");
      out.write("\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t支付宝版权所有 2015-2018 ALIPAY.COM \r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
