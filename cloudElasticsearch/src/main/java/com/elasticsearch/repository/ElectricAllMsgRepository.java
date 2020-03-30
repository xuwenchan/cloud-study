package com.elasticsearch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.elasticsearch.model.electricAllMsg;

@Component
public interface ElectricAllMsgRepository extends ElasticsearchRepository<electricAllMsg,Long>{

}
