package com.zc.bp.web.stat;

import java.io.FileNotFoundException;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.zc.bp.dao.impl.SqlDao;
import com.zc.bp.utils.file.FileUtil;
import com.zc.bp.web.BaseAction;

public class StatChartAction extends BaseAction{
	
	private SqlDao sqlDao;
	public void setSqlDao(SqlDao sqlDao) {
		this.sqlDao = sqlDao;
	}
	/**
	 * [
                {
                    "country": "USA",
                    "year2004": 3.5
                   
                },
                {
                    "country": "UK",
                    "year2004": 1.7
                    
                }
                
            ];
	 */
	/**
	 * js实现销量排行前9名，柱状图
	 * @return
	 */
	public String productsale(){
		String sql ="select * from (select product_no,sum(amount) from contract_product_c group by product_no order by sum(amount) desc) where rownum<9";
		List<String> lists = Execute(sql);
		StringBuffer sb = new StringBuffer();
		sb.append(" [");
		for(int i=0;i<lists.size();i++){
			sb.append("{\""+"商品编号"+"\": \""+lists.get(i++)+"\",");
			sb.append("\""+"销量"+"\":"+lists.get(i)+"},");
		}
		sb.delete(sb.length()-1,sb.length());
		sb.append("]");
		ActionContext.getContext().getValueStack().set("chartData", sb.toString());
		return "productsalenew";
	}

	
	/**
	 * 产品销售排行,统计前16条记录,柱状图
	 * @throws Exception 
	 */
	public String productsaleold() throws Exception{
		String sql ="select * from (select product_no,sum(amount) from contract_product_c group by product_no order by sum(amount) desc) where rownum<16";
		List<String> lists = Execute(sql);
		StringBuffer sb = genDarData(lists,"销售金额前16名");
		Write(sb.toString(),"stat\\chart\\productsale\\data.xml");
		return "productSale";
	}

	/**
	 * 生产厂家销售情况，饼状图
	 * @return
	 * @throws Exception 
	 */
	public String factorysale() throws Exception{
		//1. 查询数据库，统计出数据库中的结果信息
		String sql="select factory_name,sum(amount) from Contract_Product_c group by factory_name order by sum(amount) desc";
		List<String> executeSQL = Execute(sql);
		//2. 将这个结果拼接成data.xml需要的形式
		StringBuffer sb = genPieData(executeSQL);
		//3.发送到data.xml
		Write(sb.toString(),"stat\\chart\\factorysale\\data.xml");
		return "factorySale";
	}


	/**
	 * 提取饼状图
	 * @param executeSQL
	 * @return
	 */
	private StringBuffer genPieData(List<String> executeSQL) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><pie>");
		for(int i = 0; i<executeSQL.size();i++){
			if(i>13){
				Double sum=0.0;
				for(int k = i; k<executeSQL.size();k++){
					sum += Double.parseDouble(executeSQL.get(++k));
				}
				sb.append("<slice title=\""+"其他"+"\">"+sum+"</slice>");
				break;
			}else{
				sb.append("<slice title=\""+executeSQL.get(i++)+"\">"+executeSQL.get(i)+"</slice>");
			}
		}
		sb.append("</pie>");
		return sb;
	}



	/**
	 * 查询数据库，获取需要的集合List<String>
	 * @param sql
	 * @return
	 */
	private List<String> Execute(String sql) {
		List<String> executeSQL = sqlDao.executeSQL(sql);
		return executeSQL;
	}

	/**
	 * 将拼接好的字符串写入到需要的data.xml中
	 * @param sb
	 * @throws FileNotFoundException
	 */
	private void Write(String sb,String path) throws FileNotFoundException {
		FileUtil fileUtil = new FileUtil();
		String spath =ServletActionContext.getServletContext().getRealPath("/");
		fileUtil.createTxt(spath,path,sb.toString(),"UTF-8");
	}
	


	/**
	 * 拼接柱状图
	 * @param lists
	 * @return
	 */
	private StringBuffer genDarData(List<String> lists,String title) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><chart><series>");
		int j=0;
		for(int i=0;i<lists.size();i++){
			sb.append("<value xid=\""+(j++)+"\">"+lists.get(i++)+"</value>");
		}
		sb.append("</series><graphs><graph gid=\"30\" color=\"#FFCC00\" gradient_fill_colors=\"#111111, #1A897C\">");
		j=0;
		for(int i=0;i<lists.size();i++){
			sb.append("<value xid=\""+(j++)+"\" description=\"\" url=\"\">"+lists.get(++i)+"</value>");
		}
		sb.append("</graph></graphs><labels><label lid=\"0\"><x>0</x><y>20</y><rotate></rotate><width></width><align>center</align><text_color></text_color><text_size></text_size><text><![CDATA[<b>"+title+"</b>]]></text></label></labels></chart>");
		return sb;
	}
	
	/**
	 * 系统访问压力图
	 * @return
	 * @throws Exception 
	 */
	public String onlineinfo() throws Exception{
		String sql="select t.a1,nvl(a.countnum,0) from (select * from ONLINE_INFO_T ) t left join  ( select to_char(login_time,'hh24') a1,count(*) countnum from login_log_p group by to_char(login_time,'hh24')) a on (a.a1 = t.a1) order by t.a1 ";
		List<String> execute = Execute(sql);
		StringBuffer sb = genDarData(execute, "");
		Write(sb.toString(),"stat\\chart\\onlineinfo\\data.xml");
		return "onlineInfo";
	}
}
