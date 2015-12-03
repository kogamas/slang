/**
 * Created by kogamas on 15.07.15.
 */
public class ValueInt implements Value {
    int value;

    public ValueInt(int value){
        this.value = value;
    }

    @Override
    public int toI() {
        return this.value;
    }

    @Override
    public String toS() {
        return this.value+"";
    }
}
