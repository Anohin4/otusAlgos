package bloomfilter;

public interface BloomFilter {

    boolean probablyContains(String object);
    void add(String object);

}
