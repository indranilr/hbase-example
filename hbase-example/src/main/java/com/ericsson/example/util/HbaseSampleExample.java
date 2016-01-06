package com.ericsson.example.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.security.UserGroupInformation;

import com.ericsson.example.bo.EventsData;


public class HbaseSampleExample {
	
	private static String ZOOKEEPER_IP = "XXX";
	private static String ZOOKEEPER_PORT = "5181";
	private static String ClientRetry = "2";
	private static String Timeout = "1000";
	private static String RecoveryRetry = "2";
	private static String Username = "ericsson";

	private static String Tablename = "events_table";
	
	private static int HTABLE_POOL_SIZE = 15;
    
	private HTableInterface tableInterface = null;
	
	public HTableInterface connectDB() throws IOException {
		
		
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", ZOOKEEPER_IP);
		conf.set("hbase.zookeeper.property.clientPort", ZOOKEEPER_PORT);

		conf.set("hbase.client.retries.number", ClientRetry);
		conf.set("zookeeper.session.timeout", Timeout);
		conf.set("zookeeper.recovery.retry", RecoveryRetry);
		
		conf.set("hbase.defaults.for.version.skip", "false");
		
		
		HTablePool hTablePool = new HTablePool(conf, HTABLE_POOL_SIZE);
		
		UserGroupInformation realUser = UserGroupInformation.createRemoteUser(Username);
		UserGroupInformation.setLoginUser(realUser);
		
		tableInterface= hTablePool.getTable(Tablename);
		
		return tableInterface;
	}
	
	public void closeConnection() throws IOException{
		
		tableInterface.close();
		
	}
	
	public List<EventsData> scan (String startRowKey, String endRowKey) throws IOException{
		
		List<EventsData> eventResults = new ArrayList<EventsData>();
		
		// create and execute a scan
		 Scan scan = new Scan( Bytes.toBytes( startRowKey ), Bytes.toBytes( endRowKey ) );
		 ResultScanner results = tableInterface.getScanner(scan);
		 
		 for(Result result: results){
			 
			 EventsData eventsData = new EventsData();
			 eventsData.setMoid(Bytes.toString(result.getValue(Bytes.toBytes("edata"), Bytes.toBytes("moid"))));
			 eventsData.setCountertime(Bytes.toString(result.getValue(Bytes.toBytes("edata"), Bytes.toBytes("countertime"))));
			 eventsData.setNodename(Bytes.toString(result.getValue(Bytes.toBytes("edata"), Bytes.toBytes("nodename"))));
			 eventsData.setPmSchedActivityCellDl(Bytes.toString(result.getValue(Bytes.toBytes("edata"), Bytes.toBytes("pmSchedActivityCellDl"))));
			 eventsData.setPmUeThpTimeDl(Bytes.toString(result.getValue(Bytes.toBytes("edata"), Bytes.toBytes("pmUeThpTimeDl"))));
			 
			 eventResults.add(eventsData);
		 }
		
		return eventResults;
	}
	
}
