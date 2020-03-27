package simulator.factories;

import org.json.JSONObject;

import java.util.List;

public class BuilderBasedFactory<T> implements Factory<T> {
    private final List<Builder<T>> _builders;

    public BuilderBasedFactory(List<Builder<T>> builders) {
        _builders = builders;
    }

    @Override
    public T createInstance(JSONObject info) {
        if (info != null) {
            for (Builder<T> bb : _builders) {
                T o = bb.createInstance(info);
                if (o != null)
                    return o;
            }
        }

        throw new IllegalArgumentException("Invalid value for createInstance: " + info);
    }
}
