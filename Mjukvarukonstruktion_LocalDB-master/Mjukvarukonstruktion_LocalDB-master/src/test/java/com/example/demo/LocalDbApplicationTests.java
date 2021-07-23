package com.example.demo;

import com.example.demo.data.MongoDb;
import com.example.demo.data.mySQL;
import com.example.demo.model.JournalRecord;
import com.example.demo.model.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ContextConfiguration(classes= LocalDbApplication.class)
@SpringBootTest
class LocalDbApplicationTests {

	@Autowired
	private RestController controller;

	private MongoDb mongoDb;

	private mySQL mySql;

	@Test
	public void contextLoad() throws Exception{
		assertThat(controller).isNotNull();
	}

	@Test
	public void testMongoDb(){
		JournalRecord jRecord1 = new JournalRecord("testCategory", "testContent", "testDate");
		mongoDb = new MongoDb(27017);

		Assertions.assertDoesNotThrow(() ->{
			mongoDb.saveToDB(jRecord1);
			List<JournalRecord> list = mongoDb.getAllJournalRecords();
			Assertions.assertTrue(list.size() > 0, "The list size is less than 0");
		});
	}

	@Test
	public void testMySql(){
		JournalRecord jRecord1 = new JournalRecord("testCategory", "testContent", "testDate");
		mySql = new mySQL("testuser", "testpassword", 3306);

		Assertions.assertDoesNotThrow(() ->{
			mySql.saveToDB(jRecord1);
			List<JournalRecord> list = mySql.getAllJournalRecords();
			Assertions.assertTrue(list.size() > 0, "The list size is less than 0");
		});
	}

	@Test
	public void testParser(){
		String date = LocalDate.now().toString();
		JournalRecord jr = new JournalRecord("category", "content", date);
		String recordString = Parser.RecordToString(jr);
		Assertions.assertNotNull(recordString, "Object is null");

		JournalRecord parseRecord = Parser.StringToJournalRecord(recordString);
		Assertions.assertNotNull(parseRecord, "Object is null");
	}
}
