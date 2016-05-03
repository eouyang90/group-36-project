package group36.cpr;

/**
 * Created by sandeepsubramanian on 5/1/16.
 */
public class Tuple {
    private Integer t;
    private Float d;
    public Tuple(Integer ts, Float dep) {
        this.t = ts;
        this.d = dep;
    }
    public Integer getInt() {
        return this.t;
    }
    public Float getFloat() {
        return this.d;
    }
}
