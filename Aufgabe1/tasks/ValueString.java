/**
 * Created by kogamas on 15.07.15.
 */
public class ValueString implements Value {
    String value;

    public ValueString(String value){
        this.value = value;
    }

    @Override
    public int toI() {
        return 0;
    }

    @Override
    public String toS() {
        return this.value;
    }
}