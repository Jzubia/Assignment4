package assignment4;

import java.util.Map;

public class Vertex<T>{
    private T name;
    private Map<T, Integer> edges; // T is a vertex, Integer is the weight

    public Vertex(T name, Map<T, Integer> edges)
    {
        this.name = name;
        this.edges = edges;
    }

    public T getName()
    {
        return name;
    }

    public void setName(T name)
    {
        this.name = name;
    }

    public Map<T, Integer> getMap()
    {
        return edges;
    }

    public void setMap(T vertex, Integer weight)
    {
        edges.put(vertex, weight);
    }

    public String getMapKey()
    {
        String mapKey = edges.keySet().toString();
        mapKey = mapKey.substring(1, mapKey.length() - 1); // deletes the brackets from the mapkey
        return mapKey;
    }

    public Integer getMapValue()
    {
        String mapValue = edges.values().toString();
        mapValue = mapValue.substring(1, mapValue.length() - 1);
        return Integer.valueOf(mapValue);
    }
}
