package assignment4;

import java.util.Scanner;
import java.util.Vector;

public class Util
{
    public static void readFile(Scanner in, Vector<String> vector)
    {
        while (in.hasNext())
        {
            vector.add(in.next().toLowerCase());
        }

        in.close();

    }

    public static Vertex<String> checkForExistingVertex(Vector<Vertex<String>> vertices, String curr, String next)
    {
        for (Vertex<String> vertex : vertices)
        {
            if (vertex.getName().equals(curr))
            {
                if (vertex.getMapKey().equals(next))
                {
                    return vertex;
                }
            }
        }
        return null;
    }

    public static boolean checkForExistingVertexName(Vector<Vertex<String>> vertices, String curr)
    {
        for(Vertex<String> vertex : vertices)
        {
            if(vertex.getName().equals(curr)) {
                return true;
            }
        }
        return false;
    }

    public static void getAllPossibleStartingVertices(Vector<Vertex<String>> vertices, Vector<Vertex<String>> verticesToCheck, String curr)
    {
        for(Vertex<String> vertex : vertices)
        {
            if(vertex.getName().equals(curr)) {
                verticesToCheck.add(vertex);
            }
        }
    }

    public static Vector<Vertex<String>> getAllPossibleEndingVertices(Vector<Vertex<String>> vertices, Vector<Vertex<String>> verticesToCheck)
    {
        Vector<Vertex<String>> endingVertices = new Vector<>();

        for(Vertex<String> beginningVertex : verticesToCheck)
        {
            for (Vertex<String> vertex : vertices)
            {
                if(beginningVertex.getMapKey().equals(vertex.getName())) // if beginning vertex mapkeyName equals vertex name
                {
                    endingVertices.add(vertex);
                }
            }
        }

        return endingVertices;
    }

    public static String getBestBridgeWord(Vector<Vertex<String>> endingVertices, String next)
    {
        if(endingVertices.size() == 0)
        {
            return null;
        } else if(endingVertices.size() == 1)
        {
            String wordKey = endingVertices.get(0).getMapKey();
            String word = endingVertices.get(0).getName();
            if(wordKey.equals(next))
            {
                return word;
            } else {
                return null;
            }
        } else {
            Vector<Vertex<String>> validVertices = new Vector<>();
            for(Vertex<String> vertex : endingVertices)
            {
                if(vertex.getMapKey().equals(next))
                {
                    validVertices.add(vertex);
                }
            }
            if(validVertices.size() > 0)
            {
                Vertex<String> bestBridgeWord = greatestWeight(validVertices);
                return bestBridgeWord.getName();
            } else {
                return null;
            }
        }
    }

    public static Vertex<String> greatestWeight(Vector<Vertex<String>> validVertices)
    {

        Vertex<String> greatestWeightedVertex = validVertices.get(0);
        for(Vertex<String> vertex : validVertices)
        {
            if(vertex.getMapValue() > greatestWeightedVertex.getMapValue())
            {
                greatestWeightedVertex = vertex;
            }
        }

        return greatestWeightedVertex;
    }

}
