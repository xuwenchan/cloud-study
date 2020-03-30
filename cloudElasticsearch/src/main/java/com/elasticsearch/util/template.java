/*package com.elasticsearch.util;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
 
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.cluster.health.ClusterIndexHealth;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.Filter;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
 
public class template {
	
	public static TransportClient client;
	
	
	public static void main(String[] args) {
		try {
			initElasticSearch();
			test4();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void initElasticSearch() {
		//指定ES集群
		Settings setting =Settings.builder().put("cluster.name","my-application").build();
		//创建访问es服务器的客户端
		try {
			client=new PreBuiltTransportClient(setting).addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.75.128"),9300));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	*//**
	 * GET查询
	 * @throws Exception
	 *//*
	public static void test1() throws Exception {
		//指定ES集群
		Settings setting =Settings.builder().put("cluster.name","my-application").build();
		//创建访问es服务器的客户端
		TransportClient client=new PreBuiltTransportClient(setting).addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.75.128"),9300));
		//查询数据
		GetResponse response=client.prepareGet("lib3","user","1").execute().actionGet();
		
		System.out.println(response.getSourceAsString());
		//关闭
		client.close();
	}
	
	
	public static void test2() {
		//向es添加文档
		*//**创建mapping
		 * PUT /index1
		{
		  "settings": {
		    "number_of_shards": 1,
		    "number_of_replicas": 0
		  },
		  "mappings": {
		    "blog":{
		      "properties":{
		        "id":{
		          "type":"long"
		        },
		        "title":{
		          "type":"text",
		          "analyzer":"ik_max_word"
		        },
		        "content":{
		          "type":"text",
		          "analyzer":"ik_max_word"
		        },
		        "postdate":{
		          "type":"date"
		        },
		        "url":{
		          "type":"text"
		        }
		      }
		    }
		    
		  }
		}
				 *//*
		try {
			XContentBuilder doc=XContentFactory.jsonBuilder().startObject()
					.field("id","1")
					.field("title","java设计模式")
					.field("content","java编程语言")
					.field("postdate","2018-05-20")
					.field("url","www.baidu.com")
					.endObject();
			//添加文档
			IndexResponse response =client.prepareIndex("index1","blog","10")
					.setSource(doc).get();
			System.out.println(response.status());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//删除文档
	public static void test3() {
		DeleteResponse response=client.prepareDelete("index1", "blog", "10").get();
		
		System.out.println(response.status());
	}
	
	//以put方式更新文档
	public static void test4() throws Exception {
		UpdateRequest request=new UpdateRequest();
		
		request.index("index1").type("blog").id("10")
		.doc(
				XContentFactory.jsonBuilder().startObject()
				.field("title","java设计模式之单例模式").endObject()
		);
		
		UpdateResponse response=client.update(request).get();
		System.out.println(response.status());
		
	}
	
	//upsert方式更新文档，文档不存在添加，存在更新
	public static void test5() throws Exception {
		IndexRequest request=new IndexRequest("index1","type","8")
				.source(
						XContentFactory.jsonBuilder().startObject()
						.field("id","2")
						.field("title","java设计模式")
						.field("content","java编程语言")
						.field("postdate","2018-05-20")
						.field("url","www.baidu.com")
						.endObject()
				);
		UpdateRequest request2=new UpdateRequest().doc(
				XContentFactory.jsonBuilder().startObject()
				.field("title","java设计模式之单例模式").endObject()
		).upsert(request);
				
		UpdateResponse response =client.update(request2).get();
		System.out.println(response.status());
	}
	
	//通过mget实现批量操作
	public static void mget() {
		MultiGetResponse reponse=client.prepareMultiGet()
				.add("index1","blog","8","10")
				.add("lib","user","1","2","3")
				.get();
		for(MultiGetItemResponse item:reponse) {
			GetResponse gr=item.getResponse();
			if(gr!=null&&gr.isExists()) {
				System.out.println(gr.getSourceAsString());
			}
		}
	}
	
	//bulk批量操作
	public static void bulk() throws IOException {
		BulkRequestBuilder bulkBuild =client.prepareBulk();
		//批量操作
		bulkBuild.add(client.prepareIndex("lib2","books","8")
				.setSource(
						XContentFactory.jsonBuilder()
							.startObject()
							.field("title","python")
							.field("price","9")
							.endObject()
						)
				);
		bulkBuild.add(client.prepareIndex("lib2","books","9")
				.setSource(
						XContentFactory.jsonBuilder()
							.startObject()
							.field("title","C")
							.field("price","99")
							.endObject()
						)
				);
		BulkResponse response=bulkBuild.get();
		System.out.println(response.status());
		if(response.hasFailures()) {
			System.out.println("添加失败");
		}
		
	}
	
	*//**
	 * 查询之后删除
	 * 
	 * 查询title中含有“工厂”的
	 * 删除掉
	 * 
	 *//*
	public static void queryAndDelete() {
		BulkByScrollResponse response=DeleteByQueryAction.INSTANCE
				.newRequestBuilder(client)
				.filter(QueryBuilders.matchQuery("title", "工厂"))
				.source("index1")
				.get();
		long count=response.getDeleted();//删除文档的个数
		System.out.println(count);
		
	}
	
	public static void match_all() {
		
		QueryBuilder dp=QueryBuilders.matchAllQuery();
		
		SearchResponse sr=client.prepareSearch("lib3")
				.setQuery(dp)
				.setSize(3).get();
		SearchHits hist=sr.getHits();
		for(SearchHit hit:hist) {
			System.out.println(hit.getSourceAsString());
			Map<String,Object> map=hit.getSourceAsMap();
			for(String key:map.keySet()) {
				System.out.println(key+"="+map.get(key));
			}
		}
	}
	
	*//**
	 * match 查询
	 * 
	 *//*
	
	public static void matchQuery() {
		QueryBuilder builder=QueryBuilders.matchQuery("interests", "song");
		SearchResponse response =client.prepareSearch("lib3")
				.setQuery(builder)
				.setSize(3)
				.get();
		
		SearchHits hist=response.getHits();
		for(SearchHit hit:hist) {
			System.out.println(hit.getSourceAsString());
			Map<String,Object> map=hit.getSourceAsMap();
			for(String key:map.keySet()) {
				System.out.println(key+"="+map.get(key));
			}
		}
	}
	
	
	public static void multiMatchQuery() {
		QueryBuilder qb=QueryBuilders.multiMatchQuery("address", "interests");
		SearchResponse response =client.prepareSearch("lib3")
				.setQuery(qb)
				.setSize(3)
				.get();
		SearchHits hist=response.getHits();
		for(SearchHit hit:hist) {
			System.out.println(hit.getSourceAsString());
			Map<String,Object> map=hit.getSourceAsMap();
			for(String key:map.keySet()) {
				System.out.println(key+"="+map.get(key));
			}
		}
	}
	
	//term 查询
	public static void term() {
		QueryBuilder builder=QueryBuilders.termQuery("interests", "song");
		SearchResponse response=client.prepareSearch("lib3")
				.setQuery(builder)
				.setSize(3).get();
		SearchHits hist=response.getHits();
		for(SearchHit hit:hist) {
			System.out.println(hit.getSourceAsString());
			Map<String,Object> map=hit.getSourceAsMap();
			for(String key:map.keySet()) {
				System.out.println(key+"="+map.get(key));
			}
		}
	}
	
	
	//terms 查询
		public static void terms() {
			QueryBuilder builder=QueryBuilders.termsQuery("interests", "song","lvyou");
			SearchResponse response=client.prepareSearch("lib3")
					.setQuery(builder)
					.setSize(3).get();
			SearchHits hist=response.getHits();
			for(SearchHit hit:hist) {
				System.out.println(hit.getSourceAsString());
				Map<String,Object> map=hit.getSourceAsMap();
				for(String key:map.keySet()) {
					System.out.println(key+"="+map.get(key));
				}
			}
		}
	
		//range 查询
		public static void rangeQuery() {
			QueryBuilder builder=QueryBuilders.rangeQuery("birthday").format("1990-01-10").to("2000-12-20").format("yyyy-MM-dd");
			SearchResponse response=client.prepareSearch("lib3")
				.setQuery(builder)
				.setSize(3).get();
				SearchHits hist=response.getHits();
				for(SearchHit hit:hist) {
					System.out.println(hit.getSourceAsString());
					Map<String,Object> map=hit.getSourceAsMap();
					for(String key:map.keySet()) {
						System.out.println(key+"="+map.get(key));
					}
				}
		}
		
		public static void prefixQuery() {
			QueryBuilder builder=QueryBuilders.prefixQuery("name", "zhang");
			SearchResponse response=client.prepareSearch("lib3")
					.setQuery(builder)
					.setSize(3).get();
					SearchHits hist=response.getHits();
					for(SearchHit hit:hist) {
						System.out.println(hit.getSourceAsString());
						Map<String,Object> map=hit.getSourceAsMap();
						for(String key:map.keySet()) {
							System.out.println(key+"="+map.get(key));
						}
					}
		}
	
		//wildcard 查询
		public static void wildcard() {
			QueryBuilder builder=QueryBuilders.wildcardQuery("name", "zhang*");
			SearchResponse response=client.prepareSearch("lib3")
					.setQuery(builder)
					.setSize(3).get();
					SearchHits hist=response.getHits();
					for(SearchHit hit:hist) {
						System.out.println(hit.getSourceAsString());
						Map<String,Object> map=hit.getSourceAsMap();
						for(String key:map.keySet()) {
							System.out.println(key+"="+map.get(key));
						}
					}
		}
	
		//fuzzy 查询
				public static void fuzzy() {
					QueryBuilder builder=QueryBuilders.fuzzyQuery("interests", "sog");
					SearchResponse response=client.prepareSearch("lib3")
							.setQuery(builder)
							.setSize(3).get();
							SearchHits hist=response.getHits();
							for(SearchHit hit:hist) {
								System.out.println(hit.getSourceAsString());
								Map<String,Object> map=hit.getSourceAsMap();
								for(String key:map.keySet()) {
									System.out.println(key+"="+map.get(key));
								}
							}
				}
	
				//type 查询
				public static void type() {
					QueryBuilder builder=QueryBuilders.typeQuery("blog");
					SearchResponse response=client.prepareSearch("index1")
							.setQuery(builder)
							.setSize(3).get();
							SearchHits hist=response.getHits();
							for(SearchHit hit:hist) {
								System.out.println(hit.getSourceAsString());
								Map<String,Object> map=hit.getSourceAsMap();
								for(String key:map.keySet()) {
									System.out.println(key+"="+map.get(key));
								}
							}
				}
	
				//ids 查询
				public static void ids() {
					QueryBuilder builder=QueryBuilders.idsQuery().addIds("1","3");
					SearchResponse response=client.prepareSearch("lib3")
							.setQuery(builder)
							.setSize(3).get();
							SearchHits hist=response.getHits();
							for(SearchHit hit:hist) {
								System.out.println(hit.getSourceAsString());
								Map<String,Object> map=hit.getSourceAsMap();
								for(String key:map.keySet()) {
									System.out.println(key+"="+map.get(key));
								}
							}
				}
				
				//max 查询
				public static void max() {
					AggregationBuilder agg=AggregationBuilders.max("aggMax").field("age");
					SearchResponse response=client.prepareSearch("lib3")
							.addAggregation(agg).get();
					Max max=response.getAggregations().get("aggMax");
					System.out.println(max.getValue());
					
					
				}
				//min 查询
				public static void min() {
					AggregationBuilder agg=AggregationBuilders.min("aggMin").field("age");
					SearchResponse response=client.prepareSearch("lib3")
							.addAggregation(agg).get();
					Min min=response.getAggregations().get("aggMin");
					System.out.println(min.getValue());
					
					
				}
				
				//avg
				public static void avg() {
					AggregationBuilder agg=AggregationBuilders.avg("aggAvg").field("age");
					SearchResponse response=client.prepareSearch("lib3")
							.addAggregation(agg).get();
					Avg avg=response.getAggregations().get("aggAvg");
					System.out.println(avg.getValue());
				}
				
				
				//sum
				public static void sum() {
					AggregationBuilder agg=AggregationBuilders.avg("aggSum").field("age");
					SearchResponse response=client.prepareSearch("lib3")
							.addAggregation(agg).get();
					Sum sum=response.getAggregations().get("aggSum");
					System.out.println(sum.getValue());
				}
				
				//cardinality
				public static void cardinality() {
					AggregationBuilder agg=AggregationBuilders.cardinality("aggcardinality").field("age");
					SearchResponse response=client.prepareSearch("lib3")
							.addAggregation(agg).get();
					Cardinality cardinality=response.getAggregations().get("aggSum");
					System.out.println(cardinality.getValue());
				}
	
				public static void query() {
					QueryBuilder build=QueryBuilders.commonTermsQuery("name", "minghui");
					SearchResponse response=client.prepareSearch("lib3")
							.setQuery(build)
							.get();
							SearchHits hist=response.getHits();
							for(SearchHit hit:hist) {
								System.out.println(hit.getSourceAsString());
								Map<String,Object> map=hit.getSourceAsMap();
								for(String key:map.keySet()) {
									System.out.println(key+"="+map.get(key));
								}
							}
				}
				
				
				public static void queryStringQuery() {
					QueryBuilder build=QueryBuilders.queryStringQuery("+song -lvyou");
					SearchResponse response=client.prepareSearch("lib3")
							.setQuery(build)
							.get();
							SearchHits hist=response.getHits();
							for(SearchHit hit:hist) {
								System.out.println(hit.getSourceAsString());
								Map<String,Object> map=hit.getSourceAsMap();
								for(String key:map.keySet()) {
									System.out.println(key+"="+map.get(key));
								}
							}
				}
				
				public static void simpleQueryStringQuery() {
					QueryBuilder build=QueryBuilders.simpleQueryStringQuery("+song -lvyou");
					SearchResponse response=client.prepareSearch("lib3")
							.setQuery(build)
							.get();
							SearchHits hist=response.getHits();
							for(SearchHit hit:hist) {
								System.out.println(hit.getSourceAsString());
								Map<String,Object> map=hit.getSourceAsMap();
								for(String key:map.keySet()) {
									System.out.println(key+"="+map.get(key));
								}
							}
				}
				
				public static void boolQuery() {
					QueryBuilder build=QueryBuilders.boolQuery()
							.must(QueryBuilders.matchQuery("interests", "song"))
							.mustNot(QueryBuilders.matchQuery("address", "bei jing"))
							.filter(QueryBuilders.rangeQuery("birthday").gte("1990-01-01").format("yyyy-MM-dd"));
					SearchResponse response=client.prepareSearch("lib3")
							.setQuery(build)
							.get();
							SearchHits hist=response.getHits();
							for(SearchHit hit:hist) {
								System.out.println(hit.getSourceAsString());
								Map<String,Object> map=hit.getSourceAsMap();
								for(String key:map.keySet()) {
									System.out.println(key+"="+map.get(key));
								}
							}
					
				}
	
				
				public static void constantScoreQuery() {
					QueryBuilder build=QueryBuilders.constantScoreQuery(QueryBuilders.termQuery("name", "minghui"));
					SearchResponse response=client.prepareSearch("lib3")
							.setQuery(build)
							.get();
							SearchHits hist=response.getHits();
							for(SearchHit hit:hist) {
								System.out.println(hit.getSourceAsString());
								Map<String,Object> map=hit.getSourceAsMap();
								for(String key:map.keySet()) {
									System.out.println(key+"="+map.get(key));
								}
							}
					
				}
	
	
				//sum
				public static void group() {
					AggregationBuilder agg=AggregationBuilders.terms("terms").field("age");
					SearchResponse response=client.prepareSearch("lib3")
							.addAggregation(agg).execute().actionGet();
					Terms terms=response.getAggregations().get("terms");
					
					for(Terms.Bucket entry:terms.getBuckets()) {
						System.out.println(entry.getKey()+":"+entry.getDocCount());
					}
					
				}
				
				public static void filter() {
					QueryBuilder qeury=QueryBuilders.termQuery("age", 20);
					AggregationBuilder agg=AggregationBuilders.filter("filter", qeury);
					SearchResponse response =client.prepareSearch("lib3").addAggregation(agg).execute().actionGet();
					Filter filter=response.getAggregations().get("filter");
					System.out.println(filter.getDocCount());
				}
				
				
				public static void range() {
					AggregationBuilder agg=AggregationBuilders
							.range("range")
							.field("age")
							.addUnboundedTo(25)
							.addRange(25,50)
							.addUnboundedFrom(25);
					SearchResponse response=client.prepareSearch("lib3").execute().actionGet();
					Range r=response.getAggregations().get("range");
					for(Range.Bucket entry:r.getBuckets()) {
						System.out.println(entry.getKey()+":"+entry.getDocCount());
					}
				}
				
				public static void health() {
					ClusterHealthResponse healths=client.admin().cluster().prepareHealth().get();
					String clusterName=healths.getClusterName();
					System.out.println("clusterName="+clusterName);
					int numberOfDataNodes=healths.getNumberOfDataNodes();
					System.out.println("numberOfDataNodes:"+numberOfDataNodes);
					
					for(ClusterIndexHealth health:healths.getIndices().values()) {
						String index=health.getIndex();
						int numberOfShards=health.getNumberOfShards();
						int numberOfReplicas=health.getNumberOfReplicas();
						ClusterHealthStatus status=health.getStatus();
						System.out.println(status.toString());
					}
					
				}
}
 
*/