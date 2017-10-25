package cn.blingfeng.mongo.demo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;


import java.util.ArrayList;
import java.util.List;

/**
 * @author : blingfeng
 * @date : 2017/10/25
 * @description
 **/
public class MongoDemo {

    public static void main(String[] args){
        /**
         * 连接的ip和端口
         */
        MongoClient mongoClient = new MongoClient("localhost",27017);
        /**
         * 数据库不存在会自动创建
         */
        MongoDatabase mydatabase = mongoClient.getDatabase("mydatabase");
        /**
         * 创建一个集合，也就相当于一张表
         */
        mydatabase.createCollection("student");
        /**
         * 获取集合
         */
        MongoCollection<Document> student = mydatabase.getCollection("student");
        /**
         * mongodb的每条记录是以文档为单位的
         */
        Document document = new Document("name","blingfeng");
        /**
         * 还可以追加字段
         */
        document.append("age",20);
        document.append("_id",10000);
        /**
         * 插入一条记录
         */
        student.insertOne(document);

        List<Document> documents = new ArrayList<>();
        documents.add(document);
        documents.add(document);
        /**
         * 一次插入多条记录
         */
        student.insertMany(documents);
        Document delOne = new Document("age",20);
        /**
         * 删除age 为20的文档
         */
        student.deleteOne(delOne);
        /**
         * 找到文档 并删除
         * return 被删除的文档对象
         */
        Document foad = student.findOneAndDelete(delOne);

        Document updateOne = new Document("name","gaofeng");
        updateOne.append("age",18);
        /**
         * 根据文档更新 前者为条件 后者为更新后的文档
         */
        UpdateResult updateResult = student.updateOne(document, updateOne);


    }
}
