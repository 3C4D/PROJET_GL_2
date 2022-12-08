package projet.ai_engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import javax.net.ssl.TrustManager;
import java.util.List;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.*;
import java.lang.Math;
public class TestNode {
    @Test
	public void TestNodeCreation() throws Exception{
        int n=2;
        Node node=new Node(1,1,1+" "+1,1*n+1,1);
        assertEquals(node.x,1);
        assertEquals(node.y,1);
        assertEquals(node.name,"1 1");
        assertEquals(node.indice,3);
        assertEquals(node.cost,1);
    }

}
