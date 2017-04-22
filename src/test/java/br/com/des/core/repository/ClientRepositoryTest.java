package br.com.des.core.repository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import br.com.des.core.model.Client;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by diogobernard on 22/04/17.
 */

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration
public class ClientRepositoryTest {

    @InjectMocks
    private ClientRepository repository;
    @Mock
    private MongoDatabase db;
    @Mock
    private Document document;
    @Mock
    private MongoCollection<Document> collection;
    @Mock
    private UpdateResult updateResult;
    @Mock
    private DeleteResult deleteResult;
    @Mock
    private FindIterable listDocument;
    @Mock
    private MongoCursor<Document> iterator;

    private Client client = null;
    private Client client2 = null;

    @Before
    public void setUp() {

        client = new Client("login","nome",30,new Date());
        client2 = new Client("login2","nome2",30,new Date());
        when(db.getCollection(Mockito.any(String.class))).thenReturn(collection);

        Document doc = new Document();
        doc.put("login","login");
        doc.put("name","nome");
        doc.put("age",30);
        doc.put("birthday",new Date());


        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                return null;
            }
        }).when(collection).insertOne(Mockito.any(Document.class));

        when(collection.updateOne(Mockito.any(Document.class),Mockito.any(Document.class))).thenReturn(updateResult);
        when(collection.deleteOne(Mockito.any(Document.class))).thenReturn(deleteResult);
        when(collection.find(Mockito.any(Document.class))).thenReturn(listDocument);
        when(collection.find(Mockito.any(Document.class)).first()).thenReturn(doc);
        when(collection.find()).thenReturn(listDocument);
        when(listDocument.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true).thenReturn(false);
        when(iterator.next()).thenReturn(doc);
    }

    @Test
    public void testUpdateClient(){
        Client clientold = new Client("login","nome",30,new Date());
        Client clientnew = new Client("login2","nome2",30,new Date());
        Client cli = repository.update(clientold,clientnew);
        assertEquals("login2",cli.getLogin());
    }

    @Test
    public void testFindByLogin() throws ParseException {
        Client cli = repository.findByLogin("login");
        assertEquals("nome",cli.getName());
    }

    @Test
    public void testFindAlLClient() throws ParseException {
        List<Client> cli = repository.findAll();
        assertEquals(1,cli.size());
    }


}
