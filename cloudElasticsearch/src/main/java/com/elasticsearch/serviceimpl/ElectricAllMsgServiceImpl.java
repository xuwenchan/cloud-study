package com.elasticsearch.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.elasticsearch.model.electricAllMsg;
import com.elasticsearch.repository.ElectricAllMsgRepository;
import com.elasticsearch.service.ElectricAllMsgService;

@Service
public class ElectricAllMsgServiceImpl implements ElectricAllMsgService{

	@Autowired
	ElectricAllMsgRepository electricAllMsgRepository;
	
	@Override
	public List<electricAllMsg> queryTodayAlarmCouont() {
		QueryBuilder builder=QueryBuilders.termQuery("electricid",12927);
		Iterable<electricAllMsg> it= electricAllMsgRepository.search(builder);
		Iterator iterator= it.iterator();
		if(iterator.hasNext()) {
			electricAllMsg ele=(electricAllMsg) iterator.next();
			System.out.println(ele.getElectmsgid()+","+ele.getStatus());
		}
		return null;
	}
	
	public void searchQueryDemo() {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start;
		try {
			start = format.parse("2019-01-01 00:00:00");
			System.out.println("start:"+start.getTime()+",end"+new Date().getTime());
			BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
			QueryBuilder status=QueryBuilders.termQuery("status", 3);
			RangeQueryBuilder rangeQueryBuilder=QueryBuilders.rangeQuery("rcvtime").gt(start.getTime()).lt(new Date().getTime());
			boolQueryBuilder.must(status);
			boolQueryBuilder.must(rangeQueryBuilder);
			Page<electricAllMsg> page=  electricAllMsgRepository.search(boolQueryBuilder,PageRequest.of(1, 10));
			System.out.println("总数:"+page.getTotalElements());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	

}
