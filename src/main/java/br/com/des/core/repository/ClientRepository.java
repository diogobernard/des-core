package br.com.des.core.repository;

import br.com.des.core.Util;
import br.com.des.core.model.Client;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by diogobernard on 22/04/17.
 */

@Repository
public class ClientRepository {

    private MongoDatabase db;

    public ClientRepository(){

        MongoClientURI uri  = new MongoClientURI("mongodb://des-core:des123@localhost:27017/des-core");
        MongoClient client = new MongoClient(uri);
        db = client.getDatabase(uri.getDatabase());
    }

    public void save(Client client){
        MongoCollection<Document> collection = (MongoCollection<Document>) db.getCollection("Client");
        Document document = buildDocument(client);

        collection.insertOne(document);

        System.out.println("SALVO o cliente: " + client);
    }

    public Client update(Client oldClient,  Client newclient){
        MongoCollection<Document> collection = (MongoCollection<Document>) db.getCollection("Client");
        Document oldDocument = buildDocument(oldClient);
        Document newDocument = buildDocument(newclient);

        BasicDBObject updateDocument = new BasicDBObject();
         updateDocument.put("$set", newDocument);

        UpdateResult updateResult = collection.updateOne(oldDocument,updateDocument);

        return newclient;
    }

    public void removeClient(Client client){
        MongoCollection<Document> collection = (MongoCollection<Document>) db.getCollection("Client");
        Document document = buildDocument(client);
        collection.deleteOne(document);

        System.out.println("REMOVIDO o cliente: " + client);

    }

    public Client findByLogin(String login) throws ParseException {
        MongoCollection<Document> collection = (MongoCollection<Document>) db.getCollection("Client");
        BasicDBObject document = new BasicDBObject();
        document.put("login", login);
        Document clientDocument = collection.find(document).first();
        return getClient(clientDocument);
    }

    public List<Client> findAll() throws ParseException {
        MongoCollection<Document> collection = (MongoCollection<Document>) db.getCollection("Client");
        List<Client> listClient = new ArrayList<Client>();
        FindIterable<Document> listDocument = collection.find();
        if(listDocument != null) {
            MongoCursor<Document> iterator = listDocument.iterator();
            while (iterator.hasNext()) {
                Document d = iterator.next();
                Client c = getClient(d);
                listClient.add(c);
            }
        }
        return listClient;
    }

    private Client getClient(Document clientDocument) throws ParseException {
        if(clientDocument == null){
            return null;
        }
        Client client = new Client(clientDocument.getString("login"),
                clientDocument.getString("name"),
                clientDocument.getInteger("age"),
                clientDocument.getDate("birthday"));

        return client;
    }

    private Document buildDocument(Client client){
        if(client == null){
            return null;
        }
        Document document = new Document();
        document.put("name", client.getName());
        document.put("age", client.getAge());
        document.put("birthday", client.getBirthday());
        document.put("login", client.getLogin());
        return document;
    }
}
