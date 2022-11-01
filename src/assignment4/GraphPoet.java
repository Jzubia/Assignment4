
package assignment4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GraphPoet {

    Vector<Vertex<String>> vertices;
    /**
     *
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */

    public GraphPoet(File corpus) throws IOException {

        /* Read in the File and place into graph here */
        Scanner in = new Scanner(corpus);
        Vector<String> vcorpus = new Vector<>();
        Vector<Vertex<String>> vertices = new Vector<>();

        Util.readFile(in, vcorpus); // Reads the file and places the file into a vector called vcorpus

        for(int i = 0; i < vcorpus.size(); i++)
        {
            String nameOfCurrentVertex = vcorpus.get(i); // Get the name of the current vertex

            Map<String, Integer> edges = new HashMap<>(); // creates a new map

            Vertex<String> currentVertex = new Vertex<>(nameOfCurrentVertex, edges); // creates a vertex

            if(i + 1 < vcorpus.size()) // if there is a next element in the vector
            {
                String nameOfNextVertex = vcorpus.get(i + 1); // gets the name of the next vertex
                currentVertex.setMap(nameOfNextVertex, 1); // creates a mapping to the new vertex and sets the weight to 1
                Vertex<String> resultingVertex = Util.checkForExistingVertex(vertices, nameOfCurrentVertex, nameOfNextVertex);
                if(resultingVertex != null)
                {
                    String key = resultingVertex.getMapKey();
                    resultingVertex.getMap().put(key, resultingVertex.getMap().get(key) + 1);
                } else
                {
                    vertices.add(currentVertex);
                }
            } else if (i + 1 == vcorpus.size()) {
                if(!Util.checkForExistingVertexName(vertices, currentVertex.getName()))
                {
                    currentVertex.setMap(null, 0);
                    vertices.add(currentVertex);
                }
            }
        }

        this.vertices = vertices;

    }

    /**
     * Generate a poem.
     *
     * @param input File from which to create the poem
     * @return poem (as described above)
     */
    public String poem(File input) throws IOException {

        /* Read in input and use graph to complete poem */
        Scanner in = new Scanner(input);
        Vector<String> originalPoem = new Vector<>();
        Util.readFile(in, originalPoem);
        String poem = "";

        for(int i = 0; i < originalPoem.size(); i++)
        {
            String currentWord = originalPoem.get(i);
            poem = poem.concat(currentWord + " ");
            if(i + 1 < originalPoem.size())
            {
                String nextWord = originalPoem.get(i + 1);
                if(Util.checkForExistingVertexName(vertices, currentWord))
                {
                    Vector<Vertex<String>> verticesToCheck = new Vector<>(); // Will hold all possible bridge words
                    Util.getAllPossibleStartingVertices(vertices, verticesToCheck, currentWord);

                    Vector<Vertex<String>> newVerticesToCheck = Util.getAllPossibleEndingVertices(vertices, verticesToCheck);

                    String bridgeWord = Util.getBestBridgeWord(newVerticesToCheck, nextWord);

                    if(bridgeWord != null)
                    {
                        poem = poem.concat(bridgeWord + " ");
                    }
                }
            }
        }

        return poem;
    }

}
