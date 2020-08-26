package com.lbr.batchprocessing.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClients;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@Configuration
public class MongoEmbeddedConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(MongoEmbeddedConfiguration.class);
	private MongodExecutable mongodExecutable;
	
    public void clean() {
        mongodExecutable.stop();
    }

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		String ip = "localhost";
		int port = 27017;
		IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
				.net(new Net(ip, port, Network.localhostIsIPv6())).build();
		MongodStarter starter = MongodStarter.getDefaultInstance();
		mongodExecutable = starter.prepare(mongodConfig);
		mongodExecutable.start();
		MongoTemplate mongoTemplate = new MongoTemplate(MongoClients.create(), "embeded_db");
		return mongoTemplate;
	}

}
