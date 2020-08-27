package com.lbr.batchprocessing.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Repository;

import com.lbr.batchprocessing.model.BiggestSale;
import com.lbr.batchprocessing.model.Sale;
import com.lbr.batchprocessing.model.WorstSalesman;

@Repository
public class SaleCustomRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	/***
	 * Responsible for find biggest sale in mongoDB, equivalent the query
	 * db.getCollection('sales').aggregate([ { "$unwind": "$items" }, { "$group": {
	 * "_id" : "$saleId", "total": { $sum: {$multiply: [ "$items.itemPrice",
	 * "$items.itemQuantity" ]}}} }, { "$sort" : { total: -1 } }, { "$limit" : 1 }
	 * ])
	 * 
	 * @return
	 */
	public BiggestSale findBiggestSale() {
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.unwind("items"),
				Aggregation.group("saleId")
						.sum(ArithmeticOperators.Multiply.valueOf("items.price").multiplyBy("items.quantity"))
						.as("total"),
				Aggregation.project("total").and("saleId").previousOperation(),
				Aggregation.sort(Sort.Direction.DESC, "total"), Aggregation.limit(1));
		AggregationResults<BiggestSale> aggregationResults = mongoTemplate.aggregate(aggregation, Sale.class,
				BiggestSale.class);
		BiggestSale biggestSale = aggregationResults.getUniqueMappedResult();
		return biggestSale;
	}

	/***
	 * Responsible for find the worst seller in the mongoDB sales collection, equivalent the query
	 * db.getCollection('sales').aggregate([ { "$unwind": "$items" }, { "$group": {
	 * "_id" : "$salesManName", "total": { $sum: {$multiply: [ "$items.itemPrice",
	 * "$items.itemQuantity" ]}}} }, { "$sort" : { total: 1 } }, { "$limit" : 1 } ])
	 * 
	 * @return
	 */
	public WorstSalesman findWorstSeller() {
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.unwind("items"),
				Aggregation.group("salesmanName")
						.sum(ArithmeticOperators.Multiply.valueOf("items.price").multiplyBy("items.quantity"))
						.as("total"),
				Aggregation.project("total").and("salesmanName").previousOperation(),
				Aggregation.sort(Sort.Direction.ASC, "total"), Aggregation.limit(1));
		AggregationResults<WorstSalesman> aggregationResults = mongoTemplate.aggregate(aggregation, Sale.class,
				WorstSalesman.class);
		WorstSalesman worstSeller = aggregationResults.getUniqueMappedResult();
		return worstSeller;
	}

}
